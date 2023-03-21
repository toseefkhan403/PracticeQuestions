package com.practice.arrays;

public class Stocks2 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }

    // buying and selling as many times - if next element > curr -> add to profit - return profit
    private static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if(prices[i+1] > prices[i]) profit += prices[i+1] - prices[i];
        }

        return profit;
    }
    
}
