package com.practice.bit_manipulation;

public class SingleNumberIII {
    
    public static void main(String[] args) {
        // every element repeats twice - two non repeating elements
        twoUnique(new int[]{1,2,1,3,2,5});
        int[] res = singleNumber(new int[]{1,2,1,3,2,5});
        System.out.println(res[0]+","+res[1]);
    }

    // res = a^b
    // find rightmost set bit - one arr with that set bit - xor - get a - res ^ a = b
    public static void twoUnique(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }

        int rmsb = BitManipulation.rightMostSetBit(res);
        
        // can be done without using space
        int[] leftArr = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            // can do ==1 ONLY because shifting right by pos - else HAVE TO DO ==0 and check 1 with else condition
            if(BitManipulation.findBit(nums[i], rmsb) == 1) {
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

    // submitted solution - O(n),O(1)
    public static int[] singleNumber(int[] nums) {
        int n = nums.length;
        // xor all
        int res = 0;
        for(int i=0; i<n; i++) {
            res^=nums[i];
        }

        // find the rightmost set bit
        int temp = res;
        int i = 0;

        while(temp!=0) {
            if((temp&1) == 1) {
                break;
            }
            i++;
            temp = temp >> 1;
        }

        // xor leftArr with res
        int a = res;
        for(int j=0; j<n; j++) {
        // can do !=0 ONLY - not ==1
            if((nums[j] & (1<<i)) != 0) {
                a^=nums[j];
            }
        }

        // first element = a
        res^=a;
        // second element = res
        
        return new int[]{a, res};
    }
    
}
