package com.renaissance.contractor.repository.impl;

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

import com.renaissance.contractor.model.ContractorPersonalDetailsEntity;
import com.renaissance.contractor.repository.ContractorPersonalDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorPersonalDetailsCustomRepositoryImpl implements ContractorPersonalDetailsCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorPersonalDetailsEntity contractorPersonal;

	@Override
	public ContractorPersonalDetailsEntity getPersonalDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorPersonalDetailsEntity> query = cb.createQuery(ContractorPersonalDetailsEntity.class);
		Root<ContractorPersonalDetailsEntity> contractorPersonal = query.from(ContractorPersonalDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(
					cb.equal(contractorPersonal.get("contractorId"),  contractorId ));
		List<ContractorPersonalDetailsEntity> contractorPersonalList = null;
		if (predicates.size() > 0) {
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractorPersonal).where(cb.and(predicates.toArray(predicatesArray)));
			contractorPersonalList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorPersonalList))
			return contractorPersonalList.get(0);

		else
			return new ContractorPersonalDetailsEntity();
	}

	@Override
	public List<ContractorPersonalDetailsEntity> getContractors(String firstName, String lastName, String dateOfBirth,
			String personalEmail) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorPersonalDetailsEntity> query = cb.createQuery(ContractorPersonalDetailsEntity.class);
		Root<ContractorPersonalDetailsEntity> contractorPersonal = query.from(ContractorPersonalDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(firstName))
			predicates.add(cb.equal(cb.upper(contractorPersonal.get("firstName")), firstName.toUpperCase()));
		if (!ProfileParserUtils.isObjectEmpty(lastName))
			predicates.add(cb.equal(cb.upper(contractorPersonal.get("lastName")), lastName.toUpperCase()));
		if (!ProfileParserUtils.isObjectEmpty(dateOfBirth))
			predicates.add(cb.equal(contractorPersonal.get("dateOfBirth"), dateOfBirth));
		if (!ProfileParserUtils.isObjectEmpty(personalEmail))
			predicates.add(cb.equal(contractorPersonal.get("personalEmail"), personalEmail));
		List<ContractorPersonalDetailsEntity> contractorPersonalList = null;
		if (predicates.size() > 0) {
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractorPersonal).where(cb.and(predicates.toArray(predicatesArray)));
			contractorPersonalList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorPersonalList)) {
			System.out.println(" Query ..."+contractorPersonalList.size());
			return contractorPersonalList;
		}
		else return new ArrayList<ContractorPersonalDetailsEntity>();
		
	}

	public List<ContractorPersonalDetailsEntity> searchContractors(String fullName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorPersonalDetailsEntity> query = cb.createQuery(ContractorPersonalDetailsEntity.class);
		Root<ContractorPersonalDetailsEntity> contractorPersonal = query.from(ContractorPersonalDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(fullName))
			predicates.add(
					cb.like(cb.upper(contractorPersonal.get("fullName")), "%" + fullName.toUpperCase() + "%"));

		if (predicates.size() > 0) {
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractorPersonal).where(cb.and(predicates.toArray(predicatesArray)));
			return entityManager.createQuery(query).getResultList();
		} else
			return new ArrayList<ContractorPersonalDetailsEntity>();
		// return null;
	}

}
