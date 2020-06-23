package com.renaissance.contractor.dto;

public class MarginDTO {
	private Double grossMargin;
	private Double netMargin;
	private Double contractorRate;
	private Double billRate;
	private Double payrollTax;
	private String workLocationState;
	private Double insuranceCost;
	private Double additionalCost;
	private Double recruiterCommission;
	private String superIncludeCheck;
	private String payrollTaxCheck;
	private String insurancePaymentFlag;
	private String insuranceType;
	private Double superannuation;
	private String referralCommissionType;
	private Double referralCommissionValue;
	private Double insurancePercentage;
	private Double insuranceValue;
	private Double payrollTaxValue;
	private Double referralValue;
	public Double getGrossMargin() {
		return grossMargin;
	}
	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}
	public Double getNetMargin() {
		return netMargin;
	}
	public void setNetMargin(Double netMargin) {
		this.netMargin = netMargin;
	}
	public Double getContractorRate() {
		return contractorRate;
	}
	public void setContractorRate(Double contractorRate) {
		this.contractorRate = contractorRate;
	}
	public Double getBillRate() {
		return billRate;
	}
	public void setBillRate(Double billRate) {
		this.billRate = billRate;
	}
	public Double getPayrollTax() {
		return payrollTax;
	}
	public void setPayrollTax(Double payrollTax) {
		this.payrollTax = payrollTax;
	}
	public String getWorkLocationState() {
		return workLocationState;
	}
	public void setWorkLocationState(String workLocationState) {
		this.workLocationState = workLocationState;
	}
	public Double getInsuranceCost() {
		return insuranceCost;
	}
	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	
	public Double getAdditionalCost() {
		return additionalCost;
	}
	public void setAdditionalCost(Double additionalCost) {
		this.additionalCost = additionalCost;
	}
	public Double getRecruiterCommission() {
		return recruiterCommission;
	}
	public void setRecruiterCommission(Double recruiterCommission) {
		this.recruiterCommission = recruiterCommission;
	}
	public String getSuperIncludeCheck() {
		return superIncludeCheck;
	}
	public void setSuperIncludeCheck(String superIncludeCheck) {
		this.superIncludeCheck = superIncludeCheck;
	}
	public String getPayrollTaxCheck() {
		return payrollTaxCheck;
	}
	public void setPayrollTaxCheck(String payrollTaxCheck) {
		this.payrollTaxCheck = payrollTaxCheck;
	}
	
	public String getInsurancePaymentFlag() {
		return insurancePaymentFlag;
	}
	public void setInsurancePaymentFlag(String insurancePaymentFlag) {
		this.insurancePaymentFlag = insurancePaymentFlag;
	}
	
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public Double getSuperannuation() {
		return superannuation;
	}
	public void setSuperannuation(Double superannuation) {
		this.superannuation = superannuation;
	}
	public String getReferralCommissionType() {
		return referralCommissionType;
	}
	public void setReferralCommissionType(String referralCommissionType) {
		this.referralCommissionType = referralCommissionType;
	}
	public Double getReferralCommissionValue() {
		return referralCommissionValue;
	}
	public void setReferralCommissionValue(Double referralCommissionValue) {
		this.referralCommissionValue = referralCommissionValue;
	}
	
	public Double getInsurancePercentage() {
		return insurancePercentage;
	}
	public void setInsurancePercentage(Double insurancePercentage) {
		this.insurancePercentage = insurancePercentage;
	}
	
	public Double getInsuranceValue() {
		return insuranceValue;
	}
	public void setInsuranceValue(Double insuranceValue) {
		this.insuranceValue = insuranceValue;
	}
	public Double getPayrollTaxValue() {
		return payrollTaxValue;
	}
	public void setPayrollTaxValue(Double payrollTaxValue) {
		this.payrollTaxValue = payrollTaxValue;
	}
	public Double getReferralValue() {
		return referralValue;
	}
	public void setReferralValue(Double referralValue) {
		this.referralValue = referralValue;
	}
	@Override
	public String toString() {
		return "MarginDTO [grossMargin=" + grossMargin + ", netMargin=" + netMargin + ", contractorRate="
				+ contractorRate + ", billRate=" + billRate + ", payrollTax=" + payrollTax + ", workLocationState="
				+ workLocationState + ", insuranceCost=" + insuranceCost + ", additionalCost=" + additionalCost
				+ ", recruiterCommission=" + recruiterCommission + ", superIncludeCheck=" + superIncludeCheck
				+ ", payrollTaxCheck=" + payrollTaxCheck + ", insurancePaymentFlag=" + insurancePaymentFlag
				+ ", insuranceType=" + insuranceType + ", superannuation=" + superannuation
				+ ", referralCommissionType=" + referralCommissionType + ", referralCommissionValue="
				+ referralCommissionValue + ", insurancePercentage=" + insurancePercentage + ", insuranceValue="
				+ insuranceValue + ", payrollTaxValue=" + payrollTaxValue + ", referralValue=" + referralValue + "]";
	}

}
