package com.ihs.springhibernate.json.object;

import java.util.Date;

import com.ihs.springhibernate.utility.Utility;

public class SolvedTestJson
{
	private String name;
	private String description;
	private String numberOfAttempts;
	private String lastAttemptOn;
	private String action;

	/**
	 * @param name
	 *            Test Name
	 * @param description
	 *            Test Description
	 * @param numberOfAttemtps
	 *            Total number of attempts
	 * @param lastAttemptOn
	 *            Last attempt date
	 * @param action
	 *            Test Id to use for querystring
	 */
	public SolvedTestJson(String name, String description, int numberOfAttemtps, Date lastAttemptOn, String action)
	{
		this.name = name;
		this.description = description;
		this.numberOfAttempts = String.valueOf(numberOfAttemtps);
		this.lastAttemptOn = Utility.DateToString(lastAttemptOn);
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

	public String getNumberOfAttempts()
	{
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(String numberOfAttempts)
	{
		this.numberOfAttempts = numberOfAttempts;
	}

	public String getLastAttemptOn()
	{
		return lastAttemptOn;
	}

	public void setLastAttemptOn(String lstAttemptOn)
	{
		this.lastAttemptOn = lstAttemptOn;
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
