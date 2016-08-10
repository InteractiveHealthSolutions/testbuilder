package com.ihs.springhibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ihs.springhibernate.model.AnswerType;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class AnswerTypeDAO
{
	public enum By
	{
		ID, TYPE_NAME
	}

	public static List<AnswerType> getAllTypes()
	{
		List<AnswerType> answerTypeList = null;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM AnswerType WHERE";
			Query query = session.createQuery(hql);
			answerTypeList = (List<AnswerType>) query.list();

			if (answerTypeList != null)
			{

			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return answerTypeList;
	}

	public static AnswerType getAnswerType(By by, String value)
	{
		AnswerType answerType = null;
		Boolean useInteger = false;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM AnswerType WHERE ";

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

			answerType = (AnswerType) query.uniqueResult();

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

		return answerType;
	}

}
