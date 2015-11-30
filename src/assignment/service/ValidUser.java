package assignment.service;
/**
 * An interface to determine if a user is valid or not to be
 * inserted into the database.*/
public interface ValidUser {
	boolean isValidUser();
	boolean usernameIsValid();
	boolean passwordIsValid();
	boolean firstnameIsValid();
	boolean lastnameIsValid();
	boolean accessPriveledgeIsValid();
}
