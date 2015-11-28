package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.dao.ArticleDao;

public class AddArticleCommand implements Command {
	
	 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("3333");
		return "";
	}
	

}
