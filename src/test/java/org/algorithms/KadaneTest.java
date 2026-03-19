package org.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KadaneTest {

    private final Kadane kadane = new Kadane();
    int[] input = {4, -1, 2, -7, 3, 4};

    @Test
    void kadaneTest() {
        int res = kadane.findMaxSum(input);
        assertEquals(7, res);
    }

    @Test
    void slidingWindowTest() {
        int[] res = kadane.slidingWindow(input);
        assertEquals(Arrays.toString(res), Arrays.toString(new int[]{4, 5}));
    }

}