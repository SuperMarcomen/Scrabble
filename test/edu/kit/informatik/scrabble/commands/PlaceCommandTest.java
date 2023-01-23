package edu.kit.informatik.scrabble.commands;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaceCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"19;1;6;V",
            "19+;1;6;V",
            "19-;1;6;V",
            "19*;1;6;H",
            "19;1;6;V",
            "1;1;6;V"})
    void testCorrectInput(String input) {
        PlaceCommand placeCommand = new PlaceCommand();
        assertTrue(placeCommand.isInputCorrect(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12-*+*-*+*-*+*;7;2;VHVHVV",
            "111;1;6;V",
            "119*;1;6;HHHV",
            "19*;1;6;HHHV",
            "19*;1;6;"})
    void testWrongInput(String input) {
        PlaceCommand placeCommand = new PlaceCommand();
        assertFalse(placeCommand.isInputCorrect(input));
    }

}