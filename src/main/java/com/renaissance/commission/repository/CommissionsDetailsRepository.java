package com.renaissance.commission.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.commission.model.CommissionsDetailsEntity;

@Repository
public interface CommissionsDetailsRepository extends CrudRepository<CommissionsDetailsEntity, BigInteger>, CommissionsDetailsCustomRepository{

}
