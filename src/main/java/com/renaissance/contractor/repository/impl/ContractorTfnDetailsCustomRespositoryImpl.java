package com.renaissance.contractor.repository.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.renaissance.contractor.model.ContractorTFNDetailsEntity;
import com.renaissance.contractor.repository.ContractorTfnDetailsCustomRepository;

public class ContractorTfnDetailsCustomRespositoryImpl implements ContractorTfnDetailsCustomRepository  {
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorTFNDetailsEntity contractorTfn;
	@Override
	public ContractorTFNDetailsEntity getTfnDetailsByContractorId(BigInteger contractorId) {
		return null;
	}
	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorTFNDetailsEntity> delete = cb.createCriteriaDelete(ContractorTFNDetailsEntity.class);
		Root<ContractorTFNDetailsEntity> contractorTfn = delete.from(ContractorTFNDetailsEntity.class);
		delete.where(cb.equal(contractorTfn.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}
}
