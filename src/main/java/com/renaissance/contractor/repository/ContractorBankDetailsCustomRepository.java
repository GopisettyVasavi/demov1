package com.renaissance.contractor.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.contractor.model.ContractorBankDetailsEntity;

public interface ContractorBankDetailsCustomRepository {
	ContractorBankDetailsEntity getBankDetailsByContractorId(BigInteger contractorId);
	List<ContractorBankDetailsEntity> getAllBankDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);

}
