package com.optomi.rewards.service;

import com.optomi.rewards.service.DTO.RewardsReqDTO;
import com.optomi.rewards.service.DTO.RewardsResDTO;

import java.util.List;

public interface RewardCalc {

    public List<RewardsResDTO> calculateCustomerRewards(RewardsReqDTO reqDTO);


}
