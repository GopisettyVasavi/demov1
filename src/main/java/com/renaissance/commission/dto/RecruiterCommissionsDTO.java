package com.renaissance.commission.dto;

import java.time.LocalDate;

public class RecruiterCommissionsDTO {
	
private String recruiterName;
private Double contractCommissionTotal;
private Double contractCommissionTotalSuper;
private String monthYear;
private String monthYearUI;
private LocalDate orderDate;

public String getRecruiterName() {
	return recruiterName;
}
public void setRecruiterName(String recruiterName) {
	this.recruiterName = recruiterName;
}
public Double getContractCommissionTotal() {
	return contractCommissionTotal;
}
public void setContractCommissionTotal(Double contractCommissionTotal) {
	this.contractCommissionTotal = contractCommissionTotal;
}
public Double getContractCommissionTotalSuper() {
	return contractCommissionTotalSuper;
}
public void setContractCommissionTotalSuper(Double contractCommissionTotalSuper) {
	this.contractCommissionTotalSuper = contractCommissionTotalSuper;
}
public String getMonthYear() {
	return monthYear;
}
public void setMonthYear(String monthYear) {
	this.monthYear = monthYear;
}

public String getMonthYearUI() {
	return monthYearUI;
}
public void setMonthYearUI(String monthYearUI) {
	this.monthYearUI = monthYearUI;
}

public LocalDate getOrderDate() {
	return orderDate;
}
public void setOrderDate(LocalDate orderDate) {
	this.orderDate = orderDate;
}
@Override
public String toString() {
	return "RecruiterCommissionsDTO [recruiterName=" + recruiterName + ", contractCommissionTotal="
			+ contractCommissionTotal + ", contractCommissionTotalSuper=" + contractCommissionTotalSuper
			+ ", monthYear=" + monthYear + ", monthYearUI=" + monthYearUI + ", orderDate=" + orderDate + "]";
}




}
