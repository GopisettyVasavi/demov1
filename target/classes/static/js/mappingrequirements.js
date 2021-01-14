function initialize() {
	// document.getElementById("minRange").focus();
	$("#addlookup_div").hide();
	$("#id").val("");

	$
			.ajax({
				type : "GET",
				contentType : "application/json",
				url : "./loadassignedrequirements",
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					// var json=data
					// console.log("success")
					var openrqmt = [];
					var oi = 0;
					var clsrqmt = [];
					var ci = 0;
					var waitrqmt = [];
					var wi = 0;

					for (i in data) {
						// console.log(data[i].status);
						if (data[i].status == 'Cancelled'
								|| data[i].status == 'Closed') {

							clsrqmt[ci] = data[i];
							ci++;
							// break;
						}

						if (data[i].status == 'Submitted'
								|| data[i].status == 'submitted'
								|| data[i].status == 'Awaiting Approval'
								|| data[i].status == 'awaiting approval') {

							waitrqmt[wi] = data[i];
							wi++;
							// break;

						} else if (data[i].status == 'Open'
								|| data[i].status == 'open') {
							openrqmt[oi] = data[i];
							oi++;

						}
						i++;

					}
					if (openrqmt.length > 0) {
						// $("#opentabl_div").show();

						var table = $('#openassignedrequirements_tbl')
								.DataTable({

									destroy : true,
									searching : false,
									autoWidth : false,
									paging : false,
									buttons : [ 'colvis' ],
									renderer : {
										"header" : "bootstrap"
									},
									data : openrqmt,

									columns : [ {
										"data" : 'vendorName',
										"name" : "vendorName",
										"title" : "Vendor Name"
									}, {
										"data" : 'requirementId',
										"name" : "requirementId",
										"title" : "Requirement Id"
									}, {
										"data" : 'jobType',
										"name" : "jobType",
										"title" : "Job Type"
									},

									{
										"data" : 'jobTitle',
										"name" : "jobTitle",
										"title" : "Job Title"
									},

									{
										"data" : 'jobDescription',
										"name" : "jobDescription",
										"title" : "Job Description"
									},

									{
										"data" : 'location',
										"name" : "location",
										"title" : "Location"
									},

									{
										"data" : 'salary',
										"name" : "salary",
										"title" : "Salary"
									},

									{
										"data" : 'status',
										"name" : "status",
										"title" : "Status"
									},

									{
										"data" : 'id',
										"name" : "id",
										"title" : "id",
										"visible" : false
									} ]
								/*
								 * , "createdRow" : function(row, data,
								 * dataIndex) { //
								 * console.log(data.changeColor); if
								 * (data.status == 'Cancelled' || data.status ==
								 * 'Closed') { //console.log(data[11]);
								 * $(row).css('background-color', '#dc3545'); }
								 *  }
								 */

								});
						$('#openassignedrequirements_tbl tfoot tr').appendTo(
								'#openassignedrequirements_tbl thead');

						$("#openassignedrequirements_tbl tfoot tr").hide();

					} else {
						var json = "<h6>There are no Open Requirements assigned to you.</h6><pre>";
						$('#open_msg').html(json);
						$("#open_div").hide();
					}
					if (clsrqmt.length > 0) {
						// $("#closetabl_div").show();

						var table = $('#closeassignedrequirements_tbl')
								.DataTable({

									destroy : true,
									searching : false,
									autoWidth : false,
									paging : false,
									buttons : [ 'colvis' ],
									renderer : {
										"header" : "bootstrap"
									},
									data : clsrqmt,

									columns : [ {
										"data" : 'vendorName',
										"name" : "vendorName",
										"title" : "Vendor Name"
									}, {
										"data" : 'requirementId',
										"name" : "requirementId",
										"title" : "Requirement Id"
									}, {
										"data" : 'jobType',
										"name" : "jobType",
										"title" : "Job Type"
									},

									{
										"data" : 'jobTitle',
										"name" : "jobTitle",
										"title" : "Job Title"
									},

									{
										"data" : 'jobDescription',
										"name" : "jobDescription",
										"title" : "Job Description"
									},

									{
										"data" : 'location',
										"name" : "location",
										"title" : "Location"
									},

									{
										"data" : 'salary',
										"name" : "salary",
										"title" : "Salary"
									},

									{
										"data" : 'status',
										"name" : "status",
										"title" : "Status"
									},

									{
										"data" : 'id',
										"name" : "id",
										"title" : "id",
										"visible" : false
									} ]
								/*
								 * , "createdRow" : function(row, data,
								 * dataIndex) { //
								 * console.log(data.changeColor); if
								 * (data.status == 'Cancelled' || data.status ==
								 * 'Closed') { //console.log(data[11]);
								 * $(row).css('background-color', '#dc3545'); }
								 *  }
								 */

								});
						$('#closeassignedrequirements_tbl tfoot tr').appendTo(
								'#closeassignedrequirements_tbl thead');

						$("#closeassignedrequirements_tbl tfoot tr").hide();

					} else {
						var json = "<h6>There are no Closed Requirements.</h6><pre>";
						$('#cls_msg').html(json);
						$("#cls_div").hide();
					}
					if (waitrqmt.length > 0) {
						// $("#waittabl_div").show();

						var table = $('#waitassignedrequirements_tbl')
								.DataTable({

									destroy : true,
									searching : false,
									autoWidth : false,
									paging : false,
									buttons : [ 'colvis' ],
									renderer : {
										"header" : "bootstrap"
									},
									data : waitrqmt,

									columns : [ {
										"data" : 'vendorName',
										"name" : "vendorName",
										"title" : "Vendor Name"
									}, {
										"data" : 'requirementId',
										"name" : "requirementId",
										"title" : "Requirement Id"
									}, {
										"data" : 'jobType',
										"name" : "jobType",
										"title" : "Job Type"
									},

									{
										"data" : 'jobTitle',
										"name" : "jobTitle",
										"title" : "Job Title"
									},

									{
										"data" : 'jobDescription',
										"name" : "jobDescription",
										"title" : "Job Description"
									},

									{
										"data" : 'location',
										"name" : "location",
										"title" : "Location"
									},

									{
										"data" : 'salary',
										"name" : "salary",
										"title" : "Salary"
									},

									{
										"data" : 'status',
										"name" : "status",
										"title" : "Status"
									},

									{
										"data" : 'id',
										"name" : "id",
										"title" : "id",
										"visible" : false
									} ]
								/*
								 * , "createdRow" : function(row, data,
								 * dataIndex) { //
								 * console.log(data.changeColor); if
								 * (data.status == 'Cancelled' || data.status ==
								 * 'Closed') { //console.log(data[11]);
								 * $(row).css('background-color', '#dc3545'); }
								 *  }
								 */

								});
						$('#waitassignedrequirements_tbl tfoot tr').appendTo(
								'#waitassignedrequirements_tbl thead');

						$("#waitassignedrequirements_tbl tfoot tr").hide();
					} else {
						var json = "<h6>There are no Submitted/Waiting for Approval Requirements.</h6><pre>";
						$('#wait_msg').html(json);
						$("#wait_div").hide();
					}
				},
				error : function(e) {

					var json = "<h6>Response Error:Error occured while loading requirements.</h6><pre>"
							+ e.responseText + "</pre>";
					$('#feedback').html(json);

					//console.log("ERROR : ", e);
					console.log("Error:  while loading requirements: "+ e.responseText);
					
					if (e.responseText.includes('Session Expired')) {
						//alert("Session has expired. Please Login.")
						window.location = './'
					}
				}
			});

}
var jobType = "";
$(document).on("click", "#openassignedrequirements_tbl tbody tr", function(e)

{
	$('#feedback').html("");
	var table = $('#openassignedrequirements_tbl').DataTable();
	$('#waitassignedrequirements_tbl tbody > tr').removeClass('selected');
	$('#closeassignedrequirements_tbl tbody > tr').removeClass('selected');
	$('#openassignedrequirements_tbl tbody > tr').removeClass('selected');
	$(this).addClass('selected');
	var rowData = table.row(this).data();
	$("#idPkey").val(rowData.id);
	$("#requirementid_s").val(rowData.requirementId);
	jobType = rowData.jobType;
	// console.log("jobType "+rowData.jobType)
	resetSearch();
	searchCandidates();
});
$(document).on("click", "#closeassignedrequirements_tbl tbody tr", function(e)

{
	$('#feedback').html("");
	var table = $('#closeassignedrequirements_tbl').DataTable();
	$('#waitassignedrequirements_tbl tbody > tr').removeClass('selected');
	$('#closeassignedrequirements_tbl tbody > tr').removeClass('selected');
	$('#openassignedrequirements_tbl tbody > tr').removeClass('selected');
	$(this).addClass('selected');
	var rowData = table.row(this).data();
	jobType = rowData.jobType;
	$("#idPkey").val(rowData.id);
	$("#requirementid_s").val(rowData.requirementId);
	resetSearch();
	searchCandidates();
});
$(document).on("click", "#waitassignedrequirements_tbl tbody tr", function(e)

{
	$('#feedback').html("");
	var table = $('#waitassignedrequirements_tbl').DataTable();

	$('#waitassignedrequirements_tbl tbody > tr').removeClass('selected');
	$('#closeassignedrequirements_tbl tbody > tr').removeClass('selected');
	$('#openassignedrequirements_tbl tbody > tr').removeClass('selected');
	$(this).addClass('selected');
	var rowData = table.row(this).data();
	jobType = rowData.jobType;
	$("#idPkey").val(rowData.id);
	$("#requirementid_s").val(rowData.requirementId);
	resetSearch();
	searchCandidates();
});
function searchCandidates() {

	// alert( $("#currentLocation_s").val());
	$('#search_feedback').html("");
	$('#map_msg').html("");
	// console.log("search")
	event.preventDefault();
	
	var searchForm = {}
	searchForm["candidateName"] = $("#candidateName_s").val();

	searchForm["skill"] = $("#skill_s").val();

	searchForm["currentLocation"] = $("#location_s").val();
	searchForm["requirementId"] = $("#idPkey").val();

	$
			.ajax({
				type : "POST",
				contentType : "application/json",
				url : "./searchrequirementcandidates",
				data : JSON.stringify(searchForm),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {

					if (data.length > 0) {
						populateCandidatesList(data);
					} else {
						var json = "<h6>Response :There are no matching candidates for the given criteria.</h6>";
						$('#search_feedback').html(json);
						$('#searchresults_div').hide();
						$('#candidates_div').hide();
						$('#mapcandidate_div').hide();

					}

				},
				error : function(e) {

					var json = "<h6>Response Error:Error occured while searching for candidates.</h6><pre>"
							+ e.responseText + "</pre>";
					$('#search_feedback').html(json);

					console.log("ERROR : ", e);
					// $("#btn-search").prop("disabled", false);
					alert("Error:  "+ e.responseText);
					
					if (e.responseText.includes('Session Expired')) {
						//alert("Session has expired. Please Login.")
						window.location = './'
					}

				}
			});
	// }

}
function resetSearch() {
	event.preventDefault();
	$("#candidateName_s").val("");
	$("#location_s").val("");
	$("#skill_s").val("");
	$("#searchcandidates_div").show();
	// $("#searchresults_div").hide();
	$('#search_feedback').html("");
	// $('#searchresults_div').show();
	$('#candidates_div').hide();
	$('#mapcandidate_div').hide();
	document.getElementById('candidateName_s').focus();
	// console.log("reset")

}

