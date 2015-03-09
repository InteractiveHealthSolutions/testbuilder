package com.ihs.springhibernate.controller.test;

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

import com.ihs.springhibernate.dao.TestDAO;
import com.ihs.springhibernate.dao.TestDAO.FetchType;
import com.ihs.springhibernate.dao.UserDAO;
import com.ihs.springhibernate.json.TestJsonWrapper;
import com.ihs.springhibernate.model.Test;
import com.ihs.springhibernate.sessioninterface.IUserSession;
import com.ihs.springhibernate.utility.Privileges;
import com.ihs.springhibernate.utility.ResourcesName;



@Controller
@RequestMapping("/test")
public class TakeTestListController
{
	@Autowired
	private IUserSession userSession;

	@RequestMapping(value = "/taketestlist", method = RequestMethod.GET)
	public ModelAndView getTestMainPage()
	{
		ModelAndView modelAndView = null;
		ResourcesName resources = new ResourcesName();

		if (userSession != null)
		{
			if (UserDAO.hasPrivilegeFor(userSession, Privileges.TEST_TAKER) == true)
			{
				modelAndView = new ModelAndView(resources.getFOLDER_TEST() + "/" + resources.getJSP_TAKE_TEST_LIST());

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
	@RequestMapping("/getTestList")
	public @ResponseBody String getTestDataByAJAX()
	{
		List<Test> loadedTestList = TestDAO.getAllTest(FetchType.LAZY);

		TestJsonWrapper testJsonObject = new TestJsonWrapper();
		testJsonObject.setiTotalDisplayRecords(loadedTestList.size());
		testJsonObject.setiTotalRecords(loadedTestList.size());
		testJsonObject.setAaData(loadedTestList);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = "";
		try
		{
			json = ow.writeValueAsString(testJsonObject);
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

		return json;
	}
}
