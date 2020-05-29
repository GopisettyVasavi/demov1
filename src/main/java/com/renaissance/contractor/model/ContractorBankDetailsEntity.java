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
@Table(name = "parse.\"CONTRACTOR_BANK_DETAILS\"")
public class ContractorBankDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.contractor_bank_sequence")
	@SequenceGenerator(name = "parse.contractor_bank_sequence", sequenceName = "parse.contractor_bank_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"ACCOUNT_NAME\"")
	private String accountName;
	
	@Column(name = "\"BSB\"")
	private String bsb;
	
	@Column(name = "\"ACCOUNT_NUMBER\"")
	private String accountNumber;
	
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
		return "ContractorBankDetailsEntity [Id=" + Id + ", contractorId=" + contractorId + ", accountName="
				+ accountName + ", bsb=" + bsb + ", accountNumber=" + accountNumber + ", additionalInfo="
				+ additionalInfo + ", activeRecord=" + activeRecord + ", lastUpdatedUser=" + lastUpdatedUser
				+ ", lastUpdatedDateTime=" + lastUpdatedDateTime + "]";
	}
	
}
