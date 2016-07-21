package com.ihs.springhibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.utility.SessionFactoryBuilder;

public class SchemeDAO {
	static Logger log = Logger.getLogger(SchemeDAO.class);
public static Integer saveScheme(Scheme scheme){
		
		Session session = null;
		Transaction transaction = null;
		Integer newlySavedId = -1;
		
		try {
			session = SessionFactoryBuilder.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.persist(scheme);
			session.getTransaction().commit();
			newlySavedId = scheme.getId();
		}
		
		catch (Exception ex)
		{
			try
			{
				log.error("Exception occured !", ex);
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

public static List<Scheme> getSchemeData (String name)
{
	List<Scheme> schemeList = null;

	try
	{
		Session session = SessionFactoryBuilder.getSessionFactory().openSession();

		session.beginTransaction();

		String hql = "FROM Scheme WHERE name = :_value ";		
		Query query = session.createQuery(hql);
		query.setParameter("_value", name);
		
		schemeList = (List<Scheme>) query.list();

		if (schemeList != null)
		{

		}

		session.getTransaction().commit();

		session.close();
	}

	catch (Exception exc)
	{
		exc.printStackTrace();
	}

	return schemeList;
}
}
