package com.renaissance.profile.parser.util;
/**
 * This enum keeps all the regular expressions needed for parsing the profile.
 * @author Vassavi
 *
 */
public enum RegExp {
    LINK("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)" + 
    		"|(?:(?:https?|ftp):\\/\\/)?[\\w\\-?=%.]+\\.[\\w/\\-?=%.]+"+ 
    		"|/(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?/" + 
    		"|((http|ftp|https):\\/\\/)?(([\\w.-]*)\\.([\\w]*))"
    		+ "|linkedin.com\\*"),
    EMAIL("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"),
    /*PHONE("(\\+?\\(61\\)|\\(\\+?61\\)|\\+?61|\\(0[1-9]\\)|0[1-9])?( ?-?[0-9]){7,9} |"
    		+ "(0[1-9]\\)|0[1-9])?( ?-?[0-9]){7,9} |"
    		+ "(\\(\\+[0-9]\\)|\\+61|\\(0[1-9]\\)|0[1-9])?( ?-?[0-9]){6,9} |"
    		+ "(\\+?\\(61\\)|\\(\\+?61\\)|\\+?61|\\(0[1-9]\\)|0[1-9])+( ?-?[0-9]){7,9}|"
    		+"(\\(+61\\)|\\+61|\\(0[1-9]\\)|0[1-9])?( ?-?[0-9]){6,9} |"
    		+ "\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})"
    		+ "|\\d{11}|(?:\\d{3}-){2}\\d{4}"
    		+ "|\\(\\d{3}\\)\\d{3}-?\\d{4}|\\d\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d |"
    		+ "(\\+?\\(61\\)|\\(\\+?61\\)|\\+?61)|\\d{3}\\s\\d{3}\\s\\d{3}|"
    		+ "\\d{3}\\s\\d{3}\\s\\d{3}|"
    		+ "\\d{9}"),*/
	/*
	 * PHONE("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})|" +
	 * "\\d{11}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}|" +
	 * "\\d\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d" + "|\\d\\d\\d\\d\\d\\d\\d\\d\\d"),
	 */
    PHONE( "^\\+(?:[0-9] ?){6,14}[0-9]$"
    		+ "|\\b\\d{4}\\s\\d{3}\\s\\d{3}"
    		+ "|\\b(?:\\+?61|0)[2-478](?:[ -]?[0-9]){8}"
    		+ "|\\b 0?(61)0?(\\d{1,2})(\\d{4})(\\d{4})"
    		+ "|\\b\\d{5}\\s\\d{3}\\s\\d{3}"
    		+"|\\b\\d{10}"
    		+ "|\\b\\d{11}"
    		+ "|\\b\\d{12}"
    		+"|\\b\\d{3}\\s\\d{3}\\s\\d{3}"
    		+ "|\\b(\\(\\+61\\)|\\+61|(0[1-9])|0[1-9])?( ?-?[0-9]){6,9}"
    		+ "|^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})$"
    		+ "|\\b([\\+-]?\\d{2}|\\d{4})\\s*\\(?\\d+\\)?\\s*(?:\\d+\\s*)+\\b"
    		+ "|(?:\\+?61)?(?:\\(0\\)[23478]|\\(?0?[23478]\\)?)\\d{8}"
    		+ "|(?:\\+?61)?(?:\\(0\\)[23478]|\\(?0?[23478]\\)?)\\d{9}"
    		+ "|(\\(+61\\)|\\+61|\\(0[1-9]\\)|0[1-9])?( ?-?[0-9]){6,9}"
    		+ "|^(?:\\+?(61))? ?(?:\\((?=.*\\)))?(0?[2-57-8])\\)? ?(\\d\\d(?:[- ](?=\\d{3})|(?!\\d\\d[- ]?\\d[- ]))\\d\\d[- ]?\\d[- ]?\\d{3})$"
    		+ "|^(\\+?\\(61\\)|\\(\\+?61\\)|\\+?61|\\(0[1-9]\\)|0[1-9])?( ?-?[0-9]){7,9}$"),
    OBJECTIVE("^\\b(Professional Summary|Objective(s?)|Objectives:|OBJECTIVE(S?)|Summary|SUMMARY|Statement|Profile)\\b"), // summary included here
    EDUCATION("^\\b(ACADEMIC CREDENTIALS|PROFESSIONAL QUALIFICATIONS|Education(s?)|EDUCATION(S?)|Academics|ACADEMICS|EDUCATIONAL CREDENTIALS|Academic Qualification"
    		+ "|Educational Qualifications|Academic Detail(s?)|UNIVERSITY EDUCATION|Qualifications)\\b"),
    NAME("\\b(Name|NAME|Candidate Name|CANDIDATE NAME|Candidate|CANDIDATE)\\b"),
    EXPERIENCE("^\\b(Professional Work Experiences|Revelant Experience|Experience(s?)|EXPERIENCE(S?)|Work Experience|WORK EXPERIENCE(S?)|Work History|WORK HISTORY|Work Detail(s?)|WORK DETAIL(S?)"
    		+ "|EMPLOYMENT HISTORY|Employment History|PROFESSIONAL EXPERIENCE|Career Path)\\b"),
    SKILLS("^\\b(KEY SKILLS & COMPETENCIES|Technical Skills:|TECHNOLOGY|Competency Summary|IT Knowledge|KEY TECHNICAL COMPETENCIES|Skill(s?) & Expertise(s?)|Tool(s?) & Technolog(y?|ies?)|Skill(s?)|SKILL(S?)|Technical Skills|TECHNICAL SKILLS|"
    		+ "Computer Skills|COMPUTER SKILLS|Technical Summary|TECHNICAL SUMMARY|TECHNICAL IT SKILL(S?)|Core Skill(s?)|CORE SKILL(S?)"
    		+ "|IT TOOLS|TECHNICAL (IT) SKILL(S?)|PROFESSIONAL SKILLS|IT SKILL PROFILE|Technical Stack|Technical Experience|Areas of Expertise"
    		+ "|IT COMPETENCIES|PROFILE SYNOPSIS)\\b"),
    
    
   // LANGUAGE("\\b(Language(s?)|LANGUAGE(S?))\\b"),
    INTEREST("^\\b(Interest(s?)|INTEREST(S?)|Activity|Activities|ACTIVITY|ACTIVITIES)\\b"),
    MEMBERSHIP("^\\b(Membership(s?)|MEMBERSHIP(S?))\\b"),
    VISA("^\\b(Visa(s?)|VISA(S?)|VISA STATUS|Visa Status|Status|Australian Work Authorization|RESIDENCE STATUS|Work rights in Australia)\\b"),
    ADDITIONAL("\\b(Other Detail(s?)|OTHER DETAIL(S?))\\b"),
    REFERENCE("^\\b(Reference(s?)|REFERENCE(S?)|Professional Referernce(s?)|PROFESSIONAL REFERENCE(S?)|LINKEDIN RECOMMENDATIONS)\\b"),
    CERTIFICATION("^\\b(RELEVANT INDUSTRY COURSES|Training|Certification(s?)|CERTIFICATION(S?)|Accomplishment(s?)|ACCOMPLISHMENT(S?)"
    		+ " |PROFESSIONAL CERTIFICATION(S?) & TRAINING(S?)|Professional Certification(s?) & Training(s?)"
    		+ "|PROFESSIONAL CERTIFICATION(S?)|Professional Certification(s?)|CERTIFICATES & AFFILIATES)\\b"),
    AWARDS("^\\b(Award(s?)|AWARD(S?)|Honor(s?)|HONOR(S?)|Recognition(s?)|RECOGNITION(S?)|Academic Accolades|Achievements)\\b"),
    PROJECTS("^\\b(Key Projects|Roles)\\b"),
    COMMUNITY("^\\b(Community Interests|PORTFOLIO|Additional Information|PERSONAL DETAILS|CAREER SUMMARY)\\b"),
    SECONDARY_SKILLS("^\\b(Project Management Skills|GENERAL COMPETENCIES, SKILLS AND BEHAVIOURS|SUMMARY OF RELEVANT EXPERIENCE|HIGHLIGHTS|Strengths)\\b"),
    TRAINING("^\\b(Trainings Attended)"),
    DATEFROMTO("");
  //  DATEFROMTO("^([A-Za-z]+\\s)?([0-9]{4})\\s[-]\\s\\b((P|p)resent|(C|c)urrent)\\b|([A-Za-z]+\\s)?([0-9]{4})");

    /**
     * Note:
     * - if you have a combination of words make sure you put them in the beginning of the list
     */

    private final String name;

    RegExp(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
