package com.ihs.springhibernate.controller.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/error")
public class ErrorController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/errorpage", method = RequestMethod.GET)
	public ModelAndView getErrorPage(@RequestParam(value = "message", required = false) String message)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null)
		{
			modelAndView = new ModelAndView(resources.getFOLDER_ERROR() + "/" + resources.getJSP_ERROR_PAGE());
			modelAndView.getModelMap().put("resources", resources);
			modelAndView.getModelMap().put("message", message);
		}

		else
		{
			modelAndView = new ModelAndView(new RedirectView(resources.getJSP_INDEX()));
		}

		return modelAndView;
	}

}
