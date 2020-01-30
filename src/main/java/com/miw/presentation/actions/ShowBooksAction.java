package com.miw.presentation.actions;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;

import com.miw.model.Book;
import com.miw.presentation.book.BookManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage(value ="miw.Amazin")

public class ShowBooksAction extends ActionSupport implements RequestAware, ApplicationAware  {
	
	private static final long serialVersionUID = -4752542581534740735L;
	Logger logger = Logger.getLogger(this.getClass());
	Map<String,Object> request = null;
	private Map<String, Object> application = null;

	public String execute() {
		logger.debug("Executing ShowBooksCommand");
		BookManagerServiceHelper helper = new BookManagerServiceHelper();
		try {
			List<Book> list = helper.getBooks();
			application.put("booksSession", list);
			request.put("books", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	
}
