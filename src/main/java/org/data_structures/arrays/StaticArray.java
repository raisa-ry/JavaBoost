package org.data_structures.arrays;

/*
 * Reading O(1)
 * Insertion O(n)*. If inserting at the end of the array, O(1)
 * Deletion O(n)*. If inserting at the end of the array, O(1)
 */
public class StaticArray {

    int[] array = new int[10];
    int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public void removeEnd(int[] arr, int length) {
        if (length > 0) {
            arr[length - 1] = 0;
            length--;
        }
    }

    public void removeMiddle(int[] arr, int i, int length) {
        for (int index = i + 1; index < length; index++) {
            arr[index - 1] = arr[index];
        }
    }

    public void insertEnd(int[] arr, int n, int length, int capacity) {
        if (length < capacity) {
            arr[length] = n;
        }
    }

    public void insertMiddle(int[] arr, int i, int n, int length) {
        // Shift starting from the end to i.
        for (int index = length - 1; index > i - 1; index--) {
            arr[index + 1] = arr[index];
        }
        // Insert at i
        arr[i] = n;
    }

    /*
     * You are given a binary array nums, return the maximum number of consecutive 1's in the array.
     * 1 <= nums.length <= 100,000
     * nums[i] is either 0 or 1
     */
    // Basic Iteration
    // Time complexity: O(n), Space complexity: O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, currentCount = 0;
        for (int num : nums) {
            currentCount = (num == 1) ? currentCount + 1 : 0;
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    /*
     * You are given an integer array nums and an integer val. Your task is to remove all occurrences of val from nums
     * in-place.
     * After removing all occurrences of val, return the number of remaining elements, say k, such that the first k
     * elements of nums do not contain val.

     * Note:
     * The order of the elements which are not equal to val does not matter.
     * It is not necessary to consider elements beyond the first k positions of the array.
     * To be accepted, the first k elements of nums must contain only elements not equal to val.
     * Return k as the final result.
     *
     * Constraints:
     * 0 <= nums.length <= 100
     * 0 <= nums[i] <= 50
     * 0 <= val <= 100
     */
    // Two pointers
    // Time complexity: O(n), Space complexity: O(1)
    public int removeElement(int[] nums, int val) {
        int k = 0, n = nums.length;
        while (k < n) {
            if (nums[k] == val) {
                nums[k] = nums[--n];
            } else {
                k++;
            }
        }
        return k;
    }

    /*
     * You are given an array arr, replace every element in that array with the greatest element among the elements to
     * its right, and replace the last element with -1.
     * After doing so, return the array.
     */
    // Suffix Maximum Pattern
    // Time complexity: O(n), Space complexity: O(1)
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        int rightMax = -1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = rightMax;
            rightMax = Math.max(arr[i], rightMax);
        }
        return res;
    }

}