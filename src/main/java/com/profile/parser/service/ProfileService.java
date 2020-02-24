package com.profile.parser.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.parser.dto.CandidateDTO;
import com.profile.parser.model.CandidateEducationEntity;
import com.profile.parser.model.CandidatePersonalEntity;
import com.profile.parser.model.CandidateProfileEntity;
import com.profile.parser.model.CandidateWorkHistoryEntity;
import com.profile.parser.model.ProfileSearchForm;
import com.profile.parser.repository.CandidateEducationRepository;
import com.profile.parser.repository.CandidatePersonalRepository;
import com.profile.parser.repository.CandidateProfileRepository;
import com.profile.parser.repository.CandidateWorkHistoryRepository;

@Service
public class ProfileService {

	private static final Logger logger=LoggerFactory.getLogger(ProfileService.class);
	@Autowired
	CandidatePersonalRepository candidatePersonalRepository;

	@Autowired
	CandidateProfileRepository candidateProfileRepository;

	@Autowired
	CandidateWorkHistoryRepository candidateWorkRepository;

	@Autowired
	CandidateEducationRepository candidateEducationRepository;

	public CandidateDTO createProfile(CandidateDTO candidateDto) {
		
		candidateDto.setAssignedDate(LocalDate.now());
		logger.info("Profile creation..");

		
		// Check whether candidate profile exists for the same name, email and phone
		// number combination. If so, update data for that candidate.
		candidateDto = updateCandidateDetails(candidateDto);

		// Creating new profile
		// Save PersonalDetails
		if (null == candidateDto.getCandidateId()) {
			CandidatePersonalEntity candidatePersonalVO = new CandidatePersonalEntity();
			CandidateProfileEntity candidateProfileVO = new CandidateProfileEntity();
			CandidateEducationEntity candidateEducationVO = new CandidateEducationEntity();
			CandidateWorkHistoryEntity candidateWorkHistoryVO = new CandidateWorkHistoryEntity();
			logger.info("Creating new Profile... " + candidateDto.getCandidateName() + " : "
					+ candidateDto.getPrimaryEmail() + " : " + candidateDto.getPrimaryPhone());
			BeanUtils.copyProperties(candidateDto, candidatePersonalVO);
			LocalDateTime localDate = LocalDateTime.now();
			candidatePersonalVO.setLastUpdatedByDateTime(localDate);
			candidatePersonalVO = candidatePersonalRepository.save(candidatePersonalVO);

			logger.info("Created ID:: " + candidatePersonalVO.getCandidateId());

			if (null != candidatePersonalVO.getCandidateId()) {
				// Save work details
				BeanUtils.copyProperties(candidateDto, candidateWorkHistoryVO);
				candidateWorkHistoryVO.setCandidateId(candidatePersonalVO.getCandidateId());// Set the newly created
																							// candidateId
				candidateWorkHistoryVO.setLastUpdatedByDateTime(localDate);
				candidateWorkHistoryVO = candidateWorkRepository.save(candidateWorkHistoryVO);

				// Save education details
				BeanUtils.copyProperties(candidateDto, candidateEducationVO);
				candidateEducationVO.setCandidateId(candidatePersonalVO.getCandidateId());// Set the newly created
																							// candidateId
				candidateEducationVO.setLastUpdatedByDateTime(localDate);
				candidateEducationVO = candidateEducationRepository.save(candidateEducationVO);

				// Save Profile details
				BeanUtils.copyProperties(candidateDto, candidateProfileVO);
				candidateProfileVO.setCandidateId(candidatePersonalVO.getCandidateId());// Set the newly created
																						// candidateId
				candidateProfileVO.setLastUpdatedByDateTime(localDate);
				candidateProfileVO.setVersion(1);
				candidateProfileVO = candidateProfileRepository.save(candidateProfileVO);

			}

		} else {

		}

		return candidateDto;
	}

	private CandidateDTO updateCandidateDetails(CandidateDTO candidateDto) {
		CandidatePersonalEntity candidavtePersonal = candidatePersonalRepository.getCandidates(
				candidateDto.getCandidateName(), candidateDto.getPrimaryEmail(), candidateDto.getPrimaryPhone());
		if (null != candidavtePersonal.getCandidateId()) {// Candidate exists

			logger.info("Updating existing Profile... " + candidateDto.getCandidateName() + " : "
					+ candidateDto.getPrimaryEmail() + " : " + candidateDto.getPrimaryPhone());
			// Candidate Present, so update the candidate. Assuming there should be only one
			// candidate with same name, email and phone.

			// candidatePersonalVO = list.get(0);
			candidateDto.setCandidateId(candidavtePersonal.getCandidateId());
			BeanUtils.copyProperties(candidateDto, candidavtePersonal);
			LocalDateTime localDate = LocalDateTime.now();
			candidavtePersonal.setLastUpdatedByDateTime(localDate);
			candidavtePersonal = candidatePersonalRepository.save(candidavtePersonal);

			logger.info("Updated ID:: " + candidavtePersonal.getCandidateId());

			// update details in education and work tables
			CandidateEducationEntity education = candidateEducationRepository
					.getCandidateEducationByCandidateId(candidavtePersonal.getCandidateId());
			BeanUtils.copyProperties(candidateDto, education);
			// LocalDateTime localDate = LocalDateTime.now();
			education.setLastUpdatedByDateTime(localDate);
			education = candidateEducationRepository.save(education);

			CandidateProfileEntity profile = candidateProfileRepository
					.getCandidateProfileByCandidateId(candidavtePersonal.getCandidateId());

			Integer version = profile.getVersion();
			BeanUtils.copyProperties(candidateDto, profile);
			// LocalDateTime localDate = LocalDateTime.now();
			profile.setLastUpdatedByDateTime(localDate);
			if (null != version)
				profile.setVersion(version + 1);
			else
				profile.setVersion(1);
			profile = candidateProfileRepository.save(profile);
			CandidateWorkHistoryEntity candidateWorkHistoryVO = candidateWorkRepository
					.getCandidateWorkByCandidateId(candidavtePersonal.getCandidateId());
			BeanUtils.copyProperties(candidateDto, candidateWorkHistoryVO);
			candidateWorkHistoryVO.setCandidateId(candidavtePersonal.getCandidateId());// Set the newly created
																						// candidateId
			candidateWorkHistoryVO.setLastUpdatedByDateTime(localDate);
			candidateWorkHistoryVO = candidateWorkRepository.save(candidateWorkHistoryVO);

			// Populate candidateDto with all saved values
			candidateDto.setCandidateId(candidavtePersonal.getCandidateId());
		}
		return candidateDto;

	}
	
	public List<CandidateDTO> searchProfiles(ProfileSearchForm searchForm){
		
		List<CandidatePersonalEntity> personalDetails=candidatePersonalRepository.getCandidateProfiles(searchForm);
		logger.info("Search Results, {}",personalDetails.size());
		List<CandidateDTO> candidateProfiles= new ArrayList<CandidateDTO>();
		if(null!=personalDetails && !personalDetails.isEmpty()) {
		for(CandidatePersonalEntity personalEntity: personalDetails) {
			CandidateDTO candidateDto=new CandidateDTO();
			BeanUtils.copyProperties(personalEntity, candidateDto);
			logger.info("Copied Bean, {},{}",personalEntity.toString(),candidateDto.toString() );
			candidateProfiles.add(candidateDto);
		}
		}
		return candidateProfiles;
	}

}
