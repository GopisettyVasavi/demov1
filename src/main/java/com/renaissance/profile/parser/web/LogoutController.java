package com.renaissance.profile.parser.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * this controllerr class will be invoked to logout the user
 * @author Vasavi
 *
 */
@Controller
public class LogoutController {
	private static final Logger logger=LoggerFactory.getLogger(LogoutController.class);
	 @GetMapping("/logout")
	    public String index() {
	        return "logout";
	    }
/**
 * This method will invalidate the session and logs out the user.
 * @param request
 * @return
 */
	 @GetMapping("/logoutuser")
		public String logout(HttpServletRequest request){
			try {
				logger.info("Logout invoked...");
				request.logout();
			
			HttpSession session = request.getSession(false);
		      if (session != null) {
		          //invalidate session specially if we have added some user specific info
		          session.invalidate();
		      }
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "redirect:/logout";
		}
}
