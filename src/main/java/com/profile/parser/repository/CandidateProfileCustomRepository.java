package com.profile.parser.repository;

import java.math.BigInteger;
import java.util.List;

import com.profile.parser.model.CandidateProfileEntity;
import com.profile.parser.model.ProfileSearchForm;

public interface CandidateProfileCustomRepository {
	CandidateProfileEntity getCandidateProfileByCandidateId(BigInteger candidateId);
	List<CandidateProfileEntity> getCandidateProfiles(ProfileSearchForm searchForm);

}
