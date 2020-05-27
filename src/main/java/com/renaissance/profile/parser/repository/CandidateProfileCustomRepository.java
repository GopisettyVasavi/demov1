package com.renaissance.profile.parser.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.renaissance.profile.parser.model.CandidateProfileEntity;
import com.renaissance.profile.parser.model.ProfileSearchForm;

public interface CandidateProfileCustomRepository {
	CandidateProfileEntity getCandidateProfileByCandidateId(BigInteger candidateId);
	List<CandidateProfileEntity> getCandidateProfiles(ProfileSearchForm searchForm);
	

}
