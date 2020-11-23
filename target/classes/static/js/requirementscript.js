$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
	
	document.getElementById('client').focus();
	document.getElementById("vendorname_s").focus();
    //alert('TAB CHANGED');
});
function initialize(){
	retrieveClients();
	retrieveRecruiters();
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
							"<select id =\"client\" class=\"nice-select form-select\"  onchange=\"othervendorselect()\">Select Vendor</select>");
			$('#clientName_edit')
			.html(
					"<select id =\"client_edit\" class=\"nice-select form-select\"  onchange=\"othervendorselect()\">Select Vendor</select>");
			
			options += "<option value=\"none\"  selected=\"selected\">Select Vendor</option>";

			for (i in list) {
				options += "<option value = " + list[i].clientId
						+ ">" + list[i].clientName + "</option>";
			}
			options += "<option value=\"other\" >Other</option>";
			$('#clientName select').append(options);
			$('#clientName_edit select').append(options);
			document.getElementById("client_edit").classList
					.add('form-select');
			
		},
		error : function(e) {

			alert("Unable to load details");

		}
	});
	
}

function retrieveRecruiters(){
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/recruiterList",
		cache : false,
		timeout : 600000,
		success : function(list) {
			var options = "";
			var soptions = "";
			// alert(list.length);
			$('#recruiter-select')
					.html(
							"<select id =\"recruiter\"  multiple=\"multiple\" >Select Recruiter</select>");
			$('#recruiter-select-edit')
			.html(
					"<select id =\"recruiter_edit\" name =\"recruiter_edit\"  multiple=\"multiple\" >Select Recruiter</select>");
			
			options += "<optgroup label=\"ALL\">"; 
			$('#recruiter-select-s')
			.html(
					"<select id =\"recruiter_s\" class=\"nice-select form-select\">Select Recruiter</select>");
			soptions += "<option value=\"none\"  selected=\"selected\">Select Recruiter</option>";
			soptions += "<option value=\"000\"  >All</option>";
			//options += "<option value=\"none\"  selected=\"selected\">Select Recruiter</option>";
			
			// $('#recruiter-select select').append(options);
			for (i in list) {
				// alert(list[i].employeeId+" "+list[i].employeeName);
				options += "<option value = " + list[i].employeeId
						+ ">" + list[i].employeeName + "</option>";
				soptions += "<option value = " + list[i].employeeId
				+ ">" + list[i].employeeName + "</option>";
			}
			options += "</optgroup>"; 
			$('#recruiter-select select').append(options);
			$('#recruiter-select-edit select').append(options);
			$('#recruiter-select-s select').append(soptions);
			//$('#recruiter-select_s select').append(options);
			document.getElementById("recruiter_s").classList
			.add('form-select');
			document.getElementById("recruiter").classList
					.add('multiselect-ui');
			 $('#recruiter').multiselect({
		            enableClickableOptGroups: true
		        });
			 document.getElementById("recruiter_edit").classList
				.add('multiselect-ui');
		 $('#recruiter_edit').multiselect({
	            enableClickableOptGroups: true
	        });
			//$('#recruiter').multiselect();
		//	$('#recruiter').multiselect();
			// document.getElementById("select_div").className +=
			// "input-group-icon";
		},
		error : function(e) {

			alert("Unable to load details");

		}
	});
}

function othervendorselect(){
	if($("#client").val()!="" && $("#client").val()!="none"
		&& $("#client").val()=="other"){
		$("#othervendorname").val(" ");
		$("#othervendor_div").show();
		
	}else{
		$("#othervendor_div").hide();
	}
	//console.log($("#client").val());
}


