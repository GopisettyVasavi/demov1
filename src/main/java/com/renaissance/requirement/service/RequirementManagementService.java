package com.renaissance.requirement.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.profile.parser.util.ProfileParserUtils;
import com.renaissance.requirement.dto.MappingCandidateRqmtDTO;
import com.renaissance.requirement.dto.RequirementDTO;
import com.renaissance.requirement.model.JobRequirementEntity;
import com.renaissance.requirement.model.MappingRequirementCandidateEntity;
import com.renaissance.requirement.repository.JobRequirementRepository;
import com.renaissance.requirement.repository.MappingRequirementCandidateRepository;

@Service
public class RequirementManagementService {
	private static final Logger logger = LoggerFactory.getLogger(RequirementManagementService.class);
	@Autowired
	JobRequirementRepository jobRequirementRepository;
	
	@Autowired
	MappingRequirementCandidateRepository mappingRepository;
	
	

	
	public RequirementDTO createRequirement(RequirementDTO requirementDto) {
		logger.info("Details in service..{}",requirementDto.toString());
		JobRequirementEntity requirementEntity= new JobRequirementEntity();
		BeanUtils.copyProperties(requirementDto, requirementEntity);
		requirementEntity.setDatePosted(ProfileParserUtils.parseStringDate(requirementDto.getDatePosted()));
		requirementEntity.setContractStartDate(ProfileParserUtils.parseStringDate(requirementDto.getContractStartDate()));
		requirementEntity.setContractEndDate(ProfileParserUtils.parseStringDate(requirementDto.getContractEndDate()));
		jobRequirementRepository.save(requirementEntity);
		return requirementDto;
	}
	
	public List<RequirementDTO> searchRequirements(RequirementDTO requirementDto){
		List<RequirementDTO> requirementList= new ArrayList<RequirementDTO>();
		if(!ProfileParserUtils.isObjectEmpty(requirementDto)) {
			List<JobRequirementEntity> requirementEntityList=jobRequirementRepository.searchRequirements(requirementDto);
			if(!ProfileParserUtils.isObjectEmpty(requirementEntityList) ) {
				for(JobRequirementEntity requirementEntity : requirementEntityList) {
					RequirementDTO requirement= new RequirementDTO();
					BeanUtils.copyProperties(requirementEntity, requirement);
					requirement.setDatePosted(ProfileParserUtils.parseDateToString(requirementEntity.getDatePosted()));
					requirement.setContractStartDate(ProfileParserUtils.parseDateToString(requirementEntity.getContractStartDate()));
					requirement.setContractEndDate(ProfileParserUtils.parseDateToString(requirementEntity.getContractEndDate()));
					requirementList.add(requirement);
				}
			}
			
		}
		
		return requirementList;
	}
	public RequirementDTO updateRequirement(RequirementDTO requirementDto) {
		return requirementDto;
	}
	
	public List<MappingCandidateRqmtDTO> fetchRequirementMappings(BigInteger candidateId, BigInteger requirementId) {
		List<MappingCandidateRqmtDTO> mappingCandidateList= new ArrayList<MappingCandidateRqmtDTO>();
		List<MappingRequirementCandidateEntity> mappingList=mappingRepository.getCandidateRequirementMappings(candidateId, requirementId);
		logger.info("Mapping results in service,{}",mappingList.size());
		if(!ProfileParserUtils.isObjectEmpty(mappingList) ) {
			for(MappingRequirementCandidateEntity mappingEntity : mappingList) {
				MappingCandidateRqmtDTO mappingDto= new MappingCandidateRqmtDTO();
				BeanUtils.copyProperties(mappingEntity, mappingDto);
				mappingCandidateList.add(mappingDto);
				
				
			}
		}
		return mappingCandidateList;
	}
	
	public List<MappingCandidateRqmtDTO> saveCandidateMapping(List<MappingCandidateRqmtDTO> mappingList){
		
		//List<MappingCandidateRqmtDTO> mappingCandidateList= new ArrayList<MappingCandidateRqmtDTO>();
		if(!ProfileParserUtils.isObjectEmpty(mappingList)) {
			for(MappingCandidateRqmtDTO mappingDto: mappingList) {
				MappingRequirementCandidateEntity mappingEntity = new MappingRequirementCandidateEntity();
				//logger.info("Before: "+mappingDto.toString());
				BeanUtils.copyProperties(mappingDto,mappingEntity);
				//logger.info("After: "+mappingEntity.toString());
				mappingEntity =mappingRepository.save(mappingEntity);
				//MappingCandidateRqmtDTO mapping= new MappingCandidateRqmtDTO
			}
		}
		
		
		return mappingList;
	}
}
