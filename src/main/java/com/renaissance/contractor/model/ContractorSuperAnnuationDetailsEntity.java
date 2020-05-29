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
@Table(name = "parse.\"CONTRACTOR_SUPER_ANNUATION_DETAILS\"")
public class ContractorSuperAnnuationDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.contractor_super_annuation_sequence")
	@SequenceGenerator(name = "parse.contractor_super_annuation_sequence", sequenceName = "parse.contractor_super_annuation_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"SUPER_ANNUATION_FUND_NAME\"")
	private String superAnnuationFundName;
	
	@Column(name = "\"SUPER_ANNUATION_MEMBER_ID\"")
	private String superAnnuationMemberId;
	
	@Column(name = "\"ADDITIONAL_SUPER_ANNUATION_CONTRIBUTION_FLAG\"")
	private String additionalSuperAnnuationContributionFlag;
	
	@Column(name = "\"ADDITIONAL_SUPER_ANNUATION_DETAILS\"")
	private String additionalSuperAnnuationDetails;
	
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
		return "ContractorSuperAnnuationDetailsEntity [Id=" + Id + ", contractorId=" + contractorId
				+ ", superAnnuationFundName=" + superAnnuationFundName + ", superAnnuationMemberId="
				+ superAnnuationMemberId + ", additionalSuperAnnuationContributionFlag="
				+ additionalSuperAnnuationContributionFlag + ", additionalSuperAnnuationDetails="
				+ additionalSuperAnnuationDetails + ", additionalInfo=" + additionalInfo + ", activeRecord="
				+ activeRecord + ", lastUpdatedUser=" + lastUpdatedUser + ", lastUpdatedDateTime=" + lastUpdatedDateTime
				+ "]";
	}

}
