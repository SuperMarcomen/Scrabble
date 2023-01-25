package edu.kit.informatik.scrabble.commands;

/**
 * A class to handle all type of commands.
 * @author uswry
 * @version 1.0
 */
public abstract class Command {
    /**
     * It executes the command and returns its output
     * @param input - the input from the console. Can also be empty.
     * @return the output of the command
     */
    public abstract String execute(String input);

    /**
     * Checks if the command can execute
     * @param input - the input from the console. Can also be empty.
     * @return true if the command can execute. False otherwise
     */
    public boolean canExecute(String input) {
        return true;
    }

}
