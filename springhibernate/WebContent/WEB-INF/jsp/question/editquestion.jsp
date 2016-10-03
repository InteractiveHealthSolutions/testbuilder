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
<title>${resources.APPLICATION_NAME}</title>

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

<script>
	$(document).ready(function() {
		initCKEditor("description");
		updateBtnAddMore();
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
				<h3>Edit Question</h3>

				<form:form id="frmEditQuestion" method="POST"
					action="/ihsmcqbuilder/question/editquestion"
					modelAttribute="editQuestion" enctype="multipart/form-data"
					class="form-horizontal">

					<table border="1px" class="table table-bordered">
						<%--       <table>  --%>

						<tr>
							<th>Question Type</th>
							<td colspan="2" width="450px"><form:select
									path="questionType.id" id="slctQuestionType"
									onchange="updateBtnAddMore()" class="form-control input">
									<c:forEach var="questionType" items="${questionTypeList}">

										<form:option value="${questionType.getId()}"
											label="${questionType.getTypeName()}" />
									</c:forEach>

								</form:select></td>
						</tr>

						<tr>
							<th>Question Category</th>
							<td colspan="2"><form:select path="categoryType.id"
									id="selectCategory" class="form-control input">
									<c:forEach var="category" items="${categoryType}">
										<form:option value="${category.getId()}"
											label="${category.getTypeName()}" />
									</c:forEach>
								</form:select></td>
						</tr>

						<tr>
							<th>Question Title</th>

							<td><form:hidden path="id" value="${editQuestion.getId()}" />
								<form:input path="title" maxlength="90"
									class="form-control input" placeholder="Question Title"
									required="true" /> <input type="hidden" name="creatorId"
								value="${currentUser.getId()}" /> <input type="hidden"
								name="creatorId" value="${editQuestion.getCreatorId()}" /></td>

						</tr>

						<tr>
							<th colspan="3"><br />
								<center>Question Description</center></th>
						</tr>
						<tr>
							<td colspan="2"><form:textarea path="description"
									id="editor" required="true" /></td>

						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>

						<tr id="trAddMoreButton">

							<td colspan="3" align="center"><input type="button"
								id="btnAddMore" class="btn btn-primary"
								onclick="addEditMoreOption()" value="Click to add option" /></td>

						</tr>

						<tr id="trQuestionData">
							<th>Define options</th>
							<td>

								<div id="divQuestionData">

									<c:forEach var="data"
										items="${editQuestion.getQuestionDataList()}"
										varStatus="index2">
										<c:if
											test="${data.getIndex() != 0 && editQuestion.getQuestionType().getId()!= 4}">

											<div id="div${index2.count}">

												<div class="input-group">

													<input type="textbox" id="textbox${index2.count}"
														name="questionDataList[${index2.count}].data"
														value="${data.getData()}" class="form-control" /> <span
														class="input-group-btn">
														 <input type="button" id = "btnRemove${index2.count}"
														class="btn btn-primary" value="X" onclick="removeElement('div' + ${index2.count})" />
													</span>
													<div id="div1" class="radio">
														<label id="radioLbl" class="radio control-label">
															<input id="${index2.count}" type="radio"
															name="selectedOption"
															onclick="setNewAnswer('${index2.count}')"
															style="margin-left: 50px;" /> <c:if
																test="${data.correct == true }">
																<script>
																	var radioId = document
																			.getElementById('${index2.count}');
																	radioId.checked = "checked";
																</script>
															</c:if> </input>
														</label>
													</div>
												</div>

												<input type="hidden" id="hid1" /> <input type="hidden"
													name="questionDataList[${index2.count}].id"
													value="${data.getId()}" /> <input type="hidden"
													name="questionDataList[${index2.count}].index"
													value="${data.getIndex()}" />
											</div>
										</c:if>
									</c:forEach>

								</div>
							</td>
						</tr>

						<tr>
							<th>Max marks</th>
							<td><form:input type="number" path="maxMarks"
									class="form-control input" required="true" min="0" /></td>

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
												name="singlebutton" class="btn btn-primary"
												onclick="submitEditQuesForm()" />
										</div>

									</div>


									<div class="col-md-6">
										<div class="text-center">
											<a href="managequestion"><input type="button"
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
