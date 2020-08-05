package com.renaissance.commission.dto;

public class CommissionsLookupDTO {
	
	private String range;
	
	private Double percentage;
	
	private String commissionType;
	private String minRange;
	private String maxRange;
	
	private Integer Id;
	
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
	public String getMinRange() {
		return minRange;
	}
	public void setMinRange(String minRange) {
		this.minRange = minRange;
	}
	public String getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(String maxRange) {
		this.maxRange = maxRange;
	}
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@Override
	public String toString() {
		return "CommissionsLookupDTO [range=" + range + ", percentage=" + percentage + ", commissionType="
				+ commissionType + ", minRange=" + minRange + ", maxRange=" + maxRange + "]";
	}
	
	

}
