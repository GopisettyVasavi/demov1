package com.renaissance.commission.repository;

import java.time.LocalDate;
import java.util.List;

import com.renaissance.commission.model.RecruiterCommissionsEntity;
import com.renaissance.commission.model.SearchCommissionForm;

public interface RecruiterCommissionsCustomRepository {

	List<RecruiterCommissionsEntity> searchCommissions(SearchCommissionForm searchCommissionForm);
	RecruiterCommissionsEntity getRecruiterCommissionByMonthYearAndRecruiter(LocalDate monthYear, String recruiterName);
}
