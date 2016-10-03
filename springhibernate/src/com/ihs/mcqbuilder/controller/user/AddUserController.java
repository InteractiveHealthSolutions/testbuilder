package com.ihs.mcqbuilder.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.RoleDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.dao.RoleDAO.FetchType;
import com.ihs.mcqbuilder.model.Role;
import com.ihs.mcqbuilder.model.User;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
@RequestMapping("/user")
public class AddUserController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public ModelAndView getAddUser(User newUser)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_ADD_USER());

				modelAndView.getModelMap().put("currentUser", userSession);

				modelAndView.getModelMap().put("newUser", new User());
				modelAndView.getModelMap().put("resources", resources);

				List<Role> roleList = RoleDAO.getAllRole(FetchType.LAZY);

				modelAndView.getModelMap().put("roleList", roleList);
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

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("newUser") User user)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_ADD_USER());

		Integer newlySavedId = -1;

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true)
			{
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);			

				// do some server-site validation of user input
			
				newlySavedId = UserDAO.save(user);
				
				if (newlySavedId != -1)
				{
					modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_USER() + "/" + resources.getJSP_DETAIL_USER() + "?id=" + newlySavedId);
					modelAndView.getModel().put("status", resources.getMESSAGE_ADD());
				}

				else
				{
					List<Role> roleList = RoleDAO.getAllRole(FetchType.LAZY);
					modelAndView.getModelMap().put("roleList", roleList);
					modelAndView.getModel().put("status", "Id already exists");
				}			
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
