package com.renaissance.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.common.dto.ConstantsDTO;
import com.renaissance.common.model.AppConstantsEntity;
import com.renaissance.common.repository.AppConstantsRepository;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class ConstantsService {
	
	@Autowired
	AppConstantsRepository constantsRepository;
	
public ConstantsDTO saveConstants(ConstantsDTO constants) {
	if(!ProfileParserUtils.isObjectEmpty(constants)) {
		Iterable<AppConstantsEntity> entities =constantsRepository.findAll();
		if(!ProfileParserUtils.isObjectEmpty(entities)) {
			for(AppConstantsEntity constant: entities) {
				if(!ProfileParserUtils.isObjectEmpty(constant)) {
					if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.NSW)) {
						constant.setConstantValue(constants.getNsw());
						constantsRepository.save(constant);
					}
					if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.WA)) {
						constant.setConstantValue(constants.getWa());
						constantsRepository.save(constant);
					}
					if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.SA)) {
						constant.setConstantValue(constants.getSa());
						constantsRepository.save(constant);
					}
					if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.TAS)) {
						constant.setConstantValue(constants.getTas());
						constantsRepository.save(constant);
					}
					if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.QLD)) {
						constant.setConstantValue(constants.getQld());
						constantsRepository.save(constant);
					}
					if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.VIC)) {
						constant.setConstantValue(constants.getVic());
						constantsRepository.save(constant);
					}
				}
			}
		}
			/*
			 * AppConstantsEntity entity= new AppConstantsEntity();
			 * entity.setConstantName("NSW"); entity.setConstantValue(constants.getNsw());
			 * entityList.add(entity); AppConstantsEntity entity1= new AppConstantsEntity();
			 * entity1.setConstantName("WA"); entity1.setConstantValue(constants.getWa());
			 * entityList.add(entity1); AppConstantsEntity entity2= new
			 * AppConstantsEntity(); entity2.setConstantName("SA");
			 * entity2.setConstantValue(constants.getSa()); entityList.add(entity2);
			 * AppConstantsEntity entity3= new AppConstantsEntity();
			 * entity3.setConstantName("VIC"); entity3.setConstantValue(constants.getVic());
			 * entityList.add(entity3); AppConstantsEntity entity4= new
			 * AppConstantsEntity(); entity4.setConstantName("TAS");
			 * entity4.setConstantValue(constants.getTas()); entityList.add(entity4);
			 * AppConstantsEntity entity5= new AppConstantsEntity();
			 * entity5.setConstantName("QLD"); entity5.setConstantValue(constants.getQld());
			 * entityList.add(entity5); constantsRepository.saveAll(entityList);
			 */
	}
	
	return constants;
}

public ConstantsDTO getConstants() {
	ConstantsDTO constantsDto= new ConstantsDTO();
	Iterable<AppConstantsEntity> entities =constantsRepository.findAll();
	if(!ProfileParserUtils.isObjectEmpty(entities)) {
		for(AppConstantsEntity constant: entities) {
			if(!ProfileParserUtils.isObjectEmpty(constant)) {
				if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.NSW)) {
					constantsDto.setNsw(constant.getConstantValue());
				}
				if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.WA)) {
					constantsDto.setWa(constant.getConstantValue());
				}
				if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.QLD)) {
					constantsDto.setQld(constant.getConstantValue());
				}
				if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.VIC)) {
					constantsDto.setVic(constant.getConstantValue());
				}
				if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.SA)) {
					constantsDto.setSa(constant.getConstantValue());
				}
				if(constant.getConstantName().equalsIgnoreCase(ProfileParserConstants.TAS)) {
					constantsDto.setTas(constant.getConstantValue());
				}
			}
		}
	}
	return constantsDto;
}
}
