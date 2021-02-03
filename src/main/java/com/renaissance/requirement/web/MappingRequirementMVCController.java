package com.renaissance.requirement.web;

import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.LOAD_ASSIGNED_REQUIREMENT;
import static com.renaissance.util.APIConstants.MAPPING_REQUIREMENT_MAIN;
import static com.renaissance.util.APIConstants.MAP_CANDIDATE_REQUIREMENT;
import static com.renaissance.util.APIConstants.SEARCH_CANDIDATES_REQUIREMENT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.renaissance.profile.parser.dto.CandidateDTO;
import com.renaissance.profile.parser.model.ProfileSearchForm;
import com.renaissance.profile.parser.service.ProfileService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import com.renaissance.requirement.dto.MappingCandidateRqmtDTO;
import com.renaissance.requirement.dto.MappingRequirement;
import com.renaissance.requirement.dto.RequirementDTO;
import com.renaissance.requirement.service.RequirementManagementService;

@Controller
public class MappingRequirementMVCController {
	private static final Logger logger = LoggerFactory.getLogger(MappingRequirementMVCController.class);
	@Autowired
	RequirementManagementService requirementService;

	@Autowired
	ProfileService profileService;

	/**
	 * This method is invoked to load contractor main page.
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(MAPPING_REQUIREMENT_MAIN)
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())
				|| (ProfileParserConstants.RECRUITER.equalsIgnoreCase(
						request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))) {
			logger.info("Mapping client requirement Module: Loading initial Landing page.");

			return "mappingrequirementmain";
		}
		else
			return "unauthorizedaccess";

	}

	/**
	 * This method will load all commissions
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(LOAD_ASSIGNED_REQUIREMENT)
	public ResponseEntity<?> loadassignedrequests(HttpServletRequest request) {
		List<RequirementDTO> requirementList = new ArrayList<RequirementDTO>();
		List<RequirementDTO> processedList = new ArrayList<RequirementDTO>();
		logger.info("Mapping client requirement Module: Loading assigned requirements.");

		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			// lookupList=commissionService.loadAllCommissionsLookupValues();

			/*
			 * logger.info("EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ROLE,{},{}, {}",
			 * request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID)
			 * ,request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME),
			 * request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE));
			 */
			RequirementDTO requirementDto = new RequirementDTO();

