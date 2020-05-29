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
@Table(name = "parse.\"CONTRACTOR_RATE_DETAILS\"")
public class ContractorRateDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.contractor_rate_sequence")
	@SequenceGenerator(name = "parse.contractor_rate_sequence", sequenceName = "parse.contractor_rate_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"RATE_PER_DAY\"")
	private Double ratePerDay;
	
	@Column(name = "\"RATE_START_DATE\"")
	private String rateStartDate;
	
	@Column(name = "\"RATE_END_DATE\"")
	private String rateEndDate;
	
	@Column(name = "\"INCLUDE_SUPER_FLAG\"")
	private String includeSuperFlag;
	
	@Column(name = "\"BILL_RATE_PER_DAY\"")
	private Double billRatePerDay;
	
	@Column(name = "\"RECRUITER_ID\"")
	private Integer recruiterId;
	
	@Column(name = "\"RECRUITER_NAME\"")
	private String recruiterName;
	
	@Column(name = "\"PAYROLL_TAX_PAYMENT_FLAG\"")
	private String payrollTaxPaymentFlag;
	
	@Column(name = "\"WORK_COVER_FLAG\"")
	private String workCoverFlag;
	
	@Column(name = "\"INSURANCE_PERCENTAGE\"")
	private Double insurancePercentage;
	
	@Column(name = "\"OTHER_DEDUCTION_PERCENTAGE\"")
	private Double otherDeductionPercentage;
	
	@Column(name = "\"OTHER_DEDUCTION_AMOUNT\"")
	private Double otherDeductionAmount;
	
	@Column(name = "\"NET_MARGIN\"")
	private Double netMargin;
	
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

	public Double getRatePerDay() {
		return ratePerDay;
	}

	public void setRatePerDay(Double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}

	public String getRateStartDate() {
		return rateStartDate;
	}

	public void setRateStartDate(String rateStartDate) {
		this.rateStartDate = rateStartDate;
	}

	public String getRateEndDate() {
		return rateEndDate;
	}

	public void setRateEndDate(String rateEndDate) {
		this.rateEndDate = rateEndDate;
	}

	public String getIncludeSuperFlag() {
		return includeSuperFlag;
	}

	public void setIncludeSuperFlag(String includeSuperFlag) {
		this.includeSuperFlag = includeSuperFlag;
	}

	public Double getBillRatePerDay() {
		return billRatePerDay;
	}

	public void setBillRatePerDay(Double billRatePerDay) {
		this.billRatePerDay = billRatePerDay;
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

	public String getPayrollTaxPaymentFlag() {
		return payrollTaxPaymentFlag;
	}

	public void setPayrollTaxPaymentFlag(String payrollTaxPaymentFlag) {
		this.payrollTaxPaymentFlag = payrollTaxPaymentFlag;
	}

	public String getWorkCoverFlag() {
		return workCoverFlag;
	}

	public void setWorkCoverFlag(String workCoverFlag) {
		this.workCoverFlag = workCoverFlag;
	}

	public Double getInsurancePercentage() {
		return insurancePercentage;
	}

	public void setInsurancePercentage(Double insurancePercentage) {
		this.insurancePercentage = insurancePercentage;
	}

	public Double getOtherDeductionPercentage() {
		return otherDeductionPercentage;
	}

	public void setOtherDeductionPercentage(Double otherDeductionPercentage) {
		this.otherDeductionPercentage = otherDeductionPercentage;
	}

	public Double getOtherDeductionAmount() {
		return otherDeductionAmount;
	}

	public void setOtherDeductionAmount(Double otherDeductionAmount) {
		this.otherDeductionAmount = otherDeductionAmount;
	}

	public Double getNetMargin() {
		return netMargin;
	}

	public void setNetMargin(Double netMargin) {
		this.netMargin = netMargin;
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

	@Override
	public String toString() {
		return "ContractorRateDetailsEntity [Id=" + Id + ", contractorId=" + contractorId + ", ratePerDay=" + ratePerDay
				+ ", rateStartDate=" + rateStartDate + ", rateEndDate=" + rateEndDate + ", includeSuperFlag="
				+ includeSuperFlag + ", billRatePerDay=" + billRatePerDay + ", recruiterId=" + recruiterId
				+ ", recruiterName=" + recruiterName + ", payrollTaxPaymentFlag=" + payrollTaxPaymentFlag
				+ ", workCoverFlag=" + workCoverFlag + ", insurancePercentage=" + insurancePercentage
				+ ", otherDeductionPercentage=" + otherDeductionPercentage + ", otherDeductionAmount="
				+ otherDeductionAmount + ", netMargin=" + netMargin + ", additionalInfo=" + additionalInfo
				+ ", activeRecord=" + activeRecord + ", lastUpdatedUser=" + lastUpdatedUser + ", lastUpdatedDateTime="
				+ lastUpdatedDateTime + "]";
	}

}