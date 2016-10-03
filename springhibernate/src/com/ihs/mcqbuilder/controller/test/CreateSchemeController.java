package com.ihs.mcqbuilder.controller.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.CategoryTypeDAO;
import com.ihs.mcqbuilder.dao.SchemeCategoryDAO;
import com.ihs.mcqbuilder.dao.SchemeDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.model.CategoryType;
import com.ihs.mcqbuilder.model.Scheme;
import com.ihs.mcqbuilder.model.SchemeCategory;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class CreateSchemeController {

	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/createtestscheme", method = RequestMethod.GET)
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
				
				Collections.sort(categoryType, new Comparator<CategoryType>() {
					@Override
					public int compare(final CategoryType object1,
							final CategoryType object2) {
						return object1.getTypeName().toLowerCase()
								.compareTo(object2.getTypeName().toLowerCase());
					}
				});
				
				modelAndView.getModelMap().put("categoryType", categoryType);
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

	@RequestMapping(value = "/createtestscheme", method = RequestMethod.POST)
	ModelAndView submitTest(
			@ModelAttribute("newScheme") @Valid Scheme newScheme,
			BindingResult result, HttpServletRequest request) {
		ResourcesName resources = new ResourcesName();
		boolean exists = true;

		List<CategoryType> categories = CategoryTypeDAO.getCategoryTypes();
		LinkedHashMap<Integer, Integer> percentageList = new LinkedHashMap<Integer, Integer>();
		List<Integer> labelList = new ArrayList<Integer>();

		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST()
				+ "/" + resources.getJSP_CREATE_SCHEME());

		modelAndView.getModelMap().put("resources", resources);

		modelAndView.getModelMap().put("newScheme", newScheme);

		modelAndView.getModelMap().put("currentUser", userSession);
		
		List<Scheme> schemeList = SchemeDAO.getAllSchemes();

		for (Scheme schemeType : schemeList) {
			if (newScheme.getName().toLowerCase()
					.equals(schemeType.getName().toLowerCase())) {
				exists = false;
			}
		}

		if (exists == false) {
			modelAndView.getModelMap().put("status", "Scheme name already exists");

			Collections.sort(categories, new Comparator<CategoryType>() {
				@Override
				public int compare(final CategoryType object1,
						final CategoryType object2) {
					return object1.getTypeName().toLowerCase()
							.compareTo(object2.getTypeName().toLowerCase());
				}
			});
			
			modelAndView.getModelMap().put("categoryType", categories);
		}

		else {

			String parameter = request.getParameter("type");

			if (userSession.getName() != null) {

				Integer newlySavedId = -1;
				if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
					newlySavedId = SchemeDAO.saveScheme(newScheme);

					for (CategoryType cat : categories) {
						String a = request.getParameter("percentage"
								+ cat.getId());
						if (a == null) {
						} else {
							percentageList
									.put(cat.getId(), Integer.parseInt(a));
							labelList.add(cat.getId());
						}
					}

					List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
					List<Scheme> schemeIdList;
					Scheme schemeID;
					int size = labelList.size();
					SchemeCategory obj;

					for (int i = 0; i < size; i++) {
						obj = new SchemeCategory();
						obj.setCategory_id(labelList.get(i));
						obj.setWeightage(percentageList.get(labelList.get(i)));
						schemeIdList = new ArrayList<Scheme>();
						schemeID = new Scheme();
						schemeIdList = SchemeDAO.getSchemeData(newScheme
								.getName());
						schemeID = schemeIdList.get(0);
						obj.setScheme_id(schemeID.getId());

						schemeCategoryList.add(obj);
					}

					int newSaved = SchemeCategoryDAO
							.saveSchemeCategory(schemeCategoryList);

					if (newlySavedId != -1 && newSaved != -1
							&& parameter.equals("save")) {
						modelAndView = new ModelAndView("redirect:/"
								+ resources.getFOLDER_TEST() + "/"
								+ resources.getJSP_VIEWSCHEME() + "?id=1");
					}

					else if (newlySavedId != -1 && newSaved != -1
							&& parameter.equals("generate")) {
						modelAndView = new ModelAndView("redirect:/"
								+ resources.getFOLDER_TEST() + "/"
								+ resources.getJSP_FINALIZE_TEST() + "?id="
								+ newlySavedId);
					}

					else {
						modelAndView.getModelMap().put("status",
								resources.getMESSAGE_FAIL_ADD());
					}
				}

			}
		}
		return modelAndView;
	}
}
