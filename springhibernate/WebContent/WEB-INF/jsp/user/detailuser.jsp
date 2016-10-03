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

        <h3>User Detail</h3>

        <table border="1px" class="table table-bordered table-hover">
        <col width="20">
         <col width="80">
        
          <tr>
            <td colspan="2"><p class="text-success">${status}</p></td>
          </tr>

          <tr>
            <th>Name</th>
            <td>${detailUser.getName()}</td>
          </tr>
          <tr>
            <th>Login Id</th>
            <td>${detailUser.getLogin_Id()}</td>
          </tr>

      
          <tr>
            <th>Role Type</th>
            <td>${detailUser.getRole().getRoleName()}</td>
          </tr>
          <tr>

            <td colspan="2">
              <div class="text-center">
                <a href="manageuser"><input type="button" class="btn btn-default" value="Go Back" /></a>

              </div>
            </td>
          </tr>



        </table>

      </div>

    </div>

  </div>

</body>
</html>