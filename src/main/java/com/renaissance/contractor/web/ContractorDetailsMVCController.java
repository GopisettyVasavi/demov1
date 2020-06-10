package com.renaissance.contractor.web;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renaissance.contractor.dto.ContractorABNDetailsDTO;
import com.renaissance.contractor.dto.ContractorBankDetailsDTO;
import com.renaissance.contractor.dto.ContractorDetailsDTO;
import com.renaissance.contractor.dto.ContractorEmploymentDetailsDTO;
import com.renaissance.contractor.dto.ContractorRateDetailsDTO;
import com.renaissance.contractor.dto.ContractorSuperAnnuationDetailsDTO;
import com.renaissance.contractor.dto.ContractorTFNDetailsDTO;
import com.renaissance.contractor.service.ContractorManagementService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class ContractorDetailsMVCController {

	private static final Logger logger = LoggerFactory.getLogger(ContractorDetailsMVCController.class);
	@Autowired
	ContractorManagementService contractorService;

	@GetMapping("/contractordetails")
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		return "contractordetails";
	}

	/**
	 * This method will load contractor full details for the selected contractor ID
	 * 
	 * @param contractorId
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 */

	@GetMapping("/contractordetails/{contractorId}")
	public String loadContractorDetails(@PathVariable BigInteger contractorId, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		logger.info("Controller invoked, to load details...id is, {}", contractorId);
		ContractorDetailsDTO contractorDto = contractorService.getContractorFullDetails(contractorId);
		logger.info("Details...,{} ", contractorDto.getPersonalDetails().toString());
		logger.info("Details...,{} ", contractorDto.getBankList().toString());
		logger.info("Details...,{} ", contractorDto.getSuperAnnuationList().toString());
		// logger.info("Details...,{} ",contractorDto.getTfnList().toString());
		// logger.info("Details...,{} ",contractorDto.getAbnList().toString());
		logger.info("Details...,{} ", contractorDto.getEmployerList().toString());
		logger.info("Details...,{} ", contractorDto.getRateList().toString());
		redirectAttributes.addFlashAttribute("contractor", contractorDto);
		model.addAttribute("contractor", contractorDto);

		return "redirect:/contractordetails";
	}

	/**
	 * This method will update the details of contractor
	 * 
	 * @param contractorDto
	 * @param request
	 * @return
	 */
	@PostMapping("/updateContractor")
	public ResponseEntity<?> updateContractor(@RequestBody ContractorDetailsDTO contractorDto,
			HttpServletRequest request) {
		try {
			logger.info("Update Contractor..,{}", contractorDto.getPersonalDetails().toString());
			logger.info("Update Contractor Bank details..,{}", contractorDto.getBankList().toString());
			logger.info("Update Contractor SA details..,{}", contractorDto.getSuperAnnuationList().toString());
			logger.info("Update Contractor Employment details..,{}", contractorDto.getEmployerList().toString());
			logger.info("Update Contractor Rate details..,{}", contractorDto.getRateList().toString());
			// logger.info("Update Contractor ABN details..,{}",
			// contractorDto.getAbnList().toString());
			// logger.info("Update Contractor TFN details..,{}",
			// contractorDto.getTfnList().toString());

			logger.info("Service call");

			contractorDto = contractorService.updateContractorDetails(contractorDto,
					request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());

			logger.info("Update contractor ID...,{}", contractorDto.getPersonalDetails().getContractorId());
			// contractorDto.getPersonalDetails().setL
		} catch (Exception e) {
			logger.error("Error in Creating contract,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(contractorDto);
	}
	
	/**
	 * This method will get all bank history records from service and sends back to UI.
	 * @param contractorId
	 * @param request
	 * @return
	 */
	@PostMapping("/bankhistory/{contractorId}")
	public ResponseEntity<?> loadBankHistoryDetails(@PathVariable BigInteger contractorId, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return  ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Controller invoked, to load bank details...id is, {}", contractorId);
		List<ContractorBankDetailsDTO> bankList = contractorService.getBankHistoryByContractorId(contractorId);
		
		

		return new ResponseEntity<>(bankList, HttpStatus.OK);
	}
	
	/**
	 * This method will get all ABN history records from service and sends back to UI.
	 * @param contractorId
	 * @param request
	 * @return
	 */
	@PostMapping("/abnhistory/{contractorId}")
	public ResponseEntity<?> loadABNHistoryDetails(@PathVariable BigInteger contractorId, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return  ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Controller invoked, to load abn details...id is, {}", contractorId);
		List<ContractorABNDetailsDTO> abnList = contractorService.getAbnHistoryByContractorId(contractorId);
		
		

		return new ResponseEntity<>(abnList, HttpStatus.OK);
	}
	
	/**
	 * This method will get all TFN history records from service and sends back to UI.
	 * @param contractorId
	 * @param request
	 * @return
	 */
	@PostMapping("/tfnhistory/{contractorId}")
	public ResponseEntity<?> loadTFNHistoryDetails(@PathVariable BigInteger contractorId, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return  ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Controller invoked, to load tfn details...id is, {}", contractorId);
		List<ContractorTFNDetailsDTO> tfnList = contractorService.getTfnHistoryByContractorId(contractorId);
		
		

		return new ResponseEntity<>(tfnList, HttpStatus.OK);
	}
	
	/**
	 * This method will get all Rate history records from service and sends back to UI.
	 * @param contractorId
	 * @param request
	 * @return
	 */
	@PostMapping("/ratehistory/{contractorId}")
	public ResponseEntity<?> loadRateHistoryDetails(@PathVariable BigInteger contractorId, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return  ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Controller invoked, to load tfn details...id is, {}", contractorId);
		List<ContractorRateDetailsDTO> rateList = contractorService.getRateHistoryByContractorId(contractorId);
		
		

		return new ResponseEntity<>(rateList, HttpStatus.OK);
	}
	
	
	/**
	 * This method will get all Super annuation history records from service and sends back to UI.
	 * @param contractorId
	 * @param request
	 * @return
	 */
	@PostMapping("/sahistory/{contractorId}")
	public ResponseEntity<?> loadSAHistoryDetails(@PathVariable BigInteger contractorId, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return  ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Controller invoked, to load tfn details...id is, {}", contractorId);
		List<ContractorSuperAnnuationDetailsDTO> saList = contractorService.getSAHistoryByContractorId(contractorId);
		
		

		return new ResponseEntity<>(saList, HttpStatus.OK);
	}
	
	/**
	 * This method will get all Employment history records from service and sends back to UI.
	 * @param contractorId
	 * @param request
	 * @return
	 */
	@PostMapping("/emphistory/{contractorId}")
	public ResponseEntity<?> loadEmployerHistoryDetails(@PathVariable BigInteger contractorId, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return  ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Controller invoked, to load tfn details...id is, {}", contractorId);
		List<ContractorEmploymentDetailsDTO> empList = contractorService.getEmployerHistoryByContractorId(contractorId);
		
		

		return new ResponseEntity<>(empList, HttpStatus.OK);
	}
	
	
	
}
