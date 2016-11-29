<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Connexion</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value='/ressources/css/login.css' />"  rel="stylesheet"></link>
	</head>

	<body>
	
<div class="container">
	<div class="login-container">
            <div id="output"></div>
            <div class="avatar" ></div>
            <div class="form-box">
            
               <c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Erreur : Utilisateur ou mot de passe invalide.</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>Deconnexion avec succès.</p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="ssoId" placeholder="nom d'utilisateur" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
								<input type="password" class="form-control" id="password" name="password" placeholder="mot de passe" required>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								
							<div class="form-actions">
								<input type="submit"
									class="btn btn-info btn-block login" value="Connexion">
							</div>
						</form>
						<a href="<c:url value="/forgetPass" />">Mot de passe oublié ?</a>
            </div>
        	</div>
			</div>
	</body>
</html>