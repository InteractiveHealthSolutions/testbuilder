package com.ihs.springhibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.springhibernate.model.SchemeCategory;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class SchemeCategoryDAO {
	static Logger log = Logger.getLogger(SchemeCategoryDAO.class);

	public static Integer saveSchemeCategory(
			List<SchemeCategory> schemeCategoryList) {

		Session session = null;
		Transaction transaction = null;
		Integer newlySavedId = -1;

		for (SchemeCategory schemeCategory : schemeCategoryList) {

			try {
				session = SessionFactoryBuilder.getSessionFactory()
						.openSession();
				transaction = session.beginTransaction();
				session.persist(schemeCategory);
				session.getTransaction().commit();
				newlySavedId = schemeCategory.getId();
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
		}

		return newlySavedId;
	}
	
	public static List<SchemeCategory> getSchemeCategory (Integer id)
	{
		List<SchemeCategory> schemeCategoryList = null;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM SchemeCategory WHERE scheme_id = :_value ";		
			Query query = session.createQuery(hql);
			query.setParameter("_value", id);
			
			schemeCategoryList = (List<SchemeCategory>) query.list();

			if (schemeCategoryList != null)
			{

			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return schemeCategoryList;
	}
	
	public static List<SchemeCategory> getAllSchemes ()
	{
		List<SchemeCategory> schemeCategoryList = null;

		try
		{
			Session session = SessionFactoryBuilder.getSessionFactory().openSession();

			session.beginTransaction();

			String hql = "FROM SchemeCategory";		
			Query query = session.createQuery(hql);
			
			schemeCategoryList = (List<SchemeCategory>) query.list();

			if (schemeCategoryList != null)
			{

			}

			session.getTransaction().commit();

			session.close();
		}

		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return schemeCategoryList;
	}
	
}
