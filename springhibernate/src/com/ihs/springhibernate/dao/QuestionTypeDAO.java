package com.ihs.springhibernate.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ihs.springhibernate.dao.RoleDAO.By;
import com.ihs.springhibernate.dao.RoleDAO.FetchType;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.Role;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class QuestionTypeDAO
{
	// public enum FetchType
	// {
	// LAZY, EAGER;
	// }

	public enum By
	{
		ID, TYPE_NAME
	}

	public static List<QuestionType> getAllTypes()
	{
		List<QuestionType> questionTypeList = null;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM QuestionType";
			Query query = session.createQuery(hql);
			questionTypeList = (List<QuestionType>) query.list();

			if (questionTypeList != null)
			{

			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return questionTypeList;
	}

	public static QuestionType getQuestionType(By by, String value)
	{
		QuestionType questionType = null;
		Boolean useInteger = false;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM QuestionType WHERE ";

			if (by.toString().equalsIgnoreCase(By.ID.toString()))
			{
				sql += "id= :_value";
				useInteger = true;
			}

			else if (by.toString().equalsIgnoreCase(By.TYPE_NAME.toString()))
			{
				sql += "type_name= :_value";
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

			questionType = (QuestionType) query.uniqueResult();

			//
			// if (role != null)
			// {
			// if (fetchType == FetchType.EAGER)
			// {
			// Hibernate.initialize(role.getPrivilegeList());
			// }
			// }

			session.getTransaction().commit();
			session.close();
		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return questionType;
	}
}
