package com.practice.searching.binary_search;

public class SingleElementSorted {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 }));
    }

    // brute: sorted so check with the right guy - O(n)
    // better: xor all - left with the ans - O(n)
    // optimal: binary search - take example and proceed - O(logn), O(1)

    // binary search - check neighbours of mid - check if mid is even or odd - even
    // = same side - odd = opposite side
    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        // not = as it doesn't work for single element
        while (low < high) {
            int mid = (low + high) / 2;

            // check left
            if (nums[mid - 1] == nums[mid]) {
                // which way to go? if mid is even - go where the element is found - go left
                if ((mid & 1) == 0) {
                    high = mid - 2;
                } else {
                    // if mid is odd - go the opposite of where the element is found - go right
                    low = mid + 1;
                }
            }
            // check right
            else if (nums[mid + 1] == nums[mid]) {
                // if mid is even - go where the element is found - go right
                if ((mid & 1) == 0) {
                    low = mid + 2;
                } else {
                    // if mid is odd - go the opposite of where the element is found - go left
                    high = mid - 1;
                }
            } else {
                // single element
                return nums[mid];
            }

        }

        // low = high right now - can do (low+high)/2 as well
        return nums[low];
    }

    // check mid even or odd first - then check left and right neighbours
    public int singleNonDuplicateI(int[] nums) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (mid % 2 == 0) {
                // on the side where you find dup
                if (mid > 0 && nums[mid] == nums[mid - 1]) {
                    // on the left side
                    high = mid - 2;
                } else if (mid <= n - 2 && nums[mid] == nums[mid + 1]) {
                    // on the right side
                    low = mid + 2;
                } else {
                    // single element found
                    return nums[mid];
                }
            } else {
                // on the opp side where you find dup
                if (mid > 0 && nums[mid] == nums[mid - 1]) {
                    // on the right side
                    low = mid + 1;
                } else if (mid <= n - 2 && nums[mid] == nums[mid + 1]) {
                    // on the left side
                    high = mid - 1;
                } else {
                    // single element found
                    return nums[mid];
                }
            }
        }

        return -1;
    }

}
