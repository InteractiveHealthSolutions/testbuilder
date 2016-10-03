package com.ihs.mcqbuilder.audittrailfields;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AuditTrailFieldsInterceptor extends EmptyInterceptor
{	
	// It is called  before update operation of Hibernate
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames, Type[] types)
	{
		if (entity instanceof IAuditTrailFields)
		{			
//			int indexOfEditorId = ArrayUtils.indexOf(propertyNames, "lastEditorId");
//			currentState[indexOfEditorId] = userSession.getId();
			
			int indexOfEditingTS = ArrayUtils.indexOf(propertyNames, "lastEditedTS");
			currentState[indexOfEditingTS] = new Date();
			
			return true;
		}
		
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types)
	{
		if (entity instanceof IAuditTrailFields)
		{			
//			int indexOfCreatorId = ArrayUtils.indexOf(propertyNames, "creatorId");
//			state[indexOfCreatorId] = userSession.getId();			
			
			int indexOfCreationTS = ArrayUtils.indexOf(propertyNames, "creationTS");
			state[indexOfCreationTS] = new Date();
			
			return true;
		}
		
		return false;
	}

	//
	// public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
	// Object[] previousState, String[] propertyNames,
	// Type[] types) {
	// setValue(currentState, propertyNames, "updatedBy", UserUtils.getCurrentUsername())
	// setValue(currentState, propertyNames, "updatedOn", new Date())
	// true
	// }
	//
	// public boolean onSave(Object entity, Serializable id, Object[] state,
	// String[] propertyNames, Type[] types) {
	// setValue(state, propertyNames, "createdBy", UserUtils.getCurrentUsername());
	// setValue(state, propertyNames, "createdOn", new Date());
	// return true;
	// }
	//
	// private void setValue(Object[] currentState, String[] propertyNames,
	// String propertyToSet, Object value) {
	// def index = propertyNames.toList().indexOf(propertyToSet)
	// if (index >= 0) {
	// currentState[index] = value
	// }
	// }


}
