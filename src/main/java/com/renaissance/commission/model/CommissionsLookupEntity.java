package com.renaissance.commission.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"COMMISSIONS_LOOKUP\"")
public class CommissionsLookupEntity {
	@Id
	@GeneratedValue(generator = "parse.app_constants_sequence")
	@SequenceGenerator(name = "parse.app_constants_sequence", sequenceName = "parse.app_constants_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private Integer Id;
	
	@Column(name = "\"RANGE\"")
	private String range;
	
	@Column(name = "\"PERCENTAGE\"")
	private Double percentage;
	
	@Column(name = "\"COMMISSION_TYPE\"")
	private String commissionType;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	@Override
	public String toString() {
		return "CommissionsLookupEntity [Id=" + Id + ", range=" + range + ", percentage=" + percentage
				+ ", commissionType=" + commissionType + "]";
	}
	
	

}
