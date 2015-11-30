package assignment.business;

import java.io.Serializable;

import assignment.service.ValidUser;

public class User implements Serializable, ValidUser {


	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private UserAccessPriveledge accessPriveledge;

	public User() {}

	public User(String firstname, String lastname, String username, String password, 
			UserAccessPriveledge accessPriveledge) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
		setAccessPriveledge(accessPriveledge);
	}

	public User(int userId, String firstName, String lastName,
			String username, String password, UserAccessPriveledge accessPriveledge) {
		setId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
		setAccessPriveledge(accessPriveledge);
	}



	
	@Override
	public boolean passwordIsValid() {
		return getPassword() != null;
	}
	@Override
	public boolean usernameIsValid() {
		return getUsername() != null;
	}
	@Override
	public boolean firstnameIsValid() {
		return getFirstName() != null;
	}
	@Override
	public boolean lastnameIsValid() {
		return getLastName() != null;
	}
	@Override
	public boolean accessPriveledgeIsValid() {
		return getAccessPriveledge() != null;
	}
	
	
	
	public UserAccessPriveledge getAccessPriveledge() {
		return accessPriveledge;
	}
	public void setAccessPriveledge(UserAccessPriveledge accessPriveledge) {
		this.accessPriveledge = accessPriveledge;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		String returnString = 
				"Username: " + getUsername()
				+ "\nFirstname: " + getFirstName()
				+ "\nLastname; " + getLastName()
				+ "\nAccess: " + getAccessPriveledge();
		
		if (getPassword() != null) {
			return returnString + "\nPassword is not null.";
		} else {
			return returnString + "\nPassword is null.";
		}
	}
}
