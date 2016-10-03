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

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/ckfinder/ckfinder.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/ckeditorinitializer.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/questionjs.js'/>"></script>

<style type="text/css">
<!--
html, body {
	max-width: 100%;
	overflow-x: hidden;
}
-->
</style>



<script>
	$(document).ready(function() {
		initCKEditor("description");
		updateBtnAddMore();
		// 		    $('#frmSubmitQuestion').bootstrapValidator();
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
				<h3>Create Question</h3>


				<form:form id="frmSubmitQuestion" method="POST"
					action="/ihsmcqbuilder/question/createquestion"
					modelAttribute="newQuestion" enctype="multipart/form-data"
					class="form-horizontal">

					<table border="1px" class="table table-bordered">

						<tr>

							<th>Question Type *</th>
							<td colspan="2" width="450px"><form:select path="questionType.id"
									id="slctQuestionType" onchange="updateBtnAddMore()"
									class="form-control input">
									<c:forEach var="questionType" items="${questionTypeList}">
										<form:option value="${questionType.getId()}"
											label="${questionType.getTypeName()}" />
									</c:forEach>
								</form:select></td>
						</tr>

						<tr>
							<th>Category *</th>
							<td colspan="2"><form:select path="categoryType.id"
									id="selectCategory" class="form-control input">
									<c:forEach var="category" items="${categoryType}">
										<form:option value="${category.getId()}"
											label="${category.getTypeName()}" />
									</c:forEach>
								</form:select></td>
						</tr>

						<tr>
							<th>Question Title *</th>

							<td colspan="2"><form:input path="title" id="quesTitle"
									maxlength="90" class="form-control input"
									placeholder="Question Title" required="true" /> <input
								type="hidden" name="creatorId" value="${currentUser.getId()}" />
							</td>
						</tr>

						<tr>
							<th colspan="3"><br /> Question Description *</th>
						</tr>
						<tr>
							<td colspan="2"><form:textarea id="quesDesc"
									class="form-control input" path="description"></form:textarea></td>

						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>

						<tr id="trAddMoreButton">


							<td colspan="3" align="center"><input type="button"
								id="btnAddMore" class="btn btn-primary"
								onclick="addMoreOption()" value="Click to add option" disabled />
								<span style="font-weight: bold; padding-left: 480px">Correct
									Answer</span></td>

						</tr>
						<tr id="trQuestionData">
							<!--  <tr id="trQuestionData" > -->
							<th>Define options *</th>
							<td>
								<div id="divQuestionData"></div> <input type="hidden" id="hid1" />
							</td>
						</tr>

						<tr>
							<th>Max marks *</th>
							<td colspan="2"><form:input type="number" path="maxMarks"
									class="form-control input" required="true" min="1" max="10" />
						</tr>

						<tr>
							<th>Comments for Test Maker</th>

							<td colspan="2"><form:textarea path="commentsForMaker"
									class="form-control input" /></td>

						</tr>

						<tr align="center">
							<td id="errorLbl" colspan="3"
								style="color: red; font-weight: bold">${status}</td>
						</tr>

						<tr>
							<td colspan="3">

								<div class="control-group">
									<div class="col-md-6">
										<div class="text-center">
											<input type="button" value="Save" id="singlebutton"
												name="singlebutton" class="btn btn-success"
												onclick="submitQuesForm()" />
										</div>

									</div>


									<div class="col-md-6">
										<div class="text-center">
											<a href="/ihsmcqbuilder/home"><input type="button"
												class="btn btn-danger" value="Cancel & Go Back" /></a>
										</div>
									</div>

								</div>

							</td>
						</tr>
						<%-- 						<tr id="Delete_that_Tr"> --%>
						<%-- 							<td> --%>
						<!-- 							 <img src="/ihsmcqbuilder/resources/src/img/apple-touch-icon-144-precomposed.png" alt="Smiley face" height="42" width="42">  -->
						<%-- 							</td> --%>
						<%-- 						</tr> --%>
					</table>
				</form:form>

			</div>
		</div>
	</div>
	<t:footer />
</body>
</html>