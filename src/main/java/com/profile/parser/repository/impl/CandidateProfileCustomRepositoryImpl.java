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

import com.profile.parser.model.CandidateProfileEntity;
import com.profile.parser.repository.CandidateProfileCustomRepository;
import com.profile.parser.repository.CandidateProfileRepository;

public class CandidateProfileCustomRepositoryImpl implements CandidateProfileCustomRepository{
	
	@PersistenceContext
    private EntityManager entityManager;
	@Lazy
	CandidateProfileRepository candaiteRepo;
	
	@Override
	public CandidateProfileEntity getCandidateProfileByCandidateId(BigInteger candidateId){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CandidateProfileEntity> query = cb.createQuery(CandidateProfileEntity.class);
        Root<CandidateProfileEntity> candidateProfile = query.from(CandidateProfileEntity.class);
        
       // Predicate finalPredicate=null;
        if(null!=candidateId) {
        	
        	 Predicate[] predicates = new Predicate[1];
 	        predicates[0] = cb.equal(candidateProfile.get("candidateId"),candidateId);
 	       cb.max(candidateProfile.get("lastUpdatedByDateTime"));
 	        cb.max(candidateProfile.get("version"));
 			 
 	        query.select(candidateProfile).where(predicates);
        
        }
       
        List<CandidateProfileEntity> candidateProfiles=entityManager.createQuery(query)
                .getResultList();
        if(null!=candidateProfiles && !candidateProfiles.isEmpty())
        	return candidateProfiles.get(0);
        else 
	  return  new CandidateProfileEntity();
	}
	
	

}
