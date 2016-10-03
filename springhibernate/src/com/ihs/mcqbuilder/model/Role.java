package com.ihs.mcqbuilder.model;

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
	}
}
