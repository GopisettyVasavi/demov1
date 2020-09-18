$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
	document.getElementById("invoicemonth").focus();
	document.getElementById("invoiceNo_s").focus();
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
	$("#buttons_div").hide();
	
	$('#search_feedback').html("");
	$('#invoices_generated_mesg').html("");
	
	document.getElementById("searchForm").reset();
	$("#searchresults_div").hide();
	
	$("#invoicesearchtbl").hide();
	retrieveClients();
	
}

function createinvoice(){
	event.preventDefault();
	$('#invoices_generated_mesg').html("");
	if($("#invoicemonth").val()!='' ){
		
		
		
		var monthyear=$("#invoicemonth").val();
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "/createinvoice/"+monthyear,
			cache : false,
			timeout : 600000,
			success : function(data) {
				//alert(data);
				//alert("success");
				if(data.length>0){
					populateInvoiceTable(data);
				}	
				else{
					document.getElementById("monthyear_feedback").style.color = "red";
					$('#monthyear_feedback').html("There are no contractors to generate invoices for the selected month.");
					$("#invoices_div").hide();
					$("#invoicetable").hide();
					$("#buttons_div").hide();
					
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

function populateInvoiceTable(data){

	$('#monthyear_feedback').html("");
$("#invoices_div").show();
$("#invoicetable").show();
$("#buttons_div").show();
$('#invoices_generated_mesg').html("");
//$("#buttons_div").show();
//document.getElementById("tempSave_btn").disabled = false;

var i=0;
var j=0;
var k=0;
var l=0;
var n=0;
var p=0;
var q=0;
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
	
	data : data,

	columns : [
		
		{
			"data" : 'contractorId',
			"name" : "contractorId",
			"title" : "contractorId",
			"visible":false
		},
		{
			"data" : 'id',
			"name" : "S.No",
			"title" : "S.No"
		},
		{
			"data" : 'clientId',
			"name" : "clientId",
			"title" : "clientId",
			"visible":false
		}, {
		"data" : 'contractorName',
		"name" : "contractorName",
		"title" : "Candidate Name"
	}, {
		"data" : 'clientName',
		"name" : "clientName",
		"title" : "Client Name"
	},
	{
		"data" : 'endClientName',
		"name" : "endClientName",
		"title" : "End Client Name"
	},
	 {
		"data" : 'vendorId',
		"name" : "vendorId",
		"title" : "Vendor Number",
		"visible":false
	},
	{
		"data" : 'paymentTerms',
		"name" : "paymentTerms",
		"title" : "Payment Terms",
		"visible":false
	},
	 {
		"data" : 'clientAbnNo',
		"name" : "clientAbnNo",
		"title" : "Client Abn No",
		"visible":false
	},
	 
	
	{
		"data" : 'address',
		"name" : "address",
		"title" : "Client Address",
		"visible":false
	},
	{
		"data" : 'poNumber',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	i++;
                return '<input type="text"  value="'+val+'"  class="editor-active" id="poNumber' + i+'" >';
            }
            return ''; 
        },
       
       
        "title" : "PO Number"
    
	},
	
	{
		"data" : 'ratePerDay',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null )
            		data="$"+data+".00";
            	return data;
            	//i++;
               // return '<input type="text"  value="'+val+'"  class="editor-active" id="poNumber' + i+'" >';
            }
            return ''; 
        },
		"title" : "Daily Rate"
	},
	
	{
		"data" : 'startDate',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//i++;
                return '<input type="text"  value="'+val+'"  class="editor-active" id="startDate' + i+'" >';
            }
            return ''; 
        },
       
       
        "title" : "Invoice Period Start Date"
    
	},
	{
		"data" : 'endDate',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	j++;
                return '<input type="text"  value="'+val+'"  class="editor-active" id="endDate' + j+'" >';
            }
            return ''; 
        },
       
       
        "title" : "Invoice Period End Date"
    
	},
	{
		"data" : 'noOfDaysWorked',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	
            	var val="";
            	if(data !=null)
            		val=data;
            	k++;
            	//console.log("k value in days: "+k);
                return '<input type="text" onchange="daysWorked( '+row.billRatePerDay+',  '+k+ ',  '+row.gstPercent+ '   )"  value="'+val+'"  class="editor-active" id="noOfDaysWorked' + k+'" >';
            }
            return ''; 
        },
       
       
       
        "title" : "No.Of Days Worked"
    
	},
	{
		"data" : 'billRatePerDay',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null )
            		data="$"+data+".00";
            	return data;
            	//i++;
               // return '<input type="text"  value="'+val+'"  class="editor-active" id="poNumber' + i+'" >';
            }
            return ''; 
        },
		"title" : "Bill Rate Per Day"
	},
	{
		"data" : 'totalAmount',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: inclGst"+i);
            	//q++;
                return '<input type="text"  value="'+"$"+val +".00"+'"  class="editor-active" id="totalAmount' + k+'" >';
            }
            return ''; 
        },
		"title" : "Amount"
	},
	{
		"data" : 'inclGst',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: inclGst: "+val);
            	//n++;
            	if(val=='true' || val==true)
            	 return '<input type="checkbox" checked onchange="daysWorked( '+row.billRatePerDay+',  '+k+ ',  '+row.gstPercent+ '   )"   class="editor-active" id="inclGst' + k+'" >';
            	else
            		return '<input type="checkbox"  onchange="daysWorked( '+row.billRatePerDay+',  '+k+ ',  '+row.gstPercent+ '   )"   class="editor-active" id="inclGst' + k+'" >';
            }
            return ''; 
        },
       
       
        "title" : "Incl. GST?"
    
	},
	{
		"data" : 'gst',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: inclGst"+i);
            	//q++;
            	return '<input type="text"  value="'+"$"+val +".00"+'"  class="editor-active" id="gst' + k+'" >';
            }
            return ''; 
        },
		"title" : "GST"
	},
	{
		"data" : 'totalAmountWithGst',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: inclGst"+i);
            	//q++;
            	return '<input type="text"  value="'+"$"+val +".00"+'"  class="editor-active" id="totalAmountWithGst' + k+'" >';
            }
            return ''; 
        },
		"title" : "Total Amount Incl. GST"
	},
	
	{
		"data" : 'monthYear',
		"name" : "monthYear",
		"title" : "Month And Year",
		"visible":false
	},{
		"data" : 'gstPercent',
		"name" : "gstPercent",
		"title" : "gstPercent",
		"visible":false
	},
	
	
	{
		"data" : 'invoiceNo',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: inclGst"+i);
            	q++;
            	 return '<input type="textbox"  value="'+val+'"  class="editor-active" id="invoiceNo' + q+'" >';
            }
            return ''; 
        },
       
       
        "title" : "Invoice No."
    
	},
	
	
	{
		"data" : 'contractorInvoiceNotes',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: "+val);
            	l++;
                return '<textarea rows="3" cols="50" onchange="invoiceNotes('+i+' )" value="'+val+'"  class="editor-active" id="contractorInvoiceNotes' + l+'" >' 
                +val +'</textarea>';
            }
            return ''; 
        },
       
       
        "title" : "Invoice Notes"
    
	},
	
	{
		"data" : 'generateInvoice',
		render: function (data, type, row ) {
            if ( type === 'display' ) {
            	var val="";
            	if(data !=null)
            		val=data;
            	//console.log("data: generateInvoice"+i);
            	p++;
            	 return '<input type="checkbox"  value="'+val+'"  class="editor-active" id="generateInvoice' + p+'" >';
            }
            return ''; 
        },
       
       
        "title" : "Generate Invoice?"
    
	},
	{
		"data" : 'status',
		"name" : "status",
		"title" : "Status"
	},
	]

});
//i++;


