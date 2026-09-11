package org.algorithms.two_pointers.sliding_window.frequency;

import java.util.HashMap;
import java.util.Map;

/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.

Constraints:

1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length
 */

// Time O(n), space O(1)
public class LongestRepeatingCharsReplacement {

    public int characterReplacement(String s, int k) {
        if (s.isEmpty()) return 0;

        int maxLength = 0;
        int maxFreq = 0;
        Map<Character, Integer> count = new HashMap<>();
        int p1 = 0;
        for (int p2 = 0; p2 < s.length(); p2++) {
            char c = s.charAt(p2);
            count.put(c, count.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(c));

            int currentLength = p2 - p1 + 1;
            if (currentLength - maxFreq > k) {
                count.put(s.charAt(p1), count.get(s.charAt(p1)) - 1);
                p1++;
            }

            maxLength = Math.max(maxLength, p2 - p1 + 1);
        }

        return maxLength;
    }

}
