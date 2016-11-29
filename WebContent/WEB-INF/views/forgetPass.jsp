<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Looser</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value='/ressources/css/login.css' />"  rel="stylesheet"></link>
	</head>

	<body>
	
<div class="container">
	<div class="login-container">
              <div class="form-box">
            
               <c:url var="logForgPass" value="/login" />
               
						<form action="${logForgPass}" method="post" class="form-horizontal">
							<c:if test="${error = false}">
								<div class="alert alert-danger">
									<p>Erreur : Problème avec l'utilisateur renseigné.</p>
								</div>
							</c:if>
							
							
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="ssoId" placeholder="nom d'utilisateur" required>
							</div>
							
														
							<div class="form-actions">
								<input type="submit"
									class="btn btn-info btn-block login" value="Récupérer ">
							</div>
							
						</form>
						
            </div>
        	</div>
			</div>
			<input type="button" onClick="document.location.href = document.referrer" value="Retour"  />
	</body>
</html>