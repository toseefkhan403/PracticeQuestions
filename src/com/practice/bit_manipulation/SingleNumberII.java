package com.practice.bit_manipulation;

public class SingleNumberII {

    public static void main(String[] args) {
        // every element repeats thrice or k times - find only non repeating
        System.out.println(uniqueInK(new int[]{5,4,5,1,4,1,4,7,5,1}, 3));
    }

    // increasing bitcount at a particular position - % k - get binary eg. 101 - result - gives in decimal tho
    public static int uniqueInK(int[] nums, int k) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;
            for (int j = 0; j < nums.length; j++) {
                // finding ith set bit - not ==1 - keep !=0 ONLY - cuz it can be 0001000 and not found with ==1
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

    // submitted code
    public int singleNumber(int[] nums) {
        int result = 0;

        for(int i = 0; i<32; i++) {
            int bitcount=0;
            // for each position go thru all the elements of nums
            for(int j=0; j<nums.length; j++) {
                if((nums[j] & 1<<i) != 0) bitcount++;
            }

            result |= (bitcount%3) << i;
        }

        return result;
    }
    
}
