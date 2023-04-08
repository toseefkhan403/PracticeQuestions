package com.practice.sliding_window;

import java.util.Arrays;
import java.util.List;

public class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 }, 5));
        double[] res = findAllAvg(Arrays.asList(1, 3, 2, 6, -1, 4, 1, 8, 2), 5);
        for (double d : res) {
            System.out.println(d);
        }
    }

    // sliding window - size k
    // for loop for windowEnd - when windowSize==k, do calc and remove windowStart from sum and inc it
    public static double findMaxAverage(int[] nums, int k) {
        int windowStart = 0;
        double maxSum = Integer.MIN_VALUE;
        double windowSum = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            // can also do - windowEnd >= k-1 - as this if runs for every iteration after
            // windowEnd has crossed k-1
            if (windowEnd - windowStart + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return maxSum / k;
    }

    // brute force - outer loop till n-k - inner loop for k times (till i+k-1) -
    // O((n-k+1)*k)
    public static double[] findAllAvgBrute(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1]; // no of iterations -> count of avgs

        for (int i = 0; i <= n - k; i++) {
            double sum = 0;
            // runs for k times
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            res[i] = sum / k;
        }

        return res;
    }

    // sliding window
    public static double[] findAllAvg(List<Integer> nums, int k) {
        // edge case
        if (nums.size() < k) {
            throw new Error("K must be less than or equal to the size of the array");
        }

        int windowStart = 0;
        double[] res = new double[nums.size() - k + 1];
        double windowSum = 0;

        for (int windowEnd = 0; windowEnd < nums.size(); windowEnd++) {
            windowSum += nums.get(windowEnd);

            if (windowEnd - windowStart + 1 == k) {
                res[windowStart] = windowSum / k;
                windowSum -= nums.get(windowStart);
                windowStart++;
            }
        }

        return res;
    }
}
