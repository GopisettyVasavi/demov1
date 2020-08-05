package com.renaissance.commission.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"RECRUITER_COMMISSIONS\"")
public class RecruiterCommissionsEntity {
	@Id
	@GeneratedValue(generator = "parse.recruiter_commission_id_sequence")
	@SequenceGenerator(name = "parse.recruiter_commission_id_sequence", sequenceName = "parse.recruiter_commission_id_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private BigInteger Id;
	
	@Column(name = "\"RECRUITER_NAME\"")
	private String recruiterName;
	
	@Column(name = "\"MONTH_YEAR\"")
	private LocalDate monthYear;
	
	@Column(name = "\"CONTRACT_COMMISSION_TOTAL\"")
	private Double contractCommissionTotal;
	
	@Column(name = "\"CONTRACT_COMMISSION_TOTAL_SUPER\"")
	private Double contractCommissionTotalSuper;

	public BigInteger getId() {
		return Id;
	}

	public void setId(BigInteger id) {
		Id = id;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public LocalDate getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(LocalDate monthYear) {
		this.monthYear = monthYear;
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

	@Override
	public String toString() {
		return "RecruiterCommissionsEntity [Id=" + Id + ", recruiterName=" + recruiterName + ", monthYear=" + monthYear
				+ ", contractCommissionTotal=" + contractCommissionTotal + ", contractCommissionTotalSuper="
				+ contractCommissionTotalSuper + "]";
	}
	
	

}
