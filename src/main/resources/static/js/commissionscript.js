function initialize(){
	$("#commissions_feedback").hide();
	$("#commissions_div").hide();
	$("#commissiontable").hide();
	$("#buttons_div").hide();
	$("#recruiters_div").hide();
	$("#totalcomm_div").hide();

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
	document.getElementById('comdet_lbl').innerHTML = '';
	getSuperPercent();
	
}

function backToIndex() {
	window.location = '/index'
}
function createCommission(){
	event.preventDefault();
	document.getElementById('recruiters_div').innerHTML = '';
	$("#recruiters_div").hide();
	$("#totalcomm_div").hide();
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
				if(data.length>0){
					$('#monthyear_feedback').html("");
				$("#commissions_div").show();
				$("#commissiontable").show();
				$("#buttons_div").show();
				document.getElementById("tempSave_btn").disabled = false;
				
				var i=-1;
				//console.log("status: "+data[0].status);
				if(data[0].status=='TEMPORARY' || data[0].status=='FINAL SAVE'){
					invokeMarginCalc(data);
					//calculateRecruiterTotal();
					//alert("....");
				}
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
					"order": [[ 1, "asc" ]],
					
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
						"title" : "Daily Rate"
					},
					{
						"data" : 'jobStartDate',
						"name" : "jobStartDate",
						"title" : "Job Start Day",
						"visible":false
					},
					{
						"data" : 'monthYear',
						"name" : "monthYear",
						"title" : "Month And Year",
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
					},{
						"data" : 'status',
						"name" : "status",
						"title" : "status",
						"visible":false
					},
					{
						"data" : 'employmentType',
						"name" : "employmentType",
						"title" : "employmentType",
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
				
				}	
				else{
					document.getElementById("monthyear_feedback").style.color = "red";
					$('#monthyear_feedback').html("There are no contractors to run commissions for the selected month.");
					$("#commissions_div").hide();
					$("#commissiontable").hide();
					$("#buttons_div").hide();
					
				}
			},
			error : function(e) {

				alert("Unable to load details. " + e);
				//console.log(e.responseText);
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
	   commissionDTO["monthYear"]=d.monthYear;
	   commissionDTO["noOfDaysWorked"]=$(txt).val();
	   //console.log(commissionDTO);
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
var recruiters=[];
var recruiterCommissionsDTO=[];

function invokeMarginCalc(commissionList){
	
	document.getElementById('recruiters_div').innerHTML = '';
	$("#totalcomm_div").show();
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
			populateRecruitersCommissions(data);
			
			},
		error : function(e) {
			// var response = JSON.stringify(e);
			//console.log(e.responseText);
			alert("Error " + e.responseText);

		}
	});
}

