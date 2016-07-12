package com.ihs.springhibernate.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.dao.UserDAO.By;
import com.ihs.springhibernate.dao.UserDAO.FetchType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
public class IndexController
{
	@Autowired
	private IUserSession userSession;

	// @RequestMapping(value={"/formA.html", "/formB.html", "/formC.html"})
	// @RequestMapping({ "/home", "/contact" })

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView indexDefault(@ModelAttribute("loginUser") User loginUser)
	{

		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getJSP_INDEX());
		modelAndView.getModel().put("loginUser", loginUser);
		return modelAndView;
	}

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public ModelAndView index(@ModelAttribute("loginUser") User loginUser)
	// {
	// ResourcesName resources = new ResourcesName();
	//
	// ModelAndView modelAndView = new ModelAndView(resources.getJSP_INDEX());
	// modelAndView.getModel().put("loginUser", loginUser);
	// return modelAndView;
	// }

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView authenticateUser(@ModelAttribute("loginUser") @Valid User loginUser, BindingResult result)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		/**
		 * Using it to validate desired fields of an object
		 */

		Set<ConstraintViolation<User>> constraintViolations = validator.validateProperty(loginUser, "login_Id");
		Set<ConstraintViolation<User>> constraintViolations2 = validator.validateProperty(loginUser, "password");

		if (constraintViolations.size() > 0 || constraintViolations2.size() > 0)
		{
			modelAndView = new ModelAndView(resources.getJSP_INDEX());
			modelAndView.getModel().put("loginUser", loginUser);

			return modelAndView;
		}

		String notice = "";

		if (UserDAO.authenticate(loginUser) == true)
		{
			// / Setting session of user
			loginUser = UserDAO.getUser(By.LoginId, loginUser.getLogin_Id(), FetchType.EAGER);

			// user.getRole().getPrivilegeList();

			userSession.setId(loginUser.getId());
			userSession.setName(loginUser.getName());

			//userSession.setName(loginUser.getName());
			userSession.setRole(loginUser.getRole());
			userSession.setCreationTS(loginUser.getCreationTS());

			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_HOME());
		}

		else
		{
			notice = "Invalid ID or Password";

			modelAndView = new ModelAndView(resources.getJSP_INDEX());
			modelAndView.getModelMap().put("loginUser", new User());
			modelAndView.getModelMap().put("notice", notice);
		}

		return modelAndView;
	}
}
