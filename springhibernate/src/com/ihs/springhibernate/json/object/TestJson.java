package com.ihs.springhibernate.json.object;

import java.util.Date;

import com.ihs.springhibernate.utility.Utility;

public class TestJson
{
	private String name;
	private String description;
	private String creationTS;
	private String action;


	/**
	 * @param name
	 * @param description
	 * @param creationTS
	 * @param action
	 *            Id for applying Edit, Delete and other actions
	 */
	public TestJson(String name, String description, Date creationTS, String action)
	{
		this.name = name;
		this.description = description;
		this.creationTS = Utility.DateToString(creationTS);
		this.action = action;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCreationTS()
	{
		return creationTS;
	}

	public void setCreationTS(String creationTS)
	{
		this.creationTS = creationTS;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
}
