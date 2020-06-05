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

import com.renaissance.contractor.model.ContractorBankDetailsEntity;
import com.renaissance.contractor.repository.ContractorBankDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorBankDetailsCustomRepositoryImpl implements ContractorBankDetailsCustomRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorBankDetailsEntity contractorBank;
	
	@Override
	public ContractorBankDetailsEntity getBankDetailsByContractorId(BigInteger contractorId) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorBankDetailsEntity> query = cb
				.createQuery(ContractorBankDetailsEntity.class);
		Root<ContractorBankDetailsEntity> contractBank = query.from(ContractorBankDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractBank.get("contractorId"), contractorId));
		List<ContractorBankDetailsEntity> contractorBankList = null;
		if (predicates.size() > 0) {
			predicates.add(cb.equal(cb.upper(contractBank.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractBank).where(cb.and(predicates.toArray(predicatesArray)));
			contractorBankList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorBankList))
			return contractorBankList.get(0);

		else
			return new ContractorBankDetailsEntity();
		
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
