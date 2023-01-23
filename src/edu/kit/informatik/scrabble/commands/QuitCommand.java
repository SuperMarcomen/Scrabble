package edu.kit.informatik.scrabble.commands;

import edu.kit.informatik.scrabble.Game;
import edu.kit.informatik.scrabble.mechanics.Players;
import edu.kit.informatik.scrabble.mechanics.ScrabbleMap;

/**
 * A class that handles the execution of the quit command
 * @author uswry
 * @version 1.0
 */
public class QuitCommand extends Command {

    private final Game game;
    private final ScrabbleMap scrabbleMap;

    /**
     * Initializes the needed constants
     * @param game - the abstract instance of the game
     * @param scrabbleMap - the scrabble map
     */
    public QuitCommand(Game game, ScrabbleMap scrabbleMap) {
        this.game = game;
        this.scrabbleMap = scrabbleMap;
    }

    @Override
    public String execute(String input) {
        game.setRunning(false);
        StringBuilder output = new StringBuilder();
        int p1Score = scrabbleMap.calculateScore(Players.PLAYER1);
        int p2Score = scrabbleMap.calculateScore(Players.PLAYER2);
        output.append(p1Score);
        output.append(System.lineSeparator());
        output.append(p2Score);
        output.append(System.lineSeparator());
        if (p1Score > p2Score) {
            output.append("P1 wins");
        } else if (p2Score > p1Score) {
            output.append("P2 wins");
        } else {
            output.append("draw");
        }
        return output.toString();
    }
}
