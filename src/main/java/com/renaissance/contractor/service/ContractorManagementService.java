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
import com.renaissance.profile.parser.util.ProfileParserConstants;
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
			throws Exception {
		// if(ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getPersonalDetails()))

		BigInteger createdId = null;
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
			logger.info("Create Contractor Service ABN details..,{}",
					contractorDetailsDto.getAbnList().get(0).toString());
			logger.info("Create Contractor Service TFN details..,{}",
					contractorDetailsDto.getTfnList().get(0).toString());
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
					// check if contractor details exist for the same combination
					List<ContractorPersonalDetailsEntity> personalList = contractorPersonal.getContractors(
							personalDto.getFirstName(), personalDto.getLastName(), personalDto.getDateOfBirth(),
							personalDto.getPersonalEmail());

					if (!ProfileParserUtils.isObjectEmpty(personalList) && personalList.size() > 0) {
						// logger.info("Personal list matching records...,{}",personalList.size());
						contractorPersonalVo = personalList.get(0);
					}
					if (!ProfileParserUtils.isObjectEmpty(contractorPersonalVo.getContractorId())) {
						logger.error("Contractor exists, {}", contractorPersonalVo.getContractorId());
						throw new Exception(
								"Contractor details are already exist. Please use Edit module to edit details.");
					}

					personalDto = populateAndSavePersonalDetails(personalDto, contractorPersonalVo, lastUpdatedUser);
					createdId = personalDto.getContractorId();

					if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase("true"))
						abnHolder = true;

					if (abnHolder) {
						if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getAbnList().get(0))) {

							ContractorABNDetailsDTO abnDto = contractorDetailsDto.getAbnList().get(0);
							abnDto.setContractorId(personalDto.getContractorId());
							abnDto = populateAndSaveABNDetails(abnDto, contractorAbnVo, lastUpdatedUser,
									ProfileParserConstants.ACTIVE);
							List<ContractorABNDetailsDTO> abnList = new ArrayList<ContractorABNDetailsDTO>();
							abnList.add(abnDto);
							contractorDto.setAbnList(abnList);
						}
					} else {
						// TFN Details
						if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getTfnList().get(0))) {
							ContractorTFNDetailsDTO tfnDto = contractorDetailsDto.getTfnList().get(0);
							tfnDto.setContractorId(personalDto.getContractorId());
							tfnDto = populateAndSaveTFNDetails(tfnDto, contractorTfnVo, lastUpdatedUser,
									ProfileParserConstants.ACTIVE);
							List<ContractorTFNDetailsDTO> tfnList = new ArrayList<ContractorTFNDetailsDTO>();
							tfnList.add(tfnDto);
							contractorDto.setTfnList(tfnList);

						}
						// Super Annuation Details
						if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getSuperAnnuationList().get(0))) {
							ContractorSuperAnnuationDetailsDTO saDto = contractorDetailsDto.getSuperAnnuationList()
									.get(0);
							saDto.setContractorId(personalDto.getContractorId());
							saDto = populateAndSaveSADetails(saDto, contractorSaVo, lastUpdatedUser,
									ProfileParserConstants.ACTIVE);
							List<ContractorSuperAnnuationDetailsDTO> saList = new ArrayList<ContractorSuperAnnuationDetailsDTO>();
							saList.add(saDto);
							contractorDto.setSuperAnnuationList(saList);

						}
					}

					// Bank Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getBankList().get(0))) {
						ContractorBankDetailsDTO bankDto = contractorDetailsDto.getBankList().get(0);
						bankDto.setContractorId(personalDto.getContractorId());
						bankDto = populateAndSaveBankDetails(bankDto, contractorBankVo, lastUpdatedUser,
								ProfileParserConstants.ACTIVE);
						List<ContractorBankDetailsDTO> bankList = new ArrayList<ContractorBankDetailsDTO>();
						bankList.add(bankDto);
						contractorDto.setBankList(bankList);
					}

					// Employment Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList().get(0))) {
						ContractorEmploymentDetailsDTO employerDto = contractorDetailsDto.getEmployerList().get(0);
						employerDto.setContractorId(personalDto.getContractorId());
						employerDto = populateAndSaveEmploymentDetails(employerDto, contractorEmploymentVo,
								lastUpdatedUser, ProfileParserConstants.ACTIVE);

						List<ContractorEmploymentDetailsDTO> empList = new ArrayList<ContractorEmploymentDetailsDTO>();
						empList.add(employerDto);
						contractorDto.setEmployerList(empList);
					}
					// Rate Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getRateList().get(0))) {
						ContractorRateDetailsDTO rateDto = contractorDetailsDto.getRateList().get(0);
						rateDto.setContractorId(personalDto.getContractorId());
						rateDto = populateAndSaveRateDetails(rateDto, contractorRateVo, lastUpdatedUser,
								ProfileParserConstants.ACTIVE);

						List<ContractorRateDetailsDTO> rateList = new ArrayList<ContractorRateDetailsDTO>();
						rateList.add(rateDto);
						contractorDto.setRateList(rateList);
					}
					contractorDto.setPersonalDetails(personalDto);

					logger.info("Created ContractorId...{},", contractorPersonalVo.getContractorId());
					return contractorDto;
				}
			}
		} catch (Exception e) {
			logger.error("Error in creation..,{} ", e.getMessage());
			logger.error("Deleting newly created contractor in all tables..,{} ", createdId);
			if (createdId != null) {// Record has been created. But other tables has created issue. So Delete the
									// records.
				logger.error("Deleting newly created contractor in all tables..,{} ", createdId);
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
			ContractorABNDetailsEntity contractorAbnVo, String lastUpdatedUser, String status) {

		BeanUtils.copyProperties(abnDto, contractorAbnVo);
		contractorAbnVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorAbnVo.setLastUpdatedUser(lastUpdatedUser);
		contractorAbnVo.setActiveRecord(status);
		contractorAbnVo = contractorAbn.save(contractorAbnVo);
		BeanUtils.copyProperties(contractorAbnVo, abnDto);
		return abnDto;
	}

	private ContractorTFNDetailsDTO populateAndSaveTFNDetails(ContractorTFNDetailsDTO tfnDto,
			ContractorTFNDetailsEntity contractorTfnVo, String lastUpdatedUser, String status) {
		BeanUtils.copyProperties(tfnDto, contractorTfnVo);
		contractorTfnVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorTfnVo.setLastUpdatedUser(lastUpdatedUser);
		contractorTfnVo.setActiveRecord(status);
		contractorTfnVo = contractorTfn.save(contractorTfnVo);
		BeanUtils.copyProperties(contractorTfnVo, tfnDto);
		return tfnDto;
	}

	private ContractorSuperAnnuationDetailsDTO populateAndSaveSADetails(ContractorSuperAnnuationDetailsDTO saDto,
			ContractorSuperAnnuationDetailsEntity contractorSaVo, String lastUpdatedUser, String status) {

		BeanUtils.copyProperties(saDto, contractorSaVo);
		contractorSaVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorSaVo.setLastUpdatedUser(lastUpdatedUser);
		contractorSaVo.setActiveRecord(status);
		contractorSaVo = contractorSA.save(contractorSaVo);
		BeanUtils.copyProperties(contractorSaVo, saDto);

		return saDto;

	}

	private ContractorBankDetailsDTO populateAndSaveBankDetails(ContractorBankDetailsDTO bankDto,
			ContractorBankDetailsEntity contractorBankVo, String lastUpdatedUser, String status) {
		BeanUtils.copyProperties(bankDto, contractorBankVo);
		contractorBankVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorBankVo.setLastUpdatedUser(lastUpdatedUser);
		contractorBankVo.setActiveRecord(status);
		contractorBankVo = contractorBank.save(contractorBankVo);
		BeanUtils.copyProperties(contractorBankVo, bankDto);
		return bankDto;
	}

	private ContractorEmploymentDetailsDTO populateAndSaveEmploymentDetails(ContractorEmploymentDetailsDTO employerDto,
			ContractorEmploymentDetailsEntity contractorEmploymentVo, String lastUpdatedUser, String status) {
		BeanUtils.copyProperties(employerDto, contractorEmploymentVo);
		contractorEmploymentVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorEmploymentVo.setLastUpdatedUser(lastUpdatedUser);
		contractorEmploymentVo.setActiveRecord(status);
		contractorEmploymentVo = contractorEmployment.save(contractorEmploymentVo);
		BeanUtils.copyProperties(contractorEmploymentVo, employerDto);

		return employerDto;
	}

	private ContractorRateDetailsDTO populateAndSaveRateDetails(ContractorRateDetailsDTO rateDto,
			ContractorRateDetailsEntity contractorRateVo, String lastUpdatedUser, String status) {
		BeanUtils.copyProperties(rateDto, contractorRateVo);
		contractorRateVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorRateVo.setLastUpdatedUser(lastUpdatedUser);
		contractorRateVo.setActiveRecord(status);
		contractorRateVo = contractorRate.save(contractorRateVo);
		BeanUtils.copyProperties(contractorRateVo, rateDto);

		return rateDto;
	}

	public List<ContractorSearchResultsForm> getContractorSearchResults(ContractorSearchForm searchForm) {

		List<ContractorPersonalDetailsEntity> personalDetails = contractorPersonal
				.searchContractors(searchForm.getContractorName());
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
		// List<ContractorSearchResultsForm> contractorSearchResults = new
		// ArrayList<ContractorSearchResultsForm>();
		if (!ProfileParserUtils.isObjectEmpty(empDetails)) {
			for (ContractorEmploymentDetailsEntity empEntity : empDetails) {
				contractorIdList.add(empEntity.getContractorId());

			}
		}

		logger.info("List size before.. ,{}, {}", contractorIdList.size(), contractorIdList);
		List<BigInteger> listWithoutDuplicates = new ArrayList<>(new HashSet<>(contractorIdList));
		logger.info("List size after.. ,{}, {}", listWithoutDuplicates.size(), listWithoutDuplicates);
		for (BigInteger contractorId : listWithoutDuplicates) {
			ContractorSearchResultsForm resultForm = new ContractorSearchResultsForm();

			ContractorPersonalDetailsEntity personalEntity = contractorPersonal
					.getPersonalDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(personalEntity)) {
				BeanUtils.copyProperties(personalEntity, resultForm);
				if (!ProfileParserUtils.isObjectEmpty(resultForm.getAbnHolder())) {
					if (resultForm.getAbnHolder().equalsIgnoreCase("true"))
						resultForm.setAbnHolder("Yes");
					else
						resultForm.setAbnHolder("No");
				}

			}
			ContractorEmploymentDetailsEntity empEntity = contractorEmployment
					.getEmploymentDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(empEntity)
					&& resultForm.getContractorId().equals(empEntity.getContractorId())) {
				BeanUtils.copyProperties(empEntity, resultForm);

			}

			ContractorRateDetailsEntity rateEntity = contractorRate.getRateDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(rateEntity)
					&& resultForm.getContractorId().equals(rateEntity.getContractorId())) {
				BeanUtils.copyProperties(rateEntity, resultForm);

			}

			logger.info("Result data...,{} ", resultForm.toString());
			contractorSearchResults.add(resultForm);

		}
		return contractorSearchResults;
	}

	public ContractorDetailsDTO getContractorFullDetails(BigInteger contractorId) {
		ContractorDetailsDTO contractorDto = new ContractorDetailsDTO();
boolean abnHolder=false;
		ContractorPersonalDetailsEntity personalEntity = contractorPersonal
				.getPersonalDetailsByContractorId(contractorId);
		if (!ProfileParserUtils.isObjectEmpty(personalEntity)) {
			ContractorPersonalDetailsDTO personalDto = new ContractorPersonalDetailsDTO();
			BeanUtils.copyProperties(personalEntity, personalDto);
			contractorDto.setPersonalDetails(personalDto);
			if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase("true"))
				abnHolder = true;

		}
		ContractorEmploymentDetailsEntity empEntity = contractorEmployment
				.getEmploymentDetailsByContractorId(contractorId);
		ContractorEmploymentDetailsDTO empDto = new ContractorEmploymentDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(empEntity)) {

			BeanUtils.copyProperties(empEntity, empDto);

		}
		List<ContractorEmploymentDetailsDTO> empList = new ArrayList<ContractorEmploymentDetailsDTO>();
		empList.add(empDto);
		contractorDto.setEmployerList(empList);
		ContractorBankDetailsEntity bankEntity = contractorBank.getBankDetailsByContractorId(contractorId);
		ContractorBankDetailsDTO bankDto = new ContractorBankDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(bankEntity)) {

			BeanUtils.copyProperties(bankEntity, bankDto);

		}
		List<ContractorBankDetailsDTO> bankList = new ArrayList<ContractorBankDetailsDTO>();
		bankList.add(bankDto);
		contractorDto.setBankList(bankList);
		
		ContractorRateDetailsEntity rateEntity = contractorRate.getRateDetailsByContractorId(contractorId);
		ContractorRateDetailsDTO rateDto = new ContractorRateDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(rateEntity)) {
			BeanUtils.copyProperties(rateEntity, rateDto);

		}
		List<ContractorRateDetailsDTO> rateList = new ArrayList<ContractorRateDetailsDTO>();
		rateList.add(rateDto);
		contractorDto.setRateList(rateList);
		ContractorSuperAnnuationDetailsEntity saEntity = contractorSA.getSADetailsByContractorId(contractorId);
		ContractorSuperAnnuationDetailsDTO saDto = new ContractorSuperAnnuationDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(saEntity)) {

			BeanUtils.copyProperties(saEntity, saDto);

		}
		List<ContractorSuperAnnuationDetailsDTO> saList = new ArrayList<ContractorSuperAnnuationDetailsDTO>();
		saList.add(saDto);
		contractorDto.setSuperAnnuationList(saList);
		
		if(abnHolder) {
		ContractorABNDetailsEntity abnEntity = contractorAbn.getAbnDetailsByContractorId(contractorId);
		ContractorABNDetailsDTO abnDto = new ContractorABNDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(abnEntity)) {
			BeanUtils.copyProperties(abnEntity, abnDto);
		}
		List<ContractorABNDetailsDTO> abnList = new ArrayList<ContractorABNDetailsDTO>();
		abnList.add(abnDto);
		contractorDto.setAbnList(abnList);
		}
		else {
		
		logger.info("Contractor id.. before tfn call..,{}",contractorId);
		ContractorTFNDetailsEntity tfnEntity = contractorTfn.getAllTfnDetailsByContractorId(contractorId).get(0);
		ContractorTFNDetailsDTO tfnDto = new ContractorTFNDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(tfnEntity)) {
			BeanUtils.copyProperties(tfnEntity, tfnDto);

		}
		List<ContractorTFNDetailsDTO> tfnList = new ArrayList<ContractorTFNDetailsDTO>();
		tfnList.add(tfnDto);
		contractorDto.setTfnList(tfnList);
		}
		
		return contractorDto;

	}

	@Transactional
	public ContractorDetailsDTO updateContractorDetails(ContractorDetailsDTO contractorDetailsDto,
			String lastUpdatedUser) throws Exception {

		// update Personal details
		ContractorDetailsDTO contractorDto = new ContractorDetailsDTO();
		ContractorPersonalDetailsDTO personalDto = contractorDetailsDto.getPersonalDetails();
		if (!ProfileParserUtils.isObjectEmpty(personalDto)
				&& !ProfileParserUtils.isObjectEmpty(personalDto.getContractorId())) {
			boolean abnHolder = false;
			ContractorPersonalDetailsEntity contractorPersonalVo = new ContractorPersonalDetailsEntity();

			// check if contractor details exist for the same combination
			List<ContractorPersonalDetailsEntity> personalList = contractorPersonal.getContractors(
					personalDto.getFirstName(), personalDto.getLastName(), personalDto.getDateOfBirth(),
					personalDto.getPersonalEmail());
			if (!ProfileParserUtils.isObjectEmpty(personalList) && personalList.size() > 0) {
				// contractorPersonalVo=personalList.get(0);
				logger.info("checking Contractor id...,{}", personalDto.getContractorId());
				for (ContractorPersonalDetailsEntity personalVo : personalList) {
					logger.info("matching details check...,{}", personalVo.toString());
					if (!ProfileParserUtils.isObjectEmpty(personalVo.getContractorId())
							&& !personalVo.getContractorId().equals(personalDto.getContractorId())) {
						logger.error("Contractor exists, {},  {}", personalVo.getContractorId(),
								personalDto.getContractorId());
						throw new Exception(
								"Contractor details are already exist. Please give unique details to edit.");
					}
				}
			}

			logger.info("No matching details... hence saving..,{}", personalDto.toString());
			if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase("true"))
				abnHolder = true;
			personalDto = populateAndSavePersonalDetails(personalDto, contractorPersonalVo, lastUpdatedUser);
			contractorDto.setPersonalDetails(personalDto);
			
			// Update previous dependent records with Active status as "Inactive" and then update this
			// as new Active record
			if (abnHolder) {
				// Update ABN
				List<ContractorABNDetailsEntity> abnList = contractorAbn
						.getAllAbnDetailsByContractorId(personalDto.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(abnList) && abnList.size() > 0) {
					for (ContractorABNDetailsEntity abnVo : abnList) {
						ContractorABNDetailsDTO abnDto = new ContractorABNDetailsDTO();
						BeanUtils.copyProperties(abnVo, abnDto);

						 populateAndSaveABNDetails(abnDto, abnVo, lastUpdatedUser,
								ProfileParserConstants.INACTIVE);

					}

				}
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getAbnList().get(0))) {

					ContractorABNDetailsDTO contractorAbnDto = contractorDetailsDto.getAbnList().get(0);
					ContractorABNDetailsEntity contractorAbnVo = new ContractorABNDetailsEntity();
					contractorAbnDto.setContractorId(personalDto.getContractorId());

					contractorAbnDto = populateAndSaveABNDetails(contractorAbnDto, contractorAbnVo, lastUpdatedUser,
							ProfileParserConstants.ACTIVE);
					List<ContractorABNDetailsDTO> abnDtoList = new ArrayList<ContractorABNDetailsDTO>();
					abnDtoList.add(contractorAbnDto);
					contractorDto.setAbnList(abnDtoList);
				}

			} else {
				// update TFN
				List<ContractorTFNDetailsEntity> tfnList = contractorTfn
						.getAllTfnDetailsByContractorId(personalDto.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(tfnList) && tfnList.size() > 0) {
					for (ContractorTFNDetailsEntity tfnVo : tfnList) {
						ContractorTFNDetailsDTO tfnDto = new ContractorTFNDetailsDTO();
						BeanUtils.copyProperties(tfnVo, tfnDto);

						 populateAndSaveTFNDetails(tfnDto, tfnVo, lastUpdatedUser,
								ProfileParserConstants.INACTIVE);

					}

				}
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getTfnList().get(0))) {

					ContractorTFNDetailsDTO contractorTfnDto = contractorDetailsDto.getTfnList().get(0);
					ContractorTFNDetailsEntity contractorTfnVo = new ContractorTFNDetailsEntity();
					contractorTfnDto.setContractorId(personalDto.getContractorId());

					contractorTfnDto = populateAndSaveTFNDetails(contractorTfnDto, contractorTfnVo, lastUpdatedUser,
							ProfileParserConstants.ACTIVE);
					List<ContractorTFNDetailsDTO> tfnDtoList = new ArrayList<ContractorTFNDetailsDTO>();
					tfnDtoList.add(contractorTfnDto);
					contractorDto.setTfnList(tfnDtoList);
				}

				// update SA
				List<ContractorSuperAnnuationDetailsEntity> saList = contractorSA
						.getAllSADetailsByContractorId(personalDto.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(saList) && saList.size() > 0) {
					for (ContractorSuperAnnuationDetailsEntity saVo : saList) {
						ContractorSuperAnnuationDetailsDTO saDto = new ContractorSuperAnnuationDetailsDTO();
						BeanUtils.copyProperties(saVo, saDto);

						 populateAndSaveSADetails(saDto, saVo, lastUpdatedUser,
								ProfileParserConstants.INACTIVE);

					}

				}
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getSuperAnnuationList().get(0))) {

					ContractorSuperAnnuationDetailsDTO contractorSaDto = contractorDetailsDto.getSuperAnnuationList().get(0);
					ContractorSuperAnnuationDetailsEntity contractorSaVo = new ContractorSuperAnnuationDetailsEntity();
					contractorSaDto.setContractorId(personalDto.getContractorId());

					contractorSaDto = populateAndSaveSADetails(contractorSaDto, contractorSaVo, lastUpdatedUser,
							ProfileParserConstants.ACTIVE);
					List<ContractorSuperAnnuationDetailsDTO> saDtoList = new ArrayList<ContractorSuperAnnuationDetailsDTO>();
					saDtoList.add(contractorSaDto);
					contractorDto.setSuperAnnuationList(saDtoList);
				}
			}

			// update Bank
			List<ContractorBankDetailsEntity> bankList = contractorBank
					.getAllBankDetailsByContractorId(personalDto.getContractorId());
			if (!ProfileParserUtils.isObjectEmpty(bankList) && bankList.size() > 0) {
				for (ContractorBankDetailsEntity bankVo : bankList) {
					ContractorBankDetailsDTO bankDto = new ContractorBankDetailsDTO();
					BeanUtils.copyProperties(bankVo, bankDto);

					 populateAndSaveBankDetails(bankDto, bankVo, lastUpdatedUser,
							ProfileParserConstants.INACTIVE);

				}

			}
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getBankList().get(0))) {

				ContractorBankDetailsDTO contractorBankDto = contractorDetailsDto.getBankList().get(0);
				ContractorBankDetailsEntity contractorBankVo = new ContractorBankDetailsEntity();
				contractorBankDto.setContractorId(personalDto.getContractorId());

				contractorBankDto = populateAndSaveBankDetails(contractorBankDto, contractorBankVo, lastUpdatedUser,
						ProfileParserConstants.ACTIVE);
				List<ContractorBankDetailsDTO> bankDtoList = new ArrayList<ContractorBankDetailsDTO>();
				bankDtoList.add(contractorBankDto);
				contractorDto.setBankList(bankDtoList);
			}

			// update Employer

			List<ContractorEmploymentDetailsEntity> empList = contractorEmployment
					.getAllEmploymentDetailsByContractorId(personalDto.getContractorId());
			if (!ProfileParserUtils.isObjectEmpty(bankList) && bankList.size() > 0) {
				for (ContractorEmploymentDetailsEntity empVo : empList) {
					ContractorEmploymentDetailsDTO empDto = new ContractorEmploymentDetailsDTO();
					BeanUtils.copyProperties(empVo, empDto);

					 populateAndSaveEmploymentDetails(empDto, empVo, lastUpdatedUser,
							ProfileParserConstants.INACTIVE);

				}

			}
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList().get(0))) {

				ContractorEmploymentDetailsDTO contractorEmpDto = contractorDetailsDto.getEmployerList().get(0);
				ContractorEmploymentDetailsEntity contractorEmpVo = new ContractorEmploymentDetailsEntity();
				contractorEmpDto.setContractorId(personalDto.getContractorId());

				contractorEmpDto = populateAndSaveEmploymentDetails(contractorEmpDto, contractorEmpVo, lastUpdatedUser,
						ProfileParserConstants.ACTIVE);
				List<ContractorEmploymentDetailsDTO> empDtoList = new ArrayList<ContractorEmploymentDetailsDTO>();
				empDtoList.add(contractorEmpDto);
				contractorDto.setEmployerList(empDtoList);
			}
			
			// update Rate
			
			List<ContractorRateDetailsEntity> rateList = contractorRate
					.getAllRateDetailsByContractorId(personalDto.getContractorId());
			if (!ProfileParserUtils.isObjectEmpty(rateList) && rateList.size() > 0) {
				for (ContractorRateDetailsEntity rateVo : rateList) {
					ContractorRateDetailsDTO rateDto = new ContractorRateDetailsDTO();
					BeanUtils.copyProperties(rateVo, rateDto);

					 populateAndSaveRateDetails(rateDto, rateVo, lastUpdatedUser,
							ProfileParserConstants.INACTIVE);

				}

			}
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList().get(0))) {

				ContractorRateDetailsDTO contractorRateDto = contractorDetailsDto.getRateList().get(0);
				ContractorRateDetailsEntity contractorRateVo = new ContractorRateDetailsEntity();
				contractorRateDto.setContractorId(personalDto.getContractorId());

				contractorRateDto = populateAndSaveRateDetails(contractorRateDto, contractorRateVo, lastUpdatedUser,
						ProfileParserConstants.ACTIVE);
				List<ContractorRateDetailsDTO> rateDtoList = new ArrayList<ContractorRateDetailsDTO>();
				rateDtoList.add(contractorRateDto);
				contractorDto.setRateList(rateDtoList);
			}
		}
		
		return contractorDetailsDto;
	}

}
