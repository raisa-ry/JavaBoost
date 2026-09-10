package org.algorithms.two_pointers.sliding_window;

public class LongestSubarrayWithDuplicates {

    // Find the length of the longest subarray with duplicates
    // Time O(n), space O(1)
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

}
