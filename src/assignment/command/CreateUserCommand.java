package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.business.User;
import assignment.business.UserAccessPriveledge;
import assignment.dao.UserDao;
import assignment.exceptions.DaoException;
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
		
		userService.createUserAccount(user);		
		
		try {
			userDao.insertUserIntoDatabase(user);
			return "/createSuccess.jsp";
		} catch(DaoException e) {
			return "/error.jsp";
		}
	}

}
