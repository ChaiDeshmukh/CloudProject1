<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
     content="638390695926-n4rtq480tf20fb7b0gkr91lo70fo8vdk.apps.googleusercontent.com">
</head>
<body>
<h1> Welcome ${loggedUser.firstName}  ${loggedUser.lastName} </h1>                                                     <a href="/login"><h5>Back to login Page</h5></a>
<a href="#" onclick="signOut();">Sign out</a>
<script>
  function signOut() {
	  alert("_@)(#(#*#*#*#))");
	  window.location = "https://mail.google.com/mail/u/0/?logout&hl=en";
	 
	 //window.location.href="${pageContext.request.contextPath}/logout";
    //document.location.href = "https://www.google.com/accounts/Logout?continue=http://localhost:8080/logout";
  }
</script>


	<form method="POST" action="/uploadSampleFile" enctype="multipart/form-data">
		<input type="file" name="file" /><br />
		<br /> Description <input type="text" name="description" /><br />
		<br /> <input type="submit" value="Submit" /><br /><br />
	</form>

<table>
  <tr>
    <th>File Name</th>
    <th>Description</th>
    <th>File Size</th>
	<th>Created Date</th>
	<th>Updated Date</th>
	<th>Uploaded By</th>
	<th>Download</th>
	<th>Action</th>
  </tr>
  <c:forEach items="${fileDetails}" var="item">
  <tr>
    <td>${item.fileName} </td>
      <td>${item.fileDescription} </td>
      <td>${item.fileSize} </td>
      <td>${item.createdTime} </td>
    <td>${item.updatedTime} </td>
     <td>${item.email} </td>
      <td>
      
      <a href="${pageContext.request.contextPath}/downloadFile?fileName=${item.fileName}">
      <img src="${pageContext.request.contextPath}/api/downimage" height="30px;"/>
       </a>
      </td>
       <td>
       <a href="${pageContext.request.contextPath}/deleteFile?fileName=${item.fileName}&fileid=${item.fileID}">
       <img src="${pageContext.request.contextPath}/api/dimage" height="30px;"/>
       </a>
       <img src="${pageContext.request.contextPath}/api/eimage" height="30px;"/>
       </td>
  </tr>
 </c:forEach>
</table>

</form>	
</body>
</html>