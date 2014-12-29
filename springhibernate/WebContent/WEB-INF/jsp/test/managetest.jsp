<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
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
        <c:url var="createTest" value="${resources.getJSP_CREATE_TEST()}">
        </c:url>
        <h4>
          <a href="<c:out value="${createTest}"/>">Create New Test</a>
        </h4>
        <div>
          <table border="1px" class="table table-bordered">
            <tr>
              <th>Tile</th>
              <th>Description</th>
              <th>Created on</th>
              <th>Action</th>
            </tr>

            <c:forEach var="test" items="${testList}">
              <tr>
                <td>${test.getName()}</td>

                <td>${test.getDescription()}</td>

                <td>${test.getCreationTS()}</td>

                <c:url var="editUrl" value="${resources.JSP_EDIT_TEST}">
                  <c:param name="id" value="${test.getId()}" />
                </c:url>
                <td>
                <a href="<c:out value="${editUrl}"/>">Edit</a> | 
                    
                    <c:url var="detailUrl" value="${resources.JSP_VIEW_TEST}">                        
                        <c:param name="id" value="${test.getId()}" />
                      </c:url> <a href="<c:out value="${detailUrl}"/>">Details</a>
                
                </td>

              </tr>

            </c:forEach>

          </table>
        </div>

      </div>
    </div>

  </div>

  <t:footer />
</body>
</html>