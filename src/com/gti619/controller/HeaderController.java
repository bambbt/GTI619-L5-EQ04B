package com.gti619.controller;

public class HeaderController {

	static final String header = 
"<nav class=\"navbar navbar-light bg-faded\">"+
  "<ul class=\"nav navbar-nav\">"+
  "<li class=\"nav-item active\">"+
   "<a href=\"<c:url value=\"/homeAdmin\" />\">Home</a>"+
    "</li><li class=\"nav-item active\"><a href=\"<c:url value=\"/cercle\" />\">Cercle</a></li>"+
    "<li class=\"nav-item\"><a href=\"<c:url value=\"/carre\" />\">Carre</a></li><li class=\"nav-item\">"+
    "<a href=\"<c:url value=\"/administration\" />\">Administration</a></li></ul></nav>";
}




