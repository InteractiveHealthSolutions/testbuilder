var currentIndex = 1;
var divCount = 0
var editCount = 0;
var myCount = 0;

// it hide/show two table rows: "Add More" button row & Question Data row
// it enable/disable "Add More" button
// it will remove all added question data if question type == "1"
// it will add one Question Data if question type is Single/Multi

function updateBtnAddMore() {

	// if question type "text" is selected then remove all options

	console.log("function started");
	if (document.getElementById("slctQuestionType").value == "1") {

		document.getElementById("trQuestionData").style.display = "none";
		document.getElementById("trAddMoreButton").style.display = "none";

		document.getElementById("btnAddMore").disabled = true;

		// Remove all elements because text type of question is selected
		var questionDiv = document.getElementById("divQuestionData");

		// removeChildren(questionDiv.id);
	} else {

		document.getElementById("trQuestionData").style.display = "";
		document.getElementById("trAddMoreButton").style.display = "";

		document.getElementById("btnAddMore").disabled = false;

		// adding one options by default, if already added then do nothing

		var questionDiv = document.getElementById("divQuestionData");

		// removeChildren(questionDiv.id);

		if (questionDiv.children.length <= 0) {
			addMoreOption();
		}
	}

	console.log("function ended");
}

function addMoreOption() {

	$(document)
			.ready(
					function() {

						// if question type "text" is selected then remove all
						// options

						if (document.getElementById("slctQuestionType").value != "1") {

							if (divCount >= 6) {
								var error = document.getElementById("errorLbl");
								error.innerHTML = "Options must be less than or equal to 6"
							} else {
								divCount++;
								var questionDiv = document
										.getElementById("divQuestionData");

								var newDiv = document.createElement("div");

								newDiv.id = "div" + currentIndex;

								// creating div for composing Bootstrap input
								// groups

								var divInputGroup = document
										.createElement("div");
								divInputGroup.className = "input-group";

								/*
								 * var divFormGroup =
								 * document.createElement("div");
								 * divFormGroup.className = "form-group";
								 * divInputGroup.appendChild(divFormGroup);
								 */

								// creating option textbox if it is not image
								// type question
								if (document.getElementById("slctQuestionType").value != "4") {
									var newTextBox = document
											.createElement("input");
									newTextBox.type = "textbox";
									newTextBox.id = "textbox" + currentIndex;
									newTextBox.name = "questionDataList["
											+ currentIndex + "].data";
									newTextBox.className = "form-control";

									// newTextBox.getAttribute("required"); //
									// setting validation
									// newTextBox.setAttribute("required",
									// "true"); // setting validation

									divInputGroup.appendChild(newTextBox);
								}

								var removeButton = document
										.createElement("input");
								removeButton.type = "button";
								removeButton.id = "btnRemove" + currentIndex;
								removeButton.value = "X";
								removeButton.className = "btn btn-primary";

								var spanForButton = document
										.createElement("span");
								spanForButton.className = "input-group-btn";

								// add questiondatalist.iscorrect

								var checkbox = document.createElement('input');
								checkbox.id = currentIndex;
								checkbox.type = "radio";
								checkbox.name = "selectedOption";
								checkbox.style = "margin-left: 50px";
								checkbox.onclick = function() {
									var hiddenField = document
											.getElementById("hid1");
									hiddenField.value = true;
									hiddenField.name = "questionDataList["
											+ checkbox.id + "].correct";
								}
								/*
								 * $("#chkBox" + currentIndex) .on( 'click',
								 * function() { $("#hid1").val(true);
								 * $("#hid1").name("questionDataList[" +
								 * currentIndex + "].correct"); });
								 */

								var myLabel = document.createElement('Label');
								myLabel.id = "radioLbl";
								myLabel.className = "radio control-label";
								myLabel.appendChild(checkbox);

								var radioDiv = document.createElement("div");
								radioDiv.id = "div1"
								radioDiv.className = "radio"
								radioDiv.appendChild(myLabel);

								divInputGroup.appendChild(spanForButton);
								spanForButton.appendChild(removeButton);
								divInputGroup.appendChild(radioDiv);

								var indexHiddenField = document
										.createElement("input");
								indexHiddenField.type = "hidden";
								indexHiddenField.value = currentIndex;
								indexHiddenField.name = "questionDataList["
										+ currentIndex + "].index";

								newDiv.appendChild(divInputGroup);
								newDiv.appendChild(indexHiddenField);

								questionDiv.appendChild(newDiv);

								$("#btnRemove" + currentIndex)
										.on(
												'click',
												function() {
													removeElement($(newDiv)
															.attr('id'));
													divCount--;
													currentIndex--;

													if (divCount <= 7) {
														var error = document
																.getElementById("errorLbl");
														error.innerHTML = "";
													}
												});

								currentIndex++;
							}
						} else {
							// Remove all elements because text type of question
							// is selected
						}
					});

}

