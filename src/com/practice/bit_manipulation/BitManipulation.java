package com.practice.bit_manipulation;

public class BitManipulation {

    public static void main(String[] args) {
        // find, set, clear ith bit
        System.out.println(findBit(309, 5));
        System.out.println(setBit(309, 3));
        System.out.println(clearBit(309, 4));

        // find no. of bits to change to convert a to b
        System.out.println(noOfBitsToChange(22, 27));
    }

    public static int rightMostSetBit(int num) {
        int rightMostSetBitPos = 0;

        while(num!=0) {
            if((num & 1) == 1) break;
            num = num >> 1;
            rightMostSetBitPos++;
        }
        
        return rightMostSetBitPos;
    }

    public static int findBit(int num, int pos) {
        return (num & 1<< pos) >> pos;
        // int mask = 1 << pos;
        // num = num & mask;
        // return num==0 ? 0 : 1;
    }

    public static int setBit(int num, int pos) {
        return num | 1<<pos;
        // int mask = 1<<pos;
        // num = num | mask;
        // return num;
    }

    public static int clearBit(int num, int pos) {
        return num & ~(1<<pos);
        // int mask = ~(1<<pos);
        // num = num & mask;
        // return num;
    }

    public static int noOfBitsToChange(int a, int b) {
        int res = a^b;
        int count = 0;
        // how many set bits in res? hammingWeight question
        while(res!=0) {
            res = res & res-1;
            count++;
        }

        return count;
    }


    
}
