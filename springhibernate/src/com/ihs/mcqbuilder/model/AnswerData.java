package com.ihs.mcqbuilder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ihs.mcqbuilder.audittrailfields.IAuditTrailFields;

@Entity
@Table(name = "answer_data")
public class AnswerData implements IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min=1)
	@Column(name = "data")
	private String data;	
	
	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;

	@NotNull
	@Column(name = "_index")
	private Integer index;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edited_ts")
	private Date lastEditedTS;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public void setAnswer(Answer answer)
	{
		this.answer = answer;
	}

	public Integer getIndex()
	{
		return index;
	}

	public void setIndex(Integer index)
	{
		this.index = index;
	}

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
