package com.renaissance.contractor.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class ContractorMVCController {
	private static final Logger logger = LoggerFactory.getLogger(ContractorMVCController.class);
	
	@GetMapping("/contractormain")
	public String index(HttpServletRequest request) {
	//	logger.info("Bulk file upload Controller invoked");
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return "redirect:/";
		}
		if(ProfileParserConstants.ADMIN.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))
			return "contractormain";
			else
				return "unauthorizedaccess";
		
	}

}
