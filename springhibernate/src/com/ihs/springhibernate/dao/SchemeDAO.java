package com.ihs.springhibernate.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
