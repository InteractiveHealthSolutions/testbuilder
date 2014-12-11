package com.ihs.springhibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "privilege")
public class Privilege implements java.io.Serializable
{
	@Id
	@GeneratedValue
	private Integer id;

	private String right;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
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

	public String getRight()
	{
		return right;
	}

	public void setRight(String right)
	{
		this.right = right;
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

	@Override
	public boolean equals(Object v)
	{
		boolean retVal = false;

		if (v instanceof Privilege)
		{
			Privilege privilege = (Privilege) v;
			retVal = privilege.id == this.id;
		}

		return retVal;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

}
