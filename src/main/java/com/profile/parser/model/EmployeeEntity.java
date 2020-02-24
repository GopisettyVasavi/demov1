package com.profile.parser.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"EMPLOYEE\"")
public class EmployeeEntity {
	@Id
	@GeneratedValue(generator = "parse.employee_id_sequence")
	@SequenceGenerator(name = "parse.employee_id_sequence", sequenceName = "parse.employee_id_sequence"

	)
	@Column(name = "\"EMPLOYEE_ID\"")
	private Integer employeeId;

	@Column(name = "\"EMPLOYEE_NAME\"")
	private String employeeName;

	@Column(name = "\"EMPLOYEE_ROLE\"")
	private String employeeRole;

	@Column(name = "\"EMAIL\"")
	private String email;

	@Column(name = "\"CONTACT_NO\"")
	private String contactPhone;

	@Column(name = "\"ADDRESS\"")
	private String address;

	@Column(name = "\"JOINING_DATE\"")
	private Date joiningDate;

	@Column(name = "\"PROFILE_PARSER_APP_PWD\"")
	private String profileParserAppPwd;
	
	@Column(name = "\"PROFILE_PARSER_APP_LOGIN\"")
	private String profileParserAppLogin;
	
	@Column(name = "\"WORK_END_DATE\"")
	private Date workEndDate;

	@Column(name = "\"LAST_UPDATED_BY_USER\"")
	private String lastUpdatedByUser;

	@Column(name = "\"LAST_UPDATED_DATE_TIME\"")
	private LocalDateTime lastUpdatedByDateTime;
	
	

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

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getProfileParserAppPwd() {
		return profileParserAppPwd;
	}

	public void setProfileParserAppPwd(String profileParserAppPwd) {
		this.profileParserAppPwd = profileParserAppPwd;
	}

	public Date getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}

	public String getLastUpdatedByUser() {
		return lastUpdatedByUser;
	}

	public void setLastUpdatedByUser(String lastUpdatedByUser) {
		this.lastUpdatedByUser = lastUpdatedByUser;
	}

	public LocalDateTime getLastUpdatedByDateTime() {
		return lastUpdatedByDateTime;
	}

	public void setLastUpdatedByDateTime(LocalDateTime lastUpdatedByDateTime) {
		this.lastUpdatedByDateTime = lastUpdatedByDateTime;
	}

	public String getProfileParserAppLogin() {
		return profileParserAppLogin;
	}

	public void setProfileParserAppLogin(String profileParserAppLogin) {
		this.profileParserAppLogin = profileParserAppLogin;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeRole="
				+ employeeRole + ", email=" + email + ", contactPhone=" + contactPhone + ", address=" + address
				+ ", joiningDate=" + joiningDate + ", profileParserAppPwd=" + profileParserAppPwd + ", workEndDate="
				+ workEndDate + ", lastUpdatedByUser=" + lastUpdatedByUser + ", lastUpdatedByDateTime="
				+ lastUpdatedByDateTime + "]";
	}

}
