package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.Rule;

import jakarta.persistence.TypedQuery;

public class RuleDao implements IRepository<Rule> {

	@Override
	public void create(Rule entity) {
		
		try {
			Session session = databaseConnectionHibernate();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Rule data is added to Database");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("A problem has oocured while adding address data");
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Rule deleteRule = find(id);
			if(deleteRule != null) {
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteRule);
				session.getTransaction().commit();

				System.out.println("Successfully deleted.");	
			}
			
		} catch (Exception e) {
		System.out.println("A problem has occured while deleting");
		e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Rule entity) {
		Session session = null;
		try {
			Rule updateRule = find(id);
			if(updateRule != null) {				
				updateRule.setCreatedOn(entity.getCreatedOn());
				updateRule.setDescription(entity.getDescription());
				updateRule.setNameRule(entity.getNameRule());
				updateRule.setRoles(entity.getRoles());
				
				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.merge(updateRule);
				session.getTransaction().commit();
				System.out.println("Successfully updated. Welldone.");
				
			}
			
		} catch (Exception e) {
			System.out.println("A problem has occured while updating");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void listAll() {
		
		Session session = databaseConnectionHibernate();
		String hql = "SELECT usr FROM Rule AS usr"; // usr * yerine all olarak getiriyor
		TypedQuery<Rule> typedQuery = session.createQuery(hql, Rule.class);
		List<Rule> ruleList = typedQuery.getResultList();
		
		for (Rule rule : ruleList) {
			System.out.println(rule);
		}
	}

	@Override
	public Rule find(long id) {
		
		Session session = databaseConnectionHibernate();
		Rule rule = new Rule();
		try {
			rule = session.find(Rule.class, id);
			
			if( rule != null) {
				System.out.println("Rule is found " + rule);
				return rule;
			}
			else {
				System.out.println("Rule is not found");
				return rule;
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
