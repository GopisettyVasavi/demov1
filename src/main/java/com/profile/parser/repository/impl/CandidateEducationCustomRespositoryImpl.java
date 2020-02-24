package com.profile.parser.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;

import com.profile.parser.model.CandidateEducationEntity;
import com.profile.parser.repository.CandidateEducationCustomRespository;
import com.profile.parser.repository.CandidateEducationRepository;

public class CandidateEducationCustomRespositoryImpl implements CandidateEducationCustomRespository {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	CandidateEducationRepository candidateEducation;

	@Override
	public CandidateEducationEntity getCandidateEducationByCandidateId(BigInteger candidateId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidateEducationEntity> query = cb.createQuery(CandidateEducationEntity.class);
		Root<CandidateEducationEntity> candidateEducation = query.from(CandidateEducationEntity.class);

		if (null != candidateId) {

			Predicate[] predicates = new Predicate[1];
			predicates[0] = cb.equal(candidateEducation.get("candidateId"), candidateId);
			cb.max(candidateEducation.get("lastUpdatedByDateTime"));

			query.select(candidateEducation).where(predicates);

		}

		// finalPredicate=cb.createQuery();
		
		List<CandidateEducationEntity> candidateEducations = entityManager.createQuery(query).getResultList();
		if (null != candidateEducations && !candidateEducations.isEmpty())
			return candidateEducations.get(0);
		else
			return new CandidateEducationEntity();

	}

}
