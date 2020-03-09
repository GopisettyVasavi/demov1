package com.profile.parser.model;

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
@Table(name = "parse.\"CANDIDATE_PERSONAL\"")
public class CandidatePersonalEntity {
	@Id
	@GeneratedValue(generator = "parse.candidate_personal_sequence")
    @SequenceGenerator(
            name = "parse.candidate_personal_sequence",
            sequenceName = "parse.candidate_personal_sequence", allocationSize = 1
            
    )
	@Column(name = "\"CANDIDATE_ID\"", unique = true)
	private BigInteger candidateId;

	@Column(name = "\"CANDIDATE_NAME\"")
	private String candidateName;

	@Column(name = "\"PRIMARY_EMAIL\"")
	private String primaryEmail;

	@Column(name = "\"SECONDARY_EMAIL\"")
	private String secondaryEmail;

	@Column(name = "\"PRIMARY_PHONE\"")
	private String primaryPhone;

	@Column(name = "\"SECONDARY_PHONE\"")
	private String secondaryPhone;

	@Column(name = "\"TITLE\"")
	private String title;

	@Column(name = "\"GENDER\"")
	private String gender;

	@Column(name = "\"CURRENT_LOCATION\"")
	private String currentLocation;

	@Column(name = "\"DATE_OF_BIRTH\"")
	private String dateOfBirth;

	@Column(name = "\"SOCIAL_MEDIA_LINK\"")
	private String socialMediaLink;

	@Column(name = "\"NATIONALITY\"")
	private String nationality;

	@Column(name = "\"VISA_TYPE\"")
	private String visaType;
	
	@Column(name = "\"VISA_NO\"")
	private String visaNo;
	
	@Column(name = "\"VALID_UPTO\"")
	private LocalDate validUpto;

	@Column(name = "\"WORK_EXPERIENCE\"")
	private String workExperience;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
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

	

	public String getWorkExperrience() {
		return workExperience;
	}

	public void setWorkExperrience(String workExperrience) {
		this.workExperience = workExperrience;
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
		return "CandidatePersonalEntity [candidateId=" + candidateId + ", candidateName=" + candidateName
				+ ", primaryEmail=" + primaryEmail + ", secondaryEmail=" + secondaryEmail + ", primaryPhone="
				+ primaryPhone + ", secondaryPhone=" + secondaryPhone + ", title=" + title + ", gender=" + gender
				+ ", current_location=" + currentLocation + ", dateOfBirth=" + dateOfBirth + ", socialMediaLink="
				+ socialMediaLink + ", nationality=" + nationality + ", visaType=" + visaType + ", visaNo=" + visaNo
				+ ", validupto=" + validUpto + ", workExperrience=" + workExperience + ", lastUpdatedByUser="
				+ lastUpdatedByUser + ", lastUpdatedByDateTime=" + lastUpdatedByDateTime + "]";
	}


}
