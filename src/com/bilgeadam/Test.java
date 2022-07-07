package com.bilgeadam;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
import java.util.ArrayList;


import com.bilgeadam.dao.RoleDao;
import com.bilgeadam.dao.RuleDao;
import com.bilgeadam.dao.UserDao;
import com.bilgeadam.dao.UserDetailDao;
import com.bilgeadam.entity.Gender;
import com.bilgeadam.entity.Role;
import com.bilgeadam.entity.Rule;
import com.bilgeadam.entity.User;
import com.bilgeadam.entity.UserDetail;


public class Test {

	public static void main(String[] args) {
		
		Rule rule1 = new Rule();
		rule1.setNameRule("LOGIN");
		rule1.setDescription("You should enter your password");
		ArrayList<Rule> ruleList1 = new ArrayList<>();
		ruleList1.add(rule1);
		
		Rule rule2 = new Rule();
		rule2.setNameRule("EXIT");
		rule2.setDescription("You can go if you finished your education");
		ArrayList<Rule> ruleList2 = new ArrayList<>();
		ruleList2.add(rule2);
		// ----------------------------  //
		
		Role role1 = new Role();
		role1.setRoleName("Teacher");
		role1.setDescription("Master of the cat will tell you how you can be good student");
		ArrayList<Role> roleList1 = new ArrayList<>();
		roleList1.add(role1);
		ArrayList<User> userList1 = new ArrayList<>();
		
		Role role2 = new Role();
		role2.setRoleName("Student");
		role2.setDescription("Learns");
		ArrayList<Role> roleList2 = new ArrayList<>();
		roleList2.add(role2);
		ArrayList<User> userList2 = new ArrayList<>();
		// ----------------------------  //
		
		UserDetail userDetail1 = new UserDetail();
		userDetail1.setFirstName("Sütlaç");
		userDetail1.setLastName("Saygılı");
		userDetail1.setBio("Cat Master");
		userDetail1.setGender(Gender.MAN);
//		try(FileInputStream fis1 = new FileInputStream(new File("D:\\javalib\\Hibernate Practice2\\resource\\profile.png")))
//		{
//			userDetail1.setPicture(IOUtils.toByteArray(fis1));
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
		
		UserDetail userDetail2 = new UserDetail();
		userDetail2.setFirstName("Boncuk");
		userDetail2.setLastName("Saygılı");
		userDetail2.setBio("Cat School Student");
		userDetail2.setGender(Gender.OTHER);
//		try(FileInputStream fis2 = new FileInputStream(new File("D:\\javalib\\Hibernate Practice2\\resource\\profile.png")))
//		{
//			userDetail2.setPicture(IOUtils.toByteArray(fis2));
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
		// ----------------------------  //
		
		User user1 = new User();
		user1.setEmail("boncukcum@gmail.com");
		user1.setPassword("123");
		user1.setRole(role1);
		user1.setUserDetail(userDetail1);
		
		User user2 = new User();
		user2.setEmail("sutlaccim@gmail.com");
		user2.setPassword("321");
		user2.setRole(role2);
		user2.setUserDetail(userDetail2);
		// ----------------------------  //
		
		userDetail1.setUser(user1);
		userDetail2.setUser(user2);
		userList1.add(user1);
		userList2.add(user2);
		// ----------------------------  //
		
		RoleDao roleDao = new RoleDao();
		RuleDao ruleDao = new RuleDao();
		UserDao userDao = new UserDao();
		UserDetailDao userDetailDao = new UserDetailDao();
		
		roleDao.create(role1);
		roleDao.create(role2);
		
		ruleDao.create(rule1);
		ruleDao.create(rule2);
		
		userDao.create(user1);
		userDao.create(user2);
		
		userDetailDao.create(userDetail1);
		userDetailDao.create(userDetail2);
	}

}

