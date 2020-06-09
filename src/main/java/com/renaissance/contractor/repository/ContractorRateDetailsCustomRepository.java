package com.renaissance.contractor.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.contractor.model.ContractorRateDetailsEntity;

public interface ContractorRateDetailsCustomRepository {
	ContractorRateDetailsEntity getRateDetailsByContractorId(BigInteger contractorId);
	List<ContractorRateDetailsEntity> getAllRateDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);

}
