package com.renaissance.invoice.dto;

import java.math.BigInteger;
import java.time.LocalDate;

public class InvoiceDTO {
	private Integer id;
	private Integer invoiceNo;
	private BigInteger contractorId;
	private String contractorName;
	private Integer clientId;
	private String clientName;
	private String endClientName;
	private String address;
	private String vendorId;
	private String paymentTerms;
	private String clientAbnNo;
	private String startDate;
	private String endDate;
	private Double ratePerDay;
	private Double billRatePerDay;
	private Double totalAmount;
	private Double gst;
	private Double tatoalAmountWithGst;
	private String inclGst;
	private Integer noOfDaysWorked;
	private LocalDate invoiceGeneratedDate;
	private String invoiceCreatedDate;
	private String status;
	private String monthYear;
	private String contractorInvoiceNotes;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(Integer invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public BigInteger getContractorId() {
		return contractorId;
	}
	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
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
	public String getEndClientName() {
		return endClientName;
	}
	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
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
	public Double getRatePerDay() {
		return ratePerDay;
	}
	public void setRatePerDay(Double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}
	public Double getBillRatePerDay() {
		return billRatePerDay;
	}
	public void setBillRatePerDay(Double billRatePerDay) {
		this.billRatePerDay = billRatePerDay;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getGst() {
		return gst;
	}
	public void setGst(Double gst) {
		this.gst = gst;
	}
	public Double getTatoalAmountWithGst() {
		return tatoalAmountWithGst;
	}
	public void setTatoalAmountWithGst(Double tatoalAmountWithGst) {
		this.tatoalAmountWithGst = tatoalAmountWithGst;
	}
	public String getInclGst() {
		return inclGst;
	}
	public void setInclGst(String inclGst) {
		this.inclGst = inclGst;
	}
	public Integer getNoOfDaysWorked() {
		return noOfDaysWorked;
	}
	public void setNoOfDaysWorked(Integer noOfDaysWorked) {
		this.noOfDaysWorked = noOfDaysWorked;
	}
	public LocalDate getInvoiceGeneratedDate() {
		return invoiceGeneratedDate;
	}
	public void setInvoiceGeneratedDate(LocalDate invoiceGeneratedDate) {
		this.invoiceGeneratedDate = invoiceGeneratedDate;
	}
	public String getInvoiceCreatedDate() {
		return invoiceCreatedDate;
	}
	public void setInvoiceCreatedDate(String invoiceCreatedDate) {
		this.invoiceCreatedDate = invoiceCreatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public String getContractorInvoiceNotes() {
		return contractorInvoiceNotes;
	}
	public void setContractorInvoiceNotes(String contractorInvoiceNotes) {
		this.contractorInvoiceNotes = contractorInvoiceNotes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "InvoiceDTO [id=" + id + ", invoiceNo=" + invoiceNo + ", contractorId=" + contractorId
				+ ", contractorName=" + contractorName + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", endClientName=" + endClientName + ", address=" + address + ", vendorId=" + vendorId
				+ ", paymentTerms=" + paymentTerms + ", clientAbnNo=" + clientAbnNo + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", ratePerDay=" + ratePerDay + ", billRatePerDay=" + billRatePerDay
				+ ", totalAmount=" + totalAmount + ", gst=" + gst + ", tatoalAmountWithGst=" + tatoalAmountWithGst
				+ ", inclGst=" + inclGst + ", noOfDaysWorked=" + noOfDaysWorked + ", invoiceGeneratedDate="
				+ invoiceGeneratedDate + ", invoiceCreatedDate=" + invoiceCreatedDate + ", status=" + status
				+ ", monthYear=" + monthYear + ", contractorInvoiceNotes=" + contractorInvoiceNotes + ", description="
				+ description + "]";
	}
	
	
	

}
