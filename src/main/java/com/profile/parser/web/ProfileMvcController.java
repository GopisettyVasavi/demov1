package com.profile.parser.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.profile.parser.model.CandidatePersonalEntity;
import com.profile.parser.service.CandidateService;

@Controller
@RequestMapping("/profiles")
public class ProfileMvcController {
	
	@Autowired
	CandidateService service;
	
	@RequestMapping
	public String getAllProfiles(Model model) 
	
	{
		List<CandidatePersonalEntity> list= service.getAllCandidates();

		model.addAttribute("profiles", list);
		return "profile";
	}

}
