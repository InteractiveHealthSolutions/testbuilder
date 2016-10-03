package com.ihs.mcqbuilder.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.RoleDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.dao.UserDAO.By;
import com.ihs.mcqbuilder.dao.UserDAO.FetchType;
import com.ihs.mcqbuilder.model.Role;
import com.ihs.mcqbuilder.model.User;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
@RequestMapping("/user")
public class EditUserController
{

	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public ModelAndView getEditUser(@RequestParam("id") String userId)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getRole().getId() != null)
		{			
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true)

			{
				User user = UserDAO.getUser(By.Id, userId, FetchType.LAZY);

				modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_EDIT_USER());

				modelAndView.getModel().put("editUser", user);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				List<Role> roleList = RoleDAO.getAllRole(RoleDAO.FetchType.LAZY);

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


	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("editUser") @Valid User editUser, BindingResult bindingResult)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		modelAndView = new ModelAndView(resources.getFOLDER_USER() + "/" + resources.getJSP_EDIT_USER());

		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.ADMIN) == true) // if admin then it is fine
			{
				modelAndView.getModel().put("editUser", editUser);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);				

				if (bindingResult.hasErrors())
				{
					List<Role> roleList = RoleDAO.getAllRole(RoleDAO.FetchType.LAZY);

					modelAndView.getModelMap().put("roleList", roleList);

					modelAndView.getModel().put("status", resources.getMESSAGE_FAIL_UPDATE());

					return modelAndView;
				}
			
				if (UserDAO.update(editUser) == true)
				{					
					modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_USER() + "/" + resources.getJSP_DETAIL_USER() + "?id=" + editUser.getId());
					modelAndView.getModel().put("status", resources.getMESSAGE_UPDATE());
				}

				else
				{
					modelAndView.getModel().put("status", resources.getMESSAGE_FAIL_UPDATE());
				}				
			}
		}

		return modelAndView;
	}
}
