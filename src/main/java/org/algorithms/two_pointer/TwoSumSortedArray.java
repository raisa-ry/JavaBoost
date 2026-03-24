package org.algorithms.two_pointer;

public class TwoSumSortedArray {

    //Given a sorted input array, return the two indices of two elements which sums up to the target value.
    // Assume there is exactly one solution
    // O(n) time, O(1) space
    public int[] targetSum(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                return new int[]{L, R};
            }
        }
        return null;
    }

}