function initTinyMCE(elementClassToReplace) {
tinymce.init({      
        mode : "specific_textareas",
        editor_selector : elementClassToReplace,
       theme : "advanced",
	    readonly : true
    });
}