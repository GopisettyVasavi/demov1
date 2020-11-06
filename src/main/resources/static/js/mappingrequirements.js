function initialize() {
	//document.getElementById("minRange").focus();
	$("#addlookup_div").hide();
	$("#id").val("");

	$
			.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/loadassignedrequirements",
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					//var json=data
					console.log("success")

					var table = $('#assignedrequirements_tbl').DataTable(
							{

								destroy : true,
								searching : false,
								autoWidth : false,
								paging : false,
								buttons : [ 'colvis' ],
								renderer : {
									"header" : "bootstrap"
								},
								data : data,

								columns : [
									{
									"data" : 'vendorName',
									"name" : "vendorName",
									"title" : "Vendor Name"
								},
								{
									"data" : 'requirementId',
									"name" : "requirementId",
									"title" : "Requirement Id"
								},
								{
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
								} ],
								"createdRow" : function(row, data, dataIndex) {
									// console.log(data.changeColor);
									if (data.status == 'Cancelled'
											|| data.status == 'Closed') {
										//console.log(data[11]);
										$(row).css('background-color',
												'#dc3545');
									}

								}

							});
					$('#assignedrequirements_tbl tfoot tr').appendTo(
							'#assignedrequirements_tbl thead');

					$("#assignedrequirements_tbl tfoot tr").hide();
				},
				error : function(e) {

					var json = "<h4>Response Error:Error occured while loading requirements.</h4><pre>"
							+ e.responseText + "</pre>";
					$('#feedback').html(json);

					console.log("ERROR : ", e);

				}
			});

}

$(document).on("click", "#assignedrequirements_tbl tbody tr", function(e)

{
	$('#feedback').html("");
	var table = $('#assignedrequirements_tbl').DataTable();
	/*table.row(this).css('background-color',
	'#dc3545');*/
	$('#assignedrequirements_tbl tbody > tr').removeClass('selected');
	  $(this).addClass('selected');
	var rowData = table.row(this).data();
	// alert('called'+rowData.contractorId);
	$("#idPkey").val(rowData.id);
	$("#requirementid_s").val(rowData.requirementId);
	//console.log("id: "+$("#idPkey").val())
	resetSearch();
	searchCandidates();
});

function searchCandidates() {

	//alert( $("#currentLocation_s").val());
	$('#search_feedback').html("");
	//console.log("search")
	event.preventDefault();
	/*if ($("#candidateName_s").val() == "" && $("#location_s").val() == ""
			&& $("#skill_s").val() == "") {
		document.getElementById("search_feedback").style.color = "red";

		$('#search_feedback').html(
				"Please enter at least one value to search candidates.");
		//alert("Please enter at least one value to search profiles.");
	} else {*/
		var searchForm = {}
		searchForm["candidateName"] = $("#candidateName_s").val();

		searchForm["skill"] = $("#skill_s").val();

		searchForm["currentLocation"] = $("#location_s").val();
		searchForm["requirementId"] = $("#idPkey").val();

		$
				.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/searchrequirementcandidates",
					data : JSON.stringify(searchForm),
					dataType : 'json',
					cache : false,
					timeout : 600000,
					success : function(data) {

						if(data.length>0){
						$('#searchresults_div').show();
						$('#candidates_div').show();
						$('#mapcandidate_div').show();
						
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
											"order": [[ 1, "asc" ]],
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
														"visible": false
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
													},{
														"data" : 'id',
														"name" : "id",
														"title" : "id",
														"visible" : false
													},
													{
														"data" : 'interestedInRole',
														render : function(data,
																type, row) {
															
															if (type === 'display') {
																var val = "";
																if (data != null)
																	val = data;
																//console.log("data: generateInvoice"+i);
																p++;
																//console.log("interestedInRole: "+row.disableRow);
																//var dis=false;
																if(row.disableRow=='true'){
																	if(val=='true' || val==true){
																		return '<input type="checkbox" checked  disabled value="'
																				+ val
																				+ '"  class="editor-active" id="interestedInRole'
																				+ p
																				+ '"  >';
																		}else{
																			return '<input type="checkbox"  value="'
																			+ val
																			+ '"  class="editor-active" id="interestedInRole'+ p + '" >';
																		}
																}else{
																	
																	if(val=='true' || val==true){
																		return '<input type="checkbox" checked  disabled value="'
																				+ val
																				+ '"  class="editor-active" id="interestedInRole'
																				+ p
																				+ '"  >';
																		}else{
																			return '<input type="checkbox"  value="'
																			+ val
																			+ '"  class="editor-active" id="interestedInRole'+ p + '" >';
																		}
																}
																	
																
															}
															return '';
														},

														"title" : "Interested In Role?"

													},
													{
														"data" : 'authorisation',
														render : function(data,
																type, row) {
															//var p = -1;
															if (type === 'display') {
																var val = "";
																if (data != null)
																	val = data;
																
																var dis=false;
																if(row.disableRow=='true'){
																	
																	if(val=='true' || val==true){
																		return '<input type="checkbox" checked  disabled value="'
																				+ val
																				+ '"  class="editor-active" id="authorisation'
																				+ p
																				+ '" >';
																		}else{
																			return '<input type="checkbox" disabled value="'
																			+ val
																			+ '"  class="editor-active" id="authorisation'
																			+ p
																			+ '" >';
																		}
																}else{
																	if(val=='true' || val==true){
																		return '<input type="checkbox" checked   value="'
																				+ val
																				+ '"  class="editor-active" id="authorisation'
																				+ p
																				+ '" >';
																		}else{
																			return '<input type="checkbox"  value="'
																			+ val
																			+ '"  class="editor-active" id="authorisation'
																			+ p
																			+ '" >';
																		}
																}
																
																
															}
															return '';
														},

														"title" : "Authorisation Received?"

													},
													{
														"data" : 'comments',
														render : function(data,
																type, row) {
															//var p = -1;
															if (type === 'display') {
																var val = "";
																if (data != null)
																	val = data;
																
																if(row.disableRow=='true'){
																	return '<textarea rows="3" cols="50" disabled  class="editor-active" id="comments' + p+'" >'
																	 +val +'</textarea>';
																}else{
																	return '<textarea rows="3" cols="50"   class="editor-active" id="comments' + p+'" >'
																	 +val +'</textarea>';;
																}
																 
															}
															return '';
														},

														"title" : "Notes"

													},
													{
														"data" : 'status',
														"name" : "status",
														"title" : "Status"
													},{
														"data" : 'disableRow',
														"name" : "disableRow",
														"title" : "disableRow"
													}],
													"createdRow" : function(row, data, dataIndex) {
														// console.log(data.changeColor);
														if (data.disableRow == 'true'
																) {
															
															$(row).css('background-color',
																	'#17a2b8');
															
															
														}

													}

										});

						$('#candidates_tbl tfoot tr').appendTo(
								'#candidates_tbl thead');

						$("#candidates_tbl tfoot tr").hide();
						// $("#paginatedTable_foot").style.visibility='block';
						//table.destroy();
						}
						else{
							var json = "<h6>Response :There are no matching candidates for the given criteria.</h6>";
						$('#search_feedback').html(json);
						$('#searchresults_div').hide();
						$('#candidates_div').hide();
						$('#mapcandidate_div').hide();
						
						}

					},
					error : function(e) {

						var json = "<h4>Response Error:Error occured while searching for candidates.</h4><pre>"
								+ e.responseText + "</pre>";
						$('#search_feedback').html(json);

						console.log("ERROR : ", e);
						// $("#btn-search").prop("disabled", false);

					}
				});
	//}

}
function resetSearch() {
	event.preventDefault();
	$("#candidateName_s").val("");
	$("#location_s").val("");
	$("#skill_s").val("");
	$("#searchcandidates_div").show();
	//$("#searchresults_div").hide();
	$('#search_feedback').html("");
	//$('#searchresults_div').show();
	$('#candidates_div').hide();
	$('#mapcandidate_div').hide();
	
	//console.log("reset")

}

