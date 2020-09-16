package com.renaissance.profile.parser.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This class is used to have all the utility methods required by Pprofile Parser.
 * @author Vasavi
 *
 */

import com.renaissance.contractor.dto.MarginDTO;
import com.renaissance.invoice.dto.InvoiceDTO;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;
public class ProfileParserUtils {
	private static final Logger logger=LoggerFactory.getLogger(ProfileParserUtils.class);
	
	/**
	 *  Checks whether the session is alive or not.
	 * @param request
	 * @return
	 */
	public static final boolean isSessionAlive(HttpServletRequest request) {
		HttpSession session=request.getSession(false) ;
		if (session == null || session.isNew() || null== request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME)
				|| "".equalsIgnoreCase( request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_NAME).toString())) //New session.
			return false;
		else return true;
			
		
	}
	
	/**
	 * Checks if is collection empty.
	 *
	 * @param collection the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is object empty.
	 *
	 * @param object the object
	 * @return true, if is object empty
	 */
	public static boolean isObjectEmpty(Object object) {
		if(object == null) return true;
		else if(object instanceof String) {
			if (((String)object).trim().length() == 0) {
				return true;
			}
		} else if(object instanceof Collection) {
			return isCollectionEmpty((Collection<?>)object);
		}
		return false;
	}
	
	/*
	 * public static LocalDate parseDate(LocalDate date) {
	 * logger.info("before date, {}",date); LocalDate parsedDate =null;
	 * if(!isObjectEmpty(date)) { DateTimeFormatter formatters =
	 * DateTimeFormatter.ofPattern("dd/MM/yyyy"); String text =
	 * date.format(formatters); parsedDate = LocalDate.parse(text, formatters);
	 * logger.info("after date, {}",parsedDate);
	 * 
	 * } return parsedDate; } public static LocalDateTime
	 * parseDateTime(LocalDateTime date) { logger.info("before date, {}",date);
	 * LocalDateTime parsedDate =null; if(!isObjectEmpty(date)) { DateTimeFormatter
	 * formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"); String text =
	 * date.format(formatters); parsedDate = LocalDateTime.parse(text, formatters);
	 * logger.info("after date, {}",parsedDate);
	 * 
	 * } return parsedDate; }
	 * 
	 * public static CandidateDTO parseAllDates(CandidateDTO candidateDto) {
	 * candidateDto.setAssignedDate(parseDate(candidateDto.getAssignedDate()));
	 * candidateDto.setWorkStartDate(parseDate(candidateDto.getWorkStartDate()));
	 * candidateDto.setWorkEndDate(parseDate(candidateDto.getWorkEndDate()));
	 * candidateDto.setValidUpto(parseDate(candidateDto.getValidUpto())); return
	 * candidateDto;
	 * 
	 * }
	 */
	
	public static LocalDate parseStringDate(String date) {
		if(!isObjectEmpty(date)) {
			//logger.info("DATE for parsing...,{}",date);
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			 return LocalDate.parse(date, formatter);
		}
		return null;
	}
	public static LocalDate parseMonthStringDate(String date) {
		if(!isObjectEmpty(date)) {
			//logger.info("DATE for parsing...,{}",date);
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
			 return LocalDate.parse(date, formatter);
		}
		return null;
	}
	
	
	public static String parseDateToString(LocalDate date) {
		if(!isObjectEmpty(date)) {
			return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return null;
	}
	
	public static String parseMonthDateToString(LocalDate date) {
		if(!isObjectEmpty(date)) {
			return date.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy"));
		}
		return null;
	}
	public static String formatStringDate(String sDate) {
		if(!isObjectEmpty(sDate)) {
			try {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("MMM-yyyy");
		Date date;
		
			date = format1.parse(sDate);
		
		return format2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String formatStringDateToString(String sDate) {
		if(!isObjectEmpty(sDate)) {
			try {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("MM/yyyy");
		Date date;
		
			date = format1.parse(sDate);
		
		return format2.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
public static List<String> processSearchStringWithAND(String searchString){
	return Collections.list(new StringTokenizer(searchString, "&&")).stream()
		      .map(token -> (String) token)
		      .collect(Collectors.toList());
}
public static List<String> processSearchStringWithOR(String searchString){
	return Collections.list(new StringTokenizer(searchString, "||")).stream()
		      .map(token -> (String) token)
		      .collect(Collectors.toList());
}
public static List<String> processSearchStringWithNOT(String searchString){
	return Collections.list(new StringTokenizer(searchString, "!")).stream()
		      .map(token -> (String) token)
		      .collect(Collectors.toList());
}

public static long getDays(LocalDate from, LocalDate to) {
	return  ChronoUnit.DAYS.between(from, to);
	
}

public static MarginDTO calculateMargin(MarginDTO marginDto) {
	//Gross Margin  = 750 – (500 + 24.25 + 15 + 100)
	//Gross Margin = Bill Rate – (Candidate Rate + Payroll Tax based on State + Insurance Cost + Referral Commission)
	Double grossMargin=0.0;
	Double payrollTax=0.0;
	Double insuranceCost=0.0;
	if(!ProfileParserUtils.isObjectEmpty(marginDto)&& !ProfileParserUtils.isObjectEmpty(marginDto.getContractorRate())) {
	 payrollTax=calculatePercentage(marginDto.getContractorRate(),marginDto.getPayrollTax());
	 insuranceCost=calculatePercentage(marginDto.getContractorRate(),marginDto.getInsurancePercentage());
	}
	Double referralCommission=0.0;
	//Double superannuation=calculatePercentage(marginDto.getContractorRate(),marginDto.getSuperannuation());
	if(!isObjectEmpty(marginDto.getReferralCommissionType())) {
		if(marginDto.getReferralCommissionType().equalsIgnoreCase(ProfileParserConstants.AMOUNT)&&!isObjectEmpty(marginDto.getReferralCommissionValue())) {
			referralCommission=marginDto.getReferralCommissionValue();
			
		}
		else if(marginDto.getReferralCommissionType().equalsIgnoreCase(ProfileParserConstants.PERCENT)&&!isObjectEmpty(marginDto.getReferralCommissionValue())
				&& !isObjectEmpty(marginDto.getContractorRate())) {
			referralCommission=calculatePercentage(marginDto.getContractorRate(),marginDto.getReferralCommissionValue());
					}
	}
	logger.info("Values...,{},{},{},{},{},{}",marginDto.getBillRate(),marginDto.getContractorRate(),payrollTax+insuranceCost,referralCommission);
	marginDto.setPayrollTaxValue(payrollTax);
	marginDto.setInsuranceValue(insuranceCost);
	marginDto.setReferralValue(referralCommission);
	if(!ProfileParserUtils.isObjectEmpty(marginDto)&& !ProfileParserUtils.isObjectEmpty(marginDto.getContractorRate())
			&& !ProfileParserUtils.isObjectEmpty(marginDto.getBillRate())) {
	grossMargin=roundValue(marginDto.getBillRate()-(marginDto.getContractorRate()+payrollTax+insuranceCost+referralCommission));
		
	
	marginDto.setGrossMargin(grossMargin);
	}
	/*
	 * Net Margin = Gross Margin – (Additional Cost + Recruiter Commission)

 

Additional Cost = $ (This is Referral Commission based on gross margin %)

Recruiter Commission = Value entered in %.
	 */
	return marginDto;
}
private static Double calculatePercentage(Double amount,Double percentage) {
	if(!isObjectEmpty(amount)&&!isObjectEmpty(percentage)&& amount!=0.0 && percentage!=0.0) {
	//return new Double(Math.ceil(amount*percentage/100)).doubleValue();
		
		return roundValue(amount*percentage/100);
		}
	else return 0.0;
}
public static Double roundValue(Double amount) {
	BigDecimal bd = BigDecimal.valueOf(amount.doubleValue());
    bd = bd.setScale(2, RoundingMode.HALF_UP);
	return bd.doubleValue();
	
}

public static void generateInvoice(InvoiceDTO invoiceDto,String filePath) {
	try {
	Docx docx = new Docx("D:\\template.docx"); 
	docx.setVariablePattern(new VariablePattern("#{", "}"));
	
	 
	  
	  // preparing variables
	Variables variables = new Variables();
	  variables.addTextVariable(new TextVariable("#{name}", invoiceDto.getContractorName()));
	  variables.addTextVariable(new TextVariable("#{client_name}", invoiceDto.getClientName()));
	  variables.addTextVariable(new TextVariable("#{client_address}", invoiceDto.getAddress()));
	  variables.addTextVariable(new TextVariable("#{end_client_name}", invoiceDto.getEndClientName()));
	  variables.addTextVariable(new TextVariable("#{start_date}", invoiceDto.getStartDate()));
	  variables.addTextVariable(new TextVariable("#{end_date}", invoiceDto.getEndDate()));
	  variables.addTextVariable(new TextVariable("#{invoice_number}", invoiceDto.getInvoiceNo()+""));
	  variables.addTextVariable(new TextVariable("#{po_number}", invoiceDto.getPoNumber()));
	  variables.addTextVariable(new TextVariable("#{invoice_date}", ProfileParserUtils.parseDateToString(LocalDate.now())));
	  variables.addTextVariable(new TextVariable("#{payment_terms}", invoiceDto.getPaymentTerms()));
	  variables.addTextVariable(new TextVariable("#{vendor_number}", invoiceDto.getVendorId()));
	  variables.addTextVariable(new TextVariable("#{unit_price}", formatAmount(invoiceDto.getBillRatePerDay())));
	  variables.addTextVariable(new TextVariable("#{no_of_units}", invoiceDto.getNoOfDaysWorked()+".00"));
	  variables.addTextVariable(new TextVariable("#{amount}", "$"+invoiceDto.getTotalAmount()));
	  variables.addTextVariable(new TextVariable("#{total_amount}", formatAmount(invoiceDto.getTotalAmount())));
	  variables.addTextVariable(new TextVariable("#{gst}", formatAmount(invoiceDto.getGst())));
	  variables.addTextVariable(new TextVariable("#{total_amount_gst}", formatAmount(invoiceDto.getTotalAmountWithGst())));
	  // fill template 
	  docx.fillTemplate(variables);
	  File fileLoc= new File(filePath);
		if(fileLoc.exists()) {
			logger.info("Valid path");
			 docx.save(filePath+"\\"+invoiceDto.getContractorName()+".docx");
		}else {
			docx.save("D:\\invoices\\"+invoiceDto.getContractorName()+".docx");		}
	  // save filled .docx file 
	 
	}catch(Exception e) {
		
	}
}

private static String formatAmount(Double amount) {
	if(!isObjectEmpty(amount)) {
	NumberFormat formatter = NumberFormat.getCurrencyInstance();
	String moneyString = formatter.format(amount);
	return moneyString;
	}
	else
		return "$0.00";
}

public static Integer getRandomNumber() {
	Random random = new Random(System.nanoTime());
	return random.nextInt() & Integer.MAX_VALUE;
}
}
