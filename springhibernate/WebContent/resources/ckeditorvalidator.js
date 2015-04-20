function validateCKEDITORforBlank(field) {
	var vArray = new Array();
	vArray = field.split("&nbsp;");
	var vFlag = 0;
	for (var i = 0; i < vArray.length; i++) {
		if (vArray[i] == '' || vArray[i] == "") {
			continue;
		} else {
			vFlag = 1;
			break;
		}
	}
	if (vFlag == 0) {
		return true;
	} else {
		return false;
	}
}

function validateIt(e, id) {

				 var inptElement = document.getElementById(id);
				// If instance of CKEditor is empty then set value of answered as false
					if (validateCKEDITORforBlank($.trim(CKEDITOR.instances[e].getData().replace(/<[^>]*>|\s/g, ''))))
				{				
					inptElement.value = false;
				}

				else 
				{
					inptElement.value = true;
				}

}