$(document).on(
		"click",
		"#candidates_tbl tbody tr",
		function(e)

		{
			var ctable = $('#candidates_tbl').DataTable();
			var rowData = ctable.row(this).data();
			if(rowData.disableRow != 'true'){
			
			$("#candidates_tbl tbody ").on(
					'click',
					'td',
					function() {
						var that=this;
						var idx = ctable.cell( that ).index().column;
						var title = ctable.column( idx ).header();
						var titleText=$(title).html();
						//console.log("name "+ titleText.trim() )
						if (titleText.trim()=='Interested In Role?' || titleText.trim()=='Authorisation Received?' || titleText.trim()=='Notes'
							|| titleText.trim()=='Status') { // provide index of your column in which you prevent row click here is column of 4 index
							//event.preventDefault();
							return;
						} else {
							
							
							$.ajax({
								type : "GET",
								contentType : "application/json",
								url : "/candidatedetails",
								cache : false,
								timeout : 600000,
								success : function(data) {

									var win = window.open("/candidatedetails/"
											+ rowData.candidateId,
											"candidatedetails",
											"status=1,toolbar=0");
									win.focus();

								},
								error : function(e) {

									alert("Unable to load details");

								}
							});
						}

					});
			}else{
				console.log('Row sel')
				 $(this).closest('tr').find(":input").attr('disabled', true);
			}

		});

function mapCandidates(){
	
	event.preventDefault();
	var mappingList=[];
	
	var table = $('#candidates_tbl').DataTable();
	var i=0;
	 var j=0;
	//console.log("Requirement Id: "+$("#idPkey").val());
	table.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
	    var d = this.data();
	    var mappingCandidateRqmtDTO = {}
	    var interestedInRole="interestedInRole"+i;
	    var authorisation="authorisation"+i;
	    var comments="#comments"+i;
	    console.log("document.getElementById(interestedInRole): "+document.getElementById(interestedInRole));
	   if(document.getElementById(interestedInRole)!=null && document.getElementById(interestedInRole).checked)
	   { 
		   
		   mappingCandidateRqmtDTO["requirementId"] = $("#idPkey").val();

			mappingCandidateRqmtDTO["candidateId"] = d.candidateId;
			mappingCandidateRqmtDTO["recruiterId"] = d.recruiterId;
			mappingCandidateRqmtDTO["id"] = d.id;

			//mappingCandidateRqmtDTO["recruiterId"] = "";
			mappingCandidateRqmtDTO["status"] = d.status;
			mappingCandidateRqmtDTO["comments"] =$(comments).val();
			mappingCandidateRqmtDTO["authorisation"] = document.getElementById(authorisation).checked;
			mappingCandidateRqmtDTO["interestedInRole"] = document.getElementById(interestedInRole).checked;
			 mappingList[j]=mappingCandidateRqmtDTO;
			 
			 j++;
			 //console.log("j val: "+j)
	   }
	  // console.log(d);
	  //console.log("i val: "+i)
	  
	   i++;
	});
	
	  $.ajax({
				type : "POST",
				contentType : "application/json",
				url : "/mapcandidaterequirement",
				data : JSON.stringify(mappingList),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {

					console.log("mapping success");

				},
				error : function(e) {

					var json = "<h4>Response Error:Error occured while mapping  candidates.</h4><pre>"
							+ e.responseText + "</pre>";
					$('#search_feedback').html(json);

					console.log("ERROR : ", e);
					// $("#btn-search").prop("disabled", false);

				}
			});

	
}
