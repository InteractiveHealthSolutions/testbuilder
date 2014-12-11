package com.ihs.springhibernate.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.QuestionDAO.By;
import com.ihs.springhibernate.dao.QuestionDAO.FetchType;
import com.ihs.springhibernate.model.Question;

public class Editor extends PropertyEditorSupport
{
	// private final Dao hibernateService;

	public Editor()
	{
		// this.hibernateService=hibernateService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		Question question = QuestionDAO.getQuestion(By.ID, text, FetchType.EAGER);
		// Author author = hibernateService.getAuthor(Integer.parseInt(text));
		setValue(question);
	}
}
