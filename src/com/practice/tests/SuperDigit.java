package com.practice.tests;

import java.math.BigInteger;

public class SuperDigit {
    public static void main(String[] args) {
        String numberString = "123"; // Input integer as a string
        int k = 3; // Number of times to concatenate the string

        int digitSum = calculateDigitSum(numberString, k);
        System.out.println("Sum of digits until a single digit: " + digitSum);
    }

    public static int calculateDigitSum(String numberString, int k) {
        int number = Integer.parseInt(numberString); // Parse input integer from string
        int sum = getDigitSum(number);

        int concatenatedSum = sum * k;
        int finalSum = getSingleDigitSum(concatenatedSum);

        return finalSum;
    }

    public static int getDigitSum(int num) {
        if (num < 10) {
            return num;
        }

        int digitSum = 0;
        while (num != 0) {
            digitSum += num % 10;
            num /= 10;
        }

        return getDigitSum(digitSum);
    }

    public static int getSingleDigitSum(int num) {
        if (num < 10) {
            return num;
        }

        return getSingleDigitSum(getDigitSum(num));
    }

    public static int superDigit(String n, int k) {
        int in = superHelper(Integer.parseInt(n));
        int res = 0;
        if(String.valueOf(in).length() > 1) {
            res = superHelper(in);
        } else {
            res = in;
        }

        System.out.println(res);

        int orig = res;
        for (int i = 0; i < k-1; i++) {
            res += orig;
        }

        if(String.valueOf(res).length() > 1) {
            res = superHelper(res);
        }

        return res;
    }

    public static int superHelper(int n) {
        if (n % 10 == 0) {
            return 0;
        }

        return n % 10 + superHelper(n / 10);
    }

    public static BigInteger fibonacciModified(BigInteger t1, BigInteger t2, int n) {
        return fibLong(t1, t2, n);
    }

    public static BigInteger fibLong(BigInteger t1, BigInteger t2, int n) {
        if (n == 1) {
            return t1;
        }
        if (n == 2) {
            return t2;
        }
        if (n == 3) {
            return t1.add((t2.multiply(t2)));
        }

        BigInteger x = fibLong(t1, t2, n - 2);
        BigInteger y = fibLong(t1, t2, n - 1);

        return x.add((y.multiply(y)));
    }

}
