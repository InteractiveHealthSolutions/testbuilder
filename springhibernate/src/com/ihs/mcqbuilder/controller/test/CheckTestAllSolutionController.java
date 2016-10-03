package com.ihs.mcqbuilder.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ihs.mcqbuilder.dao.TestDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.model.Test;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class CheckTestAllSolutionController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/checktestallsol", method = RequestMethod.GET)
	public ModelAndView getTestAllSolution(@RequestParam("no") String testId)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_CHECKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_CHECK_ALL_SOL());

				Test test = TestDAO.getTest(TestDAO.By.ID, testId, TestDAO.FetchType.EAGER);



				// FormTakingTest exam;
				// exam = new FormTakingTest(test);

				// List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
				//
				// exam.setQuestionAnswerList(new ArrayList<QuestionAnswer>());
				//
				// for (Question question : test.getQuestionList())
				// {
				// /*
				// * Answer object should have atleast 1 answerData object
				// */
				// Answer answer = new Answer();
				//
				// // TODO: is it right?
				// // answer.setQuestionId(question.getId());
				// answer.setQuestion(question);
				//
				// answer.setAnswerDataList(new ArrayList<AnswerData>());
				// int i = 0;
				//
				// do
				// {
				// AnswerData ansData = new AnswerData();
				// answer.getAnswerDataList().add(ansData);
				// i++;
				// }
				// while (i < question.getQuestionDataList().size());
				//
				// QuestionAnswer questionAnswer = new QuestionAnswer();
				// questionAnswer.setQuestion(question);
				// questionAnswer.setAnswer(answer);
				//
				// exam.getQuestionAnswerList().add(questionAnswer);


				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_ERROR() + "/" +
						resources.getJSP_ERROR_PAGE() + "?message=" + resources.getMESSAGE_NOPRIVILEGE());
			}
		}

		else
		{
			modelAndView = new ModelAndView(new RedirectView(resources.getJSP_INDEX()));
		}

		return modelAndView;
	}
}
