package assignment.service;

import assignment.business.User;
import assignment.business.Article;

public interface ServiceRequest {
	public User login(String username, String password);
	public void addArticle(Article article);
	public Article getArticle(String id);
	
}
