package com.renaissance.common.dto;


public class ConstantsDTO {
	
	private Double nsw;
	private Double vic;
	private Double sa;
	private Double wa;
	private Double tas;
	private Double qld;
	public Double getNsw() {
		return nsw;
	}
	public void setNsw(Double nsw) {
		this.nsw = nsw;
	}
	public Double getVic() {
		return vic;
	}
	public void setVic(Double vic) {
		this.vic = vic;
	}
	public Double getSa() {
		return sa;
	}
	public void setSa(Double sa) {
		this.sa = sa;
	}
	public Double getWa() {
		return wa;
	}
	public void setWa(Double wa) {
		this.wa = wa;
	}
	public Double getTas() {
		return tas;
	}
	public void setTas(Double tas) {
		this.tas = tas;
	}
	public Double getQld() {
		return qld;
	}
	public void setQld(Double qld) {
		this.qld = qld;
	}
	@Override
	public String toString() {
		return "ConstantsDTO [nsw=" + nsw + ", vic=" + vic + ", sa=" + sa + ", wa=" + wa + ", tas=" + tas + ", qld="
				+ qld + "]";
	}
	
}
