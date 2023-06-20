package com.practice.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[] { -1, 4, 2, 1, 9, 10 }));
    }

    // trick: put elements in correct order - 1 2 3 4 5 - check at the end
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int element = nums[i];

            if (element > 0 && element <= n) {
                // put the element in its correct position
                int correctPosition = element - 1;

                // swap it only if its not at its correct place - else stuck in infinite loop
                if (element != nums[correctPosition]) {
                    int temp = nums[i];
                    nums[i] = nums[correctPosition];
                    nums[correctPosition] = temp;
                    // i-- to make sure new element get at its correct position
                    i--;
                }
            }
        }

        // return first element which is not at its correct position
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // everybody at their correct position - next number is the answer
        return n + 1;
    }

    // hashmap for i to n with true or false - first false is the ans
    public static int firstMissingPositiveSpace(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        // fill the map with 1 to n
        for (int i = 1; i <= nums.length; i++) {
            map.put(i, false);
        }

        // make present elements true
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                map.put(nums[i], true);
            }
        }

        // first false is the ans
        for (int i = 1; i <= nums.length; i++) {
            if (!map.get(i)) {
                return i;
            }
        }

        // everybody present - n+1 is the answer
        return nums.length + 1;
    }

    // store all elements in the hashset - then loop for i - if not found in the set
    // - return
    public static int firstMissingPositiveSpaceI(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        // everybody present - n+1 is the answer
        return nums.length + 1;
    }

    // find the posiive numbers one by one in the arr - O(n^2)
    public static int firstMissingPositiveBrute(int[] nums) {
        boolean iFound = false;

        for (int i = 1; i <= nums.length; i++) {
            // find i in the array
            // linear search
            iFound = false;
            for (int j = 0; j < nums.length; j++) {
                if (i == nums[j]) {
                    iFound = true;
                    break;
                }
            }

            if (!iFound)
                return i;
        }

        // everybody present - n+1 is the answer
        return nums.length + 1;
    }

    // put elements in correct order - 1 2 3 4 5 - check at the end
    public static int first_missing_positive(int n, List<Integer> arr) {
        for (int i = 0; i < n; i++) {
            int element = arr.get(i);

            if (element > 0 && element <= n) {
                // put the element in its correct position
                int correctPosition = element - 1;

                // swap it only if its not at its correct place - else stuck in infinite loop
                if (element != arr.get(correctPosition)) {
                    int temp = arr.get(i);
                    arr.set(i, arr.get(correctPosition));
                    arr.set(correctPosition, temp);
                    // i-- to make sure new element get at its correct position
                    i--;
                }
            }
        }

        // return first element which is not at its correct position
        for (int i = 0; i < n; i++) {
            if (arr.get(i) != i + 1) {
                return i + 1;
            }
        }

        // everybody at their correct position - next number is the answer
        return n + 1;
    }
}
