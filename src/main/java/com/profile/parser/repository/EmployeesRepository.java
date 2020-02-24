package com.profile.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.profile.parser.model.EmployeesEntity;

@Repository
public interface EmployeesRepository 
			extends CrudRepository<EmployeesEntity, Long> {

}
