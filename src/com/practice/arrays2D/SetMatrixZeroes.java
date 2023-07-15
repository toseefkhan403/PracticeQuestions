package com.practice.arrays2D;

public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 0, 7, 8 }, { 0, 10, 11, 12 }, { 13, 14, 15, 0 } };
        print2dArr(matrix);
        setZeroesOpti(matrix);
        print2dArr(matrix);
    }

    // do the better approach using first row and col as the required space
    // use boolean for is first row or col zero - more intuitive - O(2*m*n), O(1)
    public static void setZeroesOpti(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // use first row and col as extra space
        // for the first row - matrix[0][0] will handle
        // for the first col
        int col0 = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;

                    if (j != 0) {
                        matrix[0][j] = 0;
                    } else {
                        col0 = 0;
                    }
                }
            }
        }

        System.out.println();
        print2dArr(matrix);
        System.out.println();

        // don't touch the first row and col - else will make everyone 0 if a single 0
        // is present
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // do em at the end
        // for row --> - do matrix[0][0] first
        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // for col | - do col later as it might override matrix[0][0]
        if (col0 == 0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // extra space to see if row or col will be 0 - will be 0 if atleast 1 element
    // is 0 - fill space then check and fill matrix with 0
    public static void setZeroesBetter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] col = new int[n]; // -->
        int[] row = new int[m]; // |

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    // whenever found 0 - go and make row and col 0 - O(n^3)
    public static void setZeroesBrute(int[][] matrix) {
        int flag = Integer.MAX_VALUE - 10; // anything random except 0

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // make row and col 0
                    for (int row = 0; row < matrix.length; row++) {
                        // don't touch original 0s - else it won't convert other rows n cols in the next
                        // iterations
                        if (matrix[row][j] != 0) {
                            // j(=col) constant
                            // can't do 0 - else will fill wrong 0 in the next iterations
                            matrix[row][j] = flag;
                        }
                    }

                    for (int col = 0; col < matrix[0].length; col++) {
                        if (matrix[i][col] != 0) {
                            // i(=row) constant
                            matrix[i][col] = flag;
                        }
                    }
                }
            }
        }

        // remove all flags
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == flag) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    public static void print2dArr(int[][] arr) {
        for (int[] is : arr) {
            for (int is2 : is) {
                System.out.print(is2 + " ");
            }
            System.out.println();
        }
    }
}
