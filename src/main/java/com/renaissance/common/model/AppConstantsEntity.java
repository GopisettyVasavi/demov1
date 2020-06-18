package com.renaissance.common.model;

import java.math.BigInteger;

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
	private Integer Id;
	
	@Column(name = "\"CONSTANT_NAME\"")
	private String constantName;
	
	@Column(name = "\"CONSTANT_VALUE\"")
	private BigInteger constantValue;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getConstantName() {
		return constantName;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public BigInteger getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(BigInteger constantValue) {
		this.constantValue = constantValue;
	}

	@Override
	public String toString() {
		return "AppConstantsEntity [Id=" + Id + ", constantName=" + constantName + ", constantValue=" + constantValue
				+ "]";
	}
	
}
