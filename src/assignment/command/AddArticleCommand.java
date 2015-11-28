package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.ArticleDao;

public class AddArticleCommand implements Command {
	
	 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String articleContent = request.getParameter("contentInputArea");
		String articleTitle = request.getParameter("title");
		
		System.out.println("Article title: " + articleTitle);
		System.out.println("Article content: " + articleContent);
		return "";
	}
	

}
