package com.renaissance.commission.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.commission.model.RecruiterCommissionsEntity;

@Repository
public interface RecruiterCommissionsRepository extends CrudRepository<RecruiterCommissionsEntity, BigInteger>, RecruiterCommissionsCustomRepository {

}
