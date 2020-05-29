package com.renaissance.profile.parser.model;

import java.util.ArrayList;
import java.util.List;

import com.renaissance.profile.parser.dto.CandidateDTO;

public class BulkUploadResponse {
	List<CandidateDTO> successProfiles= new ArrayList<CandidateDTO>();
	List<CandidateDTO> errorProfiles= new ArrayList<CandidateDTO>();
	//List<CandidateDTO> profiles= new ArrayList<CandidateDTO>();
	public List<CandidateDTO> getSuccessProfiles() {
		return successProfiles;
	}
	public void setSuccessProfiles(List<CandidateDTO> successProfiles) {
		this.successProfiles = successProfiles;
	}
	public List<CandidateDTO> getErrorProfiles() {
		return errorProfiles;
	}
	public void setErrorProfiles(List<CandidateDTO> errorProfiles) {
		this.errorProfiles = errorProfiles;
	}
	
	/*
	 * public List<CandidateDTO> getProfiles() { return profiles; } public void
	 * setProfiles(List<CandidateDTO> profiles) { this.profiles = profiles; }
	 */
	
	
}
