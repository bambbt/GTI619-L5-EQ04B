<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Passwd</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="<c:url value='/ressources/js/toastr/build/toastr.css' />"
	rel="stylesheet" />
<script src="<c:url value="/ressources/js/addUser.js" />"></script>
<script src="<c:url value="/ressources/js/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/ressources/js/toastr/toastr.js" />"></script>


</head>
<nav class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a href="<c:url value="/myHome" />">Home</a></li>
		<li class="nav-item"><a href="<c:url value="/logout" />">Logout</a></li>
	</ul>
</nav>



<body onload="pop()">

	<input type="hidden" id="raison" name="raison" value="${raison}"></input>
	<input type="hidden" id="error" name="error" value="${error}"></input>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<form method="post" id="fileForm"
					action="<c:url value="/changePasswd" />">
					<fieldset>
						<legend class="text-center">
							Modifier votre mot de passe <span class="req"><small>
									( * = Obligatoire )</small></span>
						</legend>

						<label for="username"><span class="req"></span> Nom
							d'utilisateur : <strong>${username}</strong></label>

						<div class="form-group">
							<label for="password"><span class="req">* </span> Ancien
								mot de passe : </label> <input required name="oldPass" type="password"
								class="form-control inputpass" id="oldPass" maxlength="16"
								placeholder="Inscrire l'ancien mot de passe" id="oldPass"
								required />
						</div>


						<div class="form-group">
							<label for="password"><span class="req">* </span> Nouveau
								mot de passe : </label> <input required name="password" type="password"
								class="form-control inputpass" id="pass1" maxlength="16"
								placeholder="Saisir le mot de passe" id="pass1" required /> <label
								for="password"><span class="req">* </span> Confirmation
								du nouveau mot de passe : </label> <input required name="passwordAgain"
								type="password" class="form-control inputpass" maxlength="16"
								placeholder="Saisir de nouveau le mot de passe" id="pass2"
								onkeyup="checkPass(); return false;" required /> <span
								id="confirmMessage" class="confirmMessage"></span>
						</div>
						<div class="form-group">
							<input class="btn btn-success" type="submit" name="submit_reg"
								value="Ajouter">
						</div>

					</fieldset>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
	

	<script type="text/javascript">
		function pop() {

			var erreur = document.getElementById("error").value
			var raison = document.getElementById("raison").value

			if (erreur == "") {
				toastr.info("Espace de'administration");
			} else if (erreur == "false") {
				toastr.success("Succes : " + raison)
			} else if (erreur == "true") {
				toastr.error('Erreur: ' + raison);
			}
		}
	</script>
</body>
</html>