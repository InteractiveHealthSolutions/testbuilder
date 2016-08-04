package com.ihs.springhibernate.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.SchemeDAO;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.model.SchemeCategory;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class ViewSchemeController {
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/viewscheme", method = RequestMethod.GET)
	public ModelAndView getSchemeData() {
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST()
				+ "/" + resources.getJSP_VIEWSCHEME());
		
		List<Scheme> schemeList = SchemeDAO.getAllSchemes();
		
		List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
		
		modelAndView.getModelMap().put("currentUser", userSession);
		modelAndView.getModel().put("schemeList", schemeList);
		modelAndView.getModelMap().put("resources", resources);
		modelAndView.getModelMap().put("schemeCategoryList", schemeCategoryList);
		
		return modelAndView;
	}
}
