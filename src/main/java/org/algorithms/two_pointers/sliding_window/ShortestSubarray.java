package org.algorithms.two_pointers.sliding_window;

public class ShortestSubarray {

    // Find the minimum length subarray, where the sum is greater than or equal to the target.
    // Assume all values are positive.
    // Time O(n), space O(1)
    public int findShortestSubarray(int[] nums, int target) {

        int L = 0, currentSum = 0;
        int length = Integer.MAX_VALUE;

        for (int R = 0; R < nums.length; R++) {
            currentSum += nums[R];

            while (currentSum >= target) {
                length = Math.min(length, R - L + 1);
                currentSum -= nums[L];
                L++;
            }
        }

        return length == Integer.MAX_VALUE ? 0 : length;
    }

}