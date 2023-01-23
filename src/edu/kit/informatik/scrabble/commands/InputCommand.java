package edu.kit.informatik.scrabble.commands;

public abstract class InputCommand extends Command {

    private final String pattern;

    public InputCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public abstract String execute(String input) throws IllegalArgumentException; // TODO is needed?

    @Override
    public boolean canExecute(String input) {
        return input.matches(pattern);
    }
}
