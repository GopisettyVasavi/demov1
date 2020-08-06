package com.renaissance.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.commission.repository.CommissionsLookupEntity;
import com.renaissance.common.dto.ConstantsDTO;
import com.renaissance.common.model.AppConstantsEntity;
import com.renaissance.common.repository.AppConstantsRepository;
import com.renaissance.common.repository.CommissionsLookupRepository;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class ConstantsService {

	@Autowired
	AppConstantsRepository constantsRepository;

	@Autowired
	CommissionsLookupRepository commissionsLookup;
	/**
	 * This method will save all constant values.
	 * 
	 * @param constants
	 * @return
	 */
	public ConstantsDTO saveConstants(ConstantsDTO constants) {
		if (!ProfileParserUtils.isObjectEmpty(constants)) {
			Iterable<AppConstantsEntity> entities = constantsRepository.findAll();
			if (!ProfileParserUtils.isObjectEmpty(entities)) {
				for (AppConstantsEntity constant : entities) {
					if (!ProfileParserUtils.isObjectEmpty(constant)) {
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.NSW)) {
							constant.setConstantValue(constants.getNsw());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getNswEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.WA)) {
							constant.setConstantValue(constants.getWa());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getWaEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.SA)) {
							constant.setConstantValue(constants.getSa());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getSaEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.TAS)) {
							constant.setConstantValue(constants.getTas());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getTasEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.QLD)) {
							constant.setConstantValue(constants.getQld());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getQldEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.VIC)) {
							constant.setConstantValue(constants.getVic());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getVicEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.SUPER_ANNUATION)) {
							constant.setConstantValue(constants.getSuperannuation());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getSuperEffectiveFrom()));
							constantsRepository.save(constant);
						}
						if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.INSURANCE)) {
							constant.setConstantValue(constants.getInsurance());
							constant.setEffectiveFrom(ProfileParserUtils.parseStringDate(constants.getInsEffectiveFrom()));
							constantsRepository.save(constant);
						}
					}
				}
			}

		}

		return constants;
	}

	/**
	 * This method will return state taxes(Constant values/Metadata) for all the
	 * states.
	 * 
	 * @return
	 */
	public ConstantsDTO getConstants() {
		ConstantsDTO constantsDto = new ConstantsDTO();
		Iterable<AppConstantsEntity> entities = constantsRepository.findAll();
		if (!ProfileParserUtils.isObjectEmpty(entities)) {
			for (AppConstantsEntity constant : entities) {
				if (!ProfileParserUtils.isObjectEmpty(constant)) {
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.NSW)) {
						constantsDto.setNswEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setNsw(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.WA)) {
						constantsDto.setWaEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setWa(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.QLD)) {
						constantsDto.setQldEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setQld(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.VIC)) {
						constantsDto.setVicEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setVic(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.SA)) {
						constantsDto.setSaEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setSa(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.TAS)) {
						constantsDto.setTasEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setTas(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.SUPER_ANNUATION)) {
						constantsDto.setSuperEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setSuperannuation(constant.getConstantValue());
					}
					if (constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.INSURANCE)) {
						constantsDto.setInsEffectiveFrom(ProfileParserUtils.parseDateToString(constant.getEffectiveFrom()));
						constantsDto.setInsurance(constant.getConstantValue());
					}
				}
			}
		}
		return constantsDto;
	}

	/**
	 * This method will return payroll tax for the passed state.
	 * 
	 * @param state
	 * @return
	 */
	public Double getConstantValue(String constantName) {
		Iterable<AppConstantsEntity> entities = constantsRepository.findAll();
		if (!ProfileParserUtils.isObjectEmpty(entities)) {
			for (AppConstantsEntity constant : entities) {
				if (!ProfileParserUtils.isObjectEmpty(constant) && constant.getConstantName().equalsIgnoreCase(constantName)) {
					return constant.getConstantValue();
				}
			}
		}
		return 0.0;
	}

	/** 
	 * This method will get all the commissions list from lookup table and return.
	 * @return
	 */
	public List<CommissionsLookupEntity> getCommissions() {
		Iterable<CommissionsLookupEntity> commissions = commissionsLookup.findAll();
		List<CommissionsLookupEntity> commissionsList= new ArrayList<CommissionsLookupEntity>();
		if (!ProfileParserUtils.isObjectEmpty(commissions)) {
			for (CommissionsLookupEntity commission : commissions) {
				commissionsList.add(commission);
			}
		}
		return commissionsList;
	}
}
