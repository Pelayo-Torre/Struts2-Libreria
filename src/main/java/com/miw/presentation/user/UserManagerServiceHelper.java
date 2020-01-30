package com.miw.presentation.user;

import org.apache.log4j.Logger;

import com.miw.infrastructure.Factories;
import com.miw.model.User;

public class UserManagerServiceHelper {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public void saveUser(User user) throws Exception {
		logger.debug("Saving a user to Business Layer");
		(Factories.services.getUserManagerService()).saveUser(user);
	}
	
	public User getUserByLogin(String login) throws Exception  {
		logger.debug("Retrieving User by login from Business Layer");
		return (Factories.services.getUserManagerService()).getUserByLogin(login);
	}
	
	public boolean userExists(String login, String password) throws Exception{
		logger.debug("Retrieving User by login and password from Business Layer");
		return (Factories.services.getUserManagerService()).userExists(login, password);
	}

}
