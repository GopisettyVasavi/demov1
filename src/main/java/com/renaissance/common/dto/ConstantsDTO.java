package com.renaissance.common.dto;

import java.math.BigInteger;

public class ConstantsDTO {
	
	private BigInteger nsw;
	private BigInteger vic;
	private BigInteger sa;
	private BigInteger wa;
	private BigInteger tas;
	private BigInteger qld;
	public BigInteger getNsw() {
		return nsw;
	}
	public void setNsw(BigInteger nsw) {
		this.nsw = nsw;
	}
	public BigInteger getVic() {
		return vic;
	}
	public void setVic(BigInteger vic) {
		this.vic = vic;
	}
	public BigInteger getSa() {
		return sa;
	}
	public void setSa(BigInteger sa) {
		this.sa = sa;
	}
	public BigInteger getWa() {
		return wa;
	}
	public void setWa(BigInteger wa) {
		this.wa = wa;
	}
	public BigInteger getTas() {
		return tas;
	}
	public void setTas(BigInteger tas) {
		this.tas = tas;
	}
	public BigInteger getQld() {
		return qld;
	}
	public void setQld(BigInteger qld) {
		this.qld = qld;
	}
	@Override
	public String toString() {
		return "ConstantsDTO [nsw=" + nsw + ", vic=" + vic + ", sa=" + sa + ", wa=" + wa + ", tas=" + tas + ", qld="
				+ qld + "]";
	}
	
}
