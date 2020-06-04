package com.renaissance.contractor.repository.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.renaissance.contractor.model.ContractorBankDetailsEntity;
import com.renaissance.contractor.repository.ContractorBankDetailsCustomRepository;

public class ContractorBankDetailsCustomRepositoryImpl implements ContractorBankDetailsCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorBankDetailsEntity contractorBank;
	
	@Override
	public ContractorBankDetailsEntity getBankDetailsByContractorId(BigInteger contractorId) {
		return null;
	}
	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorBankDetailsEntity> delete = cb.createCriteriaDelete(ContractorBankDetailsEntity.class);
		Root<ContractorBankDetailsEntity> contractorBank = delete.from(ContractorBankDetailsEntity.class);
		delete.where(cb.equal(contractorBank.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}

}