function populateRecruitersCommissions(data){
	var keys = Object.keys(data);
	var value=[];
	//alert(keys[0]);
	if(keys.length>0){
		$("#recruiters_div").show();
		
		var key="";
		for(var i=0; i<keys.length;i++){
			//console.log("key"+ keys[i]);
			//alert(keys[i]);
			
			recruiters[i]=keys[i];
			if(keys[i]!="" || keys[i]!="undefined"){
				
				var label = document.createElement("label");
				label.id = "comm_label"+keys[i];
				document.getElementById("recruiters_div").appendChild(label);
				
				var commlabel = '#comm_label'+keys[i];
				
					$(commlabel).html('Commissions for : '+keys[i]);
					
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
				"order": [[ 1, "asc" ]],
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
					"title" : "Daily Rate"
				},
				{
					"data" : 'billRatePerDay',
					"name" : "billRatePerDay",
					"title" : "Bill Rate"
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
					"data" : 'monthYear',
					"name" : "monthYear",
					"title" : "Month And Year",
					"visible":false
				},
				
				{
					"data" : 'noOfDaysWorked',
					render: function (data, type, row ) {
	                    if ( type === 'display' ) {
	                    	var val="";
	                    	if(data !=null)
	                    		val=data;
	                    	l++;
	                    	let tid=""+keys[i]+l;
	                    	//alert(value[l]);
	                    	//console.log("Days Id: "+"workedDays" +keys[i]+ l);
	                    	 return '<input type="text" onchange="calcCommissionForCandidate( '+row.grossMargin+',  \''+tid+ '\'   )"   value="'+val+'"  class="editor-active" id="workedDays' +keys[i]+ l+'" >';
	                       // return '<input type="text" onchange='myfunction(\""+ name + "\")'  value="'+val+'"  class="editor-active" id="workedDays' +keys[i]+ l+'" >';
	                    }
	                    return ''; 
	                },
	               
	               
	                "title" : "No.Of Days Worked"
				},
				{
					"data" : 'commission',
					render: function (data, type, row ) {
	                    if ( type === 'display' ) {
	                    	var val="";
	                    	if(data !=null)
	                    		val=data;
	                    	j++;
	                    	let tid=""+keys[i]+j;
	                        return '<input type="text" onchange="calcCommissionForCandidate( '+row.grossMargin+', \''+tid+ '\'  )"  value="'+val+'"  class="editor-active" id="commission' +keys[i]+ j+'" >';
	                    }
	                    return ''; 
	                },
	               
	               
	                "title" : "Commission%"
	            
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
	               
	               
	                "title" : "Commission Amount"
	            
				},
				]

			});
			//i++;
			/*var tfoot=tableid
			
			$('#commissiontable tfoot tr').appendTo(
					'#commissiontable thead');

			$("#commissiontable tfoot tr").hide();*/
			
		}
		
			var totalLabel = document.createElement("label");
			totalLabel.id = "label"+keys[i];
			document.getElementById("recruiters_div").appendChild(totalLabel);
			totalLabel.style.fontSize="15px";
			totalLabel.style.color="green";
			totalLabel.style.paddingLeft = "70%";
			var div=document.createElement("div");
			document.getElementById("recruiters_div").appendChild(div);
		 
	}
	}
	
	//console.log(keys);
	//alert("commissions calculated...");

	calculateRecruiterTotal();
}
function calculateRecruiterTotal(){
	event.preventDefault();
	//alert(recruiters.length);
	
	for(var i=0; i<recruiters.length;i++){
		var recruiterCommissionsObj={}
		var tableid="#"+"recruiter_"+recruiters[i];
		recruiterCommissionsObj["recruiterName"]=recruiters[i];
		
		var table = $(tableid).DataTable();
		var j=1;
		var rcCommission=0.0;
		table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
		    var d = this.data();
		    var daysId="#workedDays"+recruiters[i]+j;
		    var commissionId="#commission"+recruiters[i]+j;
		    var candidateCommissionId="#candidate_commission"+recruiters[i]+j;
		    	var days=0;
		    	//console.log("days: "+$(daysId).val());
		    if($(daysId).val()=='' || $(daysId).val()<=0){
		    	days=0.0; 
		    }else{
		    	days=$(daysId).val();
		    }
		    var commission=0;
		    //console.log("comm: "+$(commissionId).val());
		    if($(commissionId).val()=='' || $(commissionId).val()<=0){
		    	commission=0.0; 
		    }else{
		    	commission=$(commissionId).val();
		    }
		    var margin=0;
		   // console.log("cc_comm: "+d.grossMargin);
		    if(d.grossMargin=='' || d.grossMargin<=0){
		    	margin=0.0; 
		    }else{
		    	margin=d.grossMargin;
		    }
		    var calcComm=(Number(days)*Number(commission)/100*Number(margin)).toFixed(2);
		    $(candidateCommissionId).val(calcComm);
		    rcCommission=(Number(rcCommission)+Number(days)*Number(commission)/100*Number(margin)).toFixed(2);
		   j++;
		   
		   recruiterCommissionsObj["monthYear"]="01/"+d.monthYear;
		} );
		//alert("Total: "+rcCommission);
		
		/**
		 * Get Super %
		 */
		//var superPercent=0.0;
		var commWithoutSuper=0.0;
		if(Number(rcCommission) >0){
		
		//getSuperPercent();
		//console.log("super : "+superPercent+ " : "+rcCommission);
		 commWithoutSuper=(Number(rcCommission) *100 / 100 + Number(superPercent)).toFixed(2);
		}
		var totalLabel = "#label"+recruiters[i];
		//console.log(totalLabel);
		$(totalLabel).html('Total Commission (Including Super):'+rcCommission+' <br/>'+'Commission (Without Super):        '+commWithoutSuper);
		recruiterCommissionsObj["contractCommissionTotalSuper"]=rcCommission;
		recruiterCommissionsObj["contractCommissionTotal"]=commWithoutSuper;
		recruiterCommissionsDTO[i]=recruiterCommissionsObj;
		//totalLabel.innerHtml="Total Commission:"+rcCommission;
	}
}
var superPercent=0.0;
function getSuperPercent(){
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/superPercent",
		cache : false,
		timeout : 600000,  
		success : function(data) {
			//alert(data);
			superPercent=Number(data);
			
		},
		error : function(e) {

			alert("Unable to load details. " + e);

		}
	});
	//console.log("inside: "+superPercent);
}

