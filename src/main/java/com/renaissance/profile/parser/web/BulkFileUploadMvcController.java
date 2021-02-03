package com.renaissance.profile.parser.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.io.Files;
import com.renaissance.profile.parser.dto.CandidateDTO;
import com.renaissance.profile.parser.dto.FileConversionDTO;
import com.renaissance.profile.parser.model.BulkUploadResponse;
import com.renaissance.profile.parser.service.ProfileParserService;
import com.renaissance.profile.parser.service.ProfileService;
import com.renaissance.profile.parser.util.FileUtils;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import static com.renaissance.util.APIConstants.*;

@Controller
public class BulkFileUploadMvcController {
	private static final Logger logger = LoggerFactory.getLogger(BulkFileUploadMvcController.class);
	@Autowired
	ProfileParserService profileParserService;

	@Autowired
	ProfileService profileService;

	@GetMapping(BULK_FILE_UPLOAD)
	public String index(HttpServletRequest request) {
	//	logger.info("Bulk file upload Controller invoked");
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if(ProfileParserConstants.DATA_ENTRY_OPERATOR.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())||
				ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())) {
			logger.info("Bulk File upload: Loading main landing page.");
			return "bulkfileupload";
		}
			else
				return "unauthorizedaccess";
		
	}

	@PostMapping(BULK_UPLOAD) // //new annotation since 4.3
	public ResponseEntity<?> bulkFileUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

		BulkUploadResponse response = new BulkUploadResponse();

		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		logger.info("Bulk upload START::: ,{}", Calendar.getInstance().getTime());
		logger.info("List of uploaded files..." + files.length);
		if (!ProfileParserUtils.isObjectEmpty(files) && files.length > 0) {
			// List<CandidateDTO> candidatesList= new ArrayList<CandidateDTO>();
			List<CandidateDTO> successCandidateList = new ArrayList<CandidateDTO>();
			List<CandidateDTO> errorCandidateList = new ArrayList<CandidateDTO>();
			for (MultipartFile file : files) {
				CandidateDTO candidateDto = null;

				try {
					File convFile = null;
					boolean isChrome = false;
					String fileName = null;
					if (file.getOriginalFilename().contains(":")) {// From IE
						fileName = file.getOriginalFilename();
						convFile = new File(fileName);// Converts multi part file to file

						Path src = Paths.get(file.getOriginalFilename());
						fileName = ProfileParserConstants.CURRENT_DIR + src.getFileName();
						Path dest = Paths.get(fileName);
						try {
							Files.copy(src.toFile(), dest.toFile());
						} catch (IOException e) {
							logger.error("error in copying file,{},{},{}", src, dest,new Exception(e.getMessage()));
							e.printStackTrace();
						}
					} else {
						// Since it is not from IE, it will not have path. It only have file name.

						fileName = ProfileParserConstants.CURRENT_DIR +  file.getOriginalFilename(); // chrome convFile

						isChrome = true;
						convFile = new File(fileName);
						file.transferTo(convFile);// Converts multi part file to file

					}
					//candidateDto = profileParserService.parse(convFile);// Parse the given file and populates the data
							String filePath="";											// to DTO
					if (!FileUtils.isFileTypePdf(file.getOriginalFilename())) {
						String filenameWithoutX = FilenameUtils.removeExtension(convFile.getName());
						File pdfFile = new File(ProfileParserConstants.CURRENT_DIR
								 + filenameWithoutX + ".pdf");
						if (!pdfFile.exists()) {
							FileConversionDTO convertedFile = FileUtils.convertDoctoPdf(convFile,
									file.getContentType());
							filePath=ProfileParserConstants.DEST_FOLDER + convertedFile.getConvertedFile().getName();
						} else {
							filePath=ProfileParserConstants.DEST_FOLDER + pdfFile.getName();
						}
						
						candidateDto = profileParserService.parse(pdfFile);
						candidateDto.setFilePath(filePath);
					} else {
						candidateDto = profileParserService.parse(convFile);
						if (!isChrome) {
							candidateDto.setFilePath(file.getOriginalFilename());
						} else {
							candidateDto.setFilePath(ProfileParserConstants.DEST_FOLDER + file.getOriginalFilename());

						}

					}
					
					  
					  candidateDto.setAssignedToEmployeeId(null);
					  candidateDto.setAssignedToEmployeeName(""); 
					  candidateDto.setLastUpdatedByUser(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
					  candidateDto.setAssignedDate("");

					candidateDto = profileService.createProfile(candidateDto);
					
					candidateDto.setFileName(file.getOriginalFilename());
					successCandidateList.add(candidateDto);
				} catch (Exception e) {
					logger.error("Error Message:: {}", new Exception(e) );
					logger.error("There is an issue in uploading  profile, {}, {}, {}"+candidateDto.getCandidateName(),candidateDto.getPrimaryEmail(),candidateDto.getPrimaryPhone());
					candidateDto.setFileName(file.getOriginalFilename());
					errorCandidateList.add(candidateDto);
					e.printStackTrace();
				}

			}

			if (!ProfileParserUtils.isObjectEmpty(successCandidateList)) {
				response.setSuccessProfiles(successCandidateList);
			}
			if (!ProfileParserUtils.isObjectEmpty(errorCandidateList)) {
				response.setErrorProfiles(errorCandidateList);
			}
		}
		logger.info("Bulk upload END::: ,{}", Calendar.getInstance().getTime());
		return ResponseEntity.ok(response);
		// return response;
	}

	@GetMapping(FAILED_CANDIDATE_DETAILS)
	public String loadFailedCandidateDetails(@PathVariable String fileName, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		logger.info("Bulk File upload: Loading details of candidate whose details are failed to upload.");

		try {
		logger.info("File name... {}", fileName);
		String filenameWithoutX = FilenameUtils.removeExtension(fileName);
		File convFile = new File(
				ProfileParserConstants.CURRENT_DIR + filenameWithoutX + ".pdf");
		if (!ProfileParserUtils.isObjectEmpty(convFile)) {
			CandidateDTO candidateDto = profileParserService.parse(convFile);
			candidateDto.setFilePath(ProfileParserConstants.DEST_FOLDER + filenameWithoutX + ".pdf");
			redirectAttributes.addFlashAttribute("profile", candidateDto);
		}
		redirectAttributes.addFlashAttribute("Error", "Error in loading profile..");
		}catch(Exception e) {
			logger.error("Error in parsing...{}",new Exception(e.getMessage()));
			redirectAttributes.addFlashAttribute("Error", "Error in loading profile..");
		}
		return "redirect:/candidatedetails";
	}
}
