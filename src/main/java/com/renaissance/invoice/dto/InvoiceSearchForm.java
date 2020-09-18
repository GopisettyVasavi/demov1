package com.renaissance.invoice.dto;

import java.math.BigInteger;

public class InvoiceSearchForm {
	private BigInteger invoiceNo;
	private String clientName;
	private String invoiceStatus;
	private String startDate;
	private String endDate;
	public BigInteger getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(BigInteger invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "InvoiceSearchForm [invoiceNo=" + invoiceNo + ", clientName=" + clientName + ", invoiceStatus="
				+ invoiceStatus + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	

}
