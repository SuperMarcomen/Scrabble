package edu.kit.informatik.scrabble;

/**
 * This class defines the general methods and variable
 * needed for a game.
 * @author uswry
 * @version 1.0
 */
public abstract class Game {

    /**
     * The input that the user inputs in the console after
     * the command. It can be empty.
     */
    protected String[] input;
    private boolean running = true;

    /**
     * Verifies the input given as argument to the program
     * @return - null if there is no error
     *         - an error message if there is one
     */
    public abstract String verifyInput();

    /**
     * Takes the input of the user, processes it and returns a
     * string as an output.
     * @param input - the user input
     * @return the output for the executed command
     */
    public abstract String handleInput(String input);

    /**
     * Returns the value of the variable running
     * @return the value of the variable running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the value of the variable running
     * @param running - the new value of the variable running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Sets the input from the user
     * @param input - the user input
     */
    public void setInput(String[] input) {
        this.input = input;
    }
}
