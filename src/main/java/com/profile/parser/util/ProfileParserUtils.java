package com.profile.parser.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileParserUtils {
	public static final boolean isSessionAlive(HttpServletRequest request) {
		HttpSession session=request.getSession(false) ;
		if (session == null || session.isNew() || null== request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME)
				|| "".equalsIgnoreCase( request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString())) //New session.
			return false;
		else return true;
			
		
	}

}