			if (!ProfileParserUtils
					.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
				requirementDto.setAssignedRecruiter(
						request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID) + "");
				Integer recruiterId = Integer
						.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID) + "");
				requirementDto.setRecruiterId(recruiterId);

			}
			if (!ProfileParserUtils
					.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE))
					&& ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession()
							.getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString().trim())) {
				// logger.info("admin");
				requirementDto.setRecruiterName(ProfileParserConstants.NONE);
				requirementDto.setRecruiterId(000);
				requirementDto.setAssignedRecruiter(null);
			}
			requirementList = requirementService.searchRequirements(requirementDto);
			// getOne month old data for Closed requirements
			if (!ProfileParserUtils.isObjectEmpty(requirementList)) {
				for (RequirementDTO rqmtDto : requirementList) {
					if (!ProfileParserUtils.isObjectEmpty(rqmtDto)
							&& !ProfileParserUtils.isObjectEmpty(rqmtDto.getStatus())
							&& ProfileParserConstants.STATUS_CLOSED.equalsIgnoreCase(rqmtDto.getStatus())
							&& !ProfileParserUtils.isObjectEmpty(rqmtDto.getModifiedDate())) {
						boolean b = ProfileParserUtils.dateLessthanAMonth(rqmtDto.getModifiedDate());
						logger.info("Requirement DTO: {},{} ", b, rqmtDto.toString());
						if (ProfileParserUtils.dateLessthanAMonth(rqmtDto.getModifiedDate())) {
							processedList.add(rqmtDto);
						}

					} else {
						processedList.add(rqmtDto);
					}
				}
			}

		} catch (Exception e) {
			logger.error("Error in loading requirements,{}", e.getMessage());
			System.out.println("Error: " + e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in loading constant. Please try again. \n" + e.getMessage());
		}

		return new ResponseEntity<>(processedList, HttpStatus.OK);
	}

	@PostMapping(MAP_CANDIDATE_REQUIREMENT)
	public ResponseEntity<?> mapCandidateRequirement(@RequestBody MappingRequirement mappingRequirement,
			HttpServletRequest request) {
		List<CandidateDTO> candidates = new ArrayList<CandidateDTO>();
		logger.info("Mapping client requirement Module: Mapping candidate requirement.");

		try {
			// logger.info("Mapping Requirement..,{}", mappingCandidateRqmtList.toString());
			List<MappingCandidateRqmtDTO> mappingCandidateRqmtList = mappingRequirement.getMappingCandidateRqmtList();
			
			if (!ProfileParserUtils.isObjectEmpty(mappingCandidateRqmtList)) {
				for (MappingCandidateRqmtDTO mappingDto : mappingCandidateRqmtList) {
					 //logger.info(" MappingCandidateRqmtDTO : , {}",mappingDto.toString());
					if (!ProfileParserUtils
							.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
						Integer recruiterId = Integer
								.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID) + "");
						if (ProfileParserUtils.isObjectEmpty(mappingDto.getRecruiterId())) {
							mappingDto.setRecruiterId(recruiterId);
						}

					}
					// logger.info("ID ddddd...{}",mappingDto.getId());
					if (ProfileParserUtils.isObjectEmpty(mappingDto.getId())) {
						mappingDto.setCreatedDate(LocalDate.now());
					} else {
						mappingDto.setModifiedDate(LocalDate.now());
					}
					// logger.info("Status:: {}",mappingDto.getStatus());
					if (ProfileParserUtils.isObjectEmpty(mappingDto.getStatus())
							|| "none".equalsIgnoreCase(mappingDto.getStatus())) {
						mappingDto.setStatus(ProfileParserConstants.STATUS_OPEN);
					}
					//Modifying record
					if(!ProfileParserUtils.isObjectEmpty(mappingDto.getId())) {
						MappingCandidateRqmtDTO prevMapDto=requirementService.getCandidateMappingById(mappingDto.getId());
						boolean dataModified=mappingDataModified(prevMapDto, mappingDto);
						//logger.info("Data modified: {}",dataModified);
						if(dataModified) {
							//Send Email
							RequirementDTO requirementDto=requirementService.getRequirementDetails(prevMapDto.getRequirementId());
							prepareEmailBody(requirementDto,mappingDto);
							/*String body="";
							 * body=prepareEmailBody(requirementDto,mappingDto); //Call Email here from Util
							 * class //Send email to requirementDto.getRequirementCreaterEmailId
							 * CandidateDTO candidateDto =
							 * profileService.getCandidateFullDetails(mappingDto.getCandidateId()); String
							 * attachment=ProfileParserConstants.CURRENT_DIR+
							 * "\\src\\main\\resources\\static\\"+"\\"+ candidateDto.getFilePath();
							 * //attachment=attachment.replaceAll("//", "\\");
							 * 
							 * logger.info("File Path: {}",attachment);
							 * ProfileParserUtils.sendEmail("vasavi@reninfo.com.au", "subject", body,
							 * attachment);
							 */
							 
						}
						
					}
				}
				requirementService.saveCandidateMapping(mappingCandidateRqmtList);
				
				ProfileSearchForm searchForm = mappingRequirement.getSearchForm();
				searchForm.setRequirementId(mappingCandidateRqmtList.get(0).getRequirementId());
				candidates = processSearchMappingCandidates(searchForm, request);
				
				
			}
		} catch (Exception e) {
			logger.error("Error in Mapping candidates,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(candidates);
	}

	/**
	 * This method will take the search criteria entered by user and sends the
	 * details to the service to retrieve profiles matching the search criteria.
	 * 
	 * @param searchForm
	 * @param request
	 * @return
	 */
	@PostMapping(SEARCH_CANDIDATES_REQUIREMENT)
	public ResponseEntity<?> getSearchResults(@RequestBody ProfileSearchForm searchForm, HttpServletRequest request) {
		List<CandidateDTO> candidates = new ArrayList<CandidateDTO>();
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("Session has expired.");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Mapping client requirement Module: Searching candidates for requirement.{}",searchForm.toString());

		candidates = processSearchMappingCandidates(searchForm, request);

		return new ResponseEntity<>(candidates, HttpStatus.OK);

	}

	private List<CandidateDTO> processSearchMappingCandidates(ProfileSearchForm searchForm,
			HttpServletRequest request) {
		List<CandidateDTO> candidates = new ArrayList<CandidateDTO>();

		logger.info("Mapping client requirement Module: Processing Search details, {}", searchForm.toString());
		candidates = profileService.searchProfiles(searchForm);
		// logger.info("List size:, {}",candidates.size());
		List<MappingCandidateRqmtDTO> mappingList = requirementService.fetchRequirementMappings(null,
				searchForm.getRequirementId());
		// logger.info("mappingList size:, {}",mappingList.size());
		if (!ProfileParserUtils.isObjectEmpty(mappingList)) {
			int i = 1;
			for (MappingCandidateRqmtDTO mappingDto : mappingList) {
				CandidateDTO candidateDto = profileService.getCandidateFullDetails(mappingDto.getCandidateId());
				candidateDto.setRecruiterId(mappingDto.getRecruiterId());
				candidateDto.setRequirementId(mappingDto.getRequirementId());
				candidateDto.setInterestedInRole(mappingDto.getInterestedInRole());
				candidateDto.setStatus(mappingDto.getStatus());
				candidateDto.setAuthorisation(mappingDto.getAuthorisation());
				candidateDto.setComments(mappingDto.getComments());
				candidateDto.setInterestedInRole(mappingDto.getInterestedInRole());
				candidateDto.setSno(i);
				candidateDto.setId(mappingDto.getId());
				candidateDto.setCreatedDate(mappingDto.getCreatedDate());
				candidateDto.setExpectedSalary(mappingDto.getExpectedSalary());
				candidateDto.setTotalExp(mappingDto.getTotalExp());
				candidateDto.setRelevantExp(mappingDto.getRelevantExp());
				candidateDto.setAppliedBefore(mappingDto.getAppliedBefore());
				candidateDto.setAuthoriseRenaissance(mappingDto.getAuthoriseRenaissance());
				candidateDto.setAvailabilityToJoin(mappingDto.getAvailabilityToJoin());
				candidateDto.setRelocationInFuture(mappingDto.getRelocationInFuture());
				if (!ProfileParserUtils
						.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE))
						&& !ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession()
								.getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString().trim())) {
					// get Logged in recruiterID
					if (!ProfileParserUtils
							.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
						Integer recruiterId = Integer
								.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID) + "");

						if (!ProfileParserUtils.isObjectEmpty(recruiterId)
								&& !ProfileParserUtils.isObjectEmpty(candidateDto.getRecruiterId())
								&& candidateDto.getRecruiterId().intValue() != recruiterId) {
							candidateDto.setDisableRow("true");
						} else {
							candidateDto.setDisableRow("false");

						}
					}
					candidateDto.setDisableStatus("true");
				} else {
					candidateDto.setDisableStatus("false");
				}
				i++;
				Predicate<CandidateDTO> condition = employee -> employee.getCandidateId().intValue() == mappingDto
						.getCandidateId().intValue();
				candidates.removeIf(condition);
				candidates.add(candidateDto);
			}

		}
		if (!ProfileParserUtils.isObjectEmpty(candidates)) {
			for (CandidateDTO candidate : candidates) {
				if (candidate.getSno() == null)
					candidate.setSno(99999);

				if (!ProfileParserUtils
						.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE))
						&& !ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession()
								.getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString().trim())) {

					candidate.setDisableStatus("true");
				} else {
					candidate.setDisableStatus("false");
				}

			}
		}
		candidates.sort(Comparator.comparing(CandidateDTO::getSno));

		//logger.info("After process List size:, {}", candidates.size());

		return candidates;
	}
	
	private boolean mappingDataModified(MappingCandidateRqmtDTO prevMapDto, MappingCandidateRqmtDTO mappingDto) {
		boolean dataModified=false;

		if((prevMapDto.getInterestedInRole()!=null && mappingDto.getInterestedInRole()!=null) &&
				(prevMapDto.getInterestedInRole().equalsIgnoreCase(mappingDto.getInterestedInRole()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getAuthorisation()!=null && mappingDto.getAuthorisation()!=null) &&
				(prevMapDto.getAuthorisation().equalsIgnoreCase(mappingDto.getAuthorisation()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getAvailabilityToJoin()!=null && mappingDto.getAvailabilityToJoin()!=null) &&
				(prevMapDto.getAvailabilityToJoin().equalsIgnoreCase(mappingDto.getAvailabilityToJoin()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getRelocationInFuture()!=null && mappingDto.getRelocationInFuture()!=null) &&
				(prevMapDto.getRelocationInFuture().equalsIgnoreCase(mappingDto.getRelocationInFuture()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getAppliedBefore()!=null && mappingDto.getAppliedBefore()!=null) &&
				(prevMapDto.getAppliedBefore().equalsIgnoreCase(mappingDto.getAppliedBefore()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getAuthoriseRenaissance()!=null && mappingDto.getAuthoriseRenaissance()!=null) &&
				(prevMapDto.getAuthoriseRenaissance().equalsIgnoreCase(mappingDto.getAuthoriseRenaissance()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getComments()!=null && mappingDto.getComments()!=null) &&
				(prevMapDto.getComments().equalsIgnoreCase(mappingDto.getComments()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getStatus()!=null && mappingDto.getStatus()!=null) &&
				(prevMapDto.getStatus().equalsIgnoreCase(mappingDto.getStatus()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getExpectedSalary()!=null && mappingDto.getExpectedSalary()!=null) &&
				(prevMapDto.getExpectedSalary().equalsIgnoreCase(mappingDto.getExpectedSalary()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getTotalExp()!=null && mappingDto.getTotalExp()!=null) &&
				(prevMapDto.getTotalExp().equalsIgnoreCase(mappingDto.getTotalExp()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		if((prevMapDto.getRelevantExp()!=null && mappingDto.getRelevantExp()!=null) &&
				(prevMapDto.getRelevantExp().equalsIgnoreCase(mappingDto.getRelevantExp()))) {
			dataModified=false;
			
		}else {
			dataModified=true;
			return dataModified;
		}
		return dataModified;
	}
private String prepareEmailBody(RequirementDTO requirementDto, MappingCandidateRqmtDTO mappingDto ) {
	 StringBuilder sb = new StringBuilder();
	  sb.append("***********REQUIREMENT DETAILS ").append(System.lineSeparator());
	  sb.append("Vendor Name: " + requirementDto.getVendorName()); 
	  sb.append(" :Requirement Id: " + requirementDto.getRequirementId()); 
	  sb.append(" :Job Type: " + requirementDto.getJobType()); 
	  sb.append(" :Job Title: " + requirementDto.getJobTitle()).append(System.lineSeparator()); 
	  sb.append(" :Location: " + requirementDto.getLocation()); 
	  sb.append(" :Salary: " + requirementDto.getSalary()); 
	  sb.append(" :Job Description: " + requirementDto.getJobDescription()).append(System.lineSeparator()); 
	  sb.append("***********MAPPED CANDIDATE DETAILS ").append(System.lineSeparator());
	  sb.append(":Name: " + mappingDto.getCandidateName()); 
	  sb.append(" :Location: " + mappingDto.getLocation()); 
	  sb.append(" :Contact No: " +  mappingDto.getContactNumber()); 
	  sb.append("  :Email: " +mappingDto.getEmail()).append(System.lineSeparator());;
	  sb.append(" :Availability: " + mappingDto.getAvailabilityToJoin());
	  sb.append(" :Expected Salary: " + mappingDto.getExpectedSalary());
	  sb.append(" :Total Experience: " + mappingDto.getTotalExp());
	  sb.append(" :Relevant Experience: " + mappingDto.getRelevantExp()).append(System.lineSeparator());
	  sb.append(" :Relocation In Future: " + mappingDto.getRelocationInFuture());
	  sb.append(" :Applied for this role Before?: " + mappingDto.getAppliedBefore());
	  sb.append(" :Authorised Renaissance?: " + mappingDto.getAuthoriseRenaissance()).append(System.lineSeparator());
	  sb.append(" :Other details: " + mappingDto.getComments());
	  sb.append(" :Visa: " + mappingDto.getVisaStatus()).append(System.lineSeparator());
	  logger.info("Mail data: {}", sb.toString());
	  
	  return sb.toString();
	  
}
}