package edu.training.task02.model;

import edu.training.task02.controller.GlobalVariables;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameTest {
    private Game game;

    @Before
    public void init() {
        game = new Game();
        game.setBounds(GlobalVariables.PRIMARY_LOWER_BOUND, GlobalVariables.PRIMARY_UPPER_BOUND);
    }

    @Test
    public void makingHiddenNumber() {
        //Given
        int secretNumber = game.makeHiddenNumber();
        //Then
        assertTrue(game.getLowerBound() < secretNumber && secretNumber < game.getUpperBound());
    }

    @Test
    public void testHiddenNumberBoundsOnWideSample() {
        //Given
        int secretNumber;
        boolean result = true;

        //When
        for (int i = 0; i < 1000; i++) {
            secretNumber = game.makeHiddenNumber();
            result &= (game.getLowerBound() < secretNumber && secretNumber < game.getUpperBound());
        }

        //Then
        assertTrue(result);
    }

    @Test
    public void testMinBoundFrequencyOnWideSample() {
        //Given
        int secretNumber;
        int result = 0;

        //When
        for (int i = 0; i < 1000; i++) {
            secretNumber = game.makeHiddenNumber();
            if (secretNumber == game.getLowerBound() + 1)
                result++;
        }

        //Then
        assertTrue(result > 0);
    }

    @Test
    public void testMaxBoundFrequencyOnWideSample() {
        //Given
        int secretNumber;
        int result = 0;

        //When
        for (int i = 0; i < 1000; i++) {
            secretNumber = game.makeHiddenNumber();
            if (secretNumber == game.getUpperBound() - 1)
                result++;
        }

        //Then
        assertTrue(result > 0);
    }
}