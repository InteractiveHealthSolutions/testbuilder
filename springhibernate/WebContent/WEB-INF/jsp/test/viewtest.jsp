<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
  <h2>View Test</h2>
  
<table border="1px">

      <tr>
        <th>Test Name</th>
       <td>${detailTest.getName()}</td>
       
      </tr>

      <tr>
        <th>Test Description</th>
         <td> ${detailTest.getDescription()}  </td>
     
      </tr>
      <tr>
        <th>Comments for Test Maker</th>
       <td>${detailTest.getComments()} </td>
      </tr>

      <tr>
        <th>Question Type</th>
        <th>Question Title</th>
      </tr>
      
        <c:forEach var="question" items="${detailTest.getQuestionList()}" varStatus="index">
       <tr>
          <td align="center">${question.getQuestionType().getTypeName()}</td>
          <td>${question.getTitle()} </td>          
      </tr>
      </c:forEach>

      <tr>
        <th>Created By</th>
        <td>Show Creator Name</td>        
      </tr>
       
       <tr>
        <th>Creation Time</th>
        <td>Show Time</td>        
      </tr>
      
         <tr>
        <th>Last Editor</th>
        <td>Show Editor Name</td>        
      </tr>
       
       <tr>
        <th>Last Editing Time</th>
        <td>Show Last Editing Time</td>        
      </tr>
      <tr>
        <td colspan="2">${status}</td>
      </tr>


    </table>
</body>
</html>