package com.ihs.springhibernate.controller.question;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ihs.springhibernate.dao.AnswerTypeDAO;
import com.ihs.springhibernate.dao.CategoryTypeDAO;
import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.dao.QuestionDataDAO;
import com.ihs.springhibernate.dao.QuestionDataDAO.By;
import com.ihs.springhibernate.dao.QuestionTypeDAO;
import com.ihs.springhibernate.model.AnswerType;
import com.ihs.springhibernate.model.CategoryType;
import com.ihs.springhibernate.model.Privilege;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.model.QuestionType;
import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.KeyValue;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;
import com.ihs.springhibernate.validator.QuestionValidator;

@Controller
@RequestMapping("/question")
public class EditQuestionController {

	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/editquestion", method = RequestMethod.GET)
	public ModelAndView getEditQuestion(@RequestParam("id") String questionId) {
		ResourcesName resources = new ResourcesName();
		ModelAndView modelAndView = null;

		if (userSession.getName() != null) {
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) {
				Question editQuestion = QuestionDAO.getQuestion(
						QuestionDAO.By.ID, questionId,
						QuestionDAO.FetchType.EAGER);

				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION()
						+ "/" + resources.getJSP_EDIT_QUESTION());

				modelAndView.getModel().put("editQuestion", editQuestion);

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);

				List<QuestionType> questionTypeList = QuestionTypeDAO
						.getAllTypes();

				modelAndView.getModel().put("questionTypeList",
						questionTypeList);

				List<CategoryType> categoryType = CategoryTypeDAO
						.getCategoryTypes();

				modelAndView.getModel().put("categoryType", categoryType);
			}

			else {
				// modelAndView = new ModelAndView(resources.getJSP_INDEX());
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

	// @RequestMapping(value = "/getImage/{imageId}")
	// @ResponseBody
	// public byte[] getImage(@PathVariable Integer imageId)
	// {
	// QuestionData questionData =
	// QuestionDataDAO.getQuestionData(QuestionDataDAO.By.ID,
	// String.valueOf(imageId), QuestionDataDAO.FetchType.EAGER);
	// // Image image = new //obtain Image instance by id somehow from
	// DAO/Hibernate
	//
	// return questionData.getFile().getBytes();
	// }

	@RequestMapping(value = "/editquestion", method = RequestMethod.POST)
	public ModelAndView submitQuestion(
			@ModelAttribute("editQuestion") @Valid Question editQuestion,
			BindingResult result) {

		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(
				resources.getFOLDER_QUESTION() + "/"
						+ resources.getJSP_EDIT_QUESTION());

		/*
		 * If user is not test maker then redirect him to login page
		 */

		if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == false) {
			modelAndView = new ModelAndView("redirect:/"
					+ resources.getJSP_INDEX());
			return modelAndView;
		}

		editQuestion = QuestionDAO.removeNullQuestionData(editQuestion);

		modelAndView.getModel().put("editQuestion", editQuestion);

		modelAndView.getModel().put("currentUser", userSession);

		modelAndView.getModel().put("resources", resources);

		QuestionValidator questionValidator = new QuestionValidator();
		questionValidator.validate(editQuestion, result);

		if (result.hasErrors()) {
			List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

			modelAndView.getModel().put("questionTypeList", questionTypeList);

			modelAndView.getModel().put("status",
					resources.getMESSAGE_VALIDATION_ERROR());

			return modelAndView;
		}

		if (userSession != null) {
			Privilege privilege = new Privilege();

			privilege.setId(Privileges.TEST_MAKER.getRoleId());

			if (QuestionDAO.update(editQuestion) == true) {
				modelAndView = new ModelAndView("redirect:/"
						+ resources.getFOLDER_QUESTION() + "/"
						+ resources.getJSP_VIEW_QUESTION() + "?id="
						+ editQuestion.getId());
				modelAndView.getModel().put("status",
						resources.getMESSAGE_UPDATE());
			}

			else {
				List<QuestionType> questionTypeList = QuestionTypeDAO
						.getAllTypes();

				modelAndView.getModel().put("questionTypeList",
						questionTypeList);

				List<CategoryType> categoryType = CategoryTypeDAO
						.getCategoryTypes();

				modelAndView.getModel().put("categoryType", categoryType);

				modelAndView.getModel().put("status",
						resources.getMESSAGE_FAIL_ADD());
			}
		}

		return modelAndView;
	}
}
