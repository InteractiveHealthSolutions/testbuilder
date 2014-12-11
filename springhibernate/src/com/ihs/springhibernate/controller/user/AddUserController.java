package com.ihs.springhibernate.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.RoleDAO;
import com.ihs.springhibernate.dao.RoleDAO.FetchType;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Role;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

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
			// if (userSession.getRole().getId() == Privileges.ADMIN.getRoleId()) // if admin then it is fine

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
	//public ModelAndView addUser(@ModelAttribute("newUser") User user, @RequestParam("name") String name, @RequestParam("loginId") String loginId, @RequestParam("password") String password,
	public ModelAndView addUser(@ModelAttribute("newUser") User user)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_ADD_USER());


		Integer newlySavedId = -1;

		if (userSession.getRole().getId() != null)
		{
			// if (userSession.getRole().getId() == Privileges.ADMIN.getRoleId()) // if admin then it is fine

			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true)
			{
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);

//				User user = new User();

				// do some server-site validation of user input
//
//				user.setName(name);
//				user.setLogin_Id(loginId);
//				user.setPassword(password);

//				Role selectedRole = RoleDAO.getRole(RoleDAO.By.ROLE_NAME, roleType.toLowerCase(), FetchType.LAZY);

//				if (selectedRole != null)
//				{
//					user.setRole(selectedRole);
					newlySavedId = UserDAO.save(user);
					if (newlySavedId != -1)
					{
						modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_USER() + "/" + resources.getJSP_DETAIL_USER() + "?id=" + newlySavedId);
						modelAndView.getModel().put("status", resources.getMESSAGE_ADD());
					}

					else
					{
						modelAndView.getModel().put("status", resources.getMESSAGE_FAIL_ADD());
					}
//				}
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
