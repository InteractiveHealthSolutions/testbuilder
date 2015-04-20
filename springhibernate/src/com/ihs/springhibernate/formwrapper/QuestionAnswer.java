package com.ihs.springhibernate.formwrapper;

import com.ihs.springhibernate.model.Answer;
import com.ihs.springhibernate.model.Question;

public class QuestionAnswer
{
	private Question question;
	private Answer answer;


	private Boolean answered;

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public Answer getAnswer()
	{
		return answer;
	}

	/**
	 * It will be used to show that paragraph answer has been given or not
	 */
	public Boolean getAnswered()
	{
		return answered;
	}

	/**
	 * It will be used to show that paragraph answer has been given or not
	 */
	public void setAnswered(Boolean answered)
	{
		this.answered = answered;
	}

	public void setAnswer(Answer answer)
	{
		this.answer = answer;
	}
}
