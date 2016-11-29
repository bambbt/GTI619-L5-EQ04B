<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cercle</title>
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
      <a href="<c:url value="/adminLog" />">Log</a>
    </li>
      <li class="nav-item">
      <a href="<c:url value="/changePasswd" />">Mon Compte</a>
    </li>
  </ul>
</nav>

<body>	
<br><br>
<div class="container">	
 <h1>Cercle</h1>                  
  <img src="http://preprod-img.medisite.fr/files/images/article/3/4/4/1051443/2325437-inline.png"
   class="img-circle" alt="Cercle" width="304" height="236"></img>
   <br>
   <br>
  <a href="<c:url value="/logout" />">Logout</a>
</div>
	
</body>
</html>