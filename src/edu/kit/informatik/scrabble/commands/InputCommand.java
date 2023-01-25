package edu.kit.informatik.scrabble.commands;

/**
 * A class to handle commands that need an input
 * @author uswry
 * @version 1.0
 */
public abstract class InputCommand extends Command {

    private final String pattern;

    /**
     * Initializing the regex pattern
     * @param pattern - the regex pattern for the input
     */
    public InputCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public abstract String execute(String input) throws IllegalArgumentException;

    @Override
    public boolean canExecute(String input) {
        return input.matches(pattern);
    }
}
