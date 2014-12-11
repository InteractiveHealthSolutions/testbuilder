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
<script src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/ckfinder/ckfinder.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/ckeditorinitializer.js'/>"></script>

<script>
	$(document).ready(function() {
		initCKEditor("description");
		toggleReadOnly();

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
        <h3>Question Details</h3>

        <table border="1px" class="table table-bordered">

          <tr>
            <th>Question Type</th>
            <td>${detailQuestion.getQuestionType().getTypeName()}</td>
          </tr>
          <tr>
            <th>Question Title</th>

            <td>${detailQuestion.getTitle()}</td>

          </tr>

          <tr>
            <th>Question Description</th>
            <td><textarea name="description" readonly>${detailQuestion.getDescription()}</textarea> <!-- <textarea name="description" readonly></textarea> -->
            </td>

          </tr>

          <!-- If question is Sinlge/Multi select then define its options -->
          <c:if test="${detailQuestion.getQuestionType().getId() != 1}">
            <tr>
              <th>${detailQuestion.getQuestionType().getTypeName()} Options</th>
              <td>
                <ul>

                  <c:forEach var="data" items="${detailQuestion.getQuestionDataList()}" varStatus="index2">

                    <li>${data.getData()}</li>
                    <br />

                  </c:forEach>
                </ul>
              </td>
            </tr>
          </c:if>

          <tr>
            <th>Max marks</th>
            <td>${detailQuestion.getMaxMarks()}</td>

          </tr>

          <tr>
            <th>Comments for Test Maker</th>
            <td>${detailQuestion.getCommentsForMaker()}</td>
          </tr>

          <tr>
            <td colspan="2">${status}</td>
          </tr>
          
          <tr>
          <td colspan="2">
                     <div class="control-group">   
                            
<!--               <div class="col-md-6"> -->
               <div class="text-center">
              <a href="managequestion"><input type="button" class="btn btn-default" value="Go Back" /></a>
<!--               </div> -->
              </div>
              
            </div>
          </td>
          </tr>


        </table>

      </div>
    </div>

  </div>

  <t:footer />

</body>
</html>