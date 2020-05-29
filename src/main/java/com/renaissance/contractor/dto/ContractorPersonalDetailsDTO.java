package com.renaissance.contractor.dto;

import java.math.BigInteger;

public class ContractorPersonalDetailsDTO {
	private BigInteger contractorId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;
	private String gender;;
	private String personalEmail;
	private String officeEmail;
	private String mobilePhone;
	private String homePhone;;
	private String previousName;
	private String address;
	private String city;
	private String state;
	private BigInteger zipCode;
	private String country;
	private String otherCountry;
	private String visaCategory;
	private String visaType;
	private String visaValidDate;
	private String emergencyContactName;
	private String emergencyContactNumber;
	private String emergencyContactAddress;
	private String emergencyContactEmail;
	private String emergencyContactRelation;
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
	public BigInteger getZipCode() {
		return zipCode;
	}
	public void setZipCode(BigInteger zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getOtherCountry() {
		return otherCountry;
	}
	public void setOtherCountry(String otherCountry) {
		this.otherCountry = otherCountry;
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
	@Override
	public String toString() {
		return "ContractorPersonalDetailsDTO [contractorId=" + contractorId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", gender="
				+ gender + ", personalEmail=" + personalEmail + ", officeEmail=" + officeEmail + ", mobilePhone="
				+ mobilePhone + ", homePhone=" + homePhone + ", previousName=" + previousName + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", country=" + country
				+ ", otherCountry=" + otherCountry + ", visaCategory=" + visaCategory + ", visaType=" + visaType
				+ ", visaValidDate=" + visaValidDate + ", emergencyContactName=" + emergencyContactName
				+ ", emergencyContactNumber=" + emergencyContactNumber + ", emergencyContactAddress="
				+ emergencyContactAddress + ", emergencyContactEmail=" + emergencyContactEmail
				+ ", emergencyContactRelation=" + emergencyContactRelation + "]";
	}
	

}
