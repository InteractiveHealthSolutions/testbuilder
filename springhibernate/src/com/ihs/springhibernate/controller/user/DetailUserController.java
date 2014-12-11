package com.ihs.springhibernate.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.dao.UserDAO.By;
import com.ihs.springhibernate.dao.UserDAO.FetchType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/user")
public class DetailUserController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/detailuser", method = RequestMethod.GET)
	public ModelAndView getHome(@RequestParam("id") String userId, @RequestParam(value = "status", required = false) String status)
	{
		User user = UserDAO.getUser(By.Id, userId, FetchType.LAZY);

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true) // if admin then it is fine
			{
				
				modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_DETAIL_USER());
			

				modelAndView.getModelMap().put("detailUser", user);
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
