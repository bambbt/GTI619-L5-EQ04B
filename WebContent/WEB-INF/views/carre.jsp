<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Carre</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
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
  </ul>
</nav>	

<body>
<br><br>
<div class="container">
 <h1>Carre</h1>        
  <img src="https://www.technal.com/globalassets/upload/ligne-graphique/carres.png" alt="Carre" width="304" height="236"></img> 
   <br>
   <br>
  <a href="<c:url value="/logout" />">Logout</a>
</div>

</body>
</html>