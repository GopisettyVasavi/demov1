package com.renaissance.profile.parser.repository;

import java.math.BigInteger;

import com.renaissance.profile.parser.model.CandidateWorkHistoryEntity;

public interface CandidateWorkHistoryCustomRepository {
	CandidateWorkHistoryEntity getCandidateWorkByCandidateId(BigInteger candidateId);

}
