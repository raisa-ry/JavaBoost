package org.algorithms;

public class Kadane {

    // O(n)
    public int findMaxSum(int[] nums) {

        int maxSum = nums[0];
        int curSum = 0;

        for (int num : nums) {
            curSum = Math.max(curSum, 0);
            curSum += num;
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    // Return min and max index of the subarray with max sum
    // Two pointers
    // O(n)
    public int[] slidingWindow(int[] nums) {

        int maxSum = nums[0];
        int curSum = 0;

        int maxL = 0, maxR = 0;
        int L = 0, R = 0;

        for (; R < nums.length; R++) {

            if (curSum < 0) {
                curSum = 0;
                L = R;
            }

            curSum += nums[R];
            if (curSum > maxSum) {
                maxSum = curSum;
                maxL = L;
                maxR = R;
            }

        }

        return new int[]{maxL, maxR};
    }

}