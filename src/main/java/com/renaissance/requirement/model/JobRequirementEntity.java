package com.renaissance.requirement.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"JOB_REQUIREMENT\"")
public class JobRequirementEntity {
	@Id
	@GeneratedValue(generator = "parse.job_requirement_sequence")
	@SequenceGenerator(name = "parse.job_requirement_sequence", sequenceName = "parse.job_requirement_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger id;

	@Column(name = "\"REQUIREMENT_ID\"")
	private String requirementId;

	@Column(name = "\"VENDOR_NAME\"")
	private String vendorName;

	@Column(name = "\"JOB_TITLE\"")
	private String jobTitle;

	@Column(name = "\"JOB_TYPE\"")
	private String jobType;

	@Column(name = "\"CONTRACT_START_DATE\"")
	private LocalDate contractStartDate;

	@Column(name = "\"CONTRACT_END_DATE\"")
	private LocalDate contractEndDate;

	@Column(name = "\"SALARY\"")
	private String salary;

	@Column(name = "\"LOCATION\"")
	private String location;

	@Column(name = "\"DATE_POSTED\"")
	private LocalDate datePosted;

	@Column(name = "\"JOB_DESCRIPTION\"")
	private String jobDescription;

	@Column(name = "\"CONFIDENTIAL_INFORMATION\"")
	private String confidentialInformation;

	@Column(name = "\"CONTACT_NAME\"")
	private String contactPersonName;

	@Column(name = "\"CONTACT_EMAIL\"")
	private String contactPersonEmail;

	@Column(name = "\"CONTACT_PHONE\"")
	private String contactPersonPhone;

	@Column(name = "\"ASSIGNED_RECRUITER\"")
	private String assignedRecruiter;
	
	@Column(name = "\"STATUS\"")
	private String status;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public LocalDate getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(LocalDate contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public LocalDate getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(LocalDate contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getConfidentialInformation() {
		return confidentialInformation;
	}

	public void setConfidentialInformation(String confidentialInformation) {
		this.confidentialInformation = confidentialInformation;
	}

	public String getAssignedRecruiter() {
		return assignedRecruiter;
	}

	public void setAssignedRecruiter(String assignedRecruiter) {
		this.assignedRecruiter = assignedRecruiter;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "JobRequirementEntity [Id=" + id + ", requirementId=" + requirementId + ", vendorName=" + vendorName
				+ ", jobTitle=" + jobTitle + ", jobType=" + jobType + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ", salary=" + salary + ", location=" + location
				+ ", datePosted=" + datePosted + ", jobDescription=" + jobDescription + ", confidentialInformation="
				+ confidentialInformation + ", contactPersonName=" + contactPersonName + ", contactPersonEmail="
				+ contactPersonEmail + ", contactPersonPhone=" + contactPersonPhone + ", assignedRecruiter="
				+ assignedRecruiter + ", status=" + status + "]";
	}

	

}
