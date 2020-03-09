package com.profile.parser.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtilsBean;
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
import com.profile.parser.util.ProfileParserUtils;

/**
 * This class will have methods to save/update or retrieve information related
 * to candidate.
 * 
 * @author Vasavi
 *
 */
@Service
public class ProfileService {

	private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);
	@Autowired
	CandidatePersonalRepository candidatePersonalRepository;

	@Autowired
	CandidateProfileRepository candidateProfileRepository;

	@Autowired
	CandidateWorkHistoryRepository candidateWorkRepository;

	@Autowired
	CandidateEducationRepository candidateEducationRepository;

	/**
	 * This method will check whether the candidate is existing or not. And then
	 * either update or create profile accordingly.
	 * 
	 * @param candidateDto
	 * @return
	 */

	public CandidateDTO createProfile(CandidateDTO candidateDto) {

		// candidateDto.setAssignedDate(ProfileParserUtils.parseDate(new Date()));
		logger.info("Profile creation..");
		candidateDto = ProfileParserUtils.parseAllDates(candidateDto);

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
			candidateDto.setCandidateId(candidatePersonalVO.getCandidateId());
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

	/**
	 * This method will check whether the candidate exists or not, if exists it
	 * updates the details.
	 * 
	 * @param candidateDto
	 * @return
	 */
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

	/**
	 * This method will search candidate profiles based on the search criteria sent
	 * in searchform.
	 * 
	 * @param searchForm
	 * @return List<CandidateDTO>
	 */

	public List<CandidateDTO> searchProfiles(ProfileSearchForm searchForm) {
		// get profiles by personal search data from candidate personal table
		List<CandidatePersonalEntity> personalDetails = candidatePersonalRepository.getCandidateProfiles(searchForm);
		logger.info("Search Results personal table, {}", personalDetails.size());
		List<BigInteger> candidateIdList = new ArrayList<BigInteger>();
		List<CandidateDTO> candidateProfiles = new ArrayList<CandidateDTO>();
		if (!ProfileParserUtils.isObjectEmpty(personalDetails)) {
			for (CandidatePersonalEntity personalEntity : personalDetails) {
				candidateIdList.add(personalEntity.getCandidateId());
				
			}
		}
		// get profiles by profile search fields from candidate profile table
		List<CandidateProfileEntity> profileDetails = candidateProfileRepository.getCandidateProfiles(searchForm);
		logger.info("Search Results profile table, {}", profileDetails.size());
		if (!ProfileParserUtils.isObjectEmpty(profileDetails)) {
			for (CandidateProfileEntity profileEntity : profileDetails) {
				candidateIdList.add(profileEntity.getCandidateId());
				
			}
		}
		logger.info("List size before.. ,{}", candidateIdList.size());
		List<BigInteger> listWithoutDuplicates = candidateIdList.stream().distinct().collect(Collectors.toList());
		logger.info("List size after.. ,{}", listWithoutDuplicates.size());
		for (BigInteger candidateId : listWithoutDuplicates) {
			candidateProfiles.add(getCandidateFullDetails(candidateId));
		}
		return candidateProfiles;
	}

	/**
	 * This method will get all the details for the candidateId.
	 * 
	 * @param candidateId
	 * @return
	 */

	public CandidateDTO getCandidateFullDetails(BigInteger candidateId) {
		CandidateDTO candidateDetailsDto = new CandidateDTO();

		try {
			Optional<CandidatePersonalEntity> personalDetails = candidatePersonalRepository.findById(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(personalDetails) && personalDetails.isPresent()) {

				CandidatePersonalEntity personal = personalDetails.get();
				BeanUtils.copyProperties(personal, candidateDetailsDto);

			}
			CandidateProfileEntity profileDetail = candidateProfileRepository
					.getCandidateProfileByCandidateId(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(profileDetail)) {
				BeanUtils.copyProperties(profileDetail, candidateDetailsDto);
			}
			CandidateWorkHistoryEntity workDetail = candidateWorkRepository.getCandidateWorkByCandidateId(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(workDetail)) {
				BeanUtils.copyProperties(workDetail, candidateDetailsDto);
			}
			CandidateEducationEntity educationDetail = candidateEducationRepository
					.getCandidateEducationByCandidateId(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(educationDetail)) {
				BeanUtils.copyProperties(educationDetail, candidateDetailsDto);
			}
			logger.info("All details,{}", candidateDetailsDto.getCandidateId() + " "
					+ candidateDetailsDto.getCandidateName() + " " + candidateDetailsDto.getVisaType());
		} catch (Exception e) {
			logger.error("Error in retrieving details" + e.getStackTrace());
		}

		return candidateDetailsDto;
	}
}
