package assignment.business;

import assignment.business.User;

public class Member extends User {

	public Member(int userId, String firstName, String lastName, String username, String password,
			UserAccessPriveledge accessPriveledge) {
		super(userId, firstName, lastName, username, password, accessPriveledge);
	}

	private static final long serialVersionUID = 1L;
	
}