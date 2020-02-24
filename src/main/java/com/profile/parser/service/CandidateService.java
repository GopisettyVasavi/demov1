package com.profile.parser.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.parser.model.CandidatePersonalEntity;
import com.profile.parser.repository.CandidatePersonalRepository;

@Service
public class CandidateService {

	/*
	 * @Autowired CandidatePersonalRepository candidatePersonal;
	 */

	public List<CandidatePersonalEntity> getAllCandidates() {
		/*
		 * List<CandidatePersonalEntity> result = (List<CandidatePersonalEntity>)
		 * candidatePersonal.findAll();
		 * 
		 * if (result.size() > 0) { return result; } else { return new
		 * ArrayList<CandidatePersonalEntity>(); }
		 */
return null;
	}

}

