<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cercle</title>
		<link href="<c:url value='/views_css/bootstrap-4.0.0-alpha.5-dist/css/bootstrap.css' />"  rel="stylesheet"></link>
</head>


<body>
	
<br><br>
<div class="container">	
 <h1>Cercle</h1>                  
  <img src="http://preprod-img.medisite.fr/files/images/article/3/4/4/1051443/2325437-inline.png"
   class="img-circle" alt="Cercle" width="304" height="236"> 
  <a href="<c:url value="/logout" />">Logout</a>
</div>
	
</body>
</html>