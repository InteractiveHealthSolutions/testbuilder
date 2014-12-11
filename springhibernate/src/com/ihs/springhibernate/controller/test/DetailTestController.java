package com.ihs.springhibernate.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;

import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class DetailTestController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/viewtest", method = RequestMethod.GET)
	public ModelAndView getQuestion(@RequestParam("id") String testId, @RequestParam(value = "status", required = false) String status)
	{
		Test test = TestDAO.getTest(TestDAO.By.ID, testId, TestDAO.FetchType.EAGER);

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) //
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_VIEW_TEST());

				modelAndView.getModelMap().put("detailTest", test);
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				modelAndView.getModel().put("status", status);
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
				// modelAndView.getModel().put("status", status);
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}
}
