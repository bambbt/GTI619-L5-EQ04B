<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Looser</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<c:url value='/ressources/css/login.css' />"  rel="stylesheet"></link>
		
		<link href="<c:url value='/ressources/js/toastr/build/toastr.css' />" rel="stylesheet" />
		<script src="<c:url value="/ressources/js/setNewPass.js" />"></script>
		<script src="<c:url value="/ressources/js/jquery-3.1.1.min.js" />"></script>
		<script src="<c:url value="/ressources/js/toastr/toastr.js" />"></script>
	</head>

<body onload="popInfo1()">
	
<div class="container">
	<div class="login-container">
              <div class="form-box">
              
              <form method="post" id="fileForm"
					action="<c:url value="/forgetPass" />" name="newPassformValideUser" role="form" class="form-horizontal">
						
							<span class="req">Reinitialisation de mot de passe</span>
							<c:if test="${error}">
								<div class="alert alert-danger">
									<p>${raison}</p>
								</div>
							</c:if>
							<br>
							<br>
							
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="uid" name="uid" 
								placeholder="nom d'utilisateur" value="${user}" required>
							</div>
							
								<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />						
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