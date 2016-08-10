package com.ihs.springhibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ihs.springhibernate.model.Privilege;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class UserDAO
{
	static Logger log = Logger.getLogger(UserDAO.class);

	public enum By
	{
		LoginId, Id
	}

	public static Boolean update(User user)
	{
		Boolean success = false;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
			session.close();
			success = true;
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return success;
	}

	public static Integer save(User user)
	{
		Integer newlySavedId = -1;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();
			session.persist(user);
			session.getTransaction().commit();
			session.close();
			newlySavedId = user.getId();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return newlySavedId;
	}

	// public static Integer save(User user)
	// {
	// Integer newlySavedId = -1;
	//
	// try
	// {
	// Session session = SessionFactoryBuilder.getSessionFactory().openSession();
	//
	// session.beginTransaction();
	// session.save(user);
	// session.getTransaction().commit();
	// session.close();
	// newlySavedId = user.getId();
	// }
	//
	// catch (Exception ex)
	// {
	// ex.printStackTrace();
	// }
	//
	// return newlySavedId;
	// }

	public static List<User> getAllUser(FetchType fetchType)
	{
		List<User> userList = null;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM User";
			Query query = session.createQuery(hql);

			userList = (List<User>) query.list();

			if (userList != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					for (User _user : userList)
					{
						Hibernate.initialize(_user.getRole().getPrivilegeList());
					}
				}
			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return userList;
	}

	public static Boolean authenticate(User user)
	{
		Boolean isAuthentic = false;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "SELECT * FROM user WHERE login_id= :_login_id AND password= :_password";
			Query query = session.createSQLQuery(sql);
			query.setParameter("_login_id", user.getLogin_Id());
			query.setParameter("_password", user.getPassword());

			if (query.uniqueResult() != null)
			{
				isAuthentic = true;

				log.info("UserName: " + user.getName() + "logged in.");
				log.info("UserId: " + user.getId() + "logged in");

			}

			session.getTransaction().commit();
			session.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return isAuthentic;
	}

	public static Boolean hasPrivilegeFor(IUserSession user, Privileges privilege)
	{
		Boolean hasPrivilege = false;
		try
		{
			Privilege _privilege = new Privilege();

			if (privilege == Privileges.ADMIN)
			{
				_privilege.setId(Privileges.ADMIN.getRoleId());
			}

			else if (privilege == Privileges.TEST_MAKER)
			{
				_privilege.setId(Privileges.TEST_MAKER.getRoleId());
			}

			else if (privilege == Privileges.TEST_TAKER)
			{
				_privilege.setId(Privileges.TEST_TAKER.getRoleId());
			}

			else if (privilege == Privileges.TEST_CHECKER)
			{
				_privilege.setId(Privileges.TEST_CHECKER.getRoleId());
			}

			if (user.getRole().getPrivilegeList().contains(_privilege))
			{
				hasPrivilege = true;
			}
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return hasPrivilege;
	}

	public static Boolean hasPrivilegeFor(User user, Privileges privilege)
	{
		Boolean hasPrivilege = false;
		try
		{
			Privilege _privilege = new Privilege();

			if (privilege == Privileges.ADMIN)
			{
				_privilege.setId(Privileges.ADMIN.getRoleId());
			}

			else if (privilege == Privileges.TEST_MAKER)
			{
				_privilege.setId(Privileges.TEST_MAKER.getRoleId());
			}

			else if (privilege == Privileges.TEST_CHECKER)
			{
				_privilege.setId(Privileges.TEST_CHECKER.getRoleId());
			}

			else if (privilege == Privileges.TEST_TAKER)
			{
				_privilege.setId(Privileges.TEST_TAKER.getRoleId());
			}

			if (user.getRole().getPrivilegeList().contains(_privilege))
			{
				hasPrivilege = true;
			}
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return hasPrivilege;
	}

	public static User getUser(By by, String value, FetchType fetchType)
	{
		User user = null;

		Boolean useInt = false;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM User WHERE ";

			if (by.toString().equalsIgnoreCase(By.Id.toString()))
			{
				useInt = true;
				sql += "id= :_value";
			}

			else if (by.toString().equalsIgnoreCase(By.LoginId.toString()))
			{
				sql += "login_id= :_value";
			}

			Query query = session.createQuery(sql);

			if (useInt)
			{
				query.setParameter("_value", Integer.valueOf(value));
			}

			else
			{
				query.setParameter("_value", value);
			}

			user = (User) query.uniqueResult();

			if (user != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					Hibernate.initialize(user.getRole().getPrivilegeList());
				}
			}

			session.getTransaction().commit();
			session.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return user;
	}

	public enum FetchType
	{
		LAZY, EAGER;
	}
}
