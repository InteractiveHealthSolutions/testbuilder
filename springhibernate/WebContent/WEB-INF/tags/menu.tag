<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag import="com.ihs.springhibernate.model.User"%>
<%-- <%@attribute name="user" required="true" type="com.ihs.springhibernate.model.User"%> --%>
<%@attribute name="user" required="true" type="com.ihs.springhibernate.sessioninterface.IUserSession"%>
<%@attribute name="Resource" required="false" type="com.ihs.springhibernate.utility.ResourcesName"%>

<!-- <div class="col-md-4 column"> -->
<div class="col-md-3 column">
  <ul class="nav nav-pills nav-stacked">


    <!--         <li class="active"> -->
    <!--           <a href="#">Home</a> -->
    <!--         </li> -->
    <!--         <li> -->
    <!--           <a href="#">Profile</a> -->
    <!--         </li> -->

    <jsp:useBean id="Resource" class="com.ihs.springhibernate.utility.ResourcesName" />

    <c:forEach var="right" items="${user.getRole().getPrivilegeList()}">

      <c:if test="${ right.getId() == 1}">
        <!--        if account management -->

        <c:url var="manageUser" value="/${Resource.FOLDER_USER}/${Resource.JSP_MANAGE_USER}">
        </c:url>

        <li><a href="<c:out value="${manageUser}"/>">Account Management</a></li>
      </c:if>

      <c:if test="${right.getId() == 2 }">
        <!--        if test maker -->

        <c:url var="manageQuestion" value="/${Resource.FOLDER_QUESTION}/${Resource.JSP_MANAGE_QUESTION}">
        </c:url>


        <li><a href="<c:out value="${manageQuestion}"/>">Manage Question</a></li>
        
        
         <c:url var="createQuestion" value="/${Resource.FOLDER_QUESTION}/${Resource.JSP_CREATE_QUESTION}">
        </c:url>
        
        <li>
          <a href="<c:out value="${createQuestion}"/>">Create New Question</a>
        </li>
        
        <c:url var="createCategory" value="/${Resource.FOLDER_QUESTION}/${Resource.JSP_ADD_CATEGORY}">
        </c:url>
        <li>
          <a href="<c:out value="${createCategory}"/>">Add New Category</a>
        </li>
        
        <c:url var="manageTest" value="/${Resource.FOLDER_TEST}/${Resource.JSP_MANAGE_TEST}">
        </c:url>

        <li><a href="<c:out value="${manageTest}"/>">Manage Test</a></li>
        
        <c:url var="createTestScheme" value="/${Resource.FOLDER_TEST}/${Resource.JSP_CREATE_SCHEME}">
        </c:url>

        <li><a href="<c:out value="${createTestScheme}"/>">Create Test Scheme</a></li>
        
          <c:url var="selectScheme" value="/${Resource.FOLDER_TEST}/${Resource.JSP_SELECT_SCHEME}">
        </c:url>

        <li><a href="<c:out value="${selectScheme}"/>">Generate Test</a></li>



      </c:if>

      <c:if test="${right.getId() == 3}">
        <!--        if test checker -->

        <c:url var="checkTest" value="/${Resource.FOLDER_TEST}/${Resource.JSP_TEST_CHECK_LIST}">
        </c:url>

        <li><a href="<c:out value="${checkTest}"/>">Check Test</a></li>
      </c:if>

      <c:if test="${ right.getId() == 4}">
        <!--        if test taker -->

        <c:url var="takeTest" value="/${Resource.FOLDER_TEST}/${Resource.JSP_TAKE_TEST_LIST}">
        </c:url>
        <li><a href="<c:out value="${takeTest}"/>">Take Test</a></li>
      </c:if>

    </c:forEach>
    
   <c:url var="logoutUser" value="/${Resource.FOLDER_USER}/${Resource.JSP_LOGOUT_USER}">
        </c:url>

        <li><a href="<c:out value="${logoutUser}"/>">Logout</a></li>

  </ul>
</div>




