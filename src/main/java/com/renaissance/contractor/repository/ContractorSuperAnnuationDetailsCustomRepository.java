package com.renaissance.contractor.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.contractor.model.ContractorSuperAnnuationDetailsEntity;

public interface ContractorSuperAnnuationDetailsCustomRepository {
	
	ContractorSuperAnnuationDetailsEntity getSADetailsByContractorId(BigInteger contractorId);
	List<ContractorSuperAnnuationDetailsEntity> getAllSADetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);
}
