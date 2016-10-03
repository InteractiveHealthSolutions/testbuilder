package com.ihs.mcqbuilder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.mcqbuilder.model.Question;
import com.ihs.mcqbuilder.model.QuestionData;
import com.ihs.mcqbuilder.model.TestQuestion;
import com.ihs.mcqbuilder.utility.SessionFactoryBuilder;

public class QuestionDAO {
	public enum By {
		ID
	}

	public enum FetchType {
		LAZY, EAGER;
	}

	public static Boolean update(Question question) {
		Boolean success = false;

		Session session = null;
		try {
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			Question savedQuestion = (Question) session.get(Question.class,
					question.getId());

			List<QuestionData> deleteQuestionDataList = new ArrayList<QuestionData>();

			// / getting list of deleting questionData
			for (QuestionData _questionData : savedQuestion
					.getQuestionDataList()) {
				if (question.getQuestionDataList().contains(_questionData) == false) {
					deleteQuestionDataList.add(_questionData);
				}
			}

			for (QuestionData questionData : question.getQuestionDataList()) {
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

			for (QuestionData deletingQuestionData : deleteQuestionDataList) {
				session.delete(deletingQuestionData);
			}

			session.getTransaction().commit();

			success = true;
		}

		catch (Exception ex) {
			Transaction tx = session.getTransaction();
			tx.rollback();
			ex.printStackTrace();
		}

		finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return success;
	}

	public static Integer save(Question question) {
		// Boolean success = false;
		Session session = null;

		Integer newlySavedId = -1;

		try {
			if (question.getQuestionDataList().size() > 0) {
				for (QuestionData questionData : question.getQuestionDataList()) {
					questionData.setQuestion(question);
				}
			}

			session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();
			session.persist(question);
			session.getTransaction().commit();
			newlySavedId = question.getId();
		}

		catch (Exception ex) {
			Transaction tx = session.getTransaction();
			tx.rollback();
			ex.printStackTrace();
		}

		finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return newlySavedId;
	}

	public static List<Question> getAllQuestion(FetchType fetchType) {
		List<Question> questionList = null;
		Session session = null;

		try {
			session = SessionFactoryBuilder.getSessionFactory().openSession();

			// session.beginTransaction();

			String hql = "FROM Question";
			Query query = session.createQuery(hql);

			questionList = (List<Question>) query.list();

			if (questionList != null) {
				if (fetchType == FetchType.EAGER) {
					for (Question _question : questionList) {
						Hibernate.initialize(_question.getQuestionDataList());
					}
				}
			}

			// session.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return questionList;

	}
	
	
	

	public static Question getQuestion(By by, String value, FetchType fetchType) {
		Question question = null;
		Boolean useInteger = false;

		Session session = null;
		try {
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM Question WHERE ";

			if (by.toString().equalsIgnoreCase(By.ID.toString())) {
				sql += "id= :_value";
				useInteger = true;
			}

			// else if (by.toString().equalsIgnoreCase(By.TYPE_NAME.toString()))
			// {
			// sql += "type_name= :_value";
			// }

			Query query = session.createQuery(sql);

			if (useInteger) {
				query.setParameter("_value", Integer.valueOf(value));
			}

			else {
				query.setParameter("_value", value.toLowerCase());
			}

			question = (Question) query.uniqueResult();

			if (question != null) {
				if (fetchType == FetchType.EAGER) {
					Hibernate.initialize(question.getQuestionDataList());
				}
			}

			session.getTransaction().commit();

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return question;
	}

	public static Question removeNullQuestionData(Question newQuestion) {
		for (Iterator<QuestionData> itr = newQuestion.getQuestionDataList()
				.iterator(); itr.hasNext();) {
			if (itr.next().getData() == null) {
				itr.remove();
			}
		}

		return newQuestion;
	}

	public static List<List<Question>> getQuestionForTest(
			HashMap<Integer, Integer> questionIdentifier) {

		int categoryType = 0;
		int numberOfQuestion = 0;
		List<Question> selectedQuestionList = new ArrayList<Question>();
		List<List<Question>> finalQuestionList = new ArrayList<List<Question>>();

		for (Integer catId : questionIdentifier.keySet()) {
			categoryType = catId;
			numberOfQuestion = questionIdentifier.get(categoryType);

			try {
				Session session = SessionFactoryBuilder.getSessionFactory()
						.openSession();

				session.beginTransaction();

				String hql = "FROM Question WHERE question_type_id = :_value AND category_id = :_value1 order by rand()";
				Query query = session.createQuery(hql);
				query.setParameter("_value", 3);
				query.setParameter("_value1", categoryType);
				query.setFirstResult(0);
				query.setMaxResults(numberOfQuestion);

				selectedQuestionList = (List<Question>) query.list();

				if (selectedQuestionList != null) {

				}

				session.getTransaction().commit();
				
				if(selectedQuestionList.size() == numberOfQuestion){
					finalQuestionList.add(selectedQuestionList);
				}
				
				else {
					
				}
				session.close();
			}

			catch (Exception exc) {
				exc.printStackTrace();
			}
		}

		return finalQuestionList;
	}
	
	public static List<TestQuestion> getQuestionForTest(Integer testId){
		List<TestQuestion> questionList = new ArrayList<TestQuestion>();
		
		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM TestQuestion WHERE test_id = :_value ";		
			Query query = session.createQuery(hql);
			query.setParameter("_value", testId);
			
			questionList = (List<TestQuestion>) query.list();

			if (questionList != null)
			{

			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return questionList;
	}
}
