package com.ihs.springhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
public class ManageTestQuestionController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/managetestquestion", method = RequestMethod.GET)
	public ModelAndView getManageTest()
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null)
		{
			if (userSession.getRole().getId() == Privileges.ADMIN.getRoleId() || userSession.getRole().getId() == Privileges.TEST_MAKER.getRoleId()) // if admin then it is fine
			{
				modelAndView = new ModelAndView(resources.getJSP_MANAGE_TEST_QUESTION());

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				// List<Role> roleList = RoleDAO.getAllRole(RoleDAO.FetchType.LAZY);
				//
				// modelAndView.getModelMap().put("roleList", roleList);
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}
}
