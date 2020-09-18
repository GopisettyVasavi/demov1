package com.renaissance.invoice.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.renaissance.invoice.model.InvoiceDetailsEntity;

public interface InvoiceDetailsCustomRepository {
	
	List<InvoiceDetailsEntity> getInvoicesForSelectedMonthAndYear(LocalDate monthYear);
	
	InvoiceDetailsEntity getContractorInvoiceByMonthYearInvoiceNo(BigInteger contractorId, LocalDate monthYear);

}
