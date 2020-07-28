package com.renaissance.commission.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.commission.dto.CommissionDTO;
import com.renaissance.contractor.model.ContractorEmploymentDetailsEntity;
import com.renaissance.contractor.model.ContractorPersonalDetailsEntity;
import com.renaissance.contractor.model.ContractorRateDetailsEntity;
import com.renaissance.contractor.repository.ContractorEmploymentDetailsRepository;
import com.renaissance.contractor.repository.ContractorPersonalDetailsRepository;
import com.renaissance.contractor.repository.ContractorRateDetailsRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class CommissionManagmentService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommissionManagmentService.class);

	@Autowired
	ContractorPersonalDetailsRepository contractorPersonal;
	
	@Autowired
	ContractorRateDetailsRepository contractorRate;
	
	@Autowired
	ContractorEmploymentDetailsRepository contractorEmployment;
	
	public List<CommissionDTO> getCommissions(String monthAndYear){
		
		List<CommissionDTO> commissionList= new ArrayList<CommissionDTO>();
		List<ContractorEmploymentDetailsEntity> empDetails = contractorEmployment.getCandidatesForCommission(monthAndYear);
		logger.info("Search Results EMP table, {}", empDetails.size());
		if (!ProfileParserUtils.isObjectEmpty(empDetails)) {
			int i=1;
			for (ContractorEmploymentDetailsEntity empEntity : empDetails) {
				logger.info("Result records...,{}", empEntity.toString());
				
				CommissionDTO commissionDto= new CommissionDTO();
				commissionDto.setId(i);
				commissionDto.setRecruiterId(empEntity.getRecruiterId());
				commissionDto.setRecruiterName(empEntity.getRecruiterName());
				ContractorPersonalDetailsEntity personalEntity = contractorPersonal
						.getPersonalDetailsByContractorId(empEntity.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(personalEntity)) {
					commissionDto.setContractorId(personalEntity.getContractorId());
					commissionDto.setFirstName(personalEntity.getFirstName());
					commissionDto.setMiddleName(personalEntity.getMiddleName());
					commissionDto.setLastName(personalEntity.getLastName());
					commissionDto.setFullName(personalEntity.getFirstName()+" "+personalEntity.getMiddleName()+" "+personalEntity.getLastName());
					
				}
				ContractorRateDetailsEntity rateEntity = contractorRate.getRateDetailsByContractorId(empEntity.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(rateEntity)) {
					commissionDto.setRatePerDay(rateEntity.getRatePerDay());
				}
				commissionList.add(commissionDto);

				i++;
			}
		}
		return commissionList;
	}
}
