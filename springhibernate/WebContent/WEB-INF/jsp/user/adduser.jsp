<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<script type="text/javascript">
$(document).ready(function() {
	document.getElementById('pass').value = "";
})
</script>

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

        <h3>Add New User</h3>

        <form:form method="POST" action="/ihsmcqbuilder/user/adduser" modelAttribute="newUser" role="form"
          class="form-horizontal">

          <div class="row">

            <div class="control-group">
              <div class="col-md-6">
                <input type="hidden" name="creatorId" value="${currentUser.getId()}"/>
                <form:label path="name" class="control-label">Name</form:label>
                <form:input path="name" class="form-control input" placeholder="User Name"  required="true"/>
                <form:errors path="name" />
              </div>
            </div>
            <div class="control-group">
              <div class="col-md-6">
                <form:label path="login_Id" class="control-label">Login Id</form:label>
                <form:input path="login_Id" class="form-control input" placeholder="Login Id"  required="true" />
                <form:errors path="name" />
              </div>
            </div>
          </div>

          <div class="row">
            <div class="control-group">
              <div class="col-md-6">
                <form:label path="password" class="control-label">Password</form:label>
                <form:input path="password" class="form-control input" placeholder="Password" required="true" type="password" id="pass" />
                <form:errors path="name" />
              </div>
            </div>

            <div class="control-group">
              <div class="col-md-6">
                <label class="control-label" for="select">Role-Type</label>

                <form:select path="role.id" class="form-control input">                 
                  <c:forEach var="role" items="${roleList}">
                    <option value="${role.getId()}">${role.getRoleName()}</option>
                  </c:forEach>
                </form:select>
              </div>
            </div>
            
          </div>

          <!--  Button -->

<br/>
          <div class="row">
         
         <p class="text-danger" align="center" style="color: red; font-weight: bold">${status}</p>
         
             <div class="control-group">
                  <div class="col-md-6">
                    <div class="text-center">
                   
                     
                      <input type="submit" value="Save" id="singlebutton" name="singlebutton" class="btn btn-success" />
                    </div>

                  </div>


                  <div class="col-md-6">
                    <div class="text-center">
                      <a href="manageuser"><input type="button" class="btn btn-danger" value="Cancel & Go Back" /></a>
                    </div>
                  </div>

                </div>         
           
          </div>
       
        </form:form>

      </div>

    </div>
  </div>

  <!-- Footer tag is called -->
  <t:footer />

</body>
</html>

