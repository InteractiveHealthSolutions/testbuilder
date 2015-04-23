package com.ihs.springhibernate.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ihs.springhibernate.dao.AnswerDAO;
import com.ihs.springhibernate.dao.AnswerDAO.By;
import com.ihs.springhibernate.dao.AnswerDAO.FetchType;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Answer;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

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

				List<Answer> answerList = AnswerDAO.getAnswerList(By.TESTID, testId, FetchType.LAZY);

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
