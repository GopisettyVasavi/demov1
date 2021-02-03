package com.renaissance.requirement.web;

import static com.renaissance.util.APIConstants.CREATE_REQUIREMENT;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.REQUIREMENT_MAIN;
import static com.renaissance.util.APIConstants.SEARCH_REQUIREMENT;
import static com.renaissance.util.APIConstants.UPDATE_REQUIREMENT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.renaissance.profile.parser.model.User;
import com.renaissance.profile.parser.service.EmployeeService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import com.renaissance.requirement.dto.RequirementDTO;
import com.renaissance.requirement.service.RequirementManagementService;

@Controller
public class RequirementMVCController {
	private static final Logger logger = LoggerFactory.getLogger(RequirementMVCController.class);
@Autowired
EmployeeService employeeService;
@Autowired
RequirementManagementService requirementService;

	/**
	 * This method is invoked to load contractor main page.
	 * @param request
	 * @return
	 */
	@GetMapping(REQUIREMENT_MAIN)
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())
				||
				ProfileParserConstants.RECRUITER
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())) {
			logger.info("Requirement Module: Loading Initial landing page.");
			return "requirementmain";
		}
		else
			return "unauthorizedaccess";

	}
	
	/**
	 * This method will call service method and create new Requirement.
	 * 
	 * @param contractorDto
	 * @param request
	 * @return
	 */
	@PostMapping(CREATE_REQUIREMENT)
	public ResponseEntity<?> createRequirement(@RequestBody RequirementDTO requirementDto,
			HttpServletRequest request) {
		try {
			logger.info("Requirement Module: Create Requirement..,{}", requirementDto.toString());
			String recruiters="";
			//getLogged in user id and email
			if (!ProfileParserUtils
					.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID))) {
				Integer userId = Integer
						.valueOf(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID) + "");
				User loggedInUser=employeeService.getRecruiterDetails(userId);
				requirementDto.setRequirementCreaterId(userId);
				requirementDto.setRequirementCreaterEmail(loggedInUser.getEmail());
			}
			if(!ProfileParserUtils.isObjectEmpty(requirementDto) && !ProfileParserUtils.isObjectEmpty( requirementDto.getRecruiters())
					&& requirementDto.getRecruiters().size()>0) {
				for(Integer employeeId:requirementDto.getRecruiters()) {
					recruiters+=employeeId+" ,";
					//Send Email
					User recruiter=employeeService.getRecruiterDetails(employeeId);
					String emailBody=prepareEmailBody(requirementDto);
					String subject=requirementDto.getVendorName()+" , "+requirementDto.getJobTitle()+" , "+requirementDto.getLocation()+" , "+requirementDto.getJobType();
					ProfileParserUtils.sendEmail(recruiter.getEmail(),subject,emailBody,null);
				}
			}
			if(recruiters.length()>0) {
				recruiters=recruiters.substring(0, recruiters.length()-1);
				requirementDto.setAssignedRecruiter(recruiters);
			}
			requirementDto.setStatus("Open");
			requirementDto.setCreatedDate(LocalDate.now());
			requirementService.createRequirement(requirementDto);		
			//employeeService.getRecruiterDetails(employeeId)
			// contractorDto.getPersonalDetails().setL
		} catch (Exception e) {
			logger.error("Error in Creating contract,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(requirementDto);
	}
	
	
	/**
	 * This method will search contractor by the given criteria and return matching
	 * records.
	 * 
	 * @param contractorSearchForm
	 * @param request
	 * @return
	 */
	@PostMapping(SEARCH_REQUIREMENT)
	public ResponseEntity<?> searchRequirements(@RequestBody RequirementDTO requirementDto,
			HttpServletRequest request) {
		List<RequirementDTO> requirements= new ArrayList<RequirementDTO>();
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			logger.info("Requirement Module: Searching Requirements.{}",requirementDto.toString());
			//logger.info("Search details, {}", requirementDto.toString());
			requirements=requirementService.searchRequirements(requirementDto);
			String disableStatus="";
			if (!ProfileParserUtils
					.isObjectEmpty(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE))
					&& !ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession()
							.getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString().trim())) {

				disableStatus="true";
			} else {
				disableStatus="false";
			}
			if(!ProfileParserUtils.isObjectEmpty(requirements)) {
				for(RequirementDTO requirement:requirements) {
					requirement.setDisableStatus(disableStatus);
				}
			}
		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		

		return new ResponseEntity<>(requirements, HttpStatus.OK);
	}
	
	
	/**
	 * This method will call service method and create new Requirement.
	 * 
	 * @param contractorDto
	 * @param request
	 * @return
	 */
	@PostMapping(UPDATE_REQUIREMENT)
	public ResponseEntity<?> updateRequirement(@RequestBody RequirementDTO requirementDto,
			HttpServletRequest request) {
		try {
			logger.info("Requirement Module: Update Requirement..,{}", requirementDto.toString());
			String recruiters="";
			if(!ProfileParserUtils.isObjectEmpty(requirementDto) && !ProfileParserUtils.isObjectEmpty( requirementDto.getRecruiters())
					&& requirementDto.getRecruiters().size()>0) {
				for(Integer employeeId:requirementDto.getRecruiters()) {
					recruiters+=employeeId+" ,";
					User recruiter=employeeService.getRecruiterDetails(employeeId);
					String subject=requirementDto.getVendorName()+" , "+requirementDto.getJobTitle()+" , "+requirementDto.getLocation()+" , "+requirementDto.getJobType();
					ProfileParserUtils.sendEmail(recruiter.getEmail(),subject,"",null);
				}
			}
			if(recruiters.length()>0) {
				recruiters=recruiters.substring(0, recruiters.length()-1);
				requirementDto.setAssignedRecruiter(recruiters);
			}
			//requirementDto.setStatus("Open");
			requirementDto.setModifiedDate(LocalDate.now());
			requirementService.createRequirement(requirementDto);		
			//employeeService.getRecruiterDetails(employeeId)
			// contractorDto.getPersonalDetails().setL
		} catch (Exception e) {
			logger.error("Error in Creating contract,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(requirementDto);
	}
	private String prepareEmailBody(RequirementDTO requirementDto ) {
		 StringBuilder sb = new StringBuilder();
		  sb.append("***********REQUIREMENT DETAILS************************** ").append(System.lineSeparator());
		  sb.append("Vendor Name: " + requirementDto.getVendorName()); 
		  sb.append(" :Requirement Id: " + requirementDto.getRequirementId()); 
		  sb.append(" :Job Type: " + requirementDto.getJobType()); 
		  sb.append(" :Job Title: " + requirementDto.getJobTitle()).append(System.lineSeparator()); 
		  sb.append(" :Location: " + requirementDto.getLocation()); 
		  sb.append(" :Salary: " + requirementDto.getSalary()); 
		  sb.append(" :Job Description: " + requirementDto.getJobDescription()).append(System.lineSeparator()); 
		 
		  logger.info("Mail data: {}", sb.toString());
		  
		  return sb.toString();
		  
	}
}
