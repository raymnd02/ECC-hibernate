package com.exist.dao;

import java.util.*;
import com.exist.model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Transaction;
 
 
public class RoleDao {
	
    private Session currentSession;
     
    private Transaction currentTransaction;
 
    public RoleDao() {
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
 
    public void persist(Role entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Role entity) {
        getCurrentSession().update(entity);
    }
 
    public Role findById(int id) {
        Role role = (Role) getCurrentSession().get(Role.class, id);
        return role; 
    }
 
    public List<Role> findRole(String roleSearch) {
        List<Role> roles = (List<Role>) getCurrentSession().createQuery("from Role where role = '" + roleSearch + "'").list();
        return roles; 
    }
	
    public void delete(Role entity) {
        getCurrentSession().delete(entity);
    }
 

    public List<Role> findAll() {
        List<Role> roles = (List<Role>) getCurrentSession().createQuery("from Role").list();
        return roles;
    }
	
}