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
import com.profile.parser.util.ProfileParserUtils;
/**
 * Custom repository class for Employee Repository.
 * @author Vasavi
 *
 */
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * This method will fetch employee details for the logged in user name and password.
	 */
	public EmployeeEntity getEmployeeDetails(String loggedInUserName, String loginPwd) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);
		Root<EmployeeEntity> employeeEntity = query.from(EmployeeEntity.class);
		if(!ProfileParserUtils.isObjectEmpty(loggedInUserName)) {
			Predicate[] predicates = new Predicate[2];
			predicates[0] = cb.equal(cb.upper(employeeEntity.get("profileParserAppLogin")),
					loggedInUserName.toUpperCase());
			predicates[1] = cb.equal(employeeEntity.get("profileParserAppPwd"),
					loginPwd);

			query.select(employeeEntity).where(predicates);
			
			
		}
		List<EmployeeEntity> employees=entityManager.createQuery(query).getResultList();
		if (!ProfileParserUtils.isObjectEmpty(employees))
			return employees.get(0);
		else return null;
		
	}
}
