package com.ihs.springhibernate.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.utility.QuestionTypes;

public class QuestionValidator implements Validator
{
	@Override
	public boolean supports(Class question)
	{
		return Question.class.equals(question);
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		Question question = (Question) target;

		if (question.getQuestionType().getId() != QuestionTypes.PARAGRAPH.getTypeId())
		{
			if (question.getQuestionDataList().size() > 0)
			{
				for (QuestionData questionData : question.getQuestionDataList())
				{
					if (questionData != null)
					{
						if (questionData.getData().isEmpty() == true)
						{
							errors.rejectValue("questionDataList", "question data can not be empty");
							break;
						}
					}

					else
					{
						errors.rejectValue("questionDataList", "question data can not be empty");
					}
				}
			}

			else
			{
				errors.rejectValue("questionDataList", "question data can not be empty");
			}
		}
	}
	// do "complex" validation here

}
