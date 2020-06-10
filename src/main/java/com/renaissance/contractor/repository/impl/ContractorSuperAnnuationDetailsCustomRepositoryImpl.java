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

import com.renaissance.contractor.model.ContractorSuperAnnuationDetailsEntity;
import com.renaissance.contractor.repository.ContractorSuperAnnuationDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorSuperAnnuationDetailsCustomRepositoryImpl
		implements ContractorSuperAnnuationDetailsCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorSuperAnnuationDetailsEntity contractorSADetails;

	/**
	 * This method will search Active SA details of selected contractor and return.
	 */
	@Override
	public ContractorSuperAnnuationDetailsEntity getSADetailsByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorSuperAnnuationDetailsEntity> query = cb
				.createQuery(ContractorSuperAnnuationDetailsEntity.class);
		Root<ContractorSuperAnnuationDetailsEntity> contractSA = query
				.from(ContractorSuperAnnuationDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractSA.get("contractorId"), contractorId));
		List<ContractorSuperAnnuationDetailsEntity> contractorSAList = null;
		if (predicates.size() > 0) {
			predicates.add(cb.equal(cb.upper(contractSA.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractSA).where(cb.and(predicates.toArray(predicatesArray)));
			contractorSAList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorSAList))
			return contractorSAList.get(0);

		else
			return new ContractorSuperAnnuationDetailsEntity();
	}

	/**
	 * This method will return all SA details of contractor.
	 */
	@Override
	public List<ContractorSuperAnnuationDetailsEntity> getAllSADetailsByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorSuperAnnuationDetailsEntity> query = cb
				.createQuery(ContractorSuperAnnuationDetailsEntity.class);
		Root<ContractorSuperAnnuationDetailsEntity> contractSA = query
				.from(ContractorSuperAnnuationDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractSA.get("contractorId"), contractorId));
		List<ContractorSuperAnnuationDetailsEntity> contractorSAList = null;
		if (predicates.size() > 0) {
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractSA).where(cb.and(predicates.toArray(predicatesArray)));
			contractorSAList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorSAList))
			return contractorSAList;

		else
			return new ArrayList<ContractorSuperAnnuationDetailsEntity>();
	}

	/**
	 * This method will delete SA details for given contractor
	 */
	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorSuperAnnuationDetailsEntity> delete = cb
				.createCriteriaDelete(ContractorSuperAnnuationDetailsEntity.class);
		Root<ContractorSuperAnnuationDetailsEntity> contractorSA = delete
				.from(ContractorSuperAnnuationDetailsEntity.class);
		delete.where(cb.equal(contractorSA.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}

}
