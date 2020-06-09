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

