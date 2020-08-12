package com.renaissance.common.dto;


public class ConstantsDTO {

	/*
	 * private Double nsw; private Double vic; private Double sa; private Double wa;
	 * private Double tas; private Double qld; private Double superannuation;
	 * private Double insurance; private String nswEffectiveFrom; private String
	 * vicEffectiveFrom; private String saEffectiveFrom; private String
	 * waEffectiveFrom; private String tasEffectiveFrom; private String
	 * qldEffectiveFrom; private String superEffectiveFrom; private String
	 * insEffectiveFrom;
	 */

	private Integer id;

	private String constantName;

	private Double constantValue;

	private String effectiveFrom;

	private String effectiveTill;

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

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTill() {
		return effectiveTill;
	}

	public void setEffectiveTill(String effectiveTill) {
		this.effectiveTill = effectiveTill;
	}

	@Override
	public String toString() {
		return "ConstantsDTO [Id=" + id + ", constantName=" + constantName + ", constantValue=" + constantValue
				+ ", effectiveFrom=" + effectiveFrom + ", effectiveTill=" + effectiveTill + "]";
	}

}
