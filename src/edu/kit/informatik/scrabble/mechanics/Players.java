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

    /**
     * Sets the string representation of the enum
     * @param consoleRepresentation - the string representation of the enum
     */
    Players(String consoleRepresentation) {
        this.consoleRepresentation = consoleRepresentation;
    }

    /**
     * It returns the enum value from a string
     * @param text - the string representing an enum value
     * @return the enum value if found, otherwise null
     */
    public static Players fromString(String text) {
        for (Players player : Players.values()) {
            if (player.consoleRepresentation.equalsIgnoreCase(text)) {
                return player;
            }
        }
        return null;
    }
}
