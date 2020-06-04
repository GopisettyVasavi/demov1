package com.renaissance.contractor.repository.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.renaissance.contractor.model.ContractorABNDetailsEntity;
import com.renaissance.contractor.repository.ContractorAbnDetailsCustomRepository;

public class ContractorAbnDetailsCustomRepositoryImpl implements ContractorAbnDetailsCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorABNDetailsEntity contractorAbnDetails;

	@Override
	public ContractorABNDetailsEntity getAbnDetailsByContractorId(BigInteger contractorId) {
		return null;
	}

	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorABNDetailsEntity> delete = cb.createCriteriaDelete(ContractorABNDetailsEntity.class);
		Root<ContractorABNDetailsEntity> contractorAbn = delete.from(ContractorABNDetailsEntity.class);
		delete.where(cb.equal(contractorAbn.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}

}
