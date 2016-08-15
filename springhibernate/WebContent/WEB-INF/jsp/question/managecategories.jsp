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

				<h3>Manage Categories</h3>

				<p style="font-weight: bold">${message}</p>

				<div>

					<form:form id="formManageCategory" method="POST"
						action="/springhibernate/question/managecategories?id=">
						<table border="1px" class="table table-bordered">
							<tr>
								<th>Category Name</th>
								<th>Category Description</th>
								<th>Action</th>
							</tr>

							<c:forEach var="category" items="${categoryList}">
								<tr>
									<td>${category.getTypeName()}</td>
									<td>${category.getDescription()}</td>
									<td><a id="${category.getId()}"
										onclick="submitManageCategory(this.id)" style="cursor: pointer">Delete</a></td>
								</tr>
							</c:forEach>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>

	<t:footer />
</body>
</html>