package com.renaissance.profile.parser.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.profile.parser.model.CandidatePersonalEntity;

@Repository
public interface CandidatePersonalRepository extends 
CrudRepository<CandidatePersonalEntity, BigInteger>, CandidatePersonalRespositoryCustom{

}