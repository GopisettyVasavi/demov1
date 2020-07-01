package com.renaissance.profile.parser.web;

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

import com.renaissance.common.dto.ConstantsDTO;
import com.renaissance.common.service.ConstantsService;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import static com.renaissance.util.APIConstants.*;

@Controller
public class ConstantsMVCController {
	private static final Logger logger=LoggerFactory.getLogger(ConstantsMVCController.class);
	
	@Autowired
	ConstantsService constantsService;
	
	@GetMapping(CONSTANTS_DEF)
    public String indexForm(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		
        return "constants";
    }
	/**
	 * 
	 * @param constantsDto
	 * @param request
	 * @return
	 */
	@PostMapping(UPDATE_CONSTANTS)
	public ResponseEntity<?> updateConstants(@RequestBody ConstantsDTO constantsDto,
			HttpServletRequest request) {
		try {
			
			constantsDto=constantsService.saveConstants(constantsDto);
			
			// contractorDto.getPersonalDetails().setL
		} catch (Exception e) {
			logger.error("Error in Creating constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating constants. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(constantsDto, HttpStatus.OK);
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(GET_CONSTANTS)
	public ResponseEntity<?> getConstants(
			HttpServletRequest request) {
		ConstantsDTO constantsDto=null;
		try {
			
			 constantsDto=constantsService.getConstants();
			logger.info("Constants values from DB,{} ",constantsDto.toString());
			// contractorDto.getPersonalDetails().setL
		} catch (Exception e) {
			logger.error("Error in loading constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating contract. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(constantsDto, HttpStatus.OK);
	}

}
