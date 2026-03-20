package org.algorithms.sliding_window;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlidingWindowTest {

    private final SlidingWindow slidingWindow = new SlidingWindow();

    @Test
    void containsDuplicatesTest() {
        int[] nums = {1, 2, 3, 2, 3, 3};
        boolean res = slidingWindow.containsDuplicate(nums, 2);
        assertTrue(res);

        int[] nums1 = {1, 2, 3, 4, 5, 6};
        boolean res1 = slidingWindow.containsDuplicate(nums1, 2);
        assertFalse(res1);
    }

}