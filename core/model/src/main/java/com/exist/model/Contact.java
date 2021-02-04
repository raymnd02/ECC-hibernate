package com.exist.model;
public class Contact implements java.io.Serializable{
	private int contactId;
	private long landLine;
	private long mobileNumber;
	private String email;
	private Person person;
	
	public Contact(){}
		
	public Contact(long landLine,long mobileNumber,String email) {
		// this.contactId = contactId;
		this.landLine = landLine;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	public int getContactId() {
		return this.contactId;
	}
	
	
	public void setLandLine(long landLine) {
		this.landLine = landLine;
	}
	
	public long getLandLine() {
		return this.landLine;
	}
	
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public long getMobileNumber() {
		return this.mobileNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return this.person;
	}
	@Override
    public String toString() {
        return "\nContact Id: " + this.contactId + ",\n" +
			"Landline Number: " + this.landLine + ",\n" +
			"Mobile Number: " + this.mobileNumber + ",\n" +
			"Email: " + this.email + "\n";
    }
}
