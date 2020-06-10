package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class BankAndSAHistoryDto {
	private BigInteger contractorId;
	private String accountName;
	private String bsb;
	private String accountNumber;
	private String superAnnuationFundName;
	private String superAnnuationMemberId;
	private String additionalSuperAnnuationContributionFlag;
	private String additionalSuperAnnuationDetails;
	public BigInteger getContractorId() {
		return contractorId;
	}
	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBsb() {
		return bsb;
	}
	public void setBsb(String bsb) {
		this.bsb = bsb;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
	

}
