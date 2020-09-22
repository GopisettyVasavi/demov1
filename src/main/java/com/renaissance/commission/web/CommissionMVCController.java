package com.renaissance.commission.web;

import static com.renaissance.util.APIConstants.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.renaissance.commission.dto.CommissionDTO;
import com.renaissance.commission.dto.FinalCommissionsDTO;
import com.renaissance.commission.dto.RecruiterCommissionsDTO;
import com.renaissance.commission.model.CommissionsLookupEntity;
import com.renaissance.commission.model.SearchCommissionForm;
import com.renaissance.commission.service.CommissionManagmentService;
import com.renaissance.common.service.ConstantsService;
import com.renaissance.profile.parser.util.ProfileParserConstants;
import com.renaissance.profile.parser.util.ProfileParserUtils;

@Controller
public class CommissionMVCController {
	private static final Logger logger = LoggerFactory.getLogger(CommissionMVCController.class);

	@Autowired
	CommissionManagmentService commissionService;

	@Autowired
	ConstantsService constantsService;

	/**
	 * This method is invoked to load commission main page.
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(COMMISSION_MAIN)
	public String index(HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return EMPTY_REDIRECT;
		}
		if (ProfileParserConstants.ADMIN
				.equalsIgnoreCase(request.getSession().getAttribute(ProfileParserConstants.EMPLOYEE_ROLE).toString()))
			return "commissionmain";
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
	@GetMapping(CREATE_COMMISSION_RUN)
	public ResponseEntity<?> commissionRun(@PathVariable String monthyear, HttpServletRequest request) {
		List<CommissionDTO> commissionList = new ArrayList<CommissionDTO>();
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
				}
			}

		} catch (Exception e) {
			logger.error("There is an issue in fetching commissions...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		return new ResponseEntity<>(commissionList, HttpStatus.OK);
	}

	/**
	 * This method is used to iterate through the list of commissions and group them
	 * by the recruiter order by joining date. Then it will fetch the commission for
	 * the recruiter from commission lookup table and calculate commission for each
	 * row. Formula used is: (Margin * Commission Percentage * Days Worked for the
	 * month).
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(CALCULATE_COMMISSION)
	public ResponseEntity<?> calculateCommission(@RequestBody List<CommissionDTO> commissionDtoList,
			HttpServletRequest request) {
		Map<String, List<CommissionDTO>> recruiterMap = new HashMap<String, List<CommissionDTO>>();
		;
		try {
			logger.info("Commission List..,{}", commissionDtoList.size());
			if (!ProfileParserUtils.isObjectEmpty(commissionDtoList)) {
				for (CommissionDTO commissionDto : commissionDtoList) {
					if (!ProfileParserUtils.isObjectEmpty(commissionDto.getJobStartDate())) {
						commissionDto
								.setParsedDate(ProfileParserUtils.parseStringDate(commissionDto.getJobStartDate()));
					}
					// logger.info("Commission details: {}", commissionDto.getParsedDate());
					if (null == commissionDto.getRecruiterName()
							|| commissionDto.getRecruiterName().equalsIgnoreCase("")) {
						commissionDto.setRecruiterName("Renaissance");
					}

				}
				commissionDtoList.sort(Comparator.comparing(CommissionDTO::getParsedDate));

				recruiterMap = populateCommissionObjects(commissionDtoList);
			}

		} catch (Exception e) {
			logger.error("Error in calculating commission,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in calculating commission. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(recruiterMap);
	}

	/**
	 * This private method is used to populate commission list for each recruiter
	 * and put it in the map.
	 * 
	 * @param commissionDtoList
	 * @return
	 */
	private Map<String, List<CommissionDTO>> populateCommissionObjects(List<CommissionDTO> commissionDtoList) {
		Map<String, List<CommissionDTO>> recruiterMap = new HashMap<String, List<CommissionDTO>>();

		recruiterMap = commissionDtoList.stream().collect(Collectors.groupingBy(CommissionDTO::getRecruiterName,
				HashMap::new, Collectors.toCollection(ArrayList::new)));

		Map<String, List<CommissionDTO>> processedMap = new HashMap<String, List<CommissionDTO>>();
		for (String key : recruiterMap.keySet()) {
			List<CommissionDTO> contractorList = recruiterMap.get(key);
			List<CommissionDTO> processedCommissions = processCommissionsForRecruiter(contractorList);
			processedMap.put(key, processedCommissions);
		}
		return processedMap;

	}

