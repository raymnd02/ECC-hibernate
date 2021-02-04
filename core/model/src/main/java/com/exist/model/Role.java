package com.exist.model;
import java.util.*;

public class Role implements java.io.Serializable{
	private int roleId;
	private String role;
	private List<Person> personRole;
	
	public Role(){}
	public Role(String role) {
		this.role = role;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
		return this.roleId;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setPersonRole(List<Person> personRole) {
		this.personRole = personRole;
	}
	
	public List<Person> getPersonRole() {
		return personRole;
	}
	
		
	@Override
    public String toString() {
        return "\nRoles: \n" + 
		"Role id: " + this.roleId + ",\n" +
		"Role: " + this.role + "\n";
    }
}
