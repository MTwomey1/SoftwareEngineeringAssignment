package assignment.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import assignment.business.User;
import assignment.business.UserAccessPriveledge;
import assignment.exceptions.DaoException;
import assignment.exceptions.InvalidUserException;
import assignment.service.UserService;


public class CreateUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = new UserService();
		
		User user = new User(request.getParameter("firstname"), 
				request.getParameter("lastname"),
				request.getParameter("username"),
				request.getParameter("password"),
				UserAccessPriveledge.MEMBER);
		try {
			userService.createUserAccount(user);
			return "/loginPage.jsp";
		} catch(DaoException e) {
			e.printStackTrace();
			return "/error.jsp";
		} catch(InvalidUserException e) {
			e.printStackTrace();
			System.out.println("Invalid user.");
			// TODO: Correct error message as to what was invalid.
			return "/error.jsp";
		}
	}

}
