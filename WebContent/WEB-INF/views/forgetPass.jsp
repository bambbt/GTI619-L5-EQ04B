<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Reinitialisation MDP1</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="<c:url value="/ressources/js/setNewPass.js" />"></script>
		<link href="<c:url value='/ressources/js/toastr/build/toastr.css' />" rel="stylesheet" />
		<script src="<c:url value="/ressources/js/jquery-3.1.1.min.js" />"></script>
		<script src="<c:url value="/ressources/js/toastr/toastr.js" />"></script>
	</head>

<body onload="popInfo1()">
	
<div class="container">
  <div class="jumbotron">
    <h1>Retrouver un mot de passe</h1> 
    <p>Veuillez saisir votre nom d'utilisateur </p>
	
	<form method="post" id="fileForm"	action="<c:url value="/forgetPass" />" name="newPassformValideUser" role="form" class="form-horizontal">
	
	<span class="req">Reinitialisation de mot de passe</span>
			<br>
		<c:if test="${error}">
			<div class="alert alert-danger">
					<p>${raison}</p>
			</div>
		</c:if>
		<br>
		<br>
		<div class="form-group row">
		  <label for="example-text-input" class="col-xs-2 col-form-label">Nom d'utilisateur</label>
		  <div class="col-xs-10">
		   <input type="text" class="form-control" id="uid" name="uid" placeholder="nom d'utilisateur" value="${user}" required>
		  </div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
		
		<div class="form-actions">
			<input type="submit" class="btn btn-info btn-block login" value="Récupérer ">
		</div>
	</form>

 </div>
 </div>
<button type="button" onClick="document.location.href = document.referrer" class="btn btn-primary">Retour</button>
</body>
</html>