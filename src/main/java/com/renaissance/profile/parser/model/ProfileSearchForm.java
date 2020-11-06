package com.renaissance.profile.parser.model;

import java.math.BigInteger;

/**
 * This form class will be mapped to the profile search screen and have fields for all the search criteria.
 * @author Vasavi
 *
 */
public class ProfileSearchForm {
	
	private String candidateName;//personal tab-1
	private String primaryEmail;//personal tab-2
	private String primaryContactNo;//personal tab-3
	private String skill;//profile tab-1
	private String currentLocation;//personal table-4
	private String availability;//profile table-2
	private String visaType;//personal tab-5
	private String  workExperience;// personal tab-6
	private String certification;//profile table-3
	private BigInteger requirementId;
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
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
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	
	public BigInteger getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(BigInteger requirementId) {
		this.requirementId = requirementId;
	}
	@Override
	public String toString() {
		return "ProfileSearchForm [candidateName=" + candidateName + ", primaryEmail=" + primaryEmail
				+ ", primaryContactNo=" + primaryContactNo + ", skill=" + skill + ", currentLocation=" + currentLocation
				+ ", availability=" + availability + ", visaType=" + visaType + ", workExperience=" + workExperience
				+ ", certification=" + certification + ", requirementId=" + requirementId + "]";
	}
	
	
	
	
	
	

}
