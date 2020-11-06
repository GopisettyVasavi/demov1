package com.renaissance.requirement.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;

import com.renaissance.profile.parser.util.ProfileParserUtils;
import com.renaissance.requirement.model.MappingRequirementCandidateEntity;
import com.renaissance.requirement.repository.MappingRequirementCandidateCustomRepository;

public class MappingRequirementCandidateCustomRepositoryImpl implements MappingRequirementCandidateCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Lazy
	MappingRequirementCandidateEntity requirement;
	public 
	List<MappingRequirementCandidateEntity> getCandidateRequirementMappings(BigInteger candidateId, BigInteger requirementId){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<MappingRequirementCandidateEntity> query = cb.createQuery(MappingRequirementCandidateEntity.class);
		Root<MappingRequirementCandidateEntity> jobRequirement = query.from(MappingRequirementCandidateEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		
			if (!ProfileParserUtils.isObjectEmpty(candidateId)) {
				predicates.add(cb.equal(jobRequirement.get("candidateId"),
						candidateId));
			}
			if (!ProfileParserUtils.isObjectEmpty(requirementId)) {
				predicates.add(cb.equal(jobRequirement.get("requirementId"),
						requirementId));
				
			}

		List<MappingRequirementCandidateEntity> requirementList = null;
		if (predicates.size() > 0) {
			// predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")),
			// "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(jobRequirement).where(cb.and(predicates.toArray(predicatesArray)));
			requirementList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(requirementList))
			return requirementList;

		else
			return new ArrayList<MappingRequirementCandidateEntity>();
	
	}

}
