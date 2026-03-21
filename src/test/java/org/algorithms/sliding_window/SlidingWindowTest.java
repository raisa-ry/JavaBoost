package org.algorithms.sliding_window;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void findLongestSubarrayWithDuplicatesTest() {
        int[] nums = {4, 2, 2, 3, 3, 3};
        int res = slidingWindow.findLongestSubarrayWithDuplicates(nums);
        assertEquals(3, res);

        int[] nums1 = {4, 2, 1, 6, 7, 3};
        int res1 = slidingWindow.findLongestSubarrayWithDuplicates(nums1);
        assertEquals(1, res1);
    }

    @Test
    void findMinLengthSubarrayTest() {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int res = slidingWindow.findShortestSubarray(nums, 6);
        assertEquals(2, res);

        int[] nums1 = {2, 3, 1, 2, 2, 3};
        int res1 = slidingWindow.findShortestSubarray(nums1, 6);
        assertEquals(3, res1);
    }

}