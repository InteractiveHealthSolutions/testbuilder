package com.ihs.mcqbuilder.audittrailfields;

import java.util.Date;

public interface IAuditTrailFields 
{
	public Date getCreationTS();
	public void setCreationTS(Date creationTS);

	public Date getLastEditedTS();
	public void setLastEditedTS(Date lastEditedTS);
}
