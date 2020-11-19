package com.renaissance.requirement.dto;

import java.util.ArrayList;
import java.util.List;

import com.renaissance.profile.parser.model.ProfileSearchForm;

public class MappingRequirement {

	private List<MappingCandidateRqmtDTO> mappingCandidateRqmtList= new ArrayList<MappingCandidateRqmtDTO>();
	private ProfileSearchForm searchForm= new ProfileSearchForm();
	//private BigInteger requirementId;//Primary key of Requirement
	
	public List<MappingCandidateRqmtDTO> getMappingCandidateRqmtList() {
		return mappingCandidateRqmtList;
	}
	public void setMappingCandidateRqmtList(List<MappingCandidateRqmtDTO> mappingCandidateRqmtList) {
		this.mappingCandidateRqmtList = mappingCandidateRqmtList;
	}
	public ProfileSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(ProfileSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	
}
