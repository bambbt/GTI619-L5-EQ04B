<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home Carre</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script></head>
<nav class="navbar navbar-light bg-faded">
  <ul class="nav navbar-nav">
  <li class="nav-item active">
      <a href="<c:url value="/homeCarre" />">Home</a>
    </li>
    <li class="nav-item active">
      <a href="<c:url value="/carre" />">Carre</a>
    </li>
     <li class="nav-item">
      <a href="<c:url value="/changePasswd" />">Changer Mon Mot de passe</a>
    </li>
    <li class="nav-item"><a href="<c:url value="/logout" />">Logout</a></li>
  </ul>
</nav>	

<body>
<br>
	<br>
	<h1>Bonjour <strong> ${user}</strong></h1>
	<br>
	<br>
	
	<h2>Bienvenue dans votre Carre de travail</h2>
	
	<br>
	<br>
	
	
</body>
</html>