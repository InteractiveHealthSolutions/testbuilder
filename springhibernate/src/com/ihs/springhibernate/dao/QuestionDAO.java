package com.ihs.springhibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.springhibernate.dao.UserDAO.FetchType;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class QuestionDAO
{
	public enum By
	{
		ID
	}

	public enum FetchType
	{
		LAZY, EAGER;
	}

	public static Boolean update(Question question)
	{
		Boolean success = false;

		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			Question savedQuestion = (Question) session.get(Question.class, question.getId());

			List<QuestionData> deleteQuestionDataList = new ArrayList<QuestionData>();

			// / getting list of deleting questionData
			for (QuestionData _questionData : savedQuestion.getQuestionDataList())
			{
				if (question.getQuestionDataList().contains(_questionData) == false)
				{
					deleteQuestionDataList.add(_questionData);
				}
			}

			for (QuestionData questionData : question.getQuestionDataList())
			{
				questionData.setQuestion(question);
			}

			session.close();

			session = SessionFactoryBuilder.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(question);
			tx.commit();
			session.close();


			// deleting related questionData
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			for (QuestionData deletingQuestionData : deleteQuestionDataList)
			{
				session.delete(deletingQuestionData);
			}

			session.getTransaction().commit();

			success = true;
		}

		catch (Exception ex)
		{
			Transaction tx = session.getTransaction();
			tx.rollback();
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

	public static Integer save(Question question)
	{
		//Boolean success = false;
		Session session = null;
		
		Integer newlySavedId = -1;

		try
		{
			if (question.getQuestionDataList().size() > 0)
			{
				for (QuestionData questionData : question.getQuestionDataList())
				{
					questionData.setQuestion(question);
				}
			}

			session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();
			session.persist(question);
			session.getTransaction().commit();
			newlySavedId = question.getId();
		}

		catch (Exception ex)
		{
			Transaction tx = session.getTransaction();
			tx.rollback();
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

	public static List<Question> getAllQuestion(FetchType fetchType)
	{
		List<Question> questionList = null;
		Session session = null;

		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			//session.beginTransaction();

			String hql = "FROM Question";
			Query query = session.createQuery(hql);

			questionList = (List<Question>) query.list();

			if (questionList != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					for (Question _question : questionList)
					{
						Hibernate.initialize(_question.getQuestionDataList());
					}
				}
			}

			//session.getTransaction().commit();
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

		return questionList;

	}

	public static Question getQuestion(By by, String value, FetchType fetchType)
	{
		Question question = null;
		Boolean useInteger = false;

		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM Question WHERE ";

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

			question = (Question) query.uniqueResult();

			if (question != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					Hibernate.initialize(question.getQuestionDataList());
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

		return question;
	}

	public static Question removeNullQuestionData(Question newQuestion)
	{
		for (Iterator<QuestionData> itr = newQuestion.getQuestionDataList().iterator(); itr.hasNext();)
		{
			if (itr.next().getData() == null)
			{
				itr.remove();
			}
		}

		return newQuestion;
	}
}
