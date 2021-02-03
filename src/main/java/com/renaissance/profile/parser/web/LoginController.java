package com.renaissance.profile.parser.web;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renaissance.profile.parser.model.AjaxResponseBody;
import com.renaissance.profile.parser.model.LoginForm;
import com.renaissance.profile.parser.model.User;
import com.renaissance.profile.parser.service.UserService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import static com.renaissance.util.APIConstants.*;


@Controller
public class LoginController {
	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	 @GetMapping("/")
	    public String index() {
		 logger.info("Login Module: Login Main landing page loaded...");
	        return "login";
	    }
/**
 * This method will check whether the details entered by user or correct or not against database and sends response accordingly.
 * @param loginForm
 * @param errors
 * @param redirectAttributes
 * @param request
 * @return
 */
		@PostMapping(LOGIN)
	    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody LoginForm loginForm, Errors errors, RedirectAttributes redirectAttributes, HttpServletRequest request) {
			    AjaxResponseBody result = new AjaxResponseBody();
			    logger.info("Inside Login user entered values...,{}",loginForm.getUsername());
	        //If error, just return a 400 bad request, along with the error message
	        if (errors.hasErrors()) {

	            result.setMsg(errors.getAllErrors()
	                    .stream().map(x -> x.getDefaultMessage())
	                    .collect(Collectors.joining(",")));
	            return ResponseEntity.badRequest().body(result);

	        }
	       
	        User user = userService.login(loginForm);
	        if (null==user) {
	            result.setMsg("No user found!");
	            logger.error("No users found for: {}"+loginForm.getUsername());
	            return ResponseEntity.badRequest().body(loginForm);
	            
	        } else {
	            result.setMsg("success");
	            logger.info("Found matching User details for: {}"+loginForm.getUsername());
	            result.setResult(user);
	            request.getSession().setAttribute(ProfileParserConstants.EMPLOYEE_NAME, user.getEmployeeName());
	            request.getSession().setAttribute(ProfileParserConstants.EMPLOYEE_ID, user.getEmployeeId());
	            request.getSession().setAttribute(ProfileParserConstants.EMPLOYEE_ROLE, user.getEmployeeRole());
	        }
	       

	        return ResponseEntity.ok(result);

	    }
		
		@GetMapping(INDEX)
	    public String indexForm(HttpServletRequest request) {
		/*
		 * if (!ProfileParserUtils.isSessionAlive(request)) {
		 * logger.info("null session"); return "redirect:/"; }
		 */
	        return "index";
	    }
		
		@GetMapping(INDEX_LOAD)
	    public ResponseEntity<?> indexLoad(RedirectAttributes redirectAttributes,
				Model model, HttpServletRequest request) {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("null session");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			logger.info("Logged in user name before rendering page, pageload.. {}",request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME));
			

	        return ResponseEntity.ok(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE));
	    }
	 
}
