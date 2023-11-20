package com.optomi.rewards.service.DTO;

import java.util.Map;

public class RewardsResDTO {
    private String customerName;
    private Map<Integer,Integer> monthlyPoints;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<Integer, Integer> getMonthlyPoints() {
        return monthlyPoints;
    }

    public void setMonthlyPoints(Map<Integer, Integer> monthlyPoints) {
        this.monthlyPoints = monthlyPoints;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    private Integer totalPoints;


}
