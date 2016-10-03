package com.ihs.mcqbuilder.controller.test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.SchemeDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.model.CategoryType;
import com.ihs.mcqbuilder.model.Scheme;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;


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
				
				Scheme newScheme = new Scheme();
				modelAndView.getModel().put("newScheme", newScheme);
				
				
				List<Scheme> schemeListAll = SchemeDAO.getAllSchemes();
				modelAndView.getModel().put("schemeListAll", schemeListAll);
				
				Collections.sort(schemeListAll, new Comparator<Scheme>() {
					@Override
					public int compare(final Scheme object1,
							final Scheme object2) {
						return object1.getName().toLowerCase()
								.compareTo(object2.getName().toLowerCase());
					}
				});
				
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
