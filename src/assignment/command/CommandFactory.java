package assignment.command;


public class CommandFactory extends AbstractCommandFactory {

    private static CommandFactory factory = null;

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
			return createLoginCommand();
		case ADD_ARTICLE_COMMAND:
			return createAddArticleCommand();
		}
		return null;
	}
	
	
	private Command createLoginCommand() {
		return new LoginUserCommand();
	}
	
	private Command createAddArticleCommand() {
		return new AddArticleCommand();
	}

}

