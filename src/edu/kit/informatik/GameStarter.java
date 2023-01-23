package edu.kit.informatik;

import edu.kit.informatik.scrabble.Game;
import edu.kit.informatik.scrabble.ScrabbleGame;
import edu.kit.informatik.scrabble.inputs.ConsoleInput;
import edu.kit.informatik.scrabble.inputs.UserInput;

/**
 * A general class to start a game on the console
 * @author uswry
 * @version 1.0
 */
public class GameStarter {

    /**
     * This method starts a game, checks if the given arguments are correct
     * and handles I/O to the console
     * @param args - The arguments required to run the game
     *              (In this case it needs two entry as arguments,
     *               representing the scrabble pieces of each player)
     */
    public static void main(String[] args) {
        Game scrabble = new ScrabbleGame();
        scrabble.setInput(args);
        String errorString = scrabble.verifyInput();
        if (errorString != null) {
            System.out.println(errorString);
            return;
        }
        UserInput userInput = new ConsoleInput();
        do {
            String line = userInput.getNext();
            String output = scrabble.handleInput(line);
            if (output != null) {
                System.out.println(output);
            }
        } while (scrabble.isRunning());
    }
}
