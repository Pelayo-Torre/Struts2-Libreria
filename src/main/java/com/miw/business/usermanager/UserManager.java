package com.miw.business.usermanager;


import org.apache.log4j.Logger;

import com.miw.business.UserDataServiceHelper;
import com.miw.model.User;

public class UserManager implements UserManagerService{
	
	Logger logger = Logger.getLogger(this.getClass());

	public void saveUser(User user) throws Exception {
		logger.debug("Asking for save user");
		(new UserDataServiceHelper()).saveUser(user);
	}

	public User getUserByLogin(String login) throws Exception {
		logger.debug("Asking for user by Login");
		return (new UserDataServiceHelper()).getUserByLogin(login);
	}

	public boolean userExists(String login, String password) throws Exception{
		logger.debug("Asking for user by Login and password");
		return (new UserDataServiceHelper()).userExists(login, password);
	}

}
