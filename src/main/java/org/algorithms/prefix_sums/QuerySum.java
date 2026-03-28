package org.algorithms.prefix_sums;

public class QuerySum {

    // Given an array of values, design a data structure that can query the sum of a subarray of the values.
    // Brute force O(n)
    public int querySum(int[] nums, int left, int right) {

        int[] preparedArray = new int[nums.length];
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            preparedArray[i] = total += nums[i];
        }

        // O(1)
        int preLeft = left == 0 ? 0 : preparedArray[left - 1];
        int preRight = preparedArray[right];

        return preRight - preLeft;
    }

}