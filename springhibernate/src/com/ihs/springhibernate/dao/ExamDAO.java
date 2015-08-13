package com.ihs.springhibernate.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ihs.springhibernate.formwrapper.FormTakingTest;
import com.ihs.springhibernate.model.Answer;
import com.mysql.fabric.xmlrpc.base.Array;

public class ExamDAO
{
	static Logger log = Logger.getLogger(ExamDAO.class);

	public static void SaveAnswerFromExam(FormTakingTest exam)
	{
		ArrayList<Answer> answerList = new ArrayList<Answer>();

		for (int i = 0; i < exam.getQuestionAnswerList().size(); i++)
		{
			answerList.add(exam.getQuestionAnswerList().get(i).getAnswer());
		}

		AnswerDAO.save(answerList);
	}

	/**
	 * Returns Test along with its question and solved answers
	 * 
	 * @param testId
	 * @param testTakerId
	 * @return FormTakingTest object
	 */
	public static FormTakingTest getExam(Integer testId, Integer testTakerId)
	{
		FormTakingTest exam = new FormTakingTest();

		return exam;

	}
}
