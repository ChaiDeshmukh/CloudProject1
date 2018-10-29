<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
     content="638390695926-n4rtq480tf20fb7b0gkr91lo70fo8vdk.apps.googleusercontent.com">

<title>Servlet OAuth example</title>
</head>
<body>
	<h2>Servlet OAuth example</h2>
	<br>
	<div class="g-signin2" data-onsuccess="onSignIn"></div>

	<script>
	function onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
      console.log('ID: ' + profile.getId());
      console.log('Name: ' + profile.getName());
      console.log('Image URL: ' + profile.getImageUrl());
      console.log('Email: ' + profile.getEmail());
      console.log('id_token: ' + googleUser.getAuthResponse().id_token);

     //do not post above info to the server because that is not safe.
     //just send the id_token

      var redirectUrl = 'clogin';
      //using jquery to post data dynamically
      var form = $('<form action="' + redirectUrl + '" method="post">' +
                          '<input type="text" name="id_token" value="' +
                           googleUser.getAuthResponse().id_token + '" />' +
                                                                '</form>');
    $('body').append(form);
    form.submit();
    }

	</script>
</body>
</html>