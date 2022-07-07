package com.bilgeadam.dao;

import org.hibernate.Session;

import com.bilgeadam.util.HibernateSession;

public interface IRepository <T> { //diğer dao lara implement edeceğimiz bir interface oluşturuyoruz
									// T generic i hangi sınıfta kullanılacaksa o değeri almak için yaratıldı
	public void create(T entity);
	
	public void delete(long id);
	
	public void update(long id, T entity);
	
	public void listAll();
	
	public T find(long id);
	
	default Session databaseConnectionHibernate() {//bu database connection bütün sınıflarda aynı olacak bu nedenle default olarak gövdeli tanımladık
		return HibernateSession.getSessionFactory().openSession(); 
		
	}
	
}
