package assignment.command;


public class CommandFactory extends AbstractCommandFactory {

    protected static CommandFactory factory = null;

    private CommandFactory() {
    }

    /**
     * Returns a sharedInstance of CommandFactory
     * @return The sharedInstance.
     **/
	public static synchronized AbstractCommandFactory getSharedInstance() {
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
	 **/
	@Override
	public Command createCommand(CommandType commandType) {
		switch (commandType) {
		case LOGIN_COMMAND:
			System.out.println("Login Command");
			return createLoginCommand();
		case ADD_ARTICLE:
			System.out.println("Add article Command");
			return createAddArticleCommand();
		case GET_ARTICLE_COMMAND:
			System.out.println("Get article Command");
			return createGetAllArticlesCommand();
		default:
			return null;
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

}

