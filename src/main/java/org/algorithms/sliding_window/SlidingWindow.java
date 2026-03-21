package org.algorithms.sliding_window;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {

    // Fixed size
    // Return true if there are two elements within a window of size k that are equal
    // Brute force solution O(n*k)
    // Optimization HashSet O(n)
    public boolean containsDuplicate(int[] nums, int k) {

        Set<Integer> currentWindow = new HashSet<>();
        int L = 0;

        for (int R = 0; R < nums.length; R++) {

            if (R - L + 1 > k) {
                currentWindow.remove(nums[L]);
                L += 1;
            }
            if (currentWindow.contains(nums[R])) {
                return true;
            }
            currentWindow.add(nums[R]);
        }

        return false;
    }

    // Find the length of the longest subarray with duplicates
    //O(n)
    public int findLongestSubarrayWithDuplicates(int[] nums) {

        int maxLength = 0;
        int L = 0;

        for (int R = 0; R < nums.length; R++) {
            if (nums[L] != nums[R]) {
                L = R;
            }
            maxLength = Math.max(maxLength, R - L + 1);
        }

        return maxLength;
    }

    // Find the minimum length subarray, where the sum is greater than or equal to the target.
    // Assume all values are positive.
    // O(n)
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