	/**
	 * This method will calculate commission for for record in commission list.
	 * 
	 * @param commissionsList
	 * @return
	 */
	private List<CommissionDTO> processCommissionsForRecruiter(List<CommissionDTO> commissionsList) {

		List<CommissionsLookupEntity> commissionsLookup = constantsService.getCommissions();
		// Double recruiterCommission=0.0;
		int i = 1;
		for (CommissionDTO commissionDto : commissionsList) {
			commissionDto.setId(i);
			Double lookupCommission = 0.0;
			if (!ProfileParserUtils.isObjectEmpty(commissionsLookup)) {
				for (CommissionsLookupEntity commissionLookup : commissionsLookup) {
					if (!ProfileParserUtils.isObjectEmpty(commissionLookup)) {
						String commissionsRange[] = commissionLookup.getRange().split("-");
						if (!ProfileParserUtils.isObjectEmpty(commissionsRange) && commissionsRange.length >= 2) {
							int totalContractors = commissionDto.getId();
							int minRange = Integer.parseInt(commissionsRange[0]);
							int maxRange = Integer.parseInt(commissionsRange[1]);
							if (totalContractors >= minRange && totalContractors <= maxRange) {
								lookupCommission = commissionLookup.getPercentage();
								break;
							}
						}
					}
				}
				commissionDto.setCommission(lookupCommission);
				int workedDays = 0;
				if (!ProfileParserUtils.isObjectEmpty(commissionDto.getNoOfDaysWorked())) {
					workedDays = commissionDto.getNoOfDaysWorked();
				}
				commissionDto.setNoOfDaysWorked(workedDays);
				Double margin = 0.0;
				if (!ProfileParserUtils.isObjectEmpty(commissionDto.getGrossMargin()))
					margin = commissionDto.getGrossMargin();
				commissionDto.setCommissionForCandidate(
						ProfileParserUtils.roundValue(workedDays * margin * lookupCommission / 100));
				i++;
			}
		}
		return commissionsList;
	}

	/**
	 * This method will return insurance%
	 * 
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping(SUPER_PERCENT)
	public ResponseEntity<?> getSuperPercent(RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request) {
		if (!ProfileParserUtils.isSessionAlive(request)) {
			logger.info("null session");
			return ResponseEntity.badRequest().body("Session Expired. Please Login");
		}
		Double superPercent = constantsService.getConstantValue(ProfileParserConstants.SUPER_ANNUATION);
		// logger.info("Super %" + superPercent);
		return new ResponseEntity<>(superPercent, HttpStatus.OK);
	}

	/**
	 * This method will be invoked by the UI to save commissions temporarily.
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(SAVE_COMMISSION)
	public ResponseEntity<?> saveCommissions(@RequestBody List<CommissionDTO> commissionDtoList,
			HttpServletRequest request) {
		List<CommissionDTO> savedCommissions = null;
		try {
			logger.info("Commission List for save..,{}", commissionDtoList.size());
			if (!ProfileParserUtils.isObjectEmpty(commissionDtoList)) {
				for (CommissionDTO commissionDto : commissionDtoList) {

					// logger.info("Commission details: {}", commissionDto.getParsedDate());
					if (null == commissionDto.getRecruiterName()
							|| commissionDto.getRecruiterName().equalsIgnoreCase("")) {
						commissionDto.setRecruiterName("Renaissance");
					}
					commissionDto.setStatus(ProfileParserConstants.TEMPORARY);
					//commissionDto
					// logger.info("Commission Dto before save,{}", commissionDto.toString());
				}

				savedCommissions = commissionService.saveCommissionsTemporary(commissionDtoList);
			}

		} catch (Exception e) {
			logger.error("Error in saving commission,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in saving commission. Please try again. \n" + e.getMessage());
		}
		return new ResponseEntity<>(savedCommissions, HttpStatus.OK);
	}

	/**
	 * This method will be invoked by UI to finalize the commissions for that
	 * particular month.
	 * 
	 * @param commissionDtoList
	 * @param request
	 * @return
	 */
	@PostMapping(FINAL_SAVE_COMMISSION)
	public ResponseEntity<?> finalSaveCommissions(@RequestBody FinalCommissionsDTO finalCommissions,HttpServletRequest request) {
		FinalCommissionsDTO savedCommissions = null;
		try {
			logger.info("Invoked final save..{}",finalCommissions.toString());
			//logger.info("Commission List for save..Final Save..,{}", commissionDtoList.size());
			if (!ProfileParserUtils.isObjectEmpty(finalCommissions)) {

				savedCommissions = commissionService.finalizeCommissions(finalCommissions);
			}

		} catch (Exception e) {
			logger.error("Error in saving commission,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in saving commission. Please try again. \n" + e.getMessage());
		}
		return new ResponseEntity<>(savedCommissions, HttpStatus.OK);
	}
	
	
	/**
	 * This method will get search inputs from UI and process them to form date ranges. Then it will invoke service method and return search results.
	 * @param searchCommissionForm
	 * @param request
	 * @return
	 */
	@PostMapping(SEARCH_COMMISSIONS)
	public ResponseEntity<?> searchContractors(@RequestBody SearchCommissionForm searchCommissionForm,
			HttpServletRequest request) {
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}

