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

				<h2 class="text-Center">Welcome to Test Manager</h2>

				<br />
				<h3>
					<p class="text-info">${message}</p>
				</h3>

				<c:url var="homeUrl" value="/${resources.getJSP_HOME()}">
				</c:url>

<!-- class="btn btn-primary active" -->

				<br /> <br /> <a href="<c:out value="${homeUrl}"/>" class="btn btn-default">Go to Home</a>


			</div>

		</div>

		<!-- Footer tag is called -->
		<t:footer />

	</div>

</body>
</html>