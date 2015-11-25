package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.business.Article;
import assignment.dao.ArticleDao;
import assignment.exceptions.DaoException;

public class GetAllArticleCommand implements Command {
	
	private String allArticlesPage = "allArticles.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleDao articleDao = new ArticleDao();
		try {
			Article[] articles = articleDao.getAllArticles();
			request.setAttribute("articles", articles);
			return allArticlesPage;
		} catch(DaoException e) {
			e.printStackTrace();
			return "errorPage.jsp";
		}
	}	
}
