package com.ihs.springhibernate.json.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.ihs.springhibernate.json.object.TestJson;
import com.ihs.springhibernate.model.Test;

public class TestJsonWrapper2 extends JsonWrapper
{

	@Override
	public void setAaData(List data)
	{
		// TODO Auto-generated method stub

		this.aaData = null;
		this.aaData = new ArrayList<TestJson>();

		for (Object objectTest : data)
		{
			Test test2 = (Test) objectTest;

			TestJson testJson = new TestJson(test2.getName(), test2.getDescription(), test2.getCreationTS(), String.valueOf(test2.getId()));
			this.aaData.add(testJson);
		}
	}
}
