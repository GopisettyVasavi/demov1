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
	$("#buttons_div").hide();
	
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
				$("#buttons_div").show();
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
						"data" : 'address',
						"name" : "address",
						"title" : "Client Address"
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
						"data" : 'billRatePerDay',
						"name" : "billRatePerDay",
						"title" : "Bill Rate Per Day"
					},
					{
						"data" : 'startDate',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	var val="";
		                    	if(data !=null)
		                    		val=data;
		                    	i++;
		                        return '<input type="text"  value="'+val+'"  class="editor-active" id="startDate' + i+'" >';
		                    }
		                    return ''; 
		                },
		               
		               
		                "title" : "Start Date"
		            
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
		               
		               
		                "title" : "End Date"
		            
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
						"data" : 'totalAmount',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	var val="";
		                    	if(data !=null)
		                    		val=data;
		                    	//console.log("data: inclGst"+i);
		                    	//q++;
		                    	
		                    	return '<label  style="font-weight:bold;" class="editor-active" id="totalAmount' + k+'" >'
		                    	+'$'+val +'.00' + '</label>';
		                    }
		                    return ''; 
		                },
						"title" : "Total Amount(Excl GST)"
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
		                    	 return '<label style="font-weight:bold;"  class="editor-active" id="gst' + k+'" >'
		                    	 +'$'+val +'.00' + '</label>';
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
		                    	 return '<label  style="font-weight:bold;" class="editor-active" id="totalAmountWithGst' + k+'" >'
		                    	 +'$'+val +'.00' +'</label>';
		                    }
		                    return ''; 
		                },
						"title" : "Total Amount With Gst"
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
						"data" : 'status',
						"name" : "status",
						"title" : "status",
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
		                        return '<textarea rows="3" cols="15" onchange="invoiceNotes('+i+' )" value="'+val+'"  class="editor-active" id="contractorInvoiceNotes' + l+'" >' 
		                        +val +'</textarea>';
		                    }
		                    return ''; 
		                },
		               
		               
		                "title" : "Invoice Notes"
		            
					},
					{
						"data" : 'inclGst',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	var val="";
		                    	if(data !=null)
		                    		val=data;
		                    	//console.log("data: inclGst"+i);
		                    	//n++;
		                    	 return '<input type="checkbox" checked onchange="daysWorked( '+row.billRatePerDay+',  '+k+ ',  '+row.gstPercent+ '   )" value="'+val+'"  class="editor-active" id="inclGst' + k+'" >';
		                    }
		                    return ''; 
		                },
		               
		               
		                "title" : "Incl. GST?"
		            
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

function daysWorked( billRate,count, gstPercent){
	event.preventDefault();
	//console.log("Row id: "+count+" : "+billRate);
	var inclGst="inclGst"+count;
	
	var noOfDaysWorked="#noOfDaysWorked"+count;
	var totalAmount=Number($(noOfDaysWorked).val())*Number(billRate);
	
	var totalAmountLbl="#totalAmount"+count;
	$(totalAmountLbl).text("$"+totalAmount+".00");
	
	var gstLbl="#gst"+count;
	var totalAmountWithGstLbl="#totalAmountWithGst"+count;
	if(document.getElementById(inclGst).checked){
		var gst=Number(gstPercent)/100 * totalAmount;
		//console.log("GST: "+gst);
		
		$(gstLbl).text("$"+gst+".00");
		$(totalAmountWithGstLbl).text("$"+Number(Number(totalAmount)+Number(gst))+".00");
		
	}else{
		var gst=0;
		//console.log("GST: "+gst);
		
		$(gstLbl).text("$"+gst+".00");
		$(totalAmountWithGstLbl).text("$"+Number(Number(totalAmount)+Number(gst))+".00");
	}
	
	
	
	//console.log("Row id: "+totalAmount+" : "+billRate);

	 
}
function invoiceNotes(notes){
	event.preventDefault();

}

function temporarySave(){
	event.preventDefault();

	
}
function generateInvoice(){
	event.preventDefault();
	/*while(!invoiceNo){
	 var invoiceNo = prompt("Please enter Invoice No.", 
     ""); 
	// console.log("invoiceNo: "+invoiceNo);
	 invoiceNo=invoiceNo--;
	}*/
	while(!filePath){
		 var filePath = prompt("Please enter File path No.", 
	     ""); 
		 //console.log("filePath: "+filePath);
		 filePath= filePath.replace(/\\/g, "SLASH");
		}
	 
	var invoiceList=[];
	//var invoiceNoExist=false;
	var invoiceParam="0000";
	var table = $('#invoicetable').DataTable();
	var i=1;
	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
		//console.log("row index:  "+rowIdx);
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
	   // if(document.getElementById(generateInvoice).checked==true){
	    	/*console.log("generateInvoice: : "+generateInvoice);
	    	console.log("inclGst: : "+inclGst);
	    	console.log("generateInvoice val: : "+generateInvoice+": "+document.getElementById(generateInvoice).checked);
	    	console.log("inclGst val: : "+inclGst+" : "+document.getElementById(inclGst).checked);*/
	    	
	    	var invoiceDTO={}
		    invoiceDTO["id"]= d.id;
		    invoiceDTO["invoiceNo"]=$(invoiceNo).val();
	    	//invoiceDTO["invoiceNo"]=invoiceNo++;
	    	
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
		    var total=Number($(noOfDaysWorked).val() * Number(d.billRatePerDay));
		    invoiceDTO["totalAmount"]=Number(total);
		    //console.log("totalAmount: "+total);
		    var gstVal=0;
		   
		    if(document.getElementById(inclGst).checked){
		    	gstVal=Number(d.gstPercent)/100 * Number(total);
		    	
		    }else{
		    	gstVal=0;
		    }
		   var totalValWithGst= Number(Number(gstVal)+Number(total));
		   invoiceDTO["gst"]=Number(gstVal);
		  // console.log("gst: "+Number($(gst).text()));
		   invoiceDTO["totalAmountWithGst"]=totalValWithGst;
		//   console.log("tatoalAmountWithGst: "+totalValWithGst);
		  // invoiceDTO["inclGst"]=$(inclGst).val();
		  // invoiceDTO["totalAmount"]=totalValWithGst;
		   invoiceDTO["poNumber"]=d.poNumber;
		   
		   invoiceDTO["noOfDaysWorked"]=$(noOfDaysWorked).val();
		   invoiceDTO["contractorInvoiceNotes"]=$(contractorInvoiceNotes).val();
		   invoiceDTO["generateInvoice"]=document.getElementById(generateInvoice).checked;
		   invoiceDTO["inclGst"]=document.getElementById(inclGst).checked;
		  
	    /*}else{
	    	console.log("generateInvoice: false: "+document.getElementById(generateInvoice).checked);
	    }*/
	    
	  
	    invoiceList[i]=invoiceDTO;
	   i++;
	} );
	/*console.log("invoiceNoExist: "+invoiceNoExist);
	if(!invoiceNoExist){
	while(!invoiceNo){
		 var invoiceNo = prompt("Please enter Invoice No.", 
	     ""); 
		 invoiceParam=invoiceNo;
		// console.log("invoiceNo: "+invoiceNo);
		// invoiceNo=invoiceNo--;
		}
	}*/
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/generateinvoice/"+  filePath,
		data : JSON.stringify(invoiceList),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			alert("Invoices Generated details data saved.");
			//document.getElementById("tempSave_btn").disabled = true;
		},
		error : function(e) {

			alert("Unable to load details. " + e);

		}
	});
	
	 
}