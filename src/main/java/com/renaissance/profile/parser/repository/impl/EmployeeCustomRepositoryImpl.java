package com.renaissance.profile.parser.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renaissance.profile.parser.model.EmployeeEntity;
import com.renaissance.profile.parser.repository.EmployeeCustomRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;
/**
 * Custom repository class for Employee Repository.
 * @author Vasavi
 *
 */
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
	@PersistenceContext
	private EntityManager entityManager;
	private static final Logger logger=LoggerFactory.getLogger(EmployeeCustomRepositoryImpl.class);

	/**
	 * This method will fetch employee details for the logged in user name and password.
	 */
	public EmployeeEntity getEmployeeDetails(String loggedInUserName, String loginPwd) throws Exception {
		
		logger.info("Inside entity...{}",loggedInUserName);
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
	/**
	 * This method will return list of Recruiters.
	 */
public List<EmployeeEntity> getRecruitersList() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);
		Root<EmployeeEntity> employeeEntity = query.from(EmployeeEntity.class);
		 Predicate[] predicates = new Predicate[1];
	        predicates[0] = cb.equal(employeeEntity.get("employeeRole"),"Recruiter");
	        query.select(employeeEntity).where(predicates);
		List<EmployeeEntity> employees=entityManager.createQuery(query).getResultList();
		if (!ProfileParserUtils.isObjectEmpty(employees))
			return employees;
		else return null;
		
	}
}
