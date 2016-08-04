package com.ihs.springhibernate.utility;

/**
 * It keep all resources name in one place.
 * Resources name are like DB Name, Table name, column name, XML files name etc.
 * 
 * @author Saad
 * 
 */
public class ResourcesName
{
	/*
	 * Core purpose of this class is to keep all resources name in one place
	 * Resources name are like DB Name, Table name, column name, xml files name.
	 */

	// / Application Name ////

	private final String APPLICATION_NAME = "Test Builder";

	// / End Of Application Name ////

	// / JSP pages name ////

	private final String JSP_INDEX = "index";

	private final String JSP_HOME = "home";

	private final String JSP_MANAGE_TEST_QUESTION = "managetestquestion"; // main page for managing test and question

	private final String JSP_ADD_USER = "adduser";
	private final String JSP_DETAIL_USER = "detailuser";
	private final String JSP_EDIT_USER = "edituser";
	private final String JSP_MANAGE_USER = "manageuser";
	private final String JSP_LOGOUT_USER = "logout";

	private final String JSP_EDIT_QUESTION = "editquestion";
	private final String JSP_VIEW_QUESTION = "viewquestion";
	private final String JSP_CREATE_QUESTION = "createquestion";
	private final String JSP_ADD_CATEGORY = "addcategory";
	private final String JSP_MANAGE_QUESTION = "managequestion"; // manage questions

	
	private final String JSP_CHECK_TEST = "checktest";
	private final String JSP_MANAGE_TEST = "managetest"; // manage test
	private final String JSP_CREATE_SCHEME = "createtest";
	private final String JSP_EDIT_TEST = "edittest";
	private final String JSP_VIEW_TEST = "viewtest";
	private final String JSP_SELECT_SCHEME = "selectscheme";
	public String getJSP_SELECT_SCHEME() {
		return JSP_SELECT_SCHEME;
	}

	private final String JSP_FINALIZE_TEST = "finalizetest";
	private final String JSP_VIEWSCHEME = "viewscheme";
	private final String JSP_TAKE_TEST_LIST = "taketestlist";
	private final String JSP_TEST_BEGIN_SUMMARY = "testbeginsummary";
	private final String JSP_TEST_END_SUMMARY = "testendsummary";

	private final String JSP_TAKING_TEST = "takingtest";
	private final String JSP_TEST_CHECK_LIST = "checktestlist";
	private final String JSP_TEST_CHECK_ALL_SOL = "checktestallsol";
	



	private final String JSP_ERROR_PAGE = "errorpage";

	// / End of JSP pages name ////

	// / folder names for jsp pages////



	private final String FOLDER_USER = "user";

	private final String FOLDER_TEST = "test";

	private final String FOLDER_QUESTION = "question";

	private final String FOLDER_ERROR = "error";

	// / End offolder names for jsp pages ////

	// / DB and Tables pages name ////

	private final String DB_NAME = "test_builder";

	private final String TABLE_ANSWER = "answer";
	private final String TABLE_ANSWER_TYPE = "answer_type";

	private final String TABLE_PRIVILEGE = "privilege";
	private final String TABLE_QUESTION = "question";

	private final String TABLE_QUESTION_DATA = "question_data";
	private final String TABLE_ROLE = "role";

	private final String TABLE_ROLE_PRIVILEGE = "role_privilege";
	private final String TABLE_STUDENT = "student";

	private final String TABLE_TEST = "test";
	private final String TABLE_TEST_QUESTION = "test_question";

	private final String TABLE_USER = "user";

	// / End of DB and Tables pages name ////

	// / Messages ////

	private final String MESSAGE_ADD = "Added Successfully";
	private final String MESSAGE_UPDATE = "Update Successfully";

	private final String MESSAGE_FAIL_ADD = "Add failed";
	private final String MESSAGE_FAIL_ADD_AND_DUPLICATE = "Add failed due to same Login ID";


	private final String MESSAGE_FAIL_UPDATE = "Update failed";

	private final String MESSAGE_VALIDATION_ERROR = "Please fill required fields";

	private final String MESSAGE_NOPRIVILEGE = "Sorry! You dont have privilege for this";

	// / End of Messages ////

	// / Labels ////

	// private final String LABEL_ADD = "Added Successfully";
	// private final String MESSAGE_UPDATE = "Update Successfully";
	//
	// private final String MESSAGE_FAIL_ADD = "Add failed";
	// private final String MESSAGE_FAIL_ADD_AND_DUPLICATE = "Add failed due to same Login ID";
	//
	//
	// private final String MESSAGE_FAIL_UPDATE = "Update failed";
	//
	// private final String MESSAGE_VALIDATION_ERROR = "Please fill required fields";

	// / End of Labels ////

	public String getJSP_TEST_CHECK_ALL_SOL()
	{
		return JSP_TEST_CHECK_ALL_SOL;
	}

	public String getJSP_TEST_CHECK_LIST()
	{
		return JSP_TEST_CHECK_LIST;
	}

	public String getJSP_TAKING_TEST()
	{
		return JSP_TAKING_TEST;
	}

