package com.ihs.mcqbuilder.sessioninterface;

import java.util.Date;

import com.ihs.mcqbuilder.model.Role;

public interface IUserSession 
{
	public Role getRole();

	public void setRole(Role role);

	public Date getCreationTS();

	public void setCreationTS(Date creationTS);

	public Integer getId();

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public String getPassword();

	public void setPassword(String password);

	public String getLogin_Id();

	public void setLogin_Id(String login_Id);	

}
