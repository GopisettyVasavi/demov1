package com.profile.parser.util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * This class is used to have all the utility methods required by Pprofile Parser.
 * @author Vasavi
 *
 */
public class ProfileParserUtils {
	
	/**
	 *  Checks whether the session is alive or not.
	 * @param request
	 * @return
	 */
	public static final boolean isSessionAlive(HttpServletRequest request) {
		HttpSession session=request.getSession(false) ;
		if (session == null || session.isNew() || null== request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME)
				|| "".equalsIgnoreCase( request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString())) //New session.
			return false;
		else return true;
			
		
	}
	
	/**
	 * Checks if is collection empty.
	 *
	 * @param collection the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is object empty.
	 *
	 * @param object the object
	 * @return true, if is object empty
	 */
	public static boolean isObjectEmpty(Object object) {
		if(object == null) return true;
		else if(object instanceof String) {
			if (((String)object).trim().length() == 0) {
				return true;
			}
		} else if(object instanceof Collection) {
			return isCollectionEmpty((Collection<?>)object);
		}
		return false;
	}
}
