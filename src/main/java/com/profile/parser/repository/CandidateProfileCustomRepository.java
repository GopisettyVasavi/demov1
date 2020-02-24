package com.profile.parser.repository;

import java.math.BigInteger;

import com.profile.parser.model.CandidateProfileEntity;

public interface CandidateProfileCustomRepository {
	CandidateProfileEntity getCandidateProfileByCandidateId(BigInteger candidateId);

}
