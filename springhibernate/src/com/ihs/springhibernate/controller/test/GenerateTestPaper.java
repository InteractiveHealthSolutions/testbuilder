package com.ihs.springhibernate.controller.test;

import java.util.ArrayList;
import java.math.*;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.SchemeCategoryDAO;
import com.ihs.springhibernate.dao.SchemeDAO;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.Scheme;
import com.ihs.springhibernate.model.SchemeCategory;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class GenerateTestPaper {
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/finalizetest", method = RequestMethod.GET)
	public ModelAndView getSchemeData(@ModelAttribute("newTest") Test newTest, HttpServletRequest request) {
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();
		List<List<Question>> questionCollection = new ArrayList<List<Question>>();
//		List<Question> questionList = new ArrayList<Question>();
		int totalQuestions = 0;
		int categoryId = 0;
		double calculateVal = 0;
		int weightage = 0;
		int questionAmount = 0;

		String id = request.getParameter("id");
		Integer schemeId = Integer.parseInt(id);

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_TEST()
						+ "/" + resources.getJSP_FINALIZE_TEST());
				
			    //Test newTest = new Test();
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				modelAndView.getModelMap().put("newTest", newTest);
				modelAndView.getModelMap().put("schemeId", schemeId);
//				modelAndView.getModelMap().put("questionList", questionList);
				
				List<Scheme> schemeList = new ArrayList<Scheme>();
				schemeList = SchemeDAO.getSchemeById(schemeId);

				List<SchemeCategory> schemeCategoryList = new ArrayList<SchemeCategory>();
				schemeCategoryList = SchemeCategoryDAO
						.getSchemeCategory(schemeId);

				totalQuestions = schemeList.get(0).getTotalQuestions();

				HashMap<Integer, Integer> categoryWeightageList = new HashMap<Integer, Integer>();

				modelAndView.getModelMap().put("schemeList", schemeList);
				modelAndView.getModelMap().put("schemeCategoryList",
						schemeCategoryList);

				for (SchemeCategory sc : schemeCategoryList) {
					categoryId = sc.getCategory_id();
					weightage = sc.getWeightage();
					calculateVal = weightage / 100.0;
					BigDecimal b = BigDecimal.valueOf(calculateVal
							* totalQuestions);
					int scale = 0;
					BigDecimal b1 = b.setScale(scale, RoundingMode.HALF_UP);
					questionAmount = Integer.parseInt(b1.toString());
					categoryWeightageList.put(categoryId, questionAmount);
				}

				questionCollection = QuestionDAO
						.getQuestionForTest(categoryWeightageList);
				modelAndView.getModelMap().put("questionCollection",
						questionCollection);
			}

			else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
			}
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/finalizetest", method = RequestMethod.POST)
	ModelAndView submitTest(
			@ModelAttribute("newTest") @Valid Test newTest,
			BindingResult result, HttpServletRequest request) {
			
		ResourcesName resources = new ResourcesName();
	
		
		ModelAndView modelAndView = new ModelAndView("redirect:/" + resources.getJSP_HOME());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newTest", newTest);

		modelAndView.getModel().put("currentUser", userSession);


		if (userSession.getName() != null) {
		
			Integer newlySavedId = -1;
			newlySavedId = TestDAO.save(newTest);
			
			if(newlySavedId == -1){
				modelAndView.getModel().put("status",
						resources.getMESSAGE_FAIL_ADD());
			}
		}
		return modelAndView;
	
	}
	
}
