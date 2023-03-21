package com.practice.bit_manipulation;

public class NumberOf1Bits {

    public static void main(String[] args) {
        System.out.println(hammingWeight(00000000000000000000000000001011));
    }

    // right shift trick
    public static int rightShift(int n) {
        int count = 0;
        while(n!=0) {
            int last_bit = n&1;
            if(last_bit != 0) count++;
            n = n >>> 1;
        }

        return count;
    }

    // n&n-1 trick
    public static int hammingWeight(int n) {
        int count = 0;
        while(n!=0) {
            n= n&n-1;
            count++;
        }

        return count;
    }
    
}
