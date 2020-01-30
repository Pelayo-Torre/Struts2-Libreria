package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.miw.model.Book;
import com.miw.model.ShoppingCart;
import com.opensymphony.xwork2.ActionSupport;


@Results({ @Result(name="success", location = "view-shopping-cart.action", type = "redirectAction"),
	@Result(name="error", location="add-to-shopping-cart-form.jsp")})


public class AddToShoppingCartAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware{

	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = 1L;
	private Map<String, Object> request;
	private Map<String, Object> session = null;	
	private List<String> addedBooks;
	private Map<String, Object> application = null;

	
	public String execute() {
		if(addedBooks.size() == 0) {
			request.put("notBooksSelected", getText("CartBooksNotSelected"));
			return ERROR;
		}
		
		ShoppingCart sc = (ShoppingCart) session.get("shoppingcart");
		
		if ( sc == null )
		{
			sc = new ShoppingCart();
			session.put("shoppingcart", sc);
		}
		
		for(String id : this.addedBooks) {
			String name = getNameOfBookByIdentifier(id);
			if(name != null)
				sc.add(name);
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String getNameOfBookByIdentifier(String id) {
		for(Book b : (List<Book>) application.get("booksSession")) {
			if(b.getId() == Integer.parseInt(id))
				return b.getTitle();
		}
		return null;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public List<String> getAddedBooks() {
		return addedBooks;
	}

	public void setAddedBooks(List<String> addedBooks) {
		this.addedBooks = addedBooks;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

}
