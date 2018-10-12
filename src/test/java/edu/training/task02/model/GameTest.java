package edu.training.task02.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameTest {
    private Game game;

    @Before
    public void init() {
        game = new Game();
    }

    @Test
    public void makingHiddenNumber() {
        //Given
        int secretNumber = game.makeHiddenNumber();
        //Then
        assertTrue(Game.LOWER_BOUND < secretNumber && secretNumber < Game.UPPER_BOUND);
    }

    @Test
    public void testHiddenNumberBoundsOnWideSample() {
        //Given
        int secretNumber;
        boolean result = true;

        //When
        for (int i = 0; i < 1000; i++) {
            secretNumber = game.makeHiddenNumber();
            result &= (Game.LOWER_BOUND < secretNumber && secretNumber < Game.UPPER_BOUND);
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
            if(secretNumber == Game.LOWER_BOUND +1 )
                result++;
        }

        //Then
        assertTrue(result >0);
    }

    @Test
    public void testMaxBoundFrequencyOnWideSample() {
        //Given
        int secretNumber;
        int result = 0;

        //When
        for (int i = 0; i < 1000; i++) {
            secretNumber = game.makeHiddenNumber();
            if(secretNumber == Game.UPPER_BOUND -1 )
                result++;
        }

        //Then
        assertTrue(result >0);
    }
}