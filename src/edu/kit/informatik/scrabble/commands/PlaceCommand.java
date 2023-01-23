package edu.kit.informatik.scrabble.commands;

import edu.kit.informatik.scrabble.ScrabbleRound;
import edu.kit.informatik.scrabble.mechanics.ScrabbleOperation;

/**
 * A class that handles the execution of the place command
 * @author uswry
 * @version 1.0
 */
public class PlaceCommand extends InputCommand {

    private static final String ARGUMENT_REGEX = "\\d{1,2}(-|\\+|\\*)?;(\\d;){2}(V|H)";
    private final ScrabbleRound round;

    /**
     * Initializes the needed constants
     * @param round - the scrabble round
     */
    public PlaceCommand(ScrabbleRound round) {
        super(ARGUMENT_REGEX);
        this.round = round;
    }

    @Override
    public String execute(String input) {
        ScrabbleOperation operation = new ScrabbleOperation(input, round.getRoundOf());
        if (round.isValidInput(operation)) {
            round.addInputToMap(operation);
            round.switchRound();
            return "OK";
        } else {
            return null;
        }
    }

}
