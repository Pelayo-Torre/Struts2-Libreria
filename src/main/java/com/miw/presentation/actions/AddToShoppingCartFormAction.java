package com.miw.presentation.actions;


import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;


public class AddToShoppingCartFormAction extends ActionSupport {

	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = Logger.getLogger(this.getClass());
	
	public String execute() {
		logger.debug("The screen to add books to the cart is displayed");
		return SUCCESS;
	}

}
