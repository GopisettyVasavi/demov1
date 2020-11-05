package com.renaissance.requirement.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.requirement.model.JobRequirementEntity;

@Repository
public interface JobRequirementRepository extends CrudRepository<JobRequirementEntity, BigInteger>,JobRequirementCustomRepository {

}
