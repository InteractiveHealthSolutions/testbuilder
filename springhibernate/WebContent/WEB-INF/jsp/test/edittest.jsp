<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Header tag is called -->

<t:header />

<link href="<c:url value='/resources/jquerymultiselect/css/multi-select.css'/>" media="screen" rel="stylesheet"
  type="text/css"
>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/testjs.js'/>"></script>

<script type="text/javascript" src="<c:url value='/resources/jquerymultiselect/js/jquery.multi-select.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/jquerymultiselect/jquerymsinitializer.js'/>"></script>
<script>
	$(document).ready(function() {
		initJQMultiSelect("slctQuestion");
	});
</script>

<script>
	// 	function renderDataOption(rawJSON) {

	// 		// Remove table if exist
	// 		var questionDiv = document.getElementById("divQuestionData");

	// 		removeChildren(questionDiv.id);

	// 		var table = document.createElement("table");
	// 		table.setAttribute("border", "1");

	// 		/// creating 1st row
	// 		var tr = table.insertRow();
	// 		//var th1 = tr.insertHeader();
	// 		var th1 = document.createElement('th');
	// 		th1.appendChild(document.createTextNode("Question Type"));
	// 		tr.appendChild(th1);

	// 		//  var th2 = tr.insertHeader();
	// 		var th2 = document.createElement('th');
	// 		th2.appendChild(document.createTextNode("Question Data"));
	// 		tr.appendChild(th2);

	// 		/// creating 2nd row
	// 		var tr2 = table.insertRow();
	// 		var td1 = tr2.insertCell();

	// 		var jsonData = JSON.parse(rawJSON);
	// 		var count = jsonData.dataArray.length;

	// 		/// wiriting question Type
	// 		td1.setAttribute("rowSpan", count);
	// 		console.log(jsonData.type);
	// 		var text = document.createTextNode(jsonData.type);
	// 		td1.appendChild(text);

	// 		/// writing first data type
	// 		var td2 = tr2.insertCell();
	// 		var text2 = document.createTextNode(jsonData.dataArray[0].data);
	// 		td2.appendChild(text2);

	// 		for (i = 0; i < jsonData.dataArray.length; i++) {

	// 			if (i != 0) {
	// 				var tr3 = table.insertRow();
	// 				var td3 = tr3.insertCell();
	// 				td3.appendChild(document
	// 						.createTextNode(jsonData.dataArray[i].data));
	// 			}
	// 		}

	// 		var div = document.getElementById("divQuestionData");
	// 		div.appendChild(table);

	// // 		  // var sel = document.getElementById('slctQuestion');
	// // 		  // var selected = sel.options[sel.selectedIndex];
	// // 		  // var dataIndex = selected.getAttribute('data-index');
	// 	}

	// 	function getQuestionData() {

	// 		var selectedValues = [];
	// 		$("#slctQuestion :selected").each(function() {
	// 			selectedValues.push($(this).val());
	// 		});

	// 		if (selectedValues.length > 0) {
	// 			$.ajax({
	// 				url : 'getQuestion',
	// 				data : "questionId="
	// 						+ selectedValues[selectedValues.length - 1],
	// 				success : function(data) {
	// 					renderDataOption(data);
	// 					// call renderData function here
	// 				}
	// 			});
	// 		}
	// 	}

	// 	function removeChildren(elementId) {
	// 		var targetElement = document.getElementById(elementId);

	// 		while (targetElement.firstChild) {
	// 			targetElement.removeChild(targetElement.firstChild);
	// 		}
	// 	}
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
        <h3>Edit Test</h3>

        <form:form id="frmSubmitQuestion" method="POST" action="/springhibernate/test/edittest" modelAttribute="editTest">

          <table border="1px" class="table table-bordered">

            <tr>
              <form:hidden path="id" value="${editTest.getId()}" />
              <th>Test Name</th>
              <td><form:input path="name" size="50" maxlength="90" class="form-control input" required="true" /></td>
              <td><form:errors path="name" /></td>

            </tr>

            <tr>
              <th>Test Description</th>
              <td><form:textarea path="description" class="form-control input" required="true" /> 
              <input type="hidden" name="creatorId" value="${currentUser.getId()}" />
              </td>

              <td><form:errors path="description" /></td>

            </tr>
            <tr>
              <th>Comments for Test Maker</th>
              <td colspan="2"><form:textarea path="comments" class="form-control input" /></td>
            </tr>

            <tr>
              <th>Select Question</th>
              <th colspan="2">Question Data</th>
            </tr>

            <tr>
              <td><form:select id="slctQuestion" path="questionList" items="${loadedQuestionList}"
                  itemLabel="title" itemValue="id" size="10" onclick="getQuestionData()" class="form-control input" />
                  </td>
              <td height="350px" width="400px" colspan="2">

                <div id="divQuestionData"></div>
              </td>
            </tr>

            <tr>
              <td colspan="3">${status}</td>
            </tr>

            <tr>
              <td colspan="3">
                <div class="control-group">

                  <div class="col-md-6">
                    <div class="text-center">
                      <input type="submit" value="Save" id="singlebutton" name="singlebutton" class="btn btn-primary" />
                    </div>

                  </div>

                  <div class="col-md-6">
                    <div class="text-center">
                      <a href="managetest"><input type="button" class="btn btn-default" value="Cancel & Go Back" /></a>
                    </div>
                  </div>

                </div>
              </td>
            </tr>

          </table>
        </form:form>

      </div>
    </div>
  </div>

  <t:footer />
</body>
</html>