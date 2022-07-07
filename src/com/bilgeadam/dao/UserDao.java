package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.User;

import jakarta.persistence.TypedQuery;

public class UserDao implements IRepository<User> {

	@Override
	public void create(User entity) {
		
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Address data is added to Database");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("A problem is oocured while adding address data");
		}
	
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			User deleteUser = find(id);
			if(deleteUser != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteUser);
				session.getTransaction().commit();

				System.out.println("Successfully deleted.");
					
			}
			
		} catch (Exception e) {
		System.out.println("Some problem occured while DELETE opertaion.");
		e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	@Override
	public void update(long id, User entity) {
		Session session = null;
		try {
			User updateUser = find(id);
			if(updateUser != null) {				
				updateUser.setEmail(entity.getEmail());
				updateUser.setPassword(entity.getPassword());
				updateUser.setUserDetail(entity.getUserDetail());
				updateUser.setRole(entity.getRole());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateUser);
				session.getTransaction().commit();
				System.out.println("Successfully updated. Welldone.");
				
			}
			
		} catch (Exception e) {
			System.out.println("Some problem has occured while UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	@Override
	public void listAll() {
		
		Session session = databaseConnectionHibernate();
		String hql = "SELECT usr FROM User AS usr"; // usr * yerine all olarak getiriyor
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> userList = typedQuery.getResultList();
		
		for (User user : userList) {
			System.out.println(user);
		}
	}

	@Override
	public User find(long id) {
		
		Session session = databaseConnectionHibernate();
		User user = new User();
		try {
			user = session.find(User.class, id);
			
			if( user != null) {
				System.out.println("user found " + user);
				return user;
			}
			else {
				System.out.println("user not found");
				return user;
			}
			
		} catch (Exception e) {
			System.out.println("a problem has occured during the find operation");
			e.printStackTrace();
			
		} finally {
			session.close();
			}
		return null;
	}
}
