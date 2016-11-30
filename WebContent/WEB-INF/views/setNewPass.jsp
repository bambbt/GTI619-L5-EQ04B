<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Nouveau MDP</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value='/ressources/css/login.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/ressources/js/toastr/build/toastr.css' />" rel="stylesheet" />
		<script src="<c:url value="/ressources/js/setNewPass.js" />"></script>
		<script src="<c:url value="/ressources/js/jquery-3.1.1.min.js" />"></script>
		<script src="<c:url value="/ressources/js/toastr/toastr.js" />"></script>
	</head>

<body onload="popInfo2()">
<input type="hidden" id="exp" name="explain" value="${notif}"></input>
<div class="container">
	<div class="login-container">
              <div class="form-box">
          <form method="post" id="fileForm"
					action="<c:url value="/setNewPass" />" name="newPassformValideUser" role="form" class="form-horizontal">
							<span class="req">Mettez à jour votre mot de passe</span>
							<c:if test="${error}">
								<div class="alert alert-danger">
									<p>Erreur : "${raison}"</p>
								</div>
							</c:if>
							<br>
							<br>
							
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="uid" 
								placeholder="nom d'utilisateur" value=${user} disabled  required>
							</div>
							<br>
							<div class="input-group input-sm">
							<label for="password"><span class="req">* </span> PIN : </label>
							<br> 
								<input required name="recovery" type="text"
								class="form-control inputpass" id="recovery" maxlength="16"
								placeholder="Saisir le PIN reçu par mail" id="pass1" required /> 
							</div>
							<br>
							<div class="input-group input-sm">
							<label for="password"><span class="req">* </span> Nouveau mot de passe : </label> 
								<br>
								<input required name="password" type="password"
								class="form-control inputpass" id="pass1" maxlength="16"
								placeholder="Saisir nouveau mot de passe" id="pass1" required /> 
								<br>
								<input 	name="passwordAgain" type="password"
								class="form-control inputpass" maxlength="16"
								placeholder="Confirmer mot de passe" id="pass2"
								onkeyup="validationMDP()" required /> <span
								id="confirmMessage" class="confirmMessage"></span>
						</div>
							<br>
							<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />				
							<div class="form-actions">
								<input type="submit" class="btn btn-info btn-block login" value="Confirmer ">
							</div>
								
						</form>
            </div>
        	</div>
			</div>
			<input type="button" onClick="document.location.href = document.referrer" value="Retour"  />
	</body>
</html>