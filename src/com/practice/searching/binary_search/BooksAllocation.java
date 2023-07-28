package com.practice.searching.binary_search;

public class BooksAllocation {
    public static void main(String[] args) {
        // contiguous search space - therefore can apply binary search
        System.out.println(booksAllocation(new int[] { 12, 34, 67, 90 }, 2));
        System.out.println(shipWithinDays(new int[] { 12, 34, 67, 90 }, 2));
    }

    // binary search between max of the array and sum of the array - O(n*log(range =
    // high - low)), O(1)
    private static int booksAllocation(int[] nums, int students) {
        // someone will get 0 pages - invalid
        if (students > nums.length)
            return -1;

        int res = Integer.MAX_VALUE;
        int low = nums[0]; // max of the array
        int high = 0; // sum of the array
        for (int i : nums) {
            high += i;
            if (i > low)
                low = i;
        }

        while (low <= high) {
            int mid = (low + high) >> 1;
            if (isAllocatingPossible(mid, nums, students)) {
                // if possible, try to minimize the answer - go left
                high = mid - 1;
                res = mid;
            } else {
                // need more pages - go right
                low = mid + 1;
            }
        }

        // or return low - gives correct values
        return res;
    }

    // no one must get > barrier books - keep everyone close to the barrier - it
    // will minimize then
    private static boolean isAllocatingPossible(int barrier, int[] nums, int students) {
        int allocatingStds = 1;
        int pages = 0;

        for (int i = 0; i < nums.length; i++) {
            // barrier too small - can't allocate even one book
            if (nums[i] > barrier)
                return false;

            // checking if barrier is crossed - inc stds if crossed - else keep giving books
            if (pages + nums[i] > barrier) {
                allocatingStds++;
                // reset for new student
                pages = nums[i];
            } else {
                pages += nums[i];
            }
        }

        // allocatingStds should NOT be greater than the stds - hence >=
        return students >= allocatingStds;
    }

    // binary search between max of the array and sum of the array - O(n*logn)
    public static int shipWithinDays(int[] weights, int days) {
        int low = weights[0]; // max of the array
        int high = 0; // sum of the array

        for (int i : weights) {
            high += i;
            low = Math.max(low, i);
        }

        // return max of the array if length=days
        if (weights.length == days)
            return low;

        // binary search
        while (low <= high) {
            int mid = (low + high) >> 1;

            if (isShippingPossible(mid, weights, days)) {
                // if possible, try to minimize the answer - go left
                high = mid - 1;
            } else {
                // allocation not possible - inc barrier - go right
                low = mid + 1;
            }
        }

        // low gives the answer
        return low;
    }

    private static boolean isShippingPossible(int barrier, int[] weights, int days) {
        int allocatedWeight = 0;
        int allocatedDays = 1;

        for (int i = 0; i < weights.length; i++) {
            // can work better without this line
            // if (barrier < weights[i]) return false;

            // checking if barrier is crossed - inc days and reset weights if crossed - else
            // keep shipping weights
            if (allocatedWeight + weights[i] > barrier) {
                allocatedDays++;
                // reset for the new day
                allocatedWeight = weights[i];
            } else {
                allocatedWeight += weights[i];
            }
        }

        // allocatingDays should NOT be greater than the days - hence >=
        return days >= allocatedDays;
    }

}
