package com.renaissance.common.dto;

public class ClientCompanyDTO {
	private Integer clientId;

	private String clientName;

	private String address;

	private String vendorId;

	private String paymentTerms;

	private String clientAbnNo;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getClientAbnNo() {
		return clientAbnNo;
	}

	public void setClientAbnNo(String clientAbnNo) {
		this.clientAbnNo = clientAbnNo;
	}

	@Override
	public String toString() {
		return "ClientCompanyDTO [clientId=" + clientId + ", clientName=" + clientName + ", address=" + address
				+ ", vendorId=" + vendorId + ", paymentTerms=" + paymentTerms + ", clientAbnNo=" + clientAbnNo + "]";
	}

}
