package com.renaissance.profile.parser.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.renaissance.profile.parser.dto.CandidateDTO;
import com.renaissance.profile.parser.util.ProfileParserUtils;
import com.renaissance.profile.parser.util.RegExp;

@Service
public class ProfileParserService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileParserService.class);
	public CandidateDTO parse(File xfile) throws Exception{
		String content = getFileContent(xfile);
		CandidateDTO candidateDto = populateprofileData(content);
		
		// System.out.println("Content:: " + content);

		return candidateDto;
	}

	private String getFileContent(File file) {
		String filecontent = new String();
		
		try {

			Tika tika = new Tika();
			
			filecontent = tika.parseToString(file);
			
		} catch (Exception e) {
			logger.error("Error in parsing... {}",new Exception(e.getMessage()));

		}
		return filecontent.replaceAll("(?m)^\\s*$[\n\r]{1,}", "").toString().trim();// Removes extra empty lines.

	}

	private CandidateDTO populateprofileData(String content) {

		Map<String, Integer> sortedIndex = getAllSectionIndexes(content);
		CandidateDTO candidateDto = new CandidateDTO();
		candidateDto.setCandidateName(findName(content, sortedIndex));
		candidateDto=splitName(candidateDto);
		List<String> emails = findEmail(content);
		if (!ProfileParserUtils.isObjectEmpty(emails))
			candidateDto.setPrimaryEmail(emails.get(0));
		if (!ProfileParserUtils.isObjectEmpty(emails) && emails.size() > 1)
			candidateDto.setSecondaryEmail(emails.get(1));
		candidateDto.setEducation(findEducation(content, sortedIndex));
		candidateDto.setSkills(findSkills(content, sortedIndex));
		candidateDto.setCertification(findCertification(content, sortedIndex));
		String visaStatus=findVisa(content, sortedIndex);
		candidateDto.setVisaType(setVisaType(visaStatus));
		//candidateDto.setVisaType(
		candidateDto.setSocialMediaLink(findSocialMedia(content).toString());
		candidateDto.setAwards(findAwards(content, sortedIndex));
		candidateDto.setWorkExperience(findExperience(content, sortedIndex));
		candidateDto.setReference(findReferences(content, sortedIndex));
		// Removes all special characters to store in DB the entire file
		String profileText = populateProfileText(content);
		candidateDto.setProfileText(profileText);
		List<String> phones = findPhone(content);
		//logger.info("PHONE::: "+phones.toString()+" :: "+phones.size());
		if (!ProfileParserUtils.isObjectEmpty(phones))
			candidateDto.setPrimaryPhone(phones.get(0));
		if (!ProfileParserUtils.isObjectEmpty(phones) && phones.size() > 1)
			candidateDto.setSecondaryPhone(phones.get(1));
		//logger.info("Social media:: "+candidateDto.getSocialMediaLink());
		return candidateDto;
	}

	Map<String, Integer> getAllSectionIndexes(String content) {
		// List<Integer> indexesOfSection = new ArrayList<Integer>();
		Map<String, Integer> sectionIndexes = new HashMap<String, Integer>();

		RegExp[] sectionRegex = new RegExp[] { RegExp.LINK, RegExp.EMAIL, RegExp.PHONE, RegExp.OBJECTIVE,
				RegExp.EDUCATION, RegExp.EXPERIENCE, RegExp.SKILLS, RegExp.INTEREST, RegExp.MEMBERSHIP,
				RegExp.VISA, RegExp.ADDITIONAL, RegExp.REFERENCE, RegExp.CERTIFICATION,RegExp.AWARDS,RegExp.PROJECTS,RegExp.COMMUNITY,RegExp.SECONDARY_SKILLS,
				RegExp.TRAINING,RegExp.DATEFROMTO };
		for (RegExp r : sectionRegex) {

			storeSectionIndexes(content, r, sectionIndexes);

		}
		// Sort indexes
		Map<String, Integer> sorted = sectionIndexes.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return sorted;
	}

	private void storeSectionIndexes(String line, RegExp r, Map<String, Integer> sectionIndexes) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			sectionIndexes.put(r.toString(), matcher.start());
		}
	}

	private int getIndexOfThisSection(RegExp regEx, String content, Map<String, Integer> sorted) {
		Integer index = sorted.get(regEx.toString());
		if (null != index)
			return Integer.parseInt(index.toString());
		else
			return -1;
	}

	private List<Integer> getAllIndexes(Map<String, Integer> sorted) {
		List<Integer> indexesList = sorted.values().stream().collect(Collectors.toList());
		return indexesList;
	}

	String getSectionContent(int sectionIndex, List<Integer> sectionIndexes, String content) {
		for (int i = 0; i < sectionIndexes.size(); i++) {
			if (sectionIndexes.get(i) == sectionIndex) {

				if (i == sectionIndexes.size() - 1) {
					if (sectionIndex < content.length()) {
						
						return content.substring(sectionIndex);}
				} else {
					if (sectionIndexes.get(i + 1) < content.length()) {
						return content.substring(sectionIndex, sectionIndexes.get(i + 1));}
				}
			}
		}
		return null;
	}

	// Find name
	private String findName(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfName = getIndexOfThisSection(RegExp.NAME, content, sortedIndex);
		if (indexOfName != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.NAME.toString(), "");
			returnText = getSectionContent(indexOfName, listOfSectionIndexes, content);
		}
		//logger.info("Return text:: {}",returnText);
		if (returnText == null) {
			//logger.info("CONTENT:::::  ,{}",content.substring(0, 100));
			String lines[] = content.split("\\r?\\n");
			int i=0;
			for (String line: lines) {
				//logger.info("LINE:::::"+i+" :: "+line.trim());
				line=line.trim();
				if(!line.contains("Strictly")&&!line.contains("PROFESSIONAL PROFILE")) {
				if(line.contains(",")) {
					String names[]=line.split(",");
					line=names[0];
					//logger.info("LINE111:::::"+i+" :: "+line.trim());
				}
				if(line.contains("-")) {
					String names[]=line.split("-");
					line=names[0];
					//logger.info("LINE2222:::::"+i+" :: "+line.trim());
				}
				if(line.contains("I am")) {
					String names[]=line.split("I am ");
					line=names[1];
					//logger.info("LINE3333:::::"+i+" :: "+line.trim()+" :: "+names[1]);
				}
				if(line.contains("CV")) {
					String names[]=line.split("CV");
					line=names[0];
					//logger.info("LINE3333:::::"+i+" :: "+line.trim()+" :: "+names[1]);
				}
				i++;
				//logger.info("LINE::::{}",line);
				if (!line.isEmpty()&&!line.contains("Page")&&!line.startsWith("1")) {
					
					if(line.startsWith("Candidate Name:")&&line.split("Candidate Name:").length>0) {
						return line.split("Candidate Name:")[1].toString().trim();
					}
					else if(line.startsWith("Candidates Name")) {
						return lines[i].toString();
					}
					else if(line.startsWith("Name:")&&line.split("Name:").length>0) {
						//logger.info("LINE after if::::{} ",line);
						return line.split("Name:")[1].toString().trim();
					}
					else if(line.startsWith("Name")&& line.split("Name").length>0) {
						//logger.info("LINE after if::::{} ",line);
						return line.split("Name")[1].toString().trim();
					}
					else if(!line.contains("Name")) { return line.toString().trim();}
					//i++;
				}
				}	// break;
			}
		}else return returnText;
		return null;
	}

	// find Phone
	private List<String> findPhone(String content) {
		List<String> phone = new ArrayList<String>();
		Pattern pattern = Pattern.compile(RegExp.PHONE.toString());
		Matcher matcher = pattern.matcher(content);
		// System.out.println("matcher:: "+matcher);
		while (matcher.find()) {
			if(!ProfileParserUtils.isObjectEmpty(matcher.group())&&matcher.group().length()>9 )
			phone.add(matcher.group());
			//logger.info("PHONE::: ",matcher.group());
		}

		return phone;
	}

	// find Email
	private List<String> findEmail(String line) {
		List<String> email = new ArrayList<String>();
		Pattern pattern = Pattern.compile(RegExp.EMAIL.toString(),Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			email.add(matcher.group());

		}
		return email;

	}
	
	// find References
	private String findReferences(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.REFERENCE, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.REFERENCE.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// find SocialMedia
	private List<String> findSocialMedia(String content) {
		/*
		 * String returnText = null; int indexOfLink =
		 * getIndexOfThisSection(RegExp.LINK, content); if (indexOfLink != -1) {
		 * List<Integer> listOfSectionIndexes = getAllIndexes(); content =
		 * content.replaceFirst(RegExp.LINK.toString(), ""); returnText =
		 * getSectionContent(indexOfLink, listOfSectionIndexes, content);
		 * 
		 * } return returnText;
		 */

		List<String> result = new ArrayList<String>();

		Pattern pattern = Pattern.compile("^https://[a-z]{2,3}[.]linkedin[.]com/.*$ "
				+ "|^linkedin[.]com/.*  ");

		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			result.add(matcher.group());
		}
		return result;

	}

	// find Skills
	private String findSkills(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.SKILLS, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			/*
			 * logger.info("Skills...,{}",returnText); returnText =
			 * content.replaceFirst(RegExp.SKILLS.toString(), "");
			 * logger.info("Skills...,{}",returnText);
			 */
			content = content.replaceFirst(RegExp.SKILLS.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
			if(!ProfileParserUtils.isObjectEmpty(returnText))
				returnText=returnText.trim();
			if(returnText.contains("Certifications")) {
				String certifications[]=returnText.split("Certifications");
				returnText=certifications[0].trim();
			}
		}
		//logger.info("Skills...,{}",returnText);
		return returnText;

	}

	// find Education
	private String findEducation(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.EDUCATION, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.EDUCATION.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
			if(!ProfileParserUtils.isObjectEmpty(returnText)) {
				returnText=returnText.trim();
			if(returnText.contains("PROFESSIO")) {
				String certifications[]=returnText.split("PROFESSIO");
				returnText=certifications[0].trim();
			}
			if(returnText.contains("CERTIFICA")) {
				String certifications[]=returnText.split("CERTIFICA");
				returnText=certifications[0].trim();
			}
			if(returnText.contains("Employmen")) {
				String certifications[]=returnText.split("Employmen");
				returnText=certifications[0].trim();
			}
			}
		}
		return returnText;

	}

	// find Experience
	private String findExperience(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.EXPERIENCE, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.EXPERIENCE.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
			if(!ProfileParserUtils.isObjectEmpty(returnText))
				returnText=returnText.trim();
		}
		return returnText;

	}

	// find Objective
	private String findObjective(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.OBJECTIVE, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.OBJECTIVE.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
			if(!ProfileParserUtils.isObjectEmpty(returnText))
				returnText=returnText.trim();
		}
		return returnText;

	}

	// find Certification
	private String findCertification(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.CERTIFICATION, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.CERTIFICATION.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
			if(!ProfileParserUtils.isObjectEmpty(returnText)) {
				returnText=returnText.trim();
				if(returnText.contains("Roles")) {
					String certifications[]=returnText.split("Roles");
					returnText=certifications[0].trim();
				}
				if(returnText.contains("KEY TECHNICAL COMPETENCIES")) {
					String certifications[]=returnText.split("KEY TECHNICAL COMPETENCIES");
					returnText=certifications[0].trim();
				}
				
			}

		}
		return returnText;

	}
	// find Education
		private String findAwards(String content, Map<String, Integer> sortedIndex) {
			String returnText = null;
			int indexOfLink = getIndexOfThisSection(RegExp.AWARDS, content, sortedIndex);
			if (indexOfLink != -1) {
				List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
				content = content.replaceFirst(RegExp.AWARDS.toString(), "");
				returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
				if(!ProfileParserUtils.isObjectEmpty(returnText))
					returnText=returnText.trim();

			}
			return returnText;

		}
	// find Visa
	private String findVisa(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.VISA, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			content = content.replaceFirst(RegExp.VISA.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);
			if(!ProfileParserUtils.isObjectEmpty(returnText))
				returnText=returnText.trim();

		}
		//logger.info("VISA>>>>"+returnText);
		return returnText;

	}

	// populate ProfileText
	private String populateProfileText(String profileText) {
		profileText = profileText.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		profileText = profileText.replaceAll("[^a-zA-Z0-9\\s+]", "").trim(); // Removes all special characters to store
																				// in DB the entire file
		profileText = profileText.replaceAll("^\\s|\n\\s|\\s$", "");
		//logger.info("PROFILE TEXT:::,{}",profileText);
		return profileText;

	}
	public CandidateDTO splitName(CandidateDTO candidateDto) {
		
		if(!ProfileParserUtils.isObjectEmpty(candidateDto) && !ProfileParserUtils.isObjectEmpty(candidateDto.getCandidateName())) {
			String[] processedName=null;
			//logger.info("CANDIDATE NAME...{}",candidateDto.getCandidateName());
			if(candidateDto.getCandidateName().contains("Page")) {
				 processedName=candidateDto.getCandidateName().split("Page");
			}
			else if(candidateDto.getCandidateName().contains(".")) {
				//logger.info("PROCESSED NAME ENTERED IF...{},{}",candidateDto.getCandidateName());
				 processedName=candidateDto.getCandidateName().split("\\.");
			}
			else {
				processedName=candidateDto.getCandidateName().trim().split(" ");
			}
			if(!ProfileParserUtils.isObjectEmpty(processedName)) {
				String middleName="";
				//logger.info("PROCESSED NAME...{},{}",processedName,processedName.length );
				//candidateDto.setCandidateName(processedName.toString());
			if(processedName.length>0) {
				if(!ProfileParserUtils.isObjectEmpty(processedName[0])) {
				candidateDto.setFirstName(processedName[0].trim());
				//logger.info("FIRST NAME...{}",processedName[0].trim() );
				}
				if(!ProfileParserUtils.isObjectEmpty(processedName[(processedName.length)-1]) && !candidateDto.getFirstName().equalsIgnoreCase(processedName[(processedName.length)-1])) {
				candidateDto.setLastName(processedName[(processedName.length)-1].trim());
				//logger.info("LAST NAME...{}",candidateDto.getLastName() );
				}
				else
					candidateDto.setLastName(" ");
				
			}
			if(processedName.length>1) {
				
			for(int i=1;i<(processedName.length)-1;i++) {
				middleName=middleName+processedName[i]+" ";
				middleName=middleName.trim();
				
			}
			}
			if(!ProfileParserUtils.isObjectEmpty(middleName) ) {
				//logger.info("MIDDLE NAME...{}",middleName);
				candidateDto.setMiddleName(middleName);
				}else
					candidateDto.setMiddleName(" ");
		}
		}
		
		
		
		  candidateDto.setCandidateName(candidateDto.getFirstName()+" "+candidateDto.
		  getMiddleName()+" "+candidateDto.getLastName());
		
		/*
		 * logger.info("Full name,{}",candidateDto.getCandidateName().toString());
		 * 
		 * logger.info("Names...{} , {} , {}",candidateDto.getFirstName(),candidateDto.
		 * getMiddleName(),candidateDto.getLastName());
		 */
		 
		 
		 
		return candidateDto;
	}
	private String setVisaType(String visaStatus) {
		//logger.info("Visa ....,{}",visaStatus);
		if(!ProfileParserUtils.isObjectEmpty(visaStatus)) {
			if(visaStatus.toLowerCase().contains("PR".toLowerCase())||visaStatus.toLowerCase().contains("Permanent Resident".toLowerCase())) {
				return "PR";
				
			}
			else if(visaStatus.toLowerCase().contains("Citizen".toLowerCase())) {
				return "Citizen";
				
			}else if(visaStatus.toLowerCase().contains("457".toLowerCase())) {
				return "Work Visa";
				
			}
		}
		return visaStatus;
	}
	public void parseMetaData(String file) {
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(file);
		BodyContentHandler handler   = new BodyContentHandler();  
        OOXMLParser parser           = new OOXMLParser();  
        Metadata metadata            = new Metadata();  
        ParseContext pcontext        = new ParseContext();  
        try {
			parser.parse(stream, handler, metadata, pcontext);
			String[] metadatas = metadata.names();   
	         for(String data : metadatas) {  
	             logger.info("METADATA::: "+ data + ":   " + metadata.get(data));    
	         }  
		} catch (IOException | SAXException | TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
