package edu.training.task03;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.IsEqual.equalTo;

public class ArrayChallengeTest {
    private int[] array;
    private Challenge challenge;

    @BeforeAll
    public void preInit() {
        challenge = new StreamChallenge();
    }

    @Before
    public void init() {
        array = new int[]{9, -3, 4, 1, 6, 0, -2, -3, 5, 1, 4};
    }

    @Test
    public void shouldFindSum() {
        var sum = challenge.findSum(array);
        assertThat(sum, is(equalTo(22)));
    }

    @Test
    public void shouldFindMaxValue() {
        var max = challenge.findValueOfMax(array);
        assertThat(max, is(equalTo(9)));
    }

    @Test
    public void shouldFindIndexOfMax() {
        var indexOfMax = challenge.findIndexOfMax(array);
        assertThat(indexOfMax, is(equalTo(0)));
    }

    @Test
    public void shouldFindMinValue() {
        var min = challenge.findValueOfMin(array);
        assertThat(min, is(equalTo(-3)));
    }

    @Test
    public void shouldFindIndexOfMin() {
        var indexOfMin = challenge.findIndexOfMin(array);
        assertThat(indexOfMin, is(equalTo(7)));
    }

    @Test
    public void shouldFindAverage() {
        var average = challenge.findTheAverage(array);
        assertThat(average, closeTo(2, 0.01));
    }

    @Test
    public void shouldCountNumberOfEqualToGiven() {
        //Given
        var k = -3;
        //When
        var count = challenge.countNumberOfEqualToGiven(array, k);
        //Then
        assertThat(count, is(equalTo(2)));
    }

    @Test
    public void shouldCountZeros() {
        var countOfZeros = challenge.countZeros(array);
        assertThat(countOfZeros, is(equalTo(1)));
    }

}
