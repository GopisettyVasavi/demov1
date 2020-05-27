package com.renaissance.profile.parser.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Lazy;

import com.renaissance.profile.parser.model.CandidateProfileEntity;
import com.renaissance.profile.parser.model.ProfileSearchForm;
import com.renaissance.profile.parser.repository.CandidateProfileCustomRepository;
import com.renaissance.profile.parser.repository.CandidateProfileRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

/**
 * This class is a custom repository for candidateprofile repository.
 * 
 * @author Dell
 *
 */
public class CandidateProfileCustomRepositoryImpl implements CandidateProfileCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;
	@Lazy
	CandidateProfileRepository candaiteRepo;

	/**
	 * This method will fetch candidate profiles for the given candidate Id.
	 */
	@Override
	public CandidateProfileEntity getCandidateProfileByCandidateId(BigInteger candidateId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidateProfileEntity> query = cb.createQuery(CandidateProfileEntity.class);
		Root<CandidateProfileEntity> candidateProfile = query.from(CandidateProfileEntity.class);

		// Predicate finalPredicate=null;
		if (!ProfileParserUtils.isObjectEmpty(candidateId)) {

			Predicate[] predicates = new Predicate[1];
			predicates[0] = cb.equal(candidateProfile.get("candidateId"), candidateId);
			cb.max(candidateProfile.get("lastUpdatedByDateTime"));
			cb.max(candidateProfile.get("version"));

			query.select(candidateProfile).where(predicates);

		}

		List<CandidateProfileEntity> candidateProfiles = entityManager.createQuery(query).getResultList();
		if (!ProfileParserUtils.isObjectEmpty(candidateProfiles))
			return candidateProfiles.get(0);
		else
			return new CandidateProfileEntity();
	}

	/**
	 * This method will search Candidate profile table for the given search results.
	 */
	public List<CandidateProfileEntity> getCandidateProfiles(ProfileSearchForm searchForm) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CandidateProfileEntity> query = cb.createQuery(CandidateProfileEntity.class);
		Root<CandidateProfileEntity> candidateProfile = query.from(CandidateProfileEntity.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		// Process if Skill is entered in search
		if (!ProfileParserUtils.isObjectEmpty(searchForm) && !ProfileParserUtils.isObjectEmpty(searchForm.getSkill())) {
			if (!searchForm.getSkill().contains("&&") && !searchForm.getSkill().contains("||")
					&& !searchForm.getSkill().contains("!")) {
				predicates.add(cb.like(cb.upper(candidateProfile.get("skills")),
						"%" + searchForm.getSkill().toUpperCase() + "%"));
				predicates.add(cb.like(cb.upper(candidateProfile.get("profileText")),
						"%" + searchForm.getSkill().toUpperCase() + "%"));

			} else if (searchForm.getSkill().contains("&&")) {
				List<String> skillListWithAnd = ProfileParserUtils.processSearchStringWithAND(searchForm.getSkill());
				for (String skill : skillListWithAnd) {
					predicates.add(cb.like(cb.upper(candidateProfile.get("skills")), "%" + skill.toUpperCase() + "%"));
					predicates.add(
							cb.like(cb.upper(candidateProfile.get("profileText")), "%" + skill.toUpperCase() + "%"));
				}
			} else if (searchForm.getSkill().contains("||")) {
				List<String> skillListWithOr = ProfileParserUtils.processSearchStringWithOR(searchForm.getSkill());
				for (String skill : skillListWithOr) {
					predicates.add(cb.like(cb.upper(candidateProfile.get("skills")), "%" + skill.toUpperCase() + "%"));
					predicates.add(
							cb.like(cb.upper(candidateProfile.get("profileText")), "%" + skill.toUpperCase() + "%"));
				}

			} else if (searchForm.getSkill().contains("!")) {
				List<String> skillListWithNot = ProfileParserUtils.processSearchStringWithNOT(searchForm.getSkill());
				for (String skill : skillListWithNot) {
					predicates
							.add(cb.notLike(cb.upper(candidateProfile.get("skills")), "%" + skill.toUpperCase() + "%"));
					predicates.add(
							cb.notLike(cb.upper(candidateProfile.get("profileText")), "%" + skill.toUpperCase() + "%"));
				}

			}

		}

		if (!ProfileParserUtils.isObjectEmpty(searchForm)
				&& !ProfileParserUtils.isObjectEmpty(searchForm.getAvailability())) {

			predicates.add(cb.like(cb.upper(candidateProfile.get("availability")),
					"%" + searchForm.getAvailability().toUpperCase() + "%"));

		}

		if (!ProfileParserUtils.isObjectEmpty(searchForm)
				&& !ProfileParserUtils.isObjectEmpty(searchForm.getCertification())) {

			predicates.add(cb.like(cb.upper(candidateProfile.get("certification")),
					"%" + searchForm.getCertification().toUpperCase() + "%"));

		}

		if (!ProfileParserUtils.isObjectEmpty(searchForm)
				&& !ProfileParserUtils.isObjectEmpty(searchForm.getCurrentLocation())) {
			if (!searchForm.getCurrentLocation().contains("&&") && !searchForm.getCurrentLocation().contains("||")
					&& !searchForm.getCurrentLocation().contains("!")) {
				predicates.add(cb.like(cb.upper(candidateProfile.get("profileText")),
						"%" + searchForm.getCurrentLocation().toUpperCase() + "%"));

			} else if (searchForm.getCurrentLocation().contains("&&")) {
				List<String> locListWithAnd = ProfileParserUtils
						.processSearchStringWithAND(searchForm.getCurrentLocation());
				for (String location : locListWithAnd) {
					predicates.add(
							cb.like(cb.upper(candidateProfile.get("profileText")), "%" + location.toUpperCase() + "%"));

				}
			} else if (searchForm.getCurrentLocation().contains("||")) {
				List<String> locListWithOr = ProfileParserUtils
						.processSearchStringWithOR(searchForm.getCurrentLocation());
				for (String location : locListWithOr) {
					predicates.add(
							cb.like(cb.upper(candidateProfile.get("profileText")), "%" + location.toUpperCase() + "%"));

				}

			} else if (searchForm.getCurrentLocation().contains("!")) {
				List<String> locListWithNot = ProfileParserUtils
						.processSearchStringWithNOT(searchForm.getCurrentLocation());
				for (String location : locListWithNot) {
					predicates.add(cb.notLike(cb.upper(candidateProfile.get("profileText")),
							"%" + location.toUpperCase() + "%"));

				}

			}

		}

		if (predicates.size() > 0) {
			Predicate[] predicatesArray = new Predicate[predicates.size()];
			query.select(candidateProfile).where(cb.or(predicates.toArray(predicatesArray)));
			return entityManager.createQuery(query).getResultList();
		} else

			return new ArrayList<CandidateProfileEntity>();
		// return profileList;
	}

	/*
	 * public List<CandidateProfileEntity> updateAssignedEmployeeDetails() {
	 * 
	 * 
	 * CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	 * CriteriaQuery<CandidateProfileEntity> query =
	 * cb.createQuery(CandidateProfileEntity.class); Root<CandidateProfileEntity>
	 * candidateProfile = query.from(CandidateProfileEntity.class); List<Predicate>
	 * predicates = new ArrayList<Predicate>(); predicates.add(cb.diff(x,
	 * y)currentDate().candidateProfile.get("")>28));
	 * 
	 * 
	 * }
	 */

}
