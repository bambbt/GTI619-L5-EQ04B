<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="/ressources/toastr/build/toastr.css" rel="stylesheet" />
<html>
<head>
<title>Administration</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value='/ressources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<script src="<c:url value="/ressources/js/addUser.js" />"></script>
</head>
<nav class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item active"><a
			href="<c:url value="/homeAdmin" />">Home</a></li>
		<li class="nav-item active"><a href="<c:url value="/cercle" />">Cercle</a>
		</li>
		<li class="nav-item"><a href="<c:url value="/carre" />">Carre</a>
		</li>
		<li class="nav-item"><a href="<c:url value="/administration" />">Administration</a>
		</li>
		 <li class="nav-item">
      <a href="<c:url value="/changePasswd" />">Mon Compte</a>
    </li>
	</ul>
</nav>
<body>
      
<h4>Administration : Formulaire d'ajout un utilisateur </h4>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<form method="post" id="fileForm"
					action="<c:url value="/adduser" />" name="formAddUser" role="form">
					<fieldset>
						<legend class="text-center">
							<span class="req"><small>
									( * = Obligatoire )</small></span>
						</legend>
						<div class="form-group">
							<label class="form-check-inline"> <input
								class="form-check-input" type="radio" name="role" id="roleCarre"
								value="roleCarre"> ROLE CARRE
							</label> <label class="form-check-inline"> <input
								class="form-check-input" type="radio" name="role"
								id="roleCercle" value="roleCercle"> ROLE CERCLE
							</label> <label class="form-check-inline"> <input
								class="form-check-input" type="radio" name="role" id="roleAdmin"
								value="roleAdmin"> ROLE AMNISTRATEUR
							</label>
							<div>
								<span id="userBoxMessage" class="confirmMessage"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="username"><span class="req">* </span> 
								Login : </label> <input class="form-control" type="text"
								name="login" id="txt" onkeyup="login(this)" placeholder="Inscrire le login"
								required />
							<div id="errLast"></div>
						</div>
						<div class="form-group">
							<label for="mail"><span class="email">* </span> 
								Email </label> <input class="form-control" type="email"
								name="mail" id="mail"  placeholder="element@element.com"
								required />
							<span id="confirmMail" class="confirmMessage"></span>
						</div>
						
											 
						<div class="form-group">
							<label for="firstname"><span class="req">* </span> Nom & Prenom :
							</label> <input class="form-control" type="text" name="completeName"
								id="txt" onkeyup="Validate(this)" placeholder="NomPrenom" />
							<div id="errFirst"></div>
						</div>

						<div class="form-group">
							<label for="password"><span class="req">* </span> Mot de
								passe : </label> <input required name="password" type="password"
								class="form-control inputpass" minlength="6" path="pass1"
								maxlength="16" placeholder="Saisir le mot de passe" id="pass1"
								required />
							</p>

							<label for="password"><span class="req">* </span>
								Confirmation du mot de passe : </label> <input required name="password"
								type="password" class="form-control inputpass" minlength="6"
								maxlength="16" placeholder="Saisir de nouveau le mot de passe"
								id="pass2" onkeyup="checkPass(); return false;" required /> <span
								id="confirmMessage" class="confirmMessage"></span>
						</div>

						<div class="form-group">
							<label for="password"><span class="req">* </span>
								(Approbation) Mot de passe Administrateur : </label> <input required
								name="paswdAdmin" type="password" class="form-control inputpass"
								minlength="6	" path="paswdAdmin" maxlength="16"
								placeholder="Valider l'ajout avec votre mot de passe admnistrateur"
								id="paswdAdmin" required />
							</p>
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
	<div id="snackbar"></div>

	<a href="<c:url value="/logout" />">Logout</a>
	<br>
	<br>
	
<script type="text/javascript">
function pop(txt) {
    var x = document.getElementById("snackbar")
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", txt); }, 3000);
}
</script> 
</body>
</html>