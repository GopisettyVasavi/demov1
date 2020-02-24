package com.profile.parser.repository;

import java.math.BigInteger;

import com.profile.parser.model.CandidateWorkHistoryEntity;

public interface CandidateWorkHistoryCustomRepository {
	CandidateWorkHistoryEntity getCandidateWorkByCandidateId(BigInteger candidateId);

}
