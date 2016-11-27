<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Administration</title>
		<link href="<c:url value='/ressources/css/bootstrap.css' />"  rel="stylesheet"></link>
		 <script src="<c:url value="/ressources/js/addUser.js" />"></script>
</head>
<nav class="navbar navbar-light bg-faded">
  <ul class="nav navbar-nav">
  <li class="nav-item active">
      <a href="<c:url value="/homeAdmin" />">Home</a>
    </li>
    <li class="nav-item active">
      <a href="<c:url value="/cercle" />">Cercle</a>
    </li>
    <li class="nav-item">
 		<a href="<c:url value="/carre" />">Carre</a>
    </li>
    <li class="nav-item">
      <a href="<c:url value="/administration" />">Administration</a>
    </li>
  </ul>
</nav>	

<body>
<h2>Administration</h2>
<div class="container">
	<div class="row">
        <div class="col-md-6">
            <form action="" method="post" id="fileForm" role="form">
            <fieldset><legend class="text-center">Formulaire d'ajout un utilisateur <span class="req"><small> ( * = Obligatoire )</small></span></legend>
 			<div class="form-group">
	          	<h4>Rôle utilisateur</h4>
				<label class="form-check-inline">
				  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="roleCarre" value="option1"> ROLE CARRE
				</label>
				<label class="form-check-inline">
				  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="roleCercle" value="option2"> ROLE CERCLE
				</label>
				<label class="form-check-inline">
				  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="roleAdmin" value="option3"> ROLE AMNISTRATEUR
				</label>
  			</div>
             <div class="form-group">
                <label for="username"><span class="req">* </span> User name:  <small>This will be your login user name</small> </label> 
                    <input class="form-control" type="text" name="username" id = "txt" onkeyup = "Validate(this)" placeholder="minimum 6 letters" required />  
                        <div id="errLast"></div>
            </div>
            
            <div class="form-group"> 	 
                <label for="firstname"><span class="req">* </span> First name: </label>
                    <input class="form-control" type="text" name="firstname" id = "txt" onkeyup = "Validate(this)" required /> 
                        <div id="errFirst"></div>    
            </div>

            <div class="form-group">
                <label for="lastname"><span class="req">* </span> Last name: </label> 
                    <input class="form-control" type="text" name="lastname" id = "txt" onkeyup = "Validate(this)" placeholder="hyphen or single quote OK" required />  
                        <div id="errLast"></div>
            </div>
           
            <div class="form-group">
                <label for="password"><span class="req">* </span> Password: </label>
                    <input required name="password" type="password" class="form-control inputpass" minlength="4" maxlength="16"  id="pass1" /> </p>

                <label for="password"><span class="req">* </span> Password Confirm: </label>
                    <input required name="password" type="password" class="form-control inputpass" minlength="4" 
                    maxlength="16" placeholder="Enter again to validate"  id="pass2" 
                    onkeyup="checkPass(); return false;" />
                        <span id="confirmMessage" class="confirmMessage"></span>
            </div>
            <div class="form-group">
                <input class="btn btn-success" type="submit" name="submit_reg" value="Ajouter">
            </div>
            </fieldset>
            </form>
		</div>
	</div>
</div>

<br>
<a href="<c:url value="/logout" />">Logout</a>
<br><br>

</body>
</html>