package com.octest.forms;

import javax.servlet.http.HttpServletRequest;

public class Cconnectionform {
	public  String res ;
    public void verifid(HttpServletRequest  request) {
        String login = request.getParameter("username");
        String pss = request.getParameter("password");

    if(   pss != null) {	if(pss.equals(login+"123")) {
    		res="bien cnc ";
    		
    	}
    	else {res="votre mot de est incorrecte";}
    	
    }}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

}
