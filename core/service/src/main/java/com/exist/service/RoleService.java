package com.exist.service;
 
import java.util.*;
 
import com.exist.dao.*;
import com.exist.model.*;
 
public class RoleService {
 
    private static RoleDao roleDao;
 
    public RoleService() {
        roleDao = new RoleDao();
    }
 
    public void persist(Role entity) {
        roleDao.openCurrentSessionwithTransaction();
        roleDao.persist(entity);
        roleDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Role entity) {
        roleDao.openCurrentSessionwithTransaction();
        roleDao.update(entity);
        roleDao.closeCurrentSessionwithTransaction();
    }
	
	public List<Role> findAll() {
        roleDao.openCurrentSession();
        List<Role> role = roleDao.findAll();
        roleDao.closeCurrentSession();
        return role;
    }
 
    public Role findById(int id) {
        roleDao.openCurrentSession();
        Role role = roleDao.findById(id);
        roleDao.closeCurrentSession();
        return role;
    }
	
	public List<Role> SearchRole(String roleSearch) {
        roleDao.openCurrentSession();
        List<Role> role = roleDao.findRole(roleSearch);
        roleDao.closeCurrentSession();
        return role;
    }
 
    public void delete(int id) {
        roleDao.openCurrentSessionwithTransaction();
        Role role = roleDao.findById(id);
        roleDao.delete(role);
        roleDao.closeCurrentSessionwithTransaction();
    }
 
 
    public RoleDao roleDao() {
        return roleDao;
    }
}