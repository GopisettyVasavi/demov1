package com.renaissance.contractor.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.contractor.model.ContractorABNDetailsEntity;
import com.renaissance.contractor.model.ContractorTFNDetailsEntity;

public interface ContractorAbnDetailsCustomRepository {
	ContractorABNDetailsEntity getAbnDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);
	List<ContractorABNDetailsEntity> getAllAbnDetailsByContractorId(BigInteger contractorId);
	
	
	List<ContractorTFNDetailsEntity> getTfnByContractorId(BigInteger contractorId);

}
