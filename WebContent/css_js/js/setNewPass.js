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

function checkPass()
{
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    
    var message = document.getElementById('confirmMessage');
    var vert = "#66cc66";
    var rouge = "#ff6666";

    
    if(pass1 == null || pass2 ==null){
    	pass1.style.backgroundColor = rouge;
    	pass2.style.backgroundColor = rouge;	   
        message.innerHTML = "N'oubliez pas de saisir le mot de passe de l'utilisateur"
    }
    if(pass1.value == pass2.value){
    	pass1.style.backgroundColor = vert;
        pass2.style.backgroundColor = vert;
        message.style.color = vert;
        message.innerHTML = "Bien! Les mots de passe concordent"
    }else{
        pass2.style.backgroundColor = rouge;
        pass1.style.backgroundColor = rouge;
        message.style.color = rouge;
        message.innerHTML = "Les mots de passe ne concordent pas!"
    }
}