$(document)
		.on(
				"click",
				"#candidates_tbl tbody tr",
				function(e)

				{
					
					var ctable = $('#candidates_tbl').DataTable();
					var rowData = ctable.row(this).data();
					console.log("BEF: "+ rowData.candidateId+ " "+rowData.candidateName);
					$('#candidates_tbl tbody > tr').removeClass('selected');
					 $(this).addClass('selected');
					if (rowData.disableRow != 'true' ) {
						$("#candidates_tbl tbody ")
								.on(
										'click',
										'td',
										function() {
											var that = this;
											var idx = ctable.cell(this).index().column;
											var title = ctable.column(idx)
													.header();
											var titleText = $(title).html();
											// console.log("name "+
											// titleText.trim() )
											if (titleText.trim() == 'Interested In Role?'
													|| titleText.trim() == 'Authorisation Received?'
													|| titleText.trim() == 'Comments'
													|| titleText.trim() == 'Status'
													|| titleText.trim() == 'Expected Salary'
													|| titleText.trim() == 'Total Experience'
													|| titleText.trim() == 'Relevant Experience'
													|| titleText.trim() == 'Relocation In Future?'
													|| titleText.trim() == 'Applied For This Role Before?'
													|| titleText.trim() == 'Authorise Renaissance?'
													|| titleText.trim() == 'Availability To Join?'
													|| titleText.trim() == 'Expected Rate(Incl Super)') { 
												return;
											} else {
												console.log("ELSE: "+ rowData.candidateId+ " "+rowData.candidateName);

												$
														.ajax({
															type : "GET",
															contentType : "application/json",
															url : "./candidatedetails",
															cache : false,
															timeout : 600000,
															success : function(
																	data) {

																var win = window
																		.open(
																				"./candidatedetails/"
																						+ rowData.candidateId,
																				"candidatedetails",
																				"status=1,toolbar=0");
																win.focus();

															},
															error : function(e) {

																alert("Error:  "+ e.responseText);
																
																if (e.responseText.includes('Session Expired')) {
																	//alert("Session has expired. Please Login.")
																	window.location = './'
																}

															}
														});
											}

										});
					} else {
						// console.log('Row sel')
						$(this).closest('tr').find(":input").attr('disabled',
								true);
					}

				});

