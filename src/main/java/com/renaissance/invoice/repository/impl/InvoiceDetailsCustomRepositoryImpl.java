package com.renaissance.invoice.repository.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;

import com.renaissance.invoice.model.InvoiceDetailsEntity;
import com.renaissance.invoice.repository.InvoiceDetailsCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

public class InvoiceDetailsCustomRepositoryImpl implements InvoiceDetailsCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Lazy
	InvoiceDetailsEntity invoice;
	
	public List<InvoiceDetailsEntity> getInvoicesForSelectedMonthAndYear(LocalDate monthYear){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<InvoiceDetailsEntity> query = cb
				.createQuery(InvoiceDetailsEntity.class);
		Root<InvoiceDetailsEntity> rcInvoice = query.from(InvoiceDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(rcInvoice.get("monthYear"), monthYear));
		
		
		List<InvoiceDetailsEntity> invoiceList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(rcInvoice).where(cb.and(predicates.toArray(predicatesArray)));
			invoiceList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(invoiceList))
			return invoiceList;

		else
			return new  ArrayList<InvoiceDetailsEntity>();
	}
	
	public InvoiceDetailsEntity getContractorInvoiceByMonthYearInvoiceNo(BigInteger contractorId, LocalDate monthYear) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<InvoiceDetailsEntity> query = cb
				.createQuery(InvoiceDetailsEntity.class);
		Root<InvoiceDetailsEntity> invoice = query.from(InvoiceDetailsEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if (!ProfileParserUtils.isObjectEmpty(contractorId))
			predicates.add(cb.equal(invoice.get("contractorId"), contractorId));
		if (!ProfileParserUtils.isObjectEmpty(monthYear))
			predicates.add(cb.equal(invoice.get("monthYear"), monthYear));
				
		List<InvoiceDetailsEntity> invoiceList = null;
		if (predicates.size() > 0) {
			//predicates.add(cb.equal(cb.upper(contractAbn.get("activeRecord")), "ACTIVE"));
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(invoice).where(cb.and(predicates.toArray(predicatesArray)));
			invoiceList = entityManager.createQuery(query).getResultList();
		}
		if (!ProfileParserUtils.isObjectEmpty(invoiceList))
			return invoiceList.get(0);

		else
			return new InvoiceDetailsEntity();
	}
	}

