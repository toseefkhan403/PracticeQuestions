package com.practice.arrays;

public class Stocks1 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }
    
    // calc minSoFar - try to sell every day - cal maxProfit - return maxProfit
    private static int maxProfit(int[] prices) {

        int minSoFar = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < minSoFar) minSoFar = prices[i];
            int diff = prices[i] - minSoFar;
            if(diff>maxProfit) maxProfit = diff;
        }

        return maxProfit;
    }

    // preprocessing - make aux array from the end storing maxSoFar prices - then cal diff on same index - calc maxProfit 
    private static int maxProfitSpace(int[] prices) {
        int[] aux = new int[prices.length];

        int maxSoFar = 0;
        for (int i = prices.length-1; i >= 0; i--) {
            if(prices[i] > maxSoFar) maxSoFar = prices[i];
            aux[i] = maxSoFar;
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if(aux[i] - prices[i] > profit)
                profit = aux[i] - prices[i];
        }

        return profit;
    }

    // brute force - 2 loops - one for buy other for sell on all forward days - return maxProfit
    private static int maxProfitBrute(int[] prices) {
        int maxDiff = 0;
        for (int i = 0; i < prices.length; i++) {
            int diff = 0;
            for (int j = i+1; j < prices.length; j++) {
                diff = prices[j] - prices[i];
                if(diff > maxDiff) maxDiff = diff;
            }
        }
        
        return maxDiff;
    }
}
