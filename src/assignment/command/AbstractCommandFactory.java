package assignment.command;

import assignment.exceptions.CommandCreationException;

/**
 * An abstract class that defines basic behavior all
 * of CommandFactory.
 */
public abstract class AbstractCommandFactory {
	public abstract Command createCommand(CommandType commandType) throws CommandCreationException;
}
