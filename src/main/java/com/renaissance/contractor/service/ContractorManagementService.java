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

	/**
	 * This method will create contractor along with all other dependent data. If
	 * there is any error in saving dependent details, then it deletes all temporary
	 * data and throws exception.
	 * 
	 * @param contractorDetailsDto
	 * @param lastUpdatedUser
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ContractorDetailsDTO createContractor(ContractorDetailsDTO contractorDetailsDto, String lastUpdatedUser)
			throws Exception {
		BigInteger createdId = null;
		try {
			logger.info("Create Contractor Service..,{}", contractorDetailsDto.getPersonalDetails().toString());
			logger.info("Create Contractor Service Bank details..,{}", contractorDetailsDto.getBankList().toString());
			logger.info("Create Contractor Service SA details..,{}",
					contractorDetailsDto.getSuperAnnuationList().toString());
			logger.info("Create Contractor Service Employment details..,{}",
					contractorDetailsDto.getEmployerList().toString());
			logger.info("Create Contractor Service Rate details..,{}", contractorDetailsDto.getRateList().toString());
			logger.info("Create Contractor Service ABN details..,{}", contractorDetailsDto.getAbnList().toString());
			logger.info("Create Contractor Service TFN details..,{}", contractorDetailsDto.getTfnList().toString());
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getPersonalDetails())) {
				ContractorDetailsDTO contractorDto = new ContractorDetailsDTO();
				ContractorPersonalDetailsDTO personalDto = contractorDetailsDto.getPersonalDetails();
				// New record

				if (!ProfileParserUtils.isObjectEmpty(personalDto)
						&& ProfileParserUtils.isObjectEmpty(personalDto.getContractorId())) {
					boolean abnHolder = false;

					// check if contractor details exist for the same combination
					List<ContractorPersonalDetailsEntity> personalList = contractorPersonal.getContractors(
							personalDto.getFirstName(), personalDto.getLastName(), personalDto.getDateOfBirth(),
							personalDto.getPersonalEmail());

					if (!ProfileParserUtils.isObjectEmpty(personalList) && personalList.size() > 0) {

						for (ContractorPersonalDetailsEntity personalVo : personalList) {
							if (!ProfileParserUtils.isObjectEmpty(personalVo.getContractorId())
									&& !personalVo.getContractorId().equals(personalDto.getContractorId())) {

								throw new Exception(
										"Contractor details are already exist. Please use Edit module to edit details.");
							}
						}
					}
					// Personal details are not duplicate, hence saving...

					personalDto = populateAndSavePersonalDetails(personalDto, lastUpdatedUser);
					createdId = personalDto.getContractorId();

					if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase(ProfileParserConstants.TRUE))
						abnHolder = true;

					if (abnHolder) {
						if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getAbnList())) {

							ContractorABNDetailsDTO abnDto = contractorDetailsDto.getAbnList();
							abnDto.setContractorId(personalDto.getContractorId());
							abnDto = populateAndSaveABNDetails(abnDto, lastUpdatedUser, ProfileParserConstants.ACTIVE);
							contractorDto.setAbnList(abnDto);
						}
					} else {
						// TFN Details
						if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getTfnList())) {
							ContractorTFNDetailsDTO tfnDto = contractorDetailsDto.getTfnList();
							tfnDto.setContractorId(personalDto.getContractorId());
							tfnDto = populateAndSaveTFNDetails(tfnDto, lastUpdatedUser, ProfileParserConstants.ACTIVE);
							contractorDto.setTfnList(tfnDto);

						}
						// Super Annuation Details
						if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getSuperAnnuationList())) {
							ContractorSuperAnnuationDetailsDTO saDto = contractorDetailsDto.getSuperAnnuationList();
							saDto.setContractorId(personalDto.getContractorId());
							saDto = populateAndSaveSADetails(saDto, lastUpdatedUser, ProfileParserConstants.ACTIVE);
							contractorDto.setSuperAnnuationList(saDto);

						}
					}

					// Bank Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getBankList())) {
						ContractorBankDetailsDTO bankDto = contractorDetailsDto.getBankList();
						bankDto.setContractorId(personalDto.getContractorId());
						bankDto = populateAndSaveBankDetails(bankDto, lastUpdatedUser, ProfileParserConstants.ACTIVE);
						contractorDto.setBankList(bankDto);
					}

					// Employment Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList())) {
						ContractorEmploymentDetailsDTO employerDto = contractorDetailsDto.getEmployerList();
						employerDto.setContractorId(personalDto.getContractorId());
						employerDto = populateAndSaveEmploymentDetails(employerDto, lastUpdatedUser,
								ProfileParserConstants.ACTIVE);
						contractorDto.setEmployerList(employerDto);
					}
					// Rate Details
					if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getRateList())) {
						ContractorRateDetailsDTO rateDto = contractorDetailsDto.getRateList();
						rateDto.setContractorId(personalDto.getContractorId());
						rateDto = populateAndSaveRateDetails(rateDto, lastUpdatedUser, ProfileParserConstants.ACTIVE);
						contractorDto.setRateList(rateDto);
					}
					contractorDto.setPersonalDetails(personalDto);

					logger.info("Created ContractorId...{},", contractorDto.getPersonalDetails().getContractorId());
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

	/**
	 * This method will populate Personal details from to DTO to VO and save to DB.
	 * 
	 * @param personalDto
	 * @param lastUpdatedUser
	 * @return
	 */

	private ContractorPersonalDetailsDTO populateAndSavePersonalDetails(ContractorPersonalDetailsDTO personalDto,
			String lastUpdatedUser) {
		ContractorPersonalDetailsEntity contractorPersonalVo = new ContractorPersonalDetailsEntity();
		BeanUtils.copyProperties(personalDto, contractorPersonalVo);
		contractorPersonalVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorPersonalVo.setLastUpdatedUser(lastUpdatedUser);
		contractorPersonalVo = contractorPersonal.save(contractorPersonalVo);
		BeanUtils.copyProperties(contractorPersonalVo, personalDto);
		return personalDto;
	}

	/**
	 * This method will populate ABN details from to DTO to VO and save to DB.
	 * 
	 * @param abnDto
	 * @param lastUpdatedUser
	 * @param status
	 * @return
	 */

	private ContractorABNDetailsDTO populateAndSaveABNDetails(ContractorABNDetailsDTO abnDto, String lastUpdatedUser,
			String status) {

		ContractorABNDetailsEntity contractorAbnVo = new ContractorABNDetailsEntity();
		BeanUtils.copyProperties(abnDto, contractorAbnVo);
		contractorAbnVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorAbnVo.setLastUpdatedUser(lastUpdatedUser);
		contractorAbnVo.setActiveRecord(status);
		contractorAbnVo = contractorAbn.save(contractorAbnVo);
		BeanUtils.copyProperties(contractorAbnVo, abnDto);
		return abnDto;
	}

	/**
	 * This method will populate TFN details from to DTO to VO and save to DB.
	 * 
	 * @param tfnDto
	 * @param lastUpdatedUser
	 * @param status
	 * @return
	 */
	private ContractorTFNDetailsDTO populateAndSaveTFNDetails(ContractorTFNDetailsDTO tfnDto, String lastUpdatedUser,
			String status) {
		ContractorTFNDetailsEntity contractorTfnVo = new ContractorTFNDetailsEntity();
		BeanUtils.copyProperties(tfnDto, contractorTfnVo);
		contractorTfnVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorTfnVo.setLastUpdatedUser(lastUpdatedUser);
		contractorTfnVo.setActiveRecord(status);
		contractorTfnVo = contractorTfn.save(contractorTfnVo);
		logger.info("TFN Update...,{} ", contractorTfnVo.toString());
		BeanUtils.copyProperties(contractorTfnVo, tfnDto);
		return tfnDto;
	}

	/**
	 * This method will populate Super annuation details from to DTO to VO and save
	 * to DB.
	 * 
	 * @param saDto
	 * @param lastUpdatedUser
	 * @param status
	 * @return
	 */
	private ContractorSuperAnnuationDetailsDTO populateAndSaveSADetails(ContractorSuperAnnuationDetailsDTO saDto,
			String lastUpdatedUser, String status) {

		ContractorSuperAnnuationDetailsEntity contractorSaVo = new ContractorSuperAnnuationDetailsEntity();
		BeanUtils.copyProperties(saDto, contractorSaVo);
		contractorSaVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorSaVo.setLastUpdatedUser(lastUpdatedUser);
		contractorSaVo.setActiveRecord(status);
		contractorSaVo = contractorSA.save(contractorSaVo);
		BeanUtils.copyProperties(contractorSaVo, saDto);

		return saDto;

	}

	/**
	 * This method will populate Bank details from DTO to VO and save to DB.
	 * 
	 * @param bankDto
	 * @param lastUpdatedUser
	 * @param status
	 * @return
	 */
	private ContractorBankDetailsDTO populateAndSaveBankDetails(ContractorBankDetailsDTO bankDto,
			String lastUpdatedUser, String status) {
		ContractorBankDetailsEntity contractorBankVo = new ContractorBankDetailsEntity();
		BeanUtils.copyProperties(bankDto, contractorBankVo);
		contractorBankVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorBankVo.setLastUpdatedUser(lastUpdatedUser);
		contractorBankVo.setActiveRecord(status);
		contractorBankVo = contractorBank.save(contractorBankVo);
		BeanUtils.copyProperties(contractorBankVo, bankDto);
		return bankDto;
	}

	/**
	 * This method will populate employer details from to DTO to VO and save to DB.
	 * 
	 * @param employerDto
	 * @param lastUpdatedUser
	 * @param status
	 * @return
	 */
	private ContractorEmploymentDetailsDTO populateAndSaveEmploymentDetails(ContractorEmploymentDetailsDTO employerDto,
			String lastUpdatedUser, String status) {
		ContractorEmploymentDetailsEntity contractorEmploymentVo = new ContractorEmploymentDetailsEntity();
		BeanUtils.copyProperties(employerDto, contractorEmploymentVo);
		contractorEmploymentVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorEmploymentVo.setLastUpdatedUser(lastUpdatedUser);
		contractorEmploymentVo.setActiveRecord(status);
		contractorEmploymentVo = contractorEmployment.save(contractorEmploymentVo);
		BeanUtils.copyProperties(contractorEmploymentVo, employerDto);

		return employerDto;
	}

	/**
	 * This method will populate details from rate DTO to VO and save to DB.
	 * 
	 * @param rateDto
	 * @param lastUpdatedUser
	 * @param status
	 * @return
	 */
	private ContractorRateDetailsDTO populateAndSaveRateDetails(ContractorRateDetailsDTO rateDto,
			String lastUpdatedUser, String status) {

		ContractorRateDetailsEntity contractorRateVo = new ContractorRateDetailsEntity();
		BeanUtils.copyProperties(rateDto, contractorRateVo);
		contractorRateVo.setLastUpdatedDateTime(LocalDateTime.now());
		contractorRateVo.setLastUpdatedUser(lastUpdatedUser);
		contractorRateVo.setActiveRecord(status);
		contractorRateVo = contractorRate.save(contractorRateVo);
		BeanUtils.copyProperties(contractorRateVo, rateDto);

		return rateDto;
	}

	/**
	 * This method will search contractors based on the given search criteria and
	 * return list of matching records.
	 * 
	 * @param searchForm
	 * @return
	 */
	public List<ContractorSearchResultsForm> getContractorSearchResults(ContractorSearchForm searchForm) {

		List<ContractorPersonalDetailsEntity> personalDetails = contractorPersonal
				.searchContractors(searchForm.getContractorName(), searchForm.getAbnHolder());
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
		if (!ProfileParserUtils.isObjectEmpty(empDetails)) {
			for (ContractorEmploymentDetailsEntity empEntity : empDetails) {
				contractorIdList.add(empEntity.getContractorId());

			}
		}

		List<BigInteger> listWithoutDuplicates = new ArrayList<>(new HashSet<>(contractorIdList));
		for (BigInteger contractorId : listWithoutDuplicates) {
			ContractorSearchResultsForm resultForm = new ContractorSearchResultsForm();

			ContractorPersonalDetailsEntity personalEntity = contractorPersonal
					.getPersonalDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(personalEntity)) {
				BeanUtils.copyProperties(personalEntity, resultForm);
				if (!ProfileParserUtils.isObjectEmpty(resultForm.getAbnHolder())) {
					if (resultForm.getAbnHolder().equalsIgnoreCase(ProfileParserConstants.TRUE))
						resultForm.setAbnHolder(ProfileParserConstants.YES);
					else
						resultForm.setAbnHolder(ProfileParserConstants.NO);
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

			contractorSearchResults.add(resultForm);

		}
		return contractorSearchResults;
	}

	/**
	 * This method will return full details of selected contractor.
	 * 
	 * @param contractorId
	 * @return
	 */
	public ContractorDetailsDTO getContractorFullDetails(BigInteger contractorId) {
		ContractorDetailsDTO contractorDto = new ContractorDetailsDTO();
		boolean abnHolder = false;

		// Get personal details
		ContractorPersonalDetailsEntity personalEntity = contractorPersonal
				.getPersonalDetailsByContractorId(contractorId);
		if (!ProfileParserUtils.isObjectEmpty(personalEntity)) {
			ContractorPersonalDetailsDTO personalDto = new ContractorPersonalDetailsDTO();
			BeanUtils.copyProperties(personalEntity, personalDto);
			contractorDto.setPersonalDetails(personalDto);
			if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase(ProfileParserConstants.TRUE))
				abnHolder = true;

		}
		// Get Employer details
		ContractorEmploymentDetailsEntity empEntity = contractorEmployment
				.getEmploymentDetailsByContractorId(contractorId);
		ContractorEmploymentDetailsDTO empDto = new ContractorEmploymentDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(empEntity)) {

			BeanUtils.copyProperties(empEntity, empDto);

		}
		contractorDto.setEmployerList(empDto);

		// Get Bank details.
		ContractorBankDetailsEntity bankEntity = contractorBank.getBankDetailsByContractorId(contractorId);
		ContractorBankDetailsDTO bankDto = new ContractorBankDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(bankEntity)) {

			BeanUtils.copyProperties(bankEntity, bankDto);

		}
		contractorDto.setBankList(bankDto);

		// Get Rate Details.
		ContractorRateDetailsEntity rateEntity = contractorRate.getRateDetailsByContractorId(contractorId);
		ContractorRateDetailsDTO rateDto = new ContractorRateDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(rateEntity)) {
			BeanUtils.copyProperties(rateEntity, rateDto);

		}
		contractorDto.setRateList(rateDto);

		// Get Superannuation details
		ContractorSuperAnnuationDetailsEntity saEntity = contractorSA.getSADetailsByContractorId(contractorId);
		ContractorSuperAnnuationDetailsDTO saDto = new ContractorSuperAnnuationDetailsDTO();
		if (!ProfileParserUtils.isObjectEmpty(saEntity)) {

			BeanUtils.copyProperties(saEntity, saDto);

		}
		contractorDto.setSuperAnnuationList(saDto);

		// If abn holder get ABN details
		if (abnHolder) {
			ContractorABNDetailsEntity abnEntity = contractorAbn.getAbnDetailsByContractorId(contractorId);
			ContractorABNDetailsDTO abnDto = new ContractorABNDetailsDTO();
			if (!ProfileParserUtils.isObjectEmpty(abnEntity)) {
				BeanUtils.copyProperties(abnEntity, abnDto);
			}
			contractorDto.setAbnList(abnDto);

		}
		// If not get TFN details
		else {

			ContractorTFNDetailsEntity tfnEntity = contractorTfn.getActiveTfnByContractorId(contractorId);
			ContractorTFNDetailsDTO tfnDto = new ContractorTFNDetailsDTO();
			if (!ProfileParserUtils.isObjectEmpty(tfnEntity)) {
				BeanUtils.copyProperties(tfnEntity, tfnDto);

			}
			contractorDto.setTfnList(tfnDto);
		}
		/*
		 * //ContractorTfnDetailsCustomRepository impl= new
		 * ContractorTfnDetailsCustomRespositoryImpl(); ContractorTFNDetailsEntity
		 * tfnEntity = contractorTfn.getTfnDetailsByContractorId(contractorId);
		 * List<ContractorTFNDetailsEntity>
		 * testList=contractorAbn.getTfnByContractorId(contractorId); if(testList!=null
		 * && testList.size()>0) logger.info("TFN LIST...,{} ",testList.size());
		 */

		return contractorDto;

	}

	/**
	 * This method will update the contractor details. First it will update personal
	 * details. And then mark all dependent records as inactive and then create new
	 * Active records in dependent table.
	 * 
	 * @param contractorDetailsDto
	 * @param lastUpdatedUser
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public ContractorDetailsDTO updateContractorDetails(ContractorDetailsDTO contractorDetailsDto,
			String lastUpdatedUser) throws Exception {

		// update Personal details
		ContractorDetailsDTO contractorDto = new ContractorDetailsDTO();
		ContractorPersonalDetailsDTO personalDto = contractorDetailsDto.getPersonalDetails();
		if (!ProfileParserUtils.isObjectEmpty(personalDto)
				&& !ProfileParserUtils.isObjectEmpty(personalDto.getContractorId())) {
			boolean abnHolder = false;
			// check if contractor details exist for the same combination
			List<ContractorPersonalDetailsEntity> personalList = contractorPersonal.getContractors(
					personalDto.getFirstName(), personalDto.getLastName(), personalDto.getDateOfBirth(),
					personalDto.getPersonalEmail());
			if (!ProfileParserUtils.isObjectEmpty(personalList) && personalList.size() > 0) {

				for (ContractorPersonalDetailsEntity personalVo : personalList) {
					if (!ProfileParserUtils.isObjectEmpty(personalVo.getContractorId())
							&& !personalVo.getContractorId().equals(personalDto.getContractorId())) {

						throw new Exception(
								"Contractor details are already exist. Please give unique details to edit.");
					}
				}
			}

			logger.info("No matching details... hence saving..,{}", personalDto.toString());
			if (personalDto.getAbnHolder() != null && personalDto.getAbnHolder().equalsIgnoreCase(ProfileParserConstants.TRUE))
				abnHolder = true;
			personalDto = populateAndSavePersonalDetails(personalDto, lastUpdatedUser);
			contractorDto.setPersonalDetails(personalDto);

			// Update previous dependent records with Active status as "Inactive" and then
			// update this
			// as new Active record
			if (abnHolder) {
				// Update ABN
				List<ContractorABNDetailsEntity> abnList = contractorAbn
						.getAllAbnDetailsByContractorId(personalDto.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(abnList) && abnList.size() > 0) {
					for (ContractorABNDetailsEntity abnVo : abnList) {
						abnVo.setActiveRecord(ProfileParserConstants.INACTIVE);
						abnVo.setLastUpdatedDateTime(LocalDateTime.now());
						abnVo.setLastUpdatedUser(lastUpdatedUser);
						contractorAbn.save(abnVo);

					}

				}
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getAbnList())) {

					ContractorABNDetailsDTO contractorAbnDto = contractorDetailsDto.getAbnList();
					contractorAbnDto.setContractorId(personalDto.getContractorId());

					contractorAbnDto = populateAndSaveABNDetails(contractorAbnDto, lastUpdatedUser,
							ProfileParserConstants.ACTIVE);
					contractorDto.setAbnList(contractorAbnDto);
				}

			} else {
				// update TFN
				List<ContractorTFNDetailsEntity> tfnList = contractorTfn
						.getAllTfnDetailsByContractorId(personalDto.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(tfnList) && tfnList.size() > 0) {
					for (ContractorTFNDetailsEntity tfnVo : tfnList) {
						tfnVo.setActiveRecord(ProfileParserConstants.INACTIVE);
						tfnVo.setLastUpdatedDateTime(LocalDateTime.now());
						tfnVo.setLastUpdatedUser(lastUpdatedUser);
						contractorTfn.save(tfnVo);

					}

				}
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getTfnList())) {

					ContractorTFNDetailsDTO contractorTfnDto = contractorDetailsDto.getTfnList();
					contractorTfnDto.setContractorId(personalDto.getContractorId());

					contractorTfnDto = populateAndSaveTFNDetails(contractorTfnDto, lastUpdatedUser,
							ProfileParserConstants.ACTIVE);

					contractorDto.setTfnList(contractorTfnDto);
				}

				// update SA
				List<ContractorSuperAnnuationDetailsEntity> saList = contractorSA
						.getAllSADetailsByContractorId(personalDto.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(saList) && saList.size() > 0) {
					for (ContractorSuperAnnuationDetailsEntity saVo : saList) {
						saVo.setActiveRecord(ProfileParserConstants.INACTIVE);
						saVo.setLastUpdatedDateTime(LocalDateTime.now());
						saVo.setLastUpdatedUser(lastUpdatedUser);
						contractorSA.save(saVo);

					}

				}
				if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getSuperAnnuationList())) {

					ContractorSuperAnnuationDetailsDTO contractorSaDto = contractorDetailsDto.getSuperAnnuationList();
					contractorSaDto.setContractorId(personalDto.getContractorId());

					contractorSaDto = populateAndSaveSADetails(contractorSaDto, lastUpdatedUser,
							ProfileParserConstants.ACTIVE);

					contractorDto.setSuperAnnuationList(contractorSaDto);
				}
			}

			// update Bank
			List<ContractorBankDetailsEntity> bankList = contractorBank
					.getAllBankDetailsByContractorId(personalDto.getContractorId());
			if (!ProfileParserUtils.isObjectEmpty(bankList) && bankList.size() > 0) {
				for (ContractorBankDetailsEntity bankVo : bankList) {
					bankVo.setActiveRecord(ProfileParserConstants.INACTIVE);
					bankVo.setLastUpdatedDateTime(LocalDateTime.now());
					bankVo.setLastUpdatedUser(lastUpdatedUser);
					contractorBank.save(bankVo);

				}

			}
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getBankList())) {

				ContractorBankDetailsDTO contractorBankDto = contractorDetailsDto.getBankList();
				contractorBankDto.setContractorId(personalDto.getContractorId());

				contractorBankDto = populateAndSaveBankDetails(contractorBankDto, lastUpdatedUser,
						ProfileParserConstants.ACTIVE);
				contractorDto.setBankList(contractorBankDto);
			}

			// update Employer

			List<ContractorEmploymentDetailsEntity> empList = contractorEmployment
					.getAllEmploymentDetailsByContractorId(personalDto.getContractorId());
			if (!ProfileParserUtils.isObjectEmpty(bankList) && bankList.size() > 0) {
				for (ContractorEmploymentDetailsEntity empVo : empList) {
					empVo.setActiveRecord(ProfileParserConstants.INACTIVE);
					empVo.setLastUpdatedDateTime(LocalDateTime.now());
					empVo.setLastUpdatedUser(lastUpdatedUser);
					contractorEmployment.save(empVo);

				}

			}
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList())) {

				ContractorEmploymentDetailsDTO contractorEmpDto = contractorDetailsDto.getEmployerList();
				contractorEmpDto.setContractorId(personalDto.getContractorId());

				contractorEmpDto = populateAndSaveEmploymentDetails(contractorEmpDto, lastUpdatedUser,
						ProfileParserConstants.ACTIVE);
				contractorDto.setEmployerList(contractorEmpDto);
			}

			// update Rate

			List<ContractorRateDetailsEntity> rateList = contractorRate
					.getAllRateDetailsByContractorId(personalDto.getContractorId());
			if (!ProfileParserUtils.isObjectEmpty(rateList) && rateList.size() > 0) {
				for (ContractorRateDetailsEntity rateVo : rateList) {
					rateVo.setActiveRecord(ProfileParserConstants.INACTIVE);
					rateVo.setLastUpdatedDateTime(LocalDateTime.now());
					rateVo.setLastUpdatedUser(lastUpdatedUser);
					contractorRate.save(rateVo);

				}

			}
			if (!ProfileParserUtils.isObjectEmpty(contractorDetailsDto.getEmployerList())) {

				ContractorRateDetailsDTO contractorRateDto = contractorDetailsDto.getRateList();
				contractorRateDto.setContractorId(personalDto.getContractorId());

				contractorRateDto = populateAndSaveRateDetails(contractorRateDto, lastUpdatedUser,
						ProfileParserConstants.ACTIVE);
				logger.info("SAving rate...{},",contractorRateDto.toString());
				contractorDto.setRateList(contractorRateDto);
			}
		}

		return contractorDetailsDto;
	}

	/**
	 * This method will return the history of all previous bank records for the given contractor
	 * @param contractorId
	 * @return
	 */
	public List<ContractorBankDetailsDTO> getBankHistoryByContractorId(BigInteger contractorId) {

		List<ContractorBankDetailsDTO> banklist = new ArrayList<ContractorBankDetailsDTO>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId)) {
			List<ContractorBankDetailsEntity> bankVoList = contractorBank.getAllBankDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(bankVoList) && bankVoList.size() > 0) {
				for (ContractorBankDetailsEntity bankVo : bankVoList) {
					ContractorBankDetailsDTO bankDto = new ContractorBankDetailsDTO();
					BeanUtils.copyProperties(bankVo, bankDto);
					banklist.add(bankDto);
				}

			}
		}

		return banklist;
	}
	
	/**
	 * This method will return the history of all previous ABN records for the given contractor
	 * @param contractorId
	 * @return
	 */
	
	public List<ContractorABNDetailsDTO> getAbnHistoryByContractorId(BigInteger contractorId){
		
		List<ContractorABNDetailsDTO> abnlist = new ArrayList<ContractorABNDetailsDTO>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId)) {
			List<ContractorABNDetailsEntity> abnVoList = contractorAbn.getAllAbnDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(abnVoList) && abnVoList.size() > 0) {
				for (ContractorABNDetailsEntity abnVo : abnVoList) {
					ContractorABNDetailsDTO abnDto = new ContractorABNDetailsDTO();
					BeanUtils.copyProperties(abnVo, abnDto);
					if(abnDto.getGstRegistered().equalsIgnoreCase(ProfileParserConstants.TRUE))
						abnDto.setGstRegistered(ProfileParserConstants.YES);
					if(abnDto.getGstRegistered().equalsIgnoreCase(ProfileParserConstants.FALSE))
						abnDto.setGstRegistered(ProfileParserConstants.NO);
					if(abnDto.getPiPlFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						abnDto.setPiPlFlag(ProfileParserConstants.YES);
					if(abnDto.getPiPlFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						abnDto.setPiPlFlag(ProfileParserConstants.NO);
					if(abnDto.getWorkCoverFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
							abnDto.setWorkCoverFlag(ProfileParserConstants.YES);
						if(abnDto.getWorkCoverFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
							abnDto.setWorkCoverFlag(ProfileParserConstants.NO);
					
					abnlist.add(abnDto);
				}

			}
		}
		
		return abnlist;
	}

	/**
	 * This method will return the history of all previous TFN records for the given contractor
	 * @param contractorId
	 * @return
	 */
	
	public List<ContractorTFNDetailsDTO> getTfnHistoryByContractorId(BigInteger contractorId){
		
		List<ContractorTFNDetailsDTO> tfnlist = new ArrayList<ContractorTFNDetailsDTO>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId)) {
			List<ContractorTFNDetailsEntity> tfnVoList = contractorTfn.getAllTfnDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(tfnVoList) && tfnVoList.size() > 0) {
				for (ContractorTFNDetailsEntity tfnVo : tfnVoList) {
					ContractorTFNDetailsDTO tfnDto = new ContractorTFNDetailsDTO();
					BeanUtils.copyProperties(tfnVo, tfnDto);
					if(tfnDto.getNewApplicationFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						tfnDto.setNewApplicationFlag(ProfileParserConstants.YES);
					if(tfnDto.getNewApplicationFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						tfnDto.setNewApplicationFlag(ProfileParserConstants.NO);
					
					if(tfnDto.getUnderAgeExemptionFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						tfnDto.setUnderAgeExemptionFlag(ProfileParserConstants.YES);
					if(tfnDto.getUnderAgeExemptionFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						tfnDto.setUnderAgeExemptionFlag(ProfileParserConstants.NO);
					
					if(tfnDto.getPensionHolderFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						tfnDto.setPensionHolderFlag(ProfileParserConstants.YES);
					if(tfnDto.getPensionHolderFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						tfnDto.setPensionHolderFlag(ProfileParserConstants.NO);
					
					if(tfnDto.getTaxPayerType().equalsIgnoreCase(ProfileParserConstants.PR))
						tfnDto.setTaxPayerType(ProfileParserConstants.AUSTRALIAN_RESIDENT);
					if(tfnDto.getTaxPayerType().equalsIgnoreCase(ProfileParserConstants.FOREIGN_RESIDENT))
						tfnDto.setTaxPayerType(ProfileParserConstants.FOREIGN_RESIDENT);
					if(tfnDto.getTaxPayerType().equalsIgnoreCase(ProfileParserConstants.WORKING_HOLIDAY))
						tfnDto.setTaxPayerType(ProfileParserConstants.WORKING_HOLIDAY_MAKER);
					
					tfnlist.add(tfnDto);
				}

			}
		}
		
		return tfnlist;
	}
	
	/**
	 * This method will return the history of all previous Rate records for the given contractor
	 * @param contractorId
	 * @return
	 */
	
	public List<ContractorRateDetailsDTO> getRateHistoryByContractorId(BigInteger contractorId){
		
		List<ContractorRateDetailsDTO> ratelist = new ArrayList<ContractorRateDetailsDTO>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId)) {
			List<ContractorRateDetailsEntity> rateVoList = contractorRate.getAllRateDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(rateVoList) && rateVoList.size() > 0) {
				for (ContractorRateDetailsEntity rateVo : rateVoList) {
					ContractorRateDetailsDTO rateDto = new ContractorRateDetailsDTO();
					BeanUtils.copyProperties(rateVo, rateDto);
					
					
					if(rateDto.getPayrollTaxPaymentFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						rateDto.setPayrollTaxPaymentFlag(ProfileParserConstants.YES);
					else if(rateDto.getPayrollTaxPaymentFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						rateDto.setPayrollTaxPaymentFlag(ProfileParserConstants.NO);
					if(rateDto.getInsurancePaymentFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						rateDto.setInsurancePaymentFlag(ProfileParserConstants.YES);
					else if(rateDto.getInsurancePaymentFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						rateDto.setInsurancePaymentFlag(ProfileParserConstants.NO);
					
					ratelist.add(rateDto);
				}

			}
		}
		
		return ratelist;
	}
	
	/**
	 * This method will return the history of all previous Employment records for the given contractor
	 * @param contractorId
	 * @return
	 */
	
	public List<ContractorEmploymentDetailsDTO> getEmployerHistoryByContractorId(BigInteger contractorId){
		
		List<ContractorEmploymentDetailsDTO> emplist = new ArrayList<ContractorEmploymentDetailsDTO>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId)) {
			List<ContractorEmploymentDetailsEntity> empVoList = contractorEmployment.getAllEmploymentDetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(empVoList) && empVoList.size() > 0) {
				for (ContractorEmploymentDetailsEntity empVo : empVoList) {
					ContractorEmploymentDetailsDTO empDto = new ContractorEmploymentDetailsDTO();
					BeanUtils.copyProperties(empVo, empDto);
					if(empDto.getFinishedClient().equalsIgnoreCase(ProfileParserConstants.TRUE))
						empDto.setFinishedClient(ProfileParserConstants.YES);
					else if(empDto.getFinishedClient().equalsIgnoreCase(ProfileParserConstants.FALSE))
						empDto.setFinishedClient(ProfileParserConstants.NO);
					if(empDto.getWorkLocationCountry().equalsIgnoreCase(ProfileParserConstants.OTHER))
						empDto.setWorkLocationCountry(empDto.getWlOtherCountry());
					emplist.add(empDto);
				}

			}
		}
		
		return emplist;
	}
	
	
	/**
	 * This method will return the history of all previous Super annuation records for the given contractor
	 * @param contractorId
	 * @return
	 */
	
	public List<ContractorSuperAnnuationDetailsDTO> getSAHistoryByContractorId(BigInteger contractorId){
		
		List<ContractorSuperAnnuationDetailsDTO> salist = new ArrayList<ContractorSuperAnnuationDetailsDTO>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId)) {
			List<ContractorSuperAnnuationDetailsEntity> saVoList = contractorSA.getAllSADetailsByContractorId(contractorId);
			if (!ProfileParserUtils.isObjectEmpty(saVoList) && saVoList.size() > 0) {
				for (ContractorSuperAnnuationDetailsEntity saVo : saVoList) {
					ContractorSuperAnnuationDetailsDTO saDto = new ContractorSuperAnnuationDetailsDTO();
					BeanUtils.copyProperties(saVo, saDto);
					if(saDto.getAdditionalSuperAnnuationContributionFlag().equalsIgnoreCase(ProfileParserConstants.TRUE))
						saDto.setAdditionalSuperAnnuationContributionFlag(ProfileParserConstants.YES);
					if(saDto.getAdditionalSuperAnnuationContributionFlag().equalsIgnoreCase(ProfileParserConstants.FALSE))
						saDto.setAdditionalSuperAnnuationContributionFlag(ProfileParserConstants.NO);
					salist.add(saDto);
				}

			}
		}
		
		return salist;
	}
}
