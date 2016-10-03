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
<script type="text/javascript" src="<c:url value='/resources/ckeditorvalidator.js'/>"></script>



<!-- 	End of declaration of JS Related to CKEditor/ CKFinder -->

<!-- Loading JS of TinyMCE -->
<script src="<c:url value="/resources/tiny_mce/tiny_mce.js"/>"></script>
<script src="<c:url value="/resources/tinyinitializer.js"/>"></script>

<!-- End of Loading JS of TinyMCE -->


<title>Insert title here</title>

</head>
<body style="opacity: 0">
	<!-- This page will be used for conducting test -->

    <form:form id="frm" method="POST" action="/ihsmcqbuilder/test/submittest"  modelAttribute="exam">

     <input type="hidden" name="token" value="${token}" />
     
		<!-- Introductory SLIDE -->
		<slides class="layout-widescreen"> 
		<slide class="title-slide segue nobackground">			
		
		<hgroup class="auto-fadein">
		
			<!--    Test name -->
		<h1>${exam.getTest().getName()}</h1>

		<!--     Test description if present -->
		<h2>${exam.getTest().getDescription()}</h2>
	
	 </hgroup> 
	 
		<article></article> 
		</slide> 
		<!-- Run a loop of Expression language and make slide tag and other important elements -->

		<%--  `${i.index}` starts from 0  --%> <%-- `${i.count}` starts from 1 --%>

		<c:forEach var="qstnAnswer" items="${exam.getQuestionAnswerList()}" varStatus="i">

			<slide class="" id="slide-${i.count}" style="background:;">
			
			<c:set var="lastSlideId" value="${i.count}"/>

			<h4>${qstnAnswer.getQuestion().getTitle()}</h4>
			<article data-timings=""> 
			<!--  It should be replaced by CKEditor  -->

<textarea class="roClass" style="width:100%" >${qstnAnswer.getQuestion().getDescription()}</textarea>

<hr>
	<h4>Your Answer</h4>
			<!-- 		Now draw checkbox/Radio or Text area for Options or Paragraph answer respectivley. -->
				
				

				<!-- PARAGRAPH(1) -->
			<c:if test="${qstnAnswer.getQuestion().getQuestionType().getId() == 1}">

<form:textarea class="ckeditor" path="questionAnswerList[${i.index}].answer.answerDataList[${i.index}].data"
 data-pa="pa${i.index}" onload="" />
 

<!-- There will be only one AnswerData for paragraph question so its index can be hardcoded as 0  -->



 <input type="hidden" name="questionAnswerList[${i.index}].answer.answerDataList[${i.index}].index"  value="0"/>
  <input type="hidden" id="pa${i.index}" name="questionAnswerList[${i.index}].answered" value="false"/>

			 
			</c:if>
			
			<!-- MULTIPLE_CHOICE(2) -->
			  <c:if test="${qstnAnswer.getQuestion().getQuestionType().getId() == 2}">  
			  	
			  	 <div class="form-group">
			  	 
			  	<c:forEach var="questionData" items="${qstnAnswer.getQuestion().getQuestionDataList()}" varStatus="i2">

			  	 <div class="checkbox">
            <label class="checkbox control-label">
            <input type="checkbox" name="questionAnswerList[${i.index}].answer.answerDataList[${i2.index}].data" 
            value="${questionData.data}">${questionData.getData()}
            
            <input type="hidden" name="questionAnswerList[${i.index}].answer.answerDataList[${i2.index}].index"
             value="${questionData.getIndex()}"/>
            </label>
                 </div>			  	 
			  	
			  
			  	</c:forEach>		   
			        	 </div>
        </c:if>
        
        	<!-- SINGLE_CHOICE(3) -->
			  <c:if test="${qstnAnswer.getQuestion().getQuestionType().getId() == 3}">       
			    <div class="form-group">
			    	<c:forEach var="questionData" items="${qstnAnswer.getQuestion().getQuestionDataList()}" varStatus="i2">
			    	
			    	 <div class="radio">
      <label class="radio control-label">
      
            <input type="radio" name="questionAnswerList[${i.index}].answer.answerDataList[0].data" 
      value="${questionData.data}">${questionData.getData()}
      
       <input type="hidden" name="questionAnswerList[${i.index}].answer.answerDataList[0].index"
             value="0"/>             

             </label>
    </div>   		 		  	
			  	
			  	</c:forEach>
			  	 </div>		
        </c:if>
        	
        	 
<!--         Storing Test Id  -->
 <input type="hidden" name="questionAnswerList[${i.index}].answer.test.id"  value="${exam.getTest().getId()}"/>
 
<!--  Set CreatorId -->
         <input type="hidden" name="questionAnswerList[${i.index}].answer.creatorId"  value="${currentUser.getId()}"/>

         
			</article> </slide>
		
		</c:forEach>
		
<!-- 		Test End Summary -->
		<slide class="" id="slide-${lastSlideId + 1}" style="background:;">

			<h4>Test End Summary</h4>
			<article data-timings=""> 
			
			<!--  It should show total question in test, total attempted question and remaining unsoolved question	 -->

<hr>
	
			<input type="submit" value="Submit Test" class="btn btn-primary"/>
        
			</article> </slide>
		
		 </slides>

	     </form:form>
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
	data-main="/ihsmcqbuilder/resources/quizslidify/libraries/frameworks/io2012/js/slides"
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

		initTinyMCE("roClass");
		 for (var i in CKEDITOR.instances) {
             console.log(i);
             var ckName = CKEDITOR.instances[i].name;
          
             var txtArea = document.getElementById(ckName);
             var inptAnsweredId = txtArea.getAttribute("data-pa"); 
             CKEDITOR.instances[i].on('blur', function (){
            	 validateIt(ckName,inptAnsweredId);
             });

         }
	});
</script>

</html>