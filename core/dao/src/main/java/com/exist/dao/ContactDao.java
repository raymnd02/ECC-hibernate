package com.exist.dao;

import java.util.*;
import com.exist.model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Transaction;
 
 
public class ContactDao {
	
    private Session currentSession;
     
    private Transaction currentTransaction;
 
    public ContactDao() {
    }
 
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Person.class).addClass(Contact.class).addClass(Role.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
 
    public void persist(Contact entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Contact entity) {
        getCurrentSession().update(entity);
    }
 
    public Contact findById(int id) {
        Contact contact = (Contact) getCurrentSession().get(Contact.class, id);
        return contact; 
    }
	
    public void delete(Contact entity) {
        getCurrentSession().delete(entity);
    }
 

    public List<Contact> findAll() {
        List<Contact> contact = (List<Contact>) getCurrentSession().createQuery("from Contact").list();
        return contact;
    }
	
}