package assignment.service;

import assignment.business.Article;
import assignment.business.User;
import assignment.dao.ArticleDao;
import assignment.dao.UserDao;
import assignment.exceptions.DaoException;


public class UserService implements ServiceRequest {

	UserDao userDao = new UserDao();
	ArticleDao articleDao = new ArticleDao();
	
	public UserService() {
	}
	
	
	public User login(String username, String password) {
		User user = null;
		
		try {			
			user = userDao.findUserByUsernamePassword(username, password);
		} 
		catch (DaoException e) {
			e.printStackTrace();
		}
		return user;	
	}


	@Override
	public void addArticle(Article article) {
		
	}


	@Override
	public Article getArticle(String id) {
		Article article = null;
		
		try {
			article = articleDao.getArticle(1);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return article;
	}
}
