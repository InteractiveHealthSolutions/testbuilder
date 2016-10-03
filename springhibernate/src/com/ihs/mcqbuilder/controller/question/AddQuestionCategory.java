package com.ihs.mcqbuilder.controller.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
public class AddQuestionCategory {

	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public ModelAndView getCreateCategory(
			@ModelAttribute("newCategory") CategoryType newCategory, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION()
						+ "/" + resources.getJSP_ADD_CATEGORY());
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				
				String status = request.getParameter("success");
				
				if(status == null){
					
				}
				
				else if(Integer.parseInt(status) == 1){
					modelAndView.getModel()
					.put("status", "Added Successfully");
				}
						

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
			@ModelAttribute("newCategory") @Valid CategoryType newCategory,
			HttpServletResponse response) throws IOException {
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(
				resources.getFOLDER_QUESTION() + "/"
						+ resources.getJSP_ADD_CATEGORY());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newCategory", newCategory);

		modelAndView.getModel().put("currentUser", userSession);

		if (userSession != null) {

			/*
			 * if (StringUtils.isBlank(newCategory.getTypeName()) ||
			 * StringUtils.isBlank(newCategory.getDescription())) {
			 * modelAndView.getModel() .put("status",
			 * "Required Fields Missing"); }
			 */

			// else {

			boolean a = true;
			String name = newCategory.getTypeName();
			List<CategoryType> cateList = CategoryTypeDAO.getCategoryTypes();

			for (CategoryType cat : cateList) {
				if (cat.getTypeName().toLowerCase().equals(name.toLowerCase())) {
					a = false;
				}
			}

			Integer newlySavedId = -1;

			if (a == false) {
				modelAndView.getModel()
				.put("status", "Category already exists");
			}

			else {

				if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
					newlySavedId = CategoryTypeDAO.saveCategory(newCategory);

					if (newlySavedId != -1) {
						
						modelAndView =	new ModelAndView("redirect:/" + resources.getFOLDER_QUESTION() + "/"
								+ resources.getJSP_ADD_CATEGORY()+"?success="+ 1);
					}

				}
			}
			// }

		}

		return modelAndView;
	}
}
