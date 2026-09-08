package org.algorithms.two_pointers;

/*
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the
i-th line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104

*/

// Два указателя + жадный алгоритм с доказательством
// Time O(n), space O(1)
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int pointer1 = 0;
        int pointer2 = height.length - 1;
        int currentMaxArea = 0;
        while (pointer1 < pointer2) {
            int h1 = height[pointer1];
            int h2 = height[pointer2];
            int axisX = pointer2 - pointer1;
            int currentArea = Math.min(h1, h2) * axisX;
            currentMaxArea = Math.max(currentMaxArea, currentArea);

            if (h1 < h2) {
                pointer1++;
            } else {
                pointer2--;
            }
        }
        return currentMaxArea;
    }

}