	public String getJSP_TEST_BEGIN_SUMMARY()
	{
		return JSP_TEST_BEGIN_SUMMARY;
	}

	public String getMESSAGE_NOPRIVILEGE()
	{
		return MESSAGE_NOPRIVILEGE;
	}
	


	public String getJSP_TAKE_TEST_LIST()
	{
		return JSP_TAKE_TEST_LIST;
	}

	public String getMESSAGE_VALIDATION_ERROR()
	{
		return MESSAGE_VALIDATION_ERROR;
	}

	public String getJSP_VIEW_QUESTION()
	{
		return JSP_VIEW_QUESTION;
	}

	public String getJSP_EDIT_TEST()
	{
		return JSP_EDIT_TEST;
	}

	// public String getJSP_VIEW_QUESTION_LIST()
	// {
	// return JSP_VIEW_QUESTION_LIST;
	// }

	public String getJSP_ERROR_PAGE()
	{
		return JSP_ERROR_PAGE;
	}

	public String getFOLDER_ERROR()
	{
		return FOLDER_ERROR;
	}

	public String getJSP_VIEW_TEST()
	{
		return JSP_VIEW_TEST;
	}

	public String getJSP_EDIT_QUESTION()
	{
		return JSP_EDIT_QUESTION;
	}

	public String getJSP_MANAGE_QUESTION()
	{
		return JSP_MANAGE_QUESTION;
	}

	public String getFOLDER_USER()
	{
		return FOLDER_USER;
	}

	public String getMESSAGE_FAIL_ADD_AND_DUPLICATE()
	{
		return MESSAGE_FAIL_ADD_AND_DUPLICATE;
	}

	public String getJSP_CREATE_QUESTION()
	{
		return JSP_CREATE_QUESTION;
	}

	public String getAPPLICATION_NAME()
	{
		return APPLICATION_NAME;
	}

	public String getJSP_INDEX()
	{
		return JSP_INDEX;
	}

	public String getJSP_HOME()
	{
		return JSP_HOME;
	}

	public String getJSP_MANAGE_USER()
	{
		return JSP_MANAGE_USER;
	}

	public String getJSP_MANAGE_TEST_QUESTION()
	{
		return JSP_MANAGE_TEST_QUESTION;
	}

	public String getJSP_CHECK_TEST()
	{
		return JSP_CHECK_TEST;
	}

	public String getFOLDER_TEST()
	{
		return FOLDER_TEST;
	}

	public String getFOLDER_QUESTION()
	{
		return FOLDER_QUESTION;
	}

	public String getJSP_MANAGE_TEST()
	{
		return JSP_MANAGE_TEST;
	}

	public String getDB_NAME()
	{
		return DB_NAME;
	}

	public String getTABLE_ANSWER()
	{
		return TABLE_ANSWER;
	}

	public String getTABLE_ANSWER_TYPE()
	{
		return TABLE_ANSWER_TYPE;
	}

	public String getTABLE_PRIVILEGE()
	{
		return TABLE_PRIVILEGE;
	}

	public String getTABLE_QUESTION()
	{
		return TABLE_QUESTION;
	}

	public String getTABLE_QUESTION_DATA()
	{
		return TABLE_QUESTION_DATA;
	}

	public String getTABLE_ROLE()
	{
		return TABLE_ROLE;
	}

	public String getTABLE_ROLE_PRIVILEGE()
	{
		return TABLE_ROLE_PRIVILEGE;
	}

	public String getTABLE_STUDENT()
	{
		return TABLE_STUDENT;
	}

	public String getTABLE_TEST()
	{
		return TABLE_TEST;
	}

	public String getTABLE_TEST_QUESTION()
	{
		return TABLE_TEST_QUESTION;
	}

	public String getTABLE_USER()
	{
		return TABLE_USER;
	}

	public String getJSP_ADD_USER()
	{
		return JSP_ADD_USER;
	}

	public String getJSP_DETAIL_USER()
	{
		return JSP_DETAIL_USER;
	}

	public String getJSP_EDIT_USER()
	{
		return JSP_EDIT_USER;
	}

	public String getMESSAGE_ADD()
	{
		return MESSAGE_ADD;
	}

	public String getMESSAGE_UPDATE()
	{
		return MESSAGE_UPDATE;
	}

	public String getMESSAGE_FAIL_ADD()
	{
		return MESSAGE_FAIL_ADD;
	}

	public String getMESSAGE_FAIL_UPDATE()
	{
		return MESSAGE_FAIL_UPDATE;
	}

	public String getJSP_TEST_END_SUMMARY()
	{
		return JSP_TEST_END_SUMMARY;
	}

	public String getJSP_LOGOUT_USER() {
		return JSP_LOGOUT_USER;
	}

	public String getJSP_ADD_CATEGORY() {
		return JSP_ADD_CATEGORY;
	}

	public String getJSP_CREATE_SCHEME() {
		return JSP_CREATE_SCHEME;
	}

	public String getJSP_FINALIZE_TEST() {
		return JSP_FINALIZE_TEST;
	}

	public String getJSP_VIEWSCHEME() {
		return JSP_VIEWSCHEME;
	}

}
