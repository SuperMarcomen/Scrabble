package edu.kit.informatik.scrabble.commands;

import edu.kit.informatik.scrabble.mechanics.MapField;
import edu.kit.informatik.scrabble.mechanics.ScrabbleMap;

/**
 * A class that handles the execution of the print command
 * @author uswry
 * @version 1.0
 */
public class PrintCommand extends Command {

    private static final String CELL_FORMAT = "[%c]";
    private final ScrabbleMap map;

    /**
     * Initializes the needed constants
     * @param map - the scrabble map
     */
    public PrintCommand(ScrabbleMap map) {
        this.map = map;
    }

    @Override
    public String execute(String input) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < map.getMap().length; i++) {
            for (MapField mapField : map.getMap()[i]) {
                if (mapField == null) {
                    string.append(CELL_FORMAT.replace("%c", " "));
                } else {
                    string.append(String.format(CELL_FORMAT, mapField.getValue()));
                }

            }
            if (i < (map.getMap().length - 1)) {
                string.append(System.lineSeparator());
            }
        }
        return string.toString();
    }
}
