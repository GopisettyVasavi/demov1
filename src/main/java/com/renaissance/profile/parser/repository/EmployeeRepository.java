package com.renaissance.profile.parser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renaissance.profile.parser.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer>, EmployeeCustomRepository {

}
