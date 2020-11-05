package com.renaissance.requirement.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RequirementDTO {
	private String vendorName;
	private String requirementId;
	private String jobTitle;
	private String jobType;
	private String jobDescription;
	private String location;
	private String salary;
	private String datePosted;
	private String confidentialInformation;
	private String status;
	private String contactPersonName;
	private String contactPersonEmail;
	private String contactPersonPhone;
	private String assignedRecruiter;
	private String contractStartDate;
	private String contractEndDate;
	private List<Integer>recruiters =new ArrayList<Integer>();
	private Integer recruiterId;
	private String recruiterName;
	private BigInteger id;
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
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
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}
	public String getConfidentialInformation() {
		return confidentialInformation;
	}
	public void setConfidentialInformation(String confidentialInformation) {
		this.confidentialInformation = confidentialInformation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getAssignedRecruiter() {
		return assignedRecruiter;
	}
	public void setAssignedRecruiter(String assignedRecruiter) {
		this.assignedRecruiter = assignedRecruiter;
	}
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	public List<Integer> getRecruiters() {
		return recruiters;
	}
	public void setRecruiters(List<Integer> recruiters) {
		this.recruiters = recruiters;
	}
	
	public Integer getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(Integer recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "RequirementDTO [vendorName=" + vendorName + ", requirementId=" + requirementId + ", jobTitle="
				+ jobTitle + ", jobType=" + jobType + ", jobDescription=" + jobDescription + ", location=" + location
				+ ", salary=" + salary + ", datePosted=" + datePosted + ", confidentialInformation="
				+ confidentialInformation + ", status=" + status + ", contactPersonName=" + contactPersonName
				+ ", contactPersonEmail=" + contactPersonEmail + ", contactPersonPhone=" + contactPersonPhone
				+ ", assignedRecruiter=" + assignedRecruiter + ", contractStartDate=" + contractStartDate
				+ ", contractEndDate=" + contractEndDate + ", recruiters=" + recruiters + ", recruiterId=" + recruiterId
				+ ", recruiterName=" + recruiterName + ", id=" + id + "]";
	}
	
	
	

}
