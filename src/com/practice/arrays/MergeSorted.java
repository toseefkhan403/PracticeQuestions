package com.practice.arrays;

public class MergeSorted {

    public static void main(String[] args) {
        int[] res = merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        for (int i : res) {
            System.out.print(i+" ");
        }
    }

    // traverse from the end - 3 pointer approach - 1st and 2nd for nums and 3rd is insertIndex(from the end) - compare 1 and 2 and fill the array from the end
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = nums1.length-1;

        while(j>=0) {
            
            if(i>=0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
                k--;
            } else {
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }

        return nums1;
    }
    
}
