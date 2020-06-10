package com.renaissance.contractor.dto;

public class ContractorDetailsDTO {
	
	ContractorRateDetailsDTO rateList=new ContractorRateDetailsDTO();
	ContractorABNDetailsDTO abnList= new ContractorABNDetailsDTO();
	ContractorTFNDetailsDTO  tfnList= new ContractorTFNDetailsDTO();
	ContractorBankDetailsDTO bankList= new ContractorBankDetailsDTO();
	ContractorSuperAnnuationDetailsDTO superAnnuationList= new ContractorSuperAnnuationDetailsDTO();
	ContractorPersonalDetailsDTO personalDetails= new ContractorPersonalDetailsDTO();
	ContractorEmploymentDetailsDTO employerList=new ContractorEmploymentDetailsDTO();
	public ContractorRateDetailsDTO getRateList() {
		return rateList;
	}
	public void setRateList(ContractorRateDetailsDTO rateList) {
		this.rateList = rateList;
	}
	public ContractorABNDetailsDTO getAbnList() {
		return abnList;
	}
	public void setAbnList(ContractorABNDetailsDTO abnList) {
		this.abnList = abnList;
	}
	public ContractorTFNDetailsDTO getTfnList() {
		return tfnList;
	}
	public void setTfnList(ContractorTFNDetailsDTO tfnList) {
		this.tfnList = tfnList;
	}
	public ContractorBankDetailsDTO getBankList() {
		return bankList;
	}
	public void setBankList(ContractorBankDetailsDTO bankList) {
		this.bankList = bankList;
	}
	public ContractorSuperAnnuationDetailsDTO getSuperAnnuationList() {
		return superAnnuationList;
	}
	public void setSuperAnnuationList(ContractorSuperAnnuationDetailsDTO superAnnuationList) {
		this.superAnnuationList = superAnnuationList;
	}
	public ContractorPersonalDetailsDTO getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(ContractorPersonalDetailsDTO personalDetails) {
		this.personalDetails = personalDetails;
	}
	public ContractorEmploymentDetailsDTO getEmployerList() {
		return employerList;
	}
	public void setEmployerList(ContractorEmploymentDetailsDTO employerList) {
		this.employerList = employerList;
	}
	

}
