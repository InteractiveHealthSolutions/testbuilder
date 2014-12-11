<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<html>

<head>


<!-- Header tag is called -->
<t:header />

</head>

<body>

  <div class="container">

    <div class="row clearfix">
      <div class="col-md-12 column">
        <h3 class="text-center"></h3>
      </div>
    </div>

    <div class="row clearfix">

      <div class="col-md-12 column" align="center">



        <form:form method="POST" action="/springhibernate/index" commandName="loginUser" class="form-horizontal">

          <fieldset>

            <!-- Form Name -->
            <legend>Welcome to Test Manager</legend>

            <!-- Text input-->
            <div class="form-group">
              <!--   <label class="col-md-4 control-label" for="textinput">LoginId</label>   -->
              <form:label path="login_Id" class="col-md-4 control-label">Login Id</form:label>

              <div class="col-md-4">
                <!--   <input id="textinput" name="textinput" type="text" placeholder="Your Login ID" class="form-control input-md"> -->
                <form:input path="login_Id" class="form-control input-md" placeholder="Your Login ID" required="true" />
                <form:errors path="login_Id" />
              </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
              <!--   <label class="col-md-4 control-label" for="passwordinput">Password</label> -->
              <form:label path="password" class="col-md-4 control-label">Password</form:label>
              <div class="col-md-4">
                <form:password path="password" class="form-control input-md" id="InputPassword"
                  placeholder="Your Password" required="true"/>
                <form:errors path="password" />

              </div>
            </div>

            <!-- Button -->
            <div class="form-group">
              <label class="col-md-4 control-label" for="singlebutton"></label>
              <div class="col-md-4">
                <button id="singlebutton" name="singlebutton" class="btn btn-success">Login</button>
              </div>
            </div>

          </fieldset>
        </form:form>
      </div>
    </div>

    <!-- 	<div align="center"> -->

    <%-- 		<form:form method="POST" action="/springhibernate/index" --%>
    <%-- 			commandName="loginUser"> --%>
    <!-- 			<table> -->
    <!-- 				<tr>					 -->
    <!-- 					<td>Login Id</td>				 -->
    <%-- 					<td><form:input path="login_Id" /></td> --%>
    <%-- 					<td><form:errors path="login_Id" /></td> --%>
    <!-- 				</tr> -->
    <!-- 				<tr> -->
    <!-- 					<td>Password</td> -->
    <!-- 										<td><input type="password" name="password" /></td> -->
    <%-- 					<td><form:password path="password" /></td> --%>
    <%-- 					<td><form:errors path="password" /></td> --%>

    <!-- 				</tr> -->
    <!-- 				<tr> -->
    <%-- 					<td colspan="2">${notice}</td> --%>
    <!-- 				</tr> -->
    <!-- 				<tr> -->
    <!-- 					<td colspan="2"><input type="submit" value="Sign In"></td> -->
    <!-- 				</tr> -->
    <!-- 			</table> -->
    <%-- 		</form:form> --%>
    <!-- 	</div> -->



    <!-- Footer tag is called -->
    <t:footer />

  </div>
</body>
</html>