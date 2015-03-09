/**
 * 
 */

	//"sAjaxSource" : "./PaginationServlet",
			// have to add ability to read dynamically all columns through variable

	

function initializeDataTable(tableId, url, sortingColumn,actionLabel, actionlink) {
	$(document).ready(function() {

		$("#" +tableId).dataTable({
			"bProcessing" : false,
			"bServerSide" : false,
			"sort" : sortingColumn,
		
			"sAjaxSource" : url,					
			"aoColumns" : [ 
            {"mData" : "name"},
            {"mData" : "description"},
            {"mData" : "creationTS"	},
            {"mData" : "action"	}
            			  ],
		   "aoColumnDefs": [
		                   {
		                        "aTargets": [3],
		                        "mData": null,
		                        "mRender": function (data, type, full) 
		                        {
		                        	var a = "<a href=" +actionlink +data+">" + actionLabel +"</a>";
		                            return a;
		                        }
		                    }
		                 ]		
		});

	});
}