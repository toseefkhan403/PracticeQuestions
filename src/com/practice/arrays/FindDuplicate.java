package com.practice.arrays;

public class FindDuplicate {
    public static void main(String[] args) {

    }

    // same as find LL cycle start - go from element to index - cycle
    // duplicate element is the cycle start because it appears more than once - thus
    // forms a cycle and its start
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // do while if need to update before the loop condn check
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // go to start
        fast = nums[0];

        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // use (boolean array - faster) hashset to see if element appears again
    public int findDuplicateSpace(int[] nums) {
        boolean[] haveVisited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            // first check then put
            if (haveVisited[nums[i]]) {
                return nums[i];
            }
            haveVisited[nums[i]] = true;
        }

        // no duplicates
        return -1;
    }

    // look for duplicate on the right hand side of every element
    public int findDuplicateBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }

        return -1;
    }

}
