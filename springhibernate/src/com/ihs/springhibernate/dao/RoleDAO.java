package com.ihs.springhibernate.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ihs.springhibernate.model.Role;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class RoleDAO
{
	public enum FetchType
	{
		LAZY, EAGER;
	}

	public enum By
	{
		ID, ROLE_NAME
	}

	public static Boolean save(Role role)
	{
		Boolean success = false;
		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();
			session.save(role);
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

	public static List<Role> getAllRole(FetchType fetchType)
	{
		List<Role> roleList = null;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM Role";
			Query query = session.createQuery(hql);
			roleList = (List<Role>) query.list();

			if (roleList != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					for (Role _role : roleList)
					{
						Hibernate.initialize(_role.getPrivilegeList());
					}
				}
			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return roleList;
	}

	public static Role getRole(By by, String value, FetchType fetchType)
	{
		Role role = null;
		Boolean useInteger = false;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM Role WHERE ";

			if (by.toString().equalsIgnoreCase(By.ID.toString()))
			{
				sql += "id= :_value";
				useInteger = true;
			}

			else if (by.toString().equalsIgnoreCase(By.ROLE_NAME.toString()))
			{
				sql += "role_name= :_value";
			}

			Query query = session.createQuery(sql);

			if (useInteger)
			{
				query.setParameter("_value", Integer.valueOf(value));
			}

			else
			{
				query.setParameter("_value", value.toLowerCase());
			}

			role = (Role) query.uniqueResult();

			if (role != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					Hibernate.initialize(role.getPrivilegeList());
				}
			}

			session.getTransaction().commit();
			session.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return role;
	}
}
