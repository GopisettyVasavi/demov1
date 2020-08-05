package com.renaissance.commission.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.commission.model.CommissionsDetailsEntity;

public interface CommissionsDetailsCustomRepository {
	
	CommissionsDetailsEntity getCommissionByContractorMonthYear(BigInteger contractorId, String monthYear, Double ratePerDay, String jobStartDate);
	List<CommissionsDetailsEntity> getCommissionsForSelectedMonthAndYear(String monthYear);
}
