package org.algorithms.two_pointers;

import java.util.*;

/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */

// Сначала сортируем
// Опираемся на решение проблемы 2Sum

// Time O(N^2), space O(1)
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            // skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int p1 = i + 1;
            int p2 = nums.length - 1;
            int targetSum = -nums[i];

            while (p1 < p2) {
                int sum = nums[p1] + nums[p2];
                if (sum == targetSum) {
                    result.add(Arrays.asList(nums[i], nums[p1], nums[p2]));
                    p1++;
                    p2--;

                    // skip duplicates
                    while (p1 < p2 && nums[p1] == nums[p1 - 1]) p1++;
                    while (p1 < p2 && nums[p2] == nums[p2 + 1]) p2--;
                } else if (sum < targetSum) {
                    p1++;
                } else {
                    p2--;
                }
            }
        }

        return result;
    }
}