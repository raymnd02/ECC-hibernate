package com.exist.dao;

import java.util.*;
import com.exist.model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Transaction;
 
 
public class PersonDao {
 
    private Session currentSession;
     
    private Transaction currentTransaction;
 
    public PersonDao() {
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
 
    public void persist(Person entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Person entity) {
        getCurrentSession().update(entity);
    }
 
    public Person findById(int id) {
        Person person = (Person) getCurrentSession().get(Person.class, id);
        return person; 
    }
 
    public void delete(Person entity) {
        getCurrentSession().delete(entity);
    }
 
    public List<Person> findAll() {
        List<Person> persons = (List<Person>) getCurrentSession().createQuery("from Person").list();
        return persons;
    }
	
    public List<Person> findAllByLastName() {
        List<Person> persons = (List<Person>) getCurrentSession().createQuery("from Person ORDER BY last_name").list();
        return persons;
    }
	
    public List<Person> findAllByDateHired() {
        List<Person> persons = (List<Person>) getCurrentSession().createQuery("from Person ORDER BY date_hired").list();
        return persons;
    }
 
}