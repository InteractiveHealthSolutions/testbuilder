package com.ihs.springhibernate.controller.test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.ihs.springhibernate.dao.SchemeCategoryDAO;
import com.ihs.springhibernate.dao.SchemeDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.model.SchemeCategory;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class GenerateTestPaper {
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/finalizetest", method = RequestMethod.GET)
	public ModelAndView getSchemeData(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();
		;
		
		String id = request.getParameter("id");
		Integer schemeId = Integer.parseInt(id);

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_TEST()
						+ "/" + resources.getJSP_FINALIZE_TEST());
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);	
				
				List<Scheme> schemeList = new ArrayList<Scheme>();
				schemeList = SchemeDAO.getSchemeById(schemeId);
				
				List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
				schemeCategoryList = SchemeCategoryDAO.getSchemeCategory(schemeId);
				
				modelAndView.getModelMap().put("schemeList", schemeList);
				
				
				
				
				
			}

			else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
			}
		}
		
		
		
		
		
		
		
		
		

		return modelAndView;
	}
}
