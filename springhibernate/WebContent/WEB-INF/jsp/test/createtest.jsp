<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Header tag is called -->

<t:header />

<link
	href="<c:url value='/resources/jquerymultiselect/css/multi-select.css'/>"
	media="screen" rel="stylesheet" type="text/css">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value='/resources/testjs.js'/>"></script>


<script type="text/javascript"
	src="<c:url value='/resources/jquerymultiselect/js/jquery.multi-select.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jquerymultiselect/jquerymsinitializer.js'/>"></script>
<script>
	$(document).ready(function() {
		initJQMultiSelect("slctQuestion");
	});
	
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
				<h3>Create Test Scheme</h3>
				<form:form id="formSubmitScheme" method="POST"
					action="/springhibernate/test/createtest?type=" modelAttribute="newScheme">

					<table border="1px" class="table table-bordered">

						<tr>
							<th>Scheme Name</th>
							<td><form:input path="name" size="50" maxlength="90"
									class="form-control input" required="true" id="nameTxt" /></td>

							<td><form:errors path="name" /></td>
						</tr>

						<tr>
							<th>Scheme Description</th>
							<td><form:textarea path="description" id="descriptionTxt"
									class="form-control input" required="true" /> <input
								type="hidden" name="creatorId" value="${currentUser.getId()}" />
							</td>

							<td><form:errors path="description" /></td>

						</tr>
						
						<tr>
							<th>Total Questions</th>
							<td colspan="2">
							<form:input type="number" id="questionTxt" path="totalQuestions" class="form-control input" required="true" min="1" max="999" />
							</td>
						</tr>
					
						<tr>
						<th>Categories</th>
						<td height="300px" width="400px" colspan="2">
						<div id="selectCategory">
						<table>
						<tr>
						  <td>
						  <form:select path="" id="selectCategoryList" class="form-control input" style="width: 302px">
						  <c:forEach var="category" items="${categoryType}">
        		            <form:option value="${category.getId()}" label="${category.getTypeName()}" />
		                  </c:forEach>
                  			</form:select>
             			 </td>
             			  
             			 <td style="padding-left: 25px">
             			 <input type="button" class="btn btn-primary" value="Add Category" onclick="addMoreCategories()" />
             			 </td>
						</tr>
						</table>
						
						<div id="dataDiv" style="margin-top: 15px; margin-left: 5px"></div>
						
						</div>
						
						
						</td>
						</tr>
						
						<tr>
							<td colspan="3" align="center">
							<label id="errorLabel" style="display: none; color: red "></label>
							</td>
						</tr>

						<tr>
							<td colspan="3">
								<div class="control-group">

									<div class="col-md-4">
										<div class="text-center">
											<input type="button" value="Save" id="save"
												name="singlebutton" class="btn btn-success" onclick="checkError(this.id)" />
										</div>

									</div>
									
									<div class="col-md-4">
										<div class="text-center">
											<input type="button" value="Generate Test" id="generate"
												name="singlebutton" class="btn btn-success" onclick="checkError(this.id)" />
										</div>

									</div>

									<div class="col-md-4">
										<div class="text-center">
											<a href="/springhibernate/home">
											<input type="button"
												class="btn btn-danger" value="Cancel & Go Back" /></a>
										</div>
									</div>

								</div>
							</td>
						</tr>


					</table>
				</form:form>

			</div>
		</div>
	</div>

	<t:footer />
</body>
</html>