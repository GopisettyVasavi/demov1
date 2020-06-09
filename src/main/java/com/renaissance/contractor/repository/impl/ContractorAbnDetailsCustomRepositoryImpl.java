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

import com.renaissance.contractor.model.ContractorABNDetailsEntity;
import com.renaissance.contractor.repository.ContractorAbnDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorAbnDetailsCustomRepositoryImpl implements ContractorAbnDetailsCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorABNDetailsEntity contractorAbnDetails;

	@Override
	public ContractorABNDetailsEntity getAbnDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorABNDetailsEntity> query = cb
				.createQuery(ContractorABNDetailsEntity.class);
		Root<ContractorABNDetailsEntity> contractAbn = query.from(ContractorABNDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractAbn.get("contractorId"), contractorId));
		List<ContractorABNDetailsEntity> contractorAbnList = null;
		if (predicates.size() > 0) {
			predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractAbn).where(cb.and(predicates.toArray(predicatesArray)));
			contractorAbnList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorAbnList))
			return contractorAbnList.get(0);

		else
			return new ContractorABNDetailsEntity();
	}

	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorABNDetailsEntity> delete = cb.createCriteriaDelete(ContractorABNDetailsEntity.class);
		Root<ContractorABNDetailsEntity> contractorAbn = delete.from(ContractorABNDetailsEntity.class);
		delete.where(cb.equal(contractorAbn.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}
	
	@Override
	public List<ContractorABNDetailsEntity> getAllAbnDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorABNDetailsEntity> query = cb
				.createQuery(ContractorABNDetailsEntity.class);
		Root<ContractorABNDetailsEntity> contractAbn = query.from(ContractorABNDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractAbn.get("contractorId"), contractorId));
		List<ContractorABNDetailsEntity> contractorAbnList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractAbn).where(cb.and(predicates.toArray(predicatesArray)));
			contractorAbnList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorAbnList))
			return contractorAbnList;

		else
			return new ArrayList<ContractorABNDetailsEntity>();
	}

}
