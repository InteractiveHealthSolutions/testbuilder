package com.ihs.mcqbuilder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.dao.UserDAO.By;
import com.ihs.mcqbuilder.dao.UserDAO.FetchType;
import com.ihs.mcqbuilder.model.User;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
public class testingjsptags
{

	@Autowired
	private IUserSession userSession;
	
	@RequestMapping(value = "/testingjsptags", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("loginUser") User loginUser)
	{
		ResourcesName resources = new ResourcesName();

		User user = UserDAO.getUser(By.Id, "2", FetchType.EAGER);
		ModelAndView modelAndView = new ModelAndView("loginpagejsp");
		modelAndView.getModel().put("user", user);
		return modelAndView;
	}
}
