package com.renaissance.common.web;

import static com.renaissance.util.APIConstants.CONSTANTS_DEF;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.GET_CONSTANTS;
import static com.renaissance.util.APIConstants.UPDATE_CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			HttpServletRequest request, HttpServletResponse response) {
		try {
			constantsDto=constantsService.saveConstant(constantsDto);
			} catch (Exception e) {
			logger.error("Error in Creating constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating constants. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(constantsDto, HttpStatus.OK);
	}
	/**
	 * This method will load available constants.
	 * @param request
	 * @return
	 */
	@GetMapping(GET_CONSTANTS)
	public ResponseEntity<?> getConstants(
			HttpServletRequest request) {
		List<ConstantsDTO> constantsList=new ArrayList<ConstantsDTO>();
		try {
			
			constantsList=constantsService.getConstants();
		} catch (Exception e) {
			logger.error("Error in loading constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in loading constant. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(constantsList, HttpStatus.OK);
	}

}
