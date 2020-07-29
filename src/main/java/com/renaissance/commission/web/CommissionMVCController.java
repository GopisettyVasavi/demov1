package com.renaissance.commission.web;

import static com.renaissance.util.APIConstants.CALCULATE_COMMISSION;
import static com.renaissance.util.APIConstants.COMMISSION_MAIN;
import static com.renaissance.util.APIConstants.CREATE_COMMISSION_RUN;
import static com.renaissance.util.APIConstants.EMPTY_REDIRECT;

import java.util.ArrayList;
import java.util.Comparator;
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
import com.renaissance.commission.service.CommissionManagmentService;
import com.renaissance.common.model.CommissionsLookupEntity;
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
	 * This method is invoked to load contractor main page.
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

	@GetMapping(CREATE_COMMISSION_RUN)
	public ResponseEntity<?> commissionRun(@PathVariable String monthyear,
			RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		List<CommissionDTO>commissionList=new ArrayList<CommissionDTO>();
		try {
			if (!ProfileParserUtils.isSessionAlive(request)) {
				logger.info("Session has expired.");
				return ResponseEntity.badRequest().body("Session Expired. Please Login");
			}

			// logger.info("Search details, {}", contractorSearchForm.toString());
			logger.info("Selected month & Year,{}", monthyear);
			if(!ProfileParserUtils.isObjectEmpty(monthyear)&& monthyear.indexOf("-")!=-1) {
				String[] mmyy=monthyear.split("-");
				if(mmyy.length>1) {
				String commissionMonthYear=mmyy[1]+"/"+mmyy[0];
				
				logger.info("Selected month & Year,{}", commissionMonthYear);
				commissionList=commissionService.getCommissions(commissionMonthYear);
				}
			}
			
		} catch (Exception e) {
			logger.error("There is an issue in searching contractors...{}", new Exception(e.getMessage()));
			e.printStackTrace();
		}
		//commissionList.get(3).setNoOfDaysWorked(10);
		return new ResponseEntity<>(commissionList, HttpStatus.OK);
	}
	
	@PostMapping(CALCULATE_COMMISSION)
	public ResponseEntity<?> calculateCommission(@RequestBody List<CommissionDTO> commissionDtoList,
			HttpServletRequest request) {
		Map<String,List<CommissionDTO>> recruiterMap=new HashMap<String,List<CommissionDTO>>();;
		try {
			logger.info("Commission List..,{}", commissionDtoList.size());
			if(!ProfileParserUtils.isObjectEmpty(commissionDtoList)) {
			for(CommissionDTO commissionDto:commissionDtoList) {
				if(!ProfileParserUtils.isObjectEmpty(commissionDto.getJobStartDate())) {
					commissionDto.setParsedDate(ProfileParserUtils.parseStringDate(commissionDto.getJobStartDate()));
				}
				//logger.info("Commission details: {}", commissionDto.getParsedDate());
				if(null==commissionDto.getRecruiterName() || commissionDto.getRecruiterName().equalsIgnoreCase("")) {
					commissionDto.setRecruiterName("NA");
				}
				
			}
			commissionDtoList.sort(
				      Comparator.comparing(CommissionDTO::getParsedDate));
				/*
				 * for(CommissionDTO commissionDto:commissionDtoList) {
				 * logger.info("After sort Commission details: {}",
				 * commissionDto.getParsedDate()); }
				 */
			recruiterMap=populateCommissionObjects(commissionDtoList);
			}
			
		} catch (Exception e) {
			logger.error("Error in calculating commission,{}", e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(" An issue in calculating commission. Please try again. \n" + e.getMessage());
		}
		return ResponseEntity.ok(recruiterMap);
	}
	
	private Map<String,List<CommissionDTO>> populateCommissionObjects(List<CommissionDTO> commissionDtoList) {
		Map<String,List<CommissionDTO>> recruiterMap=new HashMap<String,List<CommissionDTO>>();
		
		recruiterMap=commissionDtoList.stream()
	      .collect(Collectors.groupingBy(CommissionDTO::getRecruiterName, HashMap::new, Collectors.toCollection(ArrayList::new)));
		
		logger.info("Recruiters:: ,{}", recruiterMap.keySet());
		
		
		Map<String,List<CommissionDTO>> processedMap=new HashMap<String,List<CommissionDTO>>();
		for(String key: recruiterMap.keySet()) {
			//logger.info("Processing for,{}",key);
			List<CommissionDTO> contractorList=recruiterMap.get(key);
			//logger.info("contractorList.size,{}",contractorList.size());
			List<CommissionDTO> processedCommissions=	processCommissionsForRecruiter(contractorList);
			/*
			 * List<CommissionDTO> sortedList = processedCommissions.stream()
			 * .sorted(Comparator.comparingInt(CommissionDTO::getId))
			 * .collect(Collectors.toList());
			 * //logger.info("processedCommissions.size,{}",processedCommissions.size());
			 */			processedMap.put(key, processedCommissions);
		}
		return processedMap;
		
	}
	
	private List<CommissionDTO> processCommissionsForRecruiter(List<CommissionDTO> commissionsList){
		
		List<CommissionsLookupEntity> commissionsLookup =constantsService.getCommissions();
		//Double recruiterCommission=0.0;
		int i=1;
		for(CommissionDTO commissionDto:commissionsList) {
			commissionDto.setId(i);
			Double lookupCommission=0.0;
			if(!ProfileParserUtils.isObjectEmpty(commissionsLookup)) {
			for(CommissionsLookupEntity commissionLookup:commissionsLookup) {
				if(!ProfileParserUtils.isObjectEmpty(commissionLookup)) {
			String commissionsRange[]=commissionLookup.getRange().split("-");
			//logger.info("Commission Range,{}", commissionsRange.length);
			if(!ProfileParserUtils.isObjectEmpty(commissionsRange) && commissionsRange.length>=2) {
				int totalContractors=commissionsList.size();
				int minRange=Integer.parseInt(commissionsRange[0]);
				int maxRange=Integer.parseInt(commissionsRange[1]);
				//logger.info("Total contractor, min range, max range,{}, {}, {}",totalContractors,minRange,maxRange);
				if(totalContractors>=minRange && totalContractors<=maxRange ) {
					//logger.info("Entered if");
					lookupCommission=commissionLookup.getPercentage();
					//logger.info("Entered if, {}, {}",lookupCommission,commissionDto.getFullName());
					break;
				}
				//if()
			}
				}
			}
			commissionDto.setCommission(lookupCommission);
			int workedDays=0;
			if(!ProfileParserUtils.isObjectEmpty(commissionDto.getNoOfDaysWorked()) )
				workedDays=commissionDto.getNoOfDaysWorked();
			Double margin=0.0;
			if(!ProfileParserUtils.isObjectEmpty(commissionDto.getGrossMargin()))
				margin=commissionDto.getGrossMargin();
			commissionDto.setCommissionForCandidate(workedDays*margin*lookupCommission);
			i++;
			}
		}
		return commissionsList;
	}

}


