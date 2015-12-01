package assignment.command;

import assignment.exceptions.CommandCreationException;

public class CommandFactory extends AbstractCommandFactory {

    protected static CommandFactory factory = null;

    private CommandFactory() {
    }

    /**
     * Returns a sharedInstance of CommandFactory
     * @return The sharedInstance.
     **/
	public static synchronized CommandFactory getSharedInstance() {
		if (factory == null) {
			factory = new CommandFactory();
		}
		return factory;
	}
	
	
	/**
	 * Creates a command.
	 * @param commandType The type of command to create.
	 * 					  @see CommandType for the list of
	 * 					  commands to create.
	 * 
	 * @return The command created. If an unknown command was
	 * 		   passed, null will be returned.
	 **/
	@Override
	public Command createCommand(CommandType commandType) throws CommandCreationException {
		if (commandType == null) {
			throw new CommandCreationException("Could not create command. Command was null.");
		}
		
		switch (commandType) {
		case LOGIN_COMMAND:
			return createLoginCommand();
		case ADD_ARTICLE:
			return createAddArticleCommand();
		case GET_ALL_ARTICLE_COMMAND:
			return createGetAllArticlesCommand();
		case CREATE_USER:
			return createUserCreationCommand();
		default:
			throw new 
			CommandCreationException("Could not create command. Command was not recognised.");
		}
	}
	
	
	private Command createLoginCommand() {
		return new LoginUserCommand();
	}
	
	private Command createAddArticleCommand() {
		return new AddArticleCommand();
	}
	
	private Command createGetAllArticlesCommand() {
		return new GetAllArticleCommand();
	}
	
	private Command createUserCreationCommand() {
		return new CreateUserCommand();
	}

}

