function windowClose() {
		window.open('','_parent','');
		window.close();
		}
function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault();
else if ((e.which || e.keyCode) == 154) e.preventDefault();}
function disableRefresh(){
	$(document).bind("keydown", disableF5);
	/* OR jQuery >= 1.7 */
	$(document).on("keydown", disableF5);
}
function initialize(){
//alert($("#lastupdateddatetime_dtl").val());
	document.getElementById("firstName_dtl").focus();
	disableRefresh();
	if(document.getElementById("filePath_dtl").value!=null){
		//alert(document.getElementById("embedId").src);
		document.getElementById("objid_dtl").src=document.getElementById("filePath_dtl").value;
		document.getElementById("objid_dtl").data=document.getElementById("filePath_dtl").value;
		
		var object = document.getElementById("objid_dtl");
		  object.setAttribute('data', document.getElementById("filePath_dtl").value);
		  object.setAttribute('src', document.getElementById("filePath_dtl").value);

		  var clone = object.cloneNode(true);
		  var parent = object.parentNode;

		  parent.removeChild(object );
		  parent.appendChild(clone );
		}
	visaSelection();
	if($("#gender_dtl").val()=="male"){
	//$('input:radio[name=gender]:nth(0)').attr('checked',true);
	$('input:radio[name=gender]')[0].checked = true;
	}
	else if($("#gender_dtl").val()=="female"){
		//alert("inside fem");
		//$('input:radio[name=gender]:nth(1)').attr('checked',true);
		$('input:radio[name=gender]')[1].checked = true;
		
}
}
function visaSelection() {
	var visatype=document.getElementById("visatype_dtl").value;
	
	if(visatype=="Dependent Visa" || visatype=="Temporary graduate visa" || visatype=="Work Visa"){
		//alert("Inside if");
		$("#visano_lbl_dtl").show();
		$("#visaNo_dtl").show();
		$("#validupto_lbl_dtl").show();
		$("#validUpto_dtl").show();
		$("#validspn_dtl").show();
		
		//document.getElementById("visaextracols").style.visibility='block';
	}else{
		
		$("#visano_lbl_dtl").hide();
		$("#visaNo_dtl").hide();
		$("#validupto_lbl_dtl").hide();
		$("#validUpto_dtl").hide();
		$("#validspn_dtl").hide();
		
	}
	
}

