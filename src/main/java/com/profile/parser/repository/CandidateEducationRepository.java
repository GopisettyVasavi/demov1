package com.profile.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.profile.parser.model.CandidateEducationEntity;

@Repository
public interface CandidateEducationRepository extends
CrudRepository<CandidateEducationEntity, Integer> , CandidateEducationCustomRespository{

}
