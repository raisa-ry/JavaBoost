package org.algorithms.prefix_sums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuerySumTest {

    private final QuerySum querySum = new QuerySum();

    @Test
    void testQuerySumBasic() {
        int[] nums = {1, 2, 3, 4, 5};
        // Sum from index 1 to 3 should be 2 + 3 + 4 = 9
        int result = querySum.querySum(nums, 1, 3);
        assertEquals(9, result);
    }

    @Test
    void testQuerySumFromStart() {
        int[] nums = {1, 2, 3, 4, 5};
        // Sum from index 0 to 2 should be 1 + 2 + 3 = 6
        int result = querySum.querySum(nums, 0, 2);
        assertEquals(6, result);
    }

    @Test
    void testQuerySumToEnd() {
        int[] nums = {1, 2, 3, 4, 5};
        // Sum from index 2 to 4 should be 3 + 4 + 5 = 12
        int result = querySum.querySum(nums, 2, 4);
        assertEquals(12, result);
    }

    @Test
    void testQuerySumSingleElement() {
        int[] nums = {1, 2, 3, 4, 5};
        // Sum from index 2 to 2 should be 3
        int result = querySum.querySum(nums, 2, 2);
        assertEquals(3, result);
    }

    @Test
    void testQuerySumEntireArray() {
        int[] nums = {1, 2, 3, 4, 5};
        // Sum from index 0 to 4 should be 1 + 2 + 3 + 4 + 5 = 15
        int result = querySum.querySum(nums, 0, 4);
        assertEquals(15, result);
    }

    @Test
    void testQuerySumWithNegativeNumbers() {
        int[] nums = {-5, 10, -3, 8, -2};
        // Sum from index 1 to 3 should be 10 + (-3) + 8 = 15
        int result = querySum.querySum(nums, 1, 3);
        assertEquals(15, result);
    }

    @Test
    void testQuerySumWithAllNegatives() {
        int[] nums = {-1, -2, -3, -4, -5};
        // Sum from index 1 to 3 should be -2 + (-3) + (-4) = -9
        int result = querySum.querySum(nums, 1, 3);
        assertEquals(-9, result);
    }

    @Test
    void testQuerySumWithZeros() {
        int[] nums = {0, 0, 0, 0, 0};
        // Sum from index 1 to 3 should be 0
        int result = querySum.querySum(nums, 1, 3);
        assertEquals(0, result);
    }

    @Test
    void testQuerySumMixedWithZeros() {
        int[] nums = {1, 0, 2, 0, 3};
        // Sum from index 1 to 3 should be 0 + 2 + 0 = 2
        int result = querySum.querySum(nums, 1, 3);
        assertEquals(2, result);
    }

    @Test
    void testQuerySumSingleElementArray() {
        int[] nums = {42};
        // Sum from index 0 to 0 should be 42
        int result = querySum.querySum(nums, 0, 0);
        assertEquals(42, result);
    }

}