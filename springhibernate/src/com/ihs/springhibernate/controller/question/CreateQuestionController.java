package com.ihs.springhibernate.controller.question;

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

import com.ihs.springhibernate.dao.AnswerTypeDAO;
import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.QuestionTypeDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.AnswerType;
import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.model.Privilege;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;
import com.ihs.springhibernate.validator.QuestionValidator;

@Controller
@RequestMapping("/question")
public class CreateQuestionController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/createquestion", method = RequestMethod.GET)
	public ModelAndView getMakeQuestion(@ModelAttribute("newQuestion") Question newQuestion)
	{
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession.getName() != null)
		{

			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION() + "/" + resources.getJSP_CREATE_QUESTION());		

				modelAndView.getModel().put("newQuestion", newQuestion);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

				modelAndView.getModel().put("questionTypeList", questionTypeList);

				List<AnswerType> answerTypeList = AnswerTypeDAO.getAllTypes();

				modelAndView.getModel().put("answerTypeList", answerTypeList);
				
				List<CategoryType> categoryType = CategoryTypeDAO.getCategoryTypes();
				
				modelAndView.getModel().put("categoryType", categoryType);
			}

			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
				modelAndView.getModel().put("loginUser", new User());
			}
		}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/createquestion", method = RequestMethod.POST)
	public ModelAndView submitQuestion(@ModelAttribute("newQuestion") @Valid Question newQuestion, BindingResult result, HttpServletRequest httpServletRequest)
	{
		String s = 	httpServletRequest.getParameter("questionDataList[1].correct");
		
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_QUESTION() + "/" + resources.getJSP_CREATE_QUESTION());

		newQuestion = QuestionDAO.removeNullQuestionData(newQuestion);

		QuestionValidator questionValidator = new QuestionValidator();
		questionValidator.validate(newQuestion, result);

		if (result.hasErrors())
		{
			modelAndView = new ModelAndView(resources.getFOLDER_QUESTION() + "/" + resources.getJSP_CREATE_QUESTION());

			modelAndView.getModel().put("newQuestion", newQuestion);

			modelAndView.getModel().put("currentUser", userSession);

			modelAndView.getModel().put("resources", resources);

			List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

			modelAndView.getModel().put("questionTypeList", questionTypeList);
			
			List<CategoryType> categoryType = CategoryTypeDAO.getCategoryTypes();
			
			modelAndView.getModel().put("categoryType", categoryType);

			List<AnswerType> answerTypeList = AnswerTypeDAO.getAllTypes();

			modelAndView.getModel().put("answerTypeList", answerTypeList);

			modelAndView.getModel().put("status", resources.getMESSAGE_VALIDATION_ERROR());

			return modelAndView;
		}

		if (userSession != null)
		{
			Integer newlySavedId = -1;
			
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)		
			{			
				newlySavedId = QuestionDAO.save(newQuestion);
				
				if (newlySavedId != -1)
				{					
					modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_QUESTION() + "/" + resources.getJSP_VIEW_QUESTION() + "?id=" + newlySavedId);
					modelAndView.getModel().put("status", resources.getMESSAGE_ADD());
				}


//				if (QuestionDAO.save(newQuestion) == true)
//				{
//					modelAndView = new ModelAndView(resources.getFOLDER_QUESTION() + "/" + resources.getJSP_CREATE_QUESTION());
//
//					modelAndView.getModel().put("status", resources.getMESSAGE_ADD());
//				}

				else
				{
					modelAndView.getModel().put("newQuestion", newQuestion);

					modelAndView.getModel().put("currentUser", userSession);

					modelAndView.getModel().put("resources", resources);

					List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

					modelAndView.getModel().put("questionTypeList", questionTypeList);
					
					List<CategoryType> categoryType = CategoryTypeDAO.getCategoryTypes();
					
					modelAndView.getModel().put("categoryType", categoryType);

					List<AnswerType> answerTypeList = AnswerTypeDAO.getAllTypes();

					modelAndView.getModel().put("answerTypeList", answerTypeList);

					modelAndView.getModel().put("status", resources.getMESSAGE_FAIL_ADD());
				}
			}
		}

		return modelAndView;
	}
}