$('#invoicetable tfoot tr').appendTo(
		'#invoicetable thead');

$("#invoicetable tfoot tr").hide();


}

function daysWorked( billRate,count, gstPercent){
	event.preventDefault();
//console.log("gstPercent : "+gstPercent);
	var inclGst="inclGst"+count;
	
	var noOfDaysWorked="#noOfDaysWorked"+count;
	var totalAmount=Number($(noOfDaysWorked).val())*Number(billRate);
	
	var totalAmountLbl="#totalAmount"+count;
	$(totalAmountLbl).val("$"+totalAmount+".00");
	
	var gstLbl="#gst"+count;
	var totalAmountWithGstLbl="#totalAmountWithGst"+count;
	if(document.getElementById(inclGst).checked){
		var gst=Number(gstPercent)/100 * totalAmount;
		//console.log("GST: "+gst);
		
		$(gstLbl).val("$"+gst+".00");
		$(totalAmountWithGstLbl).val("$"+Number(Number(totalAmount)+Number(gst))+".00");
		
	}else{
		var gst=0;
		//console.log("GST: "+gst);
		
		$(gstLbl).val("$"+gst+".00");
		$(totalAmountWithGstLbl).val("$"+Number(Number(totalAmount)+Number(gst))+".00");
	}
	
	
	
	//console.log("Row id: "+totalAmount+" : "+billRate);

	 
}
function invoiceNotes(notes){
	event.preventDefault();

}

