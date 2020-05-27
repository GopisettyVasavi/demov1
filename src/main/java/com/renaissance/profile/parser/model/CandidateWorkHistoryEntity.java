package com.renaissance.profile.parser.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"CANDIDATE_WORK_HISTORY\"")
public class CandidateWorkHistoryEntity {
	

	@Id
	@GeneratedValue(generator = "parse.candidate_work_sequence")
    @SequenceGenerator(
            name = "parse.candidate_work_sequence",
            sequenceName = "parse.candidate_work_sequence", allocationSize =  1
            
    )
	@Column(name = "\"WORK_ID\"" , unique = true)
	private Integer workId;
	
	@Column(name = "\"CANDIDATE_ID\"")
	private BigInteger candidateId;
	
	@Column(name = "\"ORGANIZATION\"")
	private String organization;

	@Column(name = "\"DESIGNATION\"")
	private String designation;

	@Column(name = "\"WORK_START_DATE\"")
	private LocalDate workStartDate;

	@Column(name = "\"WORK_END_DATE\"")
	private LocalDate workEndDate;
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

	public LocalDate getWorkStartDate() {
		return workStartDate;
	}

	public void setWorkStartDate(LocalDate workStartDate) {
		this.workStartDate = workStartDate;
	}

	public LocalDate getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(LocalDate workEndDate) {
		this.workEndDate = workEndDate;
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

	public Integer getWorkId() {
		return workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	@Override
	public String toString() {
		return "CandidateWorkHistoryEntity [workId=" + workId + ", candidateId=" + candidateId + ", organization="
				+ organization + ", designation=" + designation + ", workStartDate=" + workStartDate + ", workEndDate="
				+ workEndDate + ", lastUpdatedByUser=" + lastUpdatedByUser + ", lastUpdatedByDateTime="
				+ lastUpdatedByDateTime + "]";
	}

	
	
	

}
