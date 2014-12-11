<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
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

        <h3>Manage Questions</h3>

        <c:url var="createQuestion" value="${resources.JSP_CREATE_QUESTION}">
        </c:url>
        <h4>
          <a href="<c:out value="${createQuestion}"/>">Create New Question</a>
        </h4>

        <div>
          <table border="1px" class="table table-bordered">
            <tr>
              <th>Question Type</th>
              <th>Question Title</th>
              <th>Question Data</th>
              <th>Action</th>
            </tr>

            <c:forEach var="question" items="${questionList}">
              <!--    If question is Single/Multi select type  -->
              <c:choose>


                <c:when test="${question.getQuestionType().getId() != 1 }">
                  <tr>
                    <td rowspan="${question.getQuestionDataList().size()}">${question.getQuestionType().getTypeName()}</td>
                    <td rowspan="${question.getQuestionDataList().size()}">${question.getTitle()}</td>
                    <td>${question.getQuestionDataList().get(0).getData()}</td>

                    <c:url var="editUrl" value="${resources.JSP_EDIT_QUESTION}">
                      <c:param name="id" value="${question.getId()}" />
                    </c:url>

                    <td rowspan="${question.getQuestionDataList().size()}">
                    <a href="<c:out value="${editUrl}"/>">Edit</a> | <c:url var="detailUrl"
                        value="${resources.JSP_VIEW_QUESTION}">
                        
                        <c:param name="id" value="${question.getId()}" />
                      </c:url> <a href="<c:out value="${detailUrl}"/>">Details</a>
                    
                    
                    </td>
                  </tr>
                  <c:forEach var="questionData" items="${question.getQuestionDataList()}" varStatus="status">

                    <c:if test="${status.index != 0 }">
                      <tr>
                        <td>${questionData.getData()}</td>
                      </tr>
                    </c:if>

                  </c:forEach>

                </c:when>
                <c:otherwise>
                  <tr>
                    <td>${question.getQuestionType().getTypeName()}</td>
                    <td colspan="2">${question.getTitle()}</td>

                    <c:url var="editUrl" value="${resources.JSP_EDIT_QUESTION}">
                      <c:param name="id" value="${question.getId()}" />
                    </c:url>

                    <td><a href="<c:out value="${editUrl}"/>">Edit</a> | <c:url var="detailUrl"
                        value="${resources.JSP_VIEW_QUESTION}">
                        
                        <c:param name="id" value="${question.getId()}" />
                      </c:url> <a href="<c:out value="${detailUrl}"/>">Details</a>
                      
                    </td>
                  </tr>

                </c:otherwise>

              </c:choose>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
  </div>
    <t:footer />
</body>
</html>