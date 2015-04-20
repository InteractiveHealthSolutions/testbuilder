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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.QuestionDAO.By;
import com.ihs.springhibernate.dao.AnswerTypeDAO;
import com.ihs.springhibernate.dao.QuestionTypeDAO;

import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.UserDAO;

import com.ihs.springhibernate.model.AnswerType;
import com.ihs.springhibernate.model.Privilege;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.model.QuestionData;
import com.ihs.springhibernate.model.QuestionType;

import com.ihs.springhibernate.model.Test;

import com.ihs.springhibernate.propertyeditor.Editor;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;


@Controller
@RequestMapping("/test")
public class CreateTestController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/createtest", method = RequestMethod.GET)
	public ModelAndView getCreateTest(@ModelAttribute("newTest") Test newTest)
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_CREATE_TEST());

				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);

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

	@RequestMapping(value = "/createtest", method = RequestMethod.POST)
	public ModelAndView submitTest(@ModelAttribute("newTest") @Valid Test newTest, BindingResult result)
	{
		ResourcesName resources = new ResourcesName();

		ModelAndView modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_CREATE_TEST());

		modelAndView.getModel().put("resources", resources);

		modelAndView.getModel().put("newTest", newTest);

		modelAndView.getModel().put("currentUser", userSession);

		if (result.hasErrors())
		{
			List<Question> loadedQuestionList = QuestionDAO.getAllQuestion(QuestionDAO.FetchType.EAGER);
			modelAndView.getModelMap().put("loadedQuestionList", loadedQuestionList);

			modelAndView.getModel().put("status", resources.getMESSAGE_VALIDATION_ERROR());

			return modelAndView;
		}

		if (userSession != null)
		{
			Integer newlySavedId = -1;

			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)
			{
				newlySavedId = TestDAO.save(newTest);

				if (newlySavedId != -1)
				{
					modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_TEST() + "/" + resources.getJSP_VIEW_TEST() + "?id=" + newlySavedId);
					modelAndView.getModel().put("status", resources.getMESSAGE_ADD());
				}

				// if (TestDAO.save(newTest) == true)
				// {
				// modelAndView.getModel().put("status", resources.getMESSAGE_ADD());
				// }

				else
				{
					List<QuestionType> questionTypeList = QuestionTypeDAO.getAllTypes();

					modelAndView.getModel().put("questionTypeList", questionTypeList);

					List<AnswerType> answerTypeList = AnswerTypeDAO.getAllTypes();

					modelAndView.getModel().put("answerTypeList", answerTypeList);

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


	// / AJAX call response
	@RequestMapping("/getQuestion")
	public @ResponseBody String getQuestionByAJAX(@RequestParam(value = "questionId") String questionId)
	{
		Question question = QuestionDAO.getQuestion(By.ID, questionId, QuestionDAO.FetchType.EAGER);

		// {"type": "file","dataArray": [{"data":"oop"},{"data":"12"},{"data":"we"},{"data":"343"}]} //old json pattern

		// [ {"typeid":"1" , "data":"oop"},{"typeid":"1" ,"data":"12"},{"typeid":"1", "data":"we"},{"typeid":"1" , "data":"343"}] //new json pattern

		StringBuilder stringBuilder = new StringBuilder();
		String response = "";

		// stringBuilder.append("{\"type\":");
		// stringBuilder.append("\"" + question.getQuestionType().getTypeName() + "\",");
		// stringBuilder.append("\"dataArray\":");

		stringBuilder.append("[");

		if (question != null)
		{
			for (QuestionData questionData : question.getQuestionDataList())
			{
				stringBuilder.append("{");
				stringBuilder.append("\"typeid\":");
				// stringBuilder.append("\"" + questionData.getQuestionType().getId() + "\"");
				stringBuilder.append("},");

				stringBuilder.append("\"data\":");
				stringBuilder.append("\"" + questionData.getData() + "\"");
				stringBuilder.append("},");
			}

			response = stringBuilder.substring(0, stringBuilder.length() - 1);
			response += "]}";
		}

		return response;
	}
}
