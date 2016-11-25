<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Carre</title>
		<link href="<c:url value='/views_css/bootstrap-4.0.0-alpha.5-dist/css/bootstrap.css' />"  rel="stylesheet"></link>
</head>


<body>

${navBar}

<br><br>
<div class="container">
 <h1>Carre</h1>        
  <img src="https://www.technal.com/globalassets/upload/ligne-graphique/carres.png" alt="Carre" width="304" height="236"> 
  <br>
  <a href="<c:url value="/logout" />">Logout</a>
</div>

</body>
</html>