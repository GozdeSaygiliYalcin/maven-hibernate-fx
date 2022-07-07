package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.Role;

import jakarta.persistence.TypedQuery;

public class RoleDao implements IRepository<Role> {

	@Override
	public void create(Role entity) {
		
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("User data is added to Database");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("A problem has oocured while adding address data");
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Role deleteRole = find(id);
			if(deleteRole != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteRole);
				session.getTransaction().commit();

				System.out.println("Successfully deleted.");
			}
			
		} catch (Exception e) {
		System.out.println("A problem has occured while DELETE opertaion.");
		e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Role entity) {
		Session session = null;
		try {
			Role updateRole = find(id);
			if(updateRole != null) {				
				updateRole.setDescription(entity.getDescription());
				updateRole.setCreatedOn(entity.getCreatedOn());
				updateRole.setId(entity.getId());
				updateRole.setRoleName(entity.getRoleName());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateRole);
				session.getTransaction().commit();
				System.out.println("Successfully updated. Welldone.");
				
			}
			
		} catch (Exception e) {
			System.out.println("A problem has occured while updating.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void listAll() {
		
		Session session = databaseConnectionHibernate();
		String hql = "SELECT usr FROM Role AS usr"; // usr * yerine all olarak getiriyor
		TypedQuery<Role> typedQuery = session.createQuery(hql, Role.class);
		List<Role> roleList = typedQuery.getResultList();
		
		for (Role role : roleList) {
			System.out.println(role);
		}
	}

	@Override
	public Role find(long id) {
		
		Session session = databaseConnectionHibernate();
		Role role = new Role();
		try {
			role = session.find(Role.class, id);
			
			if( role != null) {
				System.out.println("Role is found " + role);
				return role;
			}
			else {
				System.out.println("Role is not found");
				return role;
			}
			
		} catch (Exception e) {
			System.out.println("A problem has occured during the find operation");
			e.printStackTrace();
			
		} finally {
			session.close();
			}
		return null;
	}
}
