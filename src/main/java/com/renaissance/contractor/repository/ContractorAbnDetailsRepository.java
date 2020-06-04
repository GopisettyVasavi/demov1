package com.renaissance.contractor.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.contractor.model.ContractorABNDetailsEntity;

@Repository
public interface ContractorAbnDetailsRepository extends CrudRepository<ContractorABNDetailsEntity, BigInteger>, ContractorAbnDetailsCustomRepository {

}
