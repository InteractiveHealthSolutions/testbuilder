package com.ihs.springhibernate.controller.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.SchemeCategoryDAO;
import com.ihs.springhibernate.dao.SchemeDAO;
import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.model.SchemeCategory;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class ViewSchemeController {
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/manageschemes", method = RequestMethod.GET)
	public ModelAndView getSchemeData(HttpServletRequest request) {
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST()
				+ "/" + resources.getJSP_VIEWSCHEME());

		List<Scheme> schemeList = SchemeDAO.getAllSchemes();

		List<SchemeCategory> schemeCategoryList = SchemeCategoryDAO
				.getAllSchemes();

		String msg = request.getParameter("id");

		if (msg != null) {
			modelAndView.getModel().put("message", "Scheme added successfully");
		}

		modelAndView.getModelMap().put("currentUser", userSession);
		modelAndView.getModel().put("schemeList", schemeList);
		modelAndView.getModelMap().put("resources", resources);
		modelAndView.getModelMap()
				.put("schemeCategoryList", schemeCategoryList);

		return modelAndView;
	}

	@RequestMapping(value = "/manageschemes", method = RequestMethod.POST)
	public ModelAndView deleteScheme(HttpServletRequest request) {
		Long result;
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST()
				+ "/" + resources.getJSP_VIEWSCHEME());

		modelAndView.getModel().put("resources", resources);
		modelAndView.getModel().put("currentUser", userSession);

		String schemeName = request.getParameter("id");
		Integer schemeId = Integer.parseInt(schemeName);
		result = SchemeDAO.countSchemeById(schemeId);

		if (result == 0) {
			SchemeDAO.deleteSchemeCategoryById(schemeId);
			SchemeDAO.deleteSchemeById(schemeId);
			List<Scheme> schemeList = SchemeDAO.getAllSchemes();
			List<SchemeCategory> schemeCategoryList = SchemeCategoryDAO
					.getAllSchemes();
			modelAndView.getModelMap().put("schemeCategoryList",
					schemeCategoryList);
			modelAndView.getModel().put("schemeList", schemeList);
			modelAndView.getModelMap().put("message",
					"Scheme successfully deleted");
		}

		else {
			List<Scheme> schemeList = SchemeDAO.getAllSchemes();
			List<SchemeCategory> schemeCategoryList = SchemeCategoryDAO
					.getAllSchemes();
			modelAndView.getModelMap().put("schemeCategoryList",
					schemeCategoryList);
			modelAndView.getModel().put("schemeList", schemeList);
			modelAndView.getModelMap().put("message",
					"Scheme cannot be deleted as this scheme exists in test");
		}

		return modelAndView;

	}
}
