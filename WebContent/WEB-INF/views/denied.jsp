<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Acces Denied</title>
		<link href="<c:url value='/views_css/bootstrap-4.0.0-alpha.5-dist/css/bootstrap.css' />"  rel="stylesheet"></link>
</head>
<body>

<div class="alert alert-danger" role="alert">
<h1><strong>Desol�, ${user}!</strong></h1> <br>
   <a href="#" class="alert-link"> Vous n'avez pas acc�s � cette page </a><br>
   <strong>:-(</strong>
</div>

	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>