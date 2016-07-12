<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<!-- Header tag is called -->
<t:header />
</head>

<body>

  <div class="container">

    <div class="row clearfix">
      <div class="col-md-12 column">
        <h3 class="text-left">Hi ${currentUser.getName()}</h3>
      </div>
    </div>

    <div class="row clearfix">

      <!-- Menu tag is called -->
      <t:menu user="${currentUser}" />

      <div class="col-md-9 column">

        <h3>Manage Users</h3>

        <c:url var="createUrl" value="${resources.JSP_ADD_USER}">
        </c:url>
        <h4>
          <a href="<c:out value="${createUrl}"/>">Create New User</a>
        </h4>
        <table border="1px" class="table table-bordered table-hover">
          <tr>
            <th>Name</th>
            <th>Login ID</th>
            <th>Password</th>
            <%--            <th colspan="2">Action</th> --%>
            <th>Action</th>
          </tr>

          <c:forEach var="user" items="${userList}">
            <tr>
              <td>${user.getName()}</td>
              <td>${user.getLogin_Id()}</td>
<%--               <td>${user.getPassword()}</td> --%>

              <c:url var="editUrl" value="${resources.JSP_EDIT_USER}">
                <c:param name="id" value="${user.getId()}" />
              </c:url>

              <td><a href="<c:out value="${editUrl}"/>">Edit</a> | <c:url var="detailUrl"
               value="${resources.JSP_DETAIL_USER}">
                  
                  <c:param name="id" value="${user.getId()}" />
                </c:url> <a href="<c:out value="${detailUrl}"/>">Details</a>
                
              </td>

              <%--               <td><a href="<c:out value="${detailUrl}"/>">&nbsp;Details</a></td> --%>
            </tr>
          </c:forEach>
        </table>


      </div>

    </div>

    <!-- Footer tag is called -->
    <t:footer />

  </div>

</body>
</html>