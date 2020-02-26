package com.profile.parser.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CandidateDTO {

	private BigInteger candidateId;
	private String candidateName;
	private String primaryEmail;
	private String secondaryEmail;
	private String primaryPhone;
	private String secondaryPhone;
	private String visaType;
	private String visaNo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate validUpto;
	private String availability;
	private String education;
	private String certification;
	private String summary;
	private String currentLocation;
	private String workExperience;
	private String reference;
	private String title;
	private String awards;
	private String gender;
	private String dateOfBirth;
	private String socialMediaLink;
	private String nationality;
	private String version;
	private String filePath;
	private String additionalNotes;
	private Integer assignedToEmployeeId;
	private String assignedToEmployeeName;
	private String employedByRen;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate assignedDate;
	private String profileText;
	private String skills;
	private String organization;
	private String designation;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date workStartDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date workEndDate;
	private String lastUpdatedByUser;

	public BigInteger getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(BigInteger candidateId) {
		this.candidateId = candidateId;
	}

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

	public String getSecondaryEmail() {
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}


	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSocialMediaLink() {
		return socialMediaLink;
	}

	public void setSocialMediaLink(String socialMediaLink) {
		this.socialMediaLink = socialMediaLink;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public Integer getAssignedToEmployeeId() {
		return assignedToEmployeeId;
	}

	public void setAssignedToEmployeeId(Integer assignedToEmployeeId) {
		this.assignedToEmployeeId = assignedToEmployeeId;
	}

	public String getAssignedToEmployeeName() {
		return assignedToEmployeeName;
	}

	public void setAssignedToEmployeeName(String assignedToEmployeeName) {
		this.assignedToEmployeeName = assignedToEmployeeName;
	}

	public String getEmployedByRen() {
		return employedByRen;
	}

	public void setEmployedByRen(String employedByRen) {
		this.employedByRen = employedByRen;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getProfileText() {
		return profileText;
	}

	public void setProfileText(String profileText) {
		this.profileText = profileText;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}

	public Date getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}

	public String getLastUpdatedByUser() {
		return lastUpdatedByUser;
	}

	public void setLastUpdatedByUser(String lastUpdatedByUser) {
		this.lastUpdatedByUser = lastUpdatedByUser;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	

	public LocalDate getValidUpto() {
		return validUpto;
	}

	public void setValidUpto(LocalDate validUpto) {
		this.validUpto = validUpto;
	}

	@Override
	public String toString() {
		return "CandidateDTO [candidateId=" + candidateId + ", candidateName=" + candidateName + ", primaryEmail="
				+ primaryEmail + ", secondaryEmail=" + secondaryEmail + ", primaryPhone=" + primaryPhone
				+ ", secondaryPhone=" + secondaryPhone + ", visaType=" + visaType + ", visaNo=" + visaNo
				+ ", validupto=" + validUpto + ", availability=" + availability + ", education=" + education
				+ ", certification=" + certification + ", summary=" + summary + ", currentLocation=" + currentLocation
				+ ", workExperience=" + workExperience + ", reference=" + reference + ", title=" + title + ", awards="
				+ awards + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", socialMediaLink="
				+ socialMediaLink + ", nationality=" + nationality + ", version=" + version + ", filePath=" + filePath
				+ ", additionalNotes=" + additionalNotes + ", assignedToEmployeeId=" + assignedToEmployeeId
				+ ", assignedToEmployeeName=" + assignedToEmployeeName + ", employedByRen=" + employedByRen
				+ ", assignedDate=" + assignedDate + ", profileText=" + profileText + ", skills=" + skills
				+ ", organization=" + organization + ", designation=" + designation + ", workStartDate=" + workStartDate
				+ ", workEndDate=" + workEndDate + ", lastUpdatedByUser=" + lastUpdatedByUser + "]";
	}


}
