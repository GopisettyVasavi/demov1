package com.renaissance.commission.web;

import static com.renaissance.util.APIConstants.COMMISSIONS_LOOKUP;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.LOAD_COMMISSIONS_LOOKUP;
import static com.renaissance.util.APIConstants.SAVE_COMMISSION_LOOKUP;

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

import com.renaissance.commission.dto.CommissionsLookupDTO;
import com.renaissance.commission.service.CommissionManagmentService;
import com.renaissance.profile.parser.util.ProfileParserUtils;


@Controller
public class CommissionsLookupMVCController {
	private static final Logger logger=LoggerFactory.getLogger(CommissionsLookupMVCController.class);

	@Autowired
	CommissionManagmentService commissionService;
	
	@GetMapping(COMMISSIONS_LOOKUP)
    public String indexForm(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		
        return "commissionslookup";
    }
	/**
	 * This method will load all commissions
	 * @param request
	 * @return
	 */
	@GetMapping(LOAD_COMMISSIONS_LOOKUP)
	public ResponseEntity<?> getConstants(
			HttpServletRequest request) {
		List<CommissionsLookupDTO> lookupList=null;
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			lookupList=commissionService.loadAllCommissionsLookupValues();
			if(!ProfileParserUtils.isObjectEmpty(lookupList)) {
			logger.info("Commission lookup values from DB,{} ",lookupList);
				
			}
		} catch (Exception e) {
			logger.error("Error in loading constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in loading constant. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(lookupList, HttpStatus.OK);
	}
/**
 * This method will save commission lookup
 * @param commissionsLookupDTO
 * @param request
 * @return
 */
	
	@PostMapping(SAVE_COMMISSION_LOOKUP)
	public ResponseEntity<?> saveCommissionLookup(@RequestBody CommissionsLookupDTO commissionsLookupDTO,
			HttpServletRequest request) {
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			commissionsLookupDTO=commissionService.saveCommissionLookup(commissionsLookupDTO);
			
		} catch (Exception e) {
			logger.error("Error in Creating constants,{}", e.getMessage());
			return ResponseEntity.badRequest()
					.body(" An issue in creating constants. Please try again. \n" + e.getMessage());
		}
		
		return new ResponseEntity<>(commissionsLookupDTO, HttpStatus.OK);
	}
}
