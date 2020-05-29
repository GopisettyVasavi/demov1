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

}
