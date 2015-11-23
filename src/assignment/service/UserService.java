package assignment.service;

import java.util.ArrayList;

import assignment.business.User;
import assignment.dao.UserDao;
import assignment.exceptions.DaoException;


public class UserService {

	UserDao dao = new UserDao();
	
	public UserService() {
	}
	
	public User login(String username, String password){
		
		User u = null;
		try {			
			u = dao.findUserByUsernamePassword(username, password);
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		return u;	
	}
	
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		
		try {
			users = dao.getAllUsers();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return users;
	}
	
}
