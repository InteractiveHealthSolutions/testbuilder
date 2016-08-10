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

				<h3>Create Test Paper</h3>

				<form:form id="formSubmitTest" method="POST"
					action="/springhibernate/test/generatetest?type="
					modelAttribute="newTest">


					<table border="1px" class="table table-bordered">
						<tr>
							<th>Test Name</th>
							<td><form:input path="name" size="50" maxlength="90"
									class="form-control input" required="true" /> <input
								type="hidden" name="scheme.id" value="${schemeId }" /></td>

						</tr>

						<tr>
							<th>Test Description</th>
							<td><form:textarea path="description"
									class="form-control input" required="true" /> <input
								type="hidden" name="creatorId" value="${currentUser.getId()}" />
							</td>
						</tr>

					</table>
					<br>
					<table border="1px" class="table table-bordered">
						<tr>
							<th>Question Category</th>
							<th>Question List</th>
							<th>Action</th>
						</tr>
						<c:set var="questionIndex" scope="session" value="${0}" />
						<c:forEach var="question" items="${questionCollection}">
							<tr>
								<td>${question.get(0).getCategoryType().getTypeName()}</td>
								<td><c:forEach var="questionData" items="${question}"
										varStatus="loop">
       								  ${loop.index + 1}. ${questionData.getTitle()}
       								  <input type="hidden"
											name="questionList[${questionIndex}].id"
											value="${questionData.getId() }" />
										<br>
										<br>
										<c:set var="questionIndex" scope="session"
											value="${questionIndex + 1}" />
									</c:forEach></td>
								<td><c:forEach var="questionData" items="${question}"
										varStatus="loop">
										<c:url var="detailUrl" value="/question/viewquestion">
											<c:param name="id" value="${questionData.getId()}" />
										</c:url>
										<a
											onclick='javascript:window.open("<c:out value="${detailUrl}"/>" , "_blank", "scrollbars=1,resizable=1,height=500,width=1050");'
											style="cursor: pointer;">Details</a>
										<br>
										<br>
									</c:forEach></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="3" >${status}</td>
						</tr>

						<tr>
							<td colspan="3">

								<div class="control-group">
									<div class="col-md-4">
										<div class="text-center">
											<input type="button" value="Save" id="save"
												class="btn btn-success" onclick="submitFormByType(this.id)" />
										</div>

									</div>

									<div class="col-md-4">
										<div class="text-center">
											<input type="button" id="print" value="Print"
												class="btn btn-primary" onclick="submitFormByType(this.id)" />
										</div>
									</div>


									<div class="col-md-4">
										<div class="text-center">
											<input type="button" class="btn btn-danger" id="cancel"
												value="Cancel & Go Back" onclick="submitFormByType(this.id)" /></a>
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
</body>
</html>