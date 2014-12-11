package com.ihs.springhibernate.audittrailfields;

import java.util.Date;

public interface IAuditTrailFields
{
	// public Date getCreatedDate();
	//
	// public void setCreatedDate(Date createdDate);
	//
	// public Date getLastUpdated();
	//
	// public void setLastUpdated(Date lastUpdatedDate);

	public Date getCreationTS();


	public void setCreationTS(Date creationTS);


	public Date getLastEditedTS();

	public void setLastEditedTS(Date lastEditedTS);


}
