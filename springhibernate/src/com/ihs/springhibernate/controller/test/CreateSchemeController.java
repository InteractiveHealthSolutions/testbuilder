package com.ihs.springhibernate.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.SchemeDAO;
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
	public ModelAndView getCreateScheme(
			@ModelAttribute("newScheme") Scheme newScheme) {

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_TEST()
						+ "/" + resources.getJSP_CREATE_SCHEME());

				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);

				List<CategoryType> categoryType = CategoryTypeDAO
						.getCategoryTypes();
				modelAndView.getModel().put("categoryType", categoryType);
			} else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
			}
		}

		else {
			modelAndView = new ModelAndView("redirect:/"
					+ resources.getJSP_INDEX());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/createtest", method = RequestMethod.POST)
	ModelAndView submitTest(
			@ModelAttribute("newScheme") @Valid Scheme newScheme,
			BindingResult result, HttpServletRequest request) {
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST()
				+ "/" + resources.getJSP_CREATE_SCHEME());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newScheme", newScheme);

		modelAndView.getModel().put("currentUser", userSession);

		if (userSession.getName() != null) {

			Integer newlySavedId = -1;
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				newlySavedId = SchemeDAO.saveScheme(newScheme);

				if (newlySavedId != -1) {
					// modelAndView = new ModelAndView("redirect:/" +
					// resources.getFOLDER_QUESTION()+ "/" +
					// resources.getJSP_CREATE_QUESTION());
					// modelAndView.getModel().put("status",
					// resources.getMESSAGE_ADD());
				}

				else {
					modelAndView.getModel().put("status",
							resources.getMESSAGE_FAIL_ADD());
				}
			}

/*			List<CategoryType> categories = CategoryTypeDAO.getCategoryTypes();
			HashMap<Integer, Integer> percentageList = new HashMap<Integer, Integer>();
			HashMap<Integer, String> labelList = new HashMap<Integer, String>();
			// String a = "";

			for (CategoryType cat : categories) {
				String a = request.getParameter("percentage" + cat.getId());
				if (a == null) {
				} else {
					percentageList.put(cat.getId(), Integer.parseInt(a));
					labelList.put(cat.getId(), cat.getTypeName());
					String s = labelList.get(cat.getId());
				}
			}*/

		}
		return modelAndView;
	}
}
