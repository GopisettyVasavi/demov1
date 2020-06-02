package com.renaissance.profile.parser.repository;

import java.util.List;

import com.renaissance.profile.parser.model.EmployeeEntity;

public interface EmployeeCustomRepository {
	EmployeeEntity getEmployeeDetails(String loggedInUserName, String loginPwd);
	List<EmployeeEntity> getRecruitersList();

}
