package com.practice.graphs;

public class FloodFill {
    // do dfs once - it will cover all the connected cells
    // O(4*n*m[can visit 4 ways for cells in the grid]), O(n*m[aux space])
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int fromColor = image[sr][sc];
        // IMPORTANT - if starting point is already toColor, no need to fill - else
        // stackoverflow
        if (fromColor == color)
            return image;
        // no need for visited array - image itself will act as visited array
        dfs(sr, sc, image, fromColor, color, image.length, image[0].length);
        return image;
    }

    public void dfs(int i, int j, int[][] image, int fromColor, int toColor, int m, int n) {
        image[i][j] = toColor;

        // visit neighbours - can also use delta arrays in a single for loop instead of
        // 4 if statements
        // go right
        if (j + 1 < n && image[i][j + 1] == fromColor) {
            dfs(i, j + 1, image, fromColor, toColor, m, n);
        }

        // go left
        if (j - 1 >= 0 && image[i][j - 1] == fromColor) {
            dfs(i, j - 1, image, fromColor, toColor, m, n);
        }

        // go down
        if (i + 1 < m && image[i + 1][j] == fromColor) {
            dfs(i + 1, j, image, fromColor, toColor, m, n);
        }

        // go up
        if (i - 1 >= 0 && image[i - 1][j] == fromColor) {
            dfs(i - 1, j, image, fromColor, toColor, m, n);
        }
    }

}
