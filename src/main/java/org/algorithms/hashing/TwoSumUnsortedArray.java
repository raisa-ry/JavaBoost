package org.algorithms.hashing;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9
-10^9 <= target <= 10^9

Only one valid answer exists.


Если мы будем решать перебором, то временная сложность будет квадратичной, а пространство константным
В данном случае с учетом ограничений квадратичная временная сложность допустима, но на грани
Чтобы уменьшить временную сложность мы жертвуем памятью - и то и другое будет линейным O(n)
 */

// Time O(n), space O(n)
public class TwoSumUnsortedArray {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int x = target - current;
            if (temp.containsKey(x)) {
                return new int[]{i, temp.get(x)};
            }
            temp.put(current, i);
        }
        return null;
    }

}