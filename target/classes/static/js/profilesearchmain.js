

function searchcskill(){
	
	//$('#example').DataTable().column(0).search('^' + searchString + '$',true).draw()

	//alert("Invoked..."+$("#mySearch").val());
	var table = $('#paginatedTable').DataTable();
	 var whatsSelected = [];
	if($("#mySearch").val().includes("AND")){
		var andname=$("#mySearch").val().split("AND");
		for(var i = 0; i < andname.length; i++){
			whatsSelected.push('(?=.*' +andname[i] + ')');
			/* $.fn.dataTable.ext.search.push(function(settings, data, dataIndex) {
			       //search only in column 1 and 2
			       if (~data[0].toLowerCase().indexOf(andname[i])) return true;
			       if (~data[1].toLowerCase().indexOf(searchTerm)) return true;
			       return false;
			   })
			   table.draw(); 
			   $.fn.dataTable.ext.search.pop();*/
		 
	     
	       //table.column(0).search( andname[], true);
	     }
		$('#paginatedTable').DataTable().column(0).search(whatsSelected.join('|'), true, false, true).draw();
		//alert(andname.length);
	}
	if($("#mySearch").val().includes("NOT")){
		var notname=$("#mySearch").val().split("NOT");
		table.column(0).search( function () {
	        var that = this;
	  
	       // $( 'input', this.footer() ).on( 'keyup change', function () {
	        for(var i = 0; i < notname.length; i++){
	        	alert(that.search());
	            if ( that.search() !== notname[i] ) {
	                that
	                    .search( notname[i] )
	                    .draw();
	            }
	            }
	       } );
	  //  } );
		
		//var whatsNotSelected = [];
		//alert(notname.length+ notname)
		/*for(var i = 0; i < notname.length; i++){
			
			table.column(0).search( !notname[i], true, true, true);
			
	     }*/
		//$('#paginatedTable').DataTable().column(0).search(whatsNotSelected.join('|'), true, false, true).draw();
		//alert(andname.length);
	}else{
		table.column(0).search($("#mySearch").val(), true);
		  table.draw();
	}
	
   
	
}
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
	$('#feedback').html("");
	  event.preventDefault();
	      if($("#candidateName_s").val()=="" && $("#primaryEmail_s").val()==""&& $("#primaryContactNo_s").val()=="" 
	    	  && $("#skill_s").val() =="" && $("#availability_s").val()=="" && $("#currentLocation_s").val()=="" 
	        	&&	$("#workexperience_s").val() =="" && $("#certification_s").val()=="" &&
	        	( $("#visatype_s").val()=="" || $("#visatype_s").val()==null|| $("#visatype_s").val()=="null"||$("#visatype_s").val()=="none")){
	  		document.getElementById("feedback").style.color = "red";

	    	  $('#feedback').html("Please enter at least one value to search profiles.");
	        	//alert("Please enter at least one value to search profiles.");
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
        	 // DataTable
        	 var table=   $('#paginatedTable').DataTable( {
        		 
        		 responsive: true,
          	   destroy: true,
          	  orderCellsTop: true,
          	autoWidth: false,
          	/*targets: 'no-sort',
          	bSort: false,
          	order: [],*/
          	 dom: 'Bfrtip',
             buttons: [
                 'colvis'
             ],
             renderer: { "header": "bootstrap" },
                 data: data,
                 
                 columns: [
              	   { "data": 'candidateName', "name" : "Name", "title" : "Name"  },
                     { "data": 'primaryEmail', "name" : "Email" , "title" : "Email"},
                     { "data": 'primaryPhone', "name" : "ContactNo" , "title" : "Contact No"},
                     { "data": 'skills', "name" : "Skill" , "title" : "Skill"},
                     { "data": 'currentLocation', "name" : "CurrentLocation" , "title" : "Location"},
                     { "data": 'visaType', "name" : "VisaType" , "title" : "Visa Category"},
                     { "data": 'visaNo', "name" : "VisaNo" , "title" : "Visa Type"},
                     { "data": 'availability', "name" : "availability" , "title" : "Availability"},
                     { "data": 'education', "name" : "Education" , "title" : "Education"},
                     { "data": 'workExperience', "name" : "WorkExperience" , "title" : "Work Experience"},
                     { "data": 'certification', "name" : "certification" , "title" : "Certification"},
                     { "data": 'assignedToEmployeeName', "name" : "AssignedTo" , "title" : "Assigned To"},
                     { "data": 'assignedDate', "name" : "assignedDate" , "title" : "Assigned Date"},
                     { "data": 'employedByRen', "name" : "employedByRen" , "title" : "Employed By Ren"},
                     { "data": 'awards', "name" : "awards" , "title" : "Awards"},
                     { "data": 'additionalNotes', "name" : "additionalNotes" , "title" : "Additional Notes"}]
                    
             } );
        	 
        	 //$('#paginatedTable').dataTable( { renderer: { "header": "bootstrap" } } ); 
        	 
        	 
        	 /* $('a.toggle-vis').on( 'click', function (e) {
        	        e.preventDefault();
        	 
        	        // Get the column API object
        	        var column = table.column( $(this).attr('data-column') );
        	 
        	        // Toggle the visibility
        	        column.visible( ! column.visible() );
        	    } );
        	 */
        	 $('#paginatedTable tfoot th').each( function () {
     	        var title = $(this).text();
     	        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
     	    } );
        	 
        	 
        	 
        	//  $("#table_foot").hide();
        	    // Apply the search
        	    table.columns().every( function () {
        	        var that = this;
        	      //  alert("that ..."+that);
        	        $( 'input', this.footer() ).on( 'keyup change clear', function () {
        	        	//alert(this.value);
        	        	if(this.value.includes(";AND;")){
        	        		//alert(this.value+" "+that.search())
        	        		 if ( that.search() !== this.value ) {
             	                that
             	                    .search( this.value.replace(/;AND;/g, "&"),true,false )
             	                    .draw();
             	            }
        	        	}
        	        	if(this.value.includes(";OR;")){
        	        		//alert(this.value+" "+that.search())
        	        		 if ( that.search() !== this.value ) {
             	                that
             	                    .search( this.value.replace(/;OR;/g, "|"),true,false )
             	                    .draw();
             	            }
        	        	}
        	        	
        	        	else{
        	        		 if ( that.search() !== this.value ) {
              	                that
              	                    .search( this.value.replace(/;/g, "|"),true,false )
              	                    .draw();
        	        	}
        	        	}
        	           
        	        } );
        	      
        	    } );
        	   $('#paginatedTable tfoot tr').appendTo('#paginatedTable thead');
        	    
        	    $("#paginatedTable tfoot tr").hide();
         	   // $("#paginatedTable_foot").style.visibility='block';

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

