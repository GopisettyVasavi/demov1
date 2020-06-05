package com.renaissance.contractor.web;

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

import com.renaissance.contractor.dto.ContractorDetailsDTO;
import com.renaissance.contractor.service.ContractorManagementService;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class ContractorDetailsMVCController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContractorDetailsMVCController.class);
	@Autowired
	ContractorManagementService contractorService;
	
	 @GetMapping("/contractordetails")
	    public String index( HttpServletRequest request) {
		// logger.info("Controller invoked");
		 if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return "redirect:/";
			}
	        return "contractordetails";
	    }
	 
	 
	 @GetMapping("/contractordetails/{contractorId}")
	    public String loadContractorDetails(@PathVariable BigInteger contractorId, RedirectAttributes redirectAttributes,
				Model model, HttpServletRequest request) {
		 if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return "redirect:/";
			}
		 logger.info("Controller invoked, to load details...id is, {}", contractorId);
		 ContractorDetailsDTO contractorDto= contractorService.getContractorFullDetails(contractorId);
		 logger.info("Details...,{} ",contractorDto.getPersonalDetails().toString());
		 logger.info("Details...,{} ",contractorDto.getBankList().get(0).toString());
		 logger.info("Details...,{} ",contractorDto.getSuperAnnuationList().get(0).toString());
		 logger.info("Details...,{} ",contractorDto.getTfnList().get(0).toString());
		 logger.info("Details...,{} ",contractorDto.getAbnList().get(0).toString());
		 logger.info("Details...,{} ",contractorDto.getEmployerList().get(0).toString());
		 logger.info("Details...,{} ",contractorDto.getRateList().get(0).toString());
		 redirectAttributes.addFlashAttribute("contractor", contractorDto);
		 model.addAttribute("contractor", contractorDto);
		/*
		 * CandidateDTO
		 * candidateDto=profileService.getCandidateFullDetails(candidateId);
		 * candidateDto=profileparserService.splitName(candidateDto); //
		 * candidateDto=ProfileParserUtils.parseAllDates(candidateDto);
		 * redirectAttributes.addFlashAttribute("profile", candidateDto);
		 * model.addAttribute("profile", candidateDto);
		 */
		// logger.info("LASTUPDATED DATE::: ,{}",candidateDto.getLastUpdatedByDateTime());
		// logger.info("Assigned date and work dates.., {}, {}",candidateDto.getAssignedDate(), candidateDto.getWorkStartDate());
	        return "redirect:/contractordetails";
	    }
	 
	

}
