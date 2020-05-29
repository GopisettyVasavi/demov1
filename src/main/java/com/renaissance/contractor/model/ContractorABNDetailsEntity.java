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
@Table(name = "parse.\"CONTRACTOR_ABN_DETAILS\"")
public class ContractorABNDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.contractor_abn_sequence")
	@SequenceGenerator(name = "parse.contractor_abn_sequence", sequenceName = "parse.contractor_abn_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"ABN_NUMBER\"")
	private String abnNumber;
	
	@Column(name = "\"ACN_NUMBER\"")
	private String acnNumber;
	
	@Column(name = "\"COMPANY_NAME\"")
	private String companyName;
	
	@Column(name = "\"COMPANY_ADDRESS\"")
	private String companyAddress;
	
	@Column(name = "\"COMPANY_CITY\"")
	private String companyCity;
	
	@Column(name = "\"COMPANY_STATE\"")
	private String companyState;
	
	@Column(name = "\"COMPANY_ZIP_CODE\"")
	private BigInteger companyZipCode;
	
	@Column(name = "\"ABN_GROUP\"")
	private String abnGroup;
	
	@Column(name = "\"GST_REGISTERED\"")
	private String gstRegistered;
	
	@Column(name = "\"GST_CERTIFICATE_PATH\"")
	private String gstCertificationPath;
	
	@Column(name = "\"PI_PL_FLAG\"")
	private String piPlFlag;
	
	@Column(name = "\"PI_PL_CERT1_PATH\"")
	private String piPlCert1Path;
	
	@Column(name = "\"PI_PL_CERT2_PATH\"")
	private String piPlCert2Path;
	
	@Column(name = "\"PI_PL_CERT3_PATH\"")
	private String piPlCert3Path;
	
	@Column(name = "\"WORK_COVER_FLAG\"")
	private String workCoverFlag;
	
	@Column(name = "\"WORK_COVER_CERT_PATH\"")
	private String workCoverCertPath;
	
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

	public String getAbnNumber() {
		return abnNumber;
	}

	public void setAbnNumber(String abnNumber) {
		this.abnNumber = abnNumber;
	}

	public String getAcnNumber() {
		return acnNumber;
	}

	public void setAcnNumber(String acnNumber) {
		this.acnNumber = acnNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyState() {
		return companyState;
	}

	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	public BigInteger getCompanyZipCode() {
		return companyZipCode;
	}

	public void setCompanyZipCode(BigInteger companyZipCode) {
		this.companyZipCode = companyZipCode;
	}

	public String getAbnGroup() {
		return abnGroup;
	}

	public void setAbnGroup(String abnGroup) {
		this.abnGroup = abnGroup;
	}

	public String getGstRegistered() {
		return gstRegistered;
	}

	public void setGstRegistered(String gstRegistered) {
		this.gstRegistered = gstRegistered;
	}

	public String getGstCertificationPath() {
		return gstCertificationPath;
	}

	public void setGstCertificationPath(String gstCertificationPath) {
		this.gstCertificationPath = gstCertificationPath;
	}

	public String getPiPlFlag() {
		return piPlFlag;
	}

	public void setPiPlFlag(String piPlFlag) {
		this.piPlFlag = piPlFlag;
	}

	public String getPiPlCert1Path() {
		return piPlCert1Path;
	}

	public void setPiPlCert1Path(String piPlCert1Path) {
		this.piPlCert1Path = piPlCert1Path;
	}

	public String getPiPlCert2Path() {
		return piPlCert2Path;
	}

	public void setPiPlCert2Path(String piPlCert2Path) {
		this.piPlCert2Path = piPlCert2Path;
	}

	public String getPiPlCert3Path() {
		return piPlCert3Path;
	}

	public void setPiPlCert3Path(String piPlCert3Path) {
		this.piPlCert3Path = piPlCert3Path;
	}

	public String getWorkCoverFlag() {
		return workCoverFlag;
	}

	public void setWorkCoverFlag(String workCoverFlag) {
		this.workCoverFlag = workCoverFlag;
	}

	public String getWorkCoverCertPath() {
		return workCoverCertPath;
	}

	public void setWorkCoverCertPath(String workCoverCertPath) {
		this.workCoverCertPath = workCoverCertPath;
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
		return "ContractorAbnDetailsEntity [Id=" + Id + ", contractorId=" + contractorId + ", abnNumber=" + abnNumber
				+ ", acnNumber=" + acnNumber + ", companyName=" + companyName + ", companyAddress=" + companyAddress
				+ ", companyCity=" + companyCity + ", companyState=" + companyState + ", companyZipCode="
				+ companyZipCode + ", abnGroup=" + abnGroup + ", gstRegistered=" + gstRegistered
				+ ", gstCertificationPath=" + gstCertificationPath + ", piPlFlag=" + piPlFlag + ", piPlCert1Path="
				+ piPlCert1Path + ", piPlCert2Path=" + piPlCert2Path + ", piPlCert3Path=" + piPlCert3Path
				+ ", workCoverFlag=" + workCoverFlag + ", workCoverCertPath=" + workCoverCertPath + ", additionalInfo="
				+ additionalInfo + ", activeRecord=" + activeRecord + ", lastUpdatedUser=" + lastUpdatedUser
				+ ", lastUpdatedDateTime=" + lastUpdatedDateTime + "]";
	}
	

}
