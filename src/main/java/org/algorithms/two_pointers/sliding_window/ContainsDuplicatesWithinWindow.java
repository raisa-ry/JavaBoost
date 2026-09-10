package org.algorithms.two_pointers.sliding_window;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicatesWithinWindow {

    // Fixed size window, unsorted array
    // Return true if there are two elements within a window of size k that are equal
    // Time O(n), space O(min(n,k))
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

}