package com.renaissance.contractor.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.contractor.model.ContractorPersonalDetailsEntity;

@Repository
public interface ContractorPersonalDetailsRepository extends CrudRepository<ContractorPersonalDetailsEntity, BigInteger>,ContractorPersonalDetailsCustomRepository {

}
