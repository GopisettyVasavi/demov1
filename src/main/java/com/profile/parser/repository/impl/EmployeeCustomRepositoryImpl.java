package com.profile.parser.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.profile.parser.model.CandidateEducationEntity;
import com.profile.parser.model.EmployeeEntity;
import com.profile.parser.repository.EmployeeCustomRepository;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	public EmployeeEntity getEmployeeDetails(String loggedInUserName, String loginPwd) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);
		Root<EmployeeEntity> employeeEntity = query.from(EmployeeEntity.class);
		if(null!=loggedInUserName) {
			Predicate[] predicates = new Predicate[2];
			predicates[0] = cb.equal(cb.upper(employeeEntity.get("profileParserAppLogin")),
					loggedInUserName.toUpperCase());
			predicates[1] = cb.equal(employeeEntity.get("profileParserAppPwd"),
					loginPwd);

			query.select(employeeEntity).where(predicates);
			
			
		}
		List<EmployeeEntity> employees=entityManager.createQuery(query).getResultList();
		if (null != employees && !employees.isEmpty())
			return employees.get(0);
		else return null;
		
	}
}
