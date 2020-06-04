package com.renaissance.contractor.repository;

import java.math.BigInteger;

import com.renaissance.contractor.model.ContractorRateDetailsEntity;

public interface ContractorRateDetailsCustomRepository {
	ContractorRateDetailsEntity getRateDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);

}
