package com.renaissance.contractor.dto;

import java.util.ArrayList;
import java.util.List;

public class ContractorDetailsDTO {
	
	List<ContractorRateDetailsDTO> rateList=new ArrayList<ContractorRateDetailsDTO>();
	List<ContractorABNDetailsDTO> abnList= new ArrayList<ContractorABNDetailsDTO>();
	List<ContractorTFNDetailsDTO> tfnList= new ArrayList<ContractorTFNDetailsDTO>();
	List<ContractorBankDetailsDTO>bankList= new ArrayList<ContractorBankDetailsDTO>();
	List <ContractorSuperAnnuationDetailsDTO> superAnnuationList= new ArrayList<ContractorSuperAnnuationDetailsDTO>();
	List<ContractorEmploymentDetailsDTO>employerList= new ArrayList<ContractorEmploymentDetailsDTO>();
	ContractorPersonalDetailsDTO personalDetails= new ContractorPersonalDetailsDTO();
	public List<ContractorRateDetailsDTO> getRateList() {
		return rateList;
	}
	public void setRateList(List<ContractorRateDetailsDTO> rateList) {
		this.rateList = rateList;
	}
	public List<ContractorABNDetailsDTO> getAbnList() {
		return abnList;
	}
	public void setAbnList(List<ContractorABNDetailsDTO> abnList) {
		this.abnList = abnList;
	}
	public List<ContractorTFNDetailsDTO> getTfnList() {
		return tfnList;
	}
	public void setTfnList(List<ContractorTFNDetailsDTO> tfnList) {
		this.tfnList = tfnList;
	}
	public List<ContractorBankDetailsDTO> getBankList() {
		return bankList;
	}
	public void setBankList(List<ContractorBankDetailsDTO> bankList) {
		this.bankList = bankList;
	}
	public List<ContractorSuperAnnuationDetailsDTO> getSuperAnnuationList() {
		return superAnnuationList;
	}
	public void setSuperAnnuationList(List<ContractorSuperAnnuationDetailsDTO> superAnnuationList) {
		this.superAnnuationList = superAnnuationList;
	}
	public List<ContractorEmploymentDetailsDTO> getEmployerList() {
		return employerList;
	}
	public void setEmployerList(List<ContractorEmploymentDetailsDTO> employerList) {
		this.employerList = employerList;
	}
	public ContractorPersonalDetailsDTO getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(ContractorPersonalDetailsDTO personalDetails) {
		this.personalDetails = personalDetails;
	}
	

}
