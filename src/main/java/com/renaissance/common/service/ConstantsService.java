package com.renaissance.common.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.commission.repository.CommissionsLookupEntity;
import com.renaissance.common.dto.ConstantsDTO;
import com.renaissance.common.model.AppConstantsEntity;
import com.renaissance.common.repository.AppConstantsRepository;
import com.renaissance.common.repository.CommissionsLookupRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class ConstantsService {
	//private static final Logger logger = LoggerFactory.getLogger(ConstantsService.class);
	@Autowired
	AppConstantsRepository constantsRepository;

	@Autowired
	CommissionsLookupRepository commissionsLookup;

	/**
	 * This method will load all constant values.
	 * 
	 * @param constants
	 * @return
	 */

	public List<ConstantsDTO> getConstants() {
		List<ConstantsDTO> lookupDtoList = new ArrayList<ConstantsDTO>();
		Iterable<AppConstantsEntity> entities = constantsRepository.findAll();
		if (!ProfileParserUtils.isObjectEmpty(entities)) {
			for (AppConstantsEntity constantEntity : entities) {
				if (!ProfileParserUtils.isObjectEmpty(constantEntity)) {
					ConstantsDTO dto = new ConstantsDTO();
					BeanUtils.copyProperties(constantEntity, dto);
					dto.setId(constantEntity.getId());
					dto.setEffectiveFrom(ProfileParserUtils.parseDateToString(constantEntity.getEffectiveFrom()));
					dto.setEffectiveTill(ProfileParserUtils.parseDateToString(constantEntity.getEffectiveTill()));
					lookupDtoList.add(dto);

				}

			}
		}

		return lookupDtoList;

	}
/**
 * This method will save or update constant value
 * @param lookupObj
 * @return
 */
	public ConstantsDTO saveConstant(ConstantsDTO lookupObj) {
		if (!ProfileParserUtils.isObjectEmpty(lookupObj)) {
			AppConstantsEntity lookupEntity = new AppConstantsEntity();
			BeanUtils.copyProperties(lookupObj, lookupEntity);
			lookupEntity.setEffectiveFrom(ProfileParserUtils.parseStringDate(lookupObj.getEffectiveFrom()));
			lookupEntity.setEffectiveTill(ProfileParserUtils.parseStringDate(lookupObj.getEffectiveTill()));
			lookupEntity = constantsRepository.save(lookupEntity);
		}
		return lookupObj;
	}



	/**
	 * This method will return value from the constants lookup table.
	 * 
	 * @param state
	 * @return
	 */
	public Double getConstantValue(String constantName) {
		Iterable<AppConstantsEntity> entities = constantsRepository.findAll();
		if (!ProfileParserUtils.isObjectEmpty(entities)) {
			
			for(AppConstantsEntity entity:entities) {
				LocalDate today=LocalDate.now();
				if(!ProfileParserUtils.isObjectEmpty(entity) && entity.getConstantName().equalsIgnoreCase(constantName)
						&& entity.getEffectiveFrom().isBefore(today) && (entity.getEffectiveTill()==null ||entity.getEffectiveTill().isEqual(today)
								|| entity.getEffectiveTill().isAfter(today))) {
					return entity.getConstantValue();
				}
			}
			

		}
		return 0.0;
	}

	/**
	 * This method will get all the commissions list from lookup table and return.
	 * 
	 * @return
	 */
	public List<CommissionsLookupEntity> getCommissions() {
		Iterable<CommissionsLookupEntity> commissions = commissionsLookup.findAll();
		List<CommissionsLookupEntity> commissionsList = new ArrayList<CommissionsLookupEntity>();
		if (!ProfileParserUtils.isObjectEmpty(commissions)) {
			for (CommissionsLookupEntity commission : commissions) {
				commissionsList.add(commission);
			}
		}
		return commissionsList;
	}
}
