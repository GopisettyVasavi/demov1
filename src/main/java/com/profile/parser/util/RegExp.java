package com.profile.parser.util;

public enum RegExp {
    LINK("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)" + 
    		"| (?:(?:https?|ftp):\\/\\/)?[\\w\\-?=%.]+\\.[\\w/\\-?=%.]+"+ 
    		"| /(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?/" + 
    		"| ((http|ftp|https):\\/\\/)?(([\\w.-]*)\\.([\\w]*))"
    		+ "| linkedin.com\\*"),
    EMAIL("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"),
    PHONE("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})|"
    		+ "\\d{11}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}|" + 
    		"\\d\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d"
    		+ "|\\d\\d\\d\\d\\d\\d\\d\\d\\d"),
    OBJECTIVE("\\b(Objective(s?)|OBJECTIVE(S?)|Summary|SUMMARY|Statement|Profile)\\b"), // summary included here
    EDUCATION("\\b(Education(s?)|EDUCATION(S?)|Diploma(s?)|DIPLOMA(S?)|Academics|ACADEMICS)\\b"),
    NAME("\\b(Name|NAME|Candidate Name|CANDIDATE NAME|Candidate|CANDIDATE)\\b"),
    EXPERIENCE("\\b(Experience(s?)|EXPERIENCE(S?)|Work Experience|WORK EXPERIENCE|Work History|WORK HISTORY | Work Detail(s?) | WORK DETAIL(S?)"
    		+ "|EMPLOYMENT HISTORY|Employment History)\\b"),
    SKILLS("\\b(Skill(s?) & Expertise(s?)|Tool(s?) & Technolog(y?|ies?)|Skill(s?)|SKILL(S?)|Technical Skills|TECHNICAL SKILLS|"
    		+ "Computer Skills|COMPUTER SKILLS|Technical Summary|TECHNICAL SUMMARY|TECHNICAL IT SKILL(S?)|Core Skill(s?)|CORE SKILL(S?))\\b"),
    LANGUAGE("\\b(Language(s?)|LANGUAGE(S?))\\b"),
    INTEREST("\\b(Interest(s?)|INTEREST(S?)|Activity|Activities|ACTIVITY|ACTIVITIES)\\b"),
    MEMBERSHIP("\\b(Membership(s?)|MEMBERSHIP(S?))\\b"),
    VISA("\\b(Visa(s?)|VISA(S?)|VISA STATUS|Visa Status|Status)\\b"),
    ADDITIONAL("\\b(Other Detail(s?)|OTHER DETAIL(S?))\\b"),
    REFERENCE("\\b(Reference(s?)|REFERENCE(S?))|Professional Referernce(s?)|PROFESSIONAL REFERENCE(S?)\\b"),
    CERTIFICATION("\\b(Award(s?)|AWARD(S)|Honor(s?)|HONOR(S?)|Certification(s?)|CERTIFICATION(S?)|Accomplishment(s?)|ACCOMPLISHMENT(S?)"
    		+ " | PROFESSIONAL CERTIFICATION(S?) & TRAINING(S?) | Professional Certification(s?) & Training(s?))"
    		+ "| PROFESSIONAL CERTIFICATION(S?)| Professional Certification(s?)\\b"),
    DATEFROMTO("([A-Za-z]+\\s)?([0-9]{4})\\s[-]\\s\\b((P|p)resent|(C|c)urrent)\\b|([A-Za-z]+\\s)?([0-9]{4})");

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
