package com.bilgeadam.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.entity.Role;
import com.bilgeadam.entity.Rule;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;

public class HibernateSession {
	
	private static SessionFactory sessionFactory = sessionFactory();
	
	private static SessionFactory sessionFactory() { //build yaptığımız kısım
		
		Configuration config = new Configuration();	
		
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(UserDetail.class);
		config.addAnnotatedClass(Rule.class);
		config.addAnnotatedClass(Role.class);
		SessionFactory factory = config.configure("hibernate.cfg.xml").buildSessionFactory();
		
		return factory;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
