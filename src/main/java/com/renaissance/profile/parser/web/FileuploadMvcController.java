package com.renaissance.profile.parser.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.io.Files;
import com.renaissance.profile.parser.dto.CandidateDTO;
import com.renaissance.profile.parser.dto.FileConversionDTO;
import com.renaissance.profile.parser.service.ProfileParserService;
import com.renaissance.profile.parser.service.ProfileService;
import com.renaissance.profile.parser.util.FileUtils;
import com.renaissance.profile.parser.util.GlobalProperties;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class FileuploadMvcController {
	// Save the uploaded file to this folder

	private static final Logger logger = LoggerFactory.getLogger(FileuploadMvcController.class);
	@Autowired
	ProfileParserService profileParserService;

	@Autowired
	ProfileService profileService;
	
	@GetMapping("/profileparser")
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		//logger.info("USER ROLE",request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString());
		if(ProfileParserConstants.RECRUITER.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()) ||
				ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))
		return "fileupload";
		else
			return "unauthorizedaccess";
	}

	/**
	 * This method will get the file uploaded by user and parse the details and
	 * populate them to candidateDTO and rended them on browser.
	 * 
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
			// byte[] bytes = file.getBytes();
			File convFile = null;
			boolean isChrome = false;

			logger.info("File original file name::{} ", file.getOriginalFilename());

			String fileName = null;
			// Copy file from source o destination(root dir). Since it is from IE, it will
			// have full path
			if (file.getOriginalFilename().contains(":")) {// From IE
				fileName = file.getOriginalFilename();
				convFile = new File(fileName);// Converts multi part file to file

				Path src = Paths.get(file.getOriginalFilename());
				fileName = ProfileParserConstants.CURRENT_DIR + ProfileParserConstants.UPLOAD_FOLDER
						+ src.getFileName();
				Path dest = Paths.get(fileName);
				try {
					Files.copy(src.toFile(), dest.toFile());
				} catch (IOException e) {
					logger.error("error in copying file,{},{},{}", src, dest,new Exception(e.getMessage()));
					e.printStackTrace();
				}
			} else {
				// Since it is not from IE, it will not have path. It only have file name.

				fileName = ProfileParserConstants.CURRENT_DIR + ProfileParserConstants.UPLOAD_FOLDER
						+ file.getOriginalFilename(); // chrome convFile

				isChrome = true;
				convFile = new File(fileName);
				file.transferTo(convFile);// Converts multi part file to file

			}
			//profileParserService.parseMetaData(fileName);
			// Parse the given file and populates the data to DTO
			String filePath="";
			//logger.info("CANDIDATE DTO , {} ", candidateDto.toString());
			if (!FileUtils.isFileTypePdf(file.getOriginalFilename())) {
				String filenameWithoutX=FilenameUtils.removeExtension(convFile.getName());
				File pdfFile= new File(ProfileParserConstants.CURRENT_DIR+ProfileParserConstants.UPLOAD_FOLDER +filenameWithoutX+".pdf");
				Thread.sleep(5000);
				logger.info("FILE EXISTS...."+pdfFile.exists());
				if(!pdfFile.exists()) {
				FileConversionDTO convertedFile = FileUtils.convertDoctoPdf(convFile, file.getContentType());
				logger.info("File before redirecting..{},{}", convertedFile.getFilepath(),
						convertedFile.getConvertedFile().getName());

				redirectAttributes.addFlashAttribute("fileLocation",
						"content//" + convertedFile.getConvertedFile().getName());
				filePath="content//" + convertedFile.getConvertedFile().getName();
				}else {
					redirectAttributes.addFlashAttribute("fileLocation",
							"content//" + pdfFile.getName());
					filePath="content//" + pdfFile.getName();
				}
				
				candidateDto = profileParserService.parse(pdfFile);
				candidateDto.setFilePath(filePath);
			} else {
				candidateDto = profileParserService.parse(convFile);
				if (!isChrome) {
					logger.info("File for IE render.. {}", file.getOriginalFilename());
					redirectAttributes.addFlashAttribute("fileLocation", file.getOriginalFilename());
					candidateDto.setFilePath(file.getOriginalFilename());
				} else {
					logger.info("File for chrome render.. {}", file.getOriginalFilename());

					redirectAttributes.addFlashAttribute("fileLocation", "content//" + file.getOriginalFilename());
					candidateDto.setFilePath("content//" + file.getOriginalFilename());

				}

			}
		} catch (Exception e) {
			logger.error("There is an issue in uploading  profile..{}",new Exception(e.getMessage()));
			e.printStackTrace();
		}

		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded '" + file.getOriginalFilename() + "'");
		if (!ProfileParserUtils.isObjectEmpty(candidateDto)) {
			candidateDto.setAssignedToEmployeeId(
					(Integer) request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ID));
			candidateDto.setAssignedToEmployeeName(
					request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
			candidateDto.setLastUpdatedByUser(
					request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
			candidateDto.setAssignedDate(ProfileParserUtils.parseDateToString(LocalDate.now()));
			redirectAttributes.addFlashAttribute("profile", candidateDto);
			model.addAttribute("profile", candidateDto);
		}

		return "redirect:/profileparser";
		// return response;
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}

	/**
	 * This method will get the profile details entered by user and sets logged in
	 * user information and creates/updates profile.
	 * 
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
			long days=0;
			logger.info("Saving the details for candidate: {},{},{}", profile.getCandidateName(),
					profile.getPrimaryEmail(), profile.getPrimaryPhone());
			//logger.info("Date::: ,{}",profile.getLastUpdatedByDateTime());
			if(!ProfileParserUtils.isObjectEmpty(profile.getAssignedDate())) {
			 days=ProfileParserUtils.getDays(ProfileParserUtils.parseStringDate(profile.getAssignedDate()), LocalDate.now());
			//logger.info("DAYS DIFFERENCE...,{}",days);
			 }
			
			  if(days>28){
			 // logger.info("UPDATE PROFILE....,{}",profile.getAssignedToEmployeeName());
			  
			  profile.setAssignedToEmployeeId(null);
			  profile.setAssignedToEmployeeName("");
			  profile.setAssignedDate("");
			
			  }
			  
			  profile.setLastUpdatedByUser(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
			//  logger.info("NAMES::: {}, {}, {} ", profile.getFirstName(),profile.getMiddleName(),profile.getLastName());
			profile = profileService.createProfile(profile);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("An issue in creating profile. Please try again.");
		}
		return ResponseEntity.ok(profile);
	}
/**
 * This method will copy the selected file to the destination folder.
 * @param file
 * @param request
 * @return
 */
	@RequestMapping(value = "/copyFile", method = RequestMethod.POST)
	public ResponseEntity<?> copySingleFile(@RequestParam("selFile") MultipartFile file, HttpServletRequest request) {
		logger.info("File selected... {}", file);
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("File is not selected");

		}
		try {
			File convFile = null;
			String fileName = null;
			if (file.getOriginalFilename().contains(":")) {// From IE
				fileName = file.getOriginalFilename();
				convFile = new File(fileName);// Converts multi part file to file

				Path src = Paths.get(file.getOriginalFilename());
				fileName = ProfileParserConstants.CURRENT_DIR + ProfileParserConstants.UPLOAD_FOLDER
						+ src.getFileName();
				Path dest = Paths.get(fileName);
				try {
					Files.copy(src.toFile(), dest.toFile());
					Thread.sleep(5000);
				} catch (Exception e) {
					logger.error("error in copying file,{},{},{}", src, dest,new Exception(e.getMessage()));
					e.printStackTrace();
				}
			} else {
				fileName = ProfileParserConstants.CURRENT_DIR + ProfileParserConstants.UPLOAD_FOLDER
						+ file.getOriginalFilename(); // chrome convFile
				convFile = new File(fileName);
				file.transferTo(convFile);// Converts multi part file to file
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// Check file type. If not pdf, convert to pdf
			String filenameWithoutX=FilenameUtils.removeExtension(file.getOriginalFilename());
			File pdfFile= new File(ProfileParserConstants.CURRENT_DIR+ProfileParserConstants.UPLOAD_FOLDER +filenameWithoutX+".pdf");
			if (!FileUtils.isFileTypePdf(file.getOriginalFilename())&&!pdfFile.exists()) {
				FileConversionDTO convertedFile = FileUtils.convertDoctoPdf(convFile, file.getContentType());
				Thread.sleep(5000);

			}
		} catch (Exception e) {
			logger.error("There is an issue in uploading  profile...{}",new Exception(e.getMessage()));
			e.printStackTrace();
		}

		return ResponseEntity.ok("Success");

	}

	@RequestMapping(value = "/testService", method = RequestMethod.GET)
	public String invokeService() {
		
		logger.info("INVOKE SERVICE...");
		return "redirect:/profileparser";
		
	}
	
}