function temporarySave(){
	event.preventDefault();
var invoiceList=populateList();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/saveinvoice",
		data : JSON.stringify(invoiceList),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			//alert("Invoices Generated details data saved.");
			//console.log(data.length);
			if(data.length>0){
				//populateInvoiceTable(data);
				document.getElementById("invoices_generated_mesg").style.color = "black";
			$('#invoices_generated_mesg').html("Invoices saved successfully.");
			}
			//document.getElementById("tempSave_btn").disabled = true;
		},
		error : function(e) {

			alert("Unable to load details. " + e);
			console.log(e);

		}
	});
	
}

function populateList(){
	var invoiceList=[];
	var table = $('#invoicetable').DataTable();
	var i=1;
	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
	    var d = this.data();
	    var generateInvoice="generateInvoice"+i;
	    var startDate="#startDate"+i;
	    var endDate="#endDate"+i;
	    var noOfDaysWorked="#noOfDaysWorked"+i;
	    var inclGst="inclGst"+i;
	    var contractorInvoiceNotes="#contractorInvoiceNotes"+i;
	    var invoiceNo="#invoiceNo"+i;
	    var totalAmount="#totalAmount"+i;
	    var gst="#gst"+i;
	    var totalAmountWithGst="#totalAmountWithGst"+i;
	    var poNumber="#poNumber"+i;
	    var invoiceDTO={}
	    
		   invoiceDTO["id"]= d.id;
		   invoiceDTO["invoiceNo"]=$(invoiceNo).val();
		   invoiceDTO["poNumber"]=$(poNumber).val();
		   invoiceDTO["monthYear"]= d.monthYear;
	       invoiceDTO["contractorId"]=d.contractorId;
		   invoiceDTO["contractorName"]=d.contractorName;
		    invoiceDTO["clientId"]=d.clientId;
		    invoiceDTO["clientName"]=d.clientName;
		   invoiceDTO["endClientName"]=d.endClientName;
		   invoiceDTO["address"]=d.address;
		   invoiceDTO["vendorId"]=d.vendorId;
		   invoiceDTO["paymentTerms"]= d.paymentTerms;
		    invoiceDTO["clientAbnNo"]=d.clientAbnNo;
		    invoiceDTO["startDate"]=$(startDate).val();
		    invoiceDTO["endDate"]=$(endDate).val();
		    invoiceDTO["ratePerDay"]=d.ratePerDay;
		    invoiceDTO["billRatePerDay"]=d.billRatePerDay;
		   /* var total=Number($(noOfDaysWorked).val() * Number(d.billRatePerDay));
		    invoiceDTO["totalAmount"]=Number(total);
		    var gstVal=0;
		   
		    if(document.getElementById(inclGst).checked){
		    	gstVal=Number(d.gstPercent)/100 * Number(total);
		    	
		    }else{
		    	gstVal=0;
		    }
		   var totalValWithGst= Number(Number(gstVal)+Number(total));
		   invoiceDTO["gst"]=Number(gstVal);
		   invoiceDTO["totalAmountWithGst"]=totalValWithGst;*/
		    
		    var gstVal=0;
		    
		    if(document.getElementById(inclGst).checked){
		    	gstVal=Number($(gst).val().replace(/[^0-9.-]+/g,""));
		    	
		    }else{
		    	gstVal=0;
		    }
		    invoiceDTO["gst"]=Number(gstVal);
		    invoiceDTO["totalAmount"]=Number($(totalAmount).val().replace(/[^0-9.-]+/g,""));
		    invoiceDTO["totalAmountWithGst"]=Number($(totalAmountWithGst).val().replace(/[^0-9.-]+/g,""));
		   invoiceDTO["noOfDaysWorked"]=$(noOfDaysWorked).val();
		   invoiceDTO["contractorInvoiceNotes"]=$(contractorInvoiceNotes).val();
		   invoiceDTO["generateInvoice"]=document.getElementById(generateInvoice).checked;
		   invoiceDTO["inclGst"]=document.getElementById(inclGst).checked;
		   invoiceDTO["status"]=d.status;
		 
	    invoiceList[i]=invoiceDTO;
	   i++;
	} );
	
	return invoiceList;
}
function generateInvoice(){
	event.preventDefault();
	$('#invoices_generated_mesg').html("");
	
	var invoiceList=populateList();
	
	while(!filePath){
		 var filePath = prompt("Please enter File path No.", 
	     ""); 
		 //console.log("filePath: "+filePath);
		 filePath= filePath.replace(/\\/g, "SLASH");
		}
	 
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/generateinvoice/"+  filePath,
		data : JSON.stringify(invoiceList),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			//alert("Invoices Generated details data saved.");
			//console.log(data.length);
			if(data.length>0){
				populateInvoiceTable(data);
				document.getElementById("invoices_generated_mesg").style.color = "black";
			$('#invoices_generated_mesg').html("Invoices are generated successfully and stored at location: "+filePath);
			}
			//document.getElementById("tempSave_btn").disabled = true;
		},
		error : function(e) {

			alert("Unable to load details. " + e);
			console.log(e);

		}
	});
	
	 
}


