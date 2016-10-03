package com.ihs.mcqbuilder.formwrapper;

import java.util.ArrayList;

import com.ihs.mcqbuilder.model.Test;

public class FormTakingTest
{
	private ArrayList<QuestionAnswer> questionAnswerList;

	private Test test;

	public FormTakingTest()
	{

	}


	/**
	 * Use this constructor for making object for Form Binding Only
	 * 
	 * @param test
	 */
	public FormTakingTest(Test test)
	{
		this.test = new Test();

		this.test.setId(test.getId());
		this.test.setName(test.getName());
		this.test.setDescription(test.getDescription());
		this.test.setCreatorId(test.getCreatorId());
	}

	public ArrayList<QuestionAnswer> getQuestionAnswerList()
	{
		return questionAnswerList;
	}

	public void setQuestionAnswerList(ArrayList<QuestionAnswer> questionAnswerList)
	{
		this.questionAnswerList = questionAnswerList;
	}

	public Test getTest()
	{
		return test;
	}

	public void setTest(Test test)
	{
		this.test = test;
	}

}
