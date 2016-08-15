package com.ihs.springhibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class CategoryTypeDAO {

	static Logger log = Logger.getLogger(CategoryTypeDAO.class);

	public static List<CategoryType> getCategoryTypes() {
		List<CategoryType> categoryType = null;

		try {
			Session session = SessionFactoryBuilder.getSessionFactory()
					.openSession();

			session.beginTransaction();

			String hql = "FROM CategoryType";
			Query query = session.createQuery(hql);
			categoryType = (List<CategoryType>) query.list();

			if (categoryType != null) {

			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}

		return categoryType;
	}

	public static Integer saveCategory(CategoryType category) {

		Session session = null;
		Transaction transaction = null;
		Integer newlySavedId = -1;

		try {
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.persist(category);
			session.getTransaction().commit();
			newlySavedId = category.getId();
		}

		catch (Exception ex) {
			try {
				log.error("Exception occured !", ex);
				transaction.rollback();
			} catch (Exception eAny) {
				eAny.printStackTrace();
			}

			ex.printStackTrace();
		}

		finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return newlySavedId;
	}

	public static void deleteCategoryById(Integer id) {
		try {
			Session session = SessionFactoryBuilder.getSessionFactory()
					.openSession();

			session.beginTransaction();

			String hql = "delete from CategoryType WHERE id = :_value";
			Query query = session.createQuery(hql);
			query.setParameter("_value", id);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static Long countCategoryById(Integer id) {
		
		Long result = null;
		try {
			Session session = SessionFactoryBuilder.getSessionFactory()
					.openSession();

			session.beginTransaction();

			String hql = "select count(*) from Question WHERE category_id = :_value";
			Query query = session.createQuery(hql);
			query.setParameter("_value", id);
			result = (Long)query.uniqueResult();
			session.getTransaction().commit();
			session.close();
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return result;
	}
	
	
}
