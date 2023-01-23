package edu.kit.informatik.scrabble.commands;

import edu.kit.informatik.scrabble.ScrabbleRound;
import edu.kit.informatik.scrabble.mechanics.Players;

/**
 * A class that handles the execution of the bag command
 * @author uswry
 * @version 1.0
 */
public class BagCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "(P1|P2)";
    private final ScrabbleRound scrabbleRound;

    /**
     * Initializes the needed constants
     * @param scrabbleRound - the scrabble round
     */
    public BagCommand(ScrabbleRound scrabbleRound) {
        super(ARGUMENT_REGEX);
        this.scrabbleRound = scrabbleRound;
    }

    @Override
    public String execute(String input) {
        // Players#fromString will never be null, since the input
        // is checked by the regex
        return scrabbleRound.getPiecesOf(Players.fromString(input));
    }
}
