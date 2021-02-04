package com.exist.model;
import java.util.*;

public class PersonRole {
    private int personId;
	private int roleId;
	public PersonRole(){}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public int getPersonId() {
		return this.personId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
		return this.roleId;
	}
}
