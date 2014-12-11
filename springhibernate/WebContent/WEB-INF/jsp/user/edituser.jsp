<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <title>${resources.getAPPLICATION_NAME()}</title> --%>
<!-- <style> -->
<!-- /* .error { */ /* color: #ff0000; */ /* font-style: italic; */ /* font-weight: bold; */ /* } */ -->
<!-- </style> -->


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

        <h3>Edit User</h3>


        <form:form method="POST" action="/springhibernate/user/edituser" modelAttribute="editUser" role="form"
          class="form-horizontal">

          <div class="row">

            <div class="control-group">
              <div class="col-md-6">
                <input type="hidden" name="creatorId" value="${editUser.getCreatorId()}" /> 
                <input type="hidden" name="lastEditorId" value="${currentUser.getId()}"/>

                <form:label path="name" class="control-label">Name</form:label>
                <form:input path="name" class="form-control input" placeholder="User Name" required="true" />
                <form:errors path="name" />
              </div>
            </div>

            <div class="control-group">
              <div class="col-md-6">
                <form:hidden path="id" value="${id}" />
                <form:label path="login_Id" class="control-label">Login Id</form:label>
                <form:input path="login_Id" class="form-control input" placeholder="Login Id" required="true" />
                <form:errors path="name" />
              </div>
            </div>
          </div>


          <div class="row">
            <div class="control-group">
              <div class="col-md-6">
                <form:label path="password" class="control-label">Password</form:label>
                <form:input path="password" class="form-control input" placeholder="Password" required="true" />
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

          <br />
          <div class="row">

            <p class="text-danger">${status}</p>

      
               <div class="control-group">
                  <div class="col-md-6">
                    <div class="text-center">
                   
                     
                      <input type="submit" value="Update" id="singlebutton" name="singlebutton" class="btn btn-primary" />
                    </div>

                  </div>


                  <div class="col-md-6">
                    <div class="text-center">
                      <a href="manageuser"><input type="button" class="btn btn-default" value="Cancel & Go Back" /></a>
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




  <%--   <form:form method="POST" action="/springhibernate/edituser" commandName="editUser"> --%>
  <!--     <table border="1px"> -->
  <!--       <tr> -->
  <!--         <th>Name</th> -->
  <%--         <td><form:input path="name" /></td> --%>
  <%--         <td><form:errors path="name" cssClass="error" /></td> --%>
  <!--       </tr> -->
  <!--       <tr> -->
  <%--         <form:hidden path="id" value="${id}" /> --%>
  <!--         <th>Login Id</th> -->
  <%--         <td><form:input path="login_Id" /></td> --%>
  <%--         <td><form:errors path="login_Id" cssClass="error" /></td> --%>

  <!--       </tr> -->

  <!--       <tr> -->
  <!--         <th>Password</th> -->
  <%--         <td><form:password path="password" /></td> --%>
  <%--         <td><form:errors path="password" cssClass="error" /></td> --%>
  <!--       </tr> -->
  <!--       <tr> -->
  <!--         <th>Role Type</th> -->
  <%--         <td><form:select path="role.id"> --%>

  <%--             <c:forEach var="eachRole" items="${roleList}"> --%>

  <%--               <form:option value="${eachRole.getId()}" label="${eachRole.getRoleName()}" /> --%>

  <%--             </c:forEach> --%>

  <%--           </form:select></td> --%>

  <!--       </tr> -->

  <!--       <tr> -->

  <%--         <td colspan="3">${status}</td> --%>
  <!--       </tr> -->

  <!--       <tr> -->
  <!--         <td><input type="button" value="Cancel & Go back"></td> -->
  <!--         <td><input type="submit" value="Submit"></td> -->
  <!--       </tr> -->
  <!--     </table> -->
  <%--   </form:form> --%>

</body>
</html>