function calcCommissionForCandidate( margin,recruiterName){
	//alert('invoked '+margin);
	//return true;
	//int j=0
	 var daysId="#workedDays"+recruiterName;
	    var commissionId="#commission"+recruiterName;
	    var candidateCommissionId="#candidate_commission"+recruiterName;
	    var calcComm=Number(Number(margin)*Number($(commissionId).val())/100 * Number($(daysId).val() )  ).toFixed(2);
	    $(candidateCommissionId).val(calcComm);
	//console.log("values: "+obj);
	  
}

function temporarySave(){
	event.preventDefault();
	//alert("invoked");
	const swalWithBootstrapButtons = Swal.mixin({
		  customClass: {
		    confirmButton: 'button button-contactForm boxed-btn',
		    cancelButton: 'button button-contactForm boxed-btn'
		  },
		  buttonsStyling: true
		})
	Swal.fire({
		  title: 'Are you sure?',
		  text: "You want to Save&Continue or Continue?",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3d9ef7',
		  cancelButtonColor: '#3d9ef7',
		  confirmButtonText: 'SAVE & CONTINUE',
		  cancelButtonText: 'CONTINUE'
		}).then((result) => {
		  if (result.value) {
			  saveAndContinue();
			  } else if (
				    /* Read more about handling dismissals below */
				    result.dismiss === Swal.DismissReason.cancel
				  ) {
				  continuefn();
				  }
		})
	
}

function saveAndContinue(){
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
	  // console.log("Rate:: "+d.ratePerDay)
	   commissionDTO["ratePerDay"]=Number(d.ratePerDay);
	   commissionDTO["jobStartDate"]=d.jobStartDate;
	   commissionDTO["grossMargin"]=d.grossMargin;
	   commissionDTO["billRatePerDay"]=d.billRatePerDay;
	   commissionDTO["monthYear"]=d.monthYear;	   
	   commissionDTO["noOfDaysWorked"]=$(txt).val();
	   commissionList[i]=commissionDTO;
	   i++;
	   
	} );
	
	
	//document.getElementById('recruiters_div').innerHTML = '';
	//$("#totalcomm_div").show();
	//alert("Margin calc begin"+commissionList.length);
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/savecommission",
		data : JSON.stringify(commissionList),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			alert("data saved");
			document.getElementById("tempSave_btn").disabled = true;
		},
		error : function(e) {

			alert("Unable to load details. " + e);

		}
	});
}
function continuefn(){
	//alert('continue');
	calculateCommission();
}

function finalSave(){
	event.preventDefault();
	var monthyear=$("#commissionMonth").val();
	 if (confirm("Are you sure, You want to finalize commissions for the selected month? ")) {
		 //invokeMarginCalc(commissionList);
		 populateAndSave();
		 return true;
		} else {
		 return false;
		}
}

function populateAndSave(){
	var k=0;
	console.log("recruiters:: "+recruiters);
	var commissionList=[];
	for(var i=0; i<recruiters.length;i++){
	var tableid="#"+"recruiter_"+recruiters[i];
	var table = $(tableid).DataTable();
	var j=1;
	
	//var i=0;
	//var rcCommission=0.0;
	
	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
	    var d = this.data();
	    var daysId="#workedDays"+recruiters[i]+j;
    	//console.log("Days Id save: "+"workedDays" +recruiters[i]+j);

	    var commissionId="#commission"+recruiters[i]+j;
	    var candidateCommissionId="#candidate_commission"+recruiters[i]+j;
	    	var days=0;
	    	//console.log("days: "+$(daysId).val());
	    if($(daysId).val()=='' || $(daysId).val()<=0){
	    	days=0.0; 
	    }else{
	    	days=$(daysId).val();
	    }
	    var commission=0;
	    //console.log("comm: "+$(commissionId).val());
	    if($(commissionId).val()=='' || $(commissionId).val()<=0){
	    	commission=0.0; 
	    }else{
	    	commission=$(commissionId).val();
	    }
	    var margin=0;
	   // console.log("cc_comm: "+d.grossMargin);
	    if(d.grossMargin=='' || d.grossMargin<=0){
	    	margin=0.0; 
	    }else{
	    	margin=d.grossMargin;
	    }
	   
	    var commissionDTO={}
		 commissionDTO["id"]= d.id;
		   commissionDTO["contractorId"]=d.contractorId;
		   commissionDTO["fullName"]=d.fullName;
		   commissionDTO["recruiterName"]=d.recruiterName;
		  // console.log("fullName:: "+d.fullName)
		   commissionDTO["ratePerDay"]=d.ratePerDay;
		   commissionDTO["jobStartDate"]=d.jobStartDate;
		   //commissionDTO["grossMargin"]=d.grossMargin;
		   commissionDTO["billRatePerDay"]=d.billRatePerDay;
		   commissionDTO["monthYear"]=d.monthYear;	   
		   commissionDTO["noOfDaysWorked"]=days;
		   commissionDTO["commission"]=commission;
		   commissionDTO["grossMargin"]=margin;
		   commissionDTO["commissionForCandidate"]=$(candidateCommissionId).val();
		   commissionDTO["status"]="FINAL SAVE";
		   
		   commissionList[k]=commissionDTO;
		  
		  // i++;
		   j++;
		   k++;
		 
	} );
	 k++;
	}
	
	save(commissionList);
}

