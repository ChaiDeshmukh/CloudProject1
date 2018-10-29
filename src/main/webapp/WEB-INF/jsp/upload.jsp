<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<body>

	<h1>Spring Boot - File Upload</h1>

	<form method="POST" action="/uploadSampleFile" enctype="multipart/form-data">
		<input type="file" name="file" /><br />
		<br /> <input type="submit" value="Submit" />
	</form>

</body>
</html>
