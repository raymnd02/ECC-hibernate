package com.exist.service;
 
import java.util.*;
 
import com.exist.dao.*;
import com.exist.model.*;
 
public class ContactService {
 
    private static ContactDao contactDao;
 
    public ContactService() {
        contactDao = new ContactDao();
    }
    public void persist(Contact entity) {
        contactDao.openCurrentSessionwithTransaction();
        contactDao.persist(entity);
        contactDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Contact entity) {
        contactDao.openCurrentSessionwithTransaction();
        contactDao.update(entity);
        contactDao.closeCurrentSessionwithTransaction();
    }
	
	public List<Contact> findAll() {
        contactDao.openCurrentSession();
        List<Contact> contact = contactDao.findAll();
        contactDao.closeCurrentSession();
        return contact;
    }
 
    public Contact findById(int id) {
        contactDao.openCurrentSession();
        Contact contact = contactDao.findById(id);
        contactDao.closeCurrentSession();
        return contact;
    }
 
    public void delete(int id) {
        contactDao.openCurrentSessionwithTransaction();
        Contact contact = contactDao.findById(id);
        contactDao.delete(contact);
        contactDao.closeCurrentSessionwithTransaction();
    }
 
 
    public ContactDao contactDao() {
        return contactDao;
    }
}