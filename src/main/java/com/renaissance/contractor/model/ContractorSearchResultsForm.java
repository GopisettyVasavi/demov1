package com.renaissance.contractor.model;

import java.math.BigInteger;

public class ContractorSearchResultsForm {
	BigInteger contractorId;
	String fullName;
	String mobilePhone;
	String personalEmail;
	String clientName;
	String endClientName;
	String workLocationState;
	String jobRole;
	BigInteger recruiterId;
	String recruiterName;
	String jobStartDate;
	String jobEndDate;
	String abnHolder;
	Double ratePerDay;
	Double billRatePerDay;
	
	public BigInteger getContractorId() {
		return contractorId;
	}
	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
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
	
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
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
	public Double getRatePerDay() {
		return ratePerDay;
	}
	public void setRatePerDay(Double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}
	public Double getBillRatePerDay() {
		return billRatePerDay;
	}
	public void setBillRatePerDay(Double billRatePerDay) {
		this.billRatePerDay = billRatePerDay;
	}
	@Override
	public String toString() {
		return "ContractorSearchResultsForm [fullName=" + fullName + ", contractorId=" + contractorId+ ", mobilePhone=" + mobilePhone + ", personalEmail="
				+ personalEmail + ", clientName=" + clientName + ", endClientName=" + endClientName
				+ ", workLocationState=" + workLocationState + ", jobRole=" + jobRole + ", recruiterId=" + recruiterId
				+ ", recruiterName=" + recruiterName + ", jobStartDate=" + jobStartDate + ", jobEndDate=" + jobEndDate
				+ ", abnHolder=" + abnHolder + ", ratePerDay=" + ratePerDay + ", billRatePerDay=" + billRatePerDay
				+ "]";
	}
	

}
