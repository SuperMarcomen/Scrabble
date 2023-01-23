package edu.kit.informatik.scrabble.mechanics;

/**
 * An enum that describes the possible players
 * @author uswry
 * @version 1.0
 */
public enum Players {

    /**
     * The first player
     */
    PLAYER1("P1"),
    /**
     * The second player
     */
    PLAYER2("P2"),
    /**
     * All players. Only used the get all the words from the field
     */
    ALL("");

    private final String consoleRepresentation;

    Players(String consoleRepresentation) {
        this.consoleRepresentation = consoleRepresentation;
    }

    public static Players fromString(String text) {
        for (Players player : Players.values()) {
            if (player.consoleRepresentation.equalsIgnoreCase(text)) {
                return player;
            }
        }
        return null;
    }
}
