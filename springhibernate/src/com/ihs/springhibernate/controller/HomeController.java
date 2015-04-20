package com.ihs.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
public class HomeController
{
	@Autowired
	private IUserSession userSession;

	// Error : It will allow logged in user and don't show any dash board

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome()
	{
		// User currentUser = UserBuilder.getSessionUser();

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		/*
		 * TODO: dirty way to validate userSession is not null
		 */
		if (userSession.getName() != null)
		// if (userSession.getId() != null)
		{
			userSession.getRole().getPrivilegeList();
			modelAndView = new ModelAndView(resources.getJSP_HOME());
			modelAndView.getModelMap().put("currentUser", userSession);
			modelAndView.getModelMap().put("Resource", resources);
		}
		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}
}
