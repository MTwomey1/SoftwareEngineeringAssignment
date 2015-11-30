package assignment.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import assignment.business.User;
import assignment.business.Article;
import assignment.dao.ArticleDao;
import assignment.exceptions.DaoException;
import assignment.service.UserService;

public class AddArticleCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String articleContent = request.getParameter("contentInputArea");
		String articleTitle = request.getParameter("title");
		
		System.out.println("Article title: " + articleTitle);
		System.out.println("Article content: " + articleContent);
		
		
		ArticleDao articleDao = new ArticleDao();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		User currentUser = UserService.UserManager.getCurrentUser();
		try {
			System.out.println(request.getRemoteUser());
			
			articleDao.addArticle(new Article(articleTitle, articleContent, date.format(new Date())), currentUser);
		
		} catch (DaoException e) {
			e.printStackTrace();
			return "/errorPage.jsp";
		}	
		return "/loginSuccess.jsp";
	}
}
