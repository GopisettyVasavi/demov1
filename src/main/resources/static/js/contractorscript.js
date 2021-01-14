
$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
	document.getElementById("contractorName_s").focus();
	document.getElementById("firstName").focus();
    //alert('TAB CHANGED');
});
function initialize() {
	
	
	document.getElementById("firstName").focus();
	/* if (window.performance && window.performance.navigation.type === window.performance.navigation.TYPE_BACK_FORWARD) {
	   alert('Session Expired. Please login.');
	   window.location = './'
	} */

	$.ajax({
				type : "GET",
				contentType : "application/json",
				 url: "./recruiterList",
				cache : false,
				timeout : 600000,
				success : function(list) {
					var options = "";
					// alert(list.length);
					$('#recruiter-select')
							.html(
									"<select id =\"recruiter\" class=\"nice-select form-select\">Select Recruiter</select>");
					$('#recruiter-select_s')
							.html(
									"<select id =\"recruiter_s\" class=\"nice-select form-select\">Select Recruiter</select>");
					options += "<option value=\"none\"  selected=\"selected\">Select Recruiter</option>";

					// $('#recruiter-select select').append(options);
					for (i in list) {
						// alert(list[i].employeeId+" "+list[i].employeeName);
						options += "<option value = " + list[i].employeeId
								+ ">" + list[i].employeeName + "</option>";
					}
					options += "<option value=\"99999999\" >Other</option>";
					$('#recruiter-select select').append(options);
					$('#recruiter-select_s select').append(options);
					document.getElementById("recruiter").classList
							.add('form-select');
					document.getElementById("recruiter_s").classList
							.add('form-select');
					// document.getElementById("select_div").className +=
					// "input-group-icon";
				},
				error : function(e) {

					//alert("Unable to load details");
					//alert("Error:  "+ e.responseText);
					console.log("Error: While Retrieving recruiters List."+e.responseText);
					if (e.responseText.includes('Session Expired')) {
						//alert("Session has expired. Please Login.")
						window.location = './'
					}

				}
			});
	
	retrieveInsurancePercent();
	retrieveClients();
}

function retrieveClients(){
	$.ajax({
		type : "GET",
		contentType : "application/json",
		 url: "./getclientcompanies",
		cache : false,
		timeout : 600000,
		success : function(list) {
			var options = "";
			// alert(list.length);
			$('#clientName')
					.html(
							"<select id =\"client\" class=\"nice-select form-select\">Select Client</select>");
			/*$('#recruiter-select_s')
					.html(
							"<select id =\"recruiter_s\" class=\"nice-select form-select\">Select Recruiter</select>");*/
			options += "<option value=\"none\"  selected=\"selected\">Select Client</option>";

			// $('#recruiter-select select').append(options);
			for (i in list) {
				// alert(list[i].employeeId+" "+list[i].employeeName);
				options += "<option value = " + list[i].clientId
						+ ">" + list[i].clientName + "</option>";
			}
			//options += "<option value=\"99999999\" >Other</option>";
			$('#clientName select').append(options);
			//$('#recruiter-select_s select').append(options);
			document.getElementById("client").classList
					.add('form-select');
			//document.getElementById("recruiter_s").classList.add('form-select');
			// document.getElementById("select_div").className +=
			// "input-group-icon";
		},
		error : function(e) {

			//alert("Unable to load details");
			console.log("Error:  While loading clients: "+ e.responseText);
			
			if (e.responseText.includes('Session Expired')) {
				//alert("Session has expired. Please Login.")
				window.location = './'
			}

		}
	});
	
}

function setSelectedVisaCategory() {
	var visatype = document.getElementById("visaCategory").value;

	if (visatype == "Dependent Visa" || visatype == "Temporary graduate visa"
			|| visatype == "Work Visa") {
		// alert("Inside if");
		$("#visatype_div").show();
		$("#visaType_lbl").show();
		$("#visaType").show();
		$("#visavalid_div").show();
		$("#validupto_lbl").show();
		$("#validUpto").show();
	} else {

		$("#visatype_div").hide();
		$("#visaType_lbl").hide();
		$("#visaType").hide();
		$("#visavalid_div").hide();
		$("#validupto_lbl").hide();
		$("#validUpto").hide();

	}

}
function otherCountrySelect() {
	var country = document.getElementById("countrySelect").value;

	if (country == "Other") {
		// alert("Inside if");
		$("#otherCountry_div").show();
		$("#otherCountry_lbl").show();
		$("#otherCountry").show();
		$("#otherState_div").show();
		$("#otherState_lbl").show();
		$("#otherState").show();

	} else {

		$("#otherCountry_div").hide();
		$("#otherCountry_lbl").hide();
		$("#otherCountry").hide();
		$("#otherState_div").hide();
		$("#otherState_lbl").hide();
		$("#otherState").hide();

	}

}
function wlOtherCountrySelect() {
	var country = document.getElementById("wlCountrySelect").value;

	if (country == "Other") {
		// alert("Inside if");
		$("#wlotherCountry_div").show();
		$("#wlotherCountry_lbl").show();
		$("#wlotherCountry").show();
		$("#wlotherState_div").show();
		$("#wlotherState_lbl").show();
		$("#wlotherState").show();

	} else {

		$("#wlotherCountry_div").hide();
		$("#wlotherCountry_lbl").hide();
		$("#wlotherCountry").hide();
		$("#wlotherState_div").hide();
		$("#wlotherState_lbl").hide();
		$("#wlotherState").hide();

	}

}
function nameChangeCheck() {
	// alert(document.getElementById("nameChanged").checked);

	if (document.getElementById("nameChanged").checked) {
		$("#previousName_div").show();
		$("#previousName_lbl").show();
		$("#previousFullName").show();
	} else {
		$("#previousName_div").hide();
		$("#previousName_lbl").hide();
		$("#previousFullName").hide();
	}
}
function gstRegisteredCheck() {
	if (document.getElementById("gstRegistered").checked) {
		$("#gstCert_div").show();
		$("#gstCert_lbl").show();
		$("#gstCertFile").show();
	} else {
		$("#gstCert_div").hide();
		$("#gstCert_lbl").hide();
		$("#gstCertFile").hide();
	}

}
function piPlChecked() {
	if (document.getElementById("piPlFlag").checked) {
		$("#pipl_div1").show();
		$("#pipl_lbl1").show();
		$("#pipl_div2").show();
		$("#pipl_lbl2").show();
		$("#pipl_div3").show();
		$("#pipl_lbl3").show();
		$("#piplcertificate1").show();
		$("#piplcertificate2").show();
		$("#piplcertificate3").show();
	} else {
		$("#pipl_div1").hide();
		$("#pipl_lbl1").hide();
		$("#pipl_div2").hide();
		$("#pipl_lbl2").hide();
		$("#pipl_div3").hide();
		$("#pipl_lbl3").hide();
		$("#piplcertificate1").hide();
		$("#piplcertificate2").hide();
		$("#piplcertificate3").hide();
	}
}

