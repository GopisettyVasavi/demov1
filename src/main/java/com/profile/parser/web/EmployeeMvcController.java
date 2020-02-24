package com.profile.parser.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.profile.parser.exception.RecordNotFoundException;
import com.profile.parser.model.EmployeesEntity;
import com.profile.parser.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeMvcController 
{
	@Autowired
	EmployeeService service;

	@RequestMapping
	public String getAllEmployees(Model model) 
	{
		List<EmployeesEntity> list = service.getAllEmployees();

		model.addAttribute("employees", list);
		return "list-employees";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			EmployeesEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeesEntity());
		}
		return "add-edit-employee";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(EmployeesEntity employee) 
	{
		service.createOrUpdateEmployee(employee);
		return "redirect:/";
	}
}
