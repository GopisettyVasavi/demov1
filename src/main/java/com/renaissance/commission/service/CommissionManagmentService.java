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
import com.renaissance.commission.dto.CommissionsLookupDTO;
import com.renaissance.commission.dto.FinalCommissionsDTO;
import com.renaissance.commission.dto.RecruiterCommissionsDTO;
import com.renaissance.commission.model.CommissionsDetailsEntity;
import com.renaissance.commission.model.RecruiterCommissionsEntity;
import com.renaissance.commission.model.SearchCommissionForm;
import com.renaissance.commission.repository.CommissionsDetailsRepository;
import com.renaissance.commission.repository.CommissionsLookupEntity;
import com.renaissance.commission.repository.RecruiterCommissionsRepository;
import com.renaissance.common.repository.CommissionsLookupRepository;
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
	CommissionsDetailsRepository commissionsDetails;
	
	@Autowired
	RecruiterCommissionsRepository recruiterCommissions;
	
	@Autowired
	CommissionsLookupRepository commissionsLookup;
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
			List<CommissionsDetailsEntity> commissionsEntity=commissionsDetails.getCommissionsForSelectedMonthAndYear(monthAndYear,null);
			if(!ProfileParserUtils.isObjectEmpty(commissionsEntity)) {
				commissionsExist=true;
				logger.info("Commissions exist....");
				for(CommissionsDetailsEntity commissionEntity:commissionsEntity) {
					CommissionDTO commissionDto= new CommissionDTO();
					BeanUtils.copyProperties(commissionEntity, commissionDto);
					commissionList.add(commissionDto);
				}
			}
		}
		
		
		if(!commissionsExist) {
			logger.info("Commissions do not exist....");
		List<ContractorEmploymentDetailsEntity> empDetails = contractorEmployment.getCandidatesForCommission
				(ProfileParserUtils.parseStringDate("01/"+monthAndYear));
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
				commissionDto.setEmploymentType(empEntity.getEmploymentType());
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
				CommissionsDetailsEntity commissionEntity=new CommissionsDetailsEntity();
				CommissionDTO savedcommission= new CommissionDTO();
				BeanUtils.copyProperties(commission, commissionEntity);
				commissionEntity.setMonthYearDate(ProfileParserUtils.parseStringDate("01/"+commission.getMonthYear()));
				//logger.info("VO....{}",commissionEntity.toString());
				CommissionsDetailsEntity previousCommission=commissionsDetails.getCommissionByContractorMonthYear(commission.getContractorId(),
						commission.getMonthYear(), commission.getRatePerDay(), commission.getJobStartDate());
				//logger.info("Existing record..{}",previousCommission.toString());
				commissionsDetails.delete(previousCommission);
				commissionEntity=	commissionsDetails.save(commissionEntity);
				BeanUtils.copyProperties(commissionEntity, savedcommission);
				savedList.add(savedcommission);
			}
			}
		}
		
		return savedList;
		
	}
	
	public FinalCommissionsDTO finalizeCommissions(FinalCommissionsDTO finalCommissions) {
		List<CommissionDTO> savedCommissionsList= new ArrayList<CommissionDTO>();
		List<RecruiterCommissionsDTO> savedRecruiterCommissionsList= new ArrayList<RecruiterCommissionsDTO>();
		FinalCommissionsDTO finalCommission = new FinalCommissionsDTO();
		if(!ProfileParserUtils.isObjectEmpty(finalCommissions)) {
			List<CommissionDTO> commissions= finalCommissions.getCommissionsList();
			if(!ProfileParserUtils.isObjectEmpty(commissions)) {
				for(CommissionDTO commission:commissions) {
					if(!ProfileParserUtils.isObjectEmpty(commission)) {
					//logger.info("DTO....{}",commission.toString());
					CommissionsDetailsEntity commissionEntity=new CommissionsDetailsEntity();
					CommissionDTO savedcommission= new CommissionDTO();
					BeanUtils.copyProperties(commission, commissionEntity);
					commissionEntity.setMonthYearDate(ProfileParserUtils.parseStringDate("01/"+commission.getMonthYear()));

					//logger.info("VO....{}",commissionEntity.toString());
					CommissionsDetailsEntity previousCommission=commissionsDetails.getCommissionByContractorMonthYear(commission.getContractorId(),
							commission.getMonthYear(), commission.getRatePerDay(), commission.getJobStartDate());
					//logger.info("Existing record..{}",previousCommission.toString());
					commissionsDetails.delete(previousCommission);
					commissionEntity=	commissionsDetails.save(commissionEntity);
					BeanUtils.copyProperties(commissionEntity, savedcommission);
					savedCommissionsList.add(savedcommission);
				}
				}
			}
			
			List<RecruiterCommissionsDTO> recruiterCommissionsList=finalCommissions.getRecruiterCommissionsList();
			if(!ProfileParserUtils.isObjectEmpty(recruiterCommissionsList)) {
				for(RecruiterCommissionsDTO recruiterCommissionDto:recruiterCommissionsList) {
					RecruiterCommissionsEntity rcCommission= new RecruiterCommissionsEntity();
					RecruiterCommissionsDTO savedRecruiterComm= new RecruiterCommissionsDTO();
					/*
					 * String monthYear=recruiterCommissionDto.getMonthYear();
					 * monthYear=monthYear.replace("-", "/");
					 * recruiterCommissionDto.setMonthYear("01/"+monthYear);
					 */
					RecruiterCommissionsEntity previousCommission=recruiterCommissions.getRecruiterCommissionByMonthYearAndRecruiter(
							ProfileParserUtils.parseStringDate(recruiterCommissionDto.getMonthYear()),
							recruiterCommissionDto.getRecruiterName());
					recruiterCommissions.delete(previousCommission);
					
					//BeanUtils.copyProperties(recruiterCommissionDto, rcCommission);
					rcCommission.setRecruiterName(recruiterCommissionDto.getRecruiterName());
					rcCommission.setContractCommissionTotal(recruiterCommissionDto.getContractCommissionTotal());
					rcCommission.setContractCommissionTotalSuper(recruiterCommissionDto.getContractCommissionTotalSuper());
					rcCommission.setMonthYear(ProfileParserUtils.parseStringDate(recruiterCommissionDto.getMonthYear()));
					rcCommission= recruiterCommissions.save(rcCommission);
					//BeanUtils.copyProperties(rcCommission, savedRecruiterComm);
					savedRecruiterCommissionsList.add(savedRecruiterComm);
				}
			}
		}
		finalCommission.setCommissionsList(savedCommissionsList);
		finalCommission.setRecruiterCommissionsList(savedRecruiterCommissionsList);
		return finalCommission;
	}
	public List<RecruiterCommissionsDTO> searchCommissions(SearchCommissionForm searchForm){
		List<RecruiterCommissionsEntity> commissions=recruiterCommissions.searchCommissions(searchForm);
		List<RecruiterCommissionsDTO> recruiterCommissions= new ArrayList<RecruiterCommissionsDTO>();
		if(!ProfileParserUtils.isObjectEmpty(commissions)) {
			//commissions.sort(Comparator.comparing(RecruiterCommissionsEntity::getMonthYear));
			for(RecruiterCommissionsEntity commissionEntity:commissions) {
			RecruiterCommissionsDTO recruiterCommission= new RecruiterCommissionsDTO();
			BeanUtils.copyProperties(commissionEntity, recruiterCommission);
			recruiterCommission.setMonthYear(commissionEntity.getMonthYear().toString());
			recruiterCommission.setOrderDate(commissionEntity.getMonthYear());
			recruiterCommissions.add(recruiterCommission);
			logger.info("Processed search result...,{}", recruiterCommission.toString());
			}
			
			

		}
		return recruiterCommissions;
	}
	
	/**
	 * This method will load all commission lookup entries from database and return the list.
	 * @return
	 */
	public List<CommissionsLookupDTO> loadAllCommissionsLookupValues(){
		List<CommissionsLookupDTO> lookupDtoList= new ArrayList<CommissionsLookupDTO>();
		Iterable<CommissionsLookupEntity> entities=commissionsLookup.findAll();
		if(!ProfileParserUtils.isObjectEmpty(entities)) {
			for (CommissionsLookupEntity commissionObj : entities) {
				if(!ProfileParserUtils.isObjectEmpty(commissionObj)) {
					CommissionsLookupDTO dto= new CommissionsLookupDTO();
					BeanUtils.copyProperties(commissionObj, dto);
					lookupDtoList.add(dto);
					
				}
				
			}
		}
		
		return lookupDtoList;
		
	}
	
	public CommissionsLookupDTO saveCommissionLookup(CommissionsLookupDTO lookupObj) {
		if(!ProfileParserUtils.isObjectEmpty(lookupObj)) {
			CommissionsLookupEntity lookupEntity= new CommissionsLookupEntity();
			BeanUtils.copyProperties(lookupObj, lookupEntity);
			lookupEntity=commissionsLookup.save(lookupEntity);
		}
		return lookupObj;
	}
	
	/**
	 * This method will load List of commissions for the selected month year and recruiter name.
	 * @param monthAndYear
	 * @param recruiterName
	 * @return
	 */
	public List<CommissionDTO> loadCommissionDetails(String monthAndYear,String recruiterName){
		List<CommissionDTO> commissionsList=new ArrayList<CommissionDTO>();
		if(!ProfileParserUtils.isObjectEmpty(monthAndYear)&& !ProfileParserUtils.isObjectEmpty(recruiterName)) {
			List<CommissionsDetailsEntity> commissionsEntity=commissionsDetails.getCommissionsForSelectedMonthAndYear(monthAndYear,recruiterName);
			if(!ProfileParserUtils.isObjectEmpty(commissionsEntity)) {
				logger.info("Commissions exist....");
				for(CommissionsDetailsEntity commissionEntity:commissionsEntity) {
					CommissionDTO commissionDto= new CommissionDTO();
					BeanUtils.copyProperties(commissionEntity, commissionDto);
					commissionsList.add(commissionDto);
				}
			}
		}
		return commissionsList;
	}
}
