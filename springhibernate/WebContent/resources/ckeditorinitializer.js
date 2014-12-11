var isCKEditorSet = false;

//var globalEditor;

// The instanceReady event is fired, when an instance of CKEditor has finished
// its initialization.



function initCKEditor(elementIdToReplace) {
	window.onload = function() {
		var editor = CKEDITOR.replace(elementIdToReplace);
		CKFinder.setupCKEditor(editor, {
			basePath : '/resources/ckeditor',
			rememberLastFolder : false
		});
	};

	// this will hide "Browse Server" button

	window.onload = function() {
		var editor = CKEDITOR
				.replace(
						elementIdToReplace,
						{
							filebrowserUploadUrl : '/springhibernate/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
							filebrowserImageUploadUrl : '/springhibernate/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
							filebrowserFlashUploadUrl : '/springhibernate/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
						});
	};

	isCKEditorSet = true;

}

CKEDITOR.on( 'instanceReady', function( ev ) {
//	 globalEditor = ev.editor;

// Show this "on" button.
//document.getElementById( 'readOnlyOn' ).style.display = '';

// Event fired when the readOnly property changes.
//editor.on( 'readOnly', function() {
//document.getElementById( 'readOnlyOn' ).style.display = this.readOnly ?
//'none' : '';
//document.getElementById( 'readOnlyOff' ).style.display = this.readOnly ? '' :
//'none';
//});

});

function toggleReadOnly(isReadOnly) {
	// Change the read-only state of the editor.
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-setReadOnly
	
	 CKEDITOR.config.readOnly = true;
	 
//	if (isCKEditorSet == true) {
//		globalEditor.setReadOnly(isReadOnly);
//	}
}