package com.renaissance.contractor.model;

import java.math.BigInteger;

public class ContractorSearchForm {

	String contractorName;
	String clientName;
	String endClientName;
	String workLocationState;
	String role;
	BigInteger recruiterId;
	String recruiterName;
	String jobStartDate;
	String jobEndDate;
	String abnHolder;
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getEndClientName() {
		return endClientName;
	}
	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
	}
	public String getWorkLocationState() {
		return workLocationState;
	}
	public void setWorkLocationState(String workLocationState) {
		this.workLocationState = workLocationState;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public BigInteger getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(BigInteger recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	public String getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(String jobEndDate) {
		this.jobEndDate = jobEndDate;
	}
	
	public String getAbnHolder() {
		return abnHolder;
	}
	public void setAbnHolder(String abnHolder) {
		this.abnHolder = abnHolder;
	}
	@Override
	public String toString() {
		return "ContractorSearchForm [contractorName=" + contractorName + ", clientName=" + clientName
				+ ", endClientName=" + endClientName + ", workLocationState=" + workLocationState + ", role=" + role
				+ ", recruiterId=" + recruiterId + ", recruiterName=" + recruiterName + ", abnHolder=" + abnHolder+ ", jobStartDate=" + jobStartDate
				+ ", jobEndDate=" + jobEndDate + "]";
	}
	
	
}
