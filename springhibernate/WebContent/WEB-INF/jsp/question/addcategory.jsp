<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%
	response.setHeader("Cache-Control",
			"no-cache,no-store,must-revalidate");//HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
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
				<h3>Add Category</h3>
				<form:form id="frmSubmitCategory" method="POST"
					action="/springhibernate/question/addcategory"
					modelAttribute="newCategory">

					<table border="1px" class="table table-bordered">
						<tr>
							<th>Type Name</th>
							<td><form:input path="typeName" size="50" maxlength="90"
									class="form-control input" required="true" /></td>

							<td><form:errors path="typeName" /></td>
						</tr>

						<tr>
							<th>Type Description</th>
							<td><form:textarea path="description"
									class="form-control input" required="true" /> <input
								type="hidden" name="creatorId" value="${currentUser.getId()}" />
							</td>

							<td><form:errors path="description" /></td>

						</tr>

						<tr>
							<td colspan="3">${status}</td>
						</tr>

						<tr>
							<td colspan="3">
								<div class="control-group">

									<div class="col-md-6">
										<div class="text-center">
											<input type="submit" value="Save" id="singlebutton"
												name="singlebutton" class="btn btn-primary" />
										</div>

									</div>

									<div class="col-md-6">
										<div class="text-center">
											<a href="/springhibernate/home">
											<input type="button"
												class="btn btn-default" value="Cancel & Go Back" /></a>
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