package com.profile.parser.repository;

import java.math.BigInteger;

import com.profile.parser.model.CandidateEducationEntity;

public interface CandidateEducationCustomRespository {
	CandidateEducationEntity getCandidateEducationByCandidateId(BigInteger candidateId);
	//deleteEducationByCandidateId(BigInteger candidateId);

}
