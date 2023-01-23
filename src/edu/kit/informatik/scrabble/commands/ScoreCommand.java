package edu.kit.informatik.scrabble.commands;

import edu.kit.informatik.scrabble.mechanics.Players;
import edu.kit.informatik.scrabble.mechanics.ScrabbleMap;

/**
 * A class that handles the execution of the score command
 * @author uswry
 * @version 1.0
 */
public class ScoreCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "(P1|P2)";
    private final ScrabbleMap scrabbleMap;

    /**
     * Initializes the needed constants
     * @param scrabbleMap - the scrabble map
     */
    public ScoreCommand(ScrabbleMap scrabbleMap) {
        super(ARGUMENT_REGEX);
        this.scrabbleMap = scrabbleMap;
    }

    @Override
    public String execute(String input) {
        Players player = Players.fromString(input);
        int score = scrabbleMap.calculateScore(player);
        return String.valueOf(score);
    }
}
