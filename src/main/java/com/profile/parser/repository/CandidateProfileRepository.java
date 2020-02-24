package com.profile.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.profile.parser.model.CandidateProfileEntity;

@Repository
public interface CandidateProfileRepository 
extends CrudRepository<CandidateProfileEntity, Integer>, CandidateProfileCustomRepository{

}
