package com.renaissance.profile.parser.web;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renaissance.profile.parser.model.AjaxResponseBody;
import com.renaissance.profile.parser.model.EmployeeForm;
import com.renaissance.profile.parser.model.PasswordChangeForm;
import com.renaissance.profile.parser.model.User;
import com.renaissance.profile.parser.service.EmployeeService;
import com.renaissance.profile.parser.service.UserService;
import com.renaissance.profile.parser.util.ProfileParserUtils;

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
	
	@GetMapping("/recruiterList")
    public ResponseEntity<?> loadCandidateDetails( RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {
	 if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
	logger.info("Controller invoked to retrieve recruiter list ");
	 
	List<User> recruiterList=userService.getRecruitersList();
	 redirectAttributes.addFlashAttribute("recruiters", recruiterList);
	 model.addAttribute("recruiters", recruiterList);
	 logger.info("Recruiter list size...,{} ",recruiterList.size());
	// logger.info("LASTUPDATED DATE::: ,{}",candidateDto.getLastUpdatedByDateTime());
	// logger.info("Assigned date and work dates.., {}, {}",candidateDto.getAssignedDate(), candidateDto.getWorkStartDate());
        return ResponseEntity.ok(recruiterList);
    }
}
