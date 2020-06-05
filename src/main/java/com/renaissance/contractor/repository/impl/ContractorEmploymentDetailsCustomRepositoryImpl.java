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

import com.renaissance.contractor.model.ContractorEmploymentDetailsEntity;
import com.renaissance.contractor.model.ContractorSearchForm;
import com.renaissance.contractor.repository.ContractorEmploymentDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class ContractorEmploymentDetailsCustomRepositoryImpl implements ContractorEmploymentDetailsCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Lazy
	ContractorEmploymentDetailsEntity contractorEmployment;

	@Override
	public ContractorEmploymentDetailsEntity getEmploymentDetailsByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorEmploymentDetailsEntity> query = cb
				.createQuery(ContractorEmploymentDetailsEntity.class);
		Root<ContractorEmploymentDetailsEntity> contractEmp = query.from(ContractorEmploymentDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(contractEmp.get("contractorId"), contractorId));
		List<ContractorEmploymentDetailsEntity> contractorEmpList = null;
		if (predicates.size() > 0) {
			predicates.add(cb.equal(cb.upper(contractEmp.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractEmp).where(cb.and(predicates.toArray(predicatesArray)));
			contractorEmpList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(contractorEmpList))
			return contractorEmpList.get(0);

		else
			return new ContractorEmploymentDetailsEntity();

	}

	@Transactional
	public void deleteByContractorId(BigInteger contractorId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ContractorEmploymentDetailsEntity> delete = cb
				.createCriteriaDelete(ContractorEmploymentDetailsEntity.class);
		Root<ContractorEmploymentDetailsEntity> contractorEmp = delete.from(ContractorEmploymentDetailsEntity.class);
		delete.where(cb.equal(contractorEmp.get("contractorId"), contractorId));
		entityManager.createQuery(delete).executeUpdate();
	}

	public List<ContractorEmploymentDetailsEntity> searchEmploymentDetails(ContractorSearchForm searchForm) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ContractorEmploymentDetailsEntity> query = cb
				.createQuery(ContractorEmploymentDetailsEntity.class);
		Root<ContractorEmploymentDetailsEntity> contractorEmp = query.from(ContractorEmploymentDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(searchForm)) {
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getClientName()))
				predicates.add(cb.like(cb.upper(contractorEmp.get("clientName")),
						"%" + searchForm.getClientName().toUpperCase() + "%"));
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getEndClientName()))
				predicates.add(cb.like(cb.upper(contractorEmp.get("endClientName")),
						"%" + searchForm.getEndClientName().toUpperCase() + "%"));
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getWorkLocationState()))
				predicates.add(cb.like(cb.upper(contractorEmp.get("workLocationState")),
						"%" + searchForm.getWorkLocationState().toUpperCase() + "%"));
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getRole()))
				predicates.add(cb.like(cb.upper(contractorEmp.get("jobRole")),
						"%" + searchForm.getRole().toUpperCase() + "%"));
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getRecruiterId()))
				predicates.add(cb.equal(contractorEmp.get("recruiterId"), searchForm.getRecruiterId()));
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getJobStartDate()))
				predicates.add(cb.like(cb.upper(contractorEmp.get("jobStartDate")),
						"%" + searchForm.getJobStartDate().substring(2) + "%"));
			if (!ProfileParserUtils.isObjectEmpty(searchForm.getJobEndDate()))
				predicates.add(cb.like(cb.upper(contractorEmp.get("jobEndDate")),
						"%" + searchForm.getJobEndDate().substring(2) + "%"));

		}

		if (predicates.size() > 0) {
			predicates.add(cb.equal(cb.upper(contractorEmp.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(contractorEmp).where(cb.and(predicates.toArray(predicatesArray)));
			return entityManager.createQuery(query).getResultList();
		} else
			return new ArrayList<ContractorEmploymentDetailsEntity>();
		// return null;
	}

}