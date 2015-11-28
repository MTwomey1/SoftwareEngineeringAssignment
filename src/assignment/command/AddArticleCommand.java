package assignment.command;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.business.Article;
import assignment.dao.ArticleDao;
import assignment.exceptions.DaoException;

public class AddArticleCommand implements Command {
	
	 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String articleContent = request.getParameter("contentInputArea");
		String articleTitle = request.getParameter("title");
		
		System.out.println("Article title: " + articleTitle);
		System.out.println("Article content: " + articleContent);
		
		
		ArticleDao articleDao = new ArticleDao();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			articleDao.addArticle(new Article(articleTitle, articleContent, date.toString()));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		// If success return to login page, else error page.
		return "";
	}
	

}
