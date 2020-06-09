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
@Table(name = "parse.\"CONTRACTOR_TFN_DETAILS\"")
public class ContractorTFNDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.contractor_tfn_sequence")
	@SequenceGenerator(name = "parse.contractor_tfn_sequence", sequenceName = "parse.contractor_tfn_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"TFN_NUMBER\"")
	private String tfnNumber;
	
	@Column(name = "\"NEW_APPLICATION_FLAG\"")
	private String newApplicationFlag;
	
	@Column(name = "\"UNDER_AGE_EXEMPTION_FLAG\"")
	private String underAgeExemptionFlag;
	
	@Column(name = "\"PENSION_HOLDER_FLAG\"")
	private String pensionHolderFlag;
	
	@Column(name = "\"EMPLOYMENT_TYPE\"")
	private String employmentType;
	
	@Column(name = "\"TAX_PAYER_TYPE\"")
	private String taxPayerType;
	
	@Column(name = "\"TAX_FREE_THRESHOLD_FLAG\"")
	private String taxFreeThresholdFlag;
	
	@Column(name = "\"LOAN_FLAG\"")
	private String loanFlag;
	
	@Column(name = "\"FINANCIAL_SHIPMENT_DEBT_FLAG\"")
	private String financialShipmentDebtFlag;
	
	@Column(name = "\"ADDITIONAL_INFO\"")
	private String additionalInfo;
	
	@Column(name = "\"LAST_UPDATED_USER\"")
	private String lastUpdatedUser;

	@Column(name = "\"LAST_UPDATED_DATE_TIME\"")
	private LocalDateTime lastUpdatedDateTime;
	
	@Column(name = "\"ACTIVE_RECORD\"")
	private String activeRecord;
	

	public String getActiveRecord() {
		return activeRecord;
	}

	public void setActiveRecord(String activeRecord) {
		this.activeRecord = activeRecord;
	}

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
		return "ContractorTfnDetailsEntity [Id=" + Id + ", contractorId=" + contractorId + ", tfnNumber=" + tfnNumber
				+ ", newApplicationFlag=" + newApplicationFlag + ", underAgeExemptionFlag=" + underAgeExemptionFlag
				+ ", pensionHolderFlag=" + pensionHolderFlag + ", employmentType=" + employmentType + ", taxPayerType="
				+ taxPayerType + ", taxFreeThresholdFlag=" + taxFreeThresholdFlag + ", loanFlag=" + loanFlag
				+ ", financialShipmentDebtFlag=" + financialShipmentDebtFlag + ", additionalInfo=" + additionalInfo
				+ ", activeRecord=" + activeRecord + ", lastUpdatedUser=" + lastUpdatedUser + ", lastUpdatedDateTime="
				+ lastUpdatedDateTime + "]";
	}
	
	

}
