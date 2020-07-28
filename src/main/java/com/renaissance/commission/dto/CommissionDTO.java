package com.renaissance.commission.dto;

import java.math.BigInteger;

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
	private Integer noOfDaysWorked;
	
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
	@Override
	public String toString() {
		return "CommissionDTO [Id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", recruiterId=" + recruiterId + ", recruiterName=" + recruiterName + ", ratePerDay="
				+ ratePerDay + ", noOfDaysWorked=" + noOfDaysWorked + "]";
	}
	
}