function jobtypeselect(){
	if($("#jobtype").val()!="" && $("#jobtype").val()!="none"
		&& $("#jobtype").val()=="Contract"){
		$("#contractstartdate").val(" ");
		$("#contractenddate").val(" ");
		$("#startdt_div").show();
		$("#enddt_div").show();
		$("#contractstartdate_s").val(" ");
		$("#contractenddate_s").val(" ");
		$("#startdt_s_div").show();
		$("#enddt_s_div").show();
		
		$("#sal_lbl").text("Daily Rate ");
		
	}else{
		$("#startdt_div").hide();
		$("#enddt_div").hide();
		$("#startdt_s_div").hide();
		$("#enddt_s_div").hide();
		$("#sal_lbl").text("Salary ");
	}
	if($("#jobtype_s").val()!="" && $("#jobtype_s").val()!="none"
		&& $("#jobtype_s").val()=="Contract"){
		
		$("#contractstartdate_s").val(" ");
		$("#contractenddate_s").val(" ");
		$("#startdt_s_div").show();
		$("#enddt_s_div").show();
		
		
	}else{
		
		$("#startdt_s_div").hide();
		$("#enddt_s_div").hide();
	}
	if($("#jobtype_select_edit").val()!="" && $("#jobtype_select_edit").val()!="none"
		&& $("#jobtype_select_edit").val()=="Contract"){
		$("#contractstartdate_edit").val(" ");
		$("#contractenddate_edit").val(" ");
		$("#startdt_div_edit").show();
		$("#enddt_div_edit").show();
		
		
		$("#sal_lbl_edit").text("Daily Rate ");
		
	}else{
		$("#startdt_div_edit").hide();
		$("#enddt_div_edit").hide();
		
		$("#sal_lbl_edit").text("Salary ");
	}
	
	
}

function createrequirement(){
	//console.log($("#recruiter").val());
	event.preventDefault();
	
	if ($("#client").val() == "none" || $("#requirementid").val() == ""
		|| $("#jobtitle").val() == ""
		|| $("#jobtype").val() == "none" || $("#jobdescription").val() == "") {
	document.getElementById("personal_feedback").style.color = "red";
	$('#personal_feedback').html(
			"Please enter all mandatory fields to create requirement.");
	event.preventDefault();
	return false;
}
	else{
	var RequirementDTO = {}
	
	if($("#client").val() != "none" && $("#client").val() != "other"){
		
	RequirementDTO["vendorName"] = $("#client option:selected").text();
}
	else if($("#client").val() != "none" && $("#client").val() == "other"){
	RequirementDTO["vendorName"] = $("#othervendorname").val();
}
	RequirementDTO["requirementId"] = $("#requirementid").val();
	RequirementDTO["jobTitle"] = $("#jobtitle").val();
	RequirementDTO["jobType"] = $("#jobtype").val() ;
	RequirementDTO["jobDescription"] = $("#jobdescription").val();
	RequirementDTO["contractStartDate"] = $("#contractstartdate").val();
	RequirementDTO["contractEndDate"] = $("#contractenddate").val();
	RequirementDTO["location"] = $("#location").val();
	RequirementDTO["salary"] = $("#salary").val();
	RequirementDTO["datePosted"] = $("#jobposteddate").val();
	RequirementDTO["confidentialInformation"] = $("#confidentialinfo").val();
	//RequirementDTO["status"] = $("#jobposteddate").val();
	RequirementDTO["contactPersonName"] = $("#contactname").val();
	RequirementDTO["contactPersonEmail"] = $("#contactemail").val();
	RequirementDTO["contactPersonPhone"] = $("#contactnumber").val();
	RequirementDTO["recruiters"] = $("#recruiter").val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/createRequirement",
		data : JSON.stringify(RequirementDTO),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			//populateRecruitersCommissions(data);
			document.getElementById("personal_feedback").style.color = "black";
			 var json = "Response :Requirement has been created successfully.";
	         $('#personal_feedback').html(json);
			console.log("Success");
			},
		error : function(e) {
			  var json = "Response Error:Error occured while creating requirement."
	             + e.responseText ;
	         $('#personal_feedback').html(json);

	         console.log("ERROR : ", e);

		}
	});
	
	}
	
	
}


function resetSearch(){
	event.preventDefault();
	$("#rqmtsearchForm")[0].reset();
	$("#editdetails_div").hide();
	$("#editdetails_div").hide();
	$("#searchresults_div").hide();
	event.preventDefault();
}

