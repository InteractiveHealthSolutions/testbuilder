package com.ihs.springhibernate.json.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.json.object.SolvedTestJson;
import com.ihs.springhibernate.model.Test;

public class SolvedTestJW extends JsonWrapper
{

	@Override
	public void setAaData(List data)
	{
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		this.aaData = null;
		this.aaData = new ArrayList<SolvedTestJson>();

		for (Object objectTest : data)
		{
			Test test2 = (Test) objectTest;

			int totalAttempts = 0;

			// TODO: get total number of attemtps
			totalAttempts = TestDAO.getTestAllAnswers(test2.getId());

			SolvedTestJson solvedTestJson = new SolvedTestJson(test2.getName(),
					test2.getDescription(), totalAttempts, test2.getCreationTS(),
					String.valueOf(test2.getId()));
			this.aaData.add(solvedTestJson);
		}

	}

}
