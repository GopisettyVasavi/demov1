package com.renaissance.requirement.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;

import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import com.renaissance.requirement.dto.RequirementDTO;
import com.renaissance.requirement.model.JobRequirementEntity;
import com.renaissance.requirement.repository.JobRequirementCustomRepository;

public class JobRequirementCustomRepositoryImpl implements JobRequirementCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Lazy
	JobRequirementEntity requirement;
	public List<JobRequirementEntity> searchRequirements(RequirementDTO requirementDto){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<JobRequirementEntity> query = cb.createQuery(JobRequirementEntity.class);
		Root<JobRequirementEntity> jobRequirement = query.from(JobRequirementEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!ProfileParserUtils.isObjectEmpty(requirementDto)) {
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getVendorName())) {
				predicates.add(cb.like(cb.upper(jobRequirement.get("vendorName")),
						"%" + requirementDto.getVendorName().toUpperCase() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getStatus()) && !ProfileParserConstants.NONE.equalsIgnoreCase(requirementDto.getStatus())) {
				predicates.add(cb.equal(jobRequirement.get("status"), requirementDto.getStatus()));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getRequirementId())) {
				predicates.add(cb.like(cb.upper(jobRequirement.get("requirementId")),
						"%" + requirementDto.getRequirementId().toUpperCase() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getJobTitle())) {
				predicates.add(cb.like(cb.upper(jobRequirement.get("jobTitle")),
						"%" + requirementDto.getJobTitle().toUpperCase() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getJobType()) &&
					!ProfileParserConstants.NONE.equalsIgnoreCase(requirementDto.getJobType())) {
				predicates.add(cb.like(cb.upper(jobRequirement.get("jobType")),
						"%" + requirementDto.getJobType().toUpperCase() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getLocation())) {
				predicates.add(cb.like(cb.upper(jobRequirement.get("location")),
						"%" + requirementDto.getLocation().toUpperCase() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getJobDescription())) {
				predicates.add(cb.like(cb.upper(jobRequirement.get("jobDescription")),
						"%" + requirementDto.getJobDescription().toUpperCase() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getRecruiterId()) && requirementDto.getRecruiterId()!=000) {
				predicates.add(cb.like(jobRequirement.get("assignedRecruiter"),
						"%" + requirementDto.getRecruiterId() + "%"));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getRecruiterId()) &&
					!ProfileParserUtils.isObjectEmpty(requirementDto.getRecruiterName()) && 
					ProfileParserConstants.ALL.equalsIgnoreCase(requirementDto.getRecruiterName())) {
				predicates.add(cb.isNotNull(jobRequirement.get("assignedRecruiter")));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getRecruiterId()) &&
					!ProfileParserUtils.isObjectEmpty(requirementDto.getRecruiterName()) && 
					ProfileParserConstants.NONE.equalsIgnoreCase(requirementDto.getRecruiterName())) {
				//predicates.add(cb.isNotNull(jobRequirement.get("assignedRecruiter")));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getDatePosted())) {
				predicates.add(cb.equal(jobRequirement.get("datePosted"),
						ProfileParserUtils.parseStringDate(requirementDto.getDatePosted())));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getContractStartDate())) {
				predicates.add(cb.equal(jobRequirement.get("contractStartDate"),
						ProfileParserUtils.parseStringDate(requirementDto.getContractStartDate())));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementDto.getContractEndDate())) {
				predicates.add(cb.equal(jobRequirement.get("contractEndDate"),
						ProfileParserUtils.parseStringDate(requirementDto.getContractEndDate())));
			}

			
		}

		List<JobRequirementEntity> requirementList = null;
		if (predicates.size() > 0) {
			// predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")),
			// "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(jobRequirement).where(cb.and(predicates.toArray(predicatesArray)));
			requirementList = entityManager.createQuery(query).getResultList();
		}else {
			query.select(jobRequirement);
			requirementList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(requirementList))
			return requirementList;

		else
			return new ArrayList<JobRequirementEntity>();
	}

}
