package com.renaissance.contractor.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.renaissance.contractor.model.ContractorTFNDetailsEntity;

@Repository
public interface ContractorTfnDetailsRepository extends CrudRepository<ContractorTFNDetailsEntity, BigInteger>, ContractorTfnDetailsCustomRepository {
	
	@Query(value="SELECT * FROM parse.\"CONTRACTOR_TFN_DETAILS\" WHERE \"CONTRACTOR_ID\" = :contractorId AND upper(\"ACTIVE_RECORD\") ='ACTIVE' ", nativeQuery = true)
	ContractorTFNDetailsEntity getActiveTfnByContractorId(@Param("contractorId") BigInteger contractorId);

}
