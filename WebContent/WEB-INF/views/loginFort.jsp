<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Connexion</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>${user},Vous etes un Administrateur!</h1>
			<p>Veuillez proceder à l'authentification forte pour acceder à
				vos services</p>
			<p>Il vous faut utiliser votre grid card!</p>
		</div>

		<form method="post" id="loginFort"
			action="<c:url value="/loginFort" />" role="form">
			<div class="form-group row">
				<label for="example-text-input" class="col-xs-2 col-form-label">Cellule
					: ${defi1}</label>
				<div class="col-xs-10">
					<input type="number" class="form-control" id="valueCell1"
						name="valueCell1"
						placeholder="Inscrire la valeur de la cellule demandée" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="example-text-input" class="col-xs-2 col-form-label">Cellule
					: ${defi2}</label>
				<div class="col-xs-10">
					<input type="number" class="form-control" id="valueCell2"
						name="valueCell2"
						placeholder="Inscrire la valeur de la cellule demandée" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="example-text-input" class="col-xs-2 col-form-label">Cellule
					: ${defi3}</label>
				<div class="col-xs-10">
					<input type="number" class="form-control" id="valueCell3"
						name="valueCell3"
						placeholder="Inscrire la valeur de la cellule demandée" required>
				</div>
			</div>
			<div class="form-group row">
				<label for="example-text-input" class="col-xs-2 col-form-label">Cellule
					: ${defi4}</label>
				<div class="col-xs-10">
					<input type="number" class="form-control" id="valueCell4"
						name="valueCell4"
						placeholder="Inscrire la valeur de la cellule demandée" required>
				</div>
			</div>

			<div class="form-actions">
				<input type="submit" class="btn btn-info btn-block login"
					value="Valider son Identité">
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<a href="<c:url value="/logout" />">Sortir ?</a>
	</div>
</html>