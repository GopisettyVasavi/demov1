package com.renaissance.requirement.web;

import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.LOAD_ASSIGNED_REQUIREMENT;
import static com.renaissance.util.APIConstants.MAPPING_REQUIREMENT_MAIN;
import static com.renaissance.util.APIConstants.MAP_CANDIDATE_REQUIREMENT;
import static com.renaissance.util.APIConstants.SEARCH_CANDIDATES_REQUIREMENT;

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
				||(ProfileParserConstants.RECRUITER
						.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())))
			return "mappingrequirementmain";
		else
			return "unauthorizedaccess";

	}

	/**
	 * This method will load all commissions
	 * @param request
	 * @return
	 */
	@GetMapping(LOAD_ASSIGNED_REQUIREMENT)
	public ResponseEntity<?> loadassignedrequests(
			HttpServletRequest request) {
		List<RequirementDTO> requirementList=new ArrayList<RequirementDTO>();
		try {
			
			//lookupList=commissionService.loadAllCommissionsLookupValues();
			
			logger.info("EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_ROLE,{},{}, {}", request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID)
					,request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME), request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE));
			RequirementDTO requirementDto= new RequirementDTO();
			if(!ProfileParserUtils.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
			requirementDto.setAssignedRecruiter(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID)+"");
			Integer recruiterId=Integer.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID)+"");
			requirementDto.setRecruiterId(recruiterId);
			}
			requirementList=requirementService.searchRequirements(requirementDto);
			
		} catch (Exception e) {
			logger.error("Error in loading constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in loading constant. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(requirementList, HttpStatus.OK);
	}
	
	@PostMapping(MAP_CANDIDATE_REQUIREMENT)
	public ResponseEntity<?> mapCandidateRequirement(@RequestBody List<MappingCandidateRqmtDTO> mappingCandidateRqmtList,
			HttpServletRequest request) {
		try {
			logger.info("Create Requirement..,{}", mappingCandidateRqmtList.toString());
			if(!ProfileParserUtils.isObjectEmpty(mappingCandidateRqmtList)) {
			for(MappingCandidateRqmtDTO mappingDto: mappingCandidateRqmtList) {
				logger.info(" MappingCandidateRqmtDTO : , {}",mappingDto.toString());
				if(!ProfileParserUtils.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
					Integer recruiterId=Integer.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID)+"");
					mappingDto.setRecruiterId(recruiterId);
					}
				
			}
			requirementService.saveCandidateMapping(mappingCandidateRqmtList);
			}
		} catch (Exception e) {
			logger.error("Error in Creating contract,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(mappingCandidateRqmtList);
	}
	
	
	/**
	 * This method will take the search criteria entered by user and sends the details to the service to retrieve profiles matching the search criteria.
	 * @param searchForm
	 * @param request
	 * @return
	 */
	@PostMapping(SEARCH_CANDIDATES_REQUIREMENT) 
	public  ResponseEntity<?> getSearchResults(@RequestBody ProfileSearchForm searchForm,  HttpServletRequest request) {
		List<CandidateDTO> candidates= new ArrayList<CandidateDTO>();
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("Session has expired.");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Search details, {}", searchForm.toString());
		 candidates=profileService.searchProfiles(searchForm);
		 logger.info("List size:, {}",candidates.size());
		 List<MappingCandidateRqmtDTO> mappingList=requirementService.fetchRequirementMappings(null, searchForm.getRequirementId());
		 logger.info("mappingList size:, {}",mappingList.size());
		 if(!ProfileParserUtils.isObjectEmpty(mappingList)) {
			 int i=1;
			 for(MappingCandidateRqmtDTO mappingDto: mappingList) {
			 CandidateDTO candidateDto=profileService.getCandidateFullDetails(mappingDto.getCandidateId());
			 candidateDto.setRecruiterId(mappingDto.getRecruiterId());
			 candidateDto.setRequirementId(mappingDto.getRequirementId());
			 candidateDto.setInterestedInRole(mappingDto.getInterestedInRole());
			 candidateDto.setStatus(mappingDto.getStatus());
			 candidateDto.setAuthorisation(mappingDto.getAuthorisation());
			 candidateDto.setComments(mappingDto.getComments());
			 candidateDto.setInterestedInRole(mappingDto.getInterestedInRole());
			 candidateDto.setSno(i);
			 //get Logged in recruiterID
			 if(!ProfileParserUtils.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
					Integer recruiterId=Integer.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID)+"");
					
					if(!ProfileParserUtils.isObjectEmpty(recruiterId) && !ProfileParserUtils.isObjectEmpty(candidateDto.getRecruiterId())
						&&candidateDto.getRecruiterId().intValue() !=recruiterId ) {
						candidateDto.setDisableRow("true");
					}else {
						candidateDto.setDisableRow("false");
					}
			 }	
					
			 i++;
			 Predicate<CandidateDTO> condition = employee -> employee.getCandidateId()==mappingDto.getCandidateId();
	         
			 candidates.removeIf(condition);
			// candidates.re
			 candidates.add(candidateDto);
			 }
			 
		 }
		 if(!ProfileParserUtils.isObjectEmpty(candidates)) {
			 for(CandidateDTO candidate:candidates) {
				 if(candidate.getSno()==null)
					 candidate.setSno(99999);
			 }
		 }
		 candidates.sort(Comparator.comparing(CandidateDTO::getSno));

		 logger.info("After process List size:, {}",candidates.size());
		return new ResponseEntity<>(candidates, HttpStatus.OK);
		
	}
}
