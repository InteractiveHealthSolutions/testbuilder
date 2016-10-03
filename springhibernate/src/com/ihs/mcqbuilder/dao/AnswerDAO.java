package com.ihs.mcqbuilder.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.mcqbuilder.formwrapper.FormTakingTest;
import com.ihs.mcqbuilder.model.Answer;
import com.ihs.mcqbuilder.model.AnswerData;
import com.ihs.mcqbuilder.utility.SessionFactoryBuilder;

public class AnswerDAO
{
	static Logger log = Logger.getLogger(AnswerDAO.class);

	public enum By
	{
		ID,
		TESTID;
	}

	public enum FetchType
	{
		LAZY, EAGER;
	}

	public static List<Answer> getAllAnwer(FetchType fetchType)
	{
		List<Answer> answerList = null;
		Session session = null;

		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			// session.beginTransaction();

			String hql = "FROM Answer";
			Query query = session.createQuery(hql);

			answerList = (List<Answer>) query.list();

			if (answerList != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					for (Answer _answer : answerList)
					{
						Hibernate.initialize(_answer.getAnswerDataList());
					}
				}
			}

			// session.getTransaction().commit();

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

		return answerList;
	}

	public static Answer getAnswer(By by, String value, FetchType fetchType)
	{
		Answer answer = null;
		Boolean useInteger = false;

		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM Answer WHERE ";

			if (by.toString().equalsIgnoreCase(By.ID.toString()))
			{
				sql += "id= :_value";
				useInteger = true;
			}

			// else if (by.toString().equalsIgnoreCase(By.TESTID.toString()))
			// {
			// sql += "test_id= :_value";
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

			answer = (Answer) query.uniqueResult();

			if (answer != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					Hibernate.initialize(answer.getAnswerDataList());
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

		return answer;
	}

	public static List<Answer> getAnswerList(By by, String value, FetchType fetchType)
	{
		List<Answer> answer = null;
		Boolean useInteger = false;

		Session session = null;
		try
		{
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			String sql = "FROM Answer WHERE ";

			if (by.toString().equalsIgnoreCase(By.ID.toString()))
			{
				sql += "id= :_value";
				useInteger = true;
			}

			else if (by.toString().equalsIgnoreCase(By.TESTID.toString()))
			{
				sql += "test_id= :_value";
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

			answer = (List<Answer>) query.list();

			if (answer != null)
			{
				if (fetchType == FetchType.EAGER)
				{
					for (Answer _answer : answer)
					{
						Hibernate.initialize(_answer.getAnswerDataList());
					}
				}
			}
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

		return answer;
	}

	public static Integer save(Answer answer)
	{
		Session session = null;

		Integer newlySavedId = -1;

		try
		{
			if (answer.getAnswerDataList().size() > 0)
			{
				for (AnswerData answerData : answer.getAnswerDataList())
				{
					answerData.setAnswer(answer);
				}
			}

			session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();
			session.persist(answer);
			session.getTransaction().commit();
			newlySavedId = answer.getId();
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

	public static List<Integer> save(List<Answer> answerList)
	{
		Session session = null;

		List<Integer> newlySavedIdList = new ArrayList<Integer>();

		try
		{
			for (Answer answer : answerList)
			{
				for (AnswerData answerData : answer.getAnswerDataList())
				{
					answerData.setAnswer(answer);
				}
			}

			session = SessionFactoryBuilder.getSessionFactory().openSession();

			Transaction tx = session.beginTransaction();

			for (int i = 0; i < answerList.size(); i++)
			{
				session.save(answerList.get(i));

				if (i % 20 == 0)
				{
					// 20, same as the JDBC batch size
					// flush a batch of inserts and release memory:
					session.flush();
					session.clear();
				}
			}

			tx.commit();
			session.close();

			// newlySavedId = answer.getId();
		}

		catch (Exception ex)
		{
			Transaction tx = session.getTransaction();
			tx.rollback();
			ex.printStackTrace();

			log.error("Exception occured !", ex);
		}

		finally
		{
			if (session.isOpen())
			{
				session.close();
			}
		}

		return newlySavedIdList;
	}

	/**
	 * Removes null AnswerData from Answer
	 * 
	 * @param newAnswer
	 * @return
	 */
	public static Answer removeNullAnswerData(Answer newAnswer)
	{
		String emprtString = "";
		for (Iterator<AnswerData> itr = newAnswer.getAnswerDataList().iterator(); itr.hasNext();)
		{
			// if AnswerData is null or it is left empty then it should be removed
			String dataToBeChecked = itr.next().getData();
			if (dataToBeChecked == null || emprtString.equalsIgnoreCase(dataToBeChecked))
			{
				itr.remove();
			}
		}

		return newAnswer;
	}

	/**
	 * Removes null AnswerData from AnswerList of Exam
	 * 
	 * @param exam
	 * @return
	 */
	public static FormTakingTest removeNullAnswerDataFromExam(FormTakingTest exam)
	{
		for (int i = 0; i < exam.getQuestionAnswerList().size(); i++)
		{
			Answer cleanAnswer = AnswerDAO.removeNullAnswerData(exam.getQuestionAnswerList().get(i).getAnswer());
			exam.getQuestionAnswerList().get(i).setAnswer(cleanAnswer);
		}

		return exam;
	}
}
