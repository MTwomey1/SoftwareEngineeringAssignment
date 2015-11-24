package assignment.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import assignment.business.User;
import assignment.service.UserService;



public class ListUsersCommand implements Command {
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<User> users = new ArrayList<User>();
		
		String forwardToJSP = "";
		
		UserService userService = new UserService();
		
		users = userService.getAllUsers();
		
		if (users != null) {
			HttpSession session = request.getSession();
			String cliSessionID = session.getId();
			session.setAttribute("loggedSessionId", cliSessionID);
			session.setAttribute("users", users);
			
			forwardToJSP = "/users.jsp";
		} else {
			forwardToJSP = "/error.jsp";
		}
		
		return forwardToJSP;
	}
	
}