function updateProfile() {
	//alert( $("#validUpto_dtl").val());
	var url = "/createProfile";
	event.preventDefault();
	 $('#personal_feedback').html("");
	//alert($("#lastupdateddatetime_dtl").val());
	var firstName = document.getElementById("firstName_dtl").value;
	var lastName = document.getElementById("lastName_dtl").value;
	var middleName = document.getElementById("middleName_dtl").value;
	var primaryEmail = document.getElementById("primaryEmail_dtl").value;
	var primaryPhone = document.getElementById("primaryPhone_dtl").value;
	//alert($("#visatype").val())
	if (firstName.trim() == "" || primaryEmail.trim() == ""
			|| primaryPhone.trim() == ""|| lastName.trim() == "") {
		document.getElementById("personal_feedback").style.color = "red";

	  	  $('#personal_feedback').html("Name, Primary Email and Primary Contact No fields cannot be blank.");
		//alert("Name, Primary Email and Primary Contact No fields cannot be blank.");
	} else {
		/*var d=$("#validUpto_dtl").datepicker.parseDate("dd/mm/yy", "22/04/2009");
		
alert(d);*/
		//alert("Middle "+$("#middleName_dtl").val());
		var candidateDto = {}
		candidateDto["candidateId"] = $("#candidateId_dtl").val();
		candidateDto["candidateName"] = $("#firstName_dtl").val()+" "+$("#middleName_dtl").val()+" "+$("#lastName_dtl").val();
		candidateDto["firstName"] = $("#firstName_dtl").val();
		candidateDto["middleName"] = $("#middleName_dtl").val();
		candidateDto["lastName"] =  $("#lastName_dtl").val();
		candidateDto["primaryEmail"] = $("#primaryEmail_dtl").val();
		candidateDto["primaryPhone"] = $("#primaryPhone_dtl").val();
		candidateDto["skills"] = $("#skills_dtl").val();
		candidateDto["education"] = $("#education_dtl").val();
		candidateDto["certification"] = $("#certification_dtl").val();
		candidateDto["profileText"] = $("#profileText_dtl").val();
		candidateDto["candidateId"] = $("#candidateId_dtl").val();
		candidateDto["secondaryPhone"] = $("#secondaryPhone_dtl").val();
		candidateDto["secondaryEmail"] = $("#secondaryEmail_dtl").val();
		candidateDto["currentLocation"] = $("#currentLocation_dtl").val();
		candidateDto["availability"] = $("#availability_dtl").val();
		candidateDto["workExperience"] = $("#workExperience_dtl").val();
		candidateDto["reference"] = $("#reference_dtl").val();
		candidateDto["title"] = $("#title_dtl").val();
		candidateDto["awards"] = $("#awards_dtl").val();
		candidateDto["dateOfBirth"] = $("#dateOfBirth_dtl").val();
		candidateDto["socialMediaLink"] = $("#socialMediaLink_dtl").val();
		candidateDto["nationality"] = $("#nationality_dtl").val();
		candidateDto["version"] = $("#version_dtl").val();
		candidateDto["filePath"] = $("#filePath_dtl").val();
		candidateDto["additionalNotes"] = $("#additionalNotes_dtl").val();
		candidateDto["assignedToEmployeeId"] = $("#assignedToEmployeeId_dtl").val();
		candidateDto["assignedToEmployeeName"] = $("#assignedToEmployeeName_dtl")
				.val();
		candidateDto["employedByRen"] = $("#employedByRen_dtl").val();
		candidateDto["assignedDate"] = $("#assignedDate_dtl").val();
		candidateDto["organization"] = $("#organization_dtl").val();
		candidateDto["designation"] = $("#designation_dtl").val();
		candidateDto["workStartDate"] = $("#workStartDate_dtl").val();
		candidateDto["workEndDate"] = $("#workEndDate_dtl").val();
		candidateDto["lastUpdatedByUser"] = $("#lastUpdatedByUser_dtl").val();
		//candidateDto["lastUpdatedByDateTime"] = $("#lastupdateddatetime_dtl").val();
		candidateDto["summary"] = $("#summary_dtl").val();
		if( $("#visatype").val()!="none"){
			//alert("setting visatype");
		candidateDto["visaType"] = $("#visatype_dtl").val();
		candidateDto["visaNo"] = $("#visaNo_dtl").val();
		candidateDto["validUpto"] = $("#validUpto_dtl").val();
		}
		if($('input[name="gender"]:checked').val()!=undefined){
			//alert("setting gender");
		candidateDto["gender"] =$('input[name="gender"]:checked').val();
		}
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/createProfile",
			data : JSON.stringify(candidateDto),
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {
				var response = JSON.stringify(data);
				//$('#candidateId').val(data.candidateId);
				

				$('#candidateId_dtl').val(data.candidateId);
				$("#firstName_dtl").val(firstName);
				$("#middleName_dtl").val(middleName);
				$("#lastName_dtl").val(lastName);
				$("#primaryEmail_dtl").val(data.primaryEmail);
				 $("#primaryPhone_dtl").val(data.primaryPhone);
				$("#skills_dtl").val(data.skills);
				 $("#education_dtl").val(data.education);
				$("#certification_dtl").val(data.certification);
				$("#profileText_dtl").val(data.profileText);
				$("#secondaryPhone_dtl").val(data.secondaryPhone);
				$("#secondaryEmail_dtl").val(data.secondaryEmail);
				$("#currentLocation_dtl").val(data.currentLocation);
				$("#availability_dtl").val(data.availability);
				$("#workExperience_dtl").val(data.workExperience);
				$("#reference_dtl").val(data.reference);
				$("#title_dtl").val(data.title);
				$("#awards_dtl").val(data.awards);
				$("#dateOfBirth_dtl").val(data.dateOfBirth);
				$("#socialMediaLink_dtl").val(data.socialMediaLink);
				$("#nationality_dtl").val(data.nationality);
				$("#version_dtl").val(data.version);
				$("#filePath_dtl").val(data.filePath);
				$("#additionalNotes_dtl").val(data.additionalNotes);
				$("#assignedToEmployeeId_dtl").val(data.assignedToEmployeeId);
				$("#assignedToEmployeeName_dtl").val(data.assignedToEmployeeName);
				$("#employedByRen_dtl").val(data.employedByRen);
				$("#assignedDate_dtl").val(data.assignedDate);
				$("#organization_dtl").val(data.organization);
				$("#designation_dtl").val(data.designation);
				$("#workStartDate_dtl").val(data.workStartDate);
				$("#workEndDate_dtl").val(data.workEndDate);
				$("#lastUpdatedByUser_dtl").val(data.lastUpdatedByUser);
				$("#summary_dtl").val(data.summary);
				$("#visatype_dtl").val(data.visaType);
				$("#visaNo_dtl").val(data.visaNo);
				 $("#validUpto_dtl").val(data.validUpto);
				
				 if($("#gender_dtl").val()=="male"){
						//$('input:radio[name=gender]:nth(0)').attr('checked',true);
						$('input:radio[name=gender]')[0].checked = true;
						}
						else if($("#gender_dtl").val()=="female"){
							//alert("inside fem");
							//$('input:radio[name=gender]:nth(1)').attr('checked',true);
							$('input:radio[name=gender]')[1].checked = true;
							
					}
				alert("Profile Updated..");
				

			},
			error : function(e) {

				alert("Error:: " + e.responseText);
				if (e.responseText.includes('Session Expired')) {
					window.location = '/'
				}

			}
		});

	}
}

