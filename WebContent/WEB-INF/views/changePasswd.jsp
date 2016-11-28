<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Passwd</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/ressources/css/bootstrap.css' />"  rel="stylesheet"></link>

</head>
${navBar}
<body>
<div class="container">
	<div class="row">
        <div class="col-md-6">
            <form method="post" id="fileForm" action="<c:url value="/changePasswd" />" >
            <fieldset><legend class="text-center">Modifier votre mot de passe <span class="req"><small> ( * = Obligatoire )</small></span></legend>
 			
                <label for="username"><span class="req"></span> Nom d'utilisateur : <string>${username}</strong></label> 
            
               <div class="form-group">
                <label for="password"><span class="req">* </span> Ancien mot de passe : </label>
                    <input required name="oldPass" type="oldPass" class="form-control inputpass" minlength="4" path="oldPass" maxlength="16" placeholder="Inscrire l'ancien mot de passe" id="oldPass" required /> </p>
            	</div>

                      
            <div class="form-group">
                <label for="password"><span class="req">* </span> Nouveau mot de passe : </label>
                    <input required name="password" type="password" class="form-control inputpass" minlength="4" path="pass1" maxlength="16" placeholder="Saisir le mot de passe" id="pass1" required /> </p>

                <label for="password"><span class="req">* </span> Confirmation du nouveau mot de passe : </label>
                    <input required name="password" type="password" class="form-control inputpass" minlength="4" maxlength="16" placeholder="Saisir de nouveau le mot de passe"  id="pass2" 
                    onkeyup="checkPass(); return false;" required />
                        <span id="confirmMessage" class="confirmMessage"></span>
            </div>
            <div class="form-group">
                <input class="btn btn-success"  type="submit" name="submit_reg" value="Ajouter">
            </div>
			
            </fieldset>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
		</div>
	</div>
</div>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>