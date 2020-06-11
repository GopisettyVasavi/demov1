function initialize_details(){
	populateRecruiterList();
	//alert($("#gender_dtl").val());
	if($("#gender_dtl").val()=="male"){
		$('input:radio[name=gender]')[0].checked = true;
		}
		else if($("#gender_dtl").val()=="female"){
			$('input:radio[name=gender]')[1].checked = true;
	}
	
	if($("#previousFullName").val().trim()!=""){
		document.getElementById("nameChanged").checked=true;
		$("#previousName_div").show();
		$("#previousName_lbl").show();
		$("#previousFullName").show();
	}
	if(document.getElementById("pipl_dtl").value=="true"){
		document.getElementById("piPlFlag").checked=true;
	}
	if(document.getElementById("gstRegistered_dtl").value=="true"){
		document.getElementById("gstRegistered").checked=true;
	}
	if(document.getElementById("wc_dtl").value=="true"){
		document.getElementById("workCoverFlag").checked=true;
	}
	if(document.getElementById("tfn_na_dtl").value=="true"){
		document.getElementById("newApplicationFlag").checked=true;
	}
	if(document.getElementById("tfn_ua_dtl").value=="true"){
		document.getElementById("underAgeExempFlag").checked=true;
	}
	if(document.getElementById("tfn_ph_dtl").value=="true"){
		document.getElementById("pensionHolderFlag").checked=true;
	}
	
	if(document.getElementById("tfn_ph_dtl").value=="true"){
		document.getElementById("pensionHolderFlag").checked=true;
	}
	
	if($("#tfn_tfthreshold_dtl").val()=="Yes"){
		$('input:radio[name=taxFreeThreshold]')[0].checked = true;
		}
		else if($("#tfn_tfthreshold_dtl").val()=="No" || $("#tfn_tfthreshold_dtl").val()=="no"){
			$('input:radio[name=taxFreeThreshold]')[1].checked = true;
	}
	//alert($("#tfn_tfthreshold_dtl").val());
	if($("#tfn_loan_dtl").val()=="Yes"){
		$('input:radio[name=loan]')[0].checked = true;
		}
		else if($("#tfn_loan_dtl").val()=="No" || $("#tfn_loan_dtl").val()=="no"){
			$('input:radio[name=loan]')[1].checked = true;
	}
	if($("#tfn_fd_dtl").val()=="Yes"){
		$('input:radio[name=financialDebt]')[0].checked = true;
		}
		else if($("#tfn_fd_dtl").val()=="No" || $("#tfn_fd_dtl").val()=="no"){
			$('input:radio[name=financialDebt]')[1].checked = true;
	}
	if(document.getElementById("fin_client_dtl").value=="true"){
		document.getElementById("finishedClientCheck").checked=true;
	}
	if(document.getElementById("super_dtl").value=="true"){
		document.getElementById("superIncludeCheck").checked=true;
	}
	
	if(document.getElementById("payroll_dtl").value=="true"){
		document.getElementById("payrollTaxCheck").checked=true;
	}
		
	if(document.getElementById("wcflag_dtl").value=="true"){
		document.getElementById("workCoverCheck").checked=true;
	}
	
	
	/*$("#recruiter").val($("#recruiterid_dtl").val());
	$("#recruiter").val($("#recruitername_dtl").val());
	alert($("#recruiterid_dtl").val()+" "+ $("#recruitername_dtl").val()+" "+$("#recruiter").val());*/
	visaSelection();
	countrySelection();
	abnSelection();
	addnlSASelection();
	gstSelection();
	piplSelection();
	workCoverSelection();
	wlOtherCountrySelect();
	/*alert($("#recruitername_dtl").val());
	var rec=$("#recruitername_dtl").val();
	$("#recruiter_dtl option").each(function() {
		alert($(this).text());
		  if($(this).text() == rec) {
		    $(this).attr('selected', 'selected');            
		  }                        
		});*/
	//alert($("#previousFullName").val());
}

