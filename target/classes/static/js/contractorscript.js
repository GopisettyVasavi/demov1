
function initialize(){
	
	 $.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/recruiterList",
	        cache: false,
	        timeout: 600000,
	        success: function (list) {
	        	var options="";
	        // alert(list.length);
	         $('#recruiter-select').html("<select id =\"recruiter\" class=\"form-select\">Select Recruiter</select>");
	       
	         options += "<option value=\"none\"  selected=\"selected\">Select Recruiter</option>";
	        // $('#recruiter-select select').append(options);
	        for(i in list){
	        	//alert(list[i].employeeId+" "+list[i].employeeName);
	        	options += "<option value = "+list[i].employeeId+">"+list[i].employeeName+"</option>";
	        }
	        $('#recruiter-select select').append(options);
	       document.getElementById("recruiter").classList.add('form-select');
	         //document.getElementById("select_div").className += "input-group-icon";
	        },
	        error: function (e) {
	        	
	        	alert("Unable to load details");

	        }
	    });
}


function setSelectedVisaCategory() {
	var visatype=document.getElementById("visaCategory").value;
	
	if(visatype=="Dependent Visa" || visatype=="Temporary graduate visa" || visatype=="Work Visa"){
		//alert("Inside if");
		$("#visatype_div").show();
		$("#visaType_lbl").show();
		$("#visaType").show();
		$("#visavalid_div").show();
		$("#validupto_lbl").show();
		$("#validUpto").show();
		}else{
		
		$("#visatype_div").hide();
		$("#visaType_lbl").hide();
		$("#visaType").hide();
		$("#visavalid_div").hide();
		$("#validupto_lbl").hide();
		$("#validUpto").hide();
		
	}
	
}
function otherCountrySelect(){
var country=document.getElementById("countrySelect").value;
	
	if(country=="Other"){
		//alert("Inside if");
		$("#otherCountry_div").show();
		$("#otherCountry_lbl").show();
		$("#otherCountry").show();
		$("#otherState_div").show();
		$("#otherState_lbl").show();
		$("#otherState").show();
		
		}else{
		
		$("#otherCountry_div").hide();
		$("#otherCountry_lbl").hide();
		$("#otherCountry").hide();
		$("#otherState_div").hide();
		$("#otherState_lbl").hide();
		$("#otherState").hide();
			
	}
	
}
function wlOtherCountrySelect(){
	var country=document.getElementById("wlCountrySelect").value;
		
		if(country=="Other"){
			//alert("Inside if");
			$("#wlotherCountry_div").show();
			$("#wlotherCountry_lbl").show();
			$("#wlotherCountry").show();
			$("#wlotherState_div").show();
			$("#wlotherState_lbl").show();
			$("#wlotherState").show();
			
			}else{
			
			$("#wlotherCountry_div").hide();
			$("#wlotherCountry_lbl").hide();
			$("#wlotherCountry").hide();
			$("#wlotherState_div").hide();
			$("#wlotherState_lbl").hide();
			$("#wlotherState").hide();
				
		}
		
	}
