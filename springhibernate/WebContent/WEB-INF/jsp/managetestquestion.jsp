<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${resources.APPLICATION_NAME}</title>
</head>
<body>
  <ul>
    <c:forEach var="right" items="${currentUser.getRole().getPrivilegeList()}">

      <c:if test="${ right.getId() == 1}">
        <!-- if account management -->

        <c:url var="manageAccount" value="${resources.JSP_MANAGE_USER}">
        
        </c:url>

        <li><a href="<c:out value="${manageAccount}"/>">${right.getRight()}</a></li>
      </c:if>    

      <c:if test="${right.getId() == 3}">
        <!-- if test checker -->

        <c:url var="checkTest" value="${resources.JSP_CHECK_TEST}">
        </c:url>

        <li><a href="<c:out value="${checkTest}"/>">${right.getRight()}</a></li>
      </c:if>

      <c:if test="${ right.getId() == 4}">
        <!-- if test taker -->

        <c:url var="takeTest" value="${resources.JSP_CHECK_TEST}">
        </c:url>
        <li><a href="<c:out value="${takeTest}"/>">${right.getRight()}</a></li>
      </c:if>

    </c:forEach>
  </ul>



  <h2>Manage Tests and Questions</h2>

  <div align="left">Welcome ${currentUser.getName()}</div>

  <ul>
 
    <c:url var="manageTest" value="${resources.FOLDER_TEST}/${resources.getJSP_MANAGE_TEST()}">
    </c:url>
    <li><a href="<c:out value="${manageTest}"/>">Manage Test</a></li>

    <c:url var="manageQuestion" value="${resources.FOLDER_QUESTION}/${resources.JSP_MANAGE_QUESTION}">
    </c:url>
    <li><a href="<c:out value="${manageQuestion}"/>">Manage Question</a></li>

  </ul>
</body>
</html>