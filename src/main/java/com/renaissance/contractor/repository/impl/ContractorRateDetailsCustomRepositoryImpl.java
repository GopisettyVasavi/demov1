package com.renaissance.contractor.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.renaissance.contractor.model.ContractorRateDetailsEntity;
import com.renaissance.contractor.repository.ContractorRateDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorRateDetailsCustomRepositoryImpl implements ContractorRateDetailsCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorRateDetailsEntity contractorRate;
	
	@Override
	public ContractorRateDetailsEntity getRateDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorRateDetailsEntity> query = cb.createQuery(ContractorRateDetailsEntity.class);
		Root<ContractorRateDetailsEntity> contractRate = query.from(ContractorRateDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(
					cb.equal(contractRate.get("contractorId"),  contractorId ));
		List<ContractorRateDetailsEntity> contractorRateList = null;
		if (predicates.size() > 0) {
			predicates.add(
					cb.equal(cb.upper(contractRate.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractRate).where(cb.and(predicates.toArray(predicatesArray)));
			contractorRateList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorRateList))
			return contractorRateList.get(0);

		else
			return new ContractorRateDetailsEntity();
	}
	
	@Override
	public List<ContractorRateDetailsEntity> getAllRateDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorRateDetailsEntity> query = cb.createQuery(ContractorRateDetailsEntity.class);
		Root<ContractorRateDetailsEntity> contractRate = query.from(ContractorRateDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(
					cb.equal(contractRate.get("contractorId"),  contractorId ));
		List<ContractorRateDetailsEntity> contractorRateList = null;
		if (predicates.size() > 0) {
			
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractRate).where(cb.and(predicates.toArray(predicatesArray)));
			contractorRateList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorRateList))
			return contractorRateList;

		else
			return new ArrayList<ContractorRateDetailsEntity>();
	}
	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorRateDetailsEntity> delete = cb.createCriteriaDelete(ContractorRateDetailsEntity.class);
		Root<ContractorRateDetailsEntity> contractorRate = delete.from(ContractorRateDetailsEntity.class);
		delete.where(cb.equal(contractorRate.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}

}
