package com.ihs.mcqbuilder.controller.test;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ihs.mcqbuilder.dao.TestDAO;
import com.ihs.mcqbuilder.dao.UserDAO;
import com.ihs.mcqbuilder.dao.TestDAO.FetchType;
import com.ihs.mcqbuilder.json.wrapper.SolvedTestJW;
import com.ihs.mcqbuilder.model.Test;
import com.ihs.mcqbuilder.sessioninterface.IUserSession;
import com.ihs.mcqbuilder.utility.Privileges;
import com.ihs.mcqbuilder.utility.ResourcesName;

@Controller
@RequestMapping("/test")
public class CheckTestListController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/checktestlist", method = RequestMethod.GET)
	public ModelAndView getTestMainPage()
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_CHECKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TEST_CHECK_LIST());

				modelAndView.getModelMap().put("currentUser", userSession);
				modelAndView.getModelMap().put("resources", resources);
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/" + resources.getFOLDER_ERROR() + "/" +
						resources.getJSP_ERROR_PAGE() + "?message=" + resources.getMESSAGE_NOPRIVILEGE());
			}
		}

		else
		{
			modelAndView = new ModelAndView(new RedirectView(resources.getJSP_INDEX()));
		}

		return modelAndView;
	}

	// / AJAX call response
	@RequestMapping("/getCheckTestList")
	public @ResponseBody
	String getCheckTestListByAJAX()
	{
		String json = "";

		/*
		 * Have to load test which are not taken by that particular User
		 */

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_CHECKER) == true)
			{
				List<Test> loadedTestList = TestDAO.getAllTest(FetchType.LAZY);

				SolvedTestJW solvedTestJsonObject = new SolvedTestJW();
				solvedTestJsonObject.setiTotalDisplayRecords(loadedTestList.size());
				solvedTestJsonObject.setiTotalRecords(loadedTestList.size());
				solvedTestJsonObject.setAaData(loadedTestList);

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

				try
				{
					json = ow.writeValueAsString(solvedTestJsonObject);
				}
				catch (JsonGenerationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (JsonMappingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return json;
	}
}
