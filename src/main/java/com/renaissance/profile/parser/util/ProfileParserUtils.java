package com.renaissance.profile.parser.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
			logger.info("DATE for parsing...,{}",date);
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
}
