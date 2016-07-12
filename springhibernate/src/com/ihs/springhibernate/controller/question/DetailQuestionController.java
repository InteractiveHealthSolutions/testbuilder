package com.ihs.springhibernate.controller.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.UserDAO;

import com.ihs.springhibernate.model.Question;

import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/question")
public class DetailQuestionController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/viewquestion", method = RequestMethod.GET)
	public ModelAndView getQuestion(@RequestParam("id") String questionId, @RequestParam(value = "status", required = false) String status)
	{
		Question question = QuestionDAO.getQuestion(QuestionDAO.By.ID, questionId, QuestionDAO.FetchType.EAGER);

		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

	if(userSession.getName() != null){
		if (userSession.getRole().getId() != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true) //
			{
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION() + "/" + resources.getJSP_VIEW_QUESTION());
				modelAndView.getModelMap().put("detailQuestion", question);
				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
				modelAndView.getModel().put("status", status);
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
				// modelAndView.getModel().put("status", status);
			}
		}
	}

		else
		{
			modelAndView = new ModelAndView("redirect:/" + resources.getJSP_INDEX());
		}

		return modelAndView;
	}

}
