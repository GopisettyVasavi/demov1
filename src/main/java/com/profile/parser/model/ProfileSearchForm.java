package com.profile.parser.model;

public class ProfileSearchForm {
	
	String candidateName;
	String primaryEmail;
	String primaryContactNo;
	String skill;
	String currentLocation;
	String availability;
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getPrimaryContactNo() {
		return primaryContactNo;
	}
	public void setPrimaryContactNo(String primaryContactNo) {
		this.primaryContactNo = primaryContactNo;
	}
	@Override
	public String toString() {
		return "ProfileSearchForm [candidateName=" + candidateName + ", primaryEmail=" + primaryEmail
				+ ", primaryContactNo=" + primaryContactNo + ", skill=" + skill + ", currentLocation=" + currentLocation
				+ ", availability=" + availability + "]";
	}
	
	
	

}
