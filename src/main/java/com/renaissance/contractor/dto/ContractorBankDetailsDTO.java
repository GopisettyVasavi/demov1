package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorBankDetailsDTO {
	private BigInteger contractorId;
	private String accountName;
	private String bsb;
	private String accountNumber;
	private String additionalInfo;
	private String activeRecord;
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
		return "ContractorBankDetailsDTO [contractorId=" + contractorId + ", accountName=" + accountName + ", bsb="
				+ bsb + ", accountNumber=" + accountNumber + ", additionalInfo=" + additionalInfo + ", activeRecord="
				+ activeRecord + "]";
	}





}
