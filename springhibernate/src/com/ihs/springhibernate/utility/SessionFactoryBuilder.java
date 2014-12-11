package com.ihs.springhibernate.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ihs.springhibernate.audittrailfields.AuditTrailFieldsInterceptor;

public class SessionFactoryBuilder
{
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			sessionFactory = new Configuration().configure().setInterceptor(new AuditTrailFieldsInterceptor()).buildSessionFactory();
		}

		return sessionFactory;
	}
}
