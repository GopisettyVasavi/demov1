package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorABNDetailsDTO {
	private BigInteger contractorId;
	private String abnNumber;
	private String acnNumber;
	private String companyName;
	private String companyAddress;
	private String companyCity;;
	private String companyState;
	private BigInteger companyZipCode;
	private String abnGroup;
	private String gstRegistered;
	private String gstCertPath;
	private String piPlFlag;
	private String piPlCert1Path;
	private String piPlCert2Path;
	private String piPlCert3Path;
	private String workCoverFlag;
	private String workCoverCertPath;
	private String additionalInfo;
	private String activeRecord;
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
	public String getGstCertPath() {
		return gstCertPath;
	}
	public void setGstCertPath(String gstCertPath) {
		this.gstCertPath = gstCertPath;
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
	@Override
	public String toString() {
		return "ContractorABNDetailsDTO [contractorId=" + contractorId + ", abnNumber=" + abnNumber + ", acnNumber="
				+ acnNumber + ", companyName=" + companyName + ", companyAddress=" + companyAddress + ", companyCity="
				+ companyCity + ", companyState=" + companyState + ", companyZipCode=" + companyZipCode + ", abnGroup="
				+ abnGroup + ", gstRegistered=" + gstRegistered + ", gstCertPath=" + gstCertPath + ", piPlFlag="
				+ piPlFlag + ", piPlCert1Path=" + piPlCert1Path + ", piPlCert2Path=" + piPlCert2Path
				+ ", piPlCert3Path=" + piPlCert3Path + ", workCoverFlag=" + workCoverFlag + ", workCoverCertPath="
				+ workCoverCertPath + ", additionalInfo=" + additionalInfo + ", activeRecord=" + activeRecord + "]";
	}
	
	
	
}
