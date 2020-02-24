package com.profile.parser.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import com.profile.parser.dto.CandidateDTO;
import com.profile.parser.util.RegExp;

@Service
public class ProfileParserService {

	public CandidateDTO parse(File xfile) {
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
			/*
			 * FileInputStream fis = new FileInputStream(xfile);
			 * 
			 * if (extension.equalsIgnoreCase("pdf")) { PDDocument pdfdoc =
			 * PDDocument.load(xfile); PDFTextStripperByArea stripper = new
			 * PDFTextStripperByArea(); stripper.setSortByPosition(true); PDFTextStripper
			 * tStripper = new PDFTextStripper();
			 * sb.append(tStripper.getText(pdfdoc).trim());
			 * 
			 * } if (extension.equalsIgnoreCase("doc")) { HWPFDocument hdoc = new
			 * HWPFDocument(fis); WordExtractor we = new WordExtractor(hdoc);
			 * sb.append(we.getText());
			 * 
			 * } if (extension.equalsIgnoreCase("docx")) { XWPFDocument xdoc = new
			 * XWPFDocument(OPCPackage.open(fis)); XWPFWordExtractor extractor = new
			 * XWPFWordExtractor(xdoc); sb.append(extractor.getText());
			 * 
			 * }
			 */
		} catch (Exception e) {

		}
		return filecontent.replaceAll("(?m)^\\s*$[\n\r]{1,}", "").toString().trim();// Removes extra empty lines.

	}

	private CandidateDTO populateprofileData(String content) {

		Map<String, Integer> sortedIndex = getAllSectionIndexes(content);
		CandidateDTO candidateDto = new CandidateDTO();
		candidateDto.setCandidateName(findName(content, sortedIndex));
		List<String> emails = findEmail(content);
		if (null != emails && !emails.isEmpty())
			candidateDto.setPrimaryEmail(emails.get(0));
		if (null != emails && !emails.isEmpty() && emails.size() > 1)
			candidateDto.setSecondaryEmail(emails.get(1));
		candidateDto.setEducation(findEducation(content, sortedIndex));
		candidateDto.setSkills(findSkills(content, sortedIndex));
		candidateDto.setCertification(findCertification(content, sortedIndex));
		candidateDto.setVisa(findVisa(content, sortedIndex));
		candidateDto.setSocialMediaLink(findSocialMedia(content).toString());
		// Removes all special characters to store in DB the entire file
		String profileText = populateProfileText(content);
		candidateDto.setProfileText(profileText);
		List<String> phones = findPhone(profileText);
		if (null != phones && !phones.isEmpty())
			candidateDto.setPrimaryPhone(phones.get(0));
		if (null != phones && !phones.isEmpty() && phones.size() > 1)
			candidateDto.setSecondaryPhone(phones.get(1));
		return candidateDto;
	}

	Map<String, Integer> getAllSectionIndexes(String content) {
		// List<Integer> indexesOfSection = new ArrayList<Integer>();
		Map<String, Integer> sectionIndexes = new HashMap<String, Integer>();

		RegExp[] sectionRegex = new RegExp[] { RegExp.LINK, RegExp.EMAIL, RegExp.PHONE, RegExp.OBJECTIVE,
				RegExp.EDUCATION, RegExp.EXPERIENCE, RegExp.SKILLS, RegExp.LANGUAGE, RegExp.INTEREST, RegExp.MEMBERSHIP,
				RegExp.VISA, RegExp.ADDITIONAL, RegExp.REFERENCE, RegExp.CERTIFICATION, RegExp.DATEFROMTO };
		for (RegExp r : sectionRegex) {

			storeSectionIndexes(content, r, sectionIndexes);

		}
		// Sort indexes
		Map<String, Integer> sorted = sectionIndexes.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return sorted;
	}

	private void storeSectionIndexes(String line, RegExp r, Map<String, Integer> sectionIndexes) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
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
					if (sectionIndex < content.length())
						return content.substring(sectionIndex);
				} else {
					if (sectionIndexes.get(i + 1) < content.length())
						return content.substring(sectionIndex, sectionIndexes.get(i + 1));
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
		if (returnText == null) {
			String lines[] = content.split("\\r?\\n");

			for (String line : lines) {
				if (!line.isEmpty()) {
					return line;

				}
				// break;
			}
		}
		return null;
	}

	// find Phone
	private List<String> findPhone(String content) {
		List<String> phone = new ArrayList<String>();
		Pattern pattern = Pattern.compile(RegExp.PHONE.toString());
		Matcher matcher = pattern.matcher(content);
		// System.out.println("matcher:: "+matcher);
		while (matcher.find()) {
			phone.add(matcher.group());
		}

		return phone;
	}

	// find Email
	private List<String> findEmail(String line) {
		List<String> email = new ArrayList<String>();
		Pattern pattern = Pattern.compile(RegExp.EMAIL.toString());
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			email.add(matcher.group());

		}
		return email;

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

		Pattern pattern = Pattern.compile("\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)"
				+ "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" + "|mil|biz|info|mobi|name|aero|jobs|museum"
				+ "|travel|[a-z]{2}))(:[\\d]{1,5})?" + "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?"
				+ "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)"
				+ "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" + "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*"
				+ "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

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
			returnText = content.replaceFirst(RegExp.SKILLS.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// find Education
	private String findEducation(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.EDUCATION, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			returnText = content.replaceFirst(RegExp.EDUCATION.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// find Experience
	private String findExperience(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.EXPERIENCE, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			returnText = content.replaceFirst(RegExp.EXPERIENCE.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// find Objective
	private String findObjective(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.OBJECTIVE, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			returnText = content.replaceFirst(RegExp.OBJECTIVE.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// find Certification
	private String findCertification(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.CERTIFICATION, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			returnText = content.replaceFirst(RegExp.CERTIFICATION.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// find Visa
	private String findVisa(String content, Map<String, Integer> sortedIndex) {
		String returnText = null;
		int indexOfLink = getIndexOfThisSection(RegExp.VISA, content, sortedIndex);
		if (indexOfLink != -1) {
			List<Integer> listOfSectionIndexes = getAllIndexes(sortedIndex);
			returnText = content.replaceFirst(RegExp.VISA.toString(), "");
			returnText = getSectionContent(indexOfLink, listOfSectionIndexes, content);

		}
		return returnText;

	}

	// populate ProfileText
	private String populateProfileText(String profileText) {
		profileText = profileText.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		profileText = profileText.replaceAll("[^a-zA-Z0-9\\s+]", "").trim(); // Removes all special characters to store
																				// in DB the entire file
		profileText = profileText.replaceAll("^\\s|\n\\s|\\s$", "");

		System.out.println("Profile Text in Parser..." + profileText);
		return profileText;

	}
}
