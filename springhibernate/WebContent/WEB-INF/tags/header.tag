<%@ tag language="java" description="Login Page template" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag import="com.ihs.mcqbuilder.model.User" %>
<%-- <%@attribute name="user" required="true" type="com.ihs.mcqbuilder.model.User"%> --%>

<%--     <title>${user.getName()}</title> --%>
    
    <title>IHS MCQ Builder</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
     
     <!-- All Required CSS and JS for UI -->

<link href="<c:url value="/resources/src/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/src//css/style.css"/>" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

<!-- Fav and touch icons -->
<!-- <link rel="apple-touch-icon-precomposed" sizes="144x144" -->
<%--   href="<c:url value="/resources/src/img/apple-touch-icon-144-precomposed.png"/>"/> --%>

<!-- <link rel="apple-touch-icon-precomposed" sizes="114x114" -->
<%--   href="<c:url value="/resources/src/img/apple-touch-icon-114-precomposed.png"/>"/> --%>

<!-- <link rel="apple-touch-icon-precomposed" sizes="72x72" -->
<%--   href="<c:url value="/resources/src/img/apple-touch-icon-72-precomposed.png"/>"/> --%>

<%-- <link rel="apple-touch-icon-precomposed" href="<c:url value="/resources/src/img/apple-touch-icon-57-precomposed.png"/>" /> --%>
<link rel="icon" type="image/png" href="<c:url value="/resources/src/img/ihs_logo.png"/>" />


<script type="text/javascript" src="<c:url value="/resources/src/js/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/src/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/src/js/scripts.js"/>"></script>

<!-- End Of All Required CSS and JS for UI -->
