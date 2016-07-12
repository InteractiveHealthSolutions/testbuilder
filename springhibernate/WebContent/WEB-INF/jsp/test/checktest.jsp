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
<title>${resources.APPLICATION_NAME}</title>

<!-- Header tag is called -->

<t:header />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%-- <script src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script> --%>
<%-- <script type="text/javascript" src="<c:url value='/resources/ckeditorinitializer.js'/>"></script> --%>


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
        <h3>Your Test</h3>

<table border="1px" class="table table-bordered">
<tr>
      <th>Test Name</th>
       <th>Test Description</th>
         <th>Number of Solutions</th>
          <th>Last Submitted On</th>
<%--       <td>${exam.getTest().getName()}</td>    --%>
    </tr>
    
<c:forEach var="test" items="${testList}" varStatus="i">

    

    <tr>     
      <td>${exam.getTest().getDescription()}</td>
          <td>${exam.getQuestionAnswerList().size()}</td>
            <td>${answeredQuestions}</td>
            <td>${answeredQuestions}</td>
    </tr>      
  
<%--     <tr> --%>
<%--       <th>Created By</th> --%>
<%--       <td>Show Creator Name</td> --%>
<%--     </tr> --%>

<%--     <tr> --%>
<%--       <th>Creation Time</th> --%>
<%--       <td>Show Time</td> --%>
<%--     </tr> --%>

<%--     <tr> --%>
<%--       <th>Last Editor</th> --%>
<%--       <td>Show Editor Name</td> --%>
<%--     </tr> --%>

<%--     <tr> --%>
<%--       <th>Last Editing Time</th> --%>
<%--       <td>Show Last Editing Time</td> --%>
<%--     </tr> --%>
    <tr>
      <td colspan="2">${status}</td>
    </tr>

	</c:forEach>
  </table>

      </div>
    </div>


  </div>

 
  <t:footer />
  
</body>
</html>