package com.renaissance.commission.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"COMMISSIONS_DETAILS\"")
public class CommissionsDetailsEntity {

	@Id
	@GeneratedValue(generator = "parse.recruiter_commission_id_sequence")
	@SequenceGenerator(name = "parse.recruiter_commission_id_sequence", sequenceName = "parse.recruiter_commission_id_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"CONTRACTOR_NAME\"")
	private String fullName;
	
	@Column(name = "\"RECRUITER_NAME\"")
	private String recruiterName;
	
	@Column(name = "\"MONTH_YEAR\"")
	private String monthYear;
	
	@Column(name = "\"MONTH_YEAR_DATE\"")
	private LocalDate monthYearDate;
	
	@Column(name = "\"NO_OF_DAYS_WORKED\"")
	private Integer noOfDaysWorked;
	
	@Column(name = "\"COMMISSION_PERCENTAGE\"")
	private Double commission;
	
	@Column(name = "\"COMMISSION_FOR_CANDIDATE\"")
	private Double commissionForCandidate;
	
	@Column(name = "\"RATE_PER_DAY\"")
	private Double ratePerDay;
	
	@Column(name = "\"BILL_RATE_PER_DAY\"")
	private Double billRatePerDay;
	
	@Column(name = "\"GROSS_MARGIN\"")
	private Double grossMargin;
	
	@Column(name = "\"JOB_START_DATE\"")
	private String jobStartDate;
	
	@Column(name = "\"STATUS\"")
	private String status;
	
	@Column(name = "\"EMPLOYMENT_TYPE\"")
	private String employmentType;
	

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public LocalDate getMonthYearDate() {
		return monthYearDate;
	}

	public void setMonthYearDate(LocalDate monthYearDate) {
		this.monthYearDate = monthYearDate;
	}

	public Integer getNoOfDaysWorked() {
		return noOfDaysWorked;
	}

	public void setNoOfDaysWorked(Integer noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
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

	public Double getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}

	public String getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	@Override
	public String toString() {
		return "CommissionsDetailsEntity [Id=" + Id + ", contractorId=" + contractorId + ", fullName=" + fullName
				+ ", recruiterName=" + recruiterName + ", monthYear=" + monthYear + ", monthYearDate=" + monthYearDate
				+ ", noOfDaysWorked=" + noOfDaysWorked + ", commission=" + commission + ", commissionForCandidate="
				+ commissionForCandidate + ", ratePerDay=" + ratePerDay + ", billRatePerDay=" + billRatePerDay
				+ ", grossMargin=" + grossMargin + ", jobStartDate=" + jobStartDate + ", status=" + status
				+ ", employmentType=" + employmentType + "]";
	}

	
	
	
}
