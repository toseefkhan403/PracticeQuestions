package com.practice.bit_manipulation;

import java.lang.Math;

public class ReverseBits {

    public static void main(String[] args) {
        System.out.println(binaryToDecimal(new int[]{0,0,0,0,0,0,1,0,1,0,0,1,0,1,0,0,0,0,0,1,1,1,1,0,1,0,0,1,1,1,0,0}));
        System.out.println(binaryToDecimal(new int[]{1,0,0,1,0,0,1,0,1}));
    }

    // todo solution

    // works but int overflows
    public static int reverseBits(int n) {
        int[] res = new int[32];
        int i = 0;
        while(n!=0) {
            int last_bit = n&1;
            res[i++] = last_bit;
            n = n>>>1;
        }

        return binaryToDecimal(res);
    }

    public static int binaryToDecimal(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += Math.pow(2, nums.length-i-1) * nums[i];
        }

        return res;
    }
    
}
