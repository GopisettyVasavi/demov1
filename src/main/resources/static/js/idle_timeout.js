var inactivityTimeOut = 10 * 180000; //30 minutes
inactivitySessionExpireTimeOut = '';

setSessionExpireTimeOut();

function setSessionExpireTimeOut () {
'use strict';
clearSessionExpireTimeout();
inactivitySessionExpireTimeOut = setTimeout(function() {
	expireSessionIdleTime();
}, inactivityTimeOut);
}

function expireSessionIdleTime () {
'use strict';

console.log('user inactive for ' + inactivityTimeOut + " seconds");
console.log('session expired');
alert('Session has expired. Please login again.');
clearSessionExpireTimeout();
//document.location.href = "login.html";
window.location='./'

}

function clearSessionExpireTimeout () {
    'use strict';

    clearTimeout(inactivitySessionExpireTimeOut);
}

function backToIndex() {
	window.location = './index'
}
/*$(window).on('beforeunload', function(){
    return false;
});*/
function relogin(){
	window.location='./'
	
}
function cleardata() {
	//alert("Logout invoked");

	$
			.ajax({
				type : "GET",
				contentType : "application/json",
				url : "./logoutuser",
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

