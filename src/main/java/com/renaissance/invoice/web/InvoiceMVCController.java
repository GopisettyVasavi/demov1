package com.renaissance.invoice.web;

import static com.renaissance.util.APIConstants.CREATE_INVOICE_RUN;
import static com.renaissance.util.APIConstants.EDIT_INVOICE;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;
import static com.renaissance.util.APIConstants.GENERATE_INVOICE;
import static com.renaissance.util.APIConstants.INVOICE_MAIN;
import static com.renaissance.util.APIConstants.SAVE_INVOICE;
import static com.renaissance.util.APIConstants.SEARCH_INVOICES;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.renaissance.common.service.ConstantsService;
import com.renaissance.invoice.dto.InvoiceDTO;
import com.renaissance.invoice.dto.InvoiceSearchForm;
import com.renaissance.invoice.service.InvoiceDetailsService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class InvoiceMVCController {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceMVCController.class);
	@Autowired
	InvoiceDetailsService invoiceService;

	@Autowired
	ConstantsService constantsService;

	/**
	 * This method is invoked to load Invoice main page.
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
	 * This method will fetch invoices for the selected given month and year.
	 * 
	 * @param monthyear
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(CREATE_INVOICE_RUN)
	public ResponseEntity<?> invoiceRun(@PathVariable String monthyear, HttpServletRequest request) {
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
					invoiceList = invoiceService.getInvoices(commissionMonthYear);
					if (!ProfileParserUtils.isObjectEmpty(invoiceList)) {
						Double gst = constantsService.getConstantValue(ProfileParserConstants.GST);
						for (InvoiceDTO invoice : invoiceList) {
							invoice.setMonthYear("01/" + commissionMonthYear);
							if (invoice.getStatus() == null || invoice.getStatus().equalsIgnoreCase(""))
								invoice.setStatus("New");
							/** DONT REMOVE */
							if (invoice.getInvoiceNo() == null)
								invoice.setInvoiceNo(BigInteger.valueOf(ProfileParserUtils.getRandomNumber()));

							if (invoice.getInclGst() == null || invoice.getInclGst().equalsIgnoreCase("true"))
								invoice.setInclGst("true");
							else
								invoice.setInclGst("false");

							if (gst != null)
								invoice.setGstPercent(gst);
							else
								invoice.setGstPercent(0.00);

							if (invoice.getStartDate() == "" || invoice.getStartDate() == null) {
								invoice.setStartDate("01/" + commissionMonthYear);
							}

							if (invoice.getEndDate() == "" || invoice.getEndDate() == null) {
								invoice.setEndDate(ProfileParserUtils.getLastDayOfMonth(commissionMonthYear) + "/"
										+ commissionMonthYear);
							}
							if (invoice.getGst() == null || invoice.getGst() == 0.00)
								invoice.setGst(0.00);

							if (invoice.getTotalAmount() == null || invoice.getTotalAmount() == 0.00)
								invoice.setTotalAmount(0.00);

							if (invoice.getTotalAmountWithGst() == null || invoice.getTotalAmountWithGst() == 0.00)
								invoice.setTotalAmountWithGst(0.00);

							/*
							 * if (!ProfileParserUtils.isObjectEmpty(invoice.getNoOfDaysWorked()) &&
							 * invoice.getNoOfDaysWorked() > 0) {
							 * invoice.setTotalAmount(invoice.getNoOfDaysWorked() *
							 * invoice.getBillRatePerDay()); invoice.setGst(gst / 100 *
							 * invoice.getTotalAmount());
							 * invoice.setTotalAmountWithGst(invoice.getTotalAmount() + invoice.getGst()); }
							 */

							// invoiceList.add(invoice);
							/** DONT REMOVE */
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("There is an issue in fetching invoices...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		return new ResponseEntity<>(invoiceList, HttpStatus.OK);
	}

	/**
	 * This method will be invoked to generate invoices
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(GENERATE_INVOICE)
	public ResponseEntity<?> generateInvoices(@RequestBody List<InvoiceDTO> invoiceList,
			HttpServletRequest request) {
		String generatedFilePath = "";
		List<InvoiceDTO> returnInvoiceList = null;
		try {
			/*
			 * if (!ProfileParserUtils.isObjectEmpty(filePath)) { filePath =
			 * filePath.replaceAll("SLASH", "\\\\");
			 * 
			 * }
			 */
			if (!ProfileParserUtils.isObjectEmpty(invoiceList)) {
				returnInvoiceList = new ArrayList<InvoiceDTO>();
				for (InvoiceDTO invoiceDto : invoiceList) {
					if (!ProfileParserUtils.isObjectEmpty(invoiceDto)) {
						if (null != invoiceDto.getGenerateInvoice()
								&& invoiceDto.getGenerateInvoice().equalsIgnoreCase(ProfileParserConstants.TRUE)) {

							invoiceDto.setInvoiceGeneratedDate(LocalDate.now());
							generatedFilePath = ProfileParserUtils.generateInvoice(invoiceDto);
							logger.info("File saving invoked.. client,{} ",
									generatedFilePath + "\\" + invoiceDto.getContractorName() + ".docx");
							
							logger.info("File saving invoked..finished client");
							invoiceDto.setFilePath(generatedFilePath);
							invoiceDto.setStatus("Submitted");

						}

					}

				}
				returnInvoiceList = invoiceService.saveInvoices(invoiceList);

				if (!ProfileParserUtils.isObjectEmpty(returnInvoiceList)) {
					returnInvoiceList.sort(Comparator.comparing(InvoiceDTO::getContractorName));
					int i = 1;
					for (InvoiceDTO invoiceDto : returnInvoiceList) {
						invoiceDto.setId(i);
						i++;
					}
				}
			}

		} catch (Exception e) {
			logger.error("Error in generating invoices,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in generating incoices. Please try again. \n" + e.getMessage());
		}
		return new ResponseEntity<>(returnInvoiceList, HttpStatus.OK);
	}

	/**
	 * This method will be invoked by the UI to edit invoice.
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(EDIT_INVOICE)
	public ResponseEntity<?> editInvoice(@RequestBody InvoiceDTO invoiceDto, HttpServletRequest request) {

		try {
			if (!ProfileParserUtils.isObjectEmpty(invoiceDto)) {
				invoiceDto = invoiceService.editInvoice(invoiceDto);

			}

		} catch (Exception e) {
			logger.error("Error in saving invoices,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in saving incoices. Please try again. \n" + e.getMessage());
		}
		return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
	}

	/**
	 * This method will search invoices by the given criteria and return matching
	 * records.
	 * 
	 * @param contractorSearchForm
	 * @param request
	 * @return
	 */
	@PostMapping(SEARCH_INVOICES)
	public ResponseEntity<?> searchInvoices(@RequestBody InvoiceSearchForm invoiceSearchForm,
			HttpServletRequest request) {
		List<InvoiceDTO> returnInvoiceList = null;
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}
			logger.info("Invoice Search Criteria, {}", invoiceSearchForm);
			returnInvoiceList = invoiceService.searchInvoices(invoiceSearchForm);

			if (!ProfileParserUtils.isObjectEmpty(returnInvoiceList)) {
				returnInvoiceList.sort(Comparator.comparing(InvoiceDTO::getContractorName));
				int i = 1;
				for (InvoiceDTO invoiceDto : returnInvoiceList) {
					invoiceDto.setId(i);
					i++;
				}
			}
			logger.info("Search details, {}", returnInvoiceList.size());

		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}

		return new ResponseEntity<>(returnInvoiceList, HttpStatus.OK);
	}

	/**
	 * This method will be invoked by the UI to save invoices.
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(SAVE_INVOICE)
	public ResponseEntity<?> saveInvoices(@RequestBody List<InvoiceDTO> invoiceList, HttpServletRequest request) {
		List<InvoiceDTO> returnInvoiceList = null;
		try {
			if (!ProfileParserUtils.isObjectEmpty(invoiceList)) {

				returnInvoiceList = invoiceService.saveInvoices(invoiceList);

				if (!ProfileParserUtils.isObjectEmpty(returnInvoiceList)) {
					returnInvoiceList.sort(Comparator.comparing(InvoiceDTO::getContractorName));
					int i = 1;
					for (InvoiceDTO invoiceDto : returnInvoiceList) {
						invoiceDto.setId(i);
						i++;
					}
				}

			}

		} catch (Exception e) {
			logger.error("Error in saving invoices,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in saving incoices. Please try again. \n" + e.getMessage());
		}
		return new ResponseEntity<>(returnInvoiceList, HttpStatus.OK);
	}

}