function populateRecruiterList(){
	 $.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/recruiterList",
	        cache: false,
	        timeout: 600000,
	        success: function (list) {
	        	var options="";
	        	
	        	var recId=$("#recruiterid_dtl").val();
	        	//alert(rec);
	         $('#recruiter-select').html("<select id =\"recruiter\" class=\"nice-select form-select\">Select Recruiter</select>");
	         
	         
	         
	        // $('#recruiter-select select').append(options);
	        for(i in list){
	        	if(list[i].employeeId==recId){
	        		//alert("if sel "+ list[i].employeeName);
	        		options += "<option value = "+list[i].employeeId+"  selected=\"selected\">"+list[i].employeeName+"</option>";
	        	}
	        }
	        options += "<option value=\"none\"  >Select Recruiter</option>";
	        for(j in list){
	        	if(list[j].employeeId!=recId){
	        	options += "<option value = "+list[j].employeeId+">"+list[j].employeeName+"</option>";
	        	}
	        	}
	        options += "<option value=\"99999999\" >Other</option>";
	        $('#recruiter-select select').append(options);
	       
	       document.getElementById("recruiter").classList.add('form-select');
	       
	         //document.getElementById("select_div").className += "input-group-icon";
	        },
	        error: function (e) {
	        	
	        	alert("Unable to load details");

	        }
	    });
	
}

