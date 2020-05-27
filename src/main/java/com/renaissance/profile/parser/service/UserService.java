package com.renaissance.profile.parser.service;

import org.bouncycastle.openssl.PasswordFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.profile.parser.model.EmployeeEntity;
import com.renaissance.profile.parser.model.LoginForm;
import com.renaissance.profile.parser.model.PasswordChangeForm;
import com.renaissance.profile.parser.model.User;
import com.renaissance.profile.parser.repository.EmployeeRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;
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
	
	public void passwordchange(PasswordChangeForm passwordChangeForm) throws Exception {
		EmployeeEntity employee = employeeRepository.getEmployeeDetails(passwordChangeForm.getUsername(),
				passwordChangeForm.getOldPassword());

		if (!ProfileParserUtils.isObjectEmpty(employee)) {//if Employee exists and the old password is correct
			employee.setProfileParserAppPwd(passwordChangeForm.getNewPassword());
			employeeRepository.save(employee);
		}
		else {
			throw new Exception();
		}
		
	}

}
