package com.renaissance.common.web;

import static com.renaissance.util.APIConstants.CLIENT_LOOKUP;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.GET_CLIENT_COMPANIES;
import static com.renaissance.util.APIConstants.UPDATE_CLIENT_COMPANY;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.renaissance.common.dto.ClientCompanyDTO;
import com.renaissance.common.service.ClientCompanyDetailsService;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class ClientCompanyDetailsMVCController {
	private static final Logger logger=LoggerFactory.getLogger(ClientCompanyDetailsMVCController.class);

	@Autowired
	ClientCompanyDetailsService companyService;
	
	@GetMapping(CLIENT_LOOKUP)
    public String indexForm(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		
        return "clientlookup";
    }

	/**
	 * This method will load available constants.
	 * @param request
	 * @return
	 */
	@GetMapping(GET_CLIENT_COMPANIES)
	public ResponseEntity<?> getConstants(
			HttpServletRequest request) {
		List<ClientCompanyDTO> companyList=new ArrayList<ClientCompanyDTO>();
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			companyList=companyService.loadClientCompanies();
		} catch (Exception e) {
			logger.error("Error in loading Client Companies,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in loading client Companies. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(companyList, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param constantsDto
	 * @param request
	 * @return
	 */
	@PostMapping(UPDATE_CLIENT_COMPANY)
	//@Produces(MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> updateConstants(@RequestBody ClientCompanyDTO companyDto,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			companyDto=companyService.saveClientCompany(companyDto);
			

		} catch (Exception e) {
			logger.error("Error in Creating company,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating company. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(companyDto, HttpStatus.OK);
	}
}
