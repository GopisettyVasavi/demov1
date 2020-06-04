package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorEmploymentDetailsDTO {
	private BigInteger contractorId;
	private String clientName;
	private String endClientName;
	private String contractNumber;
	private String workLocationAddress;
	private String workLocationCity;;
	private String workLocationState;
	private BigInteger workLocationZipCode;
	private String workLocationCountry;
	private String wlOtherCountry;
	private String wlOtherState;
	private String jobRole;
	private String employmentType;
	private String jobStartDate;;
	private String jobEndDate;
	private String lastWorkingDate;
	private String finishedClient;
	private String additionalInfo;
	private String activeRecord;
	private BigInteger recruiterId;
	private String recruiterName;
	public BigInteger getContractorId() {
		return contractorId;
	}
	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
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
	public String getWlOtherCountry() {
		return wlOtherCountry;
	}
	public void setWlOtherCountry(String wlOtherCountry) {
		this.wlOtherCountry = wlOtherCountry;
	}
	public String getWlOtherState() {
		return wlOtherState;
	}
	public void setWlOtherState(String wlOtherState) {
		this.wlOtherState = wlOtherState;
	}
	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getWorkLocationAddress() {
		return workLocationAddress;
	}
	public void setWorkLocationAddress(String workLocationAddress) {
		this.workLocationAddress = workLocationAddress;
	}
	public String getWorkLocationCity() {
		return workLocationCity;
	}
	public void setWorkLocationCity(String workLocationCity) {
		this.workLocationCity = workLocationCity;
	}
	public String getWorkLocationState() {
		return workLocationState;
	}
	public void setWorkLocationState(String workLocationState) {
		this.workLocationState = workLocationState;
	}
	public BigInteger getWorkLocationZipCode() {
		return workLocationZipCode;
	}
	public void setWorkLocationZipCode(BigInteger workLocationZipCode) {
		this.workLocationZipCode = workLocationZipCode;
	}
	public String getWorkLocationCountry() {
		return workLocationCountry;
	}
	public void setWorkLocationCountry(String workLocationCountry) {
		this.workLocationCountry = workLocationCountry;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
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
	public String getLastWorkingDate() {
		return lastWorkingDate;
	}
	public void setLastWorkingDate(String lastWorkingDate) {
		this.lastWorkingDate = lastWorkingDate;
	}
	public String getFinishedClient() {
		return finishedClient;
	}
	public void setFinishedClient(String finishedClient) {
		this.finishedClient = finishedClient;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getActiveRecord() {
		return activeRecord;
	}
	public void setActiveRecord(String activeRecord) {
		this.activeRecord = activeRecord;
	}
	
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
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
	@Override
	public String toString() {
		return "ContractorEmploymentDetailsDTO [contractorId=" + contractorId + ", clientName=" + clientName
				+ ", endClientName=" + endClientName + ", contractNumber=" + contractNumber + ", workLocationAddress="
				+ workLocationAddress + ", workLocationCity=" + workLocationCity + ", workLocationState="
				+ workLocationState + ", workLocationZipCode=" + workLocationZipCode + ", workLocationCountry="
				+ workLocationCountry + ", wlOtherCountry=" + wlOtherCountry + ", wlOtherState=" + wlOtherState
				+ ", jobRole=" + jobRole + ", employmentType=" + employmentType + ", jobStartDate=" + jobStartDate
				+ ", jobEndDate=" + jobEndDate + ", lastWorkingDate=" + lastWorkingDate + ", finishedClient="
				+ finishedClient + ", additionalInfo=" + additionalInfo + ", activeRecord=" + activeRecord
				+ ", recruiterId=" + recruiterId + ", recruiterName=" + recruiterName + "]";
	}
	
	
	
	
}
