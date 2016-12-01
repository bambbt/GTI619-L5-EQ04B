<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-light bg-faded">
		<ul class="nav navbar-nav">
			<li class="nav-item active"><a
				href="<c:url value="/homeAdmin" />">Home</a></li>
			<li class="nav-item active"><a href="<c:url value="/cercle" />">Cercle</a></li>
			<li class="nav-item"><a href="<c:url value="/carre" />">Carre</a></li>
			<li class="nav-item"><a href="<c:url value="/administration" />">Creation
					des comptes</a></li>
			<li class="nav-item"><a
				href="<c:url value="/adminTentativeMax" />">Gestion des
					tentatives d'acces</a></li>
			<li class="nav-item"><a
				href="<c:url value="/reactiveAccount" />">Reactivation de compte</a></li>
			<li class="nav-item"><a href="<c:url value="/regexPass" />">Gestion
					Complexite MDP</a></li>
			<li class="nav-item"><a href="<c:url value="/adminLog" />">Log</a></li>
			<li class="nav-item"><a href="<c:url value="/changePasswd" />">Changer
					Mon Mot de passe</a></li>
		</ul>
	</nav>


	<h2>Consultation des LOG</h2>
	<br>
	<br>
	<p class="bg-primary">Affichage des logs de connexion</p>
	<textarea name="consoleC" id="consoleC" class="form-control" rows="10">${log_connexion}</textarea>
	<br>
	<p class="bg-danger">Affichage des logs de sécurité</p>
	<textarea name="consoleS" id="consoleS" class="form-control" rows="10"
		>${log_securite}</textarea>
	<br>
	<a href="<c:url value="/logout" />">Logout</a>
	<script type="text/javascript">
		//document.getElementById("consoleC").value = "";
		//document.getElementById("consoleS").value = "Console Sécurité";
	</script>
</body>
</html>