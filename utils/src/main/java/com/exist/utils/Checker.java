package com.exist.utils;
import java.util.*;
import java.io.*;
import com.exist.model.*;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.lang3.StringUtils;
import java.text.*;
public class Checker {
	
	public static int inputChecker (String message,int inputMin, int inputMax,Scanner sc) {
		boolean choiceChecker = false;
		int choice = 0;

		do {
			System.out.print(message);
			String input = sc.nextLine();
			while(!StringUtils.isNumeric(input)) {
				System.out.println("Invalid Input\n");
				System.out.println("Select between " + inputMin + " and " + inputMax);
			    System.out.print(message);
				input = sc.nextLine();
			} 
			choice = Integer.parseInt(input);
			if (choice >= inputMin && choice <= inputMax){
				choiceChecker = true;
			} else {
				System.out.println("Invalid Input\n");
				System.out.println("Select between " + inputMin + " and " + inputMax);
			}
		} while (choiceChecker == false);
		return choice;
	}
	
	public static int inputChecker (String message,Scanner sc) {
		String input = "input";
		int returnVal = 0;
		do {
			System.out.print(message);
			input = sc.nextLine();
			if(!StringUtils.isNumeric(input)) {
				System.out.println("Input must numeric");
				System.out.print(message);
			} else {
				returnVal = Integer.parseInt(input);
			}
			
		} while (!StringUtils.isNumeric(input));
		return returnVal;
	}
	public static String notNullString(String message,Scanner sc) {
		String strChecker = "";
		do {
			System.out.print(message);
			strChecker = sc.nextLine();
			if(StringUtils.isBlank(strChecker)) {
				System.out.println(message + "cant be null!!!");
			}
		} while(StringUtils.isBlank(strChecker));
		return strChecker;
	}
	
	public static int inputIsNumeric(String message,Scanner sc) {
		String strChecker = "";
		int numeric = 0;
		do {
			System.out.print(message);
			strChecker = sc.nextLine();
			if(!StringUtils.isNumeric(strChecker)) {
				System.out.println(message + "must be numeric!!!");
			} else {
				numeric = Integer.parseInt(strChecker);
			}
		} while(!StringUtils.isNumeric(strChecker));
		return numeric;
	}
	
	public static double inputIsDouble(String message,Scanner sc) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		
		System.out.print(message);
		while (!sc.hasNextDouble())
		{
			System.out.println(message + "must be numeric!!!");
			System.out.print(message);
			sc.next();
		}
		double numeric = sc.nextDouble(); 
		return Double.parseDouble(df2.format(numeric));
	}
	public static long checkIfValidMobileNum(String message,Scanner sc) {
		String strChecker = "";
		long mobileNumber = 0;
		boolean checker = true;
		do {
			checker = true;
			System.out.print(message);
			strChecker = sc.nextLine();
			if(!StringUtils.isNumeric(strChecker)) {
				System.out.println(message + "must be numeric!!!");
				checker = false;
			} else if(StringUtils.length(strChecker) != 12) {
				System.out.println(message + "must me 12 digit!!!");
				checker = false;
			} else if(!StringUtils.startsWith(strChecker,"09")){
				System.out.println(message + "must start with 09!!!");
				checker = false;
			} else {
				 mobileNumber = Long.parseLong(strChecker.substring(1));
			}
		} while(checker == false);
		return mobileNumber;
	}
	
	public static Date inputDate(String message,Scanner sc) {
		Date date = new Date();
		String strChecker = "";
		boolean checker = true;
		do {
			checker = true;
			System.out.print("yyyy-MM-dd ");
			System.out.print(message);
			strChecker = sc.nextLine();
			if(!GenericValidator.isDate(strChecker, "yyyy-MM-dd", true)) {
				System.out.println("invalid input");
				checker = false;
			} else {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
				try {
					date = (Date)formatter.parse(strChecker);
				}catch(ParseException e){
					checker = false;
				}
			}
		} while(checker == false);
		return date;
	}
	
	public static boolean inputBoolean(String message,Scanner sc){
		boolean returnVal = true;
		System.out.print(message);
		String strChecker = sc.nextLine();
		while(!(strChecker.equalsIgnoreCase("Y") ||strChecker.equalsIgnoreCase("N"))){
			
			System.out.print(message);
			strChecker = sc.nextLine();
		}
			
		return returnVal;
	}
	public static boolean personRoleChecker(List<Role> roles,String personRole) {
		boolean checker = false;
		if(!roles.isEmpty()){			
			for (Role role : roles) {
				if(role.getRole().equals(personRole)){
					checker = true;
				}
			}
		}
		
		return checker;
	}
	public static boolean personRoleChecker(List<Role> roles,int personRoleId) {
		boolean checker = false;
		if(!roles.isEmpty()){			
			for (Role role : roles) {
				if(role.getRoleId() == personRoleId){
					checker = true;
				}
			}
		}
		
		return checker;
	}
	public static String emailValid(String message,Scanner sc){
		String email = "";
		do {
			System.out.print(message);
			email = sc.nextLine();
			if(!GenericValidator.isEmail(email)){
				System.out.println("Invalid Email!!");
			}
		} while(!GenericValidator.isEmail(email));
		return email;
	}
	public static boolean contactIdChecker(List<Contact> contacts,int contactId) {
		boolean checker = false;
		if(!contacts.isEmpty()){			
			for (Contact contact : contacts) {
				if(contact.getContactId() == contactId){
					checker = true;
				}
			}
		}
		
		return checker;
	}
	public static Contact getContactByID(List<Contact> contacts,int contactId){
		Contact contactToReturn = new Contact();
		for (Contact contact : contacts) {
			if(contact.getContactId() == contactId){
				contactToReturn.setContactId(contactId);
				contactToReturn.setLandLine(contact.getLandLine());
				contactToReturn.setMobileNumber(contact.getMobileNumber());
				contactToReturn.setEmail(contact.getEmail());
				contactToReturn.setPerson(contact.getPerson());
			}
		}
		return contactToReturn;
	}
	
}
