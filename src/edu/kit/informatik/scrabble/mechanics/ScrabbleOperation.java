package edu.kit.informatik.scrabble.mechanics;

/**
 * A class that describes a possible
 * scrabble operation.
 * @author uswry
 * @version 1.0
 */
public class ScrabbleOperation {

    private final char[] pieces;
    private final int row;
    private final int column;
    private final Orientation orientation;
    private final Players playedBy;

    /**
     * Parses the input and initializes the local constants
     * @param operation - the scrabble operation given as an input
     * @param playedBy - the player who places these pieces
     */
    public ScrabbleOperation(String operation, Players playedBy) {
        this.playedBy = playedBy;
        String[] args = operation.split(";");
        pieces = args[0].toCharArray();
        row = Integer.parseInt(args[1]);
        column = Integer.parseInt(args[2]);
        if (args[3].equals("H")) {
            orientation = Orientation.HORIZONTAL;
        } else {
            orientation = Orientation.VERTICAL;
        }
    }

    /**
     * Gets the pieces
     * @return the pieces
     */
    public char[] getPieces() {
        return pieces;
    }

    /**
     * Gets the row
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Return the orientation
     * @return the orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns the player who places these pieces
     * @return the player who places these pieces
     */
    public Players getPlayedBy() {
        return playedBy;
    }
}
