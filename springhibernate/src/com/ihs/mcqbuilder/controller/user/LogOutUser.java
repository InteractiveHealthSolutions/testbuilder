package com.ihs.mcqbuilder.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.ResourcesName;


@Controller
@RequestMapping("/user")
public class LogOutUser {
	
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser()
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;
		userSession.setId(null);
		userSession.setLogin_Id(null);
		userSession.setPassword(null);
		userSession.setName(null);
		userSession.setRole(null);
		modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		return modelAndView;
	}

}