function save(commissionList){
	console.log("list size::"+commissionList.length);
	for(i=0;i<commissionList.length;i++){
		console.log("commissionList:: "+commissionList);
	}
	for(j=0;j<recruiterCommissionsDTO.length;j++){
		console.log("recruiterCommissionsDTO :: "+recruiterCommissionsDTO);
	}
	
	var finalCommissionsDTO={}
	finalCommissionsDTO["commissionsList"]=commissionList;
	finalCommissionsDTO["recruiterCommissionsList"]=recruiterCommissionsDTO;
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/finalsavecommission",
		data : JSON.stringify(finalCommissionsDTO),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			alert("data saved");
			//document.getElementById("tempSave_btn").disabled = true;
		},
		error : function(e) {

			alert("Unable to load details. " + e);
			console.log(e);

		}
	});
}


function searchcommissions(){
	event.preventDefault();
	$("#search_feedback").show();
	
	var isValid=validateSearchFields();
  if(isValid){
	  console.log("validated");

		var searchCommissionForm = {}
		
		searchCommissionForm["fromDate"] = $("#fromDate_s").val();
		searchCommissionForm["toDate"] = $("#toDate_s").val();
		searchCommissionForm["recruiterName"] = $("#recruiterName_s").val();

		

		if ($("#period_s").val() != "none") {
			searchCommissionForm["period"] = $(
					"#period_s").val();
		}
		

		$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/searchcommissions",
					data : JSON.stringify(searchCommissionForm),
					dataType : 'json',
					cache : false,
					timeout : 600000,
					success : function(data) {
						$("#searchresults_div").show();
						$("#commissionsearch_tbl").show();
						var table = $('#commissionsearch_tbl').DataTable({

							destroy : true,
							searching:false,
							autoWidth : false,
							/*
							 * targets: 'no-sort', bSort: false, order: [],
							 */

							buttons : [ 'colvis' ],
							renderer : {
								"header" : "bootstrap"
							},
							data : data,
							"order": [[ 1, "asc" ]],
							columns : [ 
								 {
										"data" : 'monthYearUI',
										"name" : "monthYearUI",
										"title" : "Month-Year"
									},
									{
										"data" : 'monthYear',
										"name" : "monthYear",
										"title" : "Month-Year",
										"visible": false
									},
									{
								"data" : 'orderDate',
								"name" : "orderDate",
								"title" : "orderDate",
								"visible": false
							}, {
								"data" : 'recruiterName',
								"name" : "recruiterName",
								"title" : "Recruiter"
							}, {
								"data" : 'contractCommissionTotal',
								"name" : "contractCommissionTotal",
								"title" : "Total Contract Placement commission (without Super)"
							}, {
								"data" : 'contractCommissionTotalSuper',
								"name" : "contractCommissionTotalSuper",
								"title" : "Total Contract Placement Commission (With Super)"
							} ]

						});
						$('#commissionsearch_tbl tfoot tr').appendTo(
								'#commissionsearch_tbl thead');

						$("#commissionsearch_tbl tfoot tr").hide();

					},
					error : function(e) {

						var json = "<h4>Response Error:Error occured while searching for commissions.</h4><pre>"
								+ e.responseText + "</pre>";
						$('#search_feedback').html(json);

						console.log("ERROR : ", e);
						// $("#btn-search").prop("disabled", false);

					}
						
				});
	
	  
  }
  else{
	  console.log("Not validated");
  }
}
function resetcommissions(){
	event.preventDefault();
	$("#searchForm")[0].reset();

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
	document.getElementById('comdet_lbl').innerHTML = '';
	
}

