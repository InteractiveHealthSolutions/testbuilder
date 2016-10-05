package com.ihs.mcqbuilder.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ihs.mcqbuilder.model.Scheme;
import com.ihs.mcqbuilder.utility.SessionFactoryBuilder;

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

public static List<Scheme> getSchemeById (Integer id)
{
	List<Scheme> schemeList = null;

	try
	{
		Session session = SessionFactoryBuilder.getSessionFactory().openSession();

		session.beginTransaction();

		String hql = "FROM Scheme WHERE id = :_value ";		
		Query query = session.createQuery(hql);
		query.setParameter("_value", id);
		
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

public static List<Scheme> getAllSchemes()
{
	List<Scheme> schemeList = null;

	try
	{
		Session session = SessionFactoryBuilder.getSessionFactory().openSession();

		session.beginTransaction();

		String hql = "FROM Scheme";
		Query query = session.createQuery(hql);
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

public static void deleteSchemeById(Integer id) {
	try {
		Session session = SessionFactoryBuilder.getSessionFactory()
				.openSession();

		session.beginTransaction();

		String hql = "delete from Scheme WHERE id = :_value";
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

public static void deleteSchemeCategoryById(Integer id) {
	try {
		Session session = SessionFactoryBuilder.getSessionFactory()
				.openSession();

		session.beginTransaction();

		String hql = "delete from SchemeCategory WHERE scheme_id = :_value";
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

public static Long countSchemeById(Integer id) {
	
	Long result = null;
	try {
		Session session = SessionFactoryBuilder.getSessionFactory()
				.openSession();

		session.beginTransaction();

		String hql = "select count(*) from Test WHERE scheme_id = :_value";
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