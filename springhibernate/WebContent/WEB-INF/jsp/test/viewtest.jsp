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
	
<style type="text/css">
<!--
html, body {
    max-width: 100%;
    overflow-x: hidden;
}
-->
</style>
	
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
				<h3>View Test</h3>
				
					<form:form id="formViewTest" method="POST"
					action="/springhibernate/test/viewtest?name=">

				<table border="1px" class="table table-bordered">

					<tr>
						<th>Test Name</th>
						<td>${detailTest.getName()}</td>

					</tr>

					<tr>
						<th>Test Description</th>
						<td>${detailTest.getDescription()}</td>

					</tr>
					<tr>
						<th>Comments for Test Maker</th>
						<td>${detailTest.getComments()}</td>
					</tr>

					<tr>
						<th>Created On</th>
						<td>${detailTest.getCreationTS()}</td>
					</tr>

					<tr>
						<th>Question Type</th>
						<th>Question Title</th>
					</tr>

					<c:forEach var="question" items="${detailTest.getQuestionList()}"
						varStatus="index">
						<tr>
							<td>${question.getQuestionType().getTypeName()}</td>
							<td>${question.getTitle()}</td>
						</tr>
					</c:forEach>

					<tr>
						<td colspan="3">
							<div class="control-group">

								<div class="col-md-4">
									<div class="text-center">
										<input type="button" value="Print" 
										id="1"
											name="singlebutton" class="btn btn-success" onclick="printTest(this.id)" />
									</div>

								</div>

								<div class="col-md-4">
									<div class="text-center">
										<input type="button" value="Print Test"
										id="2"
											name="singlebutton" class="btn btn-success" onclick="printTest(this.id)" />
									</div>

								</div>
								
								<div class="col-md-4">
									<div class="text-center">
									  <a href="viewtests">	<input type="button" value="Cancel & Go Back" id="cancel"
											name="singlebutton" class="btn btn-danger" /></a>
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