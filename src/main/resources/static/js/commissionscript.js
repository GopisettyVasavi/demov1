function initialize(){
	$("#commissions_feedback").hide();
	$("#commissions_div").hide();
	$("#commissiontable").hide();
	$("#buttons_div").hide();
	$("#recruiters_div").hide();
	
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
				//console.log(data);
				$("#commissions_div").show();
				$("#commissiontable").show();
				$("#buttons_div").show();
				var i=-1;
				
				var table = $('#commissiontable').DataTable({

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
					data : data,

					columns : [
						{
							"data" : 'contractorId',
							"name" : "contractorId",
							"title" : "contractorId",
							"visible":false
						},{
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
						"data" : 'jobStartDate',
						"name" : "jobStartDate",
						"title" : "Job Start Day",
						"visible":false
					},
					{
						"data" : 'billRatePerDay',
						"name" : "billRatePerDay",
						"title" : "billRatePerDay",
						"visible":false
					},
					{
						"data" : 'grossMargin',
						"name" : "grossMargin",
						"title" : "grossMargin",
						"visible":false
					},
					{
						"data" : 'noOfDaysWorked',
						render: function (data, type, row ) {
		                    if ( type === 'display' ) {
		                    	var val="";
		                    	/*if(data !=null)
		                    		val=data;*/
		                    	i++;
		                        return '<input type="text" onchange="checkdayscount('+i+' )" value="'+val+'"  class="editor-active" id="txtName' + i+'" >';
		                    }
		                    return ''; 
		                },
		               
		               
		                "title" : "No.Of Days Worked"
		            
					}, ]

				});
				//i++;
				
				
				$('#commissiontable tfoot tr').appendTo(
						'#commissiontable thead');

				$("#commissiontable tfoot tr").hide();
				
				
			},
			error : function(e) {

				alert("Unable to load details. " + e);
				console.log(e.responseText);
			}
		});
	}
	//return false;
}


function checkdayscount(count){
	 var txt="#txtName"+count;
	 if($(txt).val()!='' && $(txt).val()>31){
		 alert("Total No. Of Days worked should be less than or equal to 31." );
		 document.getElementById("txtName"+count).focus();
		return false; 
	 }
	 else return true;
	  
}
/*$(document).on("click","#commissiontable tbody tr", function (e)

		{
	var table = $('#commissiontable').DataTable();
		   var rowData = table.row( this ).data();
		   alert(rowData.id+" "+rowData.noOfDaysWorked);
		   console.log(rowData);
		   for(var i=0; i<=10;i++){
			   var txt="#txtName"+i;
		   alert($(txt).val());
		   }
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
		});*/

function calculateCommission(){
	event.preventDefault();
	var commissionList=[];
	
	var table = $('#commissiontable').DataTable();
	var emptyRows="";
	var i=0;
	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
	    var d = this.data();
	    var txt="#txtName"+i;
	    var commissionDTO={}
	 commissionDTO["id"]= d.id;
	   commissionDTO["contractorId"]=d.contractorId;
	   commissionDTO["fullName"]=d.fullName;
	   commissionDTO["recruiterName"]=d.recruiterName;
	   commissionDTO["ratePerDay"]=d.ratePerDay;
	   commissionDTO["jobStartDate"]=d.jobStartDate;
	   commissionDTO["grossMargin"]=d.grossMargin;
	   commissionDTO["billRatePerDay"]=d.billRatePerDay;
	   commissionDTO["noOfDaysWorked"]=$(txt).val();
	   console.log(commissionDTO);
	   commissionList[i]=commissionDTO;
	   i++;
	   if($(txt).val()=='' || $(txt).val()<=0){
		   //alert("No.of Days is not entered for the row: "+i);
		   emptyRows= emptyRows+" "+i+" ";
		 
	   }
	} );
	
	 if(emptyRows!=""){
		// alert("No.of Days is not entered for the rows: "+emptyRows);
		 if (confirm("No.of Days is not entered for the rows: "+emptyRows)) {
			 invokeMarginCalc(commissionList);
			} else {
			 return false;
			}
		 
		
	 }else{
		 invokeMarginCalc(commissionList);
	 }
	 
}

