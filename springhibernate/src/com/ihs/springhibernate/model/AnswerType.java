package com.ihs.springhibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer_type")
public class AnswerType
{

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "type_name")
	private String typeName;

	@Column(name = "description")
	private String description;

	@Column(name = "creation_ts")
	private Date creationTS = new Date();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getCreationTS()
	{
		return creationTS;
	}

	public void setCreationTS(Date creationTS)
	{
		this.creationTS = creationTS;
	}

}
