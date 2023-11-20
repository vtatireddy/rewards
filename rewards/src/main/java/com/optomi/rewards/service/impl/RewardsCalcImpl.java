package com.optomi.rewards.service.impl;

import com.optomi.rewards.entity.Transactions;
import com.optomi.rewards.repo.TransRepo;
import com.optomi.rewards.service.DTO.RewardsReqDTO;
import com.optomi.rewards.service.DTO.RewardsResDTO;
import com.optomi.rewards.service.RewardCalc;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardsCalcImpl implements RewardCalc {

    private TransRepo transRepo;
    public RewardsCalcImpl( TransRepo transRepo){
        this.transRepo=transRepo;
    }
    public  List<RewardsResDTO> calculateCustomerRewards(RewardsReqDTO reqDTO){
        Collection<Transactions> totalCustomersTrans = null;
        if(reqDTO.getCustomerId() == null) {
            totalCustomersTrans = transRepo.findLastThreeMonthTrans(LocalDate.now().minusMonths(3));
        }else{
            totalCustomersTrans = transRepo.findLastThreeMonthTransByCustId(LocalDate.now().minusMonths(3),2l);
        }
        Integer upperLimit=reqDTO.getUpperLimit();
        Integer lowerLimit= reqDTO.getLowerLimit();
      Map<String,Map<Integer,List<Transactions>>> transMap = totalCustomersTrans.stream().collect(Collectors.groupingBy(tran -> tran.getCustomer().getName(), Collectors.groupingBy( transaction -> transaction.getTransDTM().getMonthValue())));
      List<RewardsResDTO> customerDTOS= new ArrayList<>();

        for (String custName : transMap.keySet()){
            RewardsResDTO customer= new RewardsResDTO();
            customer.setCustomerName(custName);          
            Map<Integer,List<Transactions>> transactions= transMap.get(custName);
            Map<Integer,Integer> monthlyPointsMap= new HashMap<>();
           for( Map.Entry<Integer,List<Transactions> > monthlyTrans : transactions.entrySet()){
               int totalMonthlyPoints=0;
               for(Transactions dailyTrans :  monthlyTrans.getValue())
               {
                   totalMonthlyPoints += calculatePoints(dailyTrans.getTransAmt().intValue(),upperLimit,lowerLimit);
               }
               monthlyPointsMap.put(monthlyTrans.getKey(),totalMonthlyPoints);
           }
           customer.setMonthlyPoints(monthlyPointsMap);
           customer.setTotalPoints(monthlyPointsMap.values().stream().mapToInt(value -> value).sum());
           customerDTOS.add(customer);
        }

        return customerDTOS;

    }



    public  int calculatePoints(int purchase, int upperLimit, int lowerLimit) {

        // Purchase amount is 0
        if (purchase <= 0) return 0;

        // Calculate points until the purchase amount is > lowerLimit
        int points = 0;
        while (purchase > lowerLimit) {

            // No. of dollars above the upperLimit
            int dollarsAboveUppper = purchase - upperLimit;

            // No. of dollars above the lowerLimit
            int dollarsAboveLower = purchase - lowerLimit;

            // Check for available dollars above upperLimit
            if (dollarsAboveUppper > 0) {
                points += 2*dollarsAboveUppper;
                purchase -= dollarsAboveUppper;
            }
            // Check for available dollars above lowerLimit
            else if (dollarsAboveLower > 0) {
                points += 1*dollarsAboveLower;
                purchase -= dollarsAboveLower;
            }
        }
        return points;
    }



}
