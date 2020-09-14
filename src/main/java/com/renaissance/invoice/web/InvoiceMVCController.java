package com.renaissance.invoice.web;

import static com.renaissance.util.APIConstants.INVOICE_MAIN;

import java.util.ArrayList;
import java.util.List;

import static com.renaissance.util.APIConstants.CREATE_INVOICE_RUN;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;

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

import com.renaissance.commission.dto.CommissionDTO;
import com.renaissance.commission.service.CommissionManagmentService;
import com.renaissance.invoice.dto.InvoiceDTO;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class InvoiceMVCController {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceMVCController.class);
	@Autowired
	CommissionManagmentService commissionService;

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
	public ResponseEntity<?> commissionRun(@PathVariable String monthyear, @PathVariable String startdate,@PathVariable String enddate, HttpServletRequest request) {
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
						for(CommissionDTO commission:commissionList) {
							InvoiceDTO invoice= new InvoiceDTO();
							BeanUtils.copyProperties(commission, invoice);
							invoice.setContractorInvoiceNotes("Purchase Order No:  4000151508 Vendor No: 700007822 " );
							invoice.setAddress("Level 4&5, 818 Bourke Street, Docklands, Victoria - 3008 Australia");
							invoice.setStartDate(startdate);
							invoice.setEndDate(enddate);
							invoiceList.add(invoice);
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
}
