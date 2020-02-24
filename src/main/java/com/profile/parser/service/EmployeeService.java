package com.profile.parser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.parser.exception.RecordNotFoundException;
import com.profile.parser.model.EmployeesEntity;
import com.profile.parser.repository.EmployeesRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeesRepository repository;
	
	public List<EmployeesEntity> getAllEmployees()
	{
		List<EmployeesEntity> result = (List<EmployeesEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<EmployeesEntity>();
		}
	}
	
	public EmployeesEntity getEmployeeById(Long id) throws RecordNotFoundException 
	{
		Optional<EmployeesEntity> employee = repository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
	
	public EmployeesEntity createOrUpdateEmployee(EmployeesEntity entity) 
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<EmployeesEntity> employee = repository.findById(entity.getId());
			
			if(employee.isPresent()) 
			{
				EmployeesEntity newEntity = employee.get();
				newEntity.setEmail(entity.getEmail());
				newEntity.setFirstName(entity.getFirstName());
				newEntity.setLastName(entity.getLastName());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteEmployeeById(Long id) throws RecordNotFoundException 
	{
		Optional<EmployeesEntity> employee = repository.findById(id);
		
		if(employee.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	} 
}