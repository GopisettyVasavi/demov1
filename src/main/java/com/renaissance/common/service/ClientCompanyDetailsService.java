package com.renaissance.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.common.dto.ClientCompanyDTO;
import com.renaissance.common.model.ClientCompanyDetailsEntity;
import com.renaissance.common.repository.ClientCompanyDetailsRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service
public class ClientCompanyDetailsService {
	
	@Autowired
	ClientCompanyDetailsRepository clientCompanyRepo;
	
	/**
	 * This method will load all commission lookup entries from database and return the list.
	 * @return
	 */
	public List<ClientCompanyDTO> loadClientCompanies(){
		List<ClientCompanyDTO> lookupDtoList= new ArrayList<ClientCompanyDTO>();
		Iterable<ClientCompanyDetailsEntity> entities=clientCompanyRepo.findAll();
		if(!ProfileParserUtils.isObjectEmpty(entities)) {
			for (ClientCompanyDetailsEntity companyObj : entities) {
				if(!ProfileParserUtils.isObjectEmpty(companyObj)) {
					ClientCompanyDTO dto= new ClientCompanyDTO();
					BeanUtils.copyProperties(companyObj, dto);
					lookupDtoList.add(dto);
					
				}
				
			}
		}
		
		return lookupDtoList;
		
	}
	
	public ClientCompanyDTO saveClientCompany(ClientCompanyDTO lookupObj) {
		if(!ProfileParserUtils.isObjectEmpty(lookupObj)) {
			ClientCompanyDetailsEntity lookupEntity= new ClientCompanyDetailsEntity();
			BeanUtils.copyProperties(lookupObj, lookupEntity);
			lookupEntity=clientCompanyRepo.save(lookupEntity);
		}
		return lookupObj;
	}

	
}
