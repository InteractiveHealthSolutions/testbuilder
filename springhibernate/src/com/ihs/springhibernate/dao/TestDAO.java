package com.ihs.springhibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class TestDAO
{
	// static Logger log = Logger.getLogger(TestDAO.class.getName());

	public enum By
	{
		ID
	}

	public enum FetchType
	{
		LAZY, EAGER;
	}

	public static Integer save(Test test)
	{
		Session session = null;
		Transaction transaction = null;

		Integer newlySavedId = -1;
		try
		{
			if (test.getQuestionList().size() > 0)
			{
				// for (QuestionData questionData : test.getQuestionList())
				// {
				// questionData.setQuestion(test);
				// }
			}

			session = SessionFactoryBuilder.getSessionFactory().openSession();

			transaction = session.beginTransaction();
			session.persist(test);
			session.getTransaction().commit();
			newlySavedId = test.getId();
		}

		catch (Exception ex)
		{
			try
			{
				// log.error("Exception occured !", ex);
				transaction.rollback();
			}
			catch (Exception eAny)
			{
				eAny.printStackTrace();
			}

			ex.printStackTrace();
		}

		finally
		{
			if (session.isOpen())
			{
				session.close();
			}
		}

		return newlySavedId;
	}

	public static Boolean update(Test test)
	{
		Boolean success = false;
		Session session = null;
		Transaction transaction = null;

		try
		{
			// session = SessionFactoryBuilder.getSessionFactory().openSession();
			//
			// Test savedTest = (Test) session.get(Test.class, test.getId());
			//
			// List<Question> deleteQuestionList = new ArrayList<Question>();
			//
			// // / getting list of deleting questionData
			// for (Question _question : savedTest.getQuestionList())
			// {
			// if (test.getQuestionList().contains(_question) == false)
			// {
			// deleteQuestionList.add(_question);
			// }
			// }
			//
			// for (QuestionData questionData : test.getQuestionDataList())
			// {
			// questionData.setQuestion(test);
			// }
			//
			// session.close();

			session = SessionFactoryBuilder.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(test);
			transaction.commit();
			session.close();

			// // deleting related questionData
			// session = SessionFactoryBuilder.getSessionFactory().openSession();
			//
			// session.beginTransaction();
			//
			// for (QuestionData deletingQuestionData : deleteQuestionList)
			// {
			// session.delete(deletingQuestionData);
			// }
			//
			// session.getTransaction().commit();

			success = true;
		}

		catch (Exception ex)
		{
			try
			{
				transaction.rollback();
			}
			catch (Exception eAny)
			{
				eAny.printStackTrace();
			}

			ex.printStackTrace();
		}

		finally
		{
			if (session.isOpen())
			{
				session.close();
			}
		}

		return success;
	}

	public static Test getTest(By by, String value, FetchType fetchType)
	{
		Test test = null;
		Boolean useInteger = false;

		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM Test WHERE ";

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

			test = (Test) query.uniqueResult();

			if (test != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					Hibernate.initialize(test.getQuestionList());
				}
			}

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

		return test;
	}

	public static List<Test> getAllTest(FetchType fetchType)
	{
		List<Test> testList = null;
		Session session = null;

		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM Test";
			Query query = session.createQuery(hql);

			testList = (List<Test>) query.list();

			if (testList != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					for (Test _test : testList)
					{
						Hibernate.initialize(_test.getQuestionList());
					}
				}
			}

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

		return testList;
	}
}
