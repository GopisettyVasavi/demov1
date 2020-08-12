package com.renaissance.common.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"APP_CONSTANTS\"")
public class AppConstantsEntity {
	@Id
	@GeneratedValue(generator = "parse.app_constants_sequence")
	@SequenceGenerator(name = "parse.app_constants_sequence", sequenceName = "parse.app_constants_sequence", allocationSize = 1

	)
	@Column(name = "\"ID\"", unique = true)
	private Integer id;
	
	@Column(name = "\"CONSTANT_NAME\"")
	private String constantName;
	
	@Column(name = "\"CONSTANT_VALUE\"")
	private Double constantValue;

	@Column(name = "\"EFFECTIVE_FROM\"")
	private LocalDate effectiveFrom;
	
	@Column(name = "\"EFFECTIVE_TO\"")
	private LocalDate effectiveTill;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConstantName() {
		return constantName;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public Double getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(Double constantValue) {
		this.constantValue = constantValue;
	}

	public LocalDate getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(LocalDate effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public LocalDate getEffectiveTill() {
		return effectiveTill;
	}

	public void setEffectiveTill(LocalDate effectiveTill) {
		this.effectiveTill = effectiveTill;
	}

	@Override
	public String toString() {
		return "AppConstantsEntity [Id=" + id + ", constantName=" + constantName + ", constantValue=" + constantValue
				+ ", effectiveFrom=" + effectiveFrom + ", effectiveTill=" + effectiveTill + "]";
	}

	

		
	
}
