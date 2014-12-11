var currentIndex = 1;

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

    //removeChildren(questionDiv.id);
  } else {

    document.getElementById("trQuestionData").style.display = "";
    document.getElementById("trAddMoreButton").style.display = "";

    document.getElementById("btnAddMore").disabled = false;

    // adding one options by default, if already added then do nothing

    var questionDiv = document.getElementById("divQuestionData");

    //	removeChildren(questionDiv.id);

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

        // if question type "text" is selected then remove all options

        if (document.getElementById("slctQuestionType").value != "1") 
        {

          var questionDiv = document.getElementById("divQuestionData");

          var newDiv = document.createElement("div");

          newDiv.id = "div" + currentIndex;
          
          // creating div for composing Bootstrap input groups

          var divInputGroup = document.createElement("div");         
          divInputGroup.className = "input-group";

          // creating option textbox if it is not image type question          
          
          if (document.getElementById("slctQuestionType").value != "4") 
          {
            var newTextBox = document.createElement("input");
            newTextBox.type = "textbox";
            newTextBox.name = "questionDataList[" + currentIndex + "].data";
            newTextBox.className = "form-control";
            
      
//			 newTextBox.getAttribute("required");	// setting validation
//			 newTextBox.setAttribute("required", "true"); // setting validation
            
            divInputGroup.appendChild(newTextBox);
          }        

          var removeButton = document.createElement("input");
          removeButton.type = "button";
          removeButton.id = "btnRemove" + currentIndex;
          removeButton.value = "X";
          removeButton.className = "btn btn-primary";
          
          var spanForButton = document.createElement("span");
          spanForButton.className = "input-group-btn";        

          divInputGroup.appendChild(spanForButton);
          spanForButton.appendChild(removeButton);
          
          var indexHiddenField = document.createElement("input");
          indexHiddenField.type = "hidden";
          indexHiddenField.value = currentIndex;
          indexHiddenField.name = "questionDataList[" + currentIndex + "].index";
          
          newDiv.appendChild(divInputGroup);
          newDiv.appendChild(indexHiddenField);
          
          questionDiv.appendChild(newDiv);

          $("#btnRemove" + currentIndex)
            .on(
              'click',
              function() {
                removeElement($(newDiv)
                  .attr('id'));
              });

          currentIndex++;
        } else {
          // Remove all elements because text type of question is selected
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

function removeChildren(elementId) {
  var targetElement = document.getElementById(elementId);

  while (targetElement.firstChild) {
    targetElement.removeChild(targetElement.firstChild);
  }
}

function setDefaultValues() {
  document.getElementById("slctQuestionType").value = 1;
}

function testing() {
  alert(document.getElementById("slctQuestionType").value);
}

