package com.miw.presentation.actions;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.RequestAware;

import com.miw.model.User;
import com.miw.presentation.user.UserManagerServiceHelper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Results({ @Result(name = "success", location = "/index.jsp"),
	@Result(name = "dates-error", location = "/WEB-INF/content/register-user-form.jsp"),
	@Result(name = "error", location = "/WEB-INF/content/register-user-form.jsp"),
	
	// For validation
	@Result(name = "input", location = "/WEB-INF/content/register-user-form.jsp")

})

@Validations(
requiredStrings =
		{
			@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.name", key="registerNameErr"), 
			@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.surname", key="registerSurnameErr"),
			@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.email", key="registerEmailErr")

		},
intRangeFields = 
		{
			@IntRangeFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.age", min="18", max="120",  key="registerAgeErr"), 
		},
emails = 
		{
			@EmailValidator(type = ValidatorType.SIMPLE, fieldName = "user.email", key="registerEmailErr")
		},
regexFields = 
		{
			@RegexFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.phone", regex = "[9|6|7][0-9]{8}", key="registerPhoneErr")
		}
)
public class RegisterAction extends ActionSupport implements RequestAware {

	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = -3854762737466390371L;
	private User user = null;
	private Map<String, Object> request;
	
	@Override
	public void validate() {
		logger.debug("Invoking validate!");
		
		if(user.getLogin().length() < 5) {
			addFieldError("user.login", getText("registerLoginShortErr"));
		}
		
		if(user.getName().length() > 35) {
			addFieldError("user.name", getText("registerLongFieldErr"));
		}
		
		if(user.getSurname().length() > 50) {
			addFieldError("user.surname", getText("registerLongFieldErr"));
		}
		
		if(user.getEmail().length() > 80) {
			addFieldError("user.email", getText("registerLongFieldErr"));
		}
		
		if(user.getLogin().length() > 15) {
			addFieldError("user.login", getText("registerLongFieldErr"));
		}
		
		if(user.getPassword().length() > 30) {
			addFieldError("user.password", getText("registerLongFieldErr"));
		}
		
		if(user.getPassword().length() < 7) {
			addFieldError("user.password", getText("registerPasswordShortErr"));
		}
		
		if(!user.getPassword().equals(user.getRepeatPassword())) {
			addFieldError("user.password", getText("registerPasswordsErr"));
		}
		
		if(user.getLogin().equals(user.getPassword())) {
			addFieldError("user.password", getText("registerLoginAndPasswordEqualsErr"));
		}
				
		super.validate();
	}
	
	public String execute() {
		logger.debug("Executing RegisterUserCommand");
		UserManagerServiceHelper helper = new UserManagerServiceHelper();
		
		try {
				
			User u = helper.getUserByLogin(user.getLogin());
			if(u != null) {
				request.put("userAlreadyExist", getText("registerLoginAlreadyExistsErr"));
				return "dates-error";
			}
												
			helper.saveUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.put("registerUserErr", getText("registerUserErr"));
			return ERROR;
		}
		return SUCCESS;
	}
		
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

}