function nameChangeCheck(){
	//alert(document.getElementById("nameChanged").checked);
	
	if(document.getElementById("nameChanged").checked){
		$("#previousName_div").show();
		$("#previousName_lbl").show();
		$("#previousFullName").show();
	}else{
		$("#previousName_div").hide();
		$("#previousName_lbl").hide();
		$("#previousFullName").hide();
	}
}
function gstRegisteredCheck(){
	if(document.getElementById("gstRegistered").checked){
		$("#gstCert_div").show();
		$("#gstCert_lbl").show();
		$("#gstCertFile").show();
	}else{
		$("#gstCert_div").hide();
		$("#gstCert_lbl").hide();
		$("#gstCertFile").hide();
	}
	
}
function piPlChecked(){
	if(document.getElementById("piPlFlag").checked){
		$("#pipl_div1").show();
		$("#pipl_lbl1").show();
		$("#pipl_div2").show();
		$("#pipl_lbl2").show();
		$("#pipl_div3").show();
		$("#pipl_lbl3").show();
		$("#piplcertificate1").show();
		$("#piplcertificate2").show();
		$("#piplcertificate3").show();
	}else{
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

function workCoverFlagCheck(){
	if(document.getElementById("workCoverFlag").checked){
		$("#workCover_div").show();
		$("#workCover_lbl").show();
		$("#workCoverFile").show();
	}else{
		$("#workCover_div").hide();
		$("#workCover_lbl").hide();
		$("#workCoverFile").hide();
	}
	
}

function abnChecked(){
	if(document.getElementById("abnCheck").checked){
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
		
		
		
	}else{
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

function additionalSAChecked(){
	
	if(document.getElementById("additionalSACheck").checked){
		$("#addnlSAInfo_div").show();
		$("#addnlSAInfo_lbl").show();
		$("#addnlSAInfo").show();
	}
	else{
		$("#addnlSAInfo_div").hide();
		$("#addnlSAInfo_lbl").hide();
		$("#addnlSAInfo").hide();
	}
}
function backToIndex(){
	window.location='/index'
}

function clickonsave(){
	event.preventDefault();
	var abnchecked=document.getElementById("abnCheck").checked;
	//set Personal details
	var contractorPersonalDetailsDTO={}	
	
	contractorPersonalDetailsDTO["firstName"]=$("#firstName").val();
	contractorPersonalDetailsDTO["middleName"]=$("#middleName").val();
	contractorPersonalDetailsDTO["lastName"]=$("#lastName").val();
	contractorPersonalDetailsDTO["dateOfBirth"]=$("#dateOfBirth").val();
	if($('input[name="gender"]:checked').val()!=undefined){
		//alert("setting gender");
		contractorPersonalDetailsDTO["gender"] =$('input[name="gender"]:checked').val();
		
	}
	
	contractorPersonalDetailsDTO["personalEmail"]=$("#personalEmail").val();
	contractorPersonalDetailsDTO["officeEmail"]=$("#officeEmail").val();
	contractorPersonalDetailsDTO["mobilePhone"]=$("#mobilePhone").val();
	contractorPersonalDetailsDTO["homePhone"]=$("#homePhone").val();
	contractorPersonalDetailsDTO["previousName"]=$("#previousFullName").val();
	contractorPersonalDetailsDTO["address"]=$("#address").val();
	contractorPersonalDetailsDTO["city"]=$("#city").val();
	contractorPersonalDetailsDTO["state"]=$("#contractorState").val();
	contractorPersonalDetailsDTO["zipCode"]=$("#zipCode").val();
	contractorPersonalDetailsDTO["country"]=$("#countrySelect").val();
	contractorPersonalDetailsDTO["otherState"]=$("#otherState").val();
	contractorPersonalDetailsDTO["otherCountry"]=$("#otherCountry").val();
	
	if( $("#visaCategory").val()!="none"){
		//alert("setting visatype");
		contractorPersonalDetailsDTO["visaCategory"] = $("#visaCategory").val();
		contractorPersonalDetailsDTO["visaType"] = $("#visaType").val();
		contractorPersonalDetailsDTO["visaValidDate"] = $("#validUpto").val();
	}
	contractorPersonalDetailsDTO["abnHolder"]=abnchecked;
	contractorPersonalDetailsDTO["emergencyContactName"]=$("#emergencyContactName").val();
	contractorPersonalDetailsDTO["emergencyContactNumber"]=$("#emergencyContactNumber").val();
	contractorPersonalDetailsDTO["emergencyContactAddress"]=$("#emergencyContactAddress").val();
	contractorPersonalDetailsDTO["emergencyContactEmail"]=$("#emergencyContactEmail").val();
	contractorPersonalDetailsDTO["emergencyContactRelation"]=$("#emergencyContactRelation").val();
	//contractorPersonalDetailsDTO["firstName"]=$("#firstName").val();
	
	//alert("ABN Holder:: "+abnchecked);
	
	//Employment Details
	var employers=[];
	var contractorEmploymentDetailsDTO= {}
	
		contractorEmploymentDetailsDTO["clientName"]=$("#clientName").val();
		contractorEmploymentDetailsDTO["endClientName"]=$("#endClientName").val();
		contractorEmploymentDetailsDTO["contractNumber"]=$("#contractNumber").val();
		contractorEmploymentDetailsDTO["workLocationAddress"]=$("#workLocationAddress").val();
		contractorEmploymentDetailsDTO["workLocationCity"]=$("#workLocationCity").val();
		contractorEmploymentDetailsDTO["workLocationState"]=$("#workLocationState").val();
		contractorEmploymentDetailsDTO["workLocationZipCode"]=$("#workLocationZipCode").val();
		contractorEmploymentDetailsDTO["workLocationCountry"]=$("#wlCountrySelect").val();
		contractorEmploymentDetailsDTO["wlOtherState"]=$("#wlotherState").val();
		contractorEmploymentDetailsDTO["wlOtherCountry"]=$("#wlotherCountry").val();
		contractorEmploymentDetailsDTO["jobRole"]=$("#role").val();
		contractorEmploymentDetailsDTO["jobStartDate"]=$("#jobStartDate").val();
		contractorEmploymentDetailsDTO["jobEndDate"]=$("#jobEndDate").val();
		contractorEmploymentDetailsDTO["lastWorkingDate"]=$("#lastWorkingDate").val();
		contractorEmploymentDetailsDTO["finishedClient"]=document.getElementById("finishedClientCheck").checked;
		contractorEmploymentDetailsDTO["additionalInfo"]=$("#employmentAddnlInfo").val();
		contractorEmploymentDetailsDTO["employmentType"]=$("#employmentType").val();
		
		employers[0]=contractorEmploymentDetailsDTO;
		//Rate details
		var contractorRateDetailsDTO={}
		var rates=[];
		
		contractorRateDetailsDTO["ratePerDay"]=$("#ratePerDay").val();
		contractorRateDetailsDTO["billRatePerDay"]=$("#billRatePerDay").val();
		contractorRateDetailsDTO["rateStartDate"]=$("#rateStartDate").val();
		contractorRateDetailsDTO["rateEndDate"]=$("#rateEndDate").val();
		contractorRateDetailsDTO["includeSuperFlag"]=document.getElementById("superIncludeCheck").checked; 
		contractorRateDetailsDTO["payrollTaxPaymentFlag"]=document.getElementById("payrollTaxCheck").checked;
		contractorRateDetailsDTO["workCoverFlag"]=document.getElementById("workCoverCheck").checked;
		contractorRateDetailsDTO["insurancePercentage"]=$("#insurance").val();
		contractorRateDetailsDTO["otherDeductionPercentage"]=$("#otherDeduction").val();
		contractorRateDetailsDTO["netMargin"]=$("#netMargin").val();
		if($("#recruiter").val()!="none")
		contractorRateDetailsDTO["recruiterId"]=$("#recruiter").val();
		else contractorRateDetailsDTO["recruiterId"]=0;
		contractorRateDetailsDTO["recruiterName"]=$( "#recruiter option:selected" ).text();
		
		rates[0]=contractorRateDetailsDTO;
	var contractorBankDetailsDTO={}
	var banks=[];
	var contractorSuperAnnuationDetailsDTO ={}
	var saList=[];
	var contractorABNDetailsDTO ={}
	var abnList=[];
	var contractorTFNDetailsDTO ={}
	var tfnList=[];
			
	if(abnchecked){
		//ABN Details
		contractorBankDetailsDTO["accountName"]=$("#abnAccountName").val();
		contractorBankDetailsDTO["bsb"]=$("#abnAccountBsb").val();
		contractorBankDetailsDTO["accountNumber"]=$("#abnBankAccountNo").val();
		
		contractorABNDetailsDTO["abnNumber"]=$("#abnNumber").val();
		contractorABNDetailsDTO["acnNumber"]=$("#acnNumber").val();
		contractorABNDetailsDTO["abnGroup"]=$("#abnGroup").val();
		contractorABNDetailsDTO["companyName"]=$("#companyName").val();
		contractorABNDetailsDTO["companyAddress"]=$("#companyAddress").val();
		contractorABNDetailsDTO["companyCity"]=$("#companyCity").val();
		contractorABNDetailsDTO["companyState"]=$("#companyState").val();
		contractorABNDetailsDTO["companyZipCode"]=$("#companyZipCode").val();
		contractorABNDetailsDTO["gstRegistered"]=document.getElementById("gstRegistered").checked;
		contractorABNDetailsDTO["piPlFlag"]=document.getElementById("piPlFlag").checked;
		contractorABNDetailsDTO["workCoverFlag"]=document.getElementById("workCoverFlag").checked;
		
		contractorABNDetailsDTO["additionalInfo"]=$("#abnAdditionalInfo").val();
		
		contractorABNDetailsDTO["gstCertPath"]=gstCertPath;
		contractorABNDetailsDTO["piPlCert1Path"]=piplCertPath1;
		contractorABNDetailsDTO["piPlCert2Path"]=piplCertPath2;
		contractorABNDetailsDTO["piPlCert3Path"]=piplCertPath3;
		contractorABNDetailsDTO["workCoverCertPath"]=workCoverPath;
		//alert("Path..."+$("#gstCertFile").val());
		/*if($("#gstCertFile").val()!=""){
			getFilePath(function (result) {
			    alert(result);
				contractorABNDetailsDTO["gstCertPath"]=result;
			});
		}*/
		
	}else{
		//Bank details
	contractorBankDetailsDTO["accountName"]=$("#accountName").val();
	contractorBankDetailsDTO["bsb"]=$("#accountBsb").val();
	contractorBankDetailsDTO["accountNumber"]=$("#accountNo").val();	
	contractorBankDetailsDTO["additionalInfo"]=$("#bankAdditionalInfo").val();
	
	//Super annuation details
	contractorSuperAnnuationDetailsDTO["superAnnuationFundName"]=$("#saFundName").val();
	contractorSuperAnnuationDetailsDTO["superAnnuationMemberId"]=$("#saMemberId").val();
	contractorSuperAnnuationDetailsDTO["additionalSuperAnnuationContributionFlag"]=document.getElementById("additionalSACheck").checked;
	contractorSuperAnnuationDetailsDTO["additionalSuperAnnuationDetails"]=$("#addnlSAInfo").val();
	contractorSuperAnnuationDetailsDTO["additionalInfo"]=$("#saAdditionalInfo").val();
	
	//TFN Details
	
	contractorTFNDetailsDTO["tfnNumber"]=$("#tfnNumber").val();
	contractorTFNDetailsDTO["newApplicationFlag"]=document.getElementById("newApplicationFlag").checked;
	contractorTFNDetailsDTO["underAgeExemptionFlag"]=document.getElementById("underAgeExempFlag").checked;
	contractorTFNDetailsDTO["pensionHolderFlag"]=document.getElementById("pensionHolderFlag").checked;
	contractorTFNDetailsDTO["employmentType"]=$("#tfnEmploymentType").val();
	contractorTFNDetailsDTO["taxPayerType"]=$("#taxPaymentType").val();
	
	if($('input[name="taxFreeThreshold"]:checked').val()!=undefined){
		//alert("setting taxfree threshold "+$('input[name="taxFreeThreshold"]:checked').val());
		contractorTFNDetailsDTO["taxFreeThresholdFlag"] =$('input[name="taxFreeThreshold"]:checked').val();
		
	}
	if($('input[name="loan"]:checked').val()!=undefined){
		//alert("setting loan "+$('input[name="loan"]:checked').val());
		contractorTFNDetailsDTO["loanFlag"] =$('input[name="loan"]:checked').val();
		
	}
	if($('input[name="financialDebt"]:checked').val()!=undefined){
		//alert("setting financialDebt "+$('input[name="financialDebt"]:checked').val());
		contractorTFNDetailsDTO["financialShipmentDebtFlag"] =$('input[name="financialDebt"]:checked').val();
		
	}
	contractorTFNDetailsDTO["additionalInfo"]=$("#tfnAdditionalInfo").val();
	}
	//alert(contractorPersonalDetailsDTO);
	banks[0]=contractorBankDetailsDTO;
	saList[0]=contractorSuperAnnuationDetailsDTO;
	abnList[0]=contractorABNDetailsDTO;
	tfnList[0]=contractorTFNDetailsDTO;
	
	
	var contractorDetailsDTO = {}
	 contractorDetailsDTO["personalDetails"] =contractorPersonalDetailsDTO;
	 contractorDetailsDTO["bankList"]=banks;
	 contractorDetailsDTO["employerList"]=employers;
	 contractorDetailsDTO["rateList"]=rates;
	 contractorDetailsDTO["abnList"]=abnList;
	 contractorDetailsDTO["tfnList"]=tfnList;
	 contractorDetailsDTO["superAnnuationList"]=saList;
	 
	 $.ajax({
			type : "POST",
			contentType : "application/json",
			
			url : "/createContractor",
			data : JSON.stringify(contractorDetailsDTO),
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {
				//var response = JSON.stringify(data);
	       	alert("Contractor Registered Successfully...");
	          

	       },
	       error: function (e) {
	    	   //var response = JSON.stringify(e);
	    	   console.log(e.responseText);
	    	   alert("error"+e.responseText);

	       }
	   });

} 

var gstCertPath="";
var piplCertPath1="";
var piplCertPath2="";
var piplCertPath3="";
var workCoverPath="";

function getFilePath(file, variable){
	//alert($("#piplcertificate").get(0).files.length);
	//alert(variable);
	var formData = new FormData();
	 formData.append("uploadedCertificate", file.files[0]);
	 var xhr = new XMLHttpRequest();
	    xhr.open("POST", "/copyCertificate");
	    xhr.onload = function() {
	        console.log(xhr.responseText);
	        if(variable=="gstCert"){
	        	gstCertPath=	xhr.responseText;
	        }
	        if(variable=="pipl1"){
	        	piplCertPath1=	xhr.responseText;
	        }
	        if(variable=="pipl2"){
	        	piplCertPath2=	xhr.responseText;
	        }
	        if(variable=="pipl3"){
	        	piplCertPath3=	xhr.responseText;
	        }
	        if(variable=="workCover"){
	        	workCoverPath=	xhr.responseText;
	        }
	       
	      if(xhr.status == 200) {
	    	  //alert("1: "+piplCertPath1);
	    	 
	        } else {
	          	        }
	    }
	    xhr.send(formData);
	
}

