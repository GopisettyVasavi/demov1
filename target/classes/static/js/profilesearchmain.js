
$(document).on("click","#paginatedTable tbody tr", function (e)

		{
	var table = $('#paginatedTable').DataTable();
		   var rowData = table.row( this ).data();
		   $.ajax({
		        type: "GET",
		        contentType: "application/json",
		        url: "/candidatedetails",
		        cache: false,
		        timeout: 600000,
		        success: function (data) {
		        	
		           //alert("pageloaded"+rowData.candidateId);
		          // alert("page loaded.."+data[0]);
		          var win = window.open("/candidatedetails/"+rowData.candidateId);
		        	// var win = window.open("/profileparser");
		           win.focus();
		            

		        },
		        error: function (e) {
		        	
		        	alert("Unable to load details");

		        }
		    });
		});
function clickonsearch(){
	//alert( $("#currentLocation_s").val());
	  event.preventDefault();
	      if($("#candidateName_s").val()=="" && $("#primaryEmail_s").val()==""&& $("#primaryContactNo_s").val()=="" 
	    	  && $("#skill_s").val() =="" && $("#availability_s").val()=="" && $("#currentLocation_s").val()=="" 
	        	&&	$("#workexperience_s").val() =="" && $("#certification_s").val()=="" &&
	        	( $("#visatype_s").val()=="" || $("#visatype_s").val()==null|| $("#visatype_s").val()=="null"||$("#visatype_s").val()=="none")){
	        	
	        	alert("Please enter at least one value to search profiles.");
	        }
	        else{
    var searchForm = {}
    searchForm["candidateName"] = $("#candidateName_s").val();
    searchForm["primaryEmail"] = $("#primaryEmail_s").val();
    searchForm["primaryContactNo"] = $("#primaryContactNo_s").val();
    searchForm["skill"] = $("#skill_s").val();
    searchForm["availability"] = $("#availability_s").val();
    searchForm["currentLocation"] = $("#currentLocation_s").val();
    if($("#visatype_s").val()!="none"){
    searchForm["visaType"] = $("#visatype_s").val();}
    searchForm["workExperience"] = $("#workexperience_s").val();
    searchForm["certification"] = $("#certification_s").val();
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/searchcandidates",
        data: JSON.stringify(searchForm),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	 /*
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
                      
               } );*/
        	
        	 $('#paginatedTable tfoot th').each( function () {
        	        var title = $(this).text();
        	        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
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
                     { "data": 'currentLocation', "name" : "CurrentLocation" , "title" : "Location"},
                     { "data": 'visaType', "name" : "VisaType" , "title" : "Visa Type"},
                     { "data": 'availability', "name" : "availability" , "title" : "Availability"},
                     { "data": 'assignedToEmployeeName', "name" : "AssignedTo" , "title" : "Assigned To"}]
                    
             } );
        	  $("#table_foot").hide();
        	    // Apply the search
        	    table.columns().every( function () {
        	        var that = this;
        	 
        	        $( 'input', this.footer() ).on( 'keyup change clear', function () {
        	            if ( that.search() !== this.value ) {
        	                that
        	                    .search( this.value.replace(/;/g, "|"),true,false )
        	                    .draw();
        	            }
        	        } );
        	      
        	    } );
        	    $('#paginatedTable tfoot tr').appendTo('#paginatedTable thead');
        	    
        	    $("#table_foot").hide();

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

