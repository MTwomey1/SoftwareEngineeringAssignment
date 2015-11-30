package assignment.service;

import assignment.business.User;
import assignment.dao.UserDao;
import assignment.exceptions.DaoException;


public class UserService implements LoginServiceRequest {

	UserDao userDao = new UserDao();
	
	public UserService() {
	}
	
	
	public User login(String username, String password) {
		User user = null;
		
		try {			
			user = userDao.findUserByUsernamePassword(username, password);
			addUserToManager(user);
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * Creates a user account.
	 * @param user The user to create.
	 * @throws DaoException @see {@link UserDao#insertUserIntoDatabase(User)}
	 * 					    for throw reasons.
	 **/
	public <T extends User & ValidUser> void createUserAccount(T user) throws DaoException {
		UserDao userDao = new UserDao();
		userDao.insertUserIntoDatabase(user);
	}
	
	
	
	private void addUserToManager(User user) {
		if (user == null) {
			return;
		}
		UserManager.setCurrentUser(user);
	}
	
	
	/**
	 * A static inner class that will hold a record
	 * of the current user that is logged in.
	 **/
	public static class UserManager {
		private static User currentUser;
		
		public static void setCurrentUser(User currentUser) {
			UserManager.currentUser = currentUser;
		}
		
		public static User getCurrentUser() {
			return currentUser;
		}
	}

}
