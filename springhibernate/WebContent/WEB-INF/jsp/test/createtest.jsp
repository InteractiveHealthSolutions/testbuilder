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
				<h3>Create Test Sceheme</h3>
				<form:form id="frmSubmitQuestion" method="POST"
					action="/springhibernate/test/createtest" modelAttribute="newTest">

					<table border="1px" class="table table-bordered">

						<tr>
							<th>Test Name</th>
							<td><form:input path="name" size="50" maxlength="90"
									class="form-control input" required="true" /></td>

							<td><form:errors path="name" /></td>
						</tr>

						<tr>
							<th>Test Description</th>
							<td><form:textarea path="description"
									class="form-control input" required="true" /> <input
								type="hidden" name="creatorId" value="${currentUser.getId()}" />
							</td>

							<td><form:errors path="description" /></td>

						</tr>
						
						<tr>
							<th>Total Questions</th>
							<td colspan="2">
							<form:input type="number" path="" class="form-control input" required="true" min="1" max="999" />
							</td>
						</tr>
						
						<tr>
							<th>Comments for Test Maker</th>
							<td colspan="2"><form:textarea path="comments"
									class="form-control input" /></td>
						</tr>

						<tr>
							<th>Select Question</th>
							<th colspan="2">Question Data</th>
						</tr>

						<tr>
							<td>
								<%-- <form:select id="slctQuestion" path="questionList" items="${loadedQuestionList}" itemLabel="title" itemValue="id" size="10" onclick="getQuestionData()"/>          --%>
								<form:select id="slctQuestion" path="questionList"
									items="${loadedQuestionList}" itemLabel="title" itemValue="id"
									onclick="getQuestionData()" class="form-control input" />

							</td>
							<td height="350px" width="400px" colspan="2">

								<div id="divQuestionData"></div>
							</td>
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
											<a href="createquestion"><input type="button"
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