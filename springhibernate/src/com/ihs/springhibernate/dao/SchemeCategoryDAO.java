package com.ihs.springhibernate.dao;

import java.util.List;

import org.apache.log4j.Logger;
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
}
