package com.renaissance.commission.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.commission.entity.RecruiterCommissionsEntity;

@Repository
public interface RecruiterCommissionRepository extends CrudRepository<RecruiterCommissionsEntity, BigInteger>, RecruiterCommissionsCustomRepository{

}
