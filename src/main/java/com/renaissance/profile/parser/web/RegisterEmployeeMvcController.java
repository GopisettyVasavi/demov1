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

import com.renaissance.profile.parser.model.AjaxResponseBody;
import com.renaissance.profile.parser.model.EmployeeForm;
import com.renaissance.profile.parser.model.PasswordChangeForm;
import com.renaissance.profile.parser.service.EmployeeService;
import com.renaissance.profile.parser.service.UserService;

@Controller
public class RegisterEmployeeMvcController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterEmployeeMvcController.class);

	@Autowired
	EmployeeService employeeService;
	@Autowired
	UserService userService;

	@GetMapping("/registeremployee")
	public String index(HttpServletRequest request) {
		logger.info("Register Employee Mvc Controller invoked");
		
		return "createemployee";
	}

	@GetMapping("/changepassword")
	public String changePassword(HttpServletRequest request) {
		logger.info("Register Employee Mvc Controller invoked, ChangePassword");
		
		return "changepassword";
	}

	@PostMapping("/saveemployee")
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeForm employeeForm, HttpServletRequest request) {
		AjaxResponseBody result = new AjaxResponseBody();
		EmployeeForm employee=null;
		logger.info("Employee details...,{},{}", employeeForm.getEmployeeName(), employeeForm.getEmployeeRole());

		try {
			//employeeForm.setLastUpdatedByUser(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString());
			employeeService.registerEmployee(employeeForm);
		} catch (Exception e) {
			logger.error("Error in controller to register Employee,{}",new Exception(e.getMessage()));
			result.setMsg(e.getMessage());
			
			return ResponseEntity.badRequest().body(employeeForm);
		}
		result.setMsg("Employee Registered!");
		result.setResult(employee);
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/passwordchange")
	public ResponseEntity<?> passwordChange(@RequestBody PasswordChangeForm passwordChangeForm, HttpServletRequest request) {
		AjaxResponseBody result = new AjaxResponseBody();

		logger.info("Employee details...,{}", passwordChangeForm.toString());

		try {
			userService.passwordchange(passwordChangeForm);
		} catch (Exception e) {
			logger.error("Error in controller to update Password, {}",new Exception(e.getMessage()));
			//result.setMsg("No user found!");
// logger.error("No users found for: {}"+loginForm.getUsername());
			return ResponseEntity.badRequest().body(passwordChangeForm);
		}
		result.setMsg("Employee Password Updated!");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
}
