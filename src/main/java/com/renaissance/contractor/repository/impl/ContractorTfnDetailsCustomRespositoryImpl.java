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

import com.renaissance.contractor.model.ContractorTFNDetailsEntity;
import com.renaissance.contractor.repository.ContractorTfnDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorTfnDetailsCustomRespositoryImpl implements ContractorTfnDetailsCustomRepository  {
	
	//private static final Logger logger = LoggerFactory.getLogger(ContractorTfnDetailsCustomRespositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorTFNDetailsEntity contractorTfn;
	
	
	/*
	 * @Override public ContractorTFNDetailsEntity
	 * getTfnDetailsByContractorId(BigInteger contractorId) { CriteriaBuilder cb =
	 * entityManager.getCriteriaBuilder(); CriteriaQuery<ContractorTFNDetailsEntity>
	 * query = cb .createQuery(ContractorTFNDetailsEntity.class);
	 * Root<ContractorTFNDetailsEntity> contractTfn =
	 * query.from(ContractorTFNDetailsEntity.class);
	 * 
	 * List<Predicate> predicates = new ArrayList<Predicate>(); if
	 * (!ProfileParserUtils.isObjectEmpty(contractorId)) predicates.add(
	 * cb.equal(contractTfn.get("contractorId"), contractorId )); predicates.add(
	 * cb.equal(cb.upper(contractTfn.get("activeRecord")), "ACTIVE"));
	 * List<ContractorTFNDetailsEntity> contractorTfnList = null;
	 * logger.info("PREDICATES... ,{}",predicates.size()); if (predicates.size() >
	 * 0) { predicates.add( cb.equal(cb.upper(contractTfn.get("activeRecord")),
	 * "ACTIVE")); Predicate[] predicatesArray = new Predicate[predicates.size()];
	 * query.select(contractTfn).where(cb.and(predicates.toArray(predicatesArray)));
	 * //contractorTfnList = entityManager.createQuery(query).getResultList();
	 * ContractorTFNDetailsEntity
	 * v=entityManager.createQuery(query).getResultList().get(0); } if
	 * (!ProfileParserUtils.isObjectEmpty(contractorTfnList)) return
	 * contractorTfnList.get(0);
	 * 
	 * else return new ContractorTFNDetailsEntity(); }
	 */
	
	
	@Override
	public ContractorTFNDetailsEntity getTfnDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorTFNDetailsEntity> query = cb
				.createQuery(ContractorTFNDetailsEntity.class);
		Root<ContractorTFNDetailsEntity> contractTfn = query.from(ContractorTFNDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractTfn.get("contractorId"), contractorId));
		List<ContractorTFNDetailsEntity> contractorTfnList = null;
		if (predicates.size() > 0) {
			predicates.add(
					cb.equal(cb.upper(contractTfn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractTfn).where(cb.and(predicates.toArray(predicatesArray)));
			contractorTfnList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorTfnList))
			return contractorTfnList.get(0);

		else
			return new ContractorTFNDetailsEntity();
	}
	
	@Override
	public List<ContractorTFNDetailsEntity> getAllTfnDetailsByContractorId(BigInteger contractorId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorTFNDetailsEntity> query = cb
				.createQuery(ContractorTFNDetailsEntity.class);
		Root<ContractorTFNDetailsEntity> contractTfn = query.from(ContractorTFNDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractTfn.get("contractorId"), contractorId));
		List<ContractorTFNDetailsEntity> contractorTfnList = null;
		if (predicates.size() > 0) {
			/*
			 * predicates.add( cb.equal(cb.upper(contractTfn.get("activeRecord")),
			 * "ACTIVE"));
			 */
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractTfn).where(cb.and(predicates.toArray(predicatesArray)));
			contractorTfnList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorTfnList))
			return contractorTfnList;

		else
			return new ArrayList<ContractorTFNDetailsEntity>();
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
