<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<html>
<head>

<t:loginpage user="${user}"/>
<%--    First Name: ${user.getName()} <br/>     --%>
   
<%--    <c:forEach var="right" items="${user.getRole().getPrivilegeList()}"> --%>

<%--      <c:if test="${ right.getId() == 1}"> --%>
<%--      Loop: ${user.getName()} <br/> --%>
<%--      </c:if> --%>

<%--    </c:forEach> --%>
</head>

<body>
</body>
</html>


<%-- <t:loginpage> --%>
<%--     <jsp:attribute name="header">  --%>
<%--     </jsp:attribute> --%>
    
<%--    <jsp:attribute name="footer"> --%>
<!--       <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p> -->
<%--     </jsp:attribute> --%>
    
<%--     <jsp:body> --%>
<!--         <p>Hi I'm the heart of the message</p> -->
<%--     </jsp:body>     --%>
     
<%-- </t:loginpage> --%>