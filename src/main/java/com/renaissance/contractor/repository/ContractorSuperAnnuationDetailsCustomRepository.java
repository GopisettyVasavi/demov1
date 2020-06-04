package com.renaissance.contractor.repository;

import java.math.BigInteger;

import com.renaissance.contractor.model.ContractorSuperAnnuationDetailsEntity;

public interface ContractorSuperAnnuationDetailsCustomRepository {
	
	ContractorSuperAnnuationDetailsEntity getSADetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);
}
