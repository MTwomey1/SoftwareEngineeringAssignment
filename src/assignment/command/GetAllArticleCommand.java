package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import assignment.business.Article;
import assignment.dao.ArticleDao;
import assignment.exceptions.DaoException;

public class GetAllArticleCommand implements Command {
	
	private String allArticlesPage = "/allArticles.jsp";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleDao articleDao = new ArticleDao();
		try {
			Article[] articles = articleDao.getAllArticles();
			
			HttpSession session = request.getSession();
			
			String cliSessionID = session.getId();
			session.setAttribute("loggedSessionId", cliSessionID);
			session.setAttribute("articles", articles);
			
			return allArticlesPage;
		} catch(DaoException e) {
			e.printStackTrace();
			return "/errorPage.jsp";
		}
	}	
}
