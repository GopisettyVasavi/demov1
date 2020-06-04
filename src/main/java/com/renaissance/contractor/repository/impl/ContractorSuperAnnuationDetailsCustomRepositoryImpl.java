package com.renaissance.contractor.repository.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.renaissance.contractor.model.ContractorSuperAnnuationDetailsEntity;
import com.renaissance.contractor.repository.ContractorSuperAnnuationDetailsCustomRepository;

public class ContractorSuperAnnuationDetailsCustomRepositoryImpl implements ContractorSuperAnnuationDetailsCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorSuperAnnuationDetailsEntity contractorSADetails;
	@Override
	public ContractorSuperAnnuationDetailsEntity getSADetailsByContractorId(BigInteger contractorId) {
		return null;
	}
	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorSuperAnnuationDetailsEntity> delete = cb.createCriteriaDelete(ContractorSuperAnnuationDetailsEntity.class);
		Root<ContractorSuperAnnuationDetailsEntity> contractorSA = delete.from(ContractorSuperAnnuationDetailsEntity.class);
		delete.where(cb.equal(contractorSA.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}
	

}
