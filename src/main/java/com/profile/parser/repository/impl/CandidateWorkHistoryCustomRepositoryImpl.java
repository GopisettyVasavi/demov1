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

import com.profile.parser.model.CandidateWorkHistoryEntity;
import com.profile.parser.repository.CandidateWorkHistoryCustomRepository;
import com.profile.parser.repository.CandidateWorkHistoryRepository;

public class CandidateWorkHistoryCustomRepositoryImpl implements CandidateWorkHistoryCustomRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Lazy
	CandidateWorkHistoryRepository candidateWorkRepo;
	@Override
	public CandidateWorkHistoryEntity getCandidateWorkByCandidateId(BigInteger candidateId){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CandidateWorkHistoryEntity> query = cb.createQuery(CandidateWorkHistoryEntity.class);
        Root<CandidateWorkHistoryEntity> candidateProfile = query.from(CandidateWorkHistoryEntity.class);
        
       // Predicate finalPredicate=null;
        if(null!=candidateId) {
        	
        	 Predicate[] predicates = new Predicate[1];
 	        predicates[0] = cb.equal(candidateProfile.get("candidateId"),candidateId);
 	       cb.max(candidateProfile.get("lastUpdatedByDateTime"));
 	       
 			 
 	        query.select(candidateProfile).where(predicates);
        
        }
       
        List<CandidateWorkHistoryEntity> candidateProfiles=entityManager.createQuery(query)
                .getResultList();
        if(null!=candidateProfiles && !candidateProfiles.isEmpty())
        	return candidateProfiles.get(0);
        else 
	  return  new CandidateWorkHistoryEntity();
	}

}
