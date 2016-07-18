package com.ihs.springhibernate.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;


@Controller
@RequestMapping("/test")
public class CreateSchemeController {
	
	@Autowired
	private IUserSession userSession;
	
	@RequestMapping(value = "/createtest", method = RequestMethod.GET)
	public ModelAndView getCreateScheme(@ModelAttribute("newScheme") Scheme newScheme){
		
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();
		
		if (userSession.getName() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_CREATE_SCHEME());

				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				
				List<CategoryType> categoryType = CategoryTypeDAO.getCategoryTypes();
				modelAndView.getModel().put("categoryType", categoryType);
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
