package com.renaissance.invoice.web;

import static com.renaissance.util.APIConstants.CREATE_INVOICE_RUN;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.GENERATE_INVOICE;
import static com.renaissance.util.APIConstants.INVOICE_MAIN;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.renaissance.commission.dto.CommissionDTO;
import com.renaissance.commission.service.CommissionManagmentService;
import com.renaissance.common.service.ConstantsService;
import com.renaissance.invoice.dto.InvoiceDTO;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class InvoiceMVCController {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceMVCController.class);
	@Autowired
	CommissionManagmentService commissionService;
	
	@Autowired
	ConstantsService constantsService;

	/**
	 * This method is invoked to load contractor main page.
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(INVOICE_MAIN)
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))
			return "invoicemain";
		else
			return "unauthorizedaccess";

	}
	
	/**
	 * This method will fetch commissions for the selected given month and year.
	 * 
	 * @param monthyear
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(CREATE_INVOICE_RUN)
	public ResponseEntity<?> invoiceRun(@PathVariable String monthyear, @PathVariable String startdate,@PathVariable String enddate, HttpServletRequest request) {
		logger.info("Parameters..{},{},{}",monthyear,startdate,enddate);
		List<CommissionDTO> commissionList = new ArrayList<CommissionDTO>();
		List<InvoiceDTO> invoiceList = new ArrayList<InvoiceDTO>();
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			if (!ProfileParserUtils.isObjectEmpty(monthyear) && monthyear.indexOf("-") != -1) {
				String[] mmyy = monthyear.split("-");
				if (mmyy.length > 1) {
					String commissionMonthYear = mmyy[1] + "/" + mmyy[0];
					commissionList = commissionService.getCommissions(commissionMonthYear);
					if(!ProfileParserUtils.isObjectEmpty(commissionList)) {
						/** @TODO Remove this code except, GST%, Dates, Invoice# */
						Double gst=constantsService.getConstantValue(ProfileParserConstants.GST);
						for(CommissionDTO commission:commissionList) {
							InvoiceDTO invoice= new InvoiceDTO();
							BeanUtils.copyProperties(commission, invoice);
						//	invoice.setGst(10.00);
							invoice.setNoOfDaysWorked(10);
							invoice.setClientName("Infosys Technlogies Ltd.");
							invoice.setVendorId("345672");
							invoice.setPaymentTerms("30 Days");
							invoice.setContractorName(commission.getFullName());
							invoice.setEndClientName("ANZ ");
							invoice.setPoNumber("4000151508");
							invoice.setContractorInvoiceNotes("Purchase Order No:  4000151508 Vendor No: 700007822 " );
							invoice.setAddress("Level 4&5, 818 Bourke Street, Docklands, Victoria - 3008 Australia");
							/** DONT REMOVE*/
							startdate=startdate.replaceAll("-", "/");
							enddate=enddate.replaceAll("-", "/");
							invoice.setInvoiceNo(ProfileParserUtils.getRandomNumber());
							invoice.setGstPercent(gst);
							invoice.setStartDate(startdate);
							invoice.setEndDate(enddate);
							if(!ProfileParserUtils.isObjectEmpty(invoice.getNoOfDaysWorked())) {
								invoice.setTotalAmount(invoice.getNoOfDaysWorked() * invoice.getBillRatePerDay());
								invoice.setGst(gst/100 * invoice.getTotalAmount());
								invoice.setTotalAmountWithGst(invoice.getTotalAmount() + invoice.getGst());
							}
							invoiceList.add(invoice);
							/** DONT REMOVE*/
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("There is an issue in fetching commissions...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		return new ResponseEntity<>(invoiceList, HttpStatus.OK);
	}
	/**
	 * This method will be invoked by the UI to save commissions temporarily.
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(GENERATE_INVOICE)
	public ResponseEntity<?> generateInvoices(@RequestBody List<InvoiceDTO> invoiceList,  @PathVariable String filePath, 
			HttpServletRequest request) {
		//List<CommissionDTO> savedCommissions = null;
		try {
			logger.info("Invoice List for generation..,{}", invoiceList.size());
			
			if(!ProfileParserUtils.isObjectEmpty(filePath)) {
				filePath=filePath.replaceAll("SLASH", "\\\\");
				
			}
			logger.info("filePath NO: {},{}",filePath);
			if (!ProfileParserUtils.isObjectEmpty(invoiceList)) {
				for (InvoiceDTO invoiceDto : invoiceList) {
					if(!ProfileParserUtils.isObjectEmpty(invoiceDto)) {
					//logger.info("Invoice DTO values...{}", invoiceDto.toString());
						//logger.info("INVOICE no...{}",invoiceDto.getInvoiceNo());
					if(null!=invoiceDto.getGenerateInvoice() && invoiceDto.getGenerateInvoice().equalsIgnoreCase(ProfileParserConstants.TRUE)) {
						//if(null!=invoiceDto.getNoOfDaysWorked() && null!=invoiceDto.getBillRatePerDay()) {
							//invoiceDto.setTotalAmount(invoiceDto.getBillRatePerDay()*invoiceDto.getNoOfDaysWorked());
						//invoiceDto.setInvoiceGeneratedDate(LocalDate.now());
							logger.info("Amounts: {}, {}, {}", invoiceDto.getTotalAmount(), invoiceDto.getGst(), invoiceDto.getTotalAmountWithGst());
						//}
					ProfileParserUtils.generateInvoice(invoiceDto,filePath);
					//invoiceDto.
					}
					}
					
				}

				//savedCommissions = commissionService.saveCommissionsTemporary(commissionDtoList);
			}

		} catch (Exception e) {
			logger.error("Error in generating invoices,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in generating incoices. Please try again. \n" + e.getMessage());
		}
		return new ResponseEntity<>(invoiceList, HttpStatus.OK);
	}
}
