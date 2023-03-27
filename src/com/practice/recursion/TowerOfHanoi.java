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

    // hackerrank problem
    public static int hanoi_tower(int n) {
        // Write your code here
            toh(n,'A','B','C');
            return steps;
    }
        
    static int steps = 0;
        
    public static void toh(int n, char source, char aux, char dest) {
        if(n==1) {
            // from source to dest
            steps++;
            return;
        }
            
        toh(n-1,source,dest,aux);
            
        steps++;

        // from A to C 
        toh(n-1, aux, source, dest);         
    }
    
}
