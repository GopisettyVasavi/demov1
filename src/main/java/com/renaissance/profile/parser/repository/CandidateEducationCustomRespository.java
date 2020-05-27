package com.renaissance.profile.parser.repository;

import java.math.BigInteger;

import com.renaissance.profile.parser.model.CandidateEducationEntity;

public interface CandidateEducationCustomRespository {
	CandidateEducationEntity getCandidateEducationByCandidateId(BigInteger candidateId);
	//deleteEducationByCandidateId(BigInteger candidateId);

}
