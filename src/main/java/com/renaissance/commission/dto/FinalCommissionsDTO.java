package com.renaissance.commission.dto;

import java.util.ArrayList;
import java.util.List;

public class FinalCommissionsDTO {
	List<CommissionDTO> commissionsList= new ArrayList<CommissionDTO>();
	List<RecruiterCommissionsDTO> recruiterCommissionsList= new ArrayList<RecruiterCommissionsDTO>();
	public List<CommissionDTO> getCommissionsList() {
		return commissionsList;
	}
	public void setCommissionsList(List<CommissionDTO> commissionsList) {
		this.commissionsList = commissionsList;
	}
	public List<RecruiterCommissionsDTO> getRecruiterCommissionsList() {
		return recruiterCommissionsList;
	}
	public void setRecruiterCommissionsList(List<RecruiterCommissionsDTO> recruiterCommissionsList) {
		this.recruiterCommissionsList = recruiterCommissionsList;
	}
	@Override
	public String toString() {
		return "FinalCommissionsDTO [commissionsList=" + commissionsList + ", recruiterCommissionsList="
				+ recruiterCommissionsList + "]";
	}

}
