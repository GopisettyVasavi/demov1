package com.profile.parser.repository;

import java.util.List;

import com.profile.parser.model.CandidatePersonalEntity;
import com.profile.parser.model.ProfileSearchForm;


public interface CandidatePersonalRespositoryCustom {
	
	
	  CandidatePersonalEntity getCandidates(String candidateName, String
	  primaryEmail, String primaryContactNo);
	 
	  List<CandidatePersonalEntity> getCandidateProfiles(ProfileSearchForm searchForm);
}
