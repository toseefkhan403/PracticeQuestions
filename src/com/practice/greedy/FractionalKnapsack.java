package com.practice.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    // add those guys first whose value per weight is highest - if cant add fully,
    // add fractional value at the end - O(nlogn+n), O(1)
    double fractionalKnapsack(int W, Item arr[], int n) {
        // sort - desc - value per unit weight
        Arrays.sort(arr, new ItemComparator());

        int currWeight = 0;
        double totalValue = 0;

        for (int i = 0; i < n; i++) {
            Item item = arr[i];
            if (currWeight + item.weight <= W) {
                currWeight += item.weight;
                totalValue += item.value;
            } else {
                // add fractionally
                int capLeft = W - currWeight;
                double vpw = (double) item.value / (double) item.weight;
                totalValue += vpw * capLeft;

                // knapsack full - cannot add more
                break;
            }
        }

        return totalValue;
    }

    // put high value boxes first - as many as you can - then add the remaining
    // capacity if cant add all
    // value is multiple of wt - unlike FK - O(nlogn+n), O(1)
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // sort - desc - highest value first
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int currWeight = 0;
        int totalValue = 0;

        // add one by one - dont add fractionally
        for (int i = 0; i < boxTypes.length; i++) {
            int[] item = boxTypes[i];
            int weight = item[0];
            int value = item[1];

            if (currWeight + weight <= truckSize) {
                // add all
                currWeight += weight;
                totalValue += value * weight;
            } else {
                // add what's left
                int capLeft = truckSize - currWeight;
                totalValue += value * capLeft;

                // truck full - cannot add more
                break;
            }
        }

        return totalValue;
    }

}

class Item {
    int value, weight;

    Item(int x, int y) {
        this.value = x;
        this.weight = y;
    }

}

class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        double vpw1 = (double) o1.value / (double) o1.weight;
        double vpw2 = (double) o2.value / (double) o2.weight;

        // which is greater? if 1 -> swap, if -1 dont swap
        if (vpw1 < vpw2) {
            // o2 greater - descending - swap
            return 1;
        } else {
            return -1;
        }
    }
}
