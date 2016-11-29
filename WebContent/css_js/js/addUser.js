//Validation des mots de passe
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

//Validation du texte
function Validate(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z-'\n\r.]+/g, '');
}


function login(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z0-9-'\n\r.]+/g, '');
}


function nom(txt) {
    txt.value = txt.value.replace(/[^a-zA-Z0-9- '\n\r.]+/g, '');
}

function validateRole(){

var messageBox = document.getElementById('userBoxMessage');

messageBox.style.color = rouge;
messageBox.innerHTML = "N'oubliez pas le rôle de l'utilisateur"

}

function msg() {
    var x = document.getElementById("snackbar")
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}




