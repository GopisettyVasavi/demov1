package com.profile.parser.web;

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

import com.profile.parser.dto.CandidateDTO;
import com.profile.parser.model.ProfileSearchForm;
import com.profile.parser.service.ProfileService;
import com.profile.parser.util.ProfileParserUtils;

@Controller
public class ProfileSearchMvcController {
	private static final Logger logger=LoggerFactory.getLogger(ProfileSearchMvcController.class);
	@Autowired
	ProfileService profileService;
	
	@GetMapping("/profilesearch")
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		return "profilesearch";
	}
	
	@GetMapping("/searchcandidates") 
	public  ResponseEntity<?> getSearchResults(@RequestBody ProfileSearchForm searchForm,  HttpServletRequest request) {
		
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("Session has expired.");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Search details, {}", searchForm.toString());
		List<CandidateDTO> candidates=profileService.searchProfiles(searchForm);
		logger.info("Search Results, {}", candidates.size());
		return new ResponseEntity<>(candidates, HttpStatus.OK);
		
	}

}
