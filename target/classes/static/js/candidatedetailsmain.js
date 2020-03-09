function windowClose() {
		window.open('','_parent','');
		window.close();
		}

function initialize(){

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
	var url = "/createProfile";
	event.preventDefault();
	
	var candidateName = document.getElementById("candidateName_dtl").value;
	var primaryEmail = document.getElementById("primaryEmail_dtl").value;
	var primaryPhone = document.getElementById("primaryPhone_dtl").value;
	//alert($("#visatype").val())
	if (candidateName.trim() == "" || primaryEmail.trim() == ""
			|| primaryPhone.trim() == "") {

		alert("Name, Primary Email and Primary Contact No fields cannot be blank.");
	} else {
		/*var d=$("#workStartDate_dtl").datepicker.parseDate("dd/mm/yy", "22/04/2009");
		
alert(d);*/
		var candidateDto = {}
		candidateDto["candidateId"] = $("#candidateId_dtl").val();
		candidateDto["candidateName"] = $("#candidateName_dtl").val();
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

