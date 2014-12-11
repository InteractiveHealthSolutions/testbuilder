package com.ihs.springhibernate.controller.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ihs.springhibernate.dao.QuestionDAO;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.model.Question;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;

@Controller
@RequestMapping("/question")
public class ManageQuestionController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/managequestion", method = RequestMethod.GET)
	public ModelAndView getManageQuestion()
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_MAKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_QUESTION() + "/" + resources.getJSP_MANAGE_QUESTION());		

				modelAndView.getModel().put("currentUser", userSession);

				modelAndView.getModel().put("resources", resources);
				
				List<Question> questionList = QuestionDAO.getAllQuestion(QuestionDAO.FetchType.EAGER);

				modelAndView.getModelMap().put("questionList", questionList);
			
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
}
