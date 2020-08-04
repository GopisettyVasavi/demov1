package com.renaissance.commission.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchCommissionForm {
	private String period;
	private String fromDate;
	private String recruiterName;
	private String toDate;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	private List<String> monthYear = new ArrayList<String>();
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public List<String> getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(List<String> monthYear) {
		this.monthYear = monthYear;
	}
	@Override
	public String toString() {
		return "SearchCommissionForm [period=" + period + ", fromDate=" + fromDate + ", recruiterName=" + recruiterName
				+ ", toDate=" + toDate + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	

}
