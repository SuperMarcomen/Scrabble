package edu.kit.informatik.scrabble;

import edu.kit.informatik.scrabble.commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The main class that handles the command logic
 * of the game.
 * @author uswry
 * @version 1.0
 */
public class ScrabbleGame extends Game {

    private static final String ERROR_FORMAT = "Error: %s";
    private static final String INCORRECT_USER_INPUT = "Your input was not correct";
    private static final String INCORRECT_INPUT = "You have to run the program giving it two arguments,"
                                                + " representing the pieces available for each player";
    private static final String UNKNOWN_COMMAND = "The command you typed does not exist";
    private static final String CORRECT_INPUT_TIP = "Only these characters are allowed as input: [0-9*+-]";
    private static final String CORRECT_INPUT = "\\d{2,}([+\\-*])+";
    private final Map<String, Command> commands;
    private final ScrabbleRound scrabbleRound;

    /**
     * Initializes the needed variable and constants
     */
    public ScrabbleGame() {
        scrabbleRound = new ScrabbleRound();
        commands = initializeCommands();
    }

    @Override
    public String verifyInput() {
        if (input.length != 2) {
            return String.format(ERROR_FORMAT, INCORRECT_INPUT);
        }
        if (input[0].matches(CORRECT_INPUT) && input[1].matches(CORRECT_INPUT)) {
            return null;
        } else {
            return String.format(ERROR_FORMAT, CORRECT_INPUT_TIP);
        }
    }

    @Override
    public void setInput(String[] input) {
        super.setInput(input);
        scrabbleRound.setPlayersPieces(input);
    }

    @Override
    public String handleInput(String input) {
        String[] args = input.split(" ");
        if (args[0].isEmpty()) {
            return null;
        }
        Command command = commands.get(args[0]);
        if (command == null) {
            return String.format(ERROR_FORMAT, UNKNOWN_COMMAND);
        }
        String arguments = args.length > 1 ? args[1] : "";
        if (command.canExecute(arguments)) {
            try {
                return command.execute(arguments);
            } catch (IllegalArgumentException exception) {
                return String.format(ERROR_FORMAT, exception.getMessage());
            }
        } else {
            return String.format(ERROR_FORMAT, INCORRECT_USER_INPUT);
        }
    }

    private Map<String, Command> initializeCommands() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("place", new PlaceCommand(scrabbleRound));
        commands.put("quit", new QuitCommand(this, scrabbleRound.getMap()));
        commands.put("print", new PrintCommand(scrabbleRound.getMap()));
        commands.put("bag", new BagCommand(scrabbleRound));
        commands.put("score", new ScoreCommand(scrabbleRound.getMap()));
        return commands;
    }
}
