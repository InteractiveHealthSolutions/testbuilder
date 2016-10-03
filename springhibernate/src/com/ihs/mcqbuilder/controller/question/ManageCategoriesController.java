package com.ihs.mcqbuilder.controller.question;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.CategoryTypeDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.model.CategoryType;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
@RequestMapping("/question")
public class ManageCategoriesController {
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/managecategories", method = RequestMethod.GET)
	public ModelAndView getManageCategory() {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION()
						+ "/" + resources.getJSP_MANAGE_CATEGORY());
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);

				List<CategoryType> categoryList = CategoryTypeDAO
						.getCategoryTypes();
				modelAndView.getModelMap().put("categoryList", categoryList);

			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "/managecategories", method = RequestMethod.POST)
	public ModelAndView deleteCategory(HttpServletRequest request) {
		Long result;
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = new ModelAndView(
				resources.getFOLDER_QUESTION() + "/"
						+ resources.getJSP_MANAGE_CATEGORY());

		modelAndView.getModel().put("resources", resources);
		modelAndView.getModel().put("currentUser", userSession);

		String categoryName = request.getParameter("id");
		Integer categoryId = Integer.parseInt(categoryName);
		result = CategoryTypeDAO.countCategoryById(categoryId);

		if (result == 0) {
			CategoryTypeDAO.deleteCategoryById(Integer.parseInt(categoryName));
			List<CategoryType> categoryList = CategoryTypeDAO
					.getCategoryTypes();
			modelAndView.getModelMap().put("categoryList", categoryList);
			modelAndView.getModelMap().put("message",
					"Category successfully deleted");
		}

		else {
			List<CategoryType> categoryList = CategoryTypeDAO
					.getCategoryTypes();
			modelAndView.getModelMap().put("categoryList", categoryList);
			modelAndView
					.getModelMap()
					.put("message",
							"Category cannot be deleted as question exists of this category");
		}

		return modelAndView;

	}
}
