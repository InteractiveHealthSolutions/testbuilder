<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

				<h3>Test Summary</h3>

				<form id="frm" method="get" action="/springhibernate/test/takingtest">

					<table border="1px" class="table table-bordered">

						<tr>
							<th>Test Name</th>
							<td>${selectedTest.getName()}</td>
						</tr>

						<tr>
							<th>Test Description</th>
							<td>${selectedTest.getDescription()}</td>
						</tr>

						<tr>
							<th>Total Questions</th>
							<td>${selectedTest.getQuestionList().size()}</td>
						</tr>

						<tr>
							<td colspan="2">
								<div class="text-center">
									<c:url var="testUrl" value="${resources.JSP_TAKING_TEST}">
									</c:url>

									<%-- <a href="<c:out value="${testUrl}"/>" class="btn btn-default">Begin Test</a> --%>
									<input type="hidden" name="token" value="${token}" /> 
								
									<input type="submit" value="Begin Test" id="singlebutton"
										name="singlebutton" class="btn btn-primary" />
								</div>
							</td>
						</tr>


					</table>

				</form>
			</div>

		</div>

		<!-- Footer tag is called -->
		<t:footer />

	</div>
</body>
</html>