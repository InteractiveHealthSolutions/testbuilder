var array = [];

$(document).ready(function() {
	// alert('dfssdfsdfs')
	var selectTag = document.getElementById('selectCategoryList');
	var k = selectTag.options;
	for (var j = 0; j < selectTag.length; j++) {
		array.push(k[j].value);
	}
});

function getQuestionData() {
	var selectedValues = [];
	$("#slctQuestion :selected").each(function() {
		selectedValues.push($(this).val());
	});

	if (selectedValues.length > 0) {
		$.ajax({
			url : 'getQuestion',
			data : "questionId=" + selectedValues[selectedValues.length - 1],
			success : function(data) {
				renderDataOption(data);
				// call renderData function here
			}
		});
	}
}

function checkError() {

}

function addMoreCategories() {

	var selectedItem = document.getElementById('selectCategoryList');
	var selectedItemValue = selectedItem.options[selectedItem.selectedIndex];
	var deletedValue = selectedItem.selectedIndex;

	// alert(currentIndex);
	var mainDiv = document.getElementById("dataDiv");

	var categoryDiv = document.createElement("div");
	categoryDiv.id = "div" + selectedItemValue.value;

	var inputDiv = document.createElement("div");
	inputDiv.className = "input-group";

	var categoryLabel = document.createElement("Label");
	categoryLabel.style.width = "220px";
	categoryLabel.id = selectedItemValue.value;
	categoryLabel.innerHTML = selectedItemValue.text;

	var percentageBox = document.createElement("input");
	percentageBox.type = "number";
	percentageBox.min = "1";
	percentageBox.name = "percentage" + selectedItemValue.value;
	percentageBox.max = "100";
	percentageBox.id = "percentage" + selectedItemValue.value;
	percentageBox.style.width = "50px";

	var percentageLabel = document.createElement("Label");
	percentageLabel.innerHTML = "%";
	percentageLabel.className = "label label-primary";
	percentageLabel.style.marginLeft = "5px";

	var removeButton = document.createElement("input");
	removeButton.type = "button";
	removeButton.id = "btnRemove" + selectedItemValue.value;
	removeButton.value = "X";
	removeButton.className = "btn btn-primary";
	removeButton.style.marginLeft = "28px";

	inputDiv.appendChild(categoryLabel);
	inputDiv.appendChild(percentageBox);
	inputDiv.appendChild(percentageLabel);
	inputDiv.appendChild(removeButton);

	categoryDiv.appendChild(inputDiv);
	mainDiv.appendChild(categoryDiv);
	inputDiv.style.marginTop = "10px";

	var selectList = document.getElementById("selectCategoryList");
	selectList.remove(deletedValue);

	$("#btnRemove" + selectedItemValue.value).on(
			'click',
			function() {
				removeElement($(categoryDiv).attr('id'), $(categoryLabel)
						.text(), $(categoryLabel).attr('id'));
			});
}

function removeElement(elementId, text, id) {

	$(document).ready(function() {

		var questionDiv = document.getElementById("dataDiv");
		var reAdd = document.getElementById("selectCategoryList");
		var option = document.createElement("option");
		option.value = id;
		option.text = text;
		// alert(option.text);
		reAdd.add(option);
		var elem = document.getElementById(elementId);
		elem.parentNode.removeChild(elem);
		return false;
	});
}

function checkError(btnType) {
	var err = document.getElementById("errorLabel");
	var schemeName = document.getElementById("nameTxt");
	var schemeDescription = document.getElementById("descriptionTxt");
	var schemeQuestions = document.getElementById("questionTxt");
	var totalSum = 0;
	var selectTag = document.getElementById('selectCategoryList');

	if (schemeName.value == "") {
		err.innerText = "Scheme Name Missing!!";
		err.style.display = "block";
	}

	else if (schemeDescription.value == "") {
		err.style.display = "none";
		err.innerText = "Scheme Description Missing!!";
		err.style.display = "block";
	}

	else if (schemeQuestions.value == "" || schemeQuestions.value == 0) {
		err.style.display = "none";
		err.innerText = "No Of Questions Incorrect or Missing!!";
		err.style.display = "block";
	}

	else {
		err.style.display = "none";
		for (var b = 0; b < array.length; b++) {

			var checkId = "percentage" + array[b];

			if (document.getElementById(checkId) == null) {
			}

			else {
				var perBox = document.getElementById(checkId);
				totalSum += Number(perBox.value);
			}
		}

		if (totalSum == 100) {
			var form = document.getElementById("formSubmitScheme");
			form.action += btnType;
			form.submit();
		}

		else if (totalSum == 0) {
			err.innerText = "No Categories Selected or No Percentage Selected For Categories!!";
			err.style.display = "block";
		}

		else {
			err.style.display = "none";
			err.innerText = "Incorrect Percentage Of Categories!!";
			err.style.display = "block";
		}
	}

}

function submitFormByType(typeBtn) {
	var name = document.getElementById("formSubmitTest");

	if (typeBtn == "print") {
		while (true) {
			var fileName = prompt("Please enter file name", "");
			if (fileName != "") {
				break;
			}
			else {
			  alert("Invalid file name");
			}
		}

		name.action += typeBtn + "/" + fileName;
		alert("Test has been saved in Downloads folder");
		name.submit();
	}

	else {
		name.action += typeBtn;
		name.submit();
	}
}

function submitScheme(){
	var schemeForm = document.getElementById("selectScheme");
	var finalForm = document.getElementById("selecttestScheme");
	finalForm.action += schemeForm.options[schemeForm.selectedIndex].value;
	finalForm.submit();
}

function renderDataOption(rawJSON) {

	// Remove table if exist
	var questionDiv = document.getElementById("divQuestionData");

	removeChildren(questionDiv.id);

	var table = document.createElement("table");
	table.setAttribute("border", "1");

	// / creating 1st row
	var tr = table.insertRow();
	// var th1 = tr.insertHeader();
	var th1 = document.createElement('th');
	th1.appendChild(document.createTextNode("Question Type"));
	tr.appendChild(th1);

	// var th2 = tr.insertHeader();
	var th2 = document.createElement('th');
	th2.appendChild(document.createTextNode("Question Data"));
	tr.appendChild(th2);

	// / creating 2nd row
	var tr2 = table.insertRow();
	var td1 = tr2.insertCell();

	var jsonData = JSON.parse(rawJSON);
	var count = jsonData.dataArray.length;

	// / wiriting question Type
	td1.setAttribute("rowSpan", count);
	console.log(jsonData.type);
	var text = document.createTextNode(jsonData.type);
	td1.appendChild(text);

	// / writing first data type
	var td2 = tr2.insertCell();
	var text2 = document.createTextNode(jsonData.dataArray[0].data);
	td2.appendChild(text2);

	for (i = 0; i < jsonData.dataArray.length; i++) {

		if (i != 0) {
			var tr3 = table.insertRow();
			var td3 = tr3.insertCell();
			td3
					.appendChild(document
							.createTextNode(jsonData.dataArray[i].data));
		}
	}

	var div = document.getElementById("divQuestionData");
	div.appendChild(table);

	// var sel = document.getElementById('slctQuestion');
	// var selected = sel.options[sel.selectedIndex];
	// var dataIndex = selected.getAttribute('data-index');
}

function removeChildren(elementId) {
	var targetElement = document.getElementById(elementId);

	while (targetElement.firstChild) {
		targetElement.removeChild(targetElement.firstChild);
	}
}