function workCoverFlagCheck() {
	if (document.getElementById("workCoverFlag").checked) {
		$("#workCover_div").show();
		$("#workCover_lbl").show();
		$("#workCoverFile").show();
	} else {
		$("#workCover_div").hide();
		$("#workCover_lbl").hide();
		$("#workCoverFile").hide();
	}

}

function abnChecked() {
	if (document.getElementById("abnCheck").checked) {
		$("#saFundName_div").hide();
		$("#saFundName_lbl").hide();
		$("#saFundName").hide();
		$("#saMemberId_div").hide();
		$("#saMemberId_lbl").hide();
		$("#saMemberId").hide();
		$("#addnlSA_div").hide();
		$("#addnlSA_lbl").hide();
		$("#additionalSACheck").hide();
		$("#tfnAccordian_div").hide();
		$("#bankAccordian_div").hide();
		$("#abnAccordian_div").show();
		$("#abnbank_div").show();

	} else {
		$("#saFundName_div").show();
		$("#saFundName_lbl").show();
		$("#saFundName").show();
		$("#saMemberId_div").show();
		$("#saMemberId_lbl").show();
		$("#saMemberId").show();
		$("#addnlSA_div").show();
		$("#addnlSA_lbl").show();
		$("#additionalSACheck").show();
		$("#tfnAccordian_div").show();
		$("#bankAccordian_div").show();
		$("#abnAccordian_div").hide();
		$("#abnbank_div").hide();

	}

}

function additionalSAChecked() {

	if (document.getElementById("additionalSACheck").checked) {
		$("#addnlSAInfo_div").show();
		$("#addnlSAInfo_lbl").show();
		$("#addnlSAInfo").show();
	} else {
		$("#addnlSAInfo_div").hide();
		$("#addnlSAInfo_lbl").hide();
		$("#addnlSAInfo").hide();
	}
}
/*function backToIndex() {
	window.location = '/index'
}*/

