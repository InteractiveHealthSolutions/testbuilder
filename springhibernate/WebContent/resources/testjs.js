function getQuestionData() {

		var selectedValues = [];
		$("#slctQuestion :selected").each(function() {
			selectedValues.push($(this).val());
		});

		if (selectedValues.length > 0) {
			$.ajax({
				url : 'getQuestion',
				data : "questionId="
						+ selectedValues[selectedValues.length - 1],
				success : function(data) {
					renderDataOption(data);
					// call renderData function here
				}
			});
		}
}

function renderDataOption(rawJSON) {

		// Remove table if exist
		var questionDiv = document.getElementById("divQuestionData");

		removeChildren(questionDiv.id);

		var table = document.createElement("table");
		table.setAttribute("border", "1");

		/// creating 1st row
		var tr = table.insertRow();
		//var th1 = tr.insertHeader();
		var th1 = document.createElement('th');
		th1.appendChild(document.createTextNode("Question Type"));
		tr.appendChild(th1);

		//	var th2 = tr.insertHeader();
		var th2 = document.createElement('th');
		th2.appendChild(document.createTextNode("Question Data"));
		tr.appendChild(th2);

		/// creating 2nd row
		var tr2 = table.insertRow();
		var td1 = tr2.insertCell();

		var jsonData = JSON.parse(rawJSON);
		var count = jsonData.dataArray.length;

		/// wiriting question Type
		td1.setAttribute("rowSpan", count);
		console.log(jsonData.type);
		var text = document.createTextNode(jsonData.type);
		td1.appendChild(text);

		/// writing first data type
		var td2 = tr2.insertCell();
		var text2 = document.createTextNode(jsonData.dataArray[0].data);
		td2.appendChild(text2);

		for (i = 0; i < jsonData.dataArray.length; i++) {

			if (i != 0) {
				var tr3 = table.insertRow();
				var td3 = tr3.insertCell();
				td3.appendChild(document
						.createTextNode(jsonData.dataArray[i].data));
			}
		}

		var div = document.getElementById("divQuestionData");
		div.appendChild(table);

		// 		var sel = document.getElementById('slctQuestion');
		// 		var selected = sel.options[sel.selectedIndex];
		// 		var dataIndex = selected.getAttribute('data-index');
	}

	function removeChildren(elementId) {
		var targetElement = document.getElementById(elementId);

		while (targetElement.firstChild) {
			targetElement.removeChild(targetElement.firstChild);
		}
	}