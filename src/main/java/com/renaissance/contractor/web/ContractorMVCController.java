package com.renaissance.contractor.web;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.renaissance.contractor.dto.ContractorDetailsDTO;
import com.renaissance.contractor.model.ContractorSearchForm;
import com.renaissance.contractor.model.ContractorSearchResultsForm;
import com.renaissance.contractor.service.ContractorManagementService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class ContractorMVCController {
	private static final Logger logger = LoggerFactory.getLogger(ContractorMVCController.class);

	@Autowired
	ContractorManagementService contractorService;

	@GetMapping("/contractormain")
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))
			return "contractormain";
		else
			return "unauthorizedaccess";

	}

	/**
	 * This method will call service method and create new contractor.
	 * 
	 * @param contractorDto
	 * @param request
	 * @return
	 */
	@PostMapping("/createContractor")
	public ResponseEntity<?> createContractor(@RequestBody ContractorDetailsDTO contractorDto,
			HttpServletRequest request) {
		try {
			logger.info("Create Contractor..,{}", contractorDto.getPersonalDetails().toString());
			logger.info("Create Contractor Bank details..,{}", contractorDto.getBankList().toString());
			logger.info("Create Contractor SA details..,{}", contractorDto.getSuperAnnuationList().toString());
			logger.info("Create Contractor Employment details..,{}", contractorDto.getEmployerList().toString());
			logger.info("Create Contractor Rate details..,{}", contractorDto.getRateList().toString());
			// logger.info("Create Contractor ABN details..,{}",
			// contractorDto.getAbnList().toString());
			// logger.info("Create Contractor TFN details..,{}",
			// contractorDto.getTfnList().toString());

			contractorDto = contractorService.createContractor(contractorDto,
					request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());

			logger.info("Created contractor ID...,{}", contractorDto.getPersonalDetails().getContractorId());
			// contractorDto.getPersonalDetails().setL
		} catch (Exception e) {
			logger.error("Error in Creating contract,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(contractorDto);
	}

	/**
	 * This method will copy the selected file and return the path
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/copyCertificate", method = RequestMethod.POST)
	public ResponseEntity<?> copySingleFile(@RequestParam("uploadedCertificate") MultipartFile file,
			HttpServletRequest request) {
		logger.info("File selected... {}", file);
		String fileName = null;
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is not selected");

		}
		try {
			File convFile = null;

			if (file.getOriginalFilename().contains(":")) {// From IE
				fileName = file.getOriginalFilename();
				// convFile = new File(fileName);// Converts multi part file to file

				Path src = Paths.get(file.getOriginalFilename());
				fileName = ProfileParserConstants.CURRENT_DIR + ProfileParserConstants.UPLOAD_FOLDER
						+ src.getFileName();
				Path dest = Paths.get(fileName);
				try {
					Files.copy(src.toFile(), dest.toFile());
					Thread.sleep(5000);
				} catch (Exception e) {
					logger.error("error in copying file,{},{},{}", src, dest, new Exception(e.getMessage()));
					e.printStackTrace();
				}
			} else {
				fileName = ProfileParserConstants.CURRENT_DIR + ProfileParserConstants.UPLOAD_FOLDER
						+ file.getOriginalFilename(); // chrome convFile
				convFile = new File(fileName);
				file.transferTo(convFile);// Converts multi part file to file

			}

		} catch (Exception e) {
			logger.error("There is an issue in uploading  certificate...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}

		return ResponseEntity.ok(ProfileParserConstants.DEST_FOLDER + file.getOriginalFilename());
	}

	/**
	 * This method will search contractor by the given criteria and return matching
	 * records.
	 * 
	 * @param contractorSearchForm
	 * @param request
	 * @return
	 */
	@PostMapping("/searchContractors")
	public ResponseEntity<?> searchContractors(@RequestBody ContractorSearchForm contractorSearchForm,
			HttpServletRequest request) {
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}

			logger.info("Search details, {}", contractorSearchForm.toString());

		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		List<ContractorSearchResultsForm> contractors = contractorService
				.getContractorSearchResults(contractorSearchForm);

		return new ResponseEntity<>(contractors, HttpStatus.OK);
	}

}
