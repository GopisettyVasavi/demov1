package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorTFNDetailsDTO {
	private BigInteger contractorId;
	private String tfnNumber;
	private String newApplicationFlag;
	private String underAgeExemptionFlag;
	private String pensionHolderFlag;
	private String employmentType;
	private String taxPayerType;
	private String taxFreeThresholdFlag;
	private String loanFlag;
	private String financialShipmentDebtFlag;
	private String additionalInfo;
	private String activeRecord;
	public BigInteger getContractorId() {
		return contractorId;
	}
	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
	}
	public String getTfnNumber() {
		return tfnNumber;
	}
	public void setTfnNumber(String tfnNumber) {
		this.tfnNumber = tfnNumber;
	}
	public String getNewApplicationFlag() {
		return newApplicationFlag;
	}
	public void setNewApplicationFlag(String newApplicationFlag) {
		this.newApplicationFlag = newApplicationFlag;
	}
	public String getUnderAgeExemptionFlag() {
		return underAgeExemptionFlag;
	}
	public void setUnderAgeExemptionFlag(String underAgeExemptionFlag) {
		this.underAgeExemptionFlag = underAgeExemptionFlag;
	}
	public String getPensionHolderFlag() {
		return pensionHolderFlag;
	}
	public void setPensionHolderFlag(String pensionHolderFlag) {
		this.pensionHolderFlag = pensionHolderFlag;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public String getTaxPayerType() {
		return taxPayerType;
	}
	public void setTaxPayerType(String taxPayerType) {
		this.taxPayerType = taxPayerType;
	}
	public String getTaxFreeThresholdFlag() {
		return taxFreeThresholdFlag;
	}
	public void setTaxFreeThresholdFlag(String taxFreeThresholdFlag) {
		this.taxFreeThresholdFlag = taxFreeThresholdFlag;
	}
	public String getLoanFlag() {
		return loanFlag;
	}
	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}
	public String getFinancialShipmentDebtFlag() {
		return financialShipmentDebtFlag;
	}
	public void setFinancialShipmentDebtFlag(String financialShipmentDebtFlag) {
		this.financialShipmentDebtFlag = financialShipmentDebtFlag;
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
	@Override
	public String toString() {
		return "ContractorTFNDetailsDTO [contractorId=" + contractorId + ", tfnNumber=" + tfnNumber
				+ ", newApplicationFlag=" + newApplicationFlag + ", underAgeExemptionFlag=" + underAgeExemptionFlag
				+ ", pensionHolderFlag=" + pensionHolderFlag + ", employmentType=" + employmentType + ", taxPayerType="
				+ taxPayerType + ", taxFreeThresholdFlag=" + taxFreeThresholdFlag + ", loanFlag=" + loanFlag
				+ ", financialShipmentDebtFlag=" + financialShipmentDebtFlag + ", additionalInfo=" + additionalInfo
				+ ", activeRecord=" + activeRecord + "]";
	}

}
