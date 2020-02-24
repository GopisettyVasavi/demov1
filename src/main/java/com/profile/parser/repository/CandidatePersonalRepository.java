package com.profile.parser.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.profile.parser.model.CandidatePersonalEntity;

@Repository
public interface CandidatePersonalRepository extends 
CrudRepository<CandidatePersonalEntity, BigInteger>, CandidatePersonalRespositoryCustom{

}