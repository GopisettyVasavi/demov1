package com.renaissance.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"CLIENT_COMPANY_DETAILS\"")
public class ClientCompanyDetailsEntity {
	
	@Id
	@GeneratedValue(generator = "parse.app_constants_sequence")
	@SequenceGenerator(name = "parse.app_constants_sequence", sequenceName = "parse.app_constants_sequence", allocationSize = 1

	)
	@Column(name = "\"CLIENT_ID\"", unique = true)
	private Integer clientId;
	
	@Column(name = "\"CLIENT_NAME\"")
	private String clientName;
	
	@Column(name = "\"ADDRESS\"")
	private String address;
	
	@Column(name = "\"VENDOR_ID\"")
	private String vendorId;
	
	@Column(name = "\"PAYMENT_TERMS\"")
	private String paymentTerms;
	
	@Column(name = "\"CLIENT_ABN_NO\"")
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
		return "ClientCompanyDetailsEntity [clientId=" + clientId + ", clientName=" + clientName + ", address="
				+ address + ", vendorId=" + vendorId + ", paymentTerms=" + paymentTerms + ", clientAbnNo=" + clientAbnNo
				+ "]";
	}
	
	
	

}
