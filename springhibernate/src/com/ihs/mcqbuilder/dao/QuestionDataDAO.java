package com.ihs.mcqbuilder.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ihs.mcqbuilder.model.QuestionData;
import com.ihs.mcqbuilder.utility.SessionFactoryBuilder;

public class QuestionDataDAO {
	public enum By {
		ID
	}

	public enum FetchType {
		LAZY, EAGER;
	}

	public static QuestionData getQuestionData(By by, String value,
			FetchType fetchType) {
		QuestionData questionData = null;
		Boolean useInteger = false;

		Session session = null;
		try {
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			session.beginTransaction();

			String sql = "FROM QuestionData WHERE ";

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

		catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return questionData;
	}

	/*public static List<QuestionData> getQuestionDataById(
			List<TestQuestion> questionList) {
		List<QuestionData> questionDataList = new ArrayList<QuestionData>();
		QuestionData questionData = new QuestionData();

		try {
			for (int i=0 ; i < questionList.size(); i++) {
				Session session = SessionFactoryBuilder.getSessionFactory()
						.openSession();

				session.beginTransaction();

				String hql = "FROM QuestionData WHERE question_id = :_value ";
				Query query = session.createQuery(hql);
				query.setParameter("_value", questionList.get(i).getQuestion_id());
				questionData = (QuestionData) query.uniqueResult();
				questionDataList.add(questionData);
				
				//if (questionList != null) {

			//	}

				session.getTransaction().commit();

				session.close();
			}
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

		return questionDataList;
	}*/
}
