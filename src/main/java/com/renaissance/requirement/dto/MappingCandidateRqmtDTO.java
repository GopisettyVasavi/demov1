package com.renaissance.requirement.dto;

import java.math.BigInteger;
import java.time.LocalDate;

public class MappingCandidateRqmtDTO {
	private BigInteger requirementId;
	private BigInteger candidateId;
	private Integer recruiterId;
	private String status;
	private String comments;
	private String authorisation;
	private String interestedInRole;
	private BigInteger id; 
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String expectedSalary;
	private String totalExp;
	private String relevantExp;
	private String availabilityToJoin;
	private String relocationInFuture;
	private String appliedBefore;
	private String authoriseRenaissance;
	private String candidateName;
	private String location;
	private String contactNumber;
	private String email;
	private String visaStatus;
	private String visaNo;
	

	
	public BigInteger getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(BigInteger requirementId) {
		this.requirementId = requirementId;
	}
	public BigInteger getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(BigInteger candidateId) {
		this.candidateId = candidateId;
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
	public String getInterestedInRole() {
		return interestedInRole;
	}
	public void setInterestedInRole(String interestedInRole) {
		this.interestedInRole = interestedInRole;
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVisaStatus() {
		return visaStatus;
	}
	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}
	public String getVisaNo() {
		return visaNo;
	}
	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}
	@Override
	public String toString() {
		return "MappingCandidateRqmtDTO [requirementId=" + requirementId + ", candidateId=" + candidateId
				+ ", recruiterId=" + recruiterId + ", status=" + status + ", comments=" + comments + ", authorisation="
				+ authorisation + ", interestedInRole=" + interestedInRole + ", id=" + id + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", expectedSalary=" + expectedSalary + ", totalExp="
				+ totalExp + ", relevantExp=" + relevantExp + ", availabilityToJoin=" + availabilityToJoin
				+ ", relocationInFuture=" + relocationInFuture + ", appliedBefore=" + appliedBefore
				+ ", authoriseRenaissance=" + authoriseRenaissance + ", candidateName=" + candidateName + ", location="
				+ location + ", contactNumber=" + contactNumber + ", email=" + email + ", visaStatus=" + visaStatus
				+ ", visaNo=" + visaNo + "]";
	}
	
}