			logger.info("Search details, {}", searchCommissionForm.toString());

		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		
		String period=searchCommissionForm.getPeriod();
		if(!ProfileParserUtils.isObjectEmpty(period)) {
			String fromDate="";
			String toDate="";
			if(period.equalsIgnoreCase("CurrentFY")) {
				if(month<=5) {// means june month, so financial year has to be from june to previous july
					toDate="30/"+"06/"+year;
					year=year-1;
					fromDate="01/"+"07/"+year;
				}
				else {
					 fromDate="01/"+"07/"+year;
						year=year+1;
						 toDate="30/"+"06/"+year;
				}
				
				
				
				searchCommissionForm.setStartDate(ProfileParserUtils.parseStringDate(fromDate));
				searchCommissionForm.setEndDate(ProfileParserUtils.parseStringDate(toDate));
			}
if(period.equalsIgnoreCase("PreviousFY")) {
	
	if(month<=5) {// current date= marc 20, pre fy= july-18 to june- 19
		year=year-2;
		fromDate="01/"+"07/"+year;
		year=year+1;
		toDate="30/"+"06/"+year;
	}else {// current date= july 20, pre fy= july-19 to june- 20
		toDate="30/"+"06/"+year;
		year=year-1;
		fromDate="01/"+"07/"+year;
	}
	
	searchCommissionForm.setStartDate(ProfileParserUtils.parseStringDate(fromDate));
	searchCommissionForm.setEndDate(ProfileParserUtils.parseStringDate(toDate));
	
			}
if(period.equalsIgnoreCase("LastMonth")) {
	//month=month
	//String 
	month=month-1;
	String sMonth="0";
	if(month<10) {
		 sMonth="0"+month;
	}else {
		sMonth=""+month;
	}
		 toDate="27/"+sMonth+"/"+year;
		
		 fromDate="01/"+sMonth+"/"+year;
	
	
	searchCommissionForm.setStartDate(ProfileParserUtils.parseStringDate(fromDate));
	searchCommissionForm.setEndDate(ProfileParserUtils.parseStringDate(toDate));
	
}
if(period.equalsIgnoreCase("DateRange")) {
	String fDate=searchCommissionForm.getFromDate();
	String tDate=searchCommissionForm.getToDate();
	if(null!=fDate && fDate.indexOf("-")!=-1) {
		fDate=	fDate.replace("-", "/")	;
	}
	if(null!=tDate && tDate.indexOf("-")!=-1) {
		tDate=	tDate.replace("-", "/")	;
	}
	fromDate="01/"+fDate;
	toDate="27/"+tDate;
	searchCommissionForm.setStartDate(ProfileParserUtils.parseMonthStringDate(fromDate));
	searchCommissionForm.setEndDate(ProfileParserUtils.parseMonthStringDate(toDate));
}
		}
		logger.info("Search details, {}", searchCommissionForm.toString());
		List<RecruiterCommissionsDTO> searchResults=commissionService.searchCommissions(searchCommissionForm);
		if(!ProfileParserUtils.isObjectEmpty(searchResults)) {
			for(RecruiterCommissionsDTO recruiterCommissionDto:searchResults) {
				
				recruiterCommissionDto.setMonthYearUI(ProfileParserUtils.formatStringDate(recruiterCommissionDto.getMonthYear().toString()));
				//logger.info("Date {}", ProfileParserUtils.formatStringDate(recruiterCommissionDto.getMonthYear().toString()));
			}
			searchResults.sort(Comparator.comparing(RecruiterCommissionsDTO::getOrderDate));
		}
		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}
	
	/**
	 * This method will load full details of a selected commission
	 * @param monthyear
	 * @param recruiter
	 * @param request
	 * @return
	 */
	
	@PostMapping(COMMISSION_DETAILS_RECRUITER)
	public ResponseEntity<?>loadCommissionrDetails(@PathVariable String monthyear,@PathVariable String recruiter,  HttpServletRequest request) {
		List<CommissionDTO> searchResults = new ArrayList<CommissionDTO>();
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}

			logger.info("Controller invoked, to load details...id is, {}, {}", monthyear,recruiter);
			
			String formattedDate=ProfileParserUtils.formatStringDateToString(monthyear);
			logger.info("Controller invoked, after format ...id is, {}", formattedDate);
			 searchResults=commissionService.loadCommissionDetails(formattedDate,recruiter);

		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		

		return new ResponseEntity<>(searchResults, HttpStatus.OK);
	}
}
