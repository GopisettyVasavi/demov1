package com.renaissance.profile.parser.dto;

import java.math.BigInteger;
import java.time.LocalDate;

public class CandidateDTO {
	private static final long serialVersionUID = -4500094586165758427L;
	private BigInteger candidateId;
	private String candidateName;
	private String primaryEmail;
	private String secondaryEmail;
	private String primaryPhone;
	private String secondaryPhone;
	private String visaType;
	private String visaNo;

	private String validUpto;
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

	private String assignedDate;
	private String profileText;
	private String skills;
	private String organization;
	private String designation;

	private String workStartDate;

	private String workEndDate;
	private String lastUpdatedByUser;
	private String lastName;
	private String middleName;
	private String firstName;
	private String fileName;

	private String interestedInRole;
	private String comments;
	private String authorisation;
	private BigInteger id;

	private BigInteger requirementId;

	private Integer recruiterId;

	private String status;
	private Integer sno;
	private String disableRow;
	private LocalDate createdDate;
	private String disableStatus;
	private String expectedSalary;
	private String totalExp;
	private String relevantExp;
	private String availabilityToJoin;
	private String relocationInFuture;
	private String appliedBefore;
	private String authoriseRenaissance;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

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

	public String getValidUpto() {
		return validUpto;
	}

	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}

	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}

	public String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}

	public String getInterestedInRole() {
		return interestedInRole;
	}

	public void setInterestedInRole(String interestedInRole) {
		this.interestedInRole = interestedInRole;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAuthorisation() {
		return authorisation;
	}

	public void setAuthorisation(String authorisation) {
		this.authorisation = authorisation;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(BigInteger requirementId) {
		this.requirementId = requirementId;
	}

	public Integer getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Integer recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getDisableRow() {
		return disableRow;
	}

	public void setDisableRow(String disableRow) {
		this.disableRow = disableRow;
	}

	
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getDisableStatus() {
		return disableStatus;
	}

	public void setDisableStatus(String disableStatus) {
		this.disableStatus = disableStatus;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}

	public String getRelevantExp() {
		return relevantExp;
	}

	public void setRelevantExp(String relevantExp) {
		this.relevantExp = relevantExp;
	}

	public String getAvailabilityToJoin() {
		return availabilityToJoin;
	}

	public void setAvailabilityToJoin(String availabilityToJoin) {
		this.availabilityToJoin = availabilityToJoin;
	}

	public String getRelocationInFuture() {
		return relocationInFuture;
	}

	public void setRelocationInFuture(String relocationInFuture) {
		this.relocationInFuture = relocationInFuture;
	}

	public String getAppliedBefore() {
		return appliedBefore;
	}

	public void setAppliedBefore(String appliedBefore) {
		this.appliedBefore = appliedBefore;
	}

	public String getAuthoriseRenaissance() {
		return authoriseRenaissance;
	}

	public void setAuthoriseRenaissance(String authoriseRenaissance) {
		this.authoriseRenaissance = authoriseRenaissance;
	}

	@Override
	public String toString() {
		return "CandidateDTO [candidateId=" + candidateId + ", candidateName=" + candidateName + ", primaryEmail="
				+ primaryEmail + ", secondaryEmail=" + secondaryEmail + ", primaryPhone=" + primaryPhone
				+ ", secondaryPhone=" + secondaryPhone + ", visaType=" + visaType + ", visaNo=" + visaNo
				+ ", validUpto=" + validUpto + ", availability=" + availability + ", education=" + education
				+ ", certification=" + certification + ", summary=" + summary + ", currentLocation=" + currentLocation
				+ ", workExperience=" + workExperience + ", reference=" + reference + ", title=" + title + ", awards="
				+ awards + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", socialMediaLink="
				+ socialMediaLink + ", nationality=" + nationality + ", version=" + version + ", filePath=" + filePath
				+ ", additionalNotes=" + additionalNotes + ", assignedToEmployeeId=" + assignedToEmployeeId
				+ ", assignedToEmployeeName=" + assignedToEmployeeName + ", employedByRen=" + employedByRen
				+ ", assignedDate=" + assignedDate + ", profileText=" + profileText + ", skills=" + skills
				+ ", organization=" + organization + ", designation=" + designation + ", workStartDate=" + workStartDate
				+ ", workEndDate=" + workEndDate + ", lastUpdatedByUser=" + lastUpdatedByUser + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", firstName=" + firstName + ", fileName=" + fileName
				+ ", interestedInRole=" + interestedInRole + ", comments=" + comments + ", authorisation="
				+ authorisation + ", id=" + id + ", requirementId=" + requirementId + ", recruiterId=" + recruiterId
				+ ", status=" + status + ", sno=" + sno + ", disableRow=" + disableRow + ", createdDate=" + createdDate
				+ ", disableStatus=" + disableStatus + ", expectedSalary=" + expectedSalary + ", totalExp=" + totalExp
				+ ", relevantExp=" + relevantExp + ", availabilityToJoin=" + availabilityToJoin
				+ ", relocationInFuture=" + relocationInFuture + ", appliedBefore=" + appliedBefore
				+ ", authoriseRenaissance=" + authoriseRenaissance + "]";
	}

	
}
