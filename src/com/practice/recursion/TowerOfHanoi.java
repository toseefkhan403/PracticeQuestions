package com.practice.recursion;

public class TowerOfHanoi {

    public static void main(String[] args) {
        towerOfHanoi(4, 'A', 'B', 'C');
    }

    static void towerOfHanoi(int n, char source, char aux, char dest) {
        if(n == 1) {
            System.out.println("move from " + source + " to " + dest);
            return;
        }

        // move n-1 discs from A to B using C as aux
        towerOfHanoi(n-1, source, dest, aux);

        // move nth disc from A to C blindly
        System.out.println("move from " + source + " to " + dest);

        // move n-1 discs from B to C using A as aux
        towerOfHanoi(n-1, aux, source, dest);
    }
    
}
