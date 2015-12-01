package assignment.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import assignment.business.Article;
import assignment.dao.ArticleDao;
import assignment.exceptions.DaoException;
import assignment.service.UserService;



public class GetArticlesForUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {	
		ArticleDao articleDao = new ArticleDao();
		try {
			Article[] articles = 
					articleDao.getUsersArticles(UserService.UserManager.getCurrentUser());
			request.setAttribute("usersArticles", articles);
			return "/userProfile.jsp";
		} catch (DaoException e) {
			e.printStackTrace();
			return "/errorPage.jsp";
		}
	}
}
