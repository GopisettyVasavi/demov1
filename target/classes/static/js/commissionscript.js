function initialize(){
	$("#commissions_feedback").hide();
	$("#commissions_div").hide();
	$("#commissiontable").hide();
}

function backToIndex() {
	window.location = '/index'
}
function createCommission(){
	event.preventDefault();
	//alert($("#commissionMonth").val());
	if($("#commissionMonth").val()!=''){
		var monthyear=$("#commissionMonth").val();
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/createcommission/"+monthyear,
			cache : false,
			timeout : 600000,
			success : function(data) {
				//alert(data);
				//alert("success");
				$("#commissions_div").show();
				$("#commissiontable").show();
				var i=0;
				
				var table = $('#commissiontable').DataTable({

					destroy : true,

					autoWidth : false,
					/*
					 * targets: 'no-sort', bSort: false, order: [],
					 */

					buttons : [ 'colvis' ],
					renderer : {
						"header" : "bootstrap"
					},
					data : data,

					columns : [ {
						"data" : 'id',
						"name" : "S.No",
						"title" : "S.No"
					}, {
						"data" : 'fullName',
						"name" : "fullName",
						"title" : "Candidate Name"
					}, {
						"data" : 'recruiterName',
						"name" : "recruiterName",
						"title" : "Recruiter Name"
					}, {
						"data" : 'ratePerDay',
						"name" : "ratePerDay",
						"title" : "Rate Per Day"
					},
					{
						"data" : 'noOfDaysWorked',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	//var id="text"+i;
		                    	//alert(i);
		                    	i++;
		                        return '<input type="text" class="editor-active" id="txtName' + i+'" >';
		                    }
		                    return ''; // changed from `return data;` since your object doesnt seem to have a 'select' property
		                },
		               
		               
		                "title" : "No.Of Days Worked"
		            
					}, ]

				});
				i++;
				 /*var table1 = $('#commissiontable').DataTable({
				        columnDefs: [{
				            orderable: false,
				            targets: [1,2,3]
				        }]
				    });*/
				
				$('#commissiontable tfoot tr').appendTo(
						'#commissiontable thead');

				$("#commissiontable tfoot tr").hide();
				
				
				
				/*table.rows().every( function () {
				    var d = this.data();
				
				    d.counter++; // update data source for the row
				 
				    this.invalidate(); // invalidate the data DataTables has cached for this row
				} );
				 
				// Draw once all updates are done
				table.draw();*/
			},
			error : function(e) {

				alert("Unable to load details. " + e);
				console.log(e.responseText);
			}
		});
	}
	//return false;
}

$(document).on("click","#commissiontable tbody tr", function (e)

		{
	var table = $('#commissiontable').DataTable();
		   var rowData = table.row( this ).data();
		   alert(rowData.id+" "+rowData.noOfDaysWorked);
		   console.log(rowData);
		   for(var i=0; i<=10;i++){
			   var txt="#txtName"+i;
		   alert($(txt).val());
		   }
		   /*$.ajax({
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
		    });*/
		});


