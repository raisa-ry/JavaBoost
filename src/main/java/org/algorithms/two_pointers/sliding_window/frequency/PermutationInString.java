package org.algorithms.two_pointers.sliding_window.frequency;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:

1 <= s1.length, s2.length <= 10^4
s1 and s2 consist of lowercase English letters.
 */

// Time O(n), space O(1)
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> countS1 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            countS1.put(c, countS1.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> countWindow = new HashMap<>();
        int p1 = 0;

        for (int p2 = 0; p2 < s2.length(); p2++) {
            char c = s2.charAt(p2);
            countWindow.put(c, countWindow.getOrDefault(c, 0) + 1);

            if (p2 - p1 + 1 > s1.length()) {
                char leftChar = s2.charAt(p1);
                countWindow.put(leftChar, countWindow.get(leftChar) - 1);

                if (countWindow.get(leftChar) == 0) {
                    countWindow.remove(leftChar);
                }
                p1++;
            }

            if (p2 - p1 + 1 == s1.length() && countS1.equals(countWindow)) {
                return true;
            }
        }

        return false;
    }

}
