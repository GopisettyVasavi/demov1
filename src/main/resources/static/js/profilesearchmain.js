var j=1;
function clickonsearch(){
	  event.preventDefault();
	      if($("#candidateName").val()=="" && $("#primaryEmail").val()==""
	        		&& $("#primaryContactNo").val()=="" && $("#skill").val() =="" && $("#availability").val()=="" && $("#currentLocation").val()==""){
	        	
	        	alert("Please enter at least one value to search profiles.");
	        }
	        else{
    var searchForm = {}
    searchForm["candidateName"] = $("#candidateName").val();
    searchForm["primaryEmail"] = $("#primaryEmail").val();
    searchForm["primaryContactNo"] = $("#primaryContactNo").val();
    searchForm["skill"] = $("#skill").val();
    searchForm["availability"] = $("#availability").val();
    searchForm["currentLocation"] = $("#currentLocation").val();
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/searchcandidates",
        data: JSON.stringify(searchForm),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	     
        	 /*$('#paginatedTable tfoot th').each( function () {
        	        var title = $(this).text();
        	        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
        	    } );
        	 */
        	
        	$('#paginatedTable thead th').each( function () {
                var title = $('#paginatedTable tfoot th').eq( $(this).index() ).text();
                $(this).html('<input type="text" placeholder="Search '+title+'" />' );
            } );
         
            // DataTable
        	 var table=   $('#paginatedTable').DataTable( {
            	   destroy: true,
            	  orderCellsTop: true,
     	        fixedHeader: true,
                   data: data,
                   
                   columns: [
                	   { "data": 'candidateName', "name" : "Name", "title" : "Name"  },
                       { "data": 'primaryEmail', "name" : "Email" , "title" : "Email"},
                       { "data": 'primaryPhone', "name" : "ContactNo" , "title" : "ContactNo"},
                       { "data": 'skills', "name" : "Skill" , "title" : "Skill"},
                       { "data": 'availability', "name" : "availability" , "title" : "Availability"},
                       { "data": 'assignedToEmployeeName', "name" : "AssignedTo" , "title" : "Assigned To"}]
                      
               } );
         
            // Apply the search
            table.columns().eq( 0 ).each( function ( colIdx ) {
                $( 'input', table.column( colIdx ).header() ).on( 'keyup change', function () {
                    table
                        .column( colIdx )
                        .search( this.value )
                        .draw();
                } );
            } );
       
        	
        	
        	    // DataTable
        	
        	 
        	  
        	// table.search('').columns().search('').draw();
        	 /*
        	 $('div.dataTables_filter input').unbind();
             $('div.dataTables_filter input').bind('keyup', function(e) {
                 if(e.keyCode == 13) {
                     table.fnFilter(this.value);
                 }
             });
        	   */
        	  

        },
        error: function (e) {
        	

            var json = "<h4>Response Error:Error occured while searching for candidates.</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
           // $("#btn-search").prop("disabled", false);

        }
    }); 
	 }
    
}