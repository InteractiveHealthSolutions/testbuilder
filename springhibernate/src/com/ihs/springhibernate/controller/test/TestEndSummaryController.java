package com.ihs.springhibernate.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.AnswerTypeDAO;
import com.ihs.springhibernate.dao.QuestionTypeDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.formwrapper.FormTakingTest;
import com.ihs.springhibernate.model.AnswerType;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class TestEndSummaryController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/testendsummary", method = RequestMethod.GET)
	public ModelAndView getExamSummary(Model model, @RequestParam(required = false) FormTakingTest exam)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_END_SUMMARY());

				// modelAndView.getModel().put("newQuestion", newQuestion);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				// List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

				// modelAndView.getModel().put("questionTypeList", questionTypeList);



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

					modelAndView.getModel().put("answeredQuestions", answeredQuestions);
					modelAndView.getModel().put("exam", exam);

					// modelAndView.getModel().put("questionTypeList", answeredQuestions);

					String status = "Submitted";

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
