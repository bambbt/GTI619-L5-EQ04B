<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Connexion</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

<body>
<div class="container">
  <div class="jumbotron">
    <h1>Bienvenue sur votre application GTI619</h1> 
    <p>Veuillez saisir vos informations de connexion </p>
    <c:if test="${param.error != null}">
			<div class="alert alert-danger"><p>Erreur : Utilisateur ou mot de passe invalide.</p></div>
	</c:if> 
  </div>
<form method="post" id="loginUrl" 	action="<c:url value="/login" />" role="form">
		<div class="form-group row">
		  <label for="example-text-input" class="col-xs-2 col-form-label">Nom d'utilisateur</label>
		  <div class="col-xs-10">
		   <input type="text" class="form-control" id="username" name="ssoId" placeholder="nom d'utilisateur" required>
		  </div>
		</div>
		<div class="form-group row">
		  <label for="example-password-input" class="col-xs-2 col-form-label">Mot de passe</label>
		  <div class="col-xs-10">
		    <input type="password" class="form-control" id="password" name="password" placeholder="mot de passe" required>
		  </div>
		</div>
		<div class="form-actions">
			<input type="submit" class="btn btn-info btn-block login" value="Connexion">
		</div>
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
</form>
<a href="<c:url value="/forgetPass" />">Mot de passe oublié ?</a>
</div>
	<c:if test="${param.logout != null}">
			<div class="alert alert-success"><p>Deconnexion avec succès.</p></div>
	</c:if>
</body>
</html>