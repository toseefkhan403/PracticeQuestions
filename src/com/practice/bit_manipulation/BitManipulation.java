package com.practice.bit_manipulation;

public class BitManipulation {

    public static void main(String[] args) {
        // find, set, clear ith bit
        System.out.println(findBit(309, 5));
        System.out.println(setBit(309, 3));
        System.out.println(clearBit(309, 4));

        // find no. of bits to change to convert a to b
        System.out.println(noOfBitsToChange(22, 27));

        // every element repeats twice - two non repeating elements
        twoUnique(new int[]{5,4,1,4,3,5,1,2});

        // every element repeats thrice or k times - find only non repeating
        System.out.println(uniqueInK(new int[]{5,4,5,1,4,1,4,7,5,1}, 3));
    }

    // increasing bitcount at a particular position - % k - get binary eg. 101 - result - gives in decimal tho
    public static int uniqueInK(int[] nums, int k) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int j = 0; j < nums.length; j++) {
                // finding ith bit
                if((nums[j] & (1<<i)) != 0) {
                    bitCount++;
                }
            }
            
            if(bitCount % k == 1) {
                result |= 1<<i;
            }
        }

        return result;
    }

    // res = a^b
    // find rightmost set bit - one arr with that set bit - xor - get a - res ^ a = b
    public static void twoUnique(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }

        int rmsb = rightMostSetBit(res);
        
        int[] leftArr = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            if(findBit(nums[i], rmsb) == 1) {
                leftArr[i] = nums[i];
            } else {
                leftArr[i] = 0;
            }
        }

        int temp = res;

        for (int i = 0; i < leftArr.length; i++) {
            temp ^= leftArr[i];
        }

        int a = temp;
        int b = res^temp;

        System.out.println("First element: " + a);
        System.out.println("Second element: " + b);
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
