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
              
              <form method="post" id="fileForm"
					action="<c:url value="/forgetPass" />" name="newPassformValideUser" role="form" class="form-horizontal">
						
							<span class="req">Recovery</span>
							<c:if test="${error}">
								<div class="alert alert-danger">
									<p>${raison}</p>
								</div>
							</c:if>
							<br>
							<br>
							
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
								<input type="text" class="form-control" id="username" name="ssoId" 
								placeholder="nom d'utilisateur" value="${user}" required>
							</div>
								<input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
														
							<div class="form-actions">
								<input type="submit"
									class="btn btn-info btn-block login" value="R�cup�rer ">
							</div>
							
						</form>
						
            </div>
        	</div>
			</div>
			<input type="button" onClick="document.location.href = document.referrer" value="Retour"  />
	</body>
</html>