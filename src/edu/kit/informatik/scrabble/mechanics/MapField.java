package edu.kit.informatik.scrabble.mechanics;

/**
 * This class represents a single field in the map
 * @author uswry
 * @version 1.0
 */
public class MapField {

    private MapField top;
    private MapField left;
    private MapField right;
    private MapField bottom;
    private final char value;
    private final Players placedBy;

    /**
     * Initializes the needed constants
     * @param value - the char placed in this field
     * @param placedBy - the player who places this piece
     */
    public MapField(char value, Players placedBy) {
        this.value = value;
        this.placedBy = placedBy;
    }

    /**
     * Checks if this field has neighbours
     * @return true if this field has neighbours
     */
    public boolean hasNeighbours() {
        return top != null || right != null
                || bottom != null || left != null;
    }

    /**
     * Returns the horizontal word
     * @return the horizontal word
     */
    public String getHorizontalWord() {
        StringBuilder string = new StringBuilder();
        string.append(value);
        if (getRight() != null) {
            string.append(getRight().getHorizontalWord());
        }
        return string.toString();
    }

    /**
     * Returns the vertical word
     * @return the vertical word
     */
    public String getVerticalWord() {
        StringBuilder string = new StringBuilder();
        string.append(value);
        if (getBottom() != null) {
            string.append(getBottom().getVerticalWord());
        }
        return string.toString();
    }

    /**
     * Returns true if this is the first horizontal field
     * of a word
     * @return true if this is the first horizontal field
     *          of a word
     */
    public boolean isFirstHorizontalField() {
        return getLeft() == null;
    }

    /**
     * Returns true if this is the first vertical field
     * of a word
     * @return true if this is the first vertical field
     *          of a word
     */
    public boolean isFirstVerticalField() {
        return getTop() == null;
    }

    /**
     * Returns the field at the top
     * @return  the field at the top
     */
    public MapField getTop() {
        return top;
    }

    /**
     * Returns the field at the top
     * @param top - the field at the top
     */
    public void setTop(MapField top) {
        this.top = top;
    }

    /**
     * Returns the field at the left
     * @return  the field at the left
     */
    public MapField getLeft() {
        return left;
    }

    /**
     * Returns the field at the left
     * @param left - the field at the left
     */
    public void setLeft(MapField left) {
        this.left = left;
    }

    /**
     * Returns the field at the right
     * @return  the field at the right
     */
    public MapField getRight() {
        return right;
    }

    /**
     * Returns the field at the right
     * @param right - the field at the right
     */
    public void setRight(MapField right) {
        this.right = right;
    }

    /**
     * Returns the field at the bottom
     * @return  the field at the bottom
     */
    public MapField getBottom() {
        return bottom;
    }


    /**
     * Returns the field at the bottom
     * @param bottom - the field at the bottom
     */
    public void setBottom(MapField bottom) {
        this.bottom = bottom;
    }

    /**
     * Returns the player who places this field
     * @return the player who places this field
     */
    public Players getPlacedBy() {
        return placedBy;
    }

    /**
     * Returns the char places in this field
     * @return the char places in this field
     */
    public char getValue() {
        return value;
    }
}
