package com.ihs.springhibernate.controller.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.QuestionTypeDAO;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.TestDAO.By;
import com.ihs.springhibernate.dao.TestDAO.FetchType;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.QuestionTypes;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class TakingTestController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/takingtest", method = RequestMethod.GET)
	public ModelAndView getEditQuestion(@RequestParam String token)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				// Add a logic to verify if this request is valid and have valid get/post data

				// token = token + "newValue";

				// TODO:above token will have id of test

				// Integer testId = Integer.valueOf(token);

				Test test = TestDAO.getTest(By.ID, token, FetchType.EAGER);

				// If test has questions then proceed forwards
				// otherwise show error or tell him to choose other test

				if (test.getQuestionList().size() > 0)
				{
					//List<Answer> answerList = new ArrayList<Answer>();
					
					
					for (int i = 0; i < test.getQuestionList().size(); i++)
					{

					}

					modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TAKING_TEST());

					modelAndView.getModel().put("test", test);

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

	@RequestMapping(value = "/takingtest", method = RequestMethod.POST)
	public ModelAndView getTest(@RequestParam String token)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				// Add a logic to verify if this request is valid and have valid get/post data

				// token = token + "newValue";

				// TODO:above token will have id of test

				Test test = TestDAO.getTest(By.ID, token, FetchType.EAGER);

				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TAKING_TEST());

				modelAndView.getModel().put("test", test);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				// how to add enum objects ???

				List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

				modelAndView.getModel().put("questionTypeList", questionTypeList);
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