function addEditMoreOption() {
	$(document)
			.ready(
					function() {

						// if question type "text" is selected then remove all
						// options

						if (document.getElementById("slctQuestionType").value != "1") {
							editCount = 0;
							myCount = 0;
							for (var j = 1; j <= 50; j++) {
								var str1 = document.getElementById("div" + j);
								if (str1 != undefined) {
									var str = String(str1.id);
									var res = str.split("v");
									var num = parseInt(res[1]);
									editCount = num;
									myCount++;
								}
							}

							if (myCount >= 6) {
								var error = document.getElementById("errorLbl");
								error.innerHTML = "Options must be less than or equal to 6"
							} else {
								editCount++;
								var questionDiv = document
										.getElementById("divQuestionData");

								var newDiv = document.createElement("div");

								newDiv.id = "div" + editCount

								// creating div for composing Bootstrap input
								// groups

								var divInputGroup = document
										.createElement("div");
								divInputGroup.className = "input-group";

								/*
								 * var divFormGroup =
								 * document.createElement("div");
								 * divFormGroup.className = "form-group";
								 * divInputGroup.appendChild(divFormGroup);
								 */

								// creating option textbox if it is not image
								// type question
								if (document.getElementById("slctQuestionType").value != "4") {
									var newTextBox = document
											.createElement("input");
									newTextBox.type = "textbox";
									newTextBox.id = "textbox" + editCount;
									newTextBox.name = "questionDataList["
											+ editCount + "].data";
									newTextBox.className = "form-control";

									// newTextBox.getAttribute("required"); //
									// setting validation
									// newTextBox.setAttribute("required",
									// "true"); // setting validation

									divInputGroup.appendChild(newTextBox);
								}

								var removeButton = document
										.createElement("input");
								removeButton.type = "button";
								removeButton.id = "btnRemove" + editCount;
								removeButton.value = "X";
								removeButton.className = "btn btn-primary";

								var spanForButton = document
										.createElement("span");
								spanForButton.className = "input-group-btn";

								// add questiondatalist.iscorrect

								var checkbox = document.createElement('input');
								checkbox.id = editCount;
								checkbox.type = "radio";
								checkbox.name = "selectedOption";
								checkbox.style = "margin-left: 50px";
								checkbox.onclick = function() {
									var hiddenField = document
											.getElementById("hid1");
									hiddenField.value = true;
									hiddenField.name = "questionDataList["
											+ checkbox.id + "].correct";
								}
								/*
								 * $("#chkBox" + currentIndex) .on( 'click',
								 * function() { $("#hid1").val(true);
								 * $("#hid1").name("questionDataList[" +
								 * currentIndex + "].correct"); });
								 */

								var myLabel = document.createElement('Label');
								myLabel.id = "radioLbl";
								myLabel.className = "radio control-label";
								myLabel.appendChild(checkbox);

								var radioDiv = document.createElement("div");
								radioDiv.id = "div1"
								radioDiv.className = "radio"
								radioDiv.appendChild(myLabel);

								divInputGroup.appendChild(spanForButton);
								spanForButton.appendChild(removeButton);
								divInputGroup.appendChild(radioDiv);

								var indexHiddenField = document
										.createElement("input");
								indexHiddenField.type = "hidden";
								indexHiddenField.value = editCount;
								indexHiddenField.name = "questionDataList["
										+ editCount + "].index";

								newDiv.appendChild(divInputGroup);
								newDiv.appendChild(indexHiddenField);

								questionDiv.appendChild(newDiv);

								$("#btnRemove" + editCount)
										.on(
												'click',
												function() {
													removeElement($(newDiv)
															.attr('id'));

													if (editCount <= 7) {
														var error = document
																.getElementById("errorLbl");
														error.innerHTML = "";
													}
												});
							}
						} else {
							// Remove all elements because text type of question
							// is selected
						}
					});

}

function removeElement(elementId) {

	$(document).ready(function() {

		var questionDiv = document.getElementById("divQuestionData");

		// We have to keep one option atleast for
		if ($("#divQuestionData").children().length > 1) {
			var elem = document.getElementById(elementId);
			elem.parentNode.removeChild(elem);
			return false;
		}
	});
}

