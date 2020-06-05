package com.renaissance.contractor.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "parse.\"CONTRACTOR_PERSONAL_DETAILS\"")
public class ContractorPersonalDetailsEntity {
	@Id
	@GeneratedValue(generator = "parse.contractor_personal_sequence")
    @SequenceGenerator(
            name = "parse.contractor_personal_sequence",
            sequenceName = "parse.contractor_personal_sequence", allocationSize = 1
            
    )
	@Column(name = "\"CONTRACTOR_ID\"", unique = true)
	private BigInteger contractorId;

	@Column(name = "\"FIRST_NAME\"")
	private String firstName;

	@Column(name = "\"MIDDLE_NAME\"")
	private String middleName;

	@Column(name = "\"LAST_NAME\"")
	private String lastName;

	@Column(name = "\"DATE_OF_BIRTH\"")
	private String dateOfBirth;
	
	@Column(name = "\"GENDER\"")
	private String gender;
	
	
	@Column(name = "\"PERSONAL_EMAIL\"")
	private String personalEmail;
	
	@Column(name = "\"EMAIL2\"")
	private String officeEmail;
	
	@Column(name = "\"MOBILE_PHONE\"")
	private String mobilePhone;

	@Column(name = "\"HOME_PHONE\"")
	private String homePhone;

	@Column(name = "\"PREVIOUS_NAME\"")
	private String previousName;

	@Column(name = "\"ADDRESS\"")
	private String address;

	@Column(name = "\"CITY\"")
	private String city;

	@Column(name = "\"STATE\"")
	private String state;

	@Column(name = "\"OTHER_COUNTRY\"")
	private String otherCountry;
	
	@Column(name = "\"COUNTRY\"")
	private String country;

	@Column(name = "\"VISA_CATEGORY\"")
	private String visaCategory;
	
	@Column(name = "\"VISA_TYPE\"")
	private String visaType;
		
	@Column(name = "\"VISA_VALID_DATE\"")
	private String visaValidDate;

	@Column(name = "\"EMERGENCY_CONTACT_NAME\"")
	private String emergencyContactName;
	
	@Column(name = "\"EMERGENCY_CONTACT_NO\"")
	private String emergencyContactNumber;
	
	@Column(name = "\"EMERGENCY_CONTACT_ADDRESS\"")
	private String emergencyContactAddress;
	
	@Column(name = "\"EMERGENCY_CONTACT_EMAIL\"")
	private String emergencyContactEmail;
	
	@Column(name = "\"EMERGENCY_CONTACT_RELATION\"")
	private String emergencyContactRelation;

	@Column(name = "\"LAST_UPDATED_USER\"")
	private String lastUpdatedUser;

	@Column(name = "\"LAST_UPDATED_DATE_TIME\"")
	private LocalDateTime lastUpdatedDateTime;
	
	@Column(name = "\"ZIP_CODE\"")
	private BigInteger zipCode;

	@Column(name = "\"CONTRACTOR_FULL_NAME\"")
	private String fullName;
	
	@Column(name = "\"ABN_HOLDER\"")
	private String abnHolder;
	
	@Column(name = "\"OTHER_STATE\"")
	private String otherState;
	
	public BigInteger getContractorId() {
		return contractorId;
	}

	public void setContractorId(BigInteger contractorId) {
		this.contractorId = contractorId;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPreviousName() {
		return previousName;
	}

	public void setPreviousName(String previousName) {
		this.previousName = previousName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOtherCountry() {
		return otherCountry;
	}

	public void setOtherCountry(String otherCountry) {
		this.otherCountry = otherCountry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVisaCategory() {
		return visaCategory;
	}

	public void setVisaCategory(String visaCategory) {
		this.visaCategory = visaCategory;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaValidDate() {
		return visaValidDate;
	}

	public void setVisaValidDate(String visaValidDate) {
		this.visaValidDate = visaValidDate;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEmergencyContactAddress() {
		return emergencyContactAddress;
	}

	public void setEmergencyContactAddress(String emergencyContactAddress) {
		this.emergencyContactAddress = emergencyContactAddress;
	}

	public String getEmergencyContactEmail() {
		return emergencyContactEmail;
	}

	public void setEmergencyContactEmail(String emergencyContactEmail) {
		this.emergencyContactEmail = emergencyContactEmail;
	}

	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}

	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public LocalDateTime getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public BigInteger getZipCode() {
		return zipCode;
	}

	public void setZipCode(BigInteger zipCode) {
		this.zipCode = zipCode;
	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAbnHolder() {
		return abnHolder;
	}

	public void setAbnHolder(String abnHolder) {
		this.abnHolder = abnHolder;
	}

	public String getOtherState() {
		return otherState;
	}

	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}

	@Override
	public String toString() {
		return "ContractorPersonalDetailsEntity [contractorId=" + contractorId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", gender="
				+ gender + ", personalEmail=" + personalEmail + ", officeEmail=" + officeEmail + ", mobilePhone="
				+ mobilePhone + ", homePhone=" + homePhone + ", previousName=" + previousName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", otherCountry=" + otherCountry + ", country=" + country
				+ ", visaCategory=" + visaCategory + ", visaType=" + visaType + ", visaValidDate=" + visaValidDate
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactNumber="
				+ emergencyContactNumber + ", emergencyContactAddress=" + emergencyContactAddress
				+ ", emergencyContactEmail=" + emergencyContactEmail + ", emergencyContactRelation="
				+ emergencyContactRelation + ", lastUpdatedUser=" + lastUpdatedUser + ", lastUpdatedDateTime="
				+ lastUpdatedDateTime + ", zipCode=" + zipCode + ", fullName=" + fullName + ", otherState=" + otherState + ", abnHolder=" + abnHolder
				+ "]";
	}

	

}
