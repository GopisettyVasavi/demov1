package com.profile.parser.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"CANDIDATE_EDUCATION\"")
public class CandidateEducationEntity {

	@Id
	@GeneratedValue(generator = "parse.candidate_education_sequence")
	@SequenceGenerator(name = "parse.candidate_education_sequence", sequenceName = "parse.candidate_education_sequence", allocationSize = 1

	)
	@Column(name = "\"EDUCATION_ID\"", unique = true)
	private Integer educationId;

	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	@Column(name = "\"CANDIDATE_ID\"")
	private BigInteger candidateId;

	@Column(name = "\"EDUCATION\"")
	private String education;

	@Column(name = "\"AWARDS\"")
	private String awards;

	@Column(name = "\"LAST_UPDATED_BY_USER\"")
	private String lastUpdatedByUser;

	@Column(name = "\"LAST_UPDATED_DATE_TIME\"")
	private LocalDateTime lastUpdatedByDateTime;

	public BigInteger getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(BigInteger candidateId) {
		this.candidateId = candidateId;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getLastUpdatedByUser() {
		return lastUpdatedByUser;
	}

	public void setLastUpdatedByUser(String lastUpdatedByUser) {
		this.lastUpdatedByUser = lastUpdatedByUser;
	}

	public LocalDateTime getLastUpdatedByDateTime() {
		return lastUpdatedByDateTime;
	}

	public void setLastUpdatedByDateTime(LocalDateTime lastUpdatedByDateTime) {
		this.lastUpdatedByDateTime = lastUpdatedByDateTime;
	}

	@Override
	public String toString() {
		return "CandidateEducationEntity [educationId=" + educationId + ", candidateId=" + candidateId + ", education="
				+ education + ", awards=" + awards + ", lastUpdatedByUser=" + lastUpdatedByUser
				+ ", lastUpdatedByDateTime=" + lastUpdatedByDateTime + "]";
	}

}
