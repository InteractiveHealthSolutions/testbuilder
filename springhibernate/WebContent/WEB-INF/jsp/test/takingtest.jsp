<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/libraries/frameworks/io2012/css/default.css"/>"
	media="all">
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/libraries/frameworks/io2012/css/phone.css"/>"
	media="only screen and (max-device-width: 480px)">
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/libraries/frameworks/io2012/css/slidify.css"/>">
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/libraries/highlighters/highlight.js/css/solarized_light.css"/>" />
<base target="_blank">
<!-- This amazingness opens all links in a new tab. -->
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/libraries/widgets/bootstrap/css/bootstrap.css"/>"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/libraries/widgets/quiz/css/demo.css"/>"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/./assets/css/custom.css"/>"></link>
<link rel="stylesheet"
	href="<c:url value="/resources/quizslidify/./assets/css/slidecolor.css"/>"></link>




<!-- 	JS Related to CKEditor/ CKFinder -->
<!-- 	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script> -->

<script type="text/javascript" src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/ckfinder/ckfinder.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/ckeditorinitializer.js'/>"></script>

<!-- 	End of declaration of JS Related to CKEditor/ CKFinder -->

<!-- Loading JS of TinyMCE -->
<script src="<c:url value="/resources/tiny_mce/tiny_mce.js"/>"></script>
<script src="<c:url value="/resources/tinyinitializer.js"/>"></script>

<!-- End of Loading JS of TinyMCE -->


<title>Insert title here</title>

</head>
<body style="opacity: 0">
	<!-- This page will be used for conducting test -->

	<form id="frm" method="post" action="/springbernate/test/testend">

		<!-- Introductory SLIDE -->
		<slides class="layout-widescreen"> <slide
			class="title-slide segue nobackground"> <!--    Test name -->
		<hgroup class="auto-fadein">
		<h1>Test Name</h1>

		<!--     Test description if present -->
		<h2>Test description</h2>
	 </hgroup> 
		<article></article> 
		</slide> 
		<!-- Run a loop of Expression language and make slide tag and other important elements -->

		<%--  `${i.index}` starts counting at 0  --%> <%-- `${i.count}` starts counting at 1 --%>

		<c:forEach var="question" items="${test.getQuestionList()}" varStatus="i">

			<slide class="" id="slide-${i.count}" style="background:;">

			<h3>${question.getTitle()}</h3>
			<article data-timings=""> 
			<!--  It should be replaced by CKEditor  -->

<%-- <textarea name="description${i.count}" id="editor${i.count}">${question.getDescription()}</textarea> --%>
<textarea class="roClass" style="width:100%" >${question.getDescription()}dsdsdsd</textarea>

			<!-- 		Now draw checkbox/ Radio or Text area for Options or Paragraph answer respectivley. -->
			
<!-- PARAGRAPH(1),  MULTIPLE_CHOICE(2), SINGLE_CHOICE(3) -->
				
			<c:if test="${question.getQuestionType().getId() == 1}">
			
			<textarea class="ckeditor" name="description${i.count}" id="editor${i.count}"></textarea>
			
			</c:if>
			
			</article> </slide>
		
		</c:forEach>
		
		 </slides>

	</form>


</body>


<!-- QuizSlidify JS -->

<!--[if IE]>
    <script 
      src="http://ajax.googleapis.com/ajax/libs/chrome-frame/1/CFInstall.min.js">  
    </script>
    <script>CFInstall.check({mode: 'overlay'});</script>
  <![endif]-->
</body>
<!-- Load Javascripts for Widgets -->


<script type="text/javascript"	src="<c:url value="/resources/src/js/jquery.min.js"/>"></script>

<script
	data-main="/springhibernate/resources/quizslidify/libraries/frameworks/io2012/js/slides"
	src="<c:url value="/resources/quizslidify/libraries/frameworks/io2012/js/require-1.0.8.min.js"/>"></script>

<script
	src="<c:url value="/resources/quizslidify/libraries/widgets/bootstrap/js/bootstrap.min.js"/>"></script>
	
<script type="text/javascript"
	src="<c:url value="/resources/quizslidify/libraries/widgets/bootstrap/js/bootbox.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/quizslidify/libraries/widgets/quiz/js/jquery.quiz.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/quizslidify/libraries/widgets/quiz/js/mustache.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/quizslidify/libraries/widgets/quiz/js/quiz-app.js"/>"></script>


<!-- MathJax: Fall back to local if CDN offline but local image fonts are not supported (saves >100MB) -->
<script type="text/x-mathjax-config">
    MathJax.Hub.Config({
      tex2jax: {
        inlineMath: [['$','$'], ['\\(','\\)']],
        processEscapes: true
      }
    });
  </script>
<script type="text/javascript"
	src="http://cdn.mathjax.org/mathjax/2.0-latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>

<!-- <script src="https://c328740.ssl.cf1.rackcdn.com/mathjax/2.0-latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
  </script> -->

<script>
	window.MathJax
			|| document
					.write('<script type="text/x-mathjax-config">MathJax.Hub.Config({"HTML-CSS":{imageFont:null}});<\/script><script src="libraries/widgets/mathjax/MathJax.js?config=TeX-AMS-MML_HTMLorMML"><\/script>')
</script>
<script>
	$(function() {
		$("#example").popover();
		$("[rel='tooltip']").tooltip();
	});
</script>

<!-- LOAD HIGHLIGHTER JS FILES -->
<script
	src="<c:url value="/resources/quizslidify/libraries/highlighters/highlight.js/highlight.pack.js"/>"></script>
<script>
	hljs.initHighlightingOnLoad();
</script>
<!-- DONE LOADING HIGHLIGHTER JS FILES -->

<script>
	$(document).ready(function() {
		initCKEditor("description1");
		initTinyMCE("roClass");
	});
</script>

<script>
// 	$(document).ready(function() {
// 		initTinyMCE("roClass");
// 	});
</script>

</html>