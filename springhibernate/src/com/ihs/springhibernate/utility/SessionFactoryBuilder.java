package com.ihs.springhibernate.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ihs.springhibernate.audittrailfields.AuditTrailFieldsInterceptor;

public class SessionFactoryBuilder
{
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory()
	{
		// Configuration configuration = new Configuration();
		// configuration.configure("hibernate.cfg.xml");
		/**
		 * Use this import for ServiceRegistry
		 * import org.hibernate.service.ServiceRegistry;
		 */
		// ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		// SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);


		if (sessionFactory == null)
		{
			sessionFactory = new Configuration().configure().setInterceptor(new AuditTrailFieldsInterceptor()).buildSessionFactory();
		}

		return sessionFactory;
	}
}
