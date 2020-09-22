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

import com.renaissance.commission.model.CommissionsDetailsEntity;
import com.renaissance.commission.repository.CommissionsDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class CommissionsDetailsCustomRepositoryImpl implements CommissionsDetailsCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Lazy
	CommissionsDetailsEntity commission;
/**
 * This method will get commission details of contractor by given month year and job start date.
 */
	@Override
	public CommissionsDetailsEntity getCommissionByContractorMonthYear(BigInteger contractorId, String monthYear, Double ratePerDay, String jobStartDate) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CommissionsDetailsEntity> query = cb
				.createQuery(CommissionsDetailsEntity.class);
		Root<CommissionsDetailsEntity> rcCommission = query.from(CommissionsDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(rcCommission.get("contractorId"), contractorId));
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(rcCommission.get("monthYear"), monthYear));
		if (!ProfileParserUtils.isObjectEmpty(ratePerDay))
			predicates.add(cb.equal(rcCommission.get("ratePerDay"), ratePerDay));
		if (!ProfileParserUtils.isObjectEmpty(jobStartDate))
			predicates.add(cb.equal(rcCommission.get("jobStartDate"), jobStartDate));
		List<CommissionsDetailsEntity> commissionList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(rcCommission).where(cb.and(predicates.toArray(predicatesArray)));
			commissionList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(commissionList))
			return commissionList.get(0);

		else
			return new CommissionsDetailsEntity();
	}
	
	@Override
	public List<CommissionsDetailsEntity> getCommissionsForSelectedMonthAndYear(String monthYear, String recruiterName){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CommissionsDetailsEntity> query = cb
				.createQuery(CommissionsDetailsEntity.class);
		Root<CommissionsDetailsEntity> rcCommission = query.from(CommissionsDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(rcCommission.get("monthYear"), monthYear));
		
		if (!ProfileParserUtils.isObjectEmpty(recruiterName)) {
			predicates.add(cb.like(cb.upper(rcCommission.get("recruiterName")),
					"%" + recruiterName.toUpperCase() + "%"));
		}
			//predicates.add(cb.like(cb.upper(rcCommission.get("recruiterName")), "%" +recruiterName.toUpperCase()+"%" ));
		//criteriaBuilder.equal(criteriaBuilder.upper(contractorEmp.get("activeRecord")), "ACTIVE");
		List<CommissionsDetailsEntity> commissionList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(rcCommission).where(cb.and(predicates.toArray(predicatesArray)));
			commissionList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(commissionList))
			return commissionList;

		else
			return new  ArrayList<CommissionsDetailsEntity>();
	}
		
	}
