package com.renaissance.profile.parser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.renaissance.profile.parser.model.CandidateProfileEntity;

@Repository
public interface CandidateProfileRepository 
extends CrudRepository<CandidateProfileEntity, Integer>, CandidateProfileCustomRepository{
	
	@Query(value="select * from parse.\"CANDIDATE_PROFILE\" where CURRENT_DATE-\"ASSIGNED_DATE\" >= :maxNoDays", nativeQuery = true)
	List<CandidateProfileEntity> updateAssignedEmployeeDetails(@Param("maxNoDays") int maxNoDays);

}
