package com.profile.parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.parser.model.EmployeeEntity;
import com.profile.parser.model.LoginForm;
import com.profile.parser.model.User;
import com.profile.parser.repository.EmployeeRepository;
import com.profile.parser.util.ProfileParserUtils;
/**
 * This class is used to get information about the logged in user, used by Login module
 * @author Vasavi
 *
 */
@Service
public class UserService {

	@Autowired
	EmployeeRepository employeeRepository;

	public User login(LoginForm loginForm) {

		// do stuffs
		// dump user data
		EmployeeEntity employee = employeeRepository.getEmployeeDetails(loginForm.getUsername(),
				loginForm.getPassword());

		if (!ProfileParserUtils.isObjectEmpty(employee)) {// user exist.
			User user = new User(employee.getProfileParserAppLogin(), employee.getEmail(), employee.getEmployeeId(),
					employee.getEmployeeName(), employee.getEmployeeRole());

			return user;
		}

		else
			return null;
	}

}
