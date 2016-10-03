package com.ihs.mcqbuilder.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.ihs.mcqbuilder.dao.QuestionDAO;
import com.ihs.mcqbuilder.dao.QuestionDAO.By;
import com.ihs.mcqbuilder.dao.QuestionDAO.FetchType;
import com.ihs.mcqbuilder.model.Question;

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
