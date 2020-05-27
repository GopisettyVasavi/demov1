package com.renaissance.profile.parser.repository;

import com.renaissance.profile.parser.model.EmployeeEntity;

public interface EmployeeCustomRepository {
	EmployeeEntity getEmployeeDetails(String loggedInUserName, String loginPwd);

}
