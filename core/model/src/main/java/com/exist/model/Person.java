package com.exist.model;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
public class Person implements java.io.Serializable{

	private int personId;
	
	private String lastName;
	private String firstName;
	private String middleName;
	private String suffixName;
	private String titleName;
	
	
	private int streetNo;
	private String barangay;
	private String municipalityOrCity;
	private int zipcode;
	
	private Date birthday;
	private double gradeWeightedAverage;
	private Date dateHired;
	private boolean currentEmployed;
	private List<Contact> contact = new ArrayList<>();
	private List<Role> role = new ArrayList<>();
	
	public Person(){}
	public Person(String firstName,String lastName,Date birthday,double gradeWeightedAverage,Date dateHired,boolean current_employed) {
		// this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gradeWeightedAverage = gradeWeightedAverage;
		this.dateHired = dateHired;
		this.currentEmployed = current_employed;
		// this.contact = contact;
	}
	
	public void setStreetNo(int streetNo) {
		this.streetNo = streetNo;
	}
	
	public int getStreetNo() {
		return this.streetNo;
	}
	
	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}
	
	public String getBarangay() {
		return this.barangay;
	}
	
	public void setMunicipalityOrCity(String municipalityOrCity) {
		this.municipalityOrCity = municipalityOrCity;
	}
	
	public String getMunicipalityOrCity() {
		return this.municipalityOrCity;
	}
	
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	public int getZipcode() {
		return this.zipcode;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
	
	public String getSuffixName() {
		return this.suffixName;
	}
	
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	public String getTitleName() {
		return this.titleName;
	}
	
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	public List<Role> getRole() {
		return this.role;
	}
	
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	
	public List<Contact> getContact() {
		return this.contact;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getPersonId() {
		return this.personId;
	}
	

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getBirthday() {
		return this.birthday;
	}
	
	
	public void setGradeWeightedAverage(double gradeWeightedAverage) {
		this.gradeWeightedAverage = gradeWeightedAverage;
	}
	public double getGradeWeightedAverage() {
		return this.gradeWeightedAverage;
	}
	
	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}
	public Date getDateHired() {
		return this.dateHired;
	}
	
	
	public void setCurrentEmployed(boolean currentEmployed) {
		this.currentEmployed = currentEmployed;
	}
	public boolean getCurrentEmployed() {
		return this.currentEmployed;
	}
	
	@Override
    public String toString() {
		String currentlyEmployed = "";
		String roleList = "";
		String contactList = "";
		if(this.currentEmployed == true) {
			currentlyEmployed = "Yes";
		} else {
			currentlyEmployed = "No";
		}
		if(!this.role.isEmpty()){
			roleList = "Role List:\n";
			for (Role roles : this.role) {
				roleList = roleList + "   Role:\n" +
				"\tRole Id: " + roles.getRoleId() + ",\n\tRole:" + StringUtils.capitalize(roles.getRole()) + ",\n";
			}
		}
		if(!this.contact.isEmpty()){
			contactList = "Contact List: \n";
			for (Contact contacts : this.contact) {
				contactList = contactList + 
				"   Contact:" +
				"\tContact Id: " + contacts.getContactId() +
				",\n\tLandline Number: " + contacts.getLandLine() + 
				",\n\tMobile Number: 0" + contacts.getMobileNumber() +
				",\n\tEmail: " + contacts.getEmail() + ",\n";
			}
		}
        return "\nPerson: \n" + 
		"Person Id: " + this.personId + ", \n" + 
		"First Name: " + StringUtils.capitalize(this.firstName) + ", \n" + 
		"Middle Name: " + StringUtils.capitalize(this.middleName) + ", \n" + 
		"Last Name: " + StringUtils.capitalize(this.lastName) + ", \n" + 
		"Suffix Name: " + StringUtils.capitalize(this.suffixName) + ", \n" + 
		"Title Name:  " + StringUtils.capitalize(this.titleName) + ", \n" + 
		"Street No: " + this.streetNo + ", \n" + 
		"Barangay: " + StringUtils.capitalize(this.barangay) + ", \n" + 
		"Municipaliti/City: " + StringUtils.capitalize(this.municipalityOrCity) + ", \n" + 
		"Zipcode: " + this.zipcode + ", \n" + 
		"Birthday: " + this.birthday + ", \n" + 
		"Grade Weighted Average: " + this.gradeWeightedAverage + ", \n" + 
		"Date Hired: " + this.dateHired + ", \n" + 
		"Current Employed: " + currentlyEmployed+ ",\n" + roleList + contactList;
    }
}
