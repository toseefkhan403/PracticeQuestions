package com.practice.sorting;

public class SortBasics {
    public static void main(String[] args) {
        int[] nums = { 5, 4, 9, 2, 1, 100, 4, 99 };
        insertionSort(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    // push the biggest guy to the end in each iteration
    // compare adjacent elements and swap them if they are in the wrong order -
    // repeat - O(n^2), O(1)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean isSwapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSwapped = true;
                }
            }
            // optimization - not swapped in this iteration - already sorted - break
            if (!isSwapped)
                break;
        }
    }

    // Insert each guy to its correct position in the sorted part of the array
    // Shift the elements greater than the current key to the right - without
    // swapping - O(n^2), O(1)
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // find min in the rest of the array - swap it in front - repeat - O(n^2), O(1)
    public static void selectionSort(int[] arr) {
        // no need to check the last element because the last element is already sorted
        // if n-1 elements are sorted
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            // swap i with min
            swap(arr, i, min);
        }
    }

    private static void swap(int[] arr, int j, int i) {
        // if try to swap same elements - both become 0
        if (arr[i] == arr[j]) {
            return;
        }

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
