package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.exceptions.DaoException;
import assignment.service.UserService;

public class BanUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			UserService service = new UserService();
			String username = request.getParameter("usernametoban");
			System.out.println("Username to ban" + username);
			service.banUser(username);
			return "/banUserPage.jsp";
		} catch (DaoException e) {
			e.printStackTrace();
			return "/errorPage.jsp";
		}
	}

}
