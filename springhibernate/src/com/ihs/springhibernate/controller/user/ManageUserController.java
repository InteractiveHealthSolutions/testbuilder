package com.ihs.springhibernate.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.dao.UserDAO.FetchType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/user")
public class ManageUserController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/manageuser", method = RequestMethod.GET)
	public ModelAndView getManageAccount()
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true)// if admin then it is fine
			{
				modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_MANAGE_USER());

				modelAndView.getModelMap().put("currentUser", userSession);

				List<User> userList = UserDAO.getAllUser(FetchType.EAGER);

				modelAndView.getModelMap().put("userList", userList);
				modelAndView.getModelMap().put("resources", resources);
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
