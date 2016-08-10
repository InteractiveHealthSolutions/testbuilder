package com.ihs.springhibernate.controller.test;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.propertyeditor.Editor;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class EditTestController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/edittest", method = RequestMethod.GET)
	public ModelAndView getCreateTest(@RequestParam("id") String testId)
	{
		ResourcesName resources = new ResourcesName();	
		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_EDIT_TEST());
		modelAndView.getModelMap().put("currentUser", userSession);
		modelAndView.getModelMap().put("resources", resources);

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)
			{
				Test editTest = TestDAO.getTest(TestDAO.By.ID, testId, TestDAO.FetchType.EAGER);

				modelAndView.getModel().put("editTest", editTest);

				List<Question> loadedQuestionList = QuestionDAO.getAllQuestion(QuestionDAO.FetchType.EAGER);
				modelAndView.getModelMap().put("loadedQuestionList", loadedQuestionList);
			}
			
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/edittest", method = RequestMethod.POST)
	public ModelAndView submitQuestion(@ModelAttribute("editTest") @Valid Test editTest, BindingResult result)
	{
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getJSP_EDIT_TEST());

		modelAndView.getModel().put("editTest", editTest);

		modelAndView.getModel().put("currentUser", userSession);

		modelAndView.getModel().put("resources", resources);

		if (result.hasErrors())
		{
			List<Question> loadedQuestionList = QuestionDAO.getAllQuestion(QuestionDAO.FetchType.EAGER);
			modelAndView.getModelMap().put("loadedQuestionList", loadedQuestionList);

			modelAndView.getModel().put("status", resources.getMESSAGE_VALIDATION_ERROR());

			return modelAndView;
		}

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)			
			{
				if (TestDAO.update(editTest) == true)
				{					
					modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_TEST() + "/" + resources.getJSP_VIEW_TEST() + "?id=" + editTest.getId());
					modelAndView.getModel().put("status", resources.getMESSAGE_UPDATE());
				}

				else
				{
					List<Question> loadedQuestionList = QuestionDAO.getAllQuestion(QuestionDAO.FetchType.EAGER);
					modelAndView.getModelMap().put("loadedQuestionList", loadedQuestionList);

					modelAndView.getModel().put("status", resources.getMESSAGE_FAIL_ADD());
				}
			}
		}

		return modelAndView;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Question.class, new Editor());
	}
}
