package com.renaissance.contractor.repository;

import java.math.BigInteger;

import com.renaissance.contractor.model.ContractorABNDetailsEntity;

public interface ContractorAbnDetailsCustomRepository {
	ContractorABNDetailsEntity getAbnDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);

}
