package com.renaissance.commission.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.commission.entity.RecruiterCommissionsEntity;

public interface RecruiterCommissionsCustomRepository {
	
	RecruiterCommissionsEntity getCommissionByContractorMonthYear(BigInteger contractorId, String monthYear, Double ratePerDay, String jobStartDate);
	List<RecruiterCommissionsEntity> getCommissionsForSelectedMonthAndYear(String monthYear);
}
