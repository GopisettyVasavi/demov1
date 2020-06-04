package com.renaissance.contractor.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.contractor.model.ContractorPersonalDetailsEntity;

public interface ContractorPersonalDetailsCustomRepository {
	ContractorPersonalDetailsEntity getPersonalDetailsByContractorId(BigInteger contractorId);
	ContractorPersonalDetailsEntity getContractors(String firstName, String lastName, String dateOfBirth,String personalEmail);
	List<ContractorPersonalDetailsEntity> searchContractors(String fullName);
	

}
