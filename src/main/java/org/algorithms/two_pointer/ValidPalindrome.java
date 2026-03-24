package org.algorithms.two_pointer;

public class ValidPalindrome {

    // Check if a string is a palindrome
    // O(n) time, O(1) space
    public boolean isPalindrome(String string) {
        int L = 0, R = string.length() - 1;
        while (L < R) {
            if (string.charAt(L) != string.charAt(R)) return false;
            L++;
            R--;
        }
        return true;
    }

}
