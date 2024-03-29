package com.practice.recursion;

import java.util.ArrayList;

public class RatMaze {
    public static void main(String[] args) {
        int n = 4;
        int[][] m = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 } };

        System.out.println(findPathOpti(m, n));
    }

    // O(4^(n*n)), O(n*n)
    public static ArrayList<String> findPathOpti(int[][] m, int n) {
        int vis[][] = new int[n][n];
        int di[] = {
                +1,
                0,
                0,
                -1
        };
        int dj[] = {
                0,
                -1,
                1,
                0
        };
        ArrayList<String> ans = new ArrayList<>();
        if (m[0][0] == 1)
            solve(0, 0, m, n, ans, "", vis, di, dj);
        return ans;
    }

    // with di and dj shortcut
    // 1 call in a for loop instead of 4
    private static void solve(int i, int j, int a[][], int n, ArrayList<String> ans, String move,
            int vis[][], int di[], int dj[]) {
        if (i == n - 1 && j == n - 1) {
            ans.add(move);
            return;
        }

        String dir = "DLRU";
        // 0->D, 1->L, 2->R, 3->U
        for (int ind = 0; ind < 4; ind++) {
            int nexti = i + di[ind];
            int nextj = j + dj[ind];

            // boundary checks and visited check and can we go there check
            if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n &&
                    vis[nexti][nextj] == 0 && a[nexti][nextj] == 1) {

                vis[i][j] = 1;
                solve(nexti, nextj, a, n, ans, move + dir.charAt(ind), vis, di, dj);
                // IMPORTANT - backtracking step
                vis[i][j] = 0;
            }
        }
    }

    // O(4^(n*n)), O(n*n)
    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> result = new ArrayList<>();
        if (m[0][0] == 0)
            return result;

        int[][] visited = new int[n][n];
        // need to mark this visited
        visited[0][0] = 1;
        ratRecur(m, visited, 0, 0, "", result, n);
        return result;
    }

    // try to go all four ways lexicographically - if you havent visited it before
    public static void ratRecur(int[][] matrix, int[][] visited, int row, int col, String str,
            ArrayList<String> result, int n) {
        // need to be on the last cell for the result
        if (row == n - 1 && col == n - 1) {
            result.add(str);
            return;
        }

        // down - have checks of outofbounds
        if (row + 1 <= n - 1 && matrix[row + 1][col] == 1 && visited[row + 1][col] == 0) {
            // mark then go ahead
            // can also do mark row,col - then go ahead
            visited[row + 1][col] = 1;
            // str+"D" - so we dont have to remove later
            ratRecur(matrix, visited, row + 1, col, str + "D", result, n);
            // unvisit when you come back from recursion
            visited[row + 1][col] = 0;
        }

        // can do it in single call in a for loop instead of four calls
        // left
        if (col > 0 && matrix[row][col - 1] == 1 && visited[row][col - 1] == 0) {
            visited[row][col - 1] = 1;
            ratRecur(matrix, visited, row, col - 1, str + "L", result, n);
            visited[row][col - 1] = 0;
        }

        // right
        if (col + 1 <= n - 1 && matrix[row][col + 1] == 1 && visited[row][col + 1] == 0) {
            visited[row][col + 1] = 1;
            ratRecur(matrix, visited, row, col + 1, str + "R", result, n);
            visited[row][col + 1] = 0;
        }

        // up
        if (row > 0 && matrix[row - 1][col] == 1 && visited[row - 1][col] == 0) {
            visited[row - 1][col] = 1;
            ratRecur(matrix, visited, row - 1, col, str + "U", result, n);
            visited[row - 1][col] = 0;
        }

    }

}
