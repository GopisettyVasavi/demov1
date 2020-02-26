package com.profile.parser.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.profile.parser.model.CandidatePersonalEntity;
import com.profile.parser.model.ProfileSearchForm;
import com.profile.parser.repository.CandidatePersonalRepository;
import com.profile.parser.repository.CandidatePersonalRespositoryCustom;
import com.profile.parser.util.ProfileParserUtils;
/**
 * This is a custom repository class for candidate personal repo.
 * @author Dell
 *
 */
@Repository
@Transactional(readOnly = true)
public class CandidatePersonalRespositoryCustomImpl  implements CandidatePersonalRespositoryCustom{
	private static final Logger logger=LoggerFactory.getLogger(CandidatePersonalRespositoryCustomImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Lazy
	CandidatePersonalRepository candidateRepo;
	/**
	 * This method will get candidate details for the given candidate name, email and phone number combination.
	 */	   
	  public CandidatePersonalEntity getCandidates(String candidateName,
	  String primaryEmail, String primaryPhone) {
	  
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<CandidatePersonalEntity> query = cb.createQuery(CandidatePersonalEntity.class);
	        Root<CandidatePersonalEntity> candidatePersonal = query.from(CandidatePersonalEntity.class);
	        
	        Predicate[] predicates = new Predicate[3];
	        predicates[0] =cb.like(cb.upper(candidatePersonal.get("candidateName")),"%"+candidateName.
		       		  toUpperCase()+"%");
	        predicates[1] =cb.like(cb.upper(candidatePersonal.get("primaryEmail")),"%"+primaryEmail.
	       		  toUpperCase()+"%");
	        predicates[2] =cb.like(candidatePersonal.get("primaryPhone"),primaryPhone);
			 
	        query.select(candidatePersonal).where(predicates);
	        
		/*
		 * Predicate finalPredicate=null; if(null!=candidateName &&
		 * !candidateName.isEmpty()) { Predicate predicateName =
		 * cb.like(cb.upper(candidatePersonal.get("candidateName")),"%"+candidateName.
		 * toUpperCase()+"%"); finalPredicate=cb.and(predicateName); }
		 * 
		 * if(null!=primaryEmail && !primaryEmail.isEmpty()) { Predicate predicateEmail
		 * = cb.like(cb.upper(candidatePersonal.get("primaryEmail")),primaryEmail.
		 * toUpperCase()); finalPredicate=cb.and(predicateEmail);}
		 * 
		 * if(null!=primaryPhone && !primaryPhone.isEmpty()) { Predicate predicatePhone
		 * = cb.like(candidatePersonal.get("primaryPhone"),primaryPhone);
		 * finalPredicate=cb.and(predicatePhone); }
		 * 
		 * // finalPredicate=cb.createQuery(); if(null!=finalPredicate)
		 * query.where(finalPredicate);
		 */
	        
	        List<CandidatePersonalEntity> candidatePersonalList=entityManager.createQuery(query)
	                .getResultList();
	        if(!ProfileParserUtils.isObjectEmpty(candidatePersonalList))
	        	return candidatePersonalList.get(0);
	        else 
		  return  new CandidatePersonalEntity();
	  
	  
	  }
	  /**
	   * This method will fetch candidates details for the values entered in search form.
	   */
	 public List<CandidatePersonalEntity> getCandidateProfiles(ProfileSearchForm searchForm){
		  CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<CandidatePersonalEntity> query = cb.createQuery(CandidatePersonalEntity.class);
	        Root<CandidatePersonalEntity> candidatePersonal = query.from(CandidatePersonalEntity.class);
	       
            List<Predicate> predicates = new ArrayList<Predicate>();
	       // Predicate[] predicates = new Predicate[3];
	        if(!ProfileParserUtils.isObjectEmpty(searchForm.getCandidateName()))
	        predicates.add(cb.like(cb.upper(candidatePersonal.get("candidateName")),"%"+searchForm.getCandidateName().
		       		  toUpperCase()+"%"));
	        if(!ProfileParserUtils.isObjectEmpty(searchForm.getPrimaryEmail()))
	        predicates.add(cb.like(cb.upper(candidatePersonal.get("primaryEmail")),"%"+searchForm.getPrimaryEmail().
	       		  toUpperCase()+"%"));
	        if(!ProfileParserUtils.isObjectEmpty(searchForm.getPrimaryContactNo()))
	        predicates.add(cb.like(candidatePersonal.get("primaryPhone"),searchForm.getPrimaryContactNo()));
			 
	        if(predicates.size()>0) {
	        Predicate[] predicatesArray = new Predicate[predicates.size()];
	        query.select(candidatePersonal).where( cb.or(predicates.toArray(predicatesArray)));
	        }
	        else query.select(candidatePersonal);
		  return entityManager.createQuery(query)
	                .getResultList();
	  }

}