function invokeMarginCalc(commissionList){
	
	//alert("Margin calc begin"+commissionList.length);
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/calculatecommission",
		data : JSON.stringify(commissionList),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			
			var keys = Object.keys(data);
			var value=[];
			//alert(keys[0]);
			if(keys.length>0){
				$("#recruiters_div").show();
				
				var key="";
				for(var i=0; i<keys.length;i++){
					//console.log("key"+ keys[i]);
					//alert(keys[i]);
					if(keys[i]!="" || keys[i]!="undefined"){
						
						var label = document.createElement("label");
						if(keys[i]=="NA"){
							label.innerHTML="Commissions for : ";
						}else{
						label.innerHTML="Commissions for : "+keys[i];
						}
						document.getElementById("recruiters_div").appendChild(label);
						label.style.fontSize="19px";
						label.style.color="black";
						
					var childtable = document.createElement("table");
					childtable.id = "recruiter_"+keys[i];
					
					document.getElementById("recruiters_div").appendChild(childtable);
					 key = keys[i];
					//alert(key);
					 value = data[key];
					
					var tableid="#"+"recruiter_"+keys[i];
					document.getElementById(childtable.id).className = "display";
					var j=0;
					var k=0;
					var l=0;
					var table = $(tableid).DataTable({

						destroy : true,
						searching: false,
						autoWidth : false,
						paging:false,
						buttons : [ 'colvis' ],
						renderer : {
							"header" : "bootstrap"
						},
						data : value,

						columns : [
							{
								"data" : 'contractorId',
								"name" : "contractorId",
								"title" : "contractorId",
								"visible":false
							},{
							"data" : 'id',
							"name" : "S.No",
							"title" : "S.No"
						},
						{
							"data" : 'fullName',
							"name" : "fullName",
							"title" : "Contractor Name"
						},
						{
							"data" : 'recruiterName',
							"name" : "recruiterName",
							"title" : "Recruiter Name",
							"visible":false
						},
						{
							"data" : 'ratePerDay',
							"name" : "ratePerDay",
							"title" : "Rate Per Day"
						},
						{
							"data" : 'billRatePerDay',
							"name" : "billRatePerDay",
							"title" : "Bill Rate Per Day"
						},
						{
							"data" : 'grossMargin',
							"name" : "grossMargin",
							"title" : "Margin"
						},
						{
							"data" : 'jobStartDate',
							"name" : "jobStartDate",
							"title" : "Date Of Join"
						},
						
						{
							"data" : 'noOfDaysWorked',
							render: function (value, type, row ) {
			                    if ( type === 'display' ) {
			                    	var val="";
			                    	if(value !=null)
			                    		val=value;
			                    	l++;
			                        return '<input type="text"  value="'+val+'"  class="editor-active" id="workedDays' +keys[i]+ l+'" >';
			                    }
			                    return ''; 
			                },
			               
			               
			                "title" : "No.Of Days"
						},
						{
							"data" : 'commission',
							render: function (data, type, row ) {
			                    if ( type === 'display' ) {
			                    	var val="";
			                    	if(data !=null)
			                    		val=data;
			                    	j++;
			                        return '<input type="text"  value="'+val+'"  class="editor-active" id="commission' +keys[i]+ j+'" >';
			                    }
			                    return ''; 
			                },
			               
			               
			                "title" : "Commission"
			            
						},
						{
							"data" : 'commissionForCandidate',
							render: function (data, type, row ) {
			                    if ( type === 'display' ) {
			                    	var val="";
			                    	if(data !=null)
			                    		val=data;
			                    	k++;
			                        return '<input type="text"  value="'+val+'"  class="editor-active" id="candidate_commission' +keys[i]+ k+'" >';
			                    }
			                    return ''; 
			                },
			               
			               
			                "title" : "Commission For the Candidate"
			            
						},
						]

					});
					//i++;
					/*var tfoot=tableid
					
					$('#commissiontable tfoot tr').appendTo(
							'#commissiontable thead');

					$("#commissiontable tfoot tr").hide();*/
					
				}
				
				
				 
			}
			}
			//console.log(keys);
			alert("commissions calculated...");
		},
		error : function(e) {
			// var response = JSON.stringify(e);
			console.log(e.responseText);
			alert("Error " + e.responseText);

		}
	});
}
