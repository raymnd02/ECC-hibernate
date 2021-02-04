package com.exist.app;
import com.exist.model.*;
import com.exist.service.*;
import com.exist.dao.*;
import com.exist.utils.Checker;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.io.*;
 
public class App {
	static Scanner sc = new Scanner(System.in);
	static PersonService personService = new PersonService();
	static RoleService roleService = new RoleService();
	static ContactService contactService = new ContactService();
	static List<Role> role = new ArrayList<>();
    public static void main( String[] args ) {
		
		System.out.println("-----------------------------------");
		System.out.println("-------------Hibernate-------------");
		System.out.println("-----------------------------------\n\n");
		
		
		option();
    }
	public static void option() {
		System.out.println("\n");
		System.out.println(" ---------------Menu-------------- ");
		System.out.println("1  :  Show List of Person");
		System.out.println("2  :  Show List of Role");
		System.out.println("3  :  Create Person");
		System.out.println("4  :  Delete Person");
		System.out.println("5  :  Update Person");//-
		System.out.println("6  :  Add Person Role");
		System.out.println("7  :  Delete Person Role");
		System.out.println("8  :  Add Contact");
		System.out.println("9  :  Update Contact");
		System.out.println("10 :  Delete Contact");
		System.out.println("11 :  Add Role");
		System.out.println("12 :  Update Role");
		System.out.println("13 :  Delete Role");
		System.out.println("14 :  Exit");
		
		int choice = Checker.inputChecker("Select: ",1,14,sc);
		System.out.println("");
		
		switch(choice) {
			case 1:
				List<Person> persons = new ArrayList<>();
				String message = "Select Order By: \n" +
								"1 : Grade Weighted Average\n" +
								"2 : Date Hired\n" +
								"3 : Last Name\n" +
								"Select: ";
				int orderList = Checker.inputChecker(message,1,3,sc);
				if(orderList == 1) {
					persons = personService.findAllByGWA();
					if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
				} else if(orderList == 2) {
					persons = personService.findAllByDateHired();
					if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
				} else {
					persons = personService.findAllByLastName();
					if(persons.isEmpty()) {System.out.println("\nNo person on the List!!\n");}
				}
				for (Person person : persons) {System.out.println("-" + person.toString());}
				option();
				break;
			case 2:
				System.out.println("\n");
				role = roleService.findAll();
				for (Role roles : role) {
					System.out.println("-" + roles.toString());
				}
				option();
				break;
			case 3:
				System.out.println("--Add Person--");
				personService.persist(Service.addPerson(sc));
				option();
				break;
			case 4:
				int personIdToDelete = Checker.inputChecker("Enter Person Id: ",sc);
				boolean confirmation = Checker.inputBoolean("Are you sure?(Y/N): ",sc);
				if(confirmation == true){
					try {
						Person personToDelete = personService.findById(personIdToDelete);
						List<Role> deleteRoleToDeletePerson = new ArrayList<>();
						personToDelete.setRole(deleteRoleToDeletePerson);
						try{ personService.update(personToDelete); }catch(Exception e) {}
						try{ personService.delete(personIdToDelete); }catch(Exception e) {}
						// personService.delete(personIdToDelete);
					} catch(IllegalArgumentException e) {
						System.out.println("Error:Person Id not found!!");
					}
				}
				option();
				break;
			case 5:
				System.out.println("--Edit Person--");
				message = "Select you want to edit: \n" +
					"1  :First Name\n" +
					"2  :Middle Name\n" +
					"3  :Last Name\n" +
					"4  :Suffix Name\n" +
					"5  :Title Name\n" +
					"6  :Street No\n" +
					"7  :Barangay\n" +
					"8  :Municipaliti/City\n" +
					"9 :Zipcode\n" +
					"10 :Birthday\n" +
					"11 :Grade Weighted Average\n" +
					"12 :Date Hired\n" +
					"13 :Current Employed\n" +
					"Select: ";
				
				System.out.println("");
				int personIdToEdit = Checker.inputChecker("Enter Person Id: ",sc);
				
				try {
					Person personToEdit = personService.findById(personIdToEdit);
					System.out.println("");
					int personDetailToEdit = Checker.inputChecker(message,1,13,sc);
					
					personService.update(Service.editPerson(personToEdit,personDetailToEdit,sc));
				} catch(Exception e) {
					System.out.println("Error:Person Id not found!!");
				}
				option();
				break;
			case 6:
				System.out.println("--Add Person Role--");
				int personIdToAddPersonRole = Checker.inputChecker("Enter Person Id: ",sc);
				int roleIdToAddPersonRole = Checker.inputChecker("Enter Role Id: ",sc);
				boolean confirmationToAddPersonRole = Checker.inputBoolean("Are you sure?(Y/N): ",sc);
				if(confirmationToAddPersonRole == true){
					try {
						Person personToAddPersonRole = personService.findById(personIdToAddPersonRole);
						Role roleToAddPersonRole = roleService.findById(roleIdToAddPersonRole);
						
						role = personToAddPersonRole.getRole();
						role.add(roleToAddPersonRole);
						personToAddPersonRole.setRole(role);
						if(Checker.personRoleChecker(role,roleToAddPersonRole.getRole()) == false){
							System.out.println("\n\nRole is already Exist");
						} else {
							personService.update(personToAddPersonRole);
						}
					} catch(Exception e) {
						System.out.println("\n\nError: Please check the person or role id!!");
					}
				}
				option();
				break;
			case 7:
				System.out.println("--Delete Person Role--");
				int personIdToDeletePersonRole = Checker.inputChecker("Enter Person Id: ",sc);
				try {
					Person personToDeletePersonRole = personService.findById(personIdToDeletePersonRole);
					role = personToDeletePersonRole.getRole();
					if(!role.isEmpty()) {
						System.out.println("\n\nRoles available to delete!!!\n");
						for (Role roles : role) {
							System.out.println("-" + roles.toString());
						}
						int roleIdToDeletePersonRole = Checker.inputChecker("Delete Role Id: ",sc);
						if(Checker.personRoleChecker(role,roleIdToDeletePersonRole) == false){
							System.out.println("\n\nRole is Doesn't exist");
						} else {
							personToDeletePersonRole.setRole(Service.removeSameRole(role,roleIdToDeletePersonRole));
							personService.update(personToDeletePersonRole);
						}
					} else {
						System.out.println("\n\nNo available roles to delete!!");
					}
					
				} catch(Exception e) {
					System.out.println("\n\nError: Person id doesn't exist!!");
				}
				option();
				break;
			case 8:
				System.out.println("--Add Contact--");
				int personIdAddContact = Checker.inputChecker("Enter Person Id: ",sc);
				try {
					Person personToAddContact = personService.findById(personIdAddContact);
					System.out.println("\n\n");
					long landLine = Checker.inputChecker("Landline Number: ",sc);
					long mobileNumber = Checker.checkIfValidMobileNum("Mobile Number: ",sc);
					String email = Checker.emailValid("Email: ",sc);
					Contact contactAdd = new Contact(landLine,mobileNumber,email);
					contactAdd.setPerson(personToAddContact);
					contactService.persist(contactAdd);
				} catch(Exception e) {
					System.out.println("Error:Person Id not found!!");
				}
				option();
				break;
			case 9:
				System.out.println("--UpdateContact--");
				int personIdUpdateContact = Checker.inputChecker("Enter Person Id: ",sc);
				try {
					Person personToUpdateContact = personService.findById(personIdUpdateContact);
					System.out.println("\n\n");
					List<Contact> contactToUpdateList = personToUpdateContact.getContact();
					if(!contactToUpdateList.isEmpty()){
							
						System.out.println("\n\nContact Available");
						for (Contact contact : contactToUpdateList) {
							System.out.println(contact.toString());
						}
						int contactIdToUpdate = Checker.inputChecker("Enter Contact Id to update: ",sc);
						if(Checker.contactIdChecker(contactToUpdateList,contactIdToUpdate) == false) {
							System.out.println("\nContact Id not found!!");
						} else {
							message = "Select You want to edit: \n" +
								"1 : Landline Number\n" +
								"2 : Mobile Number\n" +
								"3 : Email\n" +
								"Select: ";
							int contactDetailToEdit = Checker.inputChecker(message,1,3,sc);
							contactService.update(Service.editContact(contactDetailToEdit,Checker.getContactByID(contactToUpdateList,contactIdToUpdate),sc));
						}
					} else { System.out.println("\n\n No Contact Available\n"); }
				} catch(Exception e) {
					System.out.println("Error:Id not found!!");
				}
				option();
				break;
			case 10:
				System.out.println("--DeleteContact--");
				int personIdDeleteContact = Checker.inputChecker("Enter Person Id: ",sc);
				try {
					Person personToDeleteContact = personService.findById(personIdDeleteContact);
					System.out.println("\n\n");
					List<Contact> contactToDeleteList = personToDeleteContact.getContact();
					if(!contactToDeleteList.isEmpty()){
						System.out.println("\n\nContact Available");
						for (Contact contact : contactToDeleteList) {
							System.out.println(contact.toString());
						}
						int contactIdToDelete = Checker.inputChecker("Enter Contact Id to delete: ",sc);
						if(Checker.contactIdChecker(contactToDeleteList,contactIdToDelete) == false) {
							System.out.println("\nContact Id not found!!");
						} else {
							contactService.delete(contactIdToDelete);
						}
					} else { System.out.println("\n\n No Contact Available\n"); }
				} catch(Exception e) {
					System.out.println("Error:Id not found!!");
				}
				option();
				break;
			case 11:
				System.out.println("--AddRole--");
				String addRole = Checker.notNullString("Role: ",sc).toLowerCase().toLowerCase();
				if(!roleService.SearchRole(addRole).isEmpty()) {
					System.out.println(StringUtils.capitalize(addRole) + " is Already Exist!!");
				} else {
					Role roleAdd = new Role(addRole);
					roleService.persist(roleAdd);
				}
				option();
				break;
			case 12:
				System.out.println("--Update Role--");
				int updateRoleId = Checker.inputChecker("Enter Role Id: ",sc);
				String updateRole = Checker.notNullString("Role: ",sc).toLowerCase().toLowerCase();
				boolean confirmationToUpdateRole = Checker.inputBoolean("Are you sure?(Y/N): ",sc);
				if(confirmationToUpdateRole == true){
					try {
						if(!roleService.SearchRole(updateRole).isEmpty()) {
							System.out.println(StringUtils.capitalize(updateRole) + " is Already Exist!!");
						} else {
							Role roleUpdate = new Role(updateRole);
							roleUpdate.setRoleId(updateRoleId);
							roleService.update(roleUpdate);
						}
					} catch(Exception e) {System.out.println("Error:Id not found!!");}
				}
				option();
				break;
			case 13:
				System.out.println("--Delete Role--");
				int roleIdToDelete = Checker.inputChecker("Enter Role Id: ",sc);
				boolean confirmationToDeleteRole = Checker.inputBoolean("Are you sure?(Y/N): ",sc);
				if(confirmationToDeleteRole == true){
					try {
						Role roleToDeletePersonRole = roleService.findById(roleIdToDelete);
						List<Person> personToDeleteRoles = roleToDeletePersonRole.getPersonRole();
						for (Person personToDeleteRole : personToDeleteRoles) {
							role = personToDeleteRole.getRole();
							if(!role.isEmpty()) {
								personToDeleteRole.setRole(Service.removeSameRole(role,roleIdToDelete));
								personService.update(personToDeleteRole);
							}
						}
						personToDeleteRoles.clear();
						roleToDeletePersonRole.setPersonRole(personToDeleteRoles);
						roleService.update(roleToDeletePersonRole);
						try {roleService.delete(roleIdToDelete);} catch(Exception e) {}
					} catch(Exception e) {
						System.out.println("Error:Id not found!!");
					}
				}
				option();
				break;
			case 14:
				System.out.println("Exit");
				System.exit(0);
				break;
		}
	} 
}
