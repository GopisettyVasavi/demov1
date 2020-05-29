package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorSuperAnnuationDetailsDTO {
	private BigInteger contractorId;
	private String superAnnuationFundName;
	private String superAnnuationMemberId;
	private String additionalSuperAnnuationContributionFlag;
	private String additionalSuperAnnuationDetails;
	private String additionalInfo;
	private String activeRecord;
	public BigInteger getContractorId() {
		return contractorId;
	}
	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
	}
	public String getSuperAnnuationFundName() {
		return superAnnuationFundName;
	}
	public void setSuperAnnuationFundName(String superAnnuationFundName) {
		this.superAnnuationFundName = superAnnuationFundName;
	}
	public String getSuperAnnuationMemberId() {
		return superAnnuationMemberId;
	}
	public void setSuperAnnuationMemberId(String superAnnuationMemberId) {
		this.superAnnuationMemberId = superAnnuationMemberId;
	}
	public String getAdditionalSuperAnnuationContributionFlag() {
		return additionalSuperAnnuationContributionFlag;
	}
	public void setAdditionalSuperAnnuationContributionFlag(String additionalSuperAnnuationContributionFlag) {
		this.additionalSuperAnnuationContributionFlag = additionalSuperAnnuationContributionFlag;
	}
	public String getAdditionalSuperAnnuationDetails() {
		return additionalSuperAnnuationDetails;
	}
	public void setAdditionalSuperAnnuationDetails(String additionalSuperAnnuationDetails) {
		this.additionalSuperAnnuationDetails = additionalSuperAnnuationDetails;
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
		return "ContractorSuperAnnuationDetailsDTO [contractorId=" + contractorId + ", superAnnuationFundName="
				+ superAnnuationFundName + ", superAnnuationMemberId=" + superAnnuationMemberId
				+ ", additionalSuperAnnuationContributionFlag=" + additionalSuperAnnuationContributionFlag
				+ ", additionalSuperAnnuationDetails=" + additionalSuperAnnuationDetails + ", additionalInfo="
				+ additionalInfo + ", activeRecord=" + activeRecord + "]";
	}
	
}
