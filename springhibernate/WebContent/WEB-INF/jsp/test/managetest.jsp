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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

        <h3>Manage Test</h3>
        
        <div>
          <table border="1px" class="table table-bordered">
            <tr>
              <th>Test Name</th>
              <th>Scheme Name</th>
              <th>Created on</th>
              <th>Action</th>
            </tr>

            <c:forEach var="test" items="${testList}">
              <tr>
                <td>${test.getName()}</td>

                <td>${test.getScheme().getName()}</td>

                <td>${test.getCreationTS()}</td>
                
                <td>
                <c:url var="detailUrl" value="${resources.JSP_VIEW_TEST}">
                <c:param name="id" value="${test.getId()}" /> 
				</c:url> <a href="<c:out value="${detailUrl}"/>">
				Details</a></td>

				</c:forEach>

          </table>
        </div>

      </div>
    </div>

  </div>

  <t:footer />
</body>
</html>