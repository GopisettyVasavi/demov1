package com.renaissance.profile.parser.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"CANDIDATE_PROFILE\"")
public class CandidateProfileEntity {
	

	@Id
	@GeneratedValue(generator = "parse.candidate_personal_sequence")
    @SequenceGenerator(
            name = "parse.candidate_personal_sequence",
            sequenceName = "parse.candidate_personal_sequence", allocationSize = 1
            
    )
	@Column(name = "\"PROFILE_ID\"", unique = true)
	private Integer profileId;

	@Column(name = "\"CANDIDATE_ID\"")
	private BigInteger candidateId;

	@Column(name = "\"VERSION\"")
	private Integer version;

	@Column(name = "\"FILE_PATH\"")
	private String filePath;

	@Column(name = "\"AVAILABILITY\"")
	private String availability;

	@Column(name = "\"REFERENCES\"")
	private String references;

	@Column(name = "\"ADDITIONAL_NOTES\"")
	private String additionalNotes;

	@Column(name = "\"ASSIGNED_TO_EMPLOYEE_ID\"")
	private Integer assignedToEmployeeId;

	@Column(name = "\"ASSIGNED_TO_EMPLOYEE_NAME\"")
	private String assignedToEmployeeName;

	@Column(name = "\"EMPLOYED_BY_REN\"")
	private String employedByRen;

	@Column(name = "\"ASSIGNED_DATE\"")
	private LocalDate assignedDate;

	@Column(name = "\"PROFILE_TEXT\"")
	private String profileText;

	@Column(name = "\"SKILLS\"")
	private String skills;

	@Column(name = "\"CERTIFICATION\"")
	private String certification;

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
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

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
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

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	@Override
	public String toString() {
		return "CandidateProfileEntity [profileId=" + profileId + ", candidateId=" + candidateId + ", version="
				+ version + ", filePath=" + filePath + ", availability=" + availability + ", references=" + references
				+ ", additionalNotes=" + additionalNotes + ", assignedToEmployeeId=" + assignedToEmployeeId
				+ ", assignedToEmployeeName=" + assignedToEmployeeName + ", employedByRen=" + employedByRen
				+ ", assignedDate=" + assignedDate + ", profileText=" + profileText + ", skills=" + skills
				+ ", certification=" + certification + ", lastUpdatedByUser=" + lastUpdatedByUser
				+ ", lastUpdatedByDateTime=" + lastUpdatedByDateTime + "]";
	}

	
}
