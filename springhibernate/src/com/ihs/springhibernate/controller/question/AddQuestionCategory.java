package com.ihs.springhibernate.controller.question;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/question")
public class AddQuestionCategory {

	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public ModelAndView getCreateCategory(
			@ModelAttribute("newCategory") CategoryType newCategory) {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION()
						+ "/" + resources.getJSP_ADD_CATEGORY());
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);

				List<CategoryType> categoryList = CategoryTypeDAO
						.getCategoryTypes();
				modelAndView.getModelMap().put("categoryList", categoryList);
			}

			else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/addcategory", method = RequestMethod.POST)
	public ModelAndView submitCategory(
			@ModelAttribute("newCategory") @Valid CategoryType newCategory) {
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(
				resources.getFOLDER_QUESTION() + "/"
						+ resources.getJSP_ADD_CATEGORY());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newCategory", newCategory);

		modelAndView.getModel().put("currentUser", userSession);

		if (userSession != null) {

			boolean a = true;
			String name = newCategory.getTypeName();
			List<CategoryType> cateList = CategoryTypeDAO.getCategoryTypes();

			for (CategoryType cat : cateList) {
				if (cat.getTypeName().equals(name)) {
					a = false;
				}
			}

			Integer newlySavedId = -1;

			if (a == false) {
				modelAndView.getModel().put("status",
						"Category already exists");
			}

			else {

				if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
					newlySavedId = CategoryTypeDAO.saveCategory(newCategory);

					if (newlySavedId != -1) {
						modelAndView = new ModelAndView("redirect:/"
								+ resources.getJSP_HOME());
						modelAndView.getModel().put("status",
								resources.getMESSAGE_ADD());
					}

				}
			}

		}

		return modelAndView;
	}
}
