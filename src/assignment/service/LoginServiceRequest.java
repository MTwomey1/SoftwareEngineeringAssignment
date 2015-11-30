package assignment.service;

import assignment.business.User;

public interface LoginServiceRequest {
	public User login(String username, String password);
}
