package com.practice.recursion;

public class Recursion {
    public static void main(String[] args) {
        // n*m matrix ways
        System.out.println(nmMatrixWays(4, 3));

        // josephus problem
        System.out.println(josephusProblem(5, 3));

        // palindrome string
        System.out.println(isPalin("abcba",0,"abcba".length()-1));

        // string powerset
        powerset("abc", 0, "");
        System.out.println();
        
        // string permutations
        printPermutn("abc", "");

        printFib(10);
    }

    private static int nmMatrixWays(int rows, int columns) {
        if(rows == 1 || columns == 1) return 1;
        return nmMatrixWays(rows-1, columns) + nmMatrixWays(rows, columns-1);
    }

    private static int josephusProblem(int n, int k) {
        if(n == 1) return 0;
        return (josephusProblem(n-1, k) + k)%n;
    }

    private static boolean isPalin(String s, int l, int r) {
        if(l >= r) return true;
        if(s.charAt(l) != s.charAt(r)) return false;
        return isPalin(s, l+1, r-1);
    }

    private static void powerset(String s, int i, String curr) {

        if(i==s.length()) {
            System.out.print(curr + " ");
            return;
        }

        powerset(s, i+1, curr+s.charAt(i));
        powerset(s,i+1,curr);
    }

    private static void printPermutn(String str, String ans) {
 
        // If string is empty
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return;
        }
 
        for (int i = 0; i < str.length(); i++) {
 
            // ith character of str
            char ch = str.charAt(i);
 
            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +
                        str.substring(i + 1);
 
            // Recursive call
            printPermutn(ros, ans + ch);
        }
    }

    private static int aTob(int a, int b) {
        if(b == 1) {
            return a;
        }

        return a*aTob(a, b-1);
    }

    // O(2^n), O(n)
    private static int fib(int n) {
        if(n == 0 || n == 1) {
            return n;
        }
        
        return fib(n-1)+fib(n-2);
    }

    private static void printFib(int n) {

        int first = 0;
        int second = 1;
        int third = first+second;
        int counter = 0;

        while(counter<n) {
            System.err.print(first+ " ");
            first = second;
            second = third;
            third = first+second;
            counter++;
        }
        
    }
}
