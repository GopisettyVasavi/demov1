package com.renaissance.invoice.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaissance.common.dto.ClientCompanyDTO;
import com.renaissance.common.service.ClientCompanyDetailsService;
import com.renaissance.contractor.model.ContractorEmploymentDetailsEntity;
import com.renaissance.contractor.model.ContractorPersonalDetailsEntity;
import com.renaissance.contractor.model.ContractorRateDetailsEntity;
import com.renaissance.contractor.repository.ContractorEmploymentDetailsRepository;
import com.renaissance.contractor.repository.ContractorPersonalDetailsRepository;
import com.renaissance.contractor.repository.ContractorRateDetailsRepository;
import com.renaissance.invoice.dto.InvoiceDTO;
import com.renaissance.invoice.dto.InvoiceSearchForm;
import com.renaissance.invoice.model.InvoiceDetailsEntity;
import com.renaissance.invoice.repository.InvoiceDetailsRepository;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Service

public class InvoiceDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceDetailsService.class);
	@Autowired
	ContractorPersonalDetailsRepository contractorPersonal;
	
	@Autowired
	ContractorRateDetailsRepository contractorRate;
	
	@Autowired
	ContractorEmploymentDetailsRepository contractorEmployment;
	
	@Autowired
	InvoiceDetailsRepository invoiceDetails;
	
	@Autowired
	ClientCompanyDetailsService companyDetails;
	
	/**
	 * This method will check first any temporary or final commissions exist for the given month and year. If so, those commissions will be returned or
	 * else it will create commissions for that month.
	 * @param monthAndYear
	 * @return
	 */
	public List<InvoiceDTO> getInvoices(String monthAndYear){
		List<InvoiceDTO> invoiceList= new ArrayList<InvoiceDTO>();
		boolean invoicesExist=false;
		if(!ProfileParserUtils.isObjectEmpty(monthAndYear)) {
			List<InvoiceDetailsEntity> invoicesEntiy=invoiceDetails.getInvoicesForSelectedMonthAndYear(ProfileParserUtils.parseStringDate("01/"+monthAndYear));
			if(!ProfileParserUtils.isObjectEmpty(invoicesEntiy)) {
				invoicesExist=true;
				logger.info("Invoices exist....");
				for(InvoiceDetailsEntity invoiceEntity:invoicesEntiy) {
					InvoiceDTO invoiceDto= new InvoiceDTO();
					BeanUtils.copyProperties(invoiceEntity, invoiceDto);
					invoiceDto.setStartDate(ProfileParserUtils.parseDateToString(invoiceEntity.getStartDate()));
					invoiceDto.setEndDate(ProfileParserUtils.parseDateToString(invoiceEntity.getEndDate()));
					invoiceDto.setMonthYear(ProfileParserUtils.parseDateToString(invoiceEntity.getMonthYear()));
					invoiceDto.setInvoiceGeneratedDateString(ProfileParserUtils.parseDateToString(invoiceEntity.getInvoiceGeneratedDate()));
					invoiceList.add(invoiceDto);
				}
			}
		}
		
		
		if(!invoicesExist) {
			logger.info("Invoices do not exist....");
		List<ContractorEmploymentDetailsEntity> empDetails = contractorEmployment.getCandidatesForCommission
				(ProfileParserUtils.parseStringDate("01/"+monthAndYear));
		logger.info("date.. ,{},",ProfileParserUtils.parseStringDate("01/"+monthAndYear));
		//logger.info("Search Results EMP table, {}", empDetails.size());
		if (!ProfileParserUtils.isObjectEmpty(empDetails)) {
			//int i=1;
			for (ContractorEmploymentDetailsEntity empEntity : empDetails) {
				//logger.info("Result records...,{}", empEntity.toString());
				
				InvoiceDTO invoiceDto= new InvoiceDTO();
				invoiceDto.setMonthYear(monthAndYear);
				invoiceDto.setPoNumber(empEntity.getPoNumber());
				invoiceDto.setClientName(empEntity.getClientName());
				invoiceDto.setEndClientName(empEntity.getEndClientName());
				invoiceDto.setContractorInvoiceNotes(empEntity.getInvoiceNotes());
				
				ContractorPersonalDetailsEntity personalEntity = contractorPersonal
						.getPersonalDetailsByContractorId(empEntity.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(personalEntity)) {
					invoiceDto.setContractorId(personalEntity.getContractorId());
					invoiceDto.setContractorName(personalEntity.getFirstName()+" "+personalEntity.getMiddleName()+" "+personalEntity.getLastName());
					
				}
				ContractorRateDetailsEntity rateEntity = contractorRate.getRateDetailsByContractorId(empEntity.getContractorId());
				if (!ProfileParserUtils.isObjectEmpty(rateEntity)) {
					invoiceDto.setRatePerDay(rateEntity.getRatePerDay());
					invoiceDto.setBillRatePerDay(rateEntity.getBillRatePerDay());
					
				}
				
				if(!ProfileParserUtils.isObjectEmpty(empEntity.getClientName())) {
				ClientCompanyDTO companyDto=companyDetails.getCompanyDetails(empEntity.getClientName());
				if(!ProfileParserUtils.isObjectEmpty(companyDto)) {
					invoiceDto.setAddress(companyDto.getAddress());
					invoiceDto.setVendorId(companyDto.getVendorId());
					invoiceDto.setClientAbnNo(companyDto.getClientAbnNo());
					invoiceDto.setPaymentTerms(companyDto.getPaymentTerms());
					invoiceDto.setClientId(companyDto.getClientId());
				}
				}
				invoiceList.add(invoiceDto);

				//i++;
			}
		}
		}
		if(!ProfileParserUtils.isObjectEmpty(invoiceList)) {
			invoiceList.sort(Comparator.comparing(InvoiceDTO::getContractorName));
		int i=1;
		for(InvoiceDTO invoiceDto:invoiceList) {
			invoiceDto.setId(i);
			i++;
		}
		}
		return invoiceList;
	}
	
	
	/**
	 * This method will save commissions temporarily.
	 * @param commissionDtoList
	 * @return
	 */
	public List<InvoiceDTO> saveInvoices(List<InvoiceDTO> invoiceDtoList){
		List<InvoiceDTO> savedList= new ArrayList<InvoiceDTO>();
		if(!ProfileParserUtils.isObjectEmpty(invoiceDtoList)) {
			for(InvoiceDTO invoice:invoiceDtoList) {
				if(!ProfileParserUtils.isObjectEmpty(invoice)) {
				//logger.info("DTO....{}",invoice.toString());
				InvoiceDetailsEntity invoiceEntity=new InvoiceDetailsEntity();
				InvoiceDTO savedInvoice= new InvoiceDTO();
				BeanUtils.copyProperties(invoice, invoiceEntity);
				invoiceEntity.setMonthYear(ProfileParserUtils.parseStringDate(invoice.getMonthYear()));
				invoiceEntity.setStartDate(ProfileParserUtils.parseStringDate(invoice.getStartDate()));
				invoiceEntity.setEndDate(ProfileParserUtils.parseStringDate(invoice.getEndDate()));
				
				//logger.info("VO....{}",invoiceEntity.toString());
				InvoiceDetailsEntity previousInvoice = invoiceDetails.getContractorInvoiceByMonthYearInvoiceNo(invoice.getContractorId(), 
						invoiceEntity.getMonthYear());
					
				invoiceDetails.delete(previousInvoice);
				//logger.info("VO....{}",invoiceEntity.toString());
				invoiceEntity=	invoiceDetails.save(invoiceEntity);
				BeanUtils.copyProperties(invoiceEntity, savedInvoice);
				//logger.info("VO....{}",savedInvoice.toString());
				savedInvoice.setStartDate(ProfileParserUtils.parseDateToString(invoiceEntity.getStartDate()));
				savedInvoice.setEndDate(ProfileParserUtils.parseDateToString(invoiceEntity.getEndDate()));
				savedInvoice.setMonthYear(ProfileParserUtils.parseDateToString(invoiceEntity.getMonthYear()));
				savedInvoice.setInvoiceGeneratedDateString(ProfileParserUtils.parseDateToString(invoiceEntity.getInvoiceGeneratedDate()));
				savedList.add(savedInvoice);
			}
			}
		}
		
		return savedList;
		
	}
	
	
	
	
	/**
	 * This method will save commissions temporarily.
	 * @param commissionDtoList
	 * @return
	 */
	public InvoiceDTO editInvoice(InvoiceDTO invoiceDto){
		InvoiceDTO savedInvoice= new InvoiceDTO();
		if(!ProfileParserUtils.isObjectEmpty(invoiceDto)) {
			
				if(!ProfileParserUtils.isObjectEmpty(invoiceDto)) {
				
				Optional<InvoiceDetailsEntity> previousInvoice = invoiceDetails.findById(invoiceDto.getIdPkey());
				InvoiceDetailsEntity invoiceEntity=previousInvoice.get();
				invoiceEntity.setInvoiceGeneratedDate(ProfileParserUtils.parseStringDate(invoiceDto.getInvoiceGeneratedDateString()));
				invoiceEntity.setInvoiceNo(invoiceDto.getInvoiceNo());
				invoiceEntity.setClientName(invoiceDto.getClientName());
				invoiceEntity.setContractorInvoiceNotes(invoiceDto.getContractorInvoiceNotes());
				invoiceEntity.setStatus(invoiceDto.getStatus());
				invoiceEntity.setTotalAmount(invoiceDto.getTotalAmount());
				invoiceEntity.setGst(invoiceDto.getGst());
				invoiceEntity.setTotalAmountWithGst(invoiceDto.getTotalAmountWithGst());
				
				invoiceEntity=	invoiceDetails.save(invoiceEntity);
				BeanUtils.copyProperties(invoiceEntity, savedInvoice);
				//logger.info("VO....{}",savedInvoice.toString());
				savedInvoice.setStartDate(ProfileParserUtils.parseDateToString(invoiceEntity.getStartDate()));
				savedInvoice.setEndDate(ProfileParserUtils.parseDateToString(invoiceEntity.getEndDate()));
				savedInvoice.setMonthYear(ProfileParserUtils.parseDateToString(invoiceEntity.getMonthYear()));
				savedInvoice.setInvoiceGeneratedDateString(ProfileParserUtils.parseDateToString(invoiceEntity.getInvoiceGeneratedDate()));
				//savedList.add(savedInvoice);
			}
		}
		
		return savedInvoice;
		
	}

	public List<InvoiceDTO> searchInvoices(InvoiceSearchForm searchForm){
		List<InvoiceDTO> searchList= new ArrayList<InvoiceDTO>();
		if(!ProfileParserUtils.isObjectEmpty(searchForm)) {
			List<InvoiceDetailsEntity> invoiceEntityList=null;
			invoiceEntityList=invoiceDetails.searchInvoices(searchForm);
			if(!ProfileParserUtils.isObjectEmpty(invoiceEntityList)) {
				for(InvoiceDetailsEntity entity: invoiceEntityList) {
					InvoiceDTO invoiceDto= new InvoiceDTO();
					BeanUtils.copyProperties(entity, invoiceDto);
					invoiceDto.setStartDate(ProfileParserUtils.parseDateToString(entity.getStartDate()));
					invoiceDto.setEndDate(ProfileParserUtils.parseDateToString(entity.getEndDate()));
					invoiceDto.setMonthYear(ProfileParserUtils.parseDateToString(entity.getMonthYear()));
					invoiceDto.setInvoiceGeneratedDateString(ProfileParserUtils.parseDateToString(entity.getInvoiceGeneratedDate()));
					String payment=invoiceDto.getPaymentTerms();
					logger.info("payment {}",payment);
					int days=0;
					if(!ProfileParserUtils.isObjectEmpty(payment)) {
						payment=payment.replaceFirst("Days", "");
						payment=payment.replaceFirst("days", "");
						days=new Integer(payment.trim());
						logger.info("days ..{}",days);
					}
					if(!ProfileParserUtils.isObjectEmpty(invoiceDto.getInvoiceGeneratedDate())) {
					 LocalDate invDate = LocalDate.parse(invoiceDto.getInvoiceGeneratedDate().toString()); 
				        // Parses the second date 
				        LocalDate currentDate = LocalDate.parse(LocalDate.now().toString());
					
					if(currentDate.isAfter(invDate.plusDays(days)) && "Submitted".equalsIgnoreCase(invoiceDto.getStatus())) {
						invoiceDto.setChangeColor("YES");
						logger.info("color changed..");
					}else {
						invoiceDto.setChangeColor("NO");
						logger.info("color not changed..");
					}
					}
					searchList.add(invoiceDto);
				}
			}
			
		}
		
		return searchList;
		
	}
	
}
