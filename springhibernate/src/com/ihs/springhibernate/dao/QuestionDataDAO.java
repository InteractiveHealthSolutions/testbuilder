package com.ihs.springhibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class QuestionDataDAO
{
	public enum By
	{
		ID
	}

	public enum FetchType
	{
		LAZY, EAGER;
	}

	public static QuestionData getQuestionData(By by, String value, FetchType fetchType)
	{
		QuestionData questionData = null;
		Boolean useInteger = false;

		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM QuestionData WHERE ";

			if (by.toString().equalsIgnoreCase(By.ID.toString()))
			{
				sql += "id= :_value";
				useInteger = true;
			}

			// else if (by.toString().equalsIgnoreCase(By.TYPE_NAME.toString()))
			// {
			// sql += "type_name= :_value";
			// }

			Query query = session.createQuery(sql);

			if (useInteger)
			{
				query.setParameter("_value", Integer.valueOf(value));
			}

			else
			{
				query.setParameter("_value", value.toLowerCase());
			}

			questionData = (QuestionData) query.uniqueResult();

			// if (questionData != null)
			// {
			// if (fetchType == FetchType.EAGER)
			// {
			// Hibernate.initialize(questionData.getQuestionDataList());
			// }
			// }

			session.getTransaction().commit();

		}

		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		finally
		{
			if (session.isOpen())
			{
				session.close();
			}
		}

		return questionData;
	}
}
