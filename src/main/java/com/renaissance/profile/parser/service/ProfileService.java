package com.renaissance.profile.parser.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.profile.parser.dto.CandidateDTO;
import com.renaissance.profile.parser.model.CandidateEducationEntity;
import com.renaissance.profile.parser.model.CandidatePersonalEntity;
import com.renaissance.profile.parser.model.CandidateProfileEntity;
import com.renaissance.profile.parser.model.CandidateWorkHistoryEntity;
import com.renaissance.profile.parser.model.ProfileSearchForm;
import com.renaissance.profile.parser.repository.CandidateEducationRepository;
import com.renaissance.profile.parser.repository.CandidatePersonalRepository;
import com.renaissance.profile.parser.repository.CandidateProfileRepository;
import com.renaissance.profile.parser.repository.CandidateWorkHistoryRepository;
import com.renaissance.profile.parser.util.GlobalProperties;
import com.renaissance.profile.parser.util.ProfileParserUtils;

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
	
	@Autowired
	GlobalProperties properties;

	/**
	 * This method will check whether the candidate is existing or not. And then
	 * either update or create profile accordingly.
	 * 
	 * @param candidateDto
	 * @return
	 */

	public CandidateDTO createProfile(CandidateDTO candidateDto) throws Exception {

		// candidateDto.setAssignedDate(ProfileParserUtils.parseDate(new Date()));
		// logger.info("Profile creation.."+candidateDto.getAdditionalNotes());
		// candidateDto = ProfileParserUtils.parseAllDates(candidateDto);

		// Check whether candidate profile exists for the same name, email and phone
		// number combination. If so, update data for that candidate.
		//if(!ProfileParserUtils.isObjectEmpty(candidateDto.getCandidateId()))
		candidateDto = updateCandidateDetails(candidateDto);

		
		// Creating new profile
		// Save PersonalDetails
		if (ProfileParserUtils.isObjectEmpty( candidateDto.getCandidateId())) {
			CandidatePersonalEntity candidatePersonalVO = new CandidatePersonalEntity();
			CandidateProfileEntity candidateProfileVO = new CandidateProfileEntity();
			CandidateEducationEntity candidateEducationVO = new CandidateEducationEntity();
			CandidateWorkHistoryEntity candidateWorkHistoryVO = new CandidateWorkHistoryEntity();
			logger.info("Creating new Profile... " + candidateDto.getCandidateName() + " : "
					+ candidateDto.getPrimaryEmail() + " : " + candidateDto.getPrimaryPhone());
			BeanUtils.copyProperties(candidateDto, candidatePersonalVO);
			candidatePersonalVO.setValidUpto(ProfileParserUtils.parseStringDate(candidateDto.getValidUpto()));
			LocalDateTime localDate = LocalDateTime.now();
			candidatePersonalVO.setLastUpdatedByDateTime(localDate);
			candidatePersonalVO = candidatePersonalRepository.save(candidatePersonalVO);
			candidateDto.setCandidateId(candidatePersonalVO.getCandidateId());
			logger.info("Created ID:: " + candidatePersonalVO.getCandidateId());

			if (!ProfileParserUtils.isObjectEmpty(candidatePersonalVO.getCandidateId())) {
				// Save work details
				BeanUtils.copyProperties(candidateDto, candidateWorkHistoryVO);
				candidateWorkHistoryVO.setCandidateId(candidatePersonalVO.getCandidateId());// Set the newly created
																							// candidateId
				candidateWorkHistoryVO.setLastUpdatedByDateTime(localDate);
				candidateWorkHistoryVO
						.setWorkEndDate(ProfileParserUtils.parseStringDate(candidateDto.getWorkEndDate()));
				candidateWorkHistoryVO
						.setWorkStartDate(ProfileParserUtils.parseStringDate(candidateDto.getWorkStartDate()));
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
				candidateProfileVO.setAssignedDate(ProfileParserUtils.parseStringDate(candidateDto.getAssignedDate()));
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
	private CandidateDTO updateCandidateDetails(CandidateDTO candidateDto) throws Exception {
		logger.info("Candidate id:: {}", candidateDto.getCandidateId());
		CandidatePersonalEntity candidatePersonal=null;
		if(ProfileParserUtils.isObjectEmpty(candidateDto.getCandidateId())&&!ProfileParserUtils.isObjectEmpty(candidateDto)) {
			logger.info("NO Candidate ID, but details may exist...");
			candidatePersonal=candidatePersonalRepository.getCandidates(candidateDto.getCandidateName(), candidateDto.getPrimaryEmail(), candidateDto.getPrimaryPhone());
		}
		if(ProfileParserUtils.isObjectEmpty(candidatePersonal)&&!ProfileParserUtils.isObjectEmpty(candidateDto.getCandidateId())) {
			logger.info("Could not retrieve details above, but ID exist....");
		Optional<CandidatePersonalEntity> personalDetails = candidatePersonalRepository
				.findById(candidateDto.getCandidateId());
		 candidatePersonal = personalDetails.get();}
		//logger.info("Retrieved details by ID:: {}", candidatePersonal.getCandidateId());
		// }
		logger.info("PROFILE EXISTED....,{},{},{},{}", candidatePersonal.getCandidateId(),
				candidatePersonal.getCandidateName(), candidatePersonal.getPrimaryEmail(),
				candidatePersonal.getPrimaryPhone());
		if (!ProfileParserUtils.isObjectEmpty(candidatePersonal.getCandidateId())
				|| !ProfileParserUtils.isObjectEmpty(candidateDto.getCandidateId())) {// Candidate exists

			logger.info("Updating existing Profile... " + candidatePersonal.getCandidateId() + " : "
					+ candidateDto.getCandidateName() + " : " + candidateDto.getPrimaryEmail() + " : "
					+ candidateDto.getPrimaryPhone());
			// Candidate Present, so update the candidate. Assuming there should be only one
			// candidate with same name, email and phone.

			// candidatePersonalVO = list.get(0);
			candidateDto.setCandidateId(candidatePersonal.getCandidateId());
			// logger.info("WORK EXPERIENCE..."+candidatePersonal.getw);
			BeanUtils.copyProperties(candidateDto, candidatePersonal);
			LocalDateTime localDate = LocalDateTime.now();
			candidatePersonal.setLastUpdatedByDateTime(localDate);
			candidatePersonal.setValidUpto(ProfileParserUtils.parseStringDate(candidateDto.getValidUpto()));
			candidatePersonal = candidatePersonalRepository.save(candidatePersonal);

			logger.info("Updated ID:: " + candidatePersonal.getCandidateId());

			// update details in education and work tables
			CandidateEducationEntity education = candidateEducationRepository
					.getCandidateEducationByCandidateId(candidatePersonal.getCandidateId());
			BeanUtils.copyProperties(candidateDto, education);
			// LocalDateTime localDate = LocalDateTime.now();
			education.setLastUpdatedByDateTime(localDate);
			education = candidateEducationRepository.save(education);

			CandidateProfileEntity profile = candidateProfileRepository
					.getCandidateProfileByCandidateId(candidatePersonal.getCandidateId());

			Integer version = profile.getVersion();
			// logger.info("NOTES IN DTO::::,{}",candidateDto.getAdditionalNotes());

			BeanUtils.copyProperties(candidateDto, profile);
			
			// LocalDateTime localDate = LocalDateTime.now();
			profile.setLastUpdatedByDateTime(localDate);
			if (!!ProfileParserUtils.isObjectEmpty( version))
				profile.setVersion(version + 1);
			else
				profile.setVersion(1);
			// logger.info("NOTES::::,{}",profile.getAdditionalNotes());
			profile.setAssignedDate(ProfileParserUtils.parseStringDate(candidateDto.getAssignedDate()));
			profile = candidateProfileRepository.save(profile);
			CandidateWorkHistoryEntity candidateWorkHistoryVO = candidateWorkRepository
					.getCandidateWorkByCandidateId(candidatePersonal.getCandidateId());
			BeanUtils.copyProperties(candidateDto, candidateWorkHistoryVO);
			candidateWorkHistoryVO.setCandidateId(candidatePersonal.getCandidateId());// Set the newly created
																						// candidateId
			candidateWorkHistoryVO.setLastUpdatedByDateTime(localDate);
			candidateWorkHistoryVO.setWorkEndDate(ProfileParserUtils.parseStringDate(candidateDto.getWorkEndDate()));
			candidateWorkHistoryVO
					.setWorkStartDate(ProfileParserUtils.parseStringDate(candidateDto.getWorkStartDate()));
			candidateWorkHistoryVO = candidateWorkRepository.save(candidateWorkHistoryVO);

			// Populate candidateDto with all saved values
			candidateDto.setCandidateId(candidatePersonal.getCandidateId());
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
				candidateDetailsDto.setValidUpto(ProfileParserUtils.parseDateToString(personal.getValidUpto()));
			}
			CandidateProfileEntity profileDetail = candidateProfileRepository
					.getCandidateProfileByCandidateId(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(profileDetail)) {
				BeanUtils.copyProperties(profileDetail, candidateDetailsDto);
				candidateDetailsDto
						.setAssignedDate(ProfileParserUtils.parseDateToString(profileDetail.getAssignedDate()));
			}
			CandidateWorkHistoryEntity workDetail = candidateWorkRepository.getCandidateWorkByCandidateId(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(workDetail)) {
				BeanUtils.copyProperties(workDetail, candidateDetailsDto);
				candidateDetailsDto
						.setWorkStartDate(ProfileParserUtils.parseDateToString(workDetail.getWorkStartDate()));
				candidateDetailsDto.setWorkEndDate(ProfileParserUtils.parseDateToString(workDetail.getWorkEndDate()));
			}
			CandidateEducationEntity educationDetail = candidateEducationRepository
					.getCandidateEducationByCandidateId(candidateId);
			if (!ProfileParserUtils.isObjectEmpty(educationDetail)) {
				BeanUtils.copyProperties(educationDetail, candidateDetailsDto);
			}
			logger.info("All details,{}", candidateDetailsDto.getCandidateId() + " "
					+ candidateDetailsDto.getCandidateName() + " " + candidateDetailsDto.getVisaType());
		} catch (Exception e) {
			logger.error("Error in retrieving details...{}", new Exception(e.getMessage()));
		}

		return candidateDetailsDto;
	}

	/**
	 * This method will be executed as part of the batch job which fetches all the
	 * profiles whose assigned date is older than 28 days and updates them by
	 * emptying assigned to date, employee id and employee name fields.
	 * 
	 * @throws Exception
	 */
	public void updateAssignedFields() throws Exception {
		try {
			List<CandidateProfileEntity> profiles = candidateProfileRepository.updateAssignedEmployeeDetails(properties.getMaxAssignedDays());
			if (!ProfileParserUtils.isObjectEmpty(profiles)) {
				for (CandidateProfileEntity profile : profiles) {
					logger.info("CANDIDATE ID to UPDATE:::: ,{}", profile.getCandidateId());
					profile.setAssignedDate(null);
					profile.setAssignedToEmployeeId(null);
					profile.setAssignedToEmployeeName("");
					profile.setLastUpdatedByDateTime(LocalDateTime.now());
					candidateProfileRepository.save(profile);
					// logger.info("CANDIDATE ID to UPDATEdd:::: ,{}",profile.getCandidateId());
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in updating assigned to fields..,{}", new Exception(e.getMessage()));
		}
	}
}
