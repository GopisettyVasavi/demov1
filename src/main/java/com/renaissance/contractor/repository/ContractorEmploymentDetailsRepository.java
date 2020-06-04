package com.renaissance.contractor.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.contractor.model.ContractorEmploymentDetailsEntity;

@Repository
public interface ContractorEmploymentDetailsRepository extends CrudRepository<ContractorEmploymentDetailsEntity, BigInteger>, ContractorEmploymentDetailsCustomRepository {

}
