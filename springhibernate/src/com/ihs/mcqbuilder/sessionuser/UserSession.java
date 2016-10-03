package com.ihs.mcqbuilder.sessionuser;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.ihs.mcqbuilder.model.Role;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
// public class UserSession extends User implements IUserSession
public class UserSession implements IUserSession, java.io.Serializable
{

	/**
	 *
	 */

	/*
	 * Added to suppress warning
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;
	private String password;

	private Role role = new Role();

	private Date creationTS;

	private String login_Id;

	public String getLogin_Id()
	{
		return login_Id;
	}

	public void setLogin_Id(String login_Id)
	{
		this.login_Id = login_Id;
	}

	@Override
	public Date getCreationTS()
	{
		return this.creationTS;
	}

	@Override
	public Role getRole()
	{
		return role;
	}

	@Override
	public void setRole(Role role)
	{
		this.role = role;
	}

	@Override
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

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public void setPassword(String password)
	{
		this.password = password;
	}
}
