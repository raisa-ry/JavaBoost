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
        int L = 0, R = 0;

        for (; R < nums.length; R++) {

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

}