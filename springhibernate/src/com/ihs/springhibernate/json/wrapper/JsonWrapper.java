package com.ihs.springhibernate.json.wrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonWrapper<T>
{
	int iTotalRecords;

	int iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	ArrayList<T> aaData;

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

	public List getAaData()
	{
		return aaData;
	}

	/**
	 * This Method has to be defined by User for each Json response
	 */
	public abstract void setAaData(List<?> data);
}
