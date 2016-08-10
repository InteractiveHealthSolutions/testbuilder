package com.ihs.springhibernate.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.AnswerDAO;
import com.ihs.springhibernate.dao.ExamDAO;
import com.ihs.springhibernate.dao.QuestionTypeDAO;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.TestDAO.By;
import com.ihs.springhibernate.dao.TestDAO.FetchType;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.formwrapper.FormTakingTest;
import com.ihs.springhibernate.formwrapper.QuestionAnswer;
import com.ihs.springhibernate.model.Answer;
import com.ihs.springhibernate.model.AnswerData;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class TakingTestController
{
	@Autowired
	private IUserSession userSession;

	@ModelAttribute("exam")
	// @RequestMapping(method = RequestMethod.GET) // want to restrict this population with get request only
	public FormTakingTest populateExam(@RequestParam String token)
	{
		System.out.println("populating FormTakingTest");
		FormTakingTest exam = new FormTakingTest();
		Test test = TestDAO.getTest(By.ID, token, FetchType.EAGER);

		// If test has questions then proceed forwards
		// otherwise show error or tell him to choose other test

		if (test.getQuestionList().size() > 0)
		{
			/**
			 * Preparing Exam object with test and same number of question and answer
			 */

			// exam = new FormTakingTest(test);
			exam = new FormTakingTest();
			exam.setTest(test);
			exam.setQuestionAnswerList(new ArrayList<QuestionAnswer>());

			for (Question question : test.getQuestionList())
			{
				/*
				 * Answer object should have atleast 1 answerData object
				 */
				Answer answer = new Answer();

				// TODO: is it right?
				// answer.setQuestionId(question.getId());
				answer.setQuestion(question);

				answer.setAnswerDataList(new ArrayList<AnswerData>());
				int i = 0;

				do
				{
					AnswerData ansData = new AnswerData();
					answer.getAnswerDataList().add(ansData);
					i++;
				}
				while (i < question.getQuestionDataList().size());

				QuestionAnswer questionAnswer = new QuestionAnswer();
				questionAnswer.setQuestion(question);
				questionAnswer.setAnswer(answer);

				exam.getQuestionAnswerList().add(questionAnswer);

			}
		}
		return exam;
	}

	@RequestMapping(value = "/takingtest", method = RequestMethod.GET)
	// public ModelAndView getExam(@RequestParam String token)
	public ModelAndView getExam(@RequestParam String token, @ModelAttribute("exam") FormTakingTest exam)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				/*
				 * Add a logic to verify if this request is valid and have valid get/post data
				 * token = token + "newValue";
				 * TODO:above token will have id of test
				 * Integer testId = Integer.valueOf(token);
				 */

				Test test = TestDAO.getTest(By.ID, token, FetchType.EAGER);

				// If test has questions then proceed forwards
				// otherwise show error or tell him to choose other test

				if (test.getQuestionList().size() > 0)
				{
					modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TAKING_TEST());

					modelAndView.getModel().put("token", token);

					modelAndView.getModel().put("currentUser", userSession);

					modelAndView.getModel().put("resources", resources);

					// how to add enum objects ???

					List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

					modelAndView.getModel().put("questionTypeList", questionTypeList);
				}
			}

			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
				modelAndView.getModel().put("loginUser", new User());
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/submittest", method = RequestMethod.POST)
	// public ModelAndView postExam(@ModelAttribute("exam") @Valid FormTakingTest exam, BindingResult result, @RequestParam String token, RedirectAttributes redirectAttributes)
	// public ModelAndView postExam(@ModelAttribute("exam") FormTakingTest exam, @RequestParam String token, RedirectAttributes redirectAttributes)
	public ModelAndView postExam(@ModelAttribute("exam") @Valid FormTakingTest exam, BindingResult result, @RequestParam String token)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				/*
				 * Add a logic to verify if this request is valid and have valid get/post data
				 * verify the token
				 * save the answer
				 * TODO:above token will have id of test
				 */

				FormTakingTest newExam = AnswerDAO.removeNullAnswerDataFromExam(exam);
				/*
				 * We can't show validation error to test taker,
				 * so we need a workaround
				 */

				Integer newlySavedId = -1;

				/**
				 * Make testId hashed along with test taker Id
				 */
				// Integer testId = answerList.get(0).getTestId();

				// Implement logic for handling failure while saving test
				ExamDAO.SaveAnswerFromExam(exam);

				// redirectAttributes.addFlashAttribute("exam", exam);

				// modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_END_SUMMARY());
				//
				// modelAndView.getModel().put("resources", resources);

				if (exam.getQuestionAnswerList() != null)
				{
					// getting total number of answered questions

					int answeredQuestions = exam.getQuestionAnswerList().size();

					for (int i = 0; i < exam.getQuestionAnswerList().size(); i++)
					{
						if (exam.getQuestionAnswerList().get(i).getAnswered() != null)
						{
							if (exam.getQuestionAnswerList().get(i).getAnswered() == false)
							{
								answeredQuestions -= 1;
							}
						}
					}

					modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_END_SUMMARY());

					modelAndView.getModel().put("answeredQuestions", answeredQuestions);
					modelAndView.getModel().put("exam", exam);

					String status = "Submitted";

					// modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_END_SUMMARY());

					modelAndView.getModel().put("resources", resources);

					modelAndView.getModel().put("status", status);
				}
			}

			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
				modelAndView.getModel().put("loginUser", new User());
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}
}
