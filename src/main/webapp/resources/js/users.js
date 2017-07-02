function createUser() {
	var regUsername = $("#reg-username")[0].value;
	var regPassword = $("#reg-password")[0].value;
	
	var regData = {
		username: regUsername,
		password: regPassword
	}
	console.log(regData);
	$.ajax({
	    url: 'http://localhost:9001/vveb/api/v1/members',
	    type: 'POST',
	    contentType: 'application/json',
	    data: JSON.stringify(regData),
	    success: function() {
	    	alert('Successfully created user');
	    	window.location.replace('contents.html');
	    }
	  });
}
