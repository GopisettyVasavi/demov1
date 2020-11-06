package com.renaissance.requirement.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"MAPPING_REQUIREMENT_CANDIDATE\"")
public class MappingRequirementCandidateEntity {
	@Id
	@GeneratedValue(generator = "parse.job_requirement_sequence")
	@SequenceGenerator(name = "parse.job_requirement_sequence", sequenceName = "parse.job_requirement_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger id;

	@Column(name = "\"REQUIREMENT_ID\"")
	private BigInteger requirementId;
	
	@Column(name = "\"CANDIDATE_ID\"")
	private BigInteger candidateId;
	
	@Column(name = "\"RECRUITER_ID\"")
	private Integer recruiterId;
	
	@Column(name = "\"STATUS\"")
	private String status;
	
	@Column(name = "\"COMMENTS\"")
	private String comments;
	
	@Column(name = "\"AUTHORISATION\"")
	private String authorisation;
	
	@Column(name = "\"INTERESTED_IN_ROLE\"")
	private String interestedInRole;
	
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

	@Override
	public String toString() {
		return "MappingRequirementCandidateEntity [id=" + id + ", requirementId=" + requirementId + ", candidateId="
				+ candidateId + ", recruiterId=" + recruiterId + ", status=" + status + ", comments=" + comments
				+ ", authorisation=" + authorisation + ", interestedInRole=" + interestedInRole + "]";
	}

	

	
	
	
}