function mapCandidates() {

	event.preventDefault();
	$('#map_msg').html("");
	var mappingList = [];

	var table = $('#candidates_tbl').DataTable();
	var i = 0;
	var j = 0;
	// console.log("Requirement Id: "+$("#idPkey").val());
	table.rows().every(
			function(rowIdx, tableLoop, rowLoop) {
				var d = this.data();
				console.log(d);
				var mappingCandidateRqmtDTO = {}
				var interestedInRole = "interestedInRole" + i;
				var authorisation = "authorisation" + i;
				var availabilityToJoin = "availabilityToJoin" + i;
				var relocationInFuture = "relocationInFuture" + i;
				var appliedBefore = "appliedBefore" + i;
				var authoriseRenaissance = "authoriseRenaissance" + i;
				// console.log(availabilityToJoin)
				var expectedSalary = "#expectedSalary" + i;
				var totalExp = "#totalExp" + i;
				var relevantExp = "#relevantExp" + i;
				var comments = "#comments" + i;
				var status = "#status_" + i;

				if (document.getElementById(interestedInRole) != null
						&& document.getElementById(interestedInRole).checked) {

					mappingCandidateRqmtDTO["requirementId"] = $("#idPkey")
							.val();

					mappingCandidateRqmtDTO["candidateId"] = d.candidateId;
					mappingCandidateRqmtDTO["recruiterId"] = d.recruiterId;
					mappingCandidateRqmtDTO["id"] = d.id;

					// mappingCandidateRqmtDTO["recruiterId"] = "";
					// console.log("Status while saving: "+$(status).val());
					mappingCandidateRqmtDTO["status"] = $(status).val();
					mappingCandidateRqmtDTO["createdDate"] = d.createdDate;
					mappingCandidateRqmtDTO["expectedSalary"] = $(
							expectedSalary).val();
					mappingCandidateRqmtDTO["totalExp"] = $(totalExp).val();
					mappingCandidateRqmtDTO["relevantExp"] = $(relevantExp)
							.val();
					mappingCandidateRqmtDTO["comments"] = $(comments).val();
					mappingCandidateRqmtDTO["authorisation"] = document
							.getElementById(authorisation).checked;
					mappingCandidateRqmtDTO["interestedInRole"] = document
							.getElementById(interestedInRole).checked;
					 mappingCandidateRqmtDTO["availabilityToJoin"] =
					 document.getElementById(availabilityToJoin).checked;
					 mappingCandidateRqmtDTO["relocationInFuture"] =
					 document.getElementById(relocationInFuture).checked;
					 mappingCandidateRqmtDTO["appliedBefore"] =
					 document.getElementById(appliedBefore).checked;
					 mappingCandidateRqmtDTO["authoriseRenaissance"] =
				 document.getElementById(authoriseRenaissance).checked;
					console.log("mappingCandidateRqmtDTO: "
							+ $(relevantExp).val())
						 mappingCandidateRqmtDTO["candidateName"]= d.candidateName;
							 
						 mappingCandidateRqmtDTO["location"]=d.currentLocation;
						mappingCandidateRqmtDTO["contactNumber"]=d.primaryPhone;
						mappingCandidateRqmtDTO["email"]=d.primaryEmail;
						mappingCandidateRqmtDTO["visaStatus"]=d.currentLocation;
							mappingCandidateRqmtDTO["visaNo"]=	d.visaNo;
						
					
					mappingList[j] = mappingCandidateRqmtDTO;

					j++;
					// console.log("j val: "+j)
				}
				// console.log(d);
				// console.log("i val: "+i)

				i++;
			});

	if (mappingList.length > 0) {

		var mappingRequirement = {}
		mappingRequirement["mappingCandidateRqmtList"] = mappingList;
		var searchForm = {}
		searchForm["candidateName"] = $("#candidateName_s").val();

		searchForm["skill"] = $("#skill_s").val();

		searchForm["currentLocation"] = $("#location_s").val();
		searchForm["requirementId"] = $("#idPkey").val();
		// searchForm["requirementId"] = $("#idPkey").val();
		mappingRequirement["searchForm"] = searchForm;
		
		//var requirementDto={}
		//requirementDto
		
		$
				.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./mapcandidaterequirement",
					data : JSON.stringify(mappingRequirement),
					dataType : 'json',
					cache : false,
					timeout : 600000,
					success : function(data) {

						// console.log("mapping success");
						var json = "<h6>Response:Mapping candidate for the requirement has been done successfully.</h6><pre>";
						$('#map_msg').html(json);
						populateCandidatesList(data);
					},
					error : function(e) {

						var json = "<h6>Response Error:Error occured while mapping  candidates.</h6><pre>"
								+ e.responseText + "</pre>";
						$('#map_msg').html(json);

						console.log("ERROR : ", e);
						alert("Error:  "+ e.responseText);
						
						if (e.responseText.includes('Session Expired')) {
							//alert("Session has expired. Please Login.")
							window.location = './'
						}

					}
				});
	} else {
		var json = "<h6>Response:Please select at least one candidate to map for the requirement.</h6><pre>";
		$('#map_msg').html(json);
	}

}
function populateCandidatesList(data) {

	$('#searchresults_div').show();
	$('#candidates_div').show();
	$('#mapcandidate_div').show();
	
	
	
	
	if ( $.fn.DataTable.isDataTable( '#candidates_tbl' ) ) {
	    $("#candidates_tbl").dataTable().fnDestroy();
	    $('#candidates_tbl').empty(); 
	}
	
	
	
	// DataTable
	var p = -1;
	var table = $('#candidates_tbl')
			.DataTable(
					{

						destroy : true,

						autoWidth : false,

						dom : 'Bfrtip',
						buttons : [ 'colvis' ],
						renderer : {
							"header" : "bootstrap"
						},
						"order" : [ [ 1, "asc" ] ],
						data : data,

						columns : [
								{
									"data" : 'candidateName',
									"name" : "Name",
									"title" : "Name"
								},
								{
									"data" : 'sno',
									"name" : "sno",
									"title" : "sno",
									"visible" : false
								},
								{
									"data" : 'primaryEmail',
									"name" : "Email",
									"title" : "Email"
								},
								{
									"data" : 'primaryPhone',
									"name" : "ContactNo",
									"title" : "Contact No"
								},
								{
									"data" : 'skills',
									"name" : "Skill",
									"title" : "Skill"
								},
								{
									"data" : 'currentLocation',
									"name" : "CurrentLocation",
									"title" : "Location"
								},
								{
									"data" : 'visaType',
									"name" : "visaType",
									"title" : "Visa Status"
								},
								{
									"data" : 'visaNo',
									"name" : "visaNo",
									"title" : "Visa Subclass"
								},
								{
									"data" : 'candidateId',
									"name" : "candidateId",
									"title" : "Candidate Id",
									"visible" : false
								},
								{
									"data" : 'recruiterId',
									"name" : "recruiterId",
									"title" : "recruiter Id",
									"visible" : false
								},
								{
									"data" : 'id',
									"name" : "id",
									"title" : "id",
									"visible" : false
								},
								{
									"data" : 'createdDate',
									"name" : "createdDate",
									"title" : "createdDate",
									"visible" : false
								},
								{
									"data" : 'interestedInRole',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											p++;
											
											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked  disabled value="'
															+ val
															+ '"  class="editor-active" id="interestedInRole'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="interestedInRole'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked   value="'
															+ val
															+ '"  class="editor-active" id="interestedInRole'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="interestedInRole'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Interested In Role?"

								},
								{
									"data" : 'authorisation',
									render : function(data, type, row) {
										// var p = -1;
										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;

											var dis = false;
											if (row.disableRow == 'true') {

												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked  disabled value="'
															+ val
															+ '"  class="editor-active" id="authorisation'
															+ p + '" >';
												} else {
													return '<input type="checkbox" disabled value="'
															+ val
															+ '"  class="editor-active" id="authorisation'
															+ p + '" >';
												}
											} else {
												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked   value="'
															+ val
															+ '"  class="editor-active" id="authorisation'
															+ p + '" >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="authorisation'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Authorisation Received?"

								},
								{
									"data" : 'availabilityToJoin',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											// console.log("data: id"+row.id);
											//p++;
											// console.log("interestedInRole:
											// "+row.disableRow);
											// var dis=false;
											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked  disabled value="'
															+ val
															+ '"  class="editor-active" id="availabilityToJoin'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="availabilityToJoin'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked   value="'
															+ val
															+ '"  class="editor-active" id="availabilityToJoin'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="availabilityToJoin'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Availability To Join?"

								},
								{
									"data" : 'expectedSalary',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											//p++;

											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="textbox"   disabled value="'
															+ val
															+ '"  class="editor-active" id="expectedSalary'
															+ p + '"  >';
												} else {
													return '<input type="textbox"  value="'
															+ val
															+ '"  class="editor-active" id="expectedSalary'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="textbox"    value="'
															+ val
															+ '"  class="editor-active" id="expectedSalary'
															+ p + '"  >';
												} else {
													return '<input type="textbox"  value="'
															+ val
															+ '"  class="editor-active" id="expectedSalary'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Expected Salary"

								},
								{
									"data" : 'totalExp',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											//p++;

											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="textbox"   disabled value="'
															+ val
															+ '"  class="editor-active" id="totalExp'
															+ p + '"  >';
												} else {
													return '<input type="textbox"  value="'
															+ val
															+ '"  class="editor-active" id="totalExp'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="textbox"    value="'
															+ val
															+ '"  class="editor-active" id="totalExp'
															+ p + '"  >';
												} else {
													return '<input type="textbox"  value="'
															+ val
															+ '"  class="editor-active" id="totalExp'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Total Experience"

								},
								{
									"data" : 'relevantExp',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
										//	p++;
											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="textbox"   disabled value="'
															+ val
															+ '"  class="editor-active" id="relevantExp'
															+ p + '"  >';
												} else {
													return '<input type="textbox"  value="'
															+ val
															+ '"  class="editor-active" id="relevantExp'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="textbox"    value="'
															+ val
															+ '"  class="editor-active" id="relevantExp'
															+ p + '"  >';
												} else {
													return '<input type="textbox"  value="'
															+ val
															+ '"  class="editor-active" id="relevantExp'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Relevant Experience"

								},
								{
									"data" : 'relocationInFuture',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											//p++;
											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked  disabled value="'
															+ val
															+ '"  class="editor-active" id="relocationInFuture'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="relocationInFuture'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked   value="'
															+ val
															+ '"  class="editor-active" id="relocationInFuture'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="relocationInFuture'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Relocation In Future?"

								},
								{
									"data" : 'appliedBefore',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											//p++;
											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked  disabled value="'
															+ val
															+ '"  class="editor-active" id="appliedBefore'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="appliedBefore'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked   value="'
															+ val
															+ '"  class="editor-active" id="appliedBefore'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="appliedBefore'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Applied For This Role Before?"

								},
								{
									"data" : 'authoriseRenaissance',
									render : function(data, type, row) {

										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;
											//p++;
											if (row.disableRow == 'true') {
												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked  disabled value="'
															+ val
															+ '"  class="editor-active" id="authoriseRenaissance'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="authoriseRenaissance'
															+ p + '" >';
												}
											} else {

												if (val == 'true'
														|| val == true) {
													return '<input type="checkbox" checked   value="'
															+ val
															+ '"  class="editor-active" id="authoriseRenaissance'
															+ p + '"  >';
												} else {
													return '<input type="checkbox"  value="'
															+ val
															+ '"  class="editor-active" id="authoriseRenaissance'
															+ p + '" >';
												}
											}

										}
										return '';
									},

									"title" : "Authorise Renaissance?"

								},
								{
									"data" : 'comments',
									render : function(data, type, row) {
										// var p = -1;
										if (type === 'display') {
											var val = "";
											if (data != null)
												val = data;

											if (row.disableRow == 'true') {
												return '<textarea rows="3" cols="50" disabled  class="editor-active" id="comments'
														+ p
														+ '" >'
														+ val
														+ '</textarea>';
											} else {
												return '<textarea rows="3" cols="50"   class="editor-active" id="comments'
														+ p
														+ '" >'
														+ val
														+ '</textarea>';
												;
											}

										}
										return '';
									},

									"title" : "Comments"

								},
								/*
								 * { "data" : 'disableRow', "name" :
								 * "disableRow", "title" : "disableRow" },
								 */

								{

									"title" : "Status",
									"render" : function(sdata, type, row, meta) {
										// var a = data.indexOf(row);
										// console.log('a: '+a);

										var options = "";
										options += "<option value=\"none\"  >Select Requirement Status</option>";

										options += "<option value=\"Accepted\" >Accepted</option>";
										options += "<option value=\"Open\" >Open</option>";
										options += "<option value=\"Profile Submitted\" >Profile Submitted</option>";
										options += "<option value=\"Interviewed\" >Interviewed</option>";
										options += "<option value=\"Waiting for Approval\" >Waiting for Approval</option>";
										options += "<option value=\"Rejected\" >Rejected</option>";
										if (row.disableRow == 'true'
												|| row.disableStatus == 'true') {
											var select = $("<select id='status_"
													+ p
													+ "' disabled>"
													+ options + "</select>");
										} else {
											var select = $("<select id='status_"
													+ p
													+ "' >"
													+ options
													+ "</select>");
										}

										select.find(
												'option[value="' + row.status
														+ '"]').attr(
												'selected', 'selected');
										return select[0].outerHTML;

									}
								},

						],
						"createdRow" : function(row, data, dataIndex) {
							// console.log(data.changeColor);
							if (data.disableRow == 'true') {

								$(row).css('background-color', '#D3D3D3');

							}

						}

					});

	$('#candidates_tbl tfoot tr').appendTo('#candidates_tbl thead');

	$("#candidates_tbl tfoot tr").hide();
	if (jobType == 'Contract') {
		var table1 = $('#candidates_tbl').DataTable();

		$(table1.column(15).header()).text('Expected Rate(Incl Super)');
	} else {
		var table1 = $('#candidates_tbl').DataTable();

		$(table1.column(15).header()).text('Expected Salary');
	}

}
