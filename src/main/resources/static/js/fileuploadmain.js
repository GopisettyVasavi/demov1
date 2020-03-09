
function checkFileSelected(event) {

	
	var selectedFile = document.getElementById("selectFileId").value;
	if (null == selectedFile || selectedFile == '') {
		event.preventDefault();
		alert("Please select a profile to upload.");
		return false;
	} else
		return true;
}
function createProfile() {
	var url = "/createProfile";
	event.preventDefault();
	
	var candidateName = document.getElementById("candidateName").value;
	var primaryEmail = document.getElementById("primaryEmail").value;
	var primaryPhone = document.getElementById("primaryPhone").value;
	//alert($("#visatype").val())
	if (candidateName.trim() == "" || primaryEmail.trim() == ""
			|| primaryPhone.trim() == "") {

		alert("Name, Primary Email and Primary Contact No fields cannot be blank.");
	} else {
		/*var d=$("#workStartDate_dtl").datepicker.parseDate("dd/mm/yy", "22/04/2009");
		
alert(d);*/
		var candidateDto = {}
		candidateDto["candidateName"] = $("#candidateName").val();
		candidateDto["primaryEmail"] = $("#primaryEmail").val();
		candidateDto["primaryPhone"] = $("#primaryPhone").val();
		candidateDto["skills"] = $("#skills").val();
		candidateDto["education"] = $("#education").val();
		candidateDto["certification"] = $("#certification").val();
		candidateDto["profileText"] = $("#profileText").val();
		candidateDto["candidateId"] = $("#candidateId").val();
		candidateDto["secondaryPhone"] = $("#secondaryPhone").val();
		candidateDto["secondaryEmail"] = $("#secondaryEmail").val();
		candidateDto["currentLocation"] = $("#currentLocation").val();
		candidateDto["availability"] = $("#availability").val();
		candidateDto["workExperience"] = $("#workExperience").val();
		candidateDto["reference"] = $("#reference").val();
		candidateDto["title"] = $("#title").val();
		candidateDto["awards"] = $("#awards").val();
		candidateDto["dateOfBirth"] = $("#dateOfBirth").val();
		candidateDto["socialMediaLink"] = $("#socialMediaLink").val();
		candidateDto["nationality"] = $("#nationality").val();
		candidateDto["version"] = $("#version").val();
		candidateDto["filePath"] = $("#filePath").val();
		candidateDto["additionalNotes"] = $("#additionalNotes").val();
		candidateDto["assignedToEmployeeId"] = $("#assignedToEmployeeId").val();
		candidateDto["assignedToEmployeeName"] = $("#assignedToEmployeeName")
				.val();
		candidateDto["employedByRen"] = $("#employedByRen").val();
		candidateDto["assignedDate"] = $("#assignedDate").val();
		candidateDto["organization"] = $("#organization").val();
		candidateDto["designation"] = $("#designation").val();
		candidateDto["workStartDate"] = $("#workStartDate").val();
		candidateDto["workEndDate"] = $("#workEndDate").val();
		candidateDto["lastUpdatedByUser"] = $("#lastUpdatedByUser").val();
		candidateDto["summary"] = $("#summary").val();
		if( $("#visatype").val()!="none"){
			//alert("setting visatype");
		candidateDto["visaType"] = $("#visatype").val();
		candidateDto["visaNo"] = $("#visaNo").val();
		candidateDto["validUpto"] = $("#validUpto").val();
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
				$('#candidateId').val(data.candidateId);
				alert("Profile created..");
				

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

function cleardata() {
	//alert("Logout invoked");

	$
			.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/logoutuser",
				cache : false,
				timeout : 600000,
				success : function(data) {

				},
				error : function(e) {

					var json = "<h4>Response Error: Issue in looging out user.</h4><pre>"
							+ e.responseText + "</pre>";
					console.log("ERROR : ", e);

				}
			});
}

function setSelectedVisaType() {
	var visatype=document.getElementById("visatype").value;
	var e = document.getElementById("visatype");
	var result = e.options[e.selectedIndex].text;
	//alert(result); //United State
	//alert(visatype);
	if(visatype=="Dependent Visa" || visatype=="Temporary graduate visa" || visatype=="Work Visa"){
		//alert("Inside if");
		$("#visano_lbl").show();
		$("#visaNo").show();
		$("#validupto_lbl").show();
		$("#validUpto").show();
		$("#valid_div").show();
		$("#validupto_spn").show();
		
		
		//document.getElementById("visaextracols").style.visibility='block';
	}else{
		
		$("#visano_lbl").hide();
		$("#visaNo").hide();
		$("#validupto_lbl").hide();
		$("#validUpto").hide();
		$("#valid_div").hide();
		$("#validupto_spn").hide();
	}
	
}

function renderfile(){
	if(document.getElementById("embedId")!=null){
		var val=doesFileExist(document.getElementById("embedId").src);
		//alert(document.getElementById("embedId").src+" : " +val);
	document.getElementById("objId").src=document.getElementById("embedId").src;
	document.getElementById("objId").data=document.getElementById("embedId").src;
	
	var object = document.getElementById("objId");
	  object.setAttribute('data', document.getElementById("embedId").src);
	  object.setAttribute('src', document.getElementById("embedId").src);

	  var clone = object.cloneNode(true);
	  var parent = object.parentNode;

	  parent.removeChild(object );
	  parent.appendChild(clone );
	}
	
}


function doesFileExist(urlToFile)
{
    var xhr = new XMLHttpRequest();
    xhr.open('HEAD', urlToFile, false);
    xhr.send();
	//alert(xhr.status);
    if (xhr.status == "404") {
        console.log("File doesn't exist");
        return false;
    } else {
        console.log("File exists");
        return true;
    }
}

