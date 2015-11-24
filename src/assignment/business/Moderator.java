package assignment.business;

import assignment.business.User;

public class Moderator extends User {
	
	private static final long serialVersionUID = 1L;
	
	
	public Moderator(int userId, String firstName, String lastName, String username, String password,
			UserAccessPriveledge accessPriveledge) {
		super(userId, firstName, lastName, username, password, accessPriveledge);
	}
	
}