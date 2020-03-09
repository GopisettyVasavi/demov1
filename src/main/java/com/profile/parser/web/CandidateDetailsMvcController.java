package com.profile.parser.web;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * This class will load the details of the candidate on click of
 *  table row in search results screen.
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.profile.parser.dto.CandidateDTO;
import com.profile.parser.service.ProfileService;
import com.profile.parser.util.ProfileParserUtils;

@Controller
public class CandidateDetailsMvcController {
	
	private static final Logger logger=LoggerFactory.getLogger(CandidateDetailsMvcController.class);
	
	@Autowired
	ProfileService profileService;
	
	 @GetMapping("/candidatedetails")
	    public String index( HttpServletRequest request) {
		 logger.info("Controller invoked");
		 if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return "redirect:/";
			}
	        return "candidatedetails";
	    }
	 
	 @GetMapping("/candidatedetails/{candidateId}")
	    public String loadCandidateDetails(@PathVariable BigInteger candidateId, RedirectAttributes redirectAttributes,
				Model model, HttpServletRequest request) {
		 if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return "redirect:/";
			}
		 logger.info("Controller invoked, id is, {}", candidateId);
		 
		 CandidateDTO candidateDto=profileService.getCandidateFullDetails(candidateId);
		 candidateDto=ProfileParserUtils.parseAllDates(candidateDto);
		 redirectAttributes.addFlashAttribute("profile", candidateDto);
		 model.addAttribute("profile", candidateDto);
		 logger.info("Assigned date and work dates.., {}, {}",candidateDto.getAssignedDate(), candidateDto.getWorkStartDate());
	        return "redirect:/candidatedetails";
	    }

}
