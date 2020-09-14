$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
	document.getElementById("invoicemonth").focus();
	document.getElementById("recruiterName_s").focus();
    //alert('TAB CHANGED');
});

function backToIndex() {
	window.location = '/index'
}

function initialize(){
	document.getElementById("invoicemonth").focus();
	$("#invoices_feedback").hide();
	$("#invoices_div").hide();
	$("#invoicetable").hide();
	
	$('#search_feedback').html("");
	document.getElementById("searchForm").reset();
	$("#searchresults_div").hide();
	
	
}

function createinvoice(){
	event.preventDefault();
	if($("#invoicemonth").val()!='' && $("#startDate").val()!='' && $("#endDate").val()!='' ){
		
		
		
		var monthyear=$("#invoicemonth").val();
		var startdate=$("#startDate").val();
		var endDate=$("#endDate").val();
		console.log("Dates::"+startdate +" "+endDate);
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/createinvoice/"+monthyear+"/"+ startdate+"/"+endDate,
			cache : false,
			timeout : 600000,
			success : function(data) {
				//alert(data);
				//alert("success");
				if(data.length>0){
					$('#monthyear_feedback').html("");
				$("#invoices_div").show();
				$("#invoicetable").show();
				//$("#buttons_div").show();
				//document.getElementById("tempSave_btn").disabled = false;
				
				var i=-1;
				//console.log("status: "+data[0].status);
				
				var table = $('#invoicetable').DataTable({

					destroy : true,
					searching: false,
					autoWidth : false,
					paging:false,
					/*
					 * targets: 'no-sort', bSort: false, order: [],
					 */

					buttons : [ 'colvis' ],
					renderer : {
						"header" : "bootstrap"
					}, 
					"order": [[ 1, "asc" ]],
					/*
	private Integer invoiceNo;
	private Double totalAmount;
	private Double gst;
	private Double tatoalAmountWithGst;
	private String inclGst;
	private Integer noOfDaysWorked;
	private LocalDate invoiceGeneratedDate;
	private String invoiceCreatedDate;
	private String status;
	private String contractorInvoiceNotes;
	private String description;
					 */
					data : data,

					columns : [
						{
							"data" : 'contractorId',
							"name" : "contractorId",
							"title" : "contractorId",
							"visible":false
						},
						{
							"data" : 'clientId',
							"name" : "clientId",
							"title" : "clientId",
							"visible":false
						},{
						"data" : 'id',
						"name" : "S.No",
						"title" : "S.No"
					}, {
						"data" : 'contractorName',
						"name" : "contractorName",
						"title" : "Candidate Name"
					}, {
						"data" : 'clientName',
						"name" : "clientName",
						"title" : "client Name"
					},
					 {
						"data" : 'vendorId',
						"name" : "vendorId",
						"title" : "Vendor Number"
					},
					{
						"data" : 'paymentTerms',
						"name" : "paymentTerms",
						"title" : "Payment Terms"
					},
					 {
						"data" : 'clientAbnNo',
						"name" : "clientAbnNo",
						"title" : "Client Abn No"
					},
					 {
						"data" : 'clientName',
						"name" : "clientName",
						"title" : "client Name"
					},
					{
						"data" : 'endClientName',
						"name" : "endClientName",
						"title" : "End Client Name"
					},
					{
						"data" : 'address',
						"name" : "address",
						"title" : "Client Address"
					},
					{
						"data" : 'startDate',
						"name" : "startDate",
						"title" : "Start Date"					
					},
					{
						"data" : 'endDate',
						"name" : "endDate",
						"title" : "End Date"					
					},
					{
						"data" : 'monthYear',
						"name" : "monthYear",
						"title" : "Month And Year",
						"visible":false
					},
					{
						"data" : 'ratePerDay',
						"name" : "ratePerDay",
						"title" : "Daily Rate"
					},
					{
						"data" : 'billRatePerDay',
						"name" : "billRatePerDay",
						"title" : "Bill Rate Per Day"
					},
					{
						"data" : 'status',
						"name" : "status",
						"title" : "status",
						"visible":false
					},
					
					{
						"data" : 'noOfDaysWorked',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	var val="";
		                    	if(data !=null)
		                    		val=data;
		                    	i++;
		                        return '<input type="text" onchange="daysWorked('+i+' )" value="'+val+'"  class="editor-active" id="daysWorked' + i+'" >';
		                    }
		                    return ''; 
		                },
		               
		               
		                "title" : "No.Of Days Worked"
		            
					},
					{
						"data" : 'contractorInvoiceNotes',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	var val="";
		                    	if(data !=null)
		                    		val=data;
		                    	console.log("data: "+val);
		                    	i++;
		                        return '<textarea rows="3" cols="15" onchange="invoiceNotes('+i+' )" value="'+val+'"  class="editor-active" id="invoiceNotes' + i+'" >' 
		                        +val +'</textarea>';
		                    }
		                    return ''; 
		                },
		               
		               
		                "title" : "Invoice Notes"
		            
					},]

				});
				//i++;
				
				
				$('#invoicetable tfoot tr').appendTo(
						'#invoicetable thead');

				$("#invoicetable tfoot tr").hide();
				
				}	
				else{
					document.getElementById("monthyear_feedback").style.color = "red";
					$('#monthyear_feedback').html("There are no contractors to generate invoices for the selected month.");
					$("#invoices_div").hide();
					$("#invoicetable").hide();
					//$("#buttons_div").hide();
					
				}
			},
			error : function(e) {

				alert("Unable to load details. " + e);
				//console.log(e.responseText);
			}
		});
	}else{
		document.getElementById("monthyear_feedback").style.color = "red";
		$('#monthyear_feedback').html("Please select a month, year, start and end dates to generate invoices.");
	}
}

function daysWorked(count){
	
}
function invoiceNotes(notes){}