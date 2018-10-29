<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
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
<form id="signUpForm" action="signUp" method="post">
		<h2>FileZila SignUp Form </h2>
			<table style="with: 50%">
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstName" required/></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName" required /></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" required/></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" required /></td>
				</tr>
					</table>
			<input type="submit" value="Submit" /></form>
</body>
</html>
