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
		$("#pipl_div").show();
		$("#pipl_lbl").show();
		$("#piplcertificate").show();
	}else{
		$("#pipl_div").hide();
		$("#pipl_lbl").hide();
		$("#piplcertificate").hide();
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