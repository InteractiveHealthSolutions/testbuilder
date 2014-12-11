package com.ihs.springhibernate.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class ManageTestController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/managetest", method = RequestMethod.GET)
	public ModelAndView getManageTest()
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession != null)
		{
			//if (userSession.getRole().getId() == Privileges.ADMIN.getRoleId() || userSession.getRole().getId() == Privileges.TEST_MAKER.getRoleId()) // if admin then it is fine
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)				
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_MANAGE_TEST());		
				//modelAndView = new ModelAndView(resources.getJSP_MANAGE_TEST());

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);
				
				List<Test> testList = TestDAO.getAllTest(TestDAO.FetchType.EAGER);

				modelAndView.getModelMap().put("testList", testList);
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

