package com.renaissance.contractor.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.renaissance.contractor.dto.ContractorABNDetailsDTO;
import com.renaissance.contractor.dto.ContractorBankDetailsDTO;
import com.renaissance.contractor.dto.ContractorDetailsDTO;
import com.renaissance.contractor.dto.ContractorEmploymentDetailsDTO;
import com.renaissance.contractor.dto.ContractorPersonalDetailsDTO;
import com.renaissance.contractor.dto.ContractorRateDetailsDTO;
import com.renaissance.contractor.dto.ContractorSuperAnnuationDetailsDTO;
import com.renaissance.contractor.dto.ContractorTFNDetailsDTO;
import com.renaissance.contractor.model.ContractorABNDetailsEntity;
import com.renaissance.contractor.model.ContractorBankDetailsEntity;
import com.renaissance.contractor.model.ContractorEmploymentDetailsEntity;
import com.renaissance.contractor.model.ContractorPersonalDetailsEntity;
import com.renaissance.contractor.model.ContractorRateDetailsEntity;
import com.renaissance.contractor.model.ContractorSearchForm;
import com.renaissance.contractor.model.ContractorSearchResultsForm;
import com.renaissance.contractor.model.ContractorSuperAnnuationDetailsEntity;
import com.renaissance.contractor.model.ContractorTFNDetailsEntity;
import com.renaissance.contractor.repository.ContractorAbnDetailsRepository;
import com.renaissance.contractor.repository.ContractorBankDetailsRepository;
import com.renaissance.contractor.repository.ContractorEmploymentDetailsRepository;
import com.renaissance.contractor.repository.ContractorPersonalDetailsRepository;
import com.renaissance.contractor.repository.ContractorRateDetailsRepository;
import com.renaissance.contractor.repository.ContractorSuperAnnuationDetailsRepository;
import com.renaissance.contractor.repository.ContractorTfnDetailsRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class ContractorManagementService {
	private static final Logger logger = LoggerFactory.getLogger(ContractorManagementService.class);

	@Autowired
	ContractorPersonalDetailsRepository contractorPersonal;
	@Autowired
	ContractorAbnDetailsRepository contractorAbn;
	@Autowired
	ContractorTfnDetailsRepository contractorTfn;
	@Autowired
	ContractorBankDetailsRepository contractorBank;
	@Autowired
	ContractorRateDetailsRepository contractorRate;
	@Autowired
	ContractorSuperAnnuationDetailsRepository contractorSA;
	@Autowired
	ContractorEmploymentDetailsRepository contractorEmployment;

@Transactional
	public ContractorDetailsDTO createContractor(ContractorDetailsDTO contractorDetailsDto, String lastUpdatedUser)
			 throws Exception{
		// if(ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getPersonalDetails()))
		
		BigInteger createdId=null;
		try {
		logger.info("Create Contractor Service..,{}", contractorDetailsDto.getPersonalDetails().toString());
		logger.info("Create Contractor Service Bank details..,{}",
				contractorDetailsDto.getBankList().get(0).toString());
		logger.info("Create Contractor Service SA details..,{}",
				contractorDetailsDto.getSuperAnnuationList().get(0).toString());
		logger.info("Create Contractor Service Employment details..,{}",
				contractorDetailsDto.getEmployerList().get(0).toString());
		logger.info("Create Contractor Service Rate details..,{}",
				contractorDetailsDto.getRateList().get(0).toString());
		logger.info("Create Contractor Service ABN details..,{}", contractorDetailsDto.getAbnList().get(0).toString());
		logger.info("Create Contractor Service TFN details..,{}", contractorDetailsDto.getTfnList().get(0).toString());
		if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getPersonalDetails())) {
			ContractorDetailsDTO contractorDto = new ContractorDetailsDTO();
			ContractorPersonalDetailsDTO personalDto = contractorDetailsDto.getPersonalDetails();
			// New record
			if (!ProfileParserUtils.isObjectEmpty(personalDto)
					&& ProfileParserUtils.isObjectEmpty(personalDto.getContractorId())) {
				boolean abnHolder = false;
				ContractorPersonalDetailsEntity contractorPersonalVo = new ContractorPersonalDetailsEntity();
				ContractorABNDetailsEntity contractorAbnVo = new ContractorABNDetailsEntity();
				ContractorRateDetailsEntity contractorRateVo = new ContractorRateDetailsEntity();
				ContractorTFNDetailsEntity contractorTfnVo = new ContractorTFNDetailsEntity();
				ContractorSuperAnnuationDetailsEntity contractorSaVo = new ContractorSuperAnnuationDetailsEntity();
				ContractorBankDetailsEntity contractorBankVo = new ContractorBankDetailsEntity();
				ContractorEmploymentDetailsEntity contractorEmploymentVo = new ContractorEmploymentDetailsEntity();
				//check if contractor details exist for the same combination
				contractorPersonalVo=contractorPersonal.getContractors(personalDto.getFirstName(), personalDto.getLastName(), personalDto.getDateOfBirth(), personalDto.getPersonalEmail());
				if(!ProfileParserUtils.isObjectEmpty(contractorPersonalVo.getContractorId())){
					logger.error("Contractor exists, {}",contractorPersonalVo.getContractorId());
					throw new Exception("Contractor details are already exist. Please use Edit module to edit details.");
				}
				
				personalDto = populateAndSavePersonalDetails(personalDto, contractorPersonalVo, lastUpdatedUser);
				createdId=personalDto.getContractorId();

				if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase("true"))
					abnHolder = true;

				if (abnHolder) {
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getAbnList().get(0))) {

						ContractorABNDetailsDTO abnDto = contractorDetailsDto.getAbnList().get(0);
						abnDto.setContractorId(personalDto.getContractorId());
						abnDto = populateAndSaveABNDetails(abnDto, contractorAbnVo, lastUpdatedUser);
						List<ContractorABNDetailsDTO> abnList = new ArrayList<ContractorABNDetailsDTO>();
						abnList.add(abnDto);
						contractorDto.setAbnList(abnList);
					}
				} else {
					// TFN Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getTfnList().get(0))) {
						ContractorTFNDetailsDTO tfnDto = contractorDetailsDto.getTfnList().get(0);
						tfnDto.setContractorId(personalDto.getContractorId());
						tfnDto = populateAndSaveTFNDetails(tfnDto, contractorTfnVo, lastUpdatedUser);
						List<ContractorTFNDetailsDTO> tfnList = new ArrayList<ContractorTFNDetailsDTO>();
						tfnList.add(tfnDto);
						contractorDto.setTfnList(tfnList);

					}
					// Super Annuation Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getSuperAnnuationList().get(0))) {
						ContractorSuperAnnuationDetailsDTO saDto = contractorDetailsDto.getSuperAnnuationList().get(0);
						saDto.setContractorId(personalDto.getContractorId());
						saDto = populateAndSaveSADetails(saDto, contractorSaVo, lastUpdatedUser);
						List<ContractorSuperAnnuationDetailsDTO> saList = new ArrayList<ContractorSuperAnnuationDetailsDTO>();
						saList.add(saDto);
						contractorDto.setSuperAnnuationList(saList);

					}
				}

				// Bank Details
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getBankList().get(0))) {
					ContractorBankDetailsDTO bankDto = contractorDetailsDto.getBankList().get(0);
					bankDto.setContractorId(personalDto.getContractorId());
					bankDto = populateAndSaveBankDetails(bankDto, contractorBankVo, lastUpdatedUser);
					List<ContractorBankDetailsDTO> bankList = new ArrayList<ContractorBankDetailsDTO>();
					bankList.add(bankDto);
					contractorDto.setBankList(bankList);
				}

				// Employment Details
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList().get(0))) {
					ContractorEmploymentDetailsDTO employerDto = contractorDetailsDto.getEmployerList().get(0);
					employerDto.setContractorId(personalDto.getContractorId());
					employerDto = populateAndSaveEmploymentDetails(employerDto, contractorEmploymentVo,
							lastUpdatedUser);

					List<ContractorEmploymentDetailsDTO> empList = new ArrayList<ContractorEmploymentDetailsDTO>();
					empList.add(employerDto);
					contractorDto.setEmployerList(empList);
				}
				// Rate Details
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getRateList().get(0))) {
					ContractorRateDetailsDTO rateDto = contractorDetailsDto.getRateList().get(0);
					rateDto.setContractorId(personalDto.getContractorId());
					rateDto = populateAndSaveRateDetails(rateDto, contractorRateVo, lastUpdatedUser);

					List<ContractorRateDetailsDTO> rateList = new ArrayList<ContractorRateDetailsDTO>();
					rateList.add(rateDto);
					contractorDto.setRateList(rateList);
				}
				contractorDto.setPersonalDetails(personalDto);

				logger.info("Created ContractorId...{},", contractorPersonalVo.getContractorId());
				return contractorDto;
			}
		}
		}catch(Exception e) {
			logger.error("Error in creation..,{} ", e.getMessage());
			logger.error("Deleting newly created contractor in all tables..,{} ",createdId);
			if(createdId !=null) {// Record has been created. But other tables has created issue. So Delete the records.
				logger.error("Deleting newly created contractor in all tables..,{} ",createdId);
				contractorAbn.deleteByContractorId(createdId);
				contractorTfn.deleteByContractorId(createdId);
				contractorBank.deleteByContractorId(createdId);
				contractorSA.deleteByContractorId(createdId);
				contractorRate.deleteByContractorId(createdId);
				contractorEmployment.deleteByContractorId(createdId);
				contractorPersonal.deleteById(createdId);
				
				
			}
			throw new Exception(e.getMessage());
		}
		return null;
	}

	private ContractorPersonalDetailsDTO populateAndSavePersonalDetails(ContractorPersonalDetailsDTO personalDto,
			ContractorPersonalDetailsEntity contractorPersonalVo, String lastUpdatedUser) {
		BeanUtils.copyProperties(personalDto, contractorPersonalVo);
		contractorPersonalVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorPersonalVo.setLastUpdatedUser(lastUpdatedUser);
		contractorPersonalVo = contractorPersonal.save(contractorPersonalVo);
		BeanUtils.copyProperties(contractorPersonalVo, personalDto);
		return personalDto;
	}

	private ContractorABNDetailsDTO populateAndSaveABNDetails(ContractorABNDetailsDTO abnDto,
			ContractorABNDetailsEntity contractorAbnVo, String lastUpdatedUser) {

		BeanUtils.copyProperties(abnDto, contractorAbnVo);
		contractorAbnVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorAbnVo.setLastUpdatedUser(lastUpdatedUser);
		contractorAbnVo.setActiveRecord("Active");
		contractorAbnVo = contractorAbn.save(contractorAbnVo);
		BeanUtils.copyProperties(contractorAbnVo, abnDto);
		return abnDto;
	}

	private ContractorTFNDetailsDTO populateAndSaveTFNDetails(ContractorTFNDetailsDTO tfnDto,
			ContractorTFNDetailsEntity contractorTfnVo, String lastUpdatedUser) {
		BeanUtils.copyProperties(tfnDto, contractorTfnVo);
		contractorTfnVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorTfnVo.setLastUpdatedUser(lastUpdatedUser);
		contractorTfnVo.setActiveRecord("Active");
		contractorTfnVo = contractorTfn.save(contractorTfnVo);
		BeanUtils.copyProperties(contractorTfnVo, tfnDto);
		return tfnDto;
	}

	private ContractorSuperAnnuationDetailsDTO populateAndSaveSADetails(ContractorSuperAnnuationDetailsDTO saDto,
			ContractorSuperAnnuationDetailsEntity contractorSaVo, String lastUpdatedUser) {

		BeanUtils.copyProperties(saDto, contractorSaVo);
		contractorSaVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorSaVo.setLastUpdatedUser(lastUpdatedUser);
		contractorSaVo.setActiveRecord("Active");
		contractorSaVo = contractorSA.save(contractorSaVo);
		BeanUtils.copyProperties(contractorSaVo, saDto);

		return saDto;

	}

	private ContractorBankDetailsDTO populateAndSaveBankDetails(ContractorBankDetailsDTO bankDto,
			ContractorBankDetailsEntity contractorBankVo, String lastUpdatedUser) {
		BeanUtils.copyProperties(bankDto, contractorBankVo);
		contractorBankVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorBankVo.setLastUpdatedUser(lastUpdatedUser);
		contractorBankVo.setActiveRecord("Active");
		contractorBankVo = contractorBank.save(contractorBankVo);
		BeanUtils.copyProperties(contractorBankVo, bankDto);
		return bankDto;
	}

	private ContractorEmploymentDetailsDTO populateAndSaveEmploymentDetails(ContractorEmploymentDetailsDTO employerDto,
			ContractorEmploymentDetailsEntity contractorEmploymentVo, String lastUpdatedUser) {
		BeanUtils.copyProperties(employerDto, contractorEmploymentVo);
		contractorEmploymentVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorEmploymentVo.setLastUpdatedUser(lastUpdatedUser);
		contractorEmploymentVo.setActiveRecord("Active");
		contractorEmploymentVo = contractorEmployment.save(contractorEmploymentVo);
		BeanUtils.copyProperties(contractorEmploymentVo, employerDto);

		return employerDto;
	}

	private ContractorRateDetailsDTO populateAndSaveRateDetails(ContractorRateDetailsDTO rateDto,
			ContractorRateDetailsEntity contractorRateVo, String lastUpdatedUser) {
		BeanUtils.copyProperties(rateDto, contractorRateVo);
		contractorRateVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorRateVo.setLastUpdatedUser(lastUpdatedUser);
		contractorRateVo.setActiveRecord("Active");
		contractorRateVo = contractorRate.save(contractorRateVo);
		BeanUtils.copyProperties(contractorRateVo, rateDto);

		return rateDto;
	}
	
	
	public List<ContractorSearchResultsForm>getContractorSearchResults(ContractorSearchForm searchForm){
		
		List<ContractorPersonalDetailsEntity> personalDetails = contractorPersonal.searchContractors(searchForm.getContractorName());
		logger.info("Search Results personal table, {}", personalDetails.size());
		List<BigInteger> contractorIdList = new ArrayList<BigInteger>();
		List<ContractorSearchResultsForm> contractorSearchResults = new ArrayList<ContractorSearchResultsForm>();
		if (!ProfileParserUtils.isObjectEmpty(personalDetails)) {
			for (ContractorPersonalDetailsEntity personalEntity : personalDetails) {
				contractorIdList.add(personalEntity.getContractorId());

			}
		}
		List<ContractorEmploymentDetailsEntity> empDetails = contractorEmployment.searchEmploymentDetails(searchForm);
		logger.info("Search Results EMP table, {}", empDetails.size());
		//List<ContractorSearchResultsForm> contractorSearchResults = new ArrayList<ContractorSearchResultsForm>();
		if (!ProfileParserUtils.isObjectEmpty(empDetails)) {
			for (ContractorEmploymentDetailsEntity empEntity : empDetails) {
				contractorIdList.add(empEntity.getContractorId());

			}
		}
		
		logger.info("List size before.. ,{}, {}", contractorIdList.size(),contractorIdList);
		List<BigInteger> listWithoutDuplicates = new ArrayList<>(
			      new HashSet<>(contractorIdList));
		logger.info("List size after.. ,{}, {}", listWithoutDuplicates.size(),listWithoutDuplicates);
		for (BigInteger contractorId : listWithoutDuplicates) {
			ContractorSearchResultsForm resultForm= new ContractorSearchResultsForm();
			
			ContractorPersonalDetailsEntity personalEntity = contractorPersonal.getPersonalDetailsByContractorId(contractorId);
			if(!ProfileParserUtils.isObjectEmpty(personalEntity)) {
				BeanUtils.copyProperties(personalEntity, resultForm);
				if(!ProfileParserUtils.isObjectEmpty(resultForm.getAbnHolder()))
				{
					if(resultForm.getAbnHolder().equalsIgnoreCase("true"))
						resultForm.setAbnHolder("Yes");
					else
						resultForm.setAbnHolder("No");
				}
			}
			ContractorEmploymentDetailsEntity empEntity=contractorEmployment.getEmploymentDetailsByContractorId( contractorId);
			if(!ProfileParserUtils.isObjectEmpty(empEntity)) {
				BeanUtils.copyProperties(empEntity, resultForm);
			}
			
			ContractorRateDetailsEntity rateEntity=contractorRate.getRateDetailsByContractorId(contractorId);
			if(!ProfileParserUtils.isObjectEmpty(rateEntity)) {
				BeanUtils.copyProperties(rateEntity, resultForm);
			}
			
			logger.info("Result data...,{} ",resultForm.toString());
			contractorSearchResults.add(resultForm);
			/*
			 * ContractorDetailsDTO contractorDto=getContractorFullDetails(contractorId);
			 * if(!ProfileParserUtils.isObjectEmpty(contractorDto)) {
			 * ContractorSearchResultsForm resultForm= new ContractorSearchResultsForm();
			 * if(!ProfileParserUtils.isObjectEmpty(contractorDto.getPersonalDetails())) {
			 * BeanUtils.copyProperties(contractorDto.getPersonalDetails(), resultForm); }
			 * if(!ProfileParserUtils.isObjectEmpty(contractorDto.getEmployerList()) &&
			 * !ProfileParserUtils.isObjectEmpty(contractorDto.getEmployerList().get(0))) {
			 * BeanUtils.copyProperties(contractorDto.getEmployerList().get(0), resultForm);
			 * } if(!ProfileParserUtils.isObjectEmpty(contractorDto.getRateList()) &&
			 * !ProfileParserUtils.isObjectEmpty(contractorDto.getRateList().get(0))) {
			 * BeanUtils.copyProperties(contractorDto.getRateList().get(0), resultForm); }
			 * contractorSearchResults.add(resultForm); }
			 */
			//candidateProfiles.add(getCandidateFullDetails(candidateId));
			
		}
		return contractorSearchResults;
	}
	
	public ContractorDetailsDTO getContractorFullDetails(BigInteger contractorId) {
		
		
		
		return null;
		
	}
}
