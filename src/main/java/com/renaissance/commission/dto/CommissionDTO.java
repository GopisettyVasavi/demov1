package com.renaissance.commission.dto;

import java.math.BigInteger;
import java.time.LocalDate;

public class CommissionDTO {

	Integer id;
	private BigInteger contractorId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private BigInteger recruiterId;
	private String recruiterName;	
	private Double ratePerDay;
	private Double billRatePerDay;
	private Double grossMargin;
	private Integer noOfDaysWorked;
	private String jobStartDate;
	private LocalDate parsedDate;
	private Double commission;
	private Double commissionForCandidate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Double getRatePerDay() {
		return ratePerDay;
	}
	public void setRatePerDay(Double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}
	public Integer getNoOfDaysWorked() {
		return noOfDaysWorked;
	}
	public void setNoOfDaysWorked(Integer noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
	}
	
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
	
	public String getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	
	public Double getBillRatePerDay() {
		return billRatePerDay;
	}
	public void setBillRatePerDay(Double billRatePerDay) {
		this.billRatePerDay = billRatePerDay;
	}
	public Double getGrossMargin() {
		return grossMargin;
	}
	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}
	
	public LocalDate getParsedDate() {
		return parsedDate;
	}
	public void setParsedDate(LocalDate parsedDate) {
		this.parsedDate = parsedDate;
	}
	
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getCommissionForCandidate() {
		return commissionForCandidate;
	}
	public void setCommissionForCandidate(Double commissionForCandidate) {
		this.commissionForCandidate = commissionForCandidate;
	}
	@Override
	public String toString() {
		return "CommissionDTO [id=" + id + ", contractorId=" + contractorId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", fullName=" + fullName + ", recruiterId="
				+ recruiterId + ", recruiterName=" + recruiterName + ", ratePerDay=" + ratePerDay + ", billRatePerDay="
				+ billRatePerDay + ", grossMargin=" + grossMargin + ", noOfDaysWorked=" + noOfDaysWorked
				+ ", jobStartDate=" + jobStartDate + "]";
	}
	
	
}
