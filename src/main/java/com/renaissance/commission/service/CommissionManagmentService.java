package com.renaissance.commission.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.commission.dto.CommissionDTO;
import com.renaissance.commission.entity.RecruiterCommissionsEntity;
import com.renaissance.commission.repository.RecruiterCommissionRepository;
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
	
	@Autowired
	RecruiterCommissionRepository recruiterCommission;
	/**
	 * This method will check first any temporary or final commissions exist for the given month and year. If so, those commissions will be returned or
	 * else it will create commissions for that month.
	 * @param monthAndYear
	 * @return
	 */
	public List<CommissionDTO> getCommissions(String monthAndYear){
		List<CommissionDTO> commissionList= new ArrayList<CommissionDTO>();
		boolean commissionsExist=false;
		if(!ProfileParserUtils.isObjectEmpty(monthAndYear)) {
			List<RecruiterCommissionsEntity> commissionsEntity=recruiterCommission.getCommissionsForSelectedMonthAndYear(monthAndYear);
			if(!ProfileParserUtils.isObjectEmpty(commissionsEntity)) {
				commissionsExist=true;
				logger.info("Commissions exist....");
				for(RecruiterCommissionsEntity commissionEntity:commissionsEntity) {
					CommissionDTO commissionDto= new CommissionDTO();
					BeanUtils.copyProperties(commissionEntity, commissionDto);
					commissionList.add(commissionDto);
				}
			}
		}
		
		
		if(!commissionsExist) {
			logger.info("Commissions do not exist....");
		List<ContractorEmploymentDetailsEntity> empDetails = contractorEmployment.getCandidatesForCommission(monthAndYear);
		//logger.info("Search Results EMP table, {}", empDetails.size());
		if (!ProfileParserUtils.isObjectEmpty(empDetails)) {
			//int i=1;
			for (ContractorEmploymentDetailsEntity empEntity : empDetails) {
				//logger.info("Result records...,{}", empEntity.toString());
				
				CommissionDTO commissionDto= new CommissionDTO();
				//commissionDto.setId(i);
				commissionDto.setRecruiterId(empEntity.getRecruiterId());
				commissionDto.setRecruiterName(empEntity.getRecruiterName());
				commissionDto.setJobStartDate(empEntity.getJobStartDate());
				commissionDto.setMonthYear(monthAndYear);
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
					commissionDto.setBillRatePerDay(rateEntity.getBillRatePerDay());
					commissionDto.setGrossMargin(rateEntity.getGrossMargin());
				}
				commissionList.add(commissionDto);

				//i++;
			}
		}
		}
		if(!ProfileParserUtils.isObjectEmpty(commissionList)) {
		commissionList.sort(Comparator.comparing(CommissionDTO::getFullName));
		int i=1;
		for(CommissionDTO commissionDto:commissionList) {
			commissionDto.setId(i);
			i++;
		}
		}
		return commissionList;
	}
	
	public List<CommissionDTO> saveCommissionsTemporary(List<CommissionDTO> commissionDtoList){
		List<CommissionDTO> savedList= new ArrayList<CommissionDTO>();
		if(!ProfileParserUtils.isObjectEmpty(commissionDtoList)) {
			for(CommissionDTO commission:commissionDtoList) {
				if(!ProfileParserUtils.isObjectEmpty(commission)) {
				//logger.info("DTO....{}",commission.toString());
				RecruiterCommissionsEntity commissionEntity=new RecruiterCommissionsEntity();
				CommissionDTO savedcommission= new CommissionDTO();
				BeanUtils.copyProperties(commission, commissionEntity);
				//logger.info("VO....{}",commissionEntity.toString());
				RecruiterCommissionsEntity previousCommission=recruiterCommission.getCommissionByContractorMonthYear(commission.getContractorId(),
						commission.getMonthYear(), commission.getRatePerDay(), commission.getJobStartDate());
				//logger.info("Existing record..{}",previousCommission.toString());
				recruiterCommission.delete(previousCommission);
				commissionEntity=	recruiterCommission.save(commissionEntity);
				BeanUtils.copyProperties(commissionEntity, savedcommission);
				savedList.add(savedcommission);
			}
			}
		}
		
		return savedList;
		
	}
}
