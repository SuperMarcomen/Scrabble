package edu.kit.informatik.scrabble.inputs;

import java.util.Scanner;

/**
 * A class that takes the input of the user from the console.
 * @author uswry
 * @version 1.0
 */
public class ConsoleInput implements UserInput {

    private final Scanner scanner;

    /**
     * Initializes the scanner constant
     */
    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getNext() {
        return scanner.nextLine();
    }
}