function validateSearchFields(){
	var isvalid=true;
	$('#search_feedback').html("");
	$('#search_feedback').show();
	var fromDate = $("#fromDate_s").val();
	   var toDate =  $("#toDate_s").val();
	   
	   if((document.getElementById("period_s").value).trim() == "none" &&
			   fromDate=='' &&   toDate=='' && (document.getElementById("recruiterName_s").value).trim() == ''){
		   document.getElementById("search_feedback").style.color = "red";
			$('#search_feedback').html(
					"Please enter at least one field as search criteria.");
			 isvalid= false;
	   }
	   if((document.getElementById("period_s").value).trim() == "DateRange" ){
		   if (fromDate=='' || toDate==''){
		   document.getElementById("search_feedback").style.color = "red";
			$('#search_feedback').html(
					"Please enter both From and To Date ranges.");
			   isvalid= false;
			  } 
	   }
	   if((document.getElementById("period_s").value).trim() == "DateRange" && fromDate!='' && toDate!=''){
		   if((new Date(fromDate).getTime() <= new Date(toDate).getTime())){
			   isvalid= true;
			  } 
		   
		   else if((new Date(fromDate).getTime() > new Date(toDate).getTime())){
			   document.getElementById("search_feedback").style.color = "red";
				$('#search_feedback').html(
						"From Date cannot be greater than To Date.");
				 isvalid= false;
		   }
	   }
	   
	   return isvalid;
}

function showdatefields(){
	if((document.getElementById("period_s").value).trim() != "none" && (document.getElementById("period_s").value).trim() == "DateRange"){
		
		$("#fromdate_div").show();
		$("#todate_div").show();
		
	}
	else{
		$("#fromdate_div").hide();
		$("#todate_div").hide();
	}
}


$(document).on(
		"click",
		"#commissionsearch_tbl tbody tr",
		function(e)

		{

			var table = $('#commissionsearch_tbl').DataTable();
			var rowData = table.row(this).data();
			// alert('called'+rowData.contractorId);
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "/commissiondetails/"+rowData.monthYear+"/"+ rowData.recruiterName,
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
			
					if(data.length>0){
						$("#commissiondetails_div").show();
						$("#commissiondetail_div").hide();
						$("#comdetails_feedback").html("");
						$("#commissiondetails_tbl").show();
						$("#comdet_lbl").show();
						document.getElementById('comdet_lbl').innerHTML = 'Commission Details For Recruiter: '+rowData.recruiterName+' , For the month of '+rowData.monthYearUI;
						var table = $('#commissiondetails_tbl').DataTable({

							destroy : true,
							searching: false,
							autoWidth : false,
							/*
							 * targets: 'no-sort', bSort: false, order: [],
							 */

							buttons : [ 'colvis' ],
							renderer : {
								"header" : "bootstrap"
							},
							data : data,
							"order": [[ 0, "asc" ]],
							columns : [ 
								 {
										"data" : 'fullName',
										"name" : "fullName",
										"title" : "Contractor Name"
									},
									 
									 {
								"data" : 'recruiterName',
								"name" : "recruiterName",
								"title" : "Recruiter"
							}, {
								"data" : 'ratePerDay',
								"name" : "ratePerDay",
								"title" : "Daily Rate"
							}, {
								"data" : 'billRatePerDay',
								"name" : "billRatePerDay",
								"title" : "Bill Rate"
							}, {
								"data" : 'grossMargin',
								"name" : "grossMargin",
								"title" : "Margin"
							}, {
								"data" : 'noOfDaysWorked',
								"name" : "noOfDaysWorked",
								"title" : "No.Of Days Worked"
							}, {
								"data" : 'commission',
								"name" : "commission",
								"title" : "Commission Percentage"
							}, {
								"data" : 'commissionForCandidate',
								"name" : "commissionForCandidate",
								"title" : "Commission Amount"
							} ]

						});
						$('#commissiondetails_tbl tfoot tr').appendTo(
								'#commissiondetails_tbl thead');

						$("#commissiondetails_tbl tfoot tr").hide();
						
					}
					else{
							document.getElementById("comdetails_feedback").style.color = "red";
							$('#comdetails_feedback').html("Unable to load details for the selected row. Please try again.");
							$("#commissiondetails_div").hide();
							$("#commissiondetail_div").show();
							//$("#comdetails_feedback").html("");
							$("#commissiondetails_tbl").hide();
							$("#comdet_lbl").hide();
							document.getElementById('comdet_lbl').innerHTML = '';
						}
					
				},
				error : function(e) {

					alert("Unable to load details. " + e);

				}
			});
		});
