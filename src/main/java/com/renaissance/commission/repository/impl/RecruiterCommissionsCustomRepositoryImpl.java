package com.renaissance.commission.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.renaissance.commission.model.RecruiterCommissionsEntity;
import com.renaissance.commission.model.SearchCommissionForm;
import com.renaissance.commission.repository.RecruiterCommissionsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class RecruiterCommissionsCustomRepositoryImpl implements RecruiterCommissionsCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<RecruiterCommissionsEntity> searchCommissions(SearchCommissionForm searchCommissionForm){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecruiterCommissionsEntity> query = cb
				.createQuery(RecruiterCommissionsEntity.class);
		Root<RecruiterCommissionsEntity> rcCommission = query.from(RecruiterCommissionsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!ProfileParserUtils.isObjectEmpty(searchCommissionForm)) {
			if(!ProfileParserUtils.isObjectEmpty(searchCommissionForm.getStartDate()) && !ProfileParserUtils.isObjectEmpty(searchCommissionForm.getEndDate())){
				predicates.add(cb.between(rcCommission.get("monthYear"), searchCommissionForm.getStartDate(), searchCommissionForm.getEndDate()));

				
			}
			if(!ProfileParserUtils.isObjectEmpty(searchCommissionForm.getRecruiterName())){
				
				predicates.add(cb.equal(cb.upper(rcCommission.get("recruiterName")), searchCommissionForm.getRecruiterName().toUpperCase()));
			}
		}
		
		List<RecruiterCommissionsEntity> commissionList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(rcCommission).where(cb.and(predicates.toArray(predicatesArray)));
			commissionList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(commissionList))
			return commissionList;

		else
			return new  ArrayList<RecruiterCommissionsEntity>();
	}
	
	public RecruiterCommissionsEntity getRecruiterCommissionByMonthYearAndRecruiter(LocalDate monthYear, String recruiterName) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecruiterCommissionsEntity> query = cb
				.createQuery(RecruiterCommissionsEntity.class);
		Root<RecruiterCommissionsEntity> rcCommission = query.from(RecruiterCommissionsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(rcCommission.get("monthYear"), monthYear));
		
		if (!ProfileParserUtils.isObjectEmpty(recruiterName))
			predicates.add(cb.equal(rcCommission.get("recruiterName"), recruiterName));
		List<RecruiterCommissionsEntity> commissionList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(rcCommission).where(cb.and(predicates.toArray(predicatesArray)));
			commissionList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(commissionList))
			return commissionList.get(0);

		else
			return new RecruiterCommissionsEntity();
	}
}
