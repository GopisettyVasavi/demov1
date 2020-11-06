package com.renaissance.requirement.dto;

import java.math.BigInteger;

public class MappingCandidateRqmtDTO {
	private BigInteger requirementId;
	private BigInteger candidateId;
	private Integer recruiterId;
	private String status;
	private String comments;
	private String authorisation;
	private String interestedInRole;
	private BigInteger id; 
	
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
	@Override
	public String toString() {
		return "MappingCandidateRqmtDTO [requirementId=" + requirementId + ", candidateId=" + candidateId
				+ ", recruiterId=" + recruiterId + ", status=" + status + ", comments=" + comments + ", authorisation="
				+ authorisation + ", interestedInRole=" + interestedInRole + ", id=" + id + "]";
	}
		
	

}
