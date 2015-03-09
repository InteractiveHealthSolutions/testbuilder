package com.ihs.springhibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ihs.springhibernate.audittrailfields.IAuditTrailFields;

@Entity
@Table(name = "answer")
public class Answer implements IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "question_id")
	private Question question = new Question();
	
	@Size(min=1)
	@Column(name = "data")
	private String data;
	
	@Size(min=1)
	@Column(name = "checked")
	private String checked;
	
	@NotNull
	@Column(name = "marks_obtained")
	private Integer marksObtained;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edited_ts")
	private Date lastEditedTS;
	
	@Override
	public Date getCreationTS()
	{
		return creationTS;
	}

	@Override
	public void setCreationTS(Date creationTS)
	{
		this.creationTS = creationTS;
	}

	@Override
	public Date getLastEditedTS()
	{
		return lastEditedTS;
	}

	@Override
	public void setLastEditedTS(Date lastEditedTS)
	{
		this.lastEditedTS = lastEditedTS;
	}

}
