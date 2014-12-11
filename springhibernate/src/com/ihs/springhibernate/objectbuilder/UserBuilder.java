package com.ihs.springhibernate.objectbuilder;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.ihs.springhibernate.model.User;
import com.ihs.springhibernate.sessioninterface.IUserSession;

public class UserBuilder
{
	@Autowired
	private IUserSession userSession;

	/**
	 * It creates new instance of user
	 * 
	 * @return new instance of user
	 */
	public static User buildUser()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		User user = (User) context.getBean("user");

		return user;
	}

	/**
	 * It creates instance of user realated to HTTP session
	 * 
	 * @return
	 */
	public static User getSessionUser()
	{

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		User user = (User) context.getBean("sessionUser");

		return user;
	}
}
