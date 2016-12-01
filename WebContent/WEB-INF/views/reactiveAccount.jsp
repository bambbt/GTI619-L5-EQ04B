<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Administration Reactivation des comptes bloques</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="<c:url value='/ressources/js/toastr/build/toastr.css' />"
	rel="stylesheet" />
<script src="<c:url value="/ressources/js/addUser.js" />"></script>
<script src="<c:url value="/ressources/js/jquery-3.1.1.min.js" />"></script>
<script src="<c:url value="/ressources/js/toastr/toastr.js" />"></script>

</head>
<nav class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item active"><a href="<c:url value="/homeAdmin" />">Home</a></li>
		<li class="nav-item active"><a href="<c:url value="/cercle" />">Cercle</a></li>
		<li class="nav-item"><a href="<c:url value="/carre" />">Carre</a></li>
		<li class="nav-item"><a href="<c:url value="/administration" />">Creation des comptes</a></li>
		<li class="nav-item"><a href="<c:url value="/adminTentativeMax" />">Gestion des tentatives d'acces</a></li>
		<li class="nav-item"><a href="<c:url value="/reactiveAccount" />">Reactivation de compte</a></li>
		<li class="nav-item"><a href="<c:url value="/regexPass" />">Gestion Complexite MDP</a></li>
		<li class="nav-item"><a href="<c:url value="/adminLog" />">Log</a></li>
		<li class="nav-item"><a href="<c:url value="/changePasswd" />">Changer Mon Mot de passe</a></li>
	</ul>
</nav>
<body onload="pop()">

	<input type="hidden" id="raison" name="raison" value="${raison}"></input>
	<input type="hidden" id="error" name="error" value="${error}"></input>
	<br>
	<h4>Administration : Gestion de la politique de mot de passe</h4>
	<br>
	<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>iduser</th>
								<th>login</th>
								<th>isLocked</th>
	
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="userList">
								<tr>
									<tr id="<c:out value="${userList.iduser}"/>">
									<td><c:out value="${userList.login}" /></td>
									<td><c:out value="${userList.isLocked}" /></td>		
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
	</div>
		
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<form method="post" id="formReactiveAccount" 	action="<c:url value="/reactiveAccount" />" name="formReactiveAccount" role="form">
					<fieldset>
						<br>						
						<div class="form-group">
							<label for="login"><span class="req">* </span> Login a reactive : </label> <input
								class="form-control" type="text" name="regex" id="login"
								onkeyup="" placeholder="Inscrire le login" required />
								<br>
							<div id="errLast"></div>
						</div>
							<div class="form-group">
							<label for="password"><span class="req">* </span> Mot de
								passe provisoire : </label> <input required name="password" type="password"
								class="form-control inputpass" id="pass1" maxlength="16"
								placeholder="Saisir le mot de passe" id="pass1" required /> <label
								for="passwordAgain"><span class="req">* </span>
								Confirmation du mot de passe : </label> <input required
								name="passwordAgain" type="password"
								class="form-control inputpass" maxlength="16"
								placeholder="Saisir de nouveau le mot de passe" id="pass2"
								onkeyup="checkPass(); return false;" required /> <span
								id="confirmMessage" class="confirmMessage"></span>
						</div>

						<div class="form-group">
							<label for="paswdAdmin"><span class="req">* </span>
								(Approbation) Mot de passe Administrateur : </label> <input required
								name="paswdAdmin" type="password" class="form-control inputpass"
								id="paswdAdmin" maxlength="16"
								placeholder="Valider l'ajout avec votre mot de passe admnistrateur"
								required />
						</div>

						<div class="form-group">
							<input class="btn btn-success" type="submit" name="submit_reg"
								value="Mettre en place">
						</div>

					</fieldset>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
</div>
	<a href="<c:url value="/logout" />">Logout</a>
	<br>

	<script type="text/javascript">
function pop(){
	
	var erreur = document.getElementById("error").value
	var raison = document.getElementById("raison").value
	
	if(erreur ==""){
		toastr.info("Espace de'administration");
	}
	else if(erreur== "false"){
		toastr.success("Succes : "+raison)
	}
	else if(erreur=="true"){
		toastr.error('Erreur: '+raison);
	}
}
</script>
</body>
</html>