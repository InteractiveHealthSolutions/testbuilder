package com.ihs.springhibernate.json;

import java.util.ArrayList;
import java.util.List;

import com.ihs.springhibernate.model.Test;


public class TestJsonWrapper
{
	int iTotalRecords;

	int iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<TestJson> aaData;

	public int getiTotalRecords()
	{
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords)
	{
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords)
	{
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho()
	{
		return sEcho;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

	public String getsColumns()
	{
		return sColumns;
	}

	public void setsColumns(String sColumns)
	{
		this.sColumns = sColumns;
	}

	public List<TestJson> getAaData()
	{
		return aaData;
	}

	/**
	 * It will convert Test list to TestJson list
	 * 
	 * @param data
	 */
	public void setAaData(List<Test> data)
	{
		this.aaData = null;
		this.aaData = new ArrayList<TestJson>();

		for (Test test : data)
		{
			TestJson testJson = new TestJson(test.getName(), test.getDescription(), test.getCreationTS(), String.valueOf(test.getId()));
			this.aaData.add(testJson);
		}
	}
}