function retrieveClients(){
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/getclientcompanies",
		cache : false,
		timeout : 600000,
		success : function(list) {
			var options = "";
			// alert(list.length);
			$('#clientName')
					.html(
							"<select id =\"client\" class=\"nice-select form-select\">Select Client</select>");
			/*$('#recruiter-select_s')
					.html(
							"<select id =\"recruiter_s\" class=\"nice-select form-select\">Select Recruiter</select>");*/
			options += "<option value=\"none\"  selected=\"selected\">Select Client</option>";

			// $('#recruiter-select select').append(options);
			for (i in list) {
				// alert(list[i].employeeId+" "+list[i].employeeName);
				options += "<option value = " + list[i].clientId
						+ ">" + list[i].clientName + "</option>";
			}
			//options += "<option value=\"99999999\" >Other</option>";
			$('#clientName select').append(options);
			//$('#recruiter-select_s select').append(options);
			document.getElementById("client").classList
					.add('form-select');
			//document.getElementById("recruiter_s").classList.add('form-select');
			// document.getElementById("select_div").className +=
			// "input-group-icon";
		},
		error : function(e) {

			alert("Unable to load details");

		}
	});
	
}

function searchinvoices(){
	event.preventDefault();
	$('#search_feedback').html("");
	$("#searchresults_div").hide();
	$("#invoicesearchtbl").hide();
	event.preventDefault();
	if ( $("#startDate_s").val() == "" && $("#invoiceNo_s").val() == ""
			&& $("#endDate_s").val() == ""
			&& $("#client").val() == "none"
			&&  $("#invoiceStatus").val() == "none") {
		document.getElementById("search_feedback").style.color = "red";
		$('#search_feedback').html(
				"Please enter at least one value to search invoices.");
		// alert("Please enter at least one value to search profiles.");
	} else {
		var invoiceSearchForm = {}
		invoiceSearchForm["invoiceNo"] = $("#invoiceNo_s").val();
		invoiceSearchForm["startDate"] = $("#startDate_s").val();
		invoiceSearchForm["endDate"] = $("#endDate_s").val();
		

		if ($("#client").val() != "none") {
			
			invoiceSearchForm["clientName"] = $(
					"#client option:selected").text();
		}

		if ($("#invoiceStatus").val() != "none") {
			invoiceSearchForm["invoiceStatus"] = $(
					"#invoiceStatus").val();
		}
		

		$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/searchinvoices",
					data : JSON.stringify(invoiceSearchForm),
					dataType : 'json',
					cache : false,
					timeout : 600000,
					success : function(data) {
						$("#searchresults_div").show();
						$("#invoicesearchtbl").show();

						var table = $('#invoicesearchtbl').DataTable({

							destroy : true,

							autoWidth : false,
							/*
							 * targets: 'no-sort', bSort: false, order: [],
							 */

							buttons : [ 'colvis' ],
							renderer : {
								"header" : "bootstrap"
							}, 
							"order": [[ 1, "asc" ]],
							
							data : data,

							columns : [
								
								{
									"data" : 'contractorId',
									"name" : "contractorId",
									"title" : "contractorId",
									"visible":false
								},
								{
									"data" : 'id',
									"name" : "S.No",
									"title" : "S.No"
								},
								{
									"data" : 'clientId',
									"name" : "clientId",
									"title" : "clientId",
									"visible":false
								}, {
								"data" : 'contractorName',
								"name" : "contractorName",
								"title" : "Candidate Name"
							}, {
								"data" : 'clientName',
								"name" : "clientName",
								"title" : "Client Name"
							},
							{
								"data" : 'endClientName',
								"name" : "endClientName",
								"title" : "End Client Name"
							},
							 {
								"data" : 'vendorId',
								"name" : "vendorId",
								"title" : "Vendor Number",
								"visible":false
							},
							{
								"data" : 'paymentTerms',
								"name" : "paymentTerms",
								"title" : "Payment Terms",
								"visible":false
							},
							 {
								"data" : 'clientAbnNo',
								"name" : "clientAbnNo",
								"title" : "Client Abn No",
								"visible":false
							},
							 
							
							{
								"data" : 'address',
								"name" : "address",
								"title" : "Client Address",
								"visible":false
							},
							{
								"data" : 'poNumber',
								"name" : "poNumber",
								"title" : "PO Number"
						    
							},
							
							{
								"data" : 'ratePerDay',
								"name" : "ratePerDay",
								"title" : "Daily Rate"
							},
							
							{
								"data" : 'startDate',
								"name" : "startDate",
								"title" : "Invoice Period Start Date"
						    
							},
							{
								"data" : 'endDate',
								"name" : "endDate",
								"title" : "Invoice Period End Date"
						    
							},
							{
								"data" : 'noOfDaysWorked',
								"name" : "noOfDaysWorked",
								"title" : "No.Of Days Worked"
						    
							},
							{
								"data" : 'billRatePerDay',
								"name" : "billRatePerDay",
								"title" : "Bill Rate Per Day"
							},
							{
								"data" : 'totalAmount',
								"name" : "totalAmount",
								"title" : "Amount"
							},
							{
								"data" : 'inclGst',
								render: function (data, type, row ) {
						            if ( type === 'display' ) {
						            	var val="";
						            	if(data !=null)
						            		val=data;
						            	//console.log("data: inclGst: "+val);
						            	//n++;
						            	if(val=='true' || val==true)
						            	 return '<input type="checkbox" checked    class="editor-active"  >';
						            	else
						            		return '<input type="checkbox"   class="editor-active"  >';
						            }
						            return ''; 
						        },
						       
						       
						        "title" : "Incl. GST?"
						    
							},
							{
								"data" : 'gst',
								"name" : "gst",
								"title" : "GST"
							},
							{
								"data" : 'totalAmountWithGst',
								"name" : "totalAmountWithGst",
								"title" : "Total Amount Incl. GST"
							},
							
							{
								"data" : 'monthYear',
								"name" : "monthYear",
								"title" : "Month And Year",
								"visible":false
							},{
								"data" : 'gstPercent',
								"name" : "gstPercent",
								"title" : "gstPercent",
								"visible":false
							},
							
							
							{
								"data" : 'invoiceNo',
								"name" : "invoiceNo",
								"title" : "Invoice No."
						    
							},
							
							
							{
								"data" : 'contractorInvoiceNotes',
								"name" : "contractorInvoiceNotes",
								"title" : "Invoice Notes"
							},
							
							{
								"data" : 'status',
								"name" : "status",
								"title" : "Status"
							},
							]

						});
						$('#invoicesearchtbl tfoot tr').appendTo(
								'#invoicesearchtbl thead');

						$("#invoicesearchtbl tfoot tr").hide();

					},
					error : function(e) {

						var json = "<h4>Response Error:Error occured while searching for Invoices.</h4><pre>"
								+ e.responseText + "</pre>";
						$('#search_feedback').html(json);

						console.log("ERROR : ", e);
						// $("#btn-search").prop("disabled", false);

					}
				});
	}
}

function resetinvoices(){
	event.preventDefault();
	$("#searchForm")[0].reset();
	$('#search_feedback').html("");
	$("#searchresults_div").hide();
	$("#invoicesearchtbl").hide();
/*
	//$("#period_s").val('none');
	$("#recruiterName_s").val("");
	$('#search_feedback').html("");
	document.getElementById("searchForm").reset();
	$("#searchresults_div").hide();
	$("#commissionsearch_tbl").hide();
	$("#fromdate_div").hide();
	$("#todate_div").hide();
	$("#commissiondetails_div").hide();
	$("#commissiondetail_div").hide();
	$("#commissiondetails_tbl").hide();
	$("#comdet_lbl").hide();
	document.getElementById('comdet_lbl').innerHTML = '';*/
}