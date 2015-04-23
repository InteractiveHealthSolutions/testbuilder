<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
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
      
        <h3>List of attempted test</h3>
<br/>

<div class="panel panel-default">
  <div class="panel-body">
   Test Name: Blah
   Description: sdsddsdsdsdsdsdsd
   Total Questions: 20
  </div>
</div>

<br/>
<table border="1px" class="table table-bordered">

    <tr>
      <th>Creator Id/Name</th>
        <th>Question Attempted</th>
        <th>Time taken</th>
        <th>Submitted On</th>
        <th>Action</th>
    </tr>

    <c:forEach var="question" items="${detailTest.getQuestionList()}" varStatus="index">
      <tr>
        <td>${question.getQuestionType().getTypeName()}</td>
        <td>${question.getTitle()}</td>
         <td>${submitted on}</td>
        <td>${Action}</td>
      </tr>
    </c:forEach>   


  </table>

      </div>
    </div>


  </div>

 
  <t:footer />
  
</body>
</html>