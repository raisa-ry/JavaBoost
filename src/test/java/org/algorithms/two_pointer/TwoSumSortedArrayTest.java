package org.algorithms.two_pointer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TwoSumSortedArrayTest {

    private final TwoSumSortedArray twoSumSortedArray = new TwoSumSortedArray();

    @Test
    void targetSumTest() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        int target = 7;
        int[] res = twoSumSortedArray.targetSum(nums, target);
        assertArrayEquals(new int[]{0, 5}, res);

        target = 10;
        res = twoSumSortedArray.targetSum(nums, target);
        assertArrayEquals(new int[]{3, 5}, res);

        target = 12;
        res = twoSumSortedArray.targetSum(nums, target);
        assertNull(res);
    }

}