function blockSpecialChar(e) {
	var k;
	document.all ? k = e.keyCode : k = e.which;
	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 32 || k == 8 || (k >= 48 && k <= 57));
}

function submitCatForm() {
	var typename = document.getElementById("typeName");
	var description = document.getElementById("descriptionTxt");
	var error = document.getElementById("errorLbl");
	var form = document.getElementById("frmSubmitCategory");

	if (!typename.value.replace(/\s/g, '').length
			|| !description.value.replace(/\s/g, '').length) {
		error.innerHTML = "Required Fields Missing";
	}

	else {
		form.submit();
	}
}

function submitQuesForm() {
	var error = document.getElementById("errorLbl");
	var empty = true;
	var marksBox = document.getElementById("maxMarks");
	var radioCheck = true;
	if (document.getElementById("slctQuestionType").value != "1" && divCount < 2) {
		error.innerHTML = "Options must be greater than or equal to 2";
	}

	else {

		for (var i = 1; i < currentIndex; i++) {
			var tBox = document.getElementById("textbox" + i);
			if (tBox.value == "") {
				empty = false;
			}
		}

		for (var i = 1; i < currentIndex; i++) {
			var rad = document.getElementById(i);
			if (rad.checked == true) {
				radioCheck = true;
				break;
			}

			else {
				radioCheck = false;
			}
		}

		if (document.getElementById("slctQuestionType").value != "1" && empty == false) {
			error.innerHTML = "Values of options missing";
		}

		else if (document.getElementById("slctQuestionType").value != "1" && radioCheck == false) {
			error.innerHTML = "No correct option selected";
		}

		else {
			if (marksBox.value == "" || marksBox.value == null) {
				error.innerHTML = "Marks missing";
			}

			else {
				var createQuestionForm = document
						.getElementById("frmSubmitQuestion");
				createQuestionForm.submit();
			}
		}
	}
}

function submitEditQuesForm() {
	var error = document.getElementById("errorLbl");
	var empty = true;
	var yourCount = 0;
	var marksBox = document.getElementById("maxMarks");
	// var divBox = document.getElementById("textbox2");
	var radioCheck = true;

	for (var j = 1; j <= 50; j++) {
		var str1 = document.getElementById("div" + j);
		if (str1 != undefined) {
			yourCount++;
		}
	}

	if (document.getElementById("slctQuestionType").value != "1" && yourCount < 2) {
		error.innerHTML = "Options must be greater than or equal to 2";
	}

	else {

		for (var i = 1; i < 50; i++) {
			var tBox = document.getElementById("textbox" + i);
			if (tBox != undefined) {
				if (tBox.value == "") {
					empty = false;
				}
			}
		}

		for (var i = 1; i < 50; i++) {
			var rad = document.getElementById(i);
			if (rad != undefined) {
				if (rad.checked == true) {
					radioCheck = true;
					break;
				}
			}

			else {
				radioCheck = false;
			}
		}

		if (document.getElementById("slctQuestionType").value != "1" && empty == false) {
			error.innerHTML = "Values of options missing";
		}

		else if (document.getElementById("slctQuestionType").value != "1" && radioCheck == false) {
			error.innerHTML = "No correct option selected";
		}

		else {
			if (marksBox.value == "" || marksBox.value == null) {
				error.innerHTML = "Marks missing";
			}

			else {
				var editQuestionForm = document
						.getElementById("frmEditQuestion");

				for (var i = 1; i <= 50; i++) {
					var hiddenField = document.getElementById(i);
					if (hiddenField != undefined) {
						if (hiddenField.checked) {
							hiddenField.value = true;
							hiddenField.name = "questionDataList[" + i
									+ "].correct";
						}
					}
				}
				editQuestionForm.submit();
			}
		}
	}
}

function removeChildren(elementId) {
	var targetElement = document.getElementById(elementId);

	while (targetElement.firstChild) {
		targetElement.removeChild(targetElement.firstChild);
	}
}

function setDefaultValues() {
	document.getElementById("slctQuestionType").value = 1;
}

function submitManageCategory(btnId) {
	var r = confirm("Are you sure want to delete this category?");
	if (r == true) {
		var form = document.getElementById("formManageCategory");
		form.action += btnId;
		form.submit();
	}
	else {
		
	}
}

function setNewAnswer(elementId) {
	for ( var item in "questionDataList") {
		if (item.correct === true) {
			item.correct = false;
		}
	}

	var hiddenField = document.getElementById("hid1");
	hiddenField.value = true;
	hiddenField.name = "questionDataList[" + elementId + "].correct";
}

function testing() {
	alert(document.getElementById("slctQuestionType").value);
}
