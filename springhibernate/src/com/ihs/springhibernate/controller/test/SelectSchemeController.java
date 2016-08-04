package com.ihs.springhibernate.controller.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.SchemeDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;


@Controller
@RequestMapping("/test")
public class SelectSchemeController {

	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/selectscheme", method = RequestMethod.GET)
	public ModelAndView getScheme() {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_TEST()
						+ "/" + resources.getJSP_SELECT_SCHEME());
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				
				List<Scheme> schemeListAll = SchemeDAO.getAllSchemes();
				modelAndView.getModel().put("schemeListAll", schemeListAll);
				
				modelAndView.getModelMap().put("schemeListAll", schemeListAll);
			}

			else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
			}
		}

		return modelAndView;
	}
	
	@RequestMapping(value="/selectscheme", method= RequestMethod.POST)
	public ModelAndView submitScheme(HttpServletRequest request){
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST()
				+ "/" + resources.getJSP_SELECT_SCHEME());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("currentUser", userSession);
		
		String schemeId = request.getParameter("id");
		
		if(userSession.getName() != null){
			 modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_TEST() + "/" + resources.getJSP_FINALIZE_TEST()+"?id="+ schemeId + "&type=2");
		}
		
		return modelAndView;
	}
}
