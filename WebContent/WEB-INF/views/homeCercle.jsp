<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home Cercle</title>
		<link href="<c:url value='/views_css/bootstrap-4.0.0-alpha.5-dist/css/bootstrap.css' />"  rel="stylesheet"></link>
</head>
<nav class="navbar navbar-light bg-faded">
  <a class="navbar-brand" href="">Bonjour <strong>${user}</strong></a>
  
  <ul class="nav navbar-nav">
  <li class="nav-item active">
      <a href="<c:url value="/homeCercle" />">Home</a>
    </li>
    <li class="nav-item active">
      <a href="<c:url value="/cercle" />">Cercle</a>
    </li>
  </ul>
</nav>	

<body>

	<strong>${user}</strong>,Welcome to home Cercle.
	<a href="<c:url value="/logout" />">Logout</a>
	
</body>
</html>