function visaSelection() {
	var visatype=document.getElementById("visaCategory").value;
	//alert(visatype);
	if(visatype=="Dependent Visa" || visatype=="Temporary graduate visa" || visatype=="Work Visa"){
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
	function countrySelection(){
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
	function abnSelection(){
		if(document.getElementById("abncheck_dtl").value=="true"){
			document.getElementById("abnCheck").checked=true;
		}
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
	
	function addnlSASelection(){
		if(document.getElementById("addnlSaCheck_dtl").value=="true"){
			document.getElementById("additionalSACheck").checked=true;
		}
		
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
	
	function gstSelection(){
		//alert(document.getElementById("gstRegistered_dtl").value);
		
		if(document.getElementById("gstRegistered").checked){
			$("#gstCert_div").show();
			$("#gstCert_lbl").show();
			$("#gstCertFile").show();
			$("#gstpath_dtl").show();
			
		}else{
			$("#gstCert_div").hide();
			$("#gstCert_lbl").hide();
			$("#gstCertFile").hide();
			$("#gstpath_dtl").hide();
			
		}
	}
	function piplSelection(){
		
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
			$("#piplpath_dtl").show();
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
			$("#piplpath_dtl").hide();
			
		}
		
	}
	function workCoverSelection(){
		
		if(document.getElementById("workCoverFlag").checked){
			$("#workCover_div").show();
			$("#workCover_lbl").show();
			$("#workCoverFile").show();
			$("#wcpath_dtl").show();
			
		}else{
			$("#workCover_div").hide();
			$("#workCover_lbl").hide();
			$("#workCoverFile").hide();
			$("#wcpath_dtl").hide();
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
	
function clickonreset(){
	$("#candidateDetails")[0].reset();
}

function bankHistoryCheck(){
	if(document.getElementById("bankHistory").checked){
		document.getElementById("bankHistory").checked=true;
	}
	if(document.getElementById("bankHistory").checked){
	$('#bh_feedback').html("");
	  event.preventDefault();
	 var contractorId= $("#contractorId").val();
	 
	 var url="/bankhistory/"+contractorId;
	 
	// alert("ID;;; "+contractorId+" "+url);
	  $.ajax({
			type : "POST",
			contentType : "application/json",		
			url : "/bankhistory/"+contractorId,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {	
				document.getElementById("bankHistory").checked=true;
				$("#bh_div").show();
				$("#bankHistorytable").show();
	       
				
				var table=   $('#bankHistorytable').DataTable( {
	       		  destroy: true,	         	 
	         	autoWidth: false,
	         	searching: false,	         	
	         	data: data,
	                
	                columns: [
	             	   { "data": 'accountName', "name" : "fullName", "title" : "Account Name"  },
	             	  { "data": 'bsb', "name" : "bsb" , "title" : "BSB"},
	             	 { "data": 'accountNumber', "name" : "accountNumber" , "title" : "Account No"},
	             	 { "data": 'additionalInfo', "name" : "additionalInfo" , "title" : "Additional Info"}]
	                   
	            } );
				
	     },
	     error: function (e) {
	     	

	         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
	             + e.responseText + "</pre>";
	         $('#bh_feedback').html(json);

	         console.log("ERROR : ", e);
	        // $("#btn-search").prop("disabled", false);

	     }
	 });
	}
	
	else{
		$("#bh_feedback").hide();
		$("#bankHistorytable").hide();		
		$("#bh_div").hide();
		document.getElementById("bankHistory").checked=false;
		
	}
}


function saHistoryCheck(){

if(document.getElementById("saHistory").checked){
	document.getElementById("saHistory").checked=true;
}
if(document.getElementById("saHistory").checked){
$('#sa_feedback').html("");
  event.preventDefault();
 var contractorId= $("#contractorId").val();
 
 
  $.ajax({
		type : "POST",
		contentType : "application/json",		
		url : "/sahistory/"+contractorId,
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {	
			document.getElementById("saHistory").checked=true;
			$("#sa_div").show();
			$("#saHistorytable").show();
     			
			var table=   $('#saHistorytable').DataTable( {
				 destroy: true,	         	 
		         	autoWidth: false,
		         	searching: false,
         	data: data,
                
                columns: [
             	   { "data": 'superAnnuationFundName', "name" : "superAnnuationFundName", "title" : "Fund Name"  },
             	  { "data": 'superAnnuationMemberId', "name" : "superAnnuationMemberId" , "title" : "Member Id"},
             	 { "data": 'additionalSuperAnnuationContributionFlag', "name" : "additionalSuperAnnuationContributionFlag" , "title" : "Additional Contribution?"},
             	 { "data": 'additionalSuperAnnuationDetails', "name" : "additionalSuperAnnuationDetails" , "title" : "Details"}]
                   
            } );
			
			
     },
     error: function (e) {
     	

         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
             + e.responseText + "</pre>";
         $('#bh_feedback').html(json);

         console.log("ERROR : ", e);
       

     }
 });
}

else{
	$("#sa_feedback").hide();
	$("#saHistorytable").hide();		
	$("#sa_div").hide();
	document.getElementById("saHistory").checked=false;
	
}

}

function abnHistoryCheck(){

	if(document.getElementById("abnHistory").checked){
		document.getElementById("abnHistory").checked=true;
	}
	if(document.getElementById("abnHistory").checked){
	$('#abn_feedback').html("");
	  event.preventDefault();
	 var contractorId= $("#contractorId").val();
	
	 
	// alert("ID;;; "+contractorId+" "+url);
	  $.ajax({
			type : "POST",
			contentType : "application/json",		
			url : "/abnhistory/"+contractorId,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {	
				document.getElementById("abnHistory").checked=true;
				$("#abn_div").show();
				$("#abnHistorytable").show();	      
				
				var table=   $('#abnHistorytable').DataTable( {
					 destroy: true,	         	 
			         	autoWidth: false,
			         	searching: false,
	         	data: data,
	                
	                columns: [
	             	   { "data": 'abnNumber', "name" : "abnNumber", "title" : "ABN No"  },
	             	  { "data": 'acnNumber', "name" : "acnNumber" , "title" : "ACN No"},
	             	  { "data": 'abnGroup', "name" : "abnGroup" , "title" : "ABN Group"},
	             	 { "data": 'companyName', "name" : "companyName" , "title" : "Comp Name"},
	             	 { "data": 'companyAddress', "name" : "companyAddress" , "title" : "Comp Address"},
	             	 { "data": 'companyCity', "name" : "companyCity" , "title" : "City"},
	             	 { "data": 'companyState', "name" : "companyState" , "title" : "State"},
	             	 { "data": 'gstRegistered', "name" : "gstRegistered" , "title" : "GST Registered?"},
	             	 { "data": 'piPlFlag', "name" : "piPlFlag" , "title" : "PI/PL?"},
	             	 { "data": 'workCoverFlag', "name" : "workCoverFlag" , "title" : "Work Cover?"}]
	                   
	            } );
		
	     },
	     error: function (e) {
	     	

	         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
	             + e.responseText + "</pre>";
	         $('#abn_feedback').html(json);

	         console.log("ERROR : ", e);
	        // $("#btn-search").prop("disabled", false);

	     }
	 });
	}

	else{
		$("#abn_feedback").hide();
		$("#abnHistorytable").hide();		
		$("#abn_div").hide();
		document.getElementById("abnHistory").checked=false;
		
	}

	}

function abnbankHistoryCheck(){
	if(document.getElementById("abnbankHistory").checked){
		document.getElementById("abnbankHistory").checked=true;
	}
	if(document.getElementById("abnbankHistory").checked){
	$('#abnbank_feedback').html("");
	  event.preventDefault();
	 var contractorId= $("#contractorId").val();
	
	  $.ajax({
			type : "POST",
			contentType : "application/json",		
			url : "/bankhistory/"+contractorId,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {	
				document.getElementById("abnbankHistory").checked=true;
				$("#ab_div").show();
				$("#abnbankHistorytable").show();
	     
				
				var table=   $('#abnbankHistorytable').DataTable( {
					 destroy: true,	         	 
			         	autoWidth: false,
			         	searching: false,
	         	data: data,
	                
	                columns: [
	             	   { "data": 'accountName', "name" : "fullName", "title" : "Account Name"  },
	             	  { "data": 'bsb', "name" : "bsb" , "title" : "BSB"},
	             	 { "data": 'accountNumber', "name" : "accountNumber" , "title" : "Account No"},
	             	 { "data": 'additionalInfo', "name" : "additionalInfo" , "title" : "Additional Info"}]
	                   
	            } );
				
				
	     },
	     error: function (e) {
	     	

	         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
	             + e.responseText + "</pre>";
	         $('#abnbank_feedback').html(json);

	         console.log("ERROR : ", e);

	     }
	 });
	}
	
	else{
		$("#abnbank_feedback").hide();
		$("#abnbankHistorytable").hide();		
		$("#ab_div").hide();
		document.getElementById("abnbankHistory").checked=false;
		
	}
}

function tfnHistoryCheck(){
	if(document.getElementById("tfnHistory").checked){
		document.getElementById("tfnHistory").checked=true;
	}
	if(document.getElementById("tfnHistory").checked){
	$('#th_feedback').html("");
	  event.preventDefault();
	 var contractorId= $("#contractorId").val();
	
	  $.ajax({
			type : "POST",
			contentType : "application/json",		
			url : "/tfnhistory/"+contractorId,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {	
				document.getElementById("tfnHistory").checked=true;
				$("#tfn_div").show();
				$("#tfnHistorytable").show();
				
				var table=   $('#tfnHistorytable').DataTable( {
					 destroy: true,	         	 
			         	autoWidth: false,
			         	searching: false,
	         	data: data,
	                
	                columns: [
	             	   { "data": 'tfnNumber', "name" : "tfnNumber", "title" : "TFN No"  },
	             	  { "data": 'newApplicationFlag', "name" : "newApplicationFlag" , "title" : "New Appln?"},
	             	 { "data": 'underAgeExemptionFlag', "name" : "underAgeExemptionFlag" , "title" : "Under Age?"},
	             	 { "data": 'pensionHolderFlag', "name" : "pensionHolderFlag" , "title" : "Pension Holder?"},
	             	 { "data": 'employmentType', "name" : "employmentType" , "title" : "Employment Type"},
	             	 { "data": 'taxPayerType', "name" : "taxPayerType" , "title" : "Tax Payer Type"},
	             	 { "data": 'taxFreeThresholdFlag', "name" : "taxFreeThresholdFlag" , "title" : "Tax Free Threshold?"},
	             	 { "data": 'loanFlag', "name" : "loanFlag" , "title" : "Has Loan?"},
	             	 { "data": 'financialShipmentDebtFlag', "name" : "financialShipmentDebtFlag" , "title" : "Financial Shipment Debt?"},
	             	 { "data": 'additionalInfo', "name" : "additionalInfo" , "title" : "Additional Info"}]
	                   
	            } );
				
	     },
	     error: function (e) {
	     	

	         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
	             + e.responseText + "</pre>";
	         $('#th_feedback').html(json);

	         console.log("ERROR : ", e);
	        // $("#btn-search").prop("disabled", false);

	     }
	 });
	}
	
	else{
		$("#th_feedback").hide();
		$("#tfnHistorytable").hide();		
		$("#tfn_div").hide();
		document.getElementById("tfnHistory").checked=false;
		
	}
}

function empHistoryCheck(){
	if(document.getElementById("empHistory").checked){
		document.getElementById("empHistory").checked=true;
	}
	if(document.getElementById("empHistory").checked){
	$('#eh_feedback').html("");
	  event.preventDefault();
	 var contractorId= $("#contractorId").val();
	
	  $.ajax({
			type : "POST",
			contentType : "application/json",		
			url : "/emphistory/"+contractorId,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {	
				document.getElementById("empHistory").checked=true;
				$("#emp_div").show();
				$("#empHistorytable").show();
	      
				
				var table=   $('#empHistorytable').DataTable( {
					 destroy: true,	         	 
			         	autoWidth: false,
			         	searching: false,
	         	data: data,
	                
	                columns: [
	             	   { "data": 'clientName', "name" : "clientName", "title" : "Client Name"  },
	             	  { "data": 'endClientName', "name" : "endClientName" , "title" : "End Client Name"},
	             	 { "data": 'contractNumber', "name" : "contractNumber" , "title" : "Contract No"},
	             	 { "data": 'workLocationAddress', "name" : "workLocationAddress" , "title" : "Work Location Address"},
	             	 { "data": 'workLocationCity', "name" : "workLocationCity" , "title" : "City"},
	             	 { "data": 'workLocationState', "name" : "workLocationState" , "title" : "State"},
	             	 { "data": 'workLocationCountry', "name" : "workLocationCountry" , "title" : "Country"},
	             	 { "data": 'jobRole', "name" : "jobRole" , "title" : "Job Role"},
	             	 { "data": 'employmentType', "name" : "employmentType" , "title" : "Employment Type"},
	             	 { "data": 'jobStartDate', "name" : "jobStartDate" , "title" : "Work Start Date"},
	             	 { "data": 'jobEndDate', "name" : "jobEndDate" , "title" : "Work End Date"},
	             	 { "data": 'lastWorkingDate', "name" : "lastWorkingDate" , "title" : "Last Working Date"},
	             	 { "data": 'finishedClient', "name" : "finishedClient" , "title" : "Finished Client?"},
	             	 { "data": 'recruiterName', "name" : "recruiterName" , "title" : "Recruiter Name"}]
	                   
	            } );
			
	     },
	     error: function (e) {
	     	

	         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
	             + e.responseText + "</pre>";
	         $('#eh_feedback').html(json);

	         console.log("ERROR : ", e);
	        // $("#btn-search").prop("disabled", false);

	     }
	 });
	}
	
	else{
		$("#eh_feedback").hide();
		$("#empHistorytable").hide();		
		$("#emp_div").hide();
		document.getElementById("empHistory").checked=false;
		
	}
}

function rateHistoryCheck(){
	
	//$("#rh").hide();
	if(document.getElementById("rateHistory").checked){
		document.getElementById("rateHistory").checked=true;
	}
	if(document.getElementById("rateHistory").checked){
	$('#rh_feedback').html("");
	  event.preventDefault();
	 var contractorId= $("#contractorId").val();
	
	  $.ajax({
			type : "POST",
			contentType : "application/json",		
			url : "/ratehistory/"+contractorId,
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success : function(data) {	
				document.getElementById("rateHistory").checked=true;
				$("#rate_div").show();
				$("#rateHistorytable").show();
	      
				
				var table=   $('#rateHistorytable').DataTable( {
	       		
	         	   destroy: true,	         	 
	         	autoWidth: false,
	         	searching: false,
	         	data: data,
	                
	                columns: [
	             	   { "data": 'ratePerDay', "name" : "ratePerDay", "title" : "Rate Per Day"  },
	             	  { "data": 'billRatePerDay', "name" : "billRatePerDay" , "title" : "Bill Rate"},
	             	 { "data": 'rateStartDate', "name" : "rateStartDate" , "title" : "Start Date"},
	             	 { "data": 'rateEndDate', "name" : "rateEndDate" , "title" : "End Date"},
	             	 { "data": 'includeSuperFlag', "name" : "includeSuperFlag" , "title" : "Super Included?"},
	             	 { "data": 'payrollTaxPaymentFlag', "name" : "payrollTaxPaymentFlag" , "title" : "Payroll Tax Paid?"},
	             	 { "data": 'workCoverFlag', "name" : "workCoverFlag" , "title" : "Work Cover?"},
	             	 { "data": 'insurancePercentage', "name" : "insurancePercentage" , "title" : "Insurance"},
	             	 { "data": 'otherDeductionPercentage', "name" : "otherDeductionPercentage" , "title" : "Other Deduction"},
	             	 { "data": 'netMargin', "name" : "netMargin" , "title" : "Net Margin"}]
	                   
	            } );
			
				
	     },
	     error: function (e) {
	     	

	         var json = "<h4>Response Error:Error occured while searching for contractors.</h4><pre>"
	             + e.responseText + "</pre>";
	         $('#rh_feedback').html(json);

	         console.log("ERROR : ", e);
	        // $("#btn-search").prop("disabled", false);

	     }
	 });
	}
	
	else{
		$("#rh_feedback").hide();
		$("#rateHistorytable").hide();		
		$("#rate_div").hide();
		document.getElementById("rateHistory").checked=false;
		
	}
}