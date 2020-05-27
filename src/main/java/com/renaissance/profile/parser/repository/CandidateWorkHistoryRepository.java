package com.renaissance.profile.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.profile.parser.model.CandidateWorkHistoryEntity;

@Repository
public interface CandidateWorkHistoryRepository extends
CrudRepository<CandidateWorkHistoryEntity,Integer>, CandidateWorkHistoryCustomRepository {

}