function clickonsave(mode) {

	// alert(mode);
	event.preventDefault();

	var validation = true;
	if (!document.getElementById("abnCheck").checked) {
		if (!validatePersonalDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		if (!validateBankDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		if (!validateTfnDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		if (!validatePersonalDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		if (!validateEmpDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		// validation=validatePersonalDetails()&&validateBankDetails()&&validateTfnDetails()&&validateEmpDetails();
	} else {
		// validation=validatePersonalDetails()&&validateEmpDetails()&&validateAbnDetails();
		if (!validatePersonalDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		if (!validateAbnDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}
		if (!validateEmpDetails()) {
			event.preventDefault();
			validation = false;
			return false;
		}

	}
	// alert(validation);
	if (!validation) {
		event.preventDefault();
		return false;
	} else {
		// alert("entered else");
		var abnchecked = document.getElementById("abnCheck").checked;
		// set Personal details
		var contractorPersonalDetailsDTO = {}

		contractorPersonalDetailsDTO["firstName"] = $("#firstName").val();
		contractorPersonalDetailsDTO["middleName"] = $("#middleName").val();
		contractorPersonalDetailsDTO["lastName"] = $("#lastName").val();
		contractorPersonalDetailsDTO["fullName"] = $("#firstName").val() + " "
				+ $("#middleName").val() + " " + $("#lastName").val();
		contractorPersonalDetailsDTO["dateOfBirth"] = $("#dateOfBirth").val();
		if ($('input[name="gender"]:checked').val() != undefined) {
			// alert("setting gender");
			contractorPersonalDetailsDTO["gender"] = $(
					'input[name="gender"]:checked').val();

		}

		contractorPersonalDetailsDTO["personalEmail"] = $("#personalEmail")
				.val();
		contractorPersonalDetailsDTO["officeEmail"] = $("#officeEmail").val();
		contractorPersonalDetailsDTO["mobilePhone"] = $("#mobilePhone").val();
		contractorPersonalDetailsDTO["homePhone"] = $("#homePhone").val();
		contractorPersonalDetailsDTO["previousName"] = $("#previousFullName")
				.val();
		contractorPersonalDetailsDTO["address"] = $("#address").val();
		contractorPersonalDetailsDTO["city"] = $("#city").val();
		contractorPersonalDetailsDTO["state"] = $("#contractorState").val();
		contractorPersonalDetailsDTO["zipCode"] = $("#zipCode").val();
		contractorPersonalDetailsDTO["country"] = $("#countrySelect").val();
		contractorPersonalDetailsDTO["otherState"] = $("#otherState").val();
		contractorPersonalDetailsDTO["otherCountry"] = $("#otherCountry").val();

		if ($("#visaCategory").val() != "none") {
			// alert("setting visatype");
			contractorPersonalDetailsDTO["visaCategory"] = $("#visaCategory")
					.val();
			contractorPersonalDetailsDTO["visaType"] = $("#visaType").val();
			contractorPersonalDetailsDTO["visaValidDate"] = $("#validUpto")
					.val();
		}
		contractorPersonalDetailsDTO["abnHolder"] = abnchecked;
		contractorPersonalDetailsDTO["emergencyContactName"] = $(
				"#emergencyContactName").val();
		contractorPersonalDetailsDTO["emergencyContactNumber"] = $(
				"#emergencyContactNumber").val();
		contractorPersonalDetailsDTO["emergencyContactAddress"] = $(
				"#emergencyContactAddress").val();
		contractorPersonalDetailsDTO["emergencyContactEmail"] = $(
				"#emergencyContactEmail").val();
		contractorPersonalDetailsDTO["emergencyContactRelation"] = $(
				"#emergencyContactRelation").val();
		// contractorPersonalDetailsDTO["firstName"]=$("#firstName").val();

		// alert("ABN Holder:: "+abnchecked);

		// Employment Details
		// var employers=[];
		var contractorEmploymentDetailsDTO = {}

		//contractorEmploymentDetailsDTO["clientName"] = $("#clientName").val();
		
		if ($("#client").val() != "none"){
			
		contractorEmploymentDetailsDTO["clientName"] = $(
				"#client option:selected").text();
		}
		contractorEmploymentDetailsDTO["endClientName"] = $("#endClientName")
				.val();
		contractorEmploymentDetailsDTO["contractNumber"] = $("#contractNumber")
				.val();
		contractorEmploymentDetailsDTO["workLocationAddress"] = $(
				"#workLocationAddress").val();
		contractorEmploymentDetailsDTO["workLocationCity"] = $(
				"#workLocationCity").val();
		contractorEmploymentDetailsDTO["workLocationState"] = $(
				"#workLocationState").val();
		contractorEmploymentDetailsDTO["workLocationZipCode"] = $(
				"#workLocationZipCode").val();
		contractorEmploymentDetailsDTO["workLocationCountry"] = $(
				"#wlCountrySelect").val();
		contractorEmploymentDetailsDTO["wlOtherState"] = $("#wlotherState")
				.val();
		contractorEmploymentDetailsDTO["wlOtherCountry"] = $("#wlotherCountry")
				.val();
		contractorEmploymentDetailsDTO["jobRole"] = $("#role").val();
		contractorEmploymentDetailsDTO["jobStartDate"] = $("#jobStartDate")
				.val();
		contractorEmploymentDetailsDTO["jobEndDate"] = $("#jobEndDate").val();
		contractorEmploymentDetailsDTO["lastWorkingDate"] = $(
				"#lastWorkingDate").val();
		contractorEmploymentDetailsDTO["finishedClient"] = document
				.getElementById("finishedClientCheck").checked;
		contractorEmploymentDetailsDTO["additionalInfo"] = $(
				"#employmentAddnlInfo").val();
		contractorEmploymentDetailsDTO["employmentType"] = $("#employmentType")
				.val();
		contractorEmploymentDetailsDTO["invoiceNotes"] = $("#invoiceNotes").val();
		contractorEmploymentDetailsDTO["poNumber"] = $("#poNumber").val();
		
		
		// alert($("#recruiter").val()+" "+$("#recruiterId").val()+"
		// "+$("#recruiterName").val())
		if ($("#recruiter").val() != "none")
			contractorEmploymentDetailsDTO["recruiterId"] = $("#recruiter")
					.val();
		else
			contractorEmploymentDetailsDTO["recruiterId"] = 0;
		contractorEmploymentDetailsDTO["recruiterName"] = $(
				"#recruiter option:selected").text();
		// employers[0]=contractorEmploymentDetailsDTO;
		// Rate details
		var contractorRateDetailsDTO = {}
		// var rates=[];

		contractorRateDetailsDTO["ratePerDay"] = $("#ratePerDay").val();
		contractorRateDetailsDTO["billRatePerDay"] = $("#billRatePerDay").val();
		contractorRateDetailsDTO["rateStartDate"] = $("#rateStartDate").val();
		contractorRateDetailsDTO["rateEndDate"] = $("#rateEndDate").val();
		/*contractorRateDetailsDTO["includeSuperFlag"] = document
				.getElementById("superIncludeCheck").checked;*/
		contractorRateDetailsDTO["payrollTaxPaymentFlag"] = document
				.getElementById("payrollTaxCheck").checked;
		contractorRateDetailsDTO["insurancePaymentFlag"] = document
				.getElementById("insurancePaymentCheck").checked;
		contractorRateDetailsDTO["referralCommissionValue"] = $("#referralCommissionValue").val();
		contractorRateDetailsDTO["referralCommissionType"] = $("#referralCommissionType").val();
		contractorRateDetailsDTO["payrollTaxPercentage"] = $("#payrollTaxPercent").val();
		contractorRateDetailsDTO["insurancePercentage"] = $("#insurancePercent").val();
		if(margin==0)
		calculateMargin();
		//alert($("#margin").val());
		//contractorRateDetailsDTO["grossMargin"] =margin;
		//$("#margin").val(margin);
		contractorRateDetailsDTO["grossMargin"] =$("#margin").val();
		
		// rates[0]=contractorRateDetailsDTO;
		var contractorBankDetailsDTO = {}
		// var banks=[];
		var contractorSuperAnnuationDetailsDTO = {}
		// var saList=[];
		var contractorABNDetailsDTO = {}
		// var abnList=[];
		var contractorTFNDetailsDTO = {}
		// var tfnList=[];

		if (abnchecked) {
			// ABN Details
			contractorBankDetailsDTO["accountName"] = $("#abnAccountName")
					.val();
			contractorBankDetailsDTO["bsb"] = $("#abnAccountBsb").val();
			contractorBankDetailsDTO["accountNumber"] = $("#abnBankAccountNo")
					.val();

			contractorABNDetailsDTO["abnNumber"] = $("#abnNumber").val();
			contractorABNDetailsDTO["acnNumber"] = $("#acnNumber").val();
			contractorABNDetailsDTO["abnGroup"] = $("#abnGroup").val();
			contractorABNDetailsDTO["companyName"] = $("#companyName").val();
			contractorABNDetailsDTO["companyAddress"] = $("#companyAddress")
					.val();
			contractorABNDetailsDTO["companyCity"] = $("#companyCity").val();
			contractorABNDetailsDTO["companyState"] = $("#companyState").val();
			contractorABNDetailsDTO["companyZipCode"] = $("#companyZipCode")
					.val();
			contractorABNDetailsDTO["gstRegistered"] = document
					.getElementById("gstRegistered").checked;
			contractorABNDetailsDTO["piPlFlag"] = document
					.getElementById("piPlFlag").checked;
			contractorABNDetailsDTO["workCoverFlag"] = document
					.getElementById("workCoverFlag").checked;

			contractorABNDetailsDTO["additionalInfo"] = $("#abnAdditionalInfo")
					.val();

			contractorABNDetailsDTO["gstCertPath"] = gstCertPath;
			contractorABNDetailsDTO["piPlCert1Path"] = piplCertPath1;
			contractorABNDetailsDTO["piPlCert2Path"] = piplCertPath2;
			contractorABNDetailsDTO["piPlCert3Path"] = piplCertPath3;
			contractorABNDetailsDTO["workCoverCertPath"] = workCoverPath;
			// alert("Path..."+$("#gstCertFile").val());
			/*
			 * if($("#gstCertFile").val()!=""){ getFilePath(function (result) {
			 * alert(result); contractorABNDetailsDTO["gstCertPath"]=result; }); }
			 */

		} else {
			// Bank details
			contractorBankDetailsDTO["accountName"] = $("#accountName").val();
			contractorBankDetailsDTO["bsb"] = $("#accountBsb").val();
			contractorBankDetailsDTO["accountNumber"] = $("#accountNo").val();
			contractorBankDetailsDTO["additionalInfo"] = $(
					"#bankAdditionalInfo").val();

			// Super annuation details
			contractorSuperAnnuationDetailsDTO["superAnnuationFundName"] = $(
					"#saFundName").val();
			contractorSuperAnnuationDetailsDTO["superAnnuationMemberId"] = $(
					"#saMemberId").val();
			contractorSuperAnnuationDetailsDTO["additionalSuperAnnuationContributionFlag"] = document
					.getElementById("additionalSACheck").checked;
			contractorSuperAnnuationDetailsDTO["additionalSuperAnnuationDetails"] = $(
					"#addnlSAInfo").val();
			contractorSuperAnnuationDetailsDTO["additionalInfo"] = $(
					"#saAdditionalInfo").val();

			// TFN Details

			contractorTFNDetailsDTO["tfnNumber"] = $("#tfnNumber").val();
			contractorTFNDetailsDTO["newApplicationFlag"] = document
					.getElementById("newApplicationFlag").checked;
			contractorTFNDetailsDTO["underAgeExemptionFlag"] = document
					.getElementById("underAgeExempFlag").checked;
			contractorTFNDetailsDTO["pensionHolderFlag"] = document
					.getElementById("pensionHolderFlag").checked;
			contractorTFNDetailsDTO["employmentType"] = $("#tfnEmploymentType")
					.val();
			contractorTFNDetailsDTO["taxPayerType"] = $("#taxPaymentType")
					.val();

			if ($('input[name="taxFreeThreshold"]:checked').val() != undefined) {
				// alert("setting taxfree threshold
				// "+$('input[name="taxFreeThreshold"]:checked').val());
				contractorTFNDetailsDTO["taxFreeThresholdFlag"] = $(
						'input[name="taxFreeThreshold"]:checked').val();

			}
			if ($('input[name="loan"]:checked').val() != undefined) {
				// alert("setting loan "+$('input[name="loan"]:checked').val());
				contractorTFNDetailsDTO["loanFlag"] = $(
						'input[name="loan"]:checked').val();

			}
			if ($('input[name="financialDebt"]:checked').val() != undefined) {
				// alert("setting financialDebt
				// "+$('input[name="financialDebt"]:checked').val());
				contractorTFNDetailsDTO["financialShipmentDebtFlag"] = $(
						'input[name="financialDebt"]:checked').val();

			}
			contractorTFNDetailsDTO["additionalInfo"] = $("#tfnAdditionalInfo")
					.val();
		}
		// alert(contractorPersonalDetailsDTO);
		// banks[0]=contractorBankDetailsDTO;
		// saList[0]=contractorSuperAnnuationDetailsDTO;
		// abnList[0]=contractorABNDetailsDTO;
		// tfnList[0]=contractorTFNDetailsDTO;

		var contractorDetailsDTO = {}

		contractorDetailsDTO["bankList"] = contractorBankDetailsDTO;
		contractorDetailsDTO["employerList"] = contractorEmploymentDetailsDTO;
		contractorDetailsDTO["rateList"] = contractorRateDetailsDTO;
		contractorDetailsDTO["tfnList"] = contractorTFNDetailsDTO;
		contractorDetailsDTO["superAnnuationList"] = contractorSuperAnnuationDetailsDTO;
		if (mode == "New") {
			contractorDetailsDTO["personalDetails"] = contractorPersonalDetailsDTO;
			contractorDetailsDTO["abnList"] = contractorABNDetailsDTO;
			
			$.ajax({
				type : "POST",
				contentType : "application/json",

				 url: "./createContractor",
				data : JSON.stringify(contractorDetailsDTO),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					// var response = JSON.stringify(data);
					// alert("Contractor Registered
					// Successfully..."+JSON.stringify(data));

					alert("Contractor Registered Successfully...");
				},
				error : function(e) {
					// var response = JSON.stringify(e);
					console.log(e.responseText);
					alert("Error:  "+ e.responseText);
					
					if (e.responseText.includes('Session Expired')) {
						//alert("Session has expired. Please Login.")
						window.location = './'
					}

				}
			});
		} else if (mode == 'Update') {
			contractorPersonalDetailsDTO["contractorId"] = $("#contractorId")
					.val();

			if (abnchecked) {
				if (contractorABNDetailsDTO["gstCertPath"].trim() == "")
					contractorABNDetailsDTO["gstCertPath"] = $("#gstpath_dtl")
							.text();

				if (contractorABNDetailsDTO["piPlCert1Path"].trim() == "")
					contractorABNDetailsDTO["piPlCert1Path"] = $(
							"#piplpath_dtl").text();

				if (contractorABNDetailsDTO["workCoverCertPath"].trim() == "")
					contractorABNDetailsDTO["workCoverCertPath"] = $(
							"#wcpath_dtl").text();

				contractorDetailsDTO["abnList"] = contractorABNDetailsDTO;
			}
			contractorDetailsDTO["personalDetails"] = contractorPersonalDetailsDTO;
			//alert( $("#margin").val());
			$.ajax({
				type : "POST",
				contentType : "application/json",

				 url: "./updateContractor",
				data : JSON.stringify(contractorDetailsDTO),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					// var response = JSON.stringify(data);
					// alert("Contractor Registered
					// Successfully..."+JSON.stringify(data));

					alert("Contractor Updated Successfully...");
				},
				error : function(e) {
					// var response = JSON.stringify(e);
					console.log(e.responseText);
					alert("Error:  "+ e.responseText);
					
					if (e.responseText.includes('Session Expired')) {
						//alert("Session has expired. Please Login.")
						window.location = './'
					}

				}
			});

		}

	}
}
var gstCertPath = "";
var piplCertPath1 = "";
var piplCertPath2 = "";
var piplCertPath3 = "";
var workCoverPath = "";

function getFilePath(file, variable) {
	// alert($("#piplcertificate").get(0).files.length);
	// alert(variable);
	var formData = new FormData();
	formData.append("uploadedCertificate", file.files[0]);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "./copyCertificate");
	xhr.onload = function() {
		console.log(xhr.responseText);
		if (variable == "gstCert") {
			gstCertPath = xhr.responseText;
		}
		if (variable == "pipl1") {
			piplCertPath1 = xhr.responseText;
		}
		if (variable == "pipl2") {
			piplCertPath2 = xhr.responseText;
		}
		if (variable == "pipl3") {
			piplCertPath3 = xhr.responseText;
		}
		if (variable == "workCover") {
			workCoverPath = xhr.responseText;
		}

		if (xhr.status == 200) {
			// alert("1: "+piplCertPath1);

		} else {
		}
	}
	xhr.send(formData);

}

function validatePersonalDetails() {
	$('#personal_feedback').html("");
	// alert(document.getElementById("contractorState").value);
	// alert(document.getElementById("dateOfBirth").value);
	if ((document.getElementById("firstName").value).trim() == ""
			|| (document.getElementById("lastName").value).trim() == ""
			|| (document.getElementById("dateOfBirth").value).trim == ""
			|| (document.getElementById("personalEmail").value).trim() == ""
			|| (document.getElementById("mobilePhone").value).trim == ""
			|| $('input[name="gender"]:checked').val() == undefined
			|| (document.getElementById("visaCategory").value).trim == "none"
			|| (document.getElementById("address").value).trim() == ""
			|| (document.getElementById("contractorState").value).trim == "none"
			|| (document.getElementById("emergencyContactName").value).trim() == ""
			|| (document.getElementById("emergencyContactNumber").value).trim() == ""
			|| (document.getElementById("emergencyContactAddress").value)
					.trim() == ""
			|| (document.getElementById("emergencyContactEmail").value).trim() == ""
			|| (document.getElementById("emergencyContactRelation").value)
					.trim() == "") {
		document.getElementById("personal_feedback").style.color = "red";
		$('#personal_feedback').html(
				"Please enter all mandatory fields marked with *");
		$('#collapseOne').collapse('show');

		return false;
	} else
		return true;
}
function validateBankDetails() {
	if (!document.getElementById("abnCheck").checked) {
		$('#bank_feedback').html("");
		// alert(document.getElementById("dateOfBirth").value);
		if ((document.getElementById("accountName").value).trim() == ""
				|| (document.getElementById("accountBsb").value).trim() == ""
				|| (document.getElementById("accountNo").value).trim == ""
				|| (document.getElementById("saFundName").value).trim == ""
				|| (document.getElementById("saMemberId").value).trim == "") {

			document.getElementById("bank_feedback").style.color = "red";
			$('#bank_feedback').html(
					"Please enter all mandatory fields marked with *");
			$('#collapseTwo').collapse('show');
			return false;
		} else
			return true;
	} else
		return true;
}
function validateTfnDetails() {
	// alert("tfn");
	if (!document.getElementById("abnCheck").checked) {
		$('#tfn_feedback').html("");
		// alert(document.getElementById("dateOfBirth").value);
		if (((document.getElementById("tfnNumber").value).trim() == ""
				&& (!document.getElementById("newApplicationFlag").checked)
				&& (!document.getElementById("underAgeExempFlag").checked) && (!document
				.getElementById("pensionHolderFlag").checked))) {

			document.getElementById("tfn_feedback").style.color = "red";
			$('#tfn_feedback').html(
					"Please enter all mandatory fields marked with *");
			$('#collapseFour').collapse('show');
			return false;
		}
		if ((document.getElementById("tfnEmploymentType").value).trim() == "none"
				|| (document.getElementById("taxPaymentType").value).trim() == "none"
				|| (document.getElementById("tfnEmploymentType").value).trim() == "none"
				|| $('input[name="taxFreeThreshold"]:checked').val() == undefined
				|| $('input[name="loan"]:checked').val() == undefined
				|| $('input[name="financialDebt"]:checked').val() == undefined) {

			document.getElementById("tfn_feedback").style.color = "red";
			$('#tfn_feedback').html(
					"Please enter all mandatory fields marked with *");
			$('#collapseFour').collapse('show');
			return false;
		} else
			return true;
	}
}
function validateAbnDetails() {
	if (document.getElementById("abnCheck").checked) {
		$('#abn_feedback').html("");
		// alert(document.getElementById("dateOfBirth").value);
		if (((document.getElementById("abnNumber").value).trim() == "" && (document
				.getElementById("acnNumber").value).trim() == "")
				|| (document.getElementById("companyName").value).trim() == ""
				|| (document.getElementById("companyAddress").value).trim() == ""
				|| (document.getElementById("companyCity").value).trim() == ""
				|| (document.getElementById("companyState").value).trim() == "none"
				|| (document.getElementById("abnAccountName").value).trim() == ""
				|| (document.getElementById("abnAccountBsb").value).trim() == ""
				|| (document.getElementById("abnBankAccountNo").value).trim() == "") {
			document.getElementById("abn_feedback").style.color = "red";
			$('#abn_feedback').html(
					"Please enter all mandatory fields marked with *");
			$('#collapseThree').collapse('show');
			return false;
		} else
			return true;
	} else
		return true;

}
function validateEmpDetails() {
	$('#emp_feedback').html("");
	// alert(document.getElementById("dateOfBirth").value);
	if ((document.getElementById("client").value).trim() == "none"
			|| (document.getElementById("employmentType").value).trim() == "none"
			|| (document.getElementById("jobStartDate").value).trim() == ""
			|| (document.getElementById("workLocationState").value).trim() == "none"
			|| (document.getElementById("role").value).trim() == ""
			|| (document.getElementById("recruiter").value).trim() == "none"
			|| (document.getElementById("ratePerDay").value).trim() == ""
			|| (document.getElementById("rateStartDate").value).trim() == ""
			|| (document.getElementById("billRatePerDay").value).trim() == "") {
		document.getElementById("emp_feedback").style.color = "red";
		$('#emp_feedback').html(
				"Please enter all mandatory fields marked with *");
		$('#collapseFive').collapse('show');
		return false;
	} else
		return true;

}

function contractorSearch() {
	$('#search_feedback').html("");
	event.preventDefault();
	if ($("#contractorName_s").val() == "" && $("#clientName_s").val() == ""
			&& $("#endClientName_s").val() == ""
			&& $("#workLocationState_s").val() == "none"
			&& $("#role_s").val() == "" && $("#recruiter_s").val() == "none"
			&& $("#jobStartDate_s").val() == ""
			&& $("#jobEndDate_s").val() == ""
			&& $("#abnholder_s").val() == "none") {
		document.getElementById("search_feedback").style.color = "red";
		$('#search_feedback').html(
				"Please enter at least one value to search contractors.");
		$("#searchresults_div").hide();
		$("#contractorTable").hide();
		// alert("Please enter at least one value to search profiles.");
	} else {
		var contractorSearchForm = {}
		contractorSearchForm["contractorName"] = $("#contractorName_s").val();
		contractorSearchForm["clientName"] = $("#clientName_s").val();
		contractorSearchForm["endClientName"] = $("#endClientName_s").val();
		contractorSearchForm["role"] = $("#role_s").val();
		contractorSearchForm["jobStartDate"] = $("#jobStartDate_s").val();
		contractorSearchForm["jobEndDate"] = $("#jobEndDate_s").val();

		if ($("#recruiter_s").val() != "none") {
			contractorSearchForm["recruiterId"] = $("#recruiter_s").val();
			contractorSearchForm["recruiterName"] = $(
					"#recruiter_s option:selected").text();
		}

		if ($("#workLocationState_s").val() != "none") {
			contractorSearchForm["workLocationState"] = $(
					"#workLocationState_s").val();
		}
		if ($("#abnholder_s").val() != "none") {
			contractorSearchForm["abnHolder"] = $("#abnholder_s").val();
		}

		$
				.ajax({
					type : "POST",
					contentType : "application/json",
					 url: "./searchContractors",
					data : JSON.stringify(contractorSearchForm),
					dataType : 'json',
					cache : false,
					timeout : 600000,
					success : function(data) {
						$("#searchresults_div").show();
						$("#contractorTable").show();
						/*
						 * var response=JSON.stringify(data); alert("Contractor
						 * Search done Successfully..."+data.length);
						 * $.each(data, function(i, item) { var $tr = $('<tr>').append(
						 * $('<td>').text(item.fullName), $('<td>').text(item.contractorId),
						 * $('<td>').text(item.mobilePhone)
						 * ).appendTo('#contractorTable'); //
						 * console.log($tr.wrap('<p>').html()); });
						 */

						var table = $('#contractorTable').DataTable({

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

							columns : [ {
								"data" : 'fullName',
								"name" : "fullName",
								"title" : "Name"
							}, {
								"data" : 'mobilePhone',
								"name" : "mobilePhone",
								"title" : "Phone"
							}, {
								"data" : 'personalEmail',
								"name" : "personalEmail",
								"title" : "Email"
							}, {
								"data" : 'clientName',
								"name" : "clientName",
								"title" : "Client Name"
							}, {
								"data" : 'endClientName',
								"name" : "endClientName",
								"title" : "End Client Name"
							}, {
								"data" : 'workLocationState',
								"name" : "workLocationState",
								"title" : "Work Location State"
							}, {
								"data" : 'jobRole',
								"name" : "jobRole",
								"title" : "Role"
							}, {
								"data" : 'recruiterName',
								"name" : "recruiterName",
								"title" : "Recruiter"
							}, {
								"data" : 'ratePerDay',
								"name" : "ratePerDay",
								"title" : "Rate Per Day"
							}, {
								"data" : 'billRatePerDay',
								"name" : "billRatePerDay",
								"title" : "Bill Rate"
							}, {
								"data" : 'jobStartDate',
								"name" : "jobStartDate",
								"title" : "Start Date"
							}, {
								"data" : 'jobEndDate',
								"name" : "jobEndDate",
								"title" : "End Date"
							}, {
								"data" : 'abnHolder',
								"name" : "abnHolder",
								"title" : "ABN Holder"
							} ]

						});
						$('#contractorTable tfoot tr').appendTo(
								'#contractorTable thead');

						$("#contractorTable tfoot tr").hide();

					},
					error : function(e) {

						var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
								+ e.responseText + "</pre>";
						$('#search_feedback').html(json);

						console.log("ERROR : ", e);
						// $("#btn-search").prop("disabled", false);
						//alert("Error:  "+ e.responseText);
						
						if (e.responseText.includes('Session Expired')) {
							//alert("Session has expired. Please Login.")
							window.location = './'
						}
					}
				});
	}

}

function statechange() {
	$('.state_id option').each(function() {
		//alert("called " + $(this).val());
		if ($(this).val() == 'none') {
			//alert("set...");
			$(this).prop("selected", true);
			// $(this).val("none").change();
		}
	});
}
function resetSearch() {

	$("#searchForm")[0].reset();

	//$("#workLocationState_s").val('none');
//	$("#abnholder_s").val('none');
	
	$("#contractorTable").hide();
	$("#searchresults_div").hide();
	event.preventDefault();
}

$(document).on(
		"click",
		"#contractorTable tbody tr",
		function(e)

		{

			var table = $('#contractorTable').DataTable();
			var rowData = table.row(this).data();
			$('#contractorTable tbody > tr').removeClass('selected');
			 $(this).addClass('selected');
			// alert('called'+rowData.contractorId);
			$.ajax({
				type : "GET",
				contentType : "application/json",
				 url: "./contractordetails",
				cache : false,
				timeout : 600000,
				success : function(data) {
					
					var win = window.open("./contractordetails/"
							+ rowData.contractorId, "mywindow","status=1,toolbar=0");
				},
				error : function(e) {

					//alert("Unable to load details. " + e);
					alert("Error:  "+ e.responseText);
					
					if (e.responseText.includes('Session Expired')) {
						//alert("Session has expired. Please Login.")
						window.location = './'
					}

				}
			});
		});

var margin=0;

function calculateMargin(){
	var marginDTO={}
	marginDTO["contractorRate"] = $("#ratePerDay").val();
	marginDTO["billRate"] = $("#billRatePerDay").val();
	//marginDTO["superIncludeCheck"] = document.getElementById("superIncludeCheck").checked;
	marginDTO["payrollTaxCheck"] = document.getElementById("payrollTaxCheck").checked;
	marginDTO["insurancePaymentFlag"] =  document.getElementById("insurancePaymentCheck").checked;
	marginDTO["workLocationState"] = $("#workLocationState").val();
	marginDTO["referralCommissionType"] = $("#referralCommissionType").val();
	marginDTO["referralCommissionValue"] = $("#referralCommissionValue").val();
	marginDTO["payrollTax"] = $("#payrollTaxPercent").val();
	marginDTO["insurancePercentage"] = $("#insurancePercent").val();
	//marginDTO["additionalCost"] = $("#otherDeduction").val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		 url: "./calculatemargin",
		data : JSON.stringify(marginDTO),
		dataType : 'json',
		cache : false,
		timeout : 600000,
			success : function(data) {	
				//alert(data.payrollTax);
				var margin=data.grossMargin+"";
				if(margin.indexOf('.')==-1)
					margin=margin+".00";
				$("#margin").val(margin);
				margin=data.grossMargin;
				if($("#referralCommissionValue").val()!=""){
					var referralVal=data.referralValue+"";
					if(referralVal.indexOf('.')==-1)
						referralVal=referralVal+".00";
				$("#referralvalue_lbl").text("Referral Commission: $ "+referralVal);
				}
				var insuranceVal=data.insuranceValue+"";
				if(insuranceVal.indexOf('.')==-1)
					insuranceVal=insuranceVal+".00";
				$("#insurancevalue_lbl").text("Insurance: $  "+insuranceVal);
				var payrolltax=data.payrollTaxValue+"";
				if(payrolltax.indexOf('.')==-1)
					payrolltax=payrolltax+".00";
				$("#payrolltaxvalue_lbl").text("Payroll Tax: $  "+payrolltax);
				
				payrollTaxPercent=data.payrollTax;
				insurancePercent=data.insurancePercentage;
				
	     },
	     error: function (e) {
	     	

	         var json = "Response Error:Error occured while calculating margin. Please check whether all values are entered correctly."
	             + e.responseText ;
	         $('#emp_feedback').html(json);

	         console.log("ERROR : ", e);
	        // $("#btn-search").prop("disabled", false);

	     }
	 });
	
}

function retrievePayrollTax(){
	
	if($("#workLocationState").val()!="" && $("#workLocationState").val()!="none"
		&& document.getElementById("payrollTaxCheck").checked){
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			 url: "./payrolltax/"+$("#workLocationState").val(),
			cache : false,
			timeout : 600000,
			success : function(data) {
				//alert(data);
				$("#payrollTaxPercent").val(data);
				if($("#ratePerDay").val()!=0 && $("#ratePerDay").val()!="" && data!=0){
					var payrolltaxval=$("#ratePerDay").val()*data/100;
				$("#payrolltaxvalue_lbl").text("Payroll Tax: $  "+payrolltaxval);
				
				}
				
				calcPercentages();
				
			},
			error : function(e) {

alert("Error:  "+ e.responseText);
				
				if (e.responseText.includes('Session Expired')) {
					//alert("Session has expired. Please Login.")
					window.location = './'
				}

			}
		});
		
	}else{
		$("#payrollTaxPercent").val(0);
		
		$("#payrolltaxvalue_lbl").text("");
	}
	calcPercentages();

	
}
function retrieveInsurancePercent(){
	
	if( document.getElementById("insurancePaymentCheck").checked){
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			 url: "./insurancePercent",
			cache : false,
			timeout : 600000,
			success : function(data) {
				//alert(data);
				$("#insurancePercent").val(data);
				if($("#ratePerDay").val()!=0 && $("#ratePerDay").val()!=""){
					var insurance=$("#ratePerDay").val() * data/100;
					$("#insurancevalue_lbl").text("Insurance: $  "+insurance+".00");
				}
				calcPercentages();
			},
			error : function(e) {

				console.log("Error: while getting Insurance Percent "+ e.responseText);
				
				if (e.responseText.includes('Session Expired')) {
					//alert("Session has expired. Please Login.")
					window.location = './'
				}

			}
		});
		
	}else  {
		$("#insurancePercent").val(0);
	$("#insurancevalue_lbl").text("");
	}
	calcPercentages();
	
}

