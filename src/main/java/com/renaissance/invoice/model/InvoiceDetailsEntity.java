package com.renaissance.invoice.model;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"INVOICE_DETAILS\"")
public class InvoiceDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.invoice_details_sequence")
	@SequenceGenerator(name = "parse.invoice_details_sequence", sequenceName = "parse.invoice_details_sequence", allocationSize = 1

	)
	@Column(name = "\"ID_PKEY\"", unique = true)
	private BigInteger idPkey;
	
	@Column(name = "\"INVOICE_NO\"")
	private BigInteger invoiceNo;
	
	@Column(name = "\"CONTRACTOR_ID\"")
	private BigInteger contractorId;
	
	@Column(name = "\"CONTRACTOR_NAME\"")
	private String contractorName;
	
	@Column(name = "\"CLIENT_ID\"")
	private Integer clientId;
	
	@Column(name = "\"CLIENT_NAME\"")
	private String clientName;
	
	@Column(name = "\"END_CLIENT_NAME\"")
	private String endClientName;
	
	@Column(name = "\"ADDRESS\"")
	private String address;
	
	@Column(name = "\"VENDOR_ID\"")
	private String vendorId;
	
	@Column(name = "\"PAYMENT_TERMS\"")
	private String paymentTerms;
	
	@Column(name = "\"CLIENT_ABN_NO\"")
	private String clientAbnNo;
	
	@Column(name = "\"START_DATE\"")
	private LocalDate startDate;
	
	@Column(name = "\"END_DATE\"")
	private LocalDate endDate;
	
	@Column(name = "\"RATE_PER_DAY\"")
	private Double ratePerDay;
	
	@Column(name = "\"BILL_RATE_PER_DAY\"")
	private Double billRatePerDay;
	
	@Column(name = "\"TOTAL_AMOUNT\"")
	private Double totalAmount;
	
	@Column(name = "\"GST\"")
	private Double gst;
	
	@Column(name = "\"TOTAL_AMOUNT_WITH_GST\"")
	private Double totalAmountWithGst;
	
	@Column(name = "\"INCL_GST\"")
	private String inclGst;
	
	@Column(name = "\"NO_OF_DAYS_WORKED\"")
	private Integer noOfDaysWorked;
	
	@Column(name = "\"INVOICE_GENERATED_DATE\"")
	private LocalDate invoiceGeneratedDate;
	
		
	@Column(name = "\"STATUS\"")
	private String status;
	
	@Column(name = "\"MONTH_YEAR\"")
	private LocalDate monthYear;
	
	@Column(name = "\"INVOICE_NOTES\"")
	private String contractorInvoiceNotes;
	
	@Column(name = "\"PO_NUMBER\"")
	private String poNumber;

	
	public BigInteger getIdPkey() {
		return idPkey;
	}

	public void setIdPkey(BigInteger idPkey) {
		this.idPkey = idPkey;
	}

	public BigInteger getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(BigInteger invoiceNo) {
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
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

	public Double getTotalAmountWithGst() {
		return totalAmountWithGst;
	}

	public void setTotalAmountWithGst(Double totalAmountWithGst) {
		this.totalAmountWithGst = totalAmountWithGst;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(LocalDate monthYear) {
		this.monthYear = monthYear;
	}

	public String getContractorInvoiceNotes() {
		return contractorInvoiceNotes;
	}

	public void setContractorInvoiceNotes(String contractorInvoiceNotes) {
		this.contractorInvoiceNotes = contractorInvoiceNotes;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	@Override
	public String toString() {
		return "InvoiceDetailsEntity [idPkey=" + idPkey + ", invoiceNo=" + invoiceNo + ", contractorId=" + contractorId
				+ ", contractorName=" + contractorName + ", clientId=" + clientId + ", clientName=" + clientName
				+ ", endClientName=" + endClientName + ", address=" + address + ", vendorId=" + vendorId
				+ ", paymentTerms=" + paymentTerms + ", clientAbnNo=" + clientAbnNo + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", ratePerDay=" + ratePerDay + ", billRatePerDay=" + billRatePerDay
				+ ", totalAmount=" + totalAmount + ", gst=" + gst + ", totalAmountWithGst=" + totalAmountWithGst
				+ ", inclGst=" + inclGst + ", noOfDaysWorked=" + noOfDaysWorked + ", invoiceGeneratedDate="
				+ invoiceGeneratedDate + ", status=" + status + ", monthYear=" + monthYear + ", contractorInvoiceNotes="
				+ contractorInvoiceNotes + ", poNumber=" + poNumber + "]";
	}
	
	

}
