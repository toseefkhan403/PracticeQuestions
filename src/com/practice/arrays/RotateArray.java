package com.practice.arrays;

public class RotateArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

        rotateReverse(nums, 3);

        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    // rotate once k times
    public static void rotateBrute(int[] nums, int k) {
        k = k % nums.length;
        for (int i = 0; i < k; i++) {
            rotateOnce(nums);
        }
    }

    // shift elements to right - put last in front
    public static void rotateOnce(int[] nums) {
        int last = nums[nums.length-1];
        for (int i = nums.length-1; i > 0; i--) {
            // swap i with i-1
            int temp = nums[i];
            nums[i] = nums[i-1];
            nums[i-1] = temp;
        }
        nums[0] = last;
    }

    // same thought as single rotate - just using space to store multiple values - shift rest - put stored in front
    private static void rotateSpace(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if(n < 2 || k == 0) return;
        int[] kpart = new int[k];

        int c = 0;
        // keeping k part separate
        for (int i = n-k; i < n; i++) {
            kpart[c++] = nums[i];
        }

        // swapping after k places
        for (int i = n-1; i >= k; i--) {
            // swap i with i-k
            nums[i] = nums[i] ^ nums[i-k];
            nums[i-k] = nums[i] ^ nums[i-k];
            nums[i] = nums[i] ^ nums[i-k];
        }
 
        // fill the first k elements
        c = 0;
        while(c<k) {
            nums[c] = kpart[c];
            c++;
        }
    }

    // reverse 0 to n-1-k(original) - reverse rest(k part) - reverse whole array
    public static void rotateReverse(int[] nums, int k) {
        if(nums.length < 2){
            return;
        }
        
        k = k % nums.length;
        // reverse 0 to n-1-k part
        reversePart(nums, 0, nums.length-1-k);

        // reverse n-k to n-1 part
        reversePart(nums, nums.length-k, nums.length-1);

        // reverse whole array
        reversePart(nums, 0, nums.length-1);
    }

    // 2 pointers - left and right - swap and come closer
    public static void reversePart(int nums[], int l, int r) {
        while(l<r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }

}
