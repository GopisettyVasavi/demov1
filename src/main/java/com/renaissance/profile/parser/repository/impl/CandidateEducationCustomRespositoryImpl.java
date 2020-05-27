package com.renaissance.profile.parser.repository.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;

import com.renaissance.profile.parser.model.CandidateEducationEntity;
import com.renaissance.profile.parser.repository.CandidateEducationCustomRespository;
import com.renaissance.profile.parser.repository.CandidateEducationRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;
/**
 * This is a custom repository for candidate education repository class.
 * @author Dell
 *
 */
public class CandidateEducationCustomRespositoryImpl implements CandidateEducationCustomRespository {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	CandidateEducationRepository candidateEducation;

	/**
	 * This method will get candidate education details for the given candidate Id.
	 */
	@Override
	public CandidateEducationEntity getCandidateEducationByCandidateId(BigInteger candidateId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidateEducationEntity> query = cb.createQuery(CandidateEducationEntity.class);
		Root<CandidateEducationEntity> candidateEducation = query.from(CandidateEducationEntity.class);

		if (!ProfileParserUtils.isObjectEmpty( candidateId)) {

			Predicate[] predicates = new Predicate[1];
			predicates[0] = cb.equal(candidateEducation.get("candidateId"), candidateId);
			cb.max(candidateEducation.get("lastUpdatedByDateTime"));

			query.select(candidateEducation).where(predicates);

		}

		// finalPredicate=cb.createQuery();
		
		List<CandidateEducationEntity> candidateEducations = entityManager.createQuery(query).getResultList();
		if (!ProfileParserUtils.isObjectEmpty(candidateEducations))
			return candidateEducations.get(0);
		else
			return new CandidateEducationEntity();

	}

}
