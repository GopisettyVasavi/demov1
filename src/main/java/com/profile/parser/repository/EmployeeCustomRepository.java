package com.profile.parser.repository;

import com.profile.parser.model.EmployeeEntity;

public interface EmployeeCustomRepository {
	EmployeeEntity getEmployeeDetails(String loggedInUserName, String loginPwd);

}
