package com.renaissance.profile.parser.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.profile.parser.model.EmployeeEntity;
import com.renaissance.profile.parser.model.EmployeeForm;
import com.renaissance.profile.parser.model.User;
import com.renaissance.profile.parser.repository.EmployeeRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class EmployeeService {
	private static final Logger logger=LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * This method will take the input provided user and register new Employee.
	 * @param employeeForm
	 * @return
	 * @throws Exception
	 */
	public EmployeeForm registerEmployee(EmployeeForm employeeForm) throws Exception {
		EmployeeEntity employeeEntity= new EmployeeEntity();
		BeanUtils.copyProperties(employeeForm, employeeEntity);
		try {
			logger.info("Employee details Save,{}",employeeForm.toString());
			LocalDateTime localDate = LocalDateTime.now();
			employeeEntity.setLastUpdatedByDateTime(localDate);
			if(!ProfileParserUtils.isObjectEmpty(employeeForm.getJoiningDate()))
			employeeEntity.setJoiningDate(ProfileParserUtils.parseStringDate(employeeForm.getJoiningDate()));
			if(!ProfileParserUtils.isObjectEmpty(employeeForm.getVisaValidDate()))
				employeeEntity.setVisaValidDate(ProfileParserUtils.parseStringDate(employeeForm.getVisaValidDate()));
		employeeEntity=employeeRepository.save(employeeEntity);
		BeanUtils.copyProperties(employeeEntity,employeeForm);
		if(!ProfileParserUtils.isObjectEmpty(employeeEntity.getJoiningDate()))
			employeeForm.setJoiningDate(ProfileParserUtils.parseDateToString(employeeEntity.getJoiningDate()));
		if(!ProfileParserUtils.isObjectEmpty(employeeEntity.getVisaValidDate()))
			employeeForm.setJoiningDate(ProfileParserUtils.parseDateToString(employeeEntity.getVisaValidDate()));
		}catch(Exception e) {
			logger.error("Error in creating Employee", new Exception(e));
			throw new Exception(e.getMessage());
		}
		//logger.info("After save employee details...{}",employeeEntity.toString());
		return employeeForm;
	}

	public User getRecruiterDetails(Integer employeeId) {
		User recruiter=new User();
		Optional<EmployeeEntity> employee=employeeRepository.findById(employeeId);
		if(!ProfileParserUtils.isObjectEmpty(employee)) {
		EmployeeEntity emp=employee.get();
		recruiter.setEmail(emp.getEmail());
		recruiter.setEmployeeName(emp.getEmployeeName());
		}
		return recruiter;
	}
}