function requirementSearch(){
	$('#search_feedback').html("");
	$("#editdetails_div").hide();
	event.preventDefault();
	if ($("#vendorname_s").val() == "" && $("#requirementid_s").val() == ""
			&& $("#jobtitle_s").val() == ""
			&& $("#jobtype_s").val() == "none"
			&& $("#contractstartdate_s").val() == "" && $("#contractenddate_s").val() == ""
			&& $("#location_s").val() == ""
			&& $("#jobposteddate_s").val() == ""
			&& $("#jobdescription_s").val() == ""
				&& $("#recruiter_s").val() == "none"
					&& $("#status_s").val() == "none") {
		document.getElementById("search_feedback").style.color = "red";
		$('#search_feedback').html(
				"Please enter at least one value to search requirements.");
	}
	else {
		
		var RequirementDTO = {}
		
		
		RequirementDTO["vendorName"] = $("#vendorname_s").val();
		RequirementDTO["requirementId"] = $("#requirementid_s").val();
		RequirementDTO["jobTitle"] = $("#jobtitle_s").val();
		RequirementDTO["jobType"] = $("#jobtype_s").val() ;
		RequirementDTO["jobDescription"] = $("#jobdescription_s").val();
		RequirementDTO["contractStartDate"] = $("#contractstartdate_s").val();
		RequirementDTO["contractEndDate"] = $("#contractenddate_s").val();
		RequirementDTO["location"] = $("#location_s").val();
		
		RequirementDTO["datePosted"] = $("#jobposteddate_s").val();
		RequirementDTO["status"] = $("#status_s").val();
		//console.log($("#recruiter_s").val());
		if ($("#recruiter_s").val() != "none" || $("#recruiter_s").val() != "000") {
			RequirementDTO["recruiterId"] = $("#recruiter_s").val();
			RequirementDTO["recruiterName"] = $(
					"#recruiter_s option:selected").text();
		}
		if ($("#recruiter_s").val() == "none" ) {
			RequirementDTO["recruiterId"] = 0;
			RequirementDTO["recruiterName"] = $(
					"#recruiter_s option:selected").text();
		}
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/searchRequirement",
			data : JSON.stringify(RequirementDTO),
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {
				$("#searchresults_div").show();
				$("#requirementTable").show();
				console.log("search success");
				//$("#editdetails_div").show();
				var table = $('#requirementTable').DataTable({

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

					columns : [{
						"data" : 'id',
						"name" : "id",
						"title" : "Id",
						"visible" : false
					},{
						"data" : 'vendorName',
						"name" : "vendorName",
						"title" : "Vendor Name"
					}, {
						"data" : 'requirementId',
						"name" : "requirementId",
						"title" : "Requirement Id"
					}, {
						"data" : 'jobTitle',
						"name" : "jobTitle",
						"title" : "Job Title"
					}, {
						"data" : 'jobType',
						"name" : "jobType",
						"title" : "Job Type"
					}, 
					{
						"data" : 'location',
						"name" : "location",
						"title" : "Location"
					}, {
						"data" : 'jobDescription',
						"name" : "jobDescription",
						"title" : "Job Description"
					}, {
						"data" : 'datePosted',
						"name" : "datePosted",
						"title" : "Date Posted"
					}, {
						"data" : 'contractStartDate',
						"name" : "contractStartDate",
						"title" : "Contract Start Date"
					}, {
						"data" : 'contractEndDate',
						"name" : "contractEndDate",
						"title" : "Contract End Date"
					}, {
						"data" : 'status',
						"name" : "status",
						"title" : "Status"
					},
					{
						"data" : 'salary',
						"name" : "salary",
						"title" : "Salary",
						"visible": false
					},
					{
						"data" : 'confidentialInformation',
						"name" : "confidentialInformation",
						"title" : "Confidential Information",
						"visible": false
					},
					{
						"data" : 'contactPersonName',
						"name" : "contactPersonName",
						"title" : "Contact Person Name",
						"visible": false
					},
					{
						"data" : 'contactPersonEmail',
						"name" : "contactPersonEmail",
						"title" : "Contact Person Email",
						"visible": false
					},
					{
						"data" : 'contactPersonPhone',
						"name" : "contactPersonPhone",
						"title" : "Contact Person Phone",
						"visible": false
					},
					{
						"data" : 'assignedRecruiter',
						"name" : "assignedRecruiter",
						"title" : "Assigned recruiter",
						"visible": false
					},
					{
						"data" : 'recruiterId',
						"name" : "recruiterId",
						"title" : "Recruiter Id",
						"visible": false
					},
					{
						"data" : 'recruiterName',
						"name" : "recruiterName",
						"title" : "Recruiter Name",
						"visible": false
					},
					{
						"data" : 'disableStatus',
						"name" : "disableStatus",
						"title" : "disableStatus",
						"visible": false
					},]

				});
				$('#requirementTable tfoot tr').appendTo(
						'#requirementTable thead');

				$("#requirementTable tfoot tr").hide();
			},
			error : function(e) {

				var json = "<h4>Response Error:Error occured while searching for requirements.</h4><pre>"
						+ e.responseText + "</pre>";
				$('#search_feedback').html(json);

				console.log("ERROR : ", e);
				// $("#btn-search").prop("disabled", false);

			}
		});
		
		
	}
	
}

