package org.algorithms.two_pointers.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
Given a string s, find the length of the longest substring without duplicate characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:

0 <= s.length <= 105
s consists of English letters, digits, symbols and spaces.
 */

// Variable-size sliding window
// Time O(n), space O(min(n, m), m - set of possible symbols size
public class LongestSubstringWithoutRepeatingChars {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        Map<Character, Integer> currentWindow = new HashMap<>();

        int p1 = 0;
        int maxLength = 0;

        for (int p2 = 0; p2 < s.length(); p2++) {
            char currentChar = s.charAt(p2);
            if (currentWindow.containsKey(currentChar) && currentWindow.get(currentChar) >= p1) {
                p1 = currentWindow.get(currentChar) + 1;
            }
            currentWindow.put(currentChar, p2);
            maxLength = Math.max(maxLength, p2 - p1 + 1);
        }
        return maxLength;
    }

}
