<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
     content="638390695926-n4rtq480tf20fb7b0gkr91lo70fo8vdk.apps.googleusercontent.com">
<style>
form {
  margin:200 auto;
  width:300px
}
input {
  margin-bottom:3px;
  padding:10px;
  width: 100%;
  border:1px solid #CCC
}
button {
  padding:10px
}
label {
  cursor:pointer
}
#form-switch {
  display:none
}
#register-form {
  display:none
}
#form-switch:checked~#register-form {
  display:block
}
#form-switch:checked~#login-form {
  display:none
}

</style>
</head>
<body>
<form id="login-form" action="/validateUser" method="post">  
<h1>Welcome to Filezila!</h1>
<h2>Login Form</h2>
Email:<br/><input type="text" name="email" required/><br/><br/>  
Password:<br/><input type="password" name="password" required/><br/><br/>  
<input type="submit" value="login"/>  <br/><br/> 

<a href="/signUp"><h4>New User? Register here</h4></a>


<br/><br/>
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

</form>


</body>
</html>