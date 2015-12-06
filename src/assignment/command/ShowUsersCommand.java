package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.exceptions.DaoException;
import assignment.service.UserService;

public class ShowUsersCommand implements Command {


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			UserService service = new UserService();
			
			System.out.println("LENGTH: " + service.getAllUsers().length);
			request.getSession().setAttribute("users", service.getAllUsers());
			return "/banUserPage.jsp";
		} catch (DaoException e) {
			e.printStackTrace();
			return "/errorPage.jsp";
		}
	}
}

