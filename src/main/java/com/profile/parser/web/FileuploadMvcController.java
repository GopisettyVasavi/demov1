package com.profile.parser.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.profile.parser.dto.CandidateDTO;
import com.profile.parser.dto.FileConversionDTO;
import com.profile.parser.service.ProfileParserService;
import com.profile.parser.service.ProfileService;
import com.profile.parser.util.FileUtils;
import com.profile.parser.util.ProfileParserConstants;
import com.profile.parser.util.ProfileParserUtils;

@Controller
public class FileuploadMvcController {
	// Save the uploaded file to this folder
	
	private static final Logger logger=LoggerFactory.getLogger(FileuploadMvcController.class);
	@Autowired
	ProfileParserService profileParserService;

	@Autowired
	ProfileService profileService;

	@GetMapping("/fileupload")
	public String index( HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		return "fileupload";
	}
/**
 * This method will get the file uploaded by user and parse the details and populate them to candidateDTO and rended them on browser.
 * @param file
 * @param redirectAttributes
 * @param model
 * @param request
 * @return
 */
	@PostMapping("/upload") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return null;
		}
		CandidateDTO candidateDto = null;

		try {
			

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			File convFile = null;

			logger.info("File original file name::{} " , file.getOriginalFilename());

			String fileName = null;
			if (file.getOriginalFilename().contains(":")) {// From IE
				fileName = file.getOriginalFilename();
				convFile = new File(fileName);
				// System.out.println("IE:: File content" + convFile);
			} else {

				fileName = ProfileParserConstants.UPLOADED_FOLDER + file.getOriginalFilename(); // chrome convFile

				// Path path=Paths.

				convFile = new File(fileName);
				file.transferTo(convFile);
				}
			candidateDto = profileParserService.parse(convFile);
			logger.info("DTO Values:: " + candidateDto.toString());
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
			
			//Check file type. If not pdf, convert to pdf
			if(!FileUtils.isFileTypePdf(file.getOriginalFilename())){
				FileConversionDTO convertedFile=FileUtils.convertDoctoPdf(convFile,file.getContentType());
				//File generatedPdf=	new File(ProfileParserConstants.UPLOADED_FOLDER+FilenameUtils.removeExtension(convFile.getName())+".pdf");
				logger.info("File after conversion.. {}, {},  {}", convertedFile.getFilepath(), file.getContentType(),convertedFile.getMultipartFile().getContentType() );
				redirectAttributes.addFlashAttribute("fileLocation", convertedFile.getFilepath());
				
			}else
				redirectAttributes.addFlashAttribute("fileLocation", file.getOriginalFilename());
		} catch (IOException e) { // TODO Auto-generated catch block
			logger.error("There is an issue in uploading  profile");
			e.printStackTrace();
		}

		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded '" + file.getOriginalFilename() + "'");
		redirectAttributes.addFlashAttribute("selectedFile", file.getOriginalFilename().toString());
		
		if (!ProfileParserUtils.isObjectEmpty(candidateDto)) {
			redirectAttributes.addFlashAttribute("profile", candidateDto);
			model.addAttribute("profile", candidateDto);
		}

		return "redirect:/fileupload";
		// return response;
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
/**
 * This method will get the profile details entered by user and sets logged in user information and creates/updates profile.
 * @param profile
 * @param request
 * @return
 */
	@PostMapping("/createProfile")
	public ResponseEntity<?> createProfile(@RequestBody CandidateDTO profile, HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("Session has expired.");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		try {
			logger.info("Saving the details for candidate: {},{},{}",profile.getCandidateName(),profile.getPrimaryEmail(),profile.getPrimaryPhone());
			profile.setAssignedToEmployeeId(
					(Integer) request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID));
			profile.setAssignedToEmployeeName(
					request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
			profile.setLastUpdatedByUser(
					request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
			
			profile=profileService.createProfile(profile);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("An issue in creating profile. Please try again.");
		}
		return ResponseEntity.ok(profile);
	}

}