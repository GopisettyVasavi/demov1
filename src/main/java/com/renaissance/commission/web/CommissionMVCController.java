package com.renaissance.commission.web;

import static com.renaissance.util.APIConstants.COMMISSION_MAIN;
import static com.renaissance.util.APIConstants.CREATE_COMMISSION_RUN;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renaissance.commission.dto.CommissionDTO;
import com.renaissance.commission.service.CommissionManagmentService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class CommissionMVCController {
	private static final Logger logger = LoggerFactory.getLogger(CommissionMVCController.class);

	@Autowired
	CommissionManagmentService commissionService;

	/**
	 * This method is invoked to load contractor main page.
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(COMMISSION_MAIN)
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))
			return "commissionmain";
		else
			return "unauthorizedaccess";

	}

	@GetMapping(CREATE_COMMISSION_RUN)
	public ResponseEntity<?> commissionRun(@PathVariable String monthyear,
			RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		List<CommissionDTO>commissionList=new ArrayList<CommissionDTO>();
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}

			// logger.info("Search details, {}", contractorSearchForm.toString());
			logger.info("Selected month & Year,{}", monthyear);
			if(!ProfileParserUtils.isObjectEmpty(monthyear)&& monthyear.indexOf("-")!=-1) {
				String[] mmyy=monthyear.split("-");
				if(mmyy.length>1) {
				String commissionMonthYear=mmyy[1]+"/"+mmyy[0];
				
				logger.info("Selected month & Year,{}", commissionMonthYear);
				commissionList=commissionService.getCommissions(commissionMonthYear);
				}
			}
			
		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}

		return new ResponseEntity<>(commissionList, HttpStatus.OK);
	}

}
