package com.practice.sliding_window;

import java.util.HashMap;

public class LongestSubarraySumK {

    public static void main(String[] args) {
        int arr[] = { 10, 5, 2, 7, 1, 9 };
        System.out.println(longestSubarraySumK(arr, 15));
    }

    // instead of this, take all subarrays to find maxLength
    private static int longestSubarraySumKBrute(int[] arr, int k) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int maxLength = Integer.MIN_VALUE;
        int n = arr.length;

        while(start<n) {
            while(end<n && sum+arr[end]<=k) {
                sum+=arr[end];
                if(sum == k) {
                    maxLength = Math.max(maxLength, end-start+1);
                }
                end++;
            }

            sum = 0;
            start++;
            // to make it work - but essentially its brute force now
            end = start;
        }

        return maxLength;
    }

    // variable-size sliding window: 2 pointers - if <k inc j - if =k compute max - if >k remove and inc i till >k
    // does not work if negative numbers are there
    private static int longestSubarraySumK(int[] arr, int k) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int maxLength = Integer.MIN_VALUE;
        int n = arr.length;
        
        while(j<n) {
            sum+=arr[j];
            if(sum < k) {
                // j++;
            } else if(sum == k) {
                maxLength = Math.max(maxLength, j-i+1);
                // j++;
            } else if(sum > k) {
                while(sum > k) {
                    sum -= arr[i];
                    i++;
                }

                // for safety maybe? haven't triggered in my test case
                if(sum == k){
                    maxLength = Math.max(maxLength, j-i+1);
                }
                // j++;
            }

            j++;
        }

        // O(N), O(1)
        return maxLength;
    }

    // store currsum with index in hashmap - in the loop look for currsum-sum - answer is index+1 till i - calc maxLength
    // similar to subarraywithgivensum - O(N), O(N)
    public static int findMaxLength(int[] nums, int sum) {
        int currSum = 0; int maxLength = 0;
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            currSum+=nums[i];

            if(currSum==sum) {
                maxLength = Math.max(maxLength, i+1);
            }

            if(map.containsKey(currSum-sum)) {
                maxLength = Math.max(maxLength, i-map.get(currSum-sum));
            }

            map.put(currSum, i);
        }

        return maxLength;
    }
    
}
