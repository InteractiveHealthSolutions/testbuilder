package com.ihs.mcqbuilder.controller.question;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.mcqbuilder.dao.AnswerTypeDAO;
import com.ihs.mcqbuilder.dao.CategoryTypeDAO;
import com.ihs.mcqbuilder.dao.QuestionDAO;
import com.ihs.mcqbuilder.dao.QuestionTypeDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.model.AnswerType;
import com.ihs.mcqbuilder.model.CategoryType;
import com.ihs.mcqbuilder.model.Question;
import com.ihs.mcqbuilder.model.QuestionType;
import com.ihs.mcqbuilder.model.User;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;
import com.ihs.mcqbuilder.validator.QuestionValidator;

@Controller
@RequestMapping("/question")
public class CreateQuestionController {
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/createquestion", method = RequestMethod.GET)
	public ModelAndView getMakeQuestion(
			@ModelAttribute("newQuestion") Question newQuestion) {
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession.getName() != null) {

			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION()
						+ "/" + resources.getJSP_CREATE_QUESTION());

				modelAndView.getModel().put("newQuestion", newQuestion);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				List<QuestionType> questionTypeList = QuestionTypeDAO
						.getAllTypes();

				modelAndView.getModel().put("questionTypeList",
						questionTypeList);

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

				modelAndView.getModel().put("categoryType", categoryType);
			}

			else {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getJSP_INDEX());
				modelAndView.getModel().put("loginUser", new User());
			}
		}

		else {
			modelAndView = new ModelAndView("redirect:/"
					+ resources.getJSP_INDEX());
		}

		return modelAndView;
	}

	@RequestMapping(value = "/createquestion", method = RequestMethod.POST)
	public ModelAndView submitQuestion(
			@ModelAttribute("newQuestion") @Valid Question newQuestion,
			BindingResult result, HttpServletRequest httpServletRequest) {
		String s = httpServletRequest
				.getParameter("questionDataList[1].correct");

		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(
				resources.getFOLDER_QUESTION() + "/"
						+ resources.getJSP_CREATE_QUESTION());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newQuestion", newQuestion);

		modelAndView.getModel().put("currentUser", userSession);

		newQuestion = QuestionDAO.removeNullQuestionData(newQuestion);

		/*
		 * QuestionValidator questionValidator = new QuestionValidator();
		 * questionValidator.validate(newQuestion, result);
		 * 
		 * if (result.hasErrors()) { modelAndView = new
		 * ModelAndView(resources.getFOLDER_QUESTION() + "/" +
		 * resources.getJSP_CREATE_QUESTION());
		 * 
		 * modelAndView.getModel().put("newQuestion", newQuestion);
		 * 
		 * modelAndView.getModel().put("currentUser", userSession);
		 * 
		 * modelAndView.getModel().put("resources", resources);
		 * 
		 * List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();
		 * 
		 * modelAndView.getModel().put("questionTypeList", questionTypeList);
		 * 
		 * List<CategoryType> categoryType = CategoryTypeDAO
		 * .getCategoryTypes();
		 * 
		 * modelAndView.getModel().put("categoryType", categoryType);
		 * 
		 * List<AnswerType> answerTypeList = AnswerTypeDAO.getAllTypes();
		 * 
		 * modelAndView.getModel().put("answerTypeList", answerTypeList);
		 * 
		 * modelAndView.getModel().put("status",
		 * resources.getDESCRIPTION_ERROR());
		 * 
		 * return modelAndView; }
		 */

		if (userSession.getName() != null) {

			boolean repeat = true;
			List<Question> questionList = QuestionDAO
					.getAllQuestion(QuestionDAO.FetchType.EAGER);
			for (Question ques : questionList) {
				if (ques.getTitle().toLowerCase()
						.equals(newQuestion.getTitle().toLowerCase())) {
					repeat = false;
				}
			}

			if (StringUtils.isBlank(newQuestion.getTitle())
					|| StringUtils.isBlank(newQuestion.getDescription())) {

				modelAndView.getModel()
						.put("status", "Required fields missing");

				List<QuestionType> questionTypeList = QuestionTypeDAO
						.getAllTypes();

				modelAndView.getModel().put("questionTypeList",
						questionTypeList);

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

				modelAndView.getModel().put("categoryType", categoryType);
			}

			else if (repeat == false) {
				modelAndView.getModel().put("status", "Question title exists");

				List<QuestionType> questionTypeList = QuestionTypeDAO
						.getAllTypes();

				modelAndView.getModel().put("questionTypeList",
						questionTypeList);

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

				modelAndView.getModel().put("categoryType", categoryType);
			}

			else {

				Integer newlySavedId = -1;

				if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
					newlySavedId = QuestionDAO.save(newQuestion);

					if (newlySavedId != -1) {
						modelAndView = new ModelAndView("redirect:/"
								+ resources.getFOLDER_QUESTION() + "/"
								+ resources.getJSP_VIEW_QUESTION() + "?id="
								+ newlySavedId);
						modelAndView.getModel().put("status",
								resources.getMESSAGE_ADD());
					}

					else {

						List<QuestionType> questionTypeList = QuestionTypeDAO
								.getAllTypes();

						modelAndView.getModel().put("questionTypeList",
								questionTypeList);

						List<CategoryType> categoryType = CategoryTypeDAO
								.getCategoryTypes();

						modelAndView.getModel().put("categoryType",
								categoryType);

						List<AnswerType> answerTypeList = AnswerTypeDAO
								.getAllTypes();

						modelAndView.getModel().put("answerTypeList",
								answerTypeList);

						modelAndView.getModel().put("status",
								resources.getMESSAGE_FAIL_ADD());
					}
				}
			}

		}
		return modelAndView;

	}
}
