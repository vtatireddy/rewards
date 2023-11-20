package com.optomi.rewards.controllers;

import com.optomi.rewards.service.DTO.RewardsReqDTO;
import com.optomi.rewards.service.DTO.RewardsResDTO;
import com.optomi.rewards.service.impl.RewardsCalcImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardsController {


    private RewardsCalcImpl rewardsCalc;
   public RewardsController (RewardsCalcImpl rewardCalcImpl){
       this.rewardsCalc=rewardCalcImpl;
   }
   @GetMapping(path = "/getRewards")
    public List<RewardsResDTO> getRewards(){

       return rewardsCalc.calculateCustomerRewards(new RewardsReqDTO());


    }
}
