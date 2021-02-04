package com.exist.service;
 
import java.util.*;
 
import com.exist.dao.*;
import com.exist.model.*;
 
public class PersonService {
 
    private static PersonDao personDao;
 
    public PersonService() {
        personDao = new PersonDao();
    }
 
    public void persist(Person entity) {
        personDao.openCurrentSessionwithTransaction();
        personDao.persist(entity);
        personDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Person entity) {
        personDao.openCurrentSessionwithTransaction();
        personDao.update(entity);
        personDao.closeCurrentSessionwithTransaction();
    }
 
    public Person findById(int id) {
        personDao.openCurrentSession();
        Person person = personDao.findById(id);
        personDao.closeCurrentSession();
        return person;
    }
 
    public void delete(int id) {
        personDao.openCurrentSessionwithTransaction();
        Person person = personDao.findById(id);
        personDao.delete(person);
        personDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Person> findAllByGWA() {
        personDao.openCurrentSession();
        List<Person> person = personDao.findAll();
		Collections.sort(person, new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return Double.compare(person1.getGradeWeightedAverage(), person2.getGradeWeightedAverage());
			}
		});
        personDao.closeCurrentSession();
        return person;
    }
	
	public List<Person> findAllByLastName() {
        personDao.openCurrentSession();
        List<Person> person = personDao.findAllByLastName();
        personDao.closeCurrentSession();
        return person;
    }
	
	public List<Person> findAllByDateHired() {
        personDao.openCurrentSession();
        List<Person> person = personDao.findAllByDateHired();
        personDao.closeCurrentSession();
        return person;
    }
 
 
    public PersonDao personDao() {
        return personDao;
    }
}