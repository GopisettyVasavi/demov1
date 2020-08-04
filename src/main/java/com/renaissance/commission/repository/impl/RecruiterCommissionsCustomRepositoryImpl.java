package com.renaissance.commission.repository.impl;

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

import com.renaissance.commission.model.RecruiterCommissionsEntity;
import com.renaissance.commission.repository.RecruiterCommissionsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class RecruiterCommissionsCustomRepositoryImpl implements RecruiterCommissionsCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Lazy
	RecruiterCommissionsEntity commission;

	@Override
	public RecruiterCommissionsEntity getCommissionByContractorMonthYear(BigInteger contractorId, String monthYear, Double ratePerDay, String jobStartDate) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecruiterCommissionsEntity> query = cb
				.createQuery(RecruiterCommissionsEntity.class);
		Root<RecruiterCommissionsEntity> rcCommission = query.from(RecruiterCommissionsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(rcCommission.get("contractorId"), contractorId));
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(rcCommission.get("monthYear"), monthYear));
		if (!ProfileParserUtils.isObjectEmpty(ratePerDay))
			predicates.add(cb.equal(rcCommission.get("ratePerDay"), ratePerDay));
		if (!ProfileParserUtils.isObjectEmpty(jobStartDate))
			predicates.add(cb.equal(rcCommission.get("jobStartDate"), jobStartDate));
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
	
	@Override
	public List<RecruiterCommissionsEntity> getCommissionsForSelectedMonthAndYear(String monthYear){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecruiterCommissionsEntity> query = cb
				.createQuery(RecruiterCommissionsEntity.class);
		Root<RecruiterCommissionsEntity> rcCommission = query.from(RecruiterCommissionsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(rcCommission.get("monthYear"), monthYear));
		
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
		
	}
