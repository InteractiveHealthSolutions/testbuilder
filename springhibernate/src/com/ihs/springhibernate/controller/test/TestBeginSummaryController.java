package com.ihs.springhibernate.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.TestDAO.By;
import com.ihs.springhibernate.dao.TestDAO.FetchType;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class TestBeginSummaryController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/testbeginsummary", method = RequestMethod.GET)
	public ModelAndView getTestSummary(@RequestParam("no") String testId)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				// TODO: Add logic to verify user is redirected here by taketestlistController

				// TODO: Add logic to add some temporary id which can be verified on start of test
				// Search for making of temporary link

				Test selectedTest = TestDAO.getTest(By.ID, testId, FetchType.EAGER);
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_BEGIN_SUMMARY());
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("selectedTest", selectedTest);

				/***
				 * This token will be recieved from takingtestlist.jsp
				 * It should validate it like encoded dateTime is not old more then 10 minutes
				 */
				String token = String.valueOf(selectedTest.getId());

				modelAndView.getModelMap().put("token", token);
				modelAndView.getModelMap().put("resources", resources);
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
