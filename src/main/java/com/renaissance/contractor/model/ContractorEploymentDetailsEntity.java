package com.renaissance.contractor.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"CONTRACTOR_EMPLOYMENT_DETAILS\"")
public class ContractorEploymentDetailsEntity {
	
	@Id
	@GeneratedValue(generator = "parse.contractor_employment_sequence")
	@SequenceGenerator(name = "parse.contractor_employment_sequence", sequenceName = "parse.contractor_employment_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"CLIENT_NAME\"")
	private String clientName;
	
	@Column(name = "\"END_CLIENT_NAME\"")
	private String endClientName;
	
	@Column(name = "\"CONTRACT_NUMBER\"")
	private String contractNumber;
	
	@Column(name = "\"WORK_LOCATION_ADDRESS\"")
	private String workLocationAddress;
	
	@Column(name = "\"WORK_LOCATION_CITY\"")
	private String workLocationCity;
	
	@Column(name = "\"WORK_LOCATION_STATE\"")
	private String workLocationState;
	
	@Column(name = "\"WORK_LOCATION_ZIP_CODE\"")
	private BigInteger workLocationZipCode;
	
	@Column(name = "\"WORK_LOCATION_COUNTRY\"")
	private String workLocationCountry;
	
	
	@Column(name = "\"EMPLOYMENT_TYPE\"")
	private String employmentType;
	
	@Column(name = "\"JOB_ROLE\"")
	private String jobRole;
	
	@Column(name = "\"JOB_START_DATE\"")
	private String jobStartDate;
	
	@Column(name = "\"JOB_END_DATE\"")
	private String jobEndDate;
	
	@Column(name = "\"LAST_WORKING_DATE\"")
	private String lastWorkingDate;
	
	@Column(name = "\"FINISHED_CLIENT\"")
	private String finishedClient;
	
	@Column(name = "\"ADDITIONAL_INFO\"")
	private String additionalInfo;
	
	@Column(name = "\"ACTIVE_RECORD\"")
	private String activeRecord;
	
	@Column(name = "\"LAST_UPDATED_USER\"")
	private String lastUpdatedUser;

	@Column(name = "\"LAST_UPDATED_DATE_TIME\"")
	private LocalDateTime lastUpdatedDateTime;

	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger id) {
		Id = id;
	}

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

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	@Override
	public String toString() {
		return "ContractorEploymentDetailsEntity [Id=" + Id + ", contractorId=" + contractorId + ", clientName="
				+ clientName + ", endClientName=" + endClientName + ", contractNumber=" + contractNumber
				+ ", workLocationAddress=" + workLocationAddress + ", workLocationCity=" + workLocationCity
				+ ", workLocationState=" + workLocationState + ", workLocationZipCode=" + workLocationZipCode
				+ ", workLocationCountry=" + workLocationCountry + ", employmentType=" + employmentType + ", jobRole="
				+ jobRole + ", jobStartDate=" + jobStartDate + ", jobEndDate=" + jobEndDate + ", lastWorkingDate="
				+ lastWorkingDate + ", finishedClient=" + finishedClient + ", additionalInfo=" + additionalInfo
				+ ", activeRecord=" + activeRecord + ", lastUpdatedUser=" + lastUpdatedUser + ", lastUpdatedDateTime="
				+ lastUpdatedDateTime + "]";
	}

	
	
	
}
