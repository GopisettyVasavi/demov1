$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
	document.getElementById("selectFileId").focus();
	document.getElementById("candidateName_s").focus();
    //alert('TAB CHANGED');
});
function checkFileSelected(event) {

	$('#file_feedback').html("");
	$('#divUploadSuccess').html("");
	var selectedFile = document.getElementById("selectFileId").value;
	if (null == selectedFile || selectedFile == '') {
		event.preventDefault();
		document.getElementById("file_feedback").style.color = "red";

  	  $('#file_feedback').html("Please select a profile to upload.");
		//alert("Please select a profile to upload.");
		return false;
	} else{
		$('#divUploadSuccess').html("");
		
		 $("#singlefileupload_btn").val("Processing ...").button("refresh");
		return true;
	}
		
}
function copyFile(){
	//var file=document.getElementById("selectFileId").value.replace(/C:\\fakepath\\/i, '');
	var file=selectFileId.files[0];
	 var formData = new FormData();
	 formData.append("selFile", file);
	 var xhr = new XMLHttpRequest();
	    xhr.open("POST", "/copyFile");
	    xhr.onload = function() {
	        console.log(xhr.responseText);
	       // var response = JSON.parse(xhr.responseText);
	      if(xhr.status == 200) {
	           // alert("Success");
	        } else {
	          // alert("error");
	        }
	    }

	    xhr.send(formData);
}
function createProfile() {
	var url = "/createProfile";
	event.preventDefault();
	$('#personal_feedback').html("");
	var firstName = document.getElementById("firstName").value;
	var middleName = document.getElementById("middleName").value;
	var lastName = document.getElementById("lastName").value;
	var primaryEmail = document.getElementById("primaryEmail").value;
	var primaryPhone = document.getElementById("primaryPhone").value;
	//alert($("#visatype").val())
	if (firstName.trim() == "" || primaryEmail.trim() == ""
			|| primaryPhone.trim() == ""|| lastName.trim() == "") {
		document.getElementById("personal_feedback").style.color = "red";

  	  $('#personal_feedback').html("Name, Primary Email and Primary Contact No fields cannot be blank.");

		//alert("Name, Primary Email and Primary Contact No fields cannot be blank.");
	} else {
		/*var d=$("#workStartDate_dtl").datepicker.parseDate("dd/mm/yy", "22/04/2009");
		
alert(d);*/
		//alert
		var candidateDto = {}
		candidateDto["candidateName"] = $("#firstName").val()+" "+ $("#middleName").val()+" "+ $("#lastName").val();
		candidateDto["firstName"] = $("#firstName").val();
		candidateDto["middleName"] = $("#middleName").val();
		candidateDto["lastName"] =  $("#lastName").val();
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
				$("#firstName").val(firstName);
				$("#middleName").val(middleName);
				$("#lastName").val(lastName);
				$("#primaryEmail").val(data.primaryEmail);
				 $("#primaryPhone").val(data.primaryPhone);
				$("#skills").val(data.skills);
				 $("#education").val(data.education);
				$("#certification").val(data.certification);
				$("#profileText").val(data.profileText);
				$("#secondaryPhone").val(data.secondaryPhone);
				$("#secondaryEmail").val(data.secondaryEmail);
				$("#currentLocation").val(data.currentLocation);
				$("#availability").val(data.availability);
				$("#workExperience").val(data.workExperience);
				$("#reference").val(data.reference);
				$("#title").val(data.title);
				$("#awards").val(data.awards);
				$("#dateOfBirth").val(data.dateOfBirth);
				$("#socialMediaLink").val(data.socialMediaLink);
				$("#nationality").val(data.nationality);
				$("#version").val(data.version);
				$("#filePath").val(data.filePath);
				$("#additionalNotes").val(data.additionalNotes);
				$("#assignedToEmployeeId").val(data.assignedToEmployeeId);
				$("#assignedToEmployeeName").val(data.assignedToEmployeeName);
				$("#employedByRen").val(data.employedByRen);
				$("#assignedDate").val(data.assignedDate);
				$("#organization").val(data.organization);
				$("#designation").val(data.designation);
				$("#workStartDate").val(data.workStartDate);
				$("#workEndDate").val(data.workEndDate);
				$("#lastUpdatedByUser").val(data.lastUpdatedByUser);
				$("#summary").val(data.summary);
				$("#visatype").val(data.visaType);
				$("#visaNo").val(data.visaNo);
				 $("#validUpto").val(data.validUpto);
				
				 if($("#gender").val()=="male"){
						//$('input:radio[name=gender]:nth(0)').attr('checked',true);
						$('input:radio[name=gender]')[0].checked = true;
						}
						else if($("#gender").val()=="female"){
							//alert("inside fem");
							//$('input:radio[name=gender]:nth(1)').attr('checked',true);
							$('input:radio[name=gender]')[1].checked = true;
							
					}
				
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
	document.getElementById("selectFileId").focus();
	if(document.getElementById("embedId")!=null){
		 $("#singlefileupload_btn").val("Upload").button("refresh");
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


function copyFiles(){
	//var file=document.getElementById("selectFileId").value.replace(/C:\\fakepath\\/i, '');
	/*var file=$("#selectFileId").get(0).files.length;
	alert(file);*/
	//var formData = new FormData();
	selDiv = document.querySelector("#selectedFiles");
	selDiv.innerHTML = "";
	multipleFileUploadSuccess.style.display = "none";
	multipleFileUploadSuccess.innerHTML = "";
	multipleFileUploadError.style.display = "none";
	multipleFileUploadError.innerHTML = "";
	 $("#upload_btn").val("UPLOAD").button("refresh");
	$("#selectedFiles").show();
	selDiv.innerHTML ="<b>Selected files:</b><br/>"
	for(var i=0;i<$("#selectFileId").get(0).files.length;i++){
		copySingleFile( $("#selectFileId").get(0).files[i]);
		var f = $("#selectFileId").get(0).files[i];
		
		selDiv.innerHTML += f.name + "<br/>";
	}
	  // formData.append("selFile", $("#upload #file").get(0).files[i]);
}

function copySingleFile(file){
	//alert(file);
	 var formData = new FormData();
	 formData.append("selFile", file);
	 var xhr = new XMLHttpRequest();
	    xhr.open("POST", "/copyFile");
	    xhr.onload = function() {
	        console.log(xhr.responseText);
	       // alert(xhr.responseText);
	       // var response = JSON.parse(xhr.responseText);
	      if(xhr.status == 200) {
	           // alert("Success status");
	        } else {
	          // alert("error");
	        }
	      
	    }

	    xhr.send(formData);
}

function bulkuploadFiles(event){
	event.preventDefault();
	
	var formData = new FormData();
	for(var i=0;i<$("#selectFileId").get(0).files.length;i++){
	  // formData.append("file", $("#selectFileId").get(0).files[i]);
	   uploadSingleFile($("#selectFileId").get(0).files[i]);

	//$.upload("bulkupload", formData, function(data){
		//alert("After method...");
	    //success or failure check
	//});
	}
}

function uploadSingleFile(file){
	//alert(file);
	 var formData = new FormData();
	 formData.append("file", file);
	 var xhr = new XMLHttpRequest();
	    xhr.open("POST", "/bulkupload");
	    xhr.onload = function() {
	        console.log(xhr.responseText);
	       // alert(xhr.responseText);
	       // var response = JSON.parse(xhr.responseText);
	      if(xhr.status == 200) {
	           // alert("Success upload status");
	        } else {
	         alert("Error Occured. Try again/Login again.")
	        }
	      
	    }

	    xhr.send(formData);
}

$.upload = function(url, form, _callback) {	
	//alert("Invoking...");
    $.ajax({
        url: url, 
        type: "POST", 
        processData:false,
        cache: false,
        //async: true,
        data: form,
        contentType: false,
        success:function(data) {
			
			//alert("Profile loaded.."+data);
			

		},
        error:function(data, textStatus, request){
            if(typeof _callback == "function")
                _callback.call(this, data);
        }
    });
};


function uploadmultiplefiles(){
	 multipleFileUploadSuccess.innerHTML="";
	 multipleFileUploadError.innerHTML="";
	 event.preventDefault();
	 $('#feedback').html("");
	 //$("#upload_btn").val('Processing...').button("refresh").trigger("blur");
	
	// $('#upload_btn').prev().text('Uploading...');
	// upload_btn.innerHTML = "Uploading...";
	 var files = selectFileId.files;
	    if(files.length === 0) {
	    	 document.getElementById("feedback").style.color = "red";
	            $('#feedback').html("Response Error: Please select at least one file to upload.");

	        return false;
	    }
	    $("#upload_btn").val("Processing ...").button("refresh");
	   uploadFiles(files);
	    
	
	
	
}


function uploadFiles(files) {
	 $('#feedback').html("");

    var formData = new FormData();
    for(var index = 0; index < files.length; index++) {
        formData.append("files", files[index]);
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/bulkupload");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
        	$("#upload_btn").val("UPLOADED").button("refresh");
        	//alert("Successfully uploaded" +response.messages);
          // multipleFileUploadError.style.display = "none";
        	if(response.successProfiles.length>0){
            var content = "<p><b>Profiles Uploaded successfully:</b></p>";
            for(var i = 0; i < response.successProfiles.length; i++) {
               // content += "<p>" + response.successProfiles[i].fileName+ "</p>";
            	console.log("Response..:"+response.successProfiles[i].candidateId);
                content  +="<p><u><a href='/candidatedetails/" + response.successProfiles[i].candidateId + "' target='_blank'>" 
                + response.successProfiles[i].fileName + "</a></u></p>";
            }
            multipleFileUploadSuccess.innerHTML = content;
            multipleFileUploadSuccess.style.display = "block";
        	}
        	if(response.errorProfiles.length>0){
            var errorcontent = "<p><b>Profiles failed to upload:</b></p>";
            for(var i = 0; i < response.errorProfiles.length; i++) {
            	//errorcontent += "<p>" + response.errorProfiles[i].fileName+ "</p>";
            	
            	var candidateDto = {}
            	candidateDto=JSON.stringify(response.errorProfiles[i]) ;
            //	alert(candidateDto );
            	//var r=JSON.parse(response.errorProfiles[i]);
            	//alert(r);
            	
            	console.log("Response..:"+candidateDto );
            	//console.log("Response type..:"+typeof (candidate));
            	errorcontent+="<p><u><a href='/failedcandidatedetails/" + response.errorProfiles[i].fileName + "' target='_blank'>" 
            	+ response.errorProfiles[i].fileName + "</a></u></p>";
            }
            multipleFileUploadError.innerHTML = errorcontent;
            multipleFileUploadError.style.display = "block";
        	}
        } else {
        	alert("error in uploading...");
            /*multipleFileUploadSuccess.style.display = "none";
            multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";*/
        }
    }
    
   // upload_btn.innerText = "UPLOAD";
    
    xhr.send(formData);
}

function backToIndex(){
	window.location='/index'
}
function restSearchForm(){
	$("#searchProfForm")[0].reset();
	 event.preventDefault();
	
	
	
}
