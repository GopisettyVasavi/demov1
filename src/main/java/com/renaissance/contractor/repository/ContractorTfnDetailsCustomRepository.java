package com.renaissance.contractor.repository;

import java.math.BigInteger;

import com.renaissance.contractor.model.ContractorTFNDetailsEntity;

public interface ContractorTfnDetailsCustomRepository {
	ContractorTFNDetailsEntity getTfnDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);

}