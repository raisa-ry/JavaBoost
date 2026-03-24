package org.algorithms.two_pointer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidPalindromeTest {

    private final ValidPalindrome validPalindrome = new ValidPalindrome();

    @Test
    void isPalindrome() {
        assertTrue(validPalindrome.isPalindrome("racecar"));
        assertFalse(validPalindrome.isPalindrome("hello"));
        assertTrue(validPalindrome.isPalindrome("a"));
        assertTrue(validPalindrome.isPalindrome(""));
    }

}