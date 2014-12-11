package com.ihs.springhibernate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ihs.springhibernate.sessioninterface.IUserSession;

@Entity
@Table(name = "role")
public class Role implements java.io.Serializable
{
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "role_name")
	private String roleName;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_ts")
	private Date creationTS = new Date();

	@OneToMany
	@JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	private List<Privilege> privilegeList = new ArrayList<Privilege>();

	public List<Privilege> getPrivilegeList()
	{
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList)
	{
		this.privilegeList = privilegeList;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
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
		
		// this.creationTS.set(Calendar.YEAR, creationTS.get(Calendar.YEAR));
		// this.creationTS.set(Calendar.MONTH, creationTS.get(Calendar.MONTH));
		// this.creationTS.set(Calendar.DAY_OF_YEAR, creationTS.get(Calendar.DAY_OF_YEAR));
		//
		// this.creationTS.set(Calendar.HOUR_OF_DAY, creationTS.get(Calendar.HOUR_OF_DAY));
		// this.creationTS.set(Calendar.MINUTE, creationTS.get(Calendar.MINUTE));
		// this.creationTS.set(Calendar.SECOND, creationTS.get(Calendar.SECOND));
		//
		// this.creationTS.get(Calendar.YEAR);
	}
}
