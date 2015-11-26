package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.ArticleDao;

public class AddArticleCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "/addArticle.jsp";
	}
	

}
