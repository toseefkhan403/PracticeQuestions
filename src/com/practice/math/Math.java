package com.practice.math;

import java.util.Arrays;

public class Math {

    public static void main(String[] args) {
        // trailing zeroes
        System.out.println(trailingZeroes(50));

        // palindrome number
        System.out.println(palindromeNumber(12321));

        // prime no
        System.out.println(isPrime(17));

        // sieve of eratosthenes - prime till
        boolean[] isPrime = sieveOfEratosthenes(21);
        for (int i = 0; i < isPrime.length; i++) {
            System.out.println(i + " is " + (isPrime[i] ? "prime" : "not prime"));
        }

        // gcd
        System.out.println(gcd(12, 60));

        // modulo arithmetics - fastpower
        System.out.println(fastPower(0.5, 214748368));

        // compute a^b%n
        System.out.println(fastPowerModulo(34, 5, 1000007));
    }

    static int trailingZeroes(int n) {
        int res = 0;
        for (int i = 5; i <= n; i = i * 5) {
            res += n / i;
        }
        return res;
    }

    static boolean palindromeNumber(int n) {
        int orig = n;
        int mir = 0;
        while (n > 0) {
            int last = n % 10;
            mir = mir * 10 + last;
            n /= 10;
        }

        if (mir == orig) {
            return true;
        }

        return false;
    }

    static boolean isPrime(int n) {
        if (n == 1)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            for (int j = i * 2; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }

        return isPrime;
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // O(logN) TC instead of O(N)
    public static double fastPower(double x, int n) {
        double res = 1;

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * x;
            }
            x = x * x;
            n = n >>> 1;
        }

        return res;
    }

    public static long fastPowerModulo(long x, long n, long mod) {
        long res = 1;

        while (n > 0) {
            if ((n & 1) != 0) {
                res = (res * x % mod) % mod;
            }
            x = (x % mod * x % mod) % mod;
            n = n >> 1;
        }

        return res;
    }

}
