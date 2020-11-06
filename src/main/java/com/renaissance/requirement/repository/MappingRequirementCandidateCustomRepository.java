package com.renaissance.requirement.repository;

import java.math.BigInteger;
import java.util.List;

import com.renaissance.requirement.model.MappingRequirementCandidateEntity;

public interface MappingRequirementCandidateCustomRepository {
	
	List<MappingRequirementCandidateEntity> getCandidateRequirementMappings(BigInteger candidateId, BigInteger requirementId);

}
