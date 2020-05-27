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

import com.renaissance.profile.parser.model.CandidateWorkHistoryEntity;
import com.renaissance.profile.parser.repository.CandidateWorkHistoryCustomRepository;
import com.renaissance.profile.parser.repository.CandidateWorkHistoryRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

/**
 * Custom repository class for CandidateWorkHistory repository
 * @author Vasavi
 *
 */
public class CandidateWorkHistoryCustomRepositoryImpl implements CandidateWorkHistoryCustomRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Lazy
	CandidateWorkHistoryRepository candidateWorkRepo;
	
	/**
	 * This method will get candidate work history details for the given candidate Id.
	 */
		@Override
	public CandidateWorkHistoryEntity getCandidateWorkByCandidateId(BigInteger candidateId){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CandidateWorkHistoryEntity> query = cb.createQuery(CandidateWorkHistoryEntity.class);
        Root<CandidateWorkHistoryEntity> candidateProfile = query.from(CandidateWorkHistoryEntity.class);
        
       // Predicate finalPredicate=null;
        if(!ProfileParserUtils.isObjectEmpty(candidateId)) {
        	
        	 Predicate[] predicates = new Predicate[1];
 	        predicates[0] = cb.equal(candidateProfile.get("candidateId"),candidateId);
 	       cb.max(candidateProfile.get("lastUpdatedByDateTime"));
 	       
 			 
 	        query.select(candidateProfile).where(predicates);
        
        }
       
        List<CandidateWorkHistoryEntity> candidateProfiles=entityManager.createQuery(query)
                .getResultList();
        if(!ProfileParserUtils.isObjectEmpty(candidateProfiles))
        	return candidateProfiles.get(0);
        else 
	  return  new CandidateWorkHistoryEntity();
	}

}
