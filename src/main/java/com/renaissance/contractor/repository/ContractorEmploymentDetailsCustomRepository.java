package com.renaissance.contractor.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.contractor.model.ContractorEmploymentDetailsEntity;
import com.renaissance.contractor.model.ContractorSearchForm;

public interface ContractorEmploymentDetailsCustomRepository {
	ContractorEmploymentDetailsEntity getEmploymentDetailsByContractorId(BigInteger contractorId);
	void deleteByContractorId(BigInteger contractorId);
	List<ContractorEmploymentDetailsEntity> searchEmploymentDetails(ContractorSearchForm searchForm);
	List<ContractorEmploymentDetailsEntity> getAllEmploymentDetailsByContractorId(BigInteger contractorId);

}
