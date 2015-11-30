package assignment.service;

import assignment.business.User;
import assignment.dao.UserDao;
import assignment.exceptions.DaoException;
import assignment.exceptions.InvalidUserException;


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
	 * @throws InvalidUserException This exception will be thrown if the user is invalid.
	 **/
	public <T extends User & ValidUser> void createUserAccount(T user) 
			throws InvalidUserException, DaoException {
		UserDao userDao = new UserDao();
		System.out.println(user.toString());
		validateUser(user);
		userDao.insertUserIntoDatabase(user);
	}
	
	
	private void validateUser(User user) throws InvalidUserException {
		if (!user.usernameIsValid()) {
			throw new InvalidUserException("Username is invalid");
		} else if (!user.passwordIsValid()) {
			throw new InvalidUserException("Password is invalid");
		} else if (!user.firstnameIsValid()) {
			throw new InvalidUserException("Firstname is invalid");
		} else if (!user.lastnameIsValid()) {
			throw new InvalidUserException("Last name is invalid");
		} else if (!user.accessPriveledgeIsValid()) {
			throw new InvalidUserException("AccesPriveledge is invalid");
		}
	}
	
	
	/**
	 * Adds a user to be signed in.
	 * @param user The user that wants to be signed in.
	 * */
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
		
		public static void removeCurrentUser() {
			currentUser = null;
		}
	}

}
