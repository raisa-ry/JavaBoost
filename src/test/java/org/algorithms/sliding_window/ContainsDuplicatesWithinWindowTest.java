package org.algorithms.sliding_window;

import org.algorithms.two_pointers.sliding_window.ContainsDuplicatesWithinWindow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainsDuplicatesWithinWindowTest {

    private final ContainsDuplicatesWithinWindow containsDuplicatesWithinWindow = new ContainsDuplicatesWithinWindow();

    @Test
    void containsDuplicatesTest() {
        int[] nums = {1, 2, 3, 2, 3, 3};
        boolean res = containsDuplicatesWithinWindow.containsDuplicate(nums, 2);
        assertTrue(res);

        int[] nums1 = {1, 2, 3, 4, 5, 6};
        boolean res1 = containsDuplicatesWithinWindow.containsDuplicate(nums1, 2);
        assertFalse(res1);
    }

}