package edu.kit.informatik.scrabble.mechanics;

import java.util.*;

/**
 * A class that represents the map for scrabble,
 * @author uswry
 * @version 1.0
 */
public class ScrabbleMap {

    private static final Set<Character> OPERATORS = Set.of('+', '-', '*');
    private static final int MAP_SIZE = 10;
    private static final String PIECE_ISOLATED_ERROR = "The piece you want to place is isolated";
    private static final String FIELD_ALREADY_OCCUPIED = "The field at %d %d is already occupied";
    private static final String WORD_NOT_POSTFIX_NOTATION_ERROR = "A word on the field is not in postfix notation";
    private static final String INPUT_DOES_NOT_FIT_ERROR = "Your input does not fit in the map";
    private MapField[][] map = new MapField[MAP_SIZE][MAP_SIZE];

    /**
     * Checks if the given input is valid
     * @param operation - the operation to be checked
     * @return true if the operation is valid
     * @throws IllegalArgumentException if the pieces are placed on already occupied slots
     *                                  or if the operation generates an invalid word
     */
    public boolean isValidInput(ScrabbleOperation operation) throws IllegalArgumentException {
        MapField[][] newMap = addInputToNewMap(operation);
        List<String> words = getWords(newMap, Players.ALL);
        return areWordsPostFix(words);
    }

    /**
     * Gets all words from a map
     * @param map - the map where to search the words
     * @param players - the player whose words need to be searches
     * @return all the words found
     */
    public List<String> getWords(MapField[][] map, Players players) {
        List<String> words = new ArrayList<>();
        for (MapField[] row : map) {
            for (MapField mapField : row) {
                if (mapField != null) {
                    words.addAll(getNeighbouringWordsOf(mapField, players));
                }
            }
        }
        return words;
    }

    /**
     * Calculates the score for the given player
     * @param players - the player whose score has to be calculated
     * @return the score of the player
     */
    public int calculateScore(Players players) {
        List<String> words = getWords(map, players);
        int score = 0;
        for (String word : words) {
            score += calculateWordValue(word);
        }
        return score;
    }

    private int calculateWordValue(String word) {
        Stack<Character> stack = new Stack<>();
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c);
            } else if (OPERATORS.contains(c)) {
                int right = Character.getNumericValue(stack.pop());
                int left = Character.getNumericValue(stack.pop());
                int result = calculateExpression(right, left, c);
                stack.push((char) result);
            }
        }
        return stack.pop();
    }

    private int calculateExpression(int right, int left, char operator) {
        if (operator == '+') {
            return right + left;
        } else if (operator == '-') {
            return right - left;
        } else {
            return right * left;
        }
    }

    /**
     * Adds the input to a new map and updates the current map
     * @param operation - the operation to be added
     */
    public void addInputToMap(ScrabbleOperation operation) {
        map = addInputToNewMap(operation);
    }
    
    private boolean areWordsPostFix(List<String> words) {
        for (String word : words) {
            int operands = 0;
            for (char c : word.toCharArray()) {
                if (Character.isDigit(c)) {
                    operands++;
                } else if (OPERATORS.contains(c)) {
                    if (operands < 2) {
                        throw new IllegalArgumentException(WORD_NOT_POSTFIX_NOTATION_ERROR);
                    } else {
                        operands -= 1;
                    }
                }
            }
            if (operands != 1) throw new IllegalArgumentException(WORD_NOT_POSTFIX_NOTATION_ERROR);
        }
        return true;
    }

    private Set<String> getNeighbouringWordsOf(MapField mapField, Players players) {
        Set<String> words = new HashSet<>();
        String horizontalWord = mapField.getHorizontalWord();
        if (mapField.isFirstHorizontalField() && horizontalWord.length() > 1) {
            if (players.equals(Players.ALL)) {
                words.add(horizontalWord);
            } else if (getOwner(mapField, Orientation.HORIZONTAL).equals(players)) {
                words.add(horizontalWord);
            }
        }

        String verticalWord = mapField.getVerticalWord();
        if (mapField.isFirstVerticalField() && verticalWord.length() > 1) {
            if (players.equals(Players.ALL)) {
                words.add(verticalWord);
            } else if (getOwner(mapField, Orientation.VERTICAL).equals(players)) {
                words.add(verticalWord);
            }
        }

        return words;
    }
    
    private Players getOwner(MapField first, Orientation orientation) {
        int p1Count = 0;
        int p2Count = 0;
        MapField currentField = first;
        while (currentField != null) {
            if (currentField.getPlacedBy().equals(Players.PLAYER1)) p1Count++;
            else p2Count++;

            if (orientation.equals(Orientation.HORIZONTAL)) {
                currentField = currentField.getRight();
            } else {
                currentField = currentField.getBottom();
            }
        }
        
        if (p1Count > p2Count) {
            return Players.PLAYER1;
        } else if (p2Count > p1Count) {
            return Players.PLAYER2;
        } else {
            return Players.ALL;
        }
    }

    private MapField[][] addInputToNewMap(ScrabbleOperation operation) throws IllegalArgumentException {
        MapField[][] newMap = new MapField[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < newMap.length; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, MAP_SIZE);
        }
        for (int i = 0; i < operation.getPieces().length; i++) {
            int row = operation.getRow();
            int column = operation.getColumn();
            if (operation.getOrientation().equals(Orientation.HORIZONTAL)) {
                column += i;
            } else {
                row += i;
            }
            if (row > MAP_SIZE - 1 || column > MAP_SIZE - 1) {
                throw new IllegalArgumentException(INPUT_DOES_NOT_FIT_ERROR);
            }
            if (newMap[row][column] != null) {
                String errorMessage = String.format(FIELD_ALREADY_OCCUPIED, row, column);
                throw new IllegalArgumentException(errorMessage);
            }
            MapField mapField = new MapField(operation.getPieces()[i], operation.getPlayedBy());
            newMap[row][column] = mapField;
            updateNeighbours(newMap, mapField, row, column);

            if (operation.getPieces().length == 1 && !mapField.hasNeighbours()) {
                throw new IllegalArgumentException(PIECE_ISOLATED_ERROR);
            }
        }

        return newMap;
    }

    private void updateNeighbours(MapField[][] map, MapField mapField, int row, int column) {
        if (row > 0) { // top
            updateNeighbour(map, mapField, row - 1, column, Direction.TOP);
        }
        if (column + 1 < MAP_SIZE) { // right
            updateNeighbour(map, mapField, row, column + 1, Direction.RIGHT);
        }
        if (row + 1 < MAP_SIZE) { // bottom
            updateNeighbour(map, mapField, row + 1, column, Direction.BOTTOM);
        }
        if (column > 0) { // left
            updateNeighbour(map, mapField, row, column - 1, Direction.LEFT);
        }
    }

    private void updateNeighbour(MapField[][] map, MapField mapField, int row, int column, Direction direction) {
        MapField neighbour = map[row][column];
        if (neighbour == null) return;
        switch (direction) {
            case TOP -> {
                neighbour.setBottom(mapField);
                mapField.setTop(neighbour);
            }
            case RIGHT -> {
                neighbour.setLeft(mapField);
                mapField.setRight(neighbour);
            }
            case BOTTOM -> {
                neighbour.setTop(mapField);
                mapField.setBottom(neighbour);
            }
            case LEFT -> {
                neighbour.setRight(mapField);
                mapField.setLeft(neighbour);
            }
        }
    }

    /**
     * Returns the map
     * @return the map
     */
    public MapField[][] getMap() {
        return map;
    }
}
