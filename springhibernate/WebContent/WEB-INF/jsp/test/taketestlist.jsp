<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Header tag is called -->
<t:header />

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>


<link rel="stylesheet" type="text/css"	href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css">
<script type="text/javascript"	src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"	src="<c:url value='/resources/datatablejs.js'/>"></script>

<script>
	$(document).ready(function() {
		initializeDataTable("testListTable", "getTestList", "creationTS","Take Test","testbeginsummary?no=");
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

				<h3>Select Your Test</h3>
				<br />

				<!-- 		class="table table-bordered" 		 class="display" -->

				<table id="testListTable" class="display" class="display" cellspacing="0"	width="100%">
					<thead>
						<tr>
							<th>Title</th>
							<th>Description</th>
							<th>Created on</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
				
			</div>
		</div>
	</div>
	<t:footer />
</body>
</html>