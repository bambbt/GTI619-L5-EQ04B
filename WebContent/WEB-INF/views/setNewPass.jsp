<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Reinitialisation MDP2</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<link href="<c:url value='/ressources/js/toastr/build/toastr.css' />"rel="stylesheet" />
		<script src="<c:url value="/ressources/js/setNewPass.js" />"></script>
		<script src="<c:url value="/ressources/js/jquery-3.1.1.min.js" />"></script>
		<script src="<c:url value="/ressources/js/toastr/toastr.js" />"></script>
</head>

<body onload="popInfo2()">
	<input type="hidden" id="exp" name="explain" value="${notif}"></input>
	
	<div class="container">
  <div class="jumbotron">
    <h1>Mettez à jour votre mot de passe</h1> 
    <p>Veuillez les informations requises pour accéder de nouveau à votre compte utilisateur </p>
	
	<form method="post" id="fileForm"	action="<c:url value="/setNewPass" />" name="formSewSetNewPass" role="form" class="form-horizontal">
	
	<span class="req">Reinitialisation du mot de passe</span>
			<br>
	<c:if test="${error}">
		<div class="alert alert-danger">
			<p>Erreur : ${raison}</p>
		</div>
	</c:if>
	<br><br>
	
		<div class="form-group row">
		  <label for="example-text-input" class="col-xs-2 col-form-label">Nom d'utilisateur</label>
		  <div class="col-xs-10">
		   		<input type="text" class="form-control" id="username" name="uid" placeholder="nom d'utilisateur" value="${user}" required />
		  </div>
		</div>
		
		<div class="form-group row">
		  <label for="example-text-input" class="col-xs-2 col-form-label">PIN utilisateur</label>
		  <div class="col-xs-10">
		  		<input type="number" class="form-control" id="recovery" name="recovery" step="1" min="0" max="9999999999" placeholder="Saisir le PIN reçu par mail" required /> 
		  </div>
		</div>
		
		<div class="form-group row">
		  <label for="example-password-input" class="col-xs-2 col-form-label"> Mot de passe</label>
		  <div class="col-xs-10">
		    <input type="password" class="form-control" onkeyup="ValiderMDPphase1();" id="pass1" name="password" maxlength="16" placeholder="Saisir le nouveau mot de passe" required>
		    <input type="password" onkeyup="ValiderMDPphase2();" class="form-control" id="pass2" name="passwordAgain" maxlength="16" placeholder="Confirmer le mot de passe" required>
		  <span id="confirmMessage" class="confirmMessage"></span>
		  </div>
		</div>
		<br><br>		
		<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
		
		<div class="form-actions">
			<input type="submit" class="btn btn-info btn-block login" value="Initialiser mot de passe ">
		</div>
	</form>

 </div>
 </div>
<button type="button" onClick="document.location.href = document.referrer" class="btn btn-primary">Retour</button>
</body>
</html>