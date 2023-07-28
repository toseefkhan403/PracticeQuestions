package com.practice.arrays;

import static java.lang.Math.min;

public class TrappingRainwater {
    public static void main(String[] args) {
        System.out.println(trapOptimized(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
    }

    // for every element, find leftmax and rightmax - O(n^2)
    public static int trapBrute(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int maxLeft = height[i];
            int maxRight = height[i];

            // i-1 to 0 - finding max element on the left
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > maxLeft) {
                    maxLeft = height[j];
                }
            }

            // i+1 to n-1 - finding max element on the right
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > maxRight) {
                    maxRight = height[j];
                }
            }

            // applying formula
            res += min(maxLeft, maxRight) - height[i];
        }

        return res;
    }

    // using space to reduce max element search time - O(3n), O(2n)
    public static int trapSpace(int[] height) {
        // preprocessing arrays to store maxLeft and maxRight
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        int maxSoFar = height[0];
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxSoFar) {
                maxSoFar = height[i];
            }
            left[i] = maxSoFar;
        }

        int maxSoFarRight = height[height.length - 1];
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > maxSoFarRight) {
                maxSoFarRight = height[i];
            }
            right[i] = maxSoFarRight;
        }

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            // applying formula
            res += min(left[i], right[i]) - height[i];
        }

        return res;
    }

    // 2 pointer approach - remember it - O(n), O(1)
    public static int trapOptimized(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int res = 0;

        while (left <= right) {
            // intuition: this condition makes sure there is someone with greater height on
            // right
            // therefore min(left,right) is not needed
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    // can't store water on max building
                    leftMax = height[left];
                } else {
                    // store water - min(left,right) is not needed
                    res += leftMax - height[left];
                }
                left++;
            } else {
                // similar stuff here
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }

        return res;
    }

    static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
