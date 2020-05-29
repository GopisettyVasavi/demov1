package com.renaissance.profile.parser.model;

public class EmployeeForm {
	private Integer employeeId;

	private String employeeName;

	private String employeeRole;

	private String email;

	private String contactPhone;

	private String address;

	private String joiningDate;

	private String profileParserAppPwd;

	private String profileParserAppLogin;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String visaType;
	
	private String visaNo;
	
	private String visaValidDate;
	
	private String lastUpdatedByUser;
	

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getProfileParserAppPwd() {
		return profileParserAppPwd;
	}

	public void setProfileParserAppPwd(String profileParserAppPwd) {
		this.profileParserAppPwd = profileParserAppPwd;
	}

	public String getProfileParserAppLogin() {
		return profileParserAppLogin;
	}

	public void setProfileParserAppLogin(String profileParserAppLogin) {
		this.profileParserAppLogin = profileParserAppLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	public String getVisaValidDate() {
		return visaValidDate;
	}

	public void setVisaValidDate(String visaValidDate) {
		this.visaValidDate = visaValidDate;
	}

	public String getLastUpdatedByUser() {
		return lastUpdatedByUser;
	}

	public void setLastUpdatedByUser(String lastUpdatedByUser) {
		this.lastUpdatedByUser = lastUpdatedByUser;
	}

	@Override
	public String toString() {
		return "EmployeeForm [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeRole="
				+ employeeRole + ", email=" + email + ", contactPhone=" + contactPhone + ", address=" + address
				+ ", joiningDate=" + joiningDate + ", profileParserAppPwd=" + profileParserAppPwd
				+ ", profileParserAppLogin=" + profileParserAppLogin + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", visaType=" + visaType + ", visaNo=" + visaNo
				+ ", visaValidDate=" + visaValidDate + "]";
	}



}
