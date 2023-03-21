package com.practice.arrays;

public class PlusOne {

    public static void main(String[] args) {
        
        int[] res = plusOne(new int[]{9,8,7,6,5,4,3,2,1,0});
        for (int i : res) {
            System.out.println(i);
        }
    }

    // increment the last element - take care of 10's case
    // cant do conversions - from num to array or vice versa - int overflows
    // start from the end - if <9 increment 1 and return - else put 0 there and loop over - handle 99999 case(all 0's - 1 in the start)
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            
            digits[i] = 0;
        }

        int[] res = new int[digits.length+1];
        res[0] = 1;

        return res;
    }
    
}
