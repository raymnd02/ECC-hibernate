package com.exist.service;
import java.util.*;
import com.exist.model.*;
import com.exist.utils.Checker;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.lang3.StringUtils;
import java.text.*;
public class Service {
	public static Person addPerson(Scanner sc){
		String firstName = Checker.notNullString("First Name*: ",sc).toLowerCase();
		System.out.print("Middle Name: ");
		String middleName = sc.nextLine().toLowerCase();
		String lastName = Checker.notNullString("Last Name*: ",sc).toLowerCase();
		System.out.print("Suffix Name: ");
		String suffixname = sc.nextLine().toLowerCase();
		System.out.print("Title Name: ");
		String titleName = sc.nextLine().toLowerCase();
		System.out.println("\n--Address--");
		int streetNumber = Checker.inputIsNumeric("Street Number*: ",sc);
		String barangay = Checker.notNullString("Barangay*: ",sc).toLowerCase();
		String municipality = Checker.notNullString("Municipality/City*: ",sc).toLowerCase();
		int zipcode = Checker.inputIsNumeric("Zipcode*: ",sc);
		Date birthDay = Checker.inputDate("Birthday: ",sc);
		Date dateHired = Checker.inputDate("Date Hired: ",sc);
		double gradeWeightedAverage = Checker.inputIsDouble("Grade Weighted Average: ",sc);
		
		boolean currentEmployed = Checker.inputBoolean("Current Employed(Y/N): ",sc);
		
		Person personAdd = new Person(firstName,lastName,birthDay,gradeWeightedAverage,dateHired,currentEmployed);
		personAdd.setMiddleName(middleName);
		personAdd.setSuffixName(suffixname);
		personAdd.setTitleName(titleName);
		personAdd.setStreetNo(streetNumber);
		personAdd.setBarangay(barangay);
		personAdd.setMunicipalityOrCity(municipality);
		personAdd.setZipcode(zipcode);
		return personAdd;
	}
	
	public static Person editPerson(Person person,int toEdit,Scanner sc){
		System.out.println("");
		switch(toEdit) {
			case 1:
				String firstName = Checker.notNullString("First Name*: ",sc).toLowerCase();
				person.setFirstName(firstName);
				break;
			case 2:
				System.out.print("Middle Name: ");
				String middleName = sc.nextLine().toLowerCase();
				person.setMiddleName(middleName);
				break;
			case 3:
				String lastName = Checker.notNullString("Last Name*: ",sc).toLowerCase();
				person.setLastName(lastName);
				break;
			case 4:
				System.out.print("Suffix Name: ");
				String suffixname = sc.nextLine().toLowerCase();
				person.setSuffixName(suffixname);
				break;
			case 5:
				System.out.print("Title Name: ");
				String titleName = sc.nextLine().toLowerCase();
				person.setTitleName(titleName);
				break;
			case 6:
				int streetNumber = Checker.inputIsNumeric("Street Number*: ",sc);
				person.setStreetNo(streetNumber);
				break;
			case 7:
				String barangay = Checker.notNullString("Barangay*: ",sc).toLowerCase();
				person.setBarangay(barangay);
				break;
			case 8:
				String municipality = Checker.notNullString("Municipality/City*: ",sc).toLowerCase();
				person.setMunicipalityOrCity(municipality);
				break;
			case 9:
				int zipcode = Checker.inputIsNumeric("Zipcode*: ",sc);
				person.setZipcode(zipcode);
				break;
			case 10:
				Date birthDay = Checker.inputDate("Birthday: ",sc);
				person.setBirthday(birthDay);
				break;
			case 11:
				double gradeWeightedAverage = Checker.inputIsDouble("Grade Weighted Average: ",sc);
				person.setGradeWeightedAverage(gradeWeightedAverage);
				break;
			case 12:
				Date dateHired = Checker.inputDate("Date Hired: ",sc);
				person.setDateHired(dateHired);
				break;
			case 13:
				boolean currentEmployed = Checker.inputBoolean("Current Employed(Y/N): ",sc);
				person.setCurrentEmployed(currentEmployed);
				break;
		}
		return person;
	}
	
	public static Contact editContact(int contactDetailToEdit,Contact contactToEdit,Scanner sc) {
		Contact contact = contactToEdit;
		if(contactDetailToEdit == 1){
			long landLineToEdit = Checker.inputChecker("Landline Number: ",sc);
			contact.setLandLine(landLineToEdit);
		} else if(contactDetailToEdit == 2){
			long mobileNumberToEdit = Checker.checkIfValidMobileNum("Mobile Number: ",sc);
			contact.setMobileNumber(mobileNumberToEdit);
		} else {
			String emailToEdit = Checker.emailValid("Email: ",sc);
			contact.setEmail(emailToEdit);
		}
		return contact;
	}
	public static List<Role> removeSameRole(List<Role> role ,int roleToBeRemove) {
		Iterator<Role> itr = role.iterator();
		while (itr.hasNext()) { 
			Role roleToBeDeleted = itr.next(); 
			if (roleToBeDeleted.getRoleId() == roleToBeRemove) { 
				itr.remove(); 
			} 
		}
		return role;
	}
	
}