$(document).on(
		"click",
		"#requirementTable tbody tr",
		function(e)

		{
			//document.getElementById("invoiceNodet").focus();
			$('#edit_feedback').html("");
			
			//$('#feedback').html("");
			var table = $('#requirementTable').DataTable();
			var rowData = table.row(this).data();
			// alert('called'+rowData.contractorId);
			$('#requirementTable tbody > tr').removeClass('selected');
			 $(this).addClass('selected');
			$("#editdetails_div").show();
			//$("#invoiceNodet").val(rowData.vendorName);
			$("#requirementid_edit").val(rowData.requirementId);
			$("#jobtitle_edit").val(rowData.jobTitle);
			//$("#jobtype_edit").val(rowData.jobType);
			$("#requirement_status").val(rowData.status);
			//console.log(rowData.status);
			$("#jobdescription_edit").val(rowData.jobDescription);
			$("#contractstartdate_edit").val(rowData.contractStartDate);
			$("#contractenddate_edit").val(rowData.contractEndDate);
			$("#jobposteddate_edit").val(rowData.datePosted);
			$("#location_edit").val(rowData.location);
			$("#salary_edit").val(rowData.salary);
			$("#confidentialinfo_edit").val(rowData.confidentialInformation);
			$("#contactname_edit").val(rowData.contactPersonName);
			$("#contactemail_edit").val(rowData.contactPersonEmail);
			$("#contactnumber_edit").val(rowData.contactPersonPhone);
			$("#idPkey").val(rowData.id);
/** JOB Type */
			populateJobType();
			var jobtype = document.getElementById('jobtype_select_edit');
			for (var i = 0; i < jobtype.options.length; i++) {
			    if (jobtype.options[i].text === rowData.jobType) {
			    	jobtype.selectedIndex = i;
			        break;
			    }
			}
			
			
		/**** CLIENT DROPDOWN  */
			

var dd = document.getElementById('client_edit');
for (var i = 0; i < dd.options.length; i++) {
    if (dd.options[i].text === rowData.vendorName) {
        dd.selectedIndex = i;
        $("#othervendorname_edit").val("");
 		$("#othervendor_div_edit").hide();
        break;
    }else if (dd.options[i].text === 'Other'){
    	 dd.selectedIndex = (dd.options.length)-1;
    	 $("#othervendorname_edit").val(rowData.vendorName);
 		$("#othervendor_div_edit").show();
    	 break;
    }
}

/*** STATUS DROPDOWN */
populateStatus();
var status = document.getElementById('requirement_status');
for (var i = 0; i < status.options.length; i++) {
	//console.log((dd.options.length)-1);
    if (status.options[i].text === rowData.status) {
        status.selectedIndex = i;
       break;
    }
}
//console.log("disableStatus: "+rowData.disableStatus)
if(rowData.disableStatus=='true'){
document.getElementById('requirement_status').disabled=rowData.disableStatus;
}
/*** RECRUITER DROPDOWN */
populateRecruiters(rowData.assignedRecruiter);

});	


function populateStatus(){
	
	
$('#status_edit')
.html(
	"<select id =\"requirement_status\" class=\"nice-select form-select\"  >Select Requirement Status</select>");

var options="";
options += "<option value=\"none\"  selected=\"selected\">Select Requirement Status</option>";


options += "<option value=\"Awaiting Approval\" >Awaiting Approval</option>";
options += "<option value=\"Cancelled\" >Cancelled</option>";
options += "<option value=\"Closed\" >Closed</option>";
options += "<option value=\"Open\" >Open</option>";
options += "<option value=\"Submitted\" >Submitted</option>";

$('#status_edit select').append(options);
document.getElementById("status_edit").classList
	.add('form-select');
}


function populateJobType(){
	
	
	$('#jobtype_edit')
	.html(
		"<select id =\"jobtype_select_edit\" class=\"nice-select form-select\"  onchange=\"jobtypeselect()\">Select Job Type</select>");

	var options="";
	options += "<option value=\"none\"  selected=\"selected\">Select Job Type</option>";
	options += "<option value=\"Contract\" >Contract</option>";
	options += "<option value=\"Permanent\" >Permanent</option>";
	options += "<option value=\"Open\" >Open</option>";
	options += "<option value=\"Other\" >Other</option>";

	$('#jobtype_edit select').append(options);
	document.getElementById("jobtype_edit").classList
		.add('form-select');
	}


