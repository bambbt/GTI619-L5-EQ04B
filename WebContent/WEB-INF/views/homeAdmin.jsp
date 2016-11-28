<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home Admin</title>
		<link href="<c:url value='/ressources/css/bootstrap.css' />"  rel="stylesheet"></link>
</head>
<nav class="navbar navbar-light bg-faded">
  <ul class="nav navbar-nav">
  <li class="nav-item active">
      <a href="<c:url value="/homeAdmin" />">Home</a>
    </li>
    <li class="nav-item active">
      <a href="<c:url value="/cercle" />">Cercle</a>
    </li>
    <li class="nav-item">
 		<a href="<c:url value="/carre" />">Carre</a>
    </li>
    <li class="nav-item">
      <a href="<c:url value="/administration" />">Administration</a>
    </li>
      <li class="nav-item">
      <a href="<c:url value="/changePasswd" />">Mon Compte</a>
    </li>
  </ul>
</nav>		

<body>
<br>
	<br>
	<h1>Bonjour <strong> ${user}</strong></h1>
	<br>
	<br>
	
	<h2>Bienvenue dans votre espace Admin de travail</h2>
	
	<br>
	<br>
	<a href="<c:url value="/logout" />">Logout</a>
	
</body>
</html>