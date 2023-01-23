package edu.kit.informatik.scrabble.commands;

public abstract class Command {
    public abstract String execute(String input);

    public boolean canExecute(String input) {
        return true;
    }

}
