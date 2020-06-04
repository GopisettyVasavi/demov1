package com.renaissance.contractor.repository;

import java.math.BigInteger;

import com.renaissance.contractor.model.ContractorBankDetailsEntity;

public interface ContractorBankDetailsCustomRepository {
	ContractorBankDetailsEntity getBankDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);

}
