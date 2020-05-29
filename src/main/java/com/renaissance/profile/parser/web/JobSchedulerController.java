package com.renaissance.profile.parser.web;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.renaissance.profile.parser.service.ProfileService;


@RestController
public class JobSchedulerController {
	private static final Logger logger=LoggerFactory.getLogger(JobSchedulerController.class);
	
	@Autowired
	ProfileService profileService;
	
	@Scheduled(cron = "0 30 11 * * ?")
	public void scheduleTaskWithCronExpression() {
		try {
	    logger.info("Cron Task :: Execution Time START- {}", Calendar.getInstance().getTime());
	    
	    logger.info("CALLING UPDATE FIELDS.... START");
		profileService.updateAssignedFields();
		logger.info("CALLING UPDATE FIELDS.... END");
		 logger.info("Cron Task :: Execution Time END- {}", Calendar.getInstance().getTime());
		}catch(Exception e) {
			logger.error("Error in executing batch job.,{}",new Exception(e.getMessage()));
			
		}
	}

}
