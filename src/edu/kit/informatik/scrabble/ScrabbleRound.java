package edu.kit.informatik.scrabble;

import edu.kit.informatik.scrabble.mechanics.Players;
import edu.kit.informatik.scrabble.mechanics.ScrabbleMap;
import edu.kit.informatik.scrabble.mechanics.ScrabbleOperation;

/**
 * A class that handles the main functionality
 * needed for a scrabble round
 * @author uswry
 * @version 1.0
 *
 */
public class ScrabbleRound {

    private static final String NO_PIECES_ERROR = "You do not have the pieces to place";
    private final ScrabbleMap scrabbleMap;
    private String[] playersPieces;
    private Players roundOf;

    /**
     * Initializes the needed variables and constants
     */
    public ScrabbleRound() {
        this.scrabbleMap = new ScrabbleMap();
        roundOf = Players.PLAYER1;
    }

    /**
     * Get the player who is currently playing
     * @return get the player who is currently playing
     */
    public Players getRoundOf() {
        return roundOf;
    }

    /**
     * Switches the round
     */
    public void switchRound() {
        if (roundOf.equals(Players.PLAYER1)) {
            roundOf = Players.PLAYER2;
        } else {
            roundOf = Players.PLAYER1;
        }
    }

    /**
     * Checks if an operation is valid by checking if all the new
     * formed words are valid
     * @param operation - The operation to check
     * @return true if input is valid
     */
    public boolean isValidInput(ScrabbleOperation operation) {
        String playersPieces = getPiecesOf(operation.getPlayedBy());
        replaceCharsInString(playersPieces, operation.getPieces());
        return scrabbleMap.isValidInput(operation);
    }

    private String replaceCharsInString(String string, char[] toReplace) {
        StringBuilder newString = new StringBuilder(string);
        for (char c : toReplace) {
            int indexOf = newString.indexOf(String.valueOf(c));
            if (indexOf == -1) {
                throw new IllegalArgumentException(NO_PIECES_ERROR);
            } else {
                newString.deleteCharAt(indexOf);
            }
        }
        return newString.toString();
    }

    /**
     * Returns the pieces of the player
     * @param player - The player from whose pieces will be returned
     * @return the pieces of the player
     */
    public String getPiecesOf(Players player) {
        if (player.equals(Players.PLAYER1)) {
            return playersPieces[0];
        } else {
            return playersPieces[1];
        }
    }

    private void setPiecesOf(Players player, String pieces) {
        if (player.equals(Players.PLAYER1)) {
            playersPieces[0] = pieces;
        } else {
            playersPieces[1] = pieces;
        }
    }

    /**
     * Add the given input tot the map and removes the
     * pieces from the player inventory
     * @param operation - The operation to be added in the map
     */
    public void addInputToMap(ScrabbleOperation operation) {
        String playersPieces = getPiecesOf(operation.getPlayedBy());
        String newPieces = replaceCharsInString(playersPieces, operation.getPieces());
        setPiecesOf(roundOf, newPieces);
        scrabbleMap.addInputToMap(operation);
    }

    /**
     * Return the map
     * @return the map
     */
    public ScrabbleMap getMap() {
        return scrabbleMap;
    }

    /**
     * Sets the players pieces
     * @param playersPieces - The pieces of the player
     */
    public void setPlayersPieces(String[] playersPieces) {
        this.playersPieces = playersPieces;
    }
}
