package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.UserDetail;

import jakarta.persistence.TypedQuery;

public class UserDetailDao implements IRepository<UserDetail> {

	@Override
	public void create(UserDetail entity) {
		
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
			UserDetail deleteUserDetail = find(id);
			if(deleteUserDetail != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteUserDetail);
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
	public void update(long id, UserDetail entity) {
		Session session = null;
		try {
			UserDetail updateUserDetail = find(id);
			if(updateUserDetail != null) {				
				updateUserDetail.setFirstName(entity.getFirstName());
				updateUserDetail.setLastName(entity.getLastName());
				updateUserDetail.setBio(entity.getBio());
				updateUserDetail.setId(entity.getId());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateUserDetail);
				session.getTransaction().commit();
				System.out.println("Successfully updated. Welldone.");
				
			}
			
		} catch (Exception e) {
			System.out.println("A problem has occured while UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void listAll() {
		
		Session session = databaseConnectionHibernate();
		String hql = "SELECT usr FROM User AS usr"; // usr * yerine all olarak getiriyor
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		List<UserDetail> userList = typedQuery.getResultList();
		
		for (UserDetail user : userList) {
			System.out.println(user);
		}
	}

	@Override
	public UserDetail find(long id) {
		
		Session session = databaseConnectionHibernate();
		UserDetail user = new UserDetail();
		try {
			user = session.find(UserDetail.class, id);
			
			if( user != null) {
				System.out.println("User is found " + user);
				return user;
			}
			else {
				System.out.println("User is not found");
				return user;
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
