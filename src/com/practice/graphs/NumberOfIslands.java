package com.practice.graphs;

public class NumberOfIslands {
    // counting disconnected components
    // count the number of dfs traversals you need to do to visit all the islands -
    // O(4*n*m[going in 4 directions]), O(n*m[for the visited array and aux space])
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    dfs(i, j, visited, grid, m, n);
                }
            }
        }

        return res;
    }

    // instead of adjacency list, go left up right and down - if 1 and not visited,
    // continue dfs
    private void dfs(int i, int j, boolean[][] visited, char[][] grid, int m, int n) {
        visited[i][j] = true;

        // go to the neighbours
        // go right
        if (j + 1 < n && grid[i][j + 1] == '1' && !visited[i][j + 1]) {
            dfs(i, j + 1, visited, grid, m, n);
        }

        // go left
        if (j - 1 >= 0 && grid[i][j - 1] == '1' && !visited[i][j - 1]) {
            dfs(i, j - 1, visited, grid, m, n);
        }

        // go down
        if (i + 1 < m && grid[i + 1][j] == '1' && !visited[i + 1][j]) {
            dfs(i + 1, j, visited, grid, m, n);
        }

        // go up
        if (i - 1 >= 0 && grid[i - 1][j] == '1' && !visited[i - 1][j]) {
            dfs(i - 1, j, visited, grid, m, n);
        }
    }

    // go 8 directions in a single loop - if 1 and not visited,continue dfs - gives
    // wrong answer
    private void dfs8(int i, int j, boolean[][] visited, char[][] grid, int m, int n) {
        visited[i][j] = true;

        // go to the neighbours - 2 loops instead of 8 if statements
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int row = i + di;
                int col = j + dj;
                // boundary checks, can we go there check and visited check
                if (row >= 0 && col >= 0 && row < m && col < n && grid[row][col] == '1' && !visited[row][col]) {
                    dfs8(row, col, visited, grid, m, n);
                }
            }
        }
    }

}
