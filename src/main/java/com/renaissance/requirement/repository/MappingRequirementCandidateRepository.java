package com.renaissance.requirement.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.requirement.model.MappingRequirementCandidateEntity;

@Repository

public interface MappingRequirementCandidateRepository extends CrudRepository<MappingRequirementCandidateEntity, BigInteger>, MappingRequirementCandidateCustomRepository {

}
