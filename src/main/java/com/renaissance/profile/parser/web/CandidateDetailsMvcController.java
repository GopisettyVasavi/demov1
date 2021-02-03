package com.renaissance.profile.parser.web;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renaissance.profile.parser.dto.CandidateDTO;
import com.renaissance.profile.parser.service.ProfileParserService;
import com.renaissance.profile.parser.service.ProfileService;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import static com.renaissance.util.APIConstants.*;
/**
 * This class will load the details of the candidate on click of
 *  table row in search results screen.
 */
@Controller
public class CandidateDetailsMvcController {
	
	private static final Logger logger=LoggerFactory.getLogger(CandidateDetailsMvcController.class);
	
	@Autowired
	ProfileService profileService;
	@Autowired
	ProfileParserService profileparserService;
	
	 @GetMapping(CANDIDATE_DETAILS)
	    public String index( HttpServletRequest request) {
		// logger.info("Controller invoked");
		 if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return EMPTY_REDIRECT;
			}
			logger.info("Candidate details Module: Loading main landing page.");

	        return "candidatedetails";
	    }
	 
	 @GetMapping(CANDIDATE_DETAILS_CANDIDATE_ID)
	    public String loadCandidateDetails(@PathVariable BigInteger candidateId, RedirectAttributes redirectAttributes,
				Model model, HttpServletRequest request) {
		 if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return "redirect:/";
			}
		 //logger.info("Controller invoked, id is, {}", candidateId);
			logger.info("Candidate details Module: Loading candidate details for id.{}",candidateId);

		 CandidateDTO candidateDto=profileService.getCandidateFullDetails(candidateId);
		 candidateDto=profileparserService.splitName(candidateDto);
		// candidateDto=ProfileParserUtils.parseAllDates(candidateDto);
		 redirectAttributes.addFlashAttribute("profile", candidateDto);
		 model.addAttribute("profile", candidateDto);
		// logger.info("LASTUPDATED DATE::: ,{}",candidateDto.getLastUpdatedByDateTime());
		// logger.info("Assigned date and work dates.., {}, {}",candidateDto.getAssignedDate(), candidateDto.getWorkStartDate());
	        return "redirect:/candidatedetails";
	    }

}
