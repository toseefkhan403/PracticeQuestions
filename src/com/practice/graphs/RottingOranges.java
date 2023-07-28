package com.practice.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    // not DFS as it does not cover all the neighbours at the same time - goes from
    // neighbour to neighbour to neighbour and so on

    // BFS traversal - covers all the neighbours at the same time - add the starting
    // pts(2s) to the queue - keep visited arr(same as the input) - keep count to
    // check if any 1s left at the end - O(5*n*m), O(2*n*m)
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] vis = new int[m][n];
        int freshCount = 0;
        Queue<Cell> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = grid[i][j];

                if (grid[i][j] == 2) {
                    // add the starting pt to the q
                    q.offer(new Cell(i, j));
                }

                if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        int rottenCount = 0;
        int time = -1;

        // start the traversal
        while (!q.isEmpty()) {
            int size = q.size();
            time++;

            // need the for loop - as two rotten are rotting together at any time
            for (int t = 0; t < size; t++) {
                // the polled oranges are always rotten
                Cell cell = q.poll();

                // go to its neighbours and make em rotten - left, up, down, right
                int[] delRow = { -1, 0, 0, 1 };
                int[] delCol = { 0, -1, 1, 0 };
                for (int i = 0; i < 4; i++) {
                    int r = cell.i + delRow[i];
                    int c = cell.j + delCol[i];

                    // make only fresh guys rotten
                    if (r >= 0 && c >= 0 && r < m && c < n && vis[r][c] == 1) {
                        vis[r][c] = 2;
                        rottenCount++;
                        q.offer(new Cell(r, c));
                    }
                }
            }
        }

        if (rottenCount < freshCount)
            return -1;

        // if no rotten oranges at all
        if (time < 0) {
            return 0;
        }

        return time;
    }

}

class Cell {
    int i;
    int j;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

}
