package assignment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import assignment.business.User;
import assignment.service.UserService;



public class LoginUserCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse repsonse){
			
		UserService userService = new UserService();
		String forwardToJsp = "";		

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//Check we have a user name and password...
		if (username != null && password != null){
			
			// Make call to the 'Model' using the UserServive class to login...
			User userLoggingIn = userService.login(username, password);

			if (userLoggingIn != null) {

				//If login successful, store the session id for this client...
				HttpSession session = request.getSession();
				String clientSessionId = session.getId();
				session.setAttribute("loggedSessionId", clientSessionId);

				session.setAttribute("user", userLoggingIn);

				forwardToJsp = correctPageForUser(userLoggingIn);				
			}
			else{
				forwardToJsp = "/loginFailure.jsp";	
			}
		}
		else {
			forwardToJsp = "/loginFailure.jsp";	
		}
		return forwardToJsp;
	}

	
	/**
	 * Determines the page to return given a user logging in.
	 * @param user the user that has logged in.
	 * @return The page name.
	 * */
	private String correctPageForUser(User user) {
		switch(user.getAccessPriveledge()) {			
		case MEMBER:
			return "";
			
		case MODERATOR:
			return "";
			
		case CONTENT_MANAGER:
			return "";
			
			default:
				return "/errorPage.jsp";
		}
	}
}
