package com.practice.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement1 {
    public static void main(String[] args) {

    }

    // same approach as better - use map to reduce search time in the second loop
    // O(n),O(n)
    public static int[] nextGreaterElementOpti(int[] nums1, int[] nums2) {
        // since there are no duplicates, we can store them in a map
        Map<Integer, Integer> map = new HashMap<>();
        // no need for res - use nums1 itself - as it has same length
        Stack<Integer> stack = new Stack<>();

        // go from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {
            int el = nums2[i];
            // runs for atmost n times - thus not O(n^2)
            while (!stack.isEmpty()) {
                if (stack.peek() > el) {
                    map.put(el, stack.peek());
                    break;
                }
                stack.pop();
            }
            stack.push(el);
        }

        // second loop
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }

    // algo: push arr elements to stack from the end - keep popping till you find
    // greater element - add it to ans if found - else add -1 - using LIFO property
    // calc ans for nums2 - match index for nums1 and add to res
    public static int[] nextGreaterElementBetter(int[] nums1, int[] nums2) {
        // use map instead of arr - faster
        int[] ans = new int[nums2.length];
        int[] res = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();

        // go from right to left
        for (int i = nums2.length - 1; i >= 0; i--) {
            int el = nums2[i];
            boolean found = false;
            while (!stack.isEmpty()) {
                // peek first - pop later
                int top = stack.peek();
                if (top > el) {
                    ans[i] = top;
                    found = true;
                    break;
                }
                stack.pop();
            }

            if (!found)
                ans[i] = -1;
            stack.push(el);
        }

        for (int i = 0; i < nums1.length; i++) {
            // find index in nums2 - return corresponding index from ans
            // can use map to reduce search time
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    res[i] = ans[j];
                    break;
                }
            }

        }

        return res;
    }

    // find element in nums2 - go its right - if > element found, add to ans and
    // break
    public static int[] nextGreaterElementBrute(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            int el = nums1[i];

            // find it in nums2 and find the next greater element
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == el) {
                    boolean found = false;
                    // go to the right and check
                    for (int k = j; k < nums2.length; k++) {
                        if (nums2[k] > el) {
                            ans[i] = nums2[k];
                            found = true;
                            // don't forget
                            break;
                        }
                    }

                    if (!found) {
                        ans[i] = -1;
                    }
                    // no need to traverse further
                    break;
                }
            }
        }

        return ans;
    }

}
