package com.renaissance.requirement.web;

import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.MAPPING_REQUIREMENT_MAIN;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;
@Controller
public class MappingRequirementMVCController {
	private static final Logger logger = LoggerFactory.getLogger(MappingRequirementMVCController.class);

	/**
	 * This method is invoked to load contractor main page.
	 * @param request
	 * @return
	 */
	@GetMapping(MAPPING_REQUIREMENT_MAIN)
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())
				||(ProfileParserConstants.RECRUITER
						.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString())))
			return "mappingrequirementmain";
		else
			return "unauthorizedaccess";

	}

}