function updaterequirement(){
	//console.log($("#recruiter").val());
	event.preventDefault();
	
	if ($("#client_edit").val() == "none" || $("#requirementid_edit").val() == ""
		|| $("#jobtitle_edit").val() == ""
		|| $("#jobtype_edit").val() == "none" || $("#jobdescription_edit").val() == "") {
	document.getElementById("edit_feedback").style.color = "red";
	$('#edit_feedback').html(
			"Please enter all mandatory fields to update requirement.");
	event.preventDefault();
	return false;
}
	else{
	var RequirementDTO = {}
	RequirementDTO["id"]=$("#idPkey").val();
	
	if($("#client_edit").val() != "none" && $("#client_edit").val() != "other"){

	RequirementDTO["vendorName"] = $("#client_edit option:selected").text();
}
	else if($("#client_edit").val() != "none" && $("#client_edit").val() == "other"){
	RequirementDTO["vendorName"] = $("#othervendorname_edit").val();
}
	RequirementDTO["requirementId"] = $("#requirementid_edit").val();
	RequirementDTO["jobTitle"] = $("#jobtitle_edit").val();
	RequirementDTO["jobType"] = $("#jobtype_select_edit").val() ;
	RequirementDTO["jobDescription"] = $("#jobdescription_edit").val();
	RequirementDTO["contractStartDate"] = $("#contractstartdate_edit").val();
	RequirementDTO["contractEndDate"] = $("#contractenddate_edit").val();
	RequirementDTO["location"] = $("#location_edit").val();
	RequirementDTO["salary"] = $("#salary_edit").val();
	RequirementDTO["datePosted"] = $("#jobposteddate_edit").val();
	RequirementDTO["confidentialInformation"] = $("#confidentialinfo_edit").val();
	RequirementDTO["status"] = $("#requirement_status").val();
	
	RequirementDTO["contactPersonName"] = $("#contactname_edit").val();
	RequirementDTO["contactPersonEmail"] = $("#contactemail_edit").val();
	RequirementDTO["contactPersonPhone"] = $("#contactnumber_edit").val();
	console.log("status : "+$("#recruiter_edit").val());
	RequirementDTO["recruiters"] = $("#recruiter_edit").val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",

		url : "/updateRequirement",
		data : JSON.stringify(RequirementDTO),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			//populateRecruitersCommissions(data);
			document.getElementById("edit_feedback").style.color = "black";
			 var json = "Response :Requirement has been updated successfully.";
	         $('#edit_feedback').html(json);
			console.log("Success");
			},
		error : function(e) {
			  var json = "Response Error:Error occured while updating requirement."
	             + e.responseText ;
	         $('#edit_feedback').html(json);

	         console.log("ERROR : ", e);

		}
	});
	
	}
	
	
}


function populateRecruiters(selRec){
	if(selRec!=null){
		selRec=selRec.trim();
	}
	//console.log("selRec: "+selRec)
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/recruiterList",
		cache : false,
		timeout : 600000,
		success : function(list) {
			var options = "";
			
			$('#recruiter-select-edit')
			.html(
					"<select id =\"recruiter_edit\" name =\"recruiter_edit\"  multiple=\"multiple\" >Select Recruiter</select>");
			
			options += "<optgroup label=\"ALL\">"; 
			
			var recruiterIds=[];
			if(selRec!=null){
				if(selRec.indexOf(",")!=-1){
					recruiterIds=	selRec.split(",");
				}else{
					recruiterIds[0]=	selRec;
				}
			}
			
			if(recruiterIds.length>0){
				var added=[];
			for (i in list) {
					for(j in recruiterIds){
						if(recruiterIds[j].trim()==list[i].employeeId)
						{
						options += "<option value = " + list[i].employeeId
						+ "  selected=\"selected\" >" + list[i].employeeName + "</option>";
						added.push( list[i].employeeId);
						
						}
					}
					for (k in recruiterIds){
						if(!added.includes(list[i].employeeId)){
							options += "<option value = " + list[i].employeeId
							+ " >" + list[i].employeeName + "</option>";
							added.push( list[i].employeeId);
						}
					}
				
			}	
				
			}else{
				for (i in list) {
					options += "<option value = " + list[i].employeeId
					+ " >" + list[i].employeeName + "</option>";
				}
			}
				
			options += "</optgroup>"; 
			
			$('#recruiter-select-edit select').append(options);
			
			 document.getElementById("recruiter_edit").classList
				.add('multiselect-ui');
		 $('#recruiter_edit').multiselect({
	            enableClickableOptGroups: true
	        });
			
		},
		error : function(e) {

			alert("Unable to load details");

		}
	});
}