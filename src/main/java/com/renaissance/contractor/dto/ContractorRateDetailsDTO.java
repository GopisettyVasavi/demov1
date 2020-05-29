package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorRateDetailsDTO {
	private BigInteger contractorId;
	private Double ratePerDay;
	private String rateStartDate;
	private String rateEndDate;
	private String includeSuperFlag;
	private Double billRatePerDay;;
	private Integer recruiterId;
	private String recruiterName;
	private String payrollTaxPaymentFlag;
	private String workCoverFlag;;
	private Double insurancePercentage;
	private Double otherDeductionPercentage;
	private Double otherDeductionAmount;
	private Double netMargin;
	private String additionalInfo;
	private String activeRecord;
	@Override
	public String toString() {
		return "ContractorRateDetailsDTO [contractorId=" + contractorId + ", ratePerDay=" + ratePerDay
				+ ", rateStartDate=" + rateStartDate + ", rateEndDate=" + rateEndDate + ", includeSuperFlag="
				+ includeSuperFlag + ", billRatePerDay=" + billRatePerDay + ", recruiterId=" + recruiterId
				+ ", recruiterName=" + recruiterName + ", payrollTaxPaymentFlag=" + payrollTaxPaymentFlag
				+ ", workCoverFlag=" + workCoverFlag + ", insurancePercentage=" + insurancePercentage
				+ ", otherDeductionPercentage=" + otherDeductionPercentage + ", otherDeductionAmount="
				+ otherDeductionAmount + ", netMargin=" + netMargin + ", additionalInfo=" + additionalInfo
				+ ", activeRecord=" + activeRecord + "]";
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

}