function calcPercentages(){
	if($("#ratePerDay").val()!=0 && $("#ratePerDay").val()!="")
		{
		var insurance=0.0;
		var payrolltaxval=0.0;
		var referralValue=0.0;
		var margin=0.0;
		
		if( $("#insurancePercent").val()!="" && $("#insurancePercent").val()!=0){
		 insurance=$("#ratePerDay").val() * $("#insurancePercent").val()/100;
		 //console.log("insurance "+insurance);
		insurance=insurance.toFixed(2);
		$("#insurancevalue_lbl").text("Insurance: $  "+insurance);
		}
		
		if($("#workLocationState").val()!="" && $("#workLocationState").val()!="none"
			&& $("#payrollTaxPercent").val()!="" && $("#payrollTaxPercent").val()!=0){
		 payrolltaxval=$("#ratePerDay").val()*$("#payrollTaxPercent").val()/100;
		 payrolltaxval=payrolltaxval.toFixed(2);
		$("#payrolltaxvalue_lbl").text("Payroll Tax: $  "+payrolltaxval);
		}
		if( $("#payrollTaxPercent").val()!="" && $("#payrollTaxPercent").val()!=0){
		 payrolltaxval=$("#ratePerDay").val()*$("#payrollTaxPercent").val()/100;
		 payrolltaxval=payrolltaxval.toFixed(2);
		$("#payrolltaxvalue_lbl").text("Payroll Tax: $  "+payrolltaxval);
		}
		if($("#referralCommissionType").val()!="none" && $("#referralCommissionType").val()!="" && 
				 $("#referralCommissionValue").val()!="" && $("#referralCommissionValue").val()!=0){
			if($("#referralCommissionType").val()=="percent" ){
				 referralValue=$("#ratePerDay").val()*$("#referralCommissionValue").val()/100;
				 referralValue=referralValue.toFixed(2);
				$("#referralvalue_lbl").text("Referral Commission: $ "+referralValue);
			}
			
			if($("#referralCommissionType").val()=="amount" ){
				referralValue=$("#referralCommissionValue").val();
				
			}
		}else $("#referralvalue_lbl").text("");
		//alert("call 1");
		if($("#billRatePerDay").val()!=0 && $("#billRatePerDay").val()!=""){
			//alert("call 2");
			var billrate=Number($("#billRatePerDay").val());
			var contractorRate=Number($("#ratePerDay").val());
			//alert(billrate+" "+contractorRate+" "+insurance+" "+payrolltaxval+" "+referralValue);
			//console.log("insurance "+insurance+" payrolltaxval "+payrolltaxval);
			margin=(billrate-(Number(contractorRate)+Number(insurance)+Number(payrolltaxval)+Number(referralValue))).toFixed(2);
			$("#margin").val(margin);
			
		}
		else $("#margin").val("");
	}
	else {
		if($("#referralCommissionType").val()=="percent" ){
		$("#referralvalue_lbl").text("");
		}
		$("#margin").val("");
		$("#payrolltaxvalue_lbl").text("");
		$("#insurancevalue_lbl").text("");
	}
	if($("#referralCommissionType").val()=="amount" &&  $("#referralCommissionValue").val()!="" && $("#referralCommissionValue").val()!=0){
		referralValue=$("#referralCommissionValue").val()+".00";
		//referralValue=referralValue.toFixed(2);
		$("#referralvalue_lbl").text("Referral Commission: $ "+referralValue);
	}
}