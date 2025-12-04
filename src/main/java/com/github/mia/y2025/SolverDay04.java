package com.github.mia.y2025;

import java.util.List;

import com.github.mia.Solvable;

public class SolverDay04 implements Solvable<Integer> {

    private static final int MIN_NEIGHBOURS = 4;

    @Override
    public Integer solvePartI(List<String> input) {
        int[][] grid = null;
        int n = 0;
        int rowIndex = 0;
        for (String line : input) {
            //init
            if (grid == null) {
                n = line.length();
                grid = new int[n][n];
            }
            //build the matrix
            for (int c = 0; c < n; c++) {
                grid[rowIndex][c] = line.charAt(c) == '@' ? 1 : 0;
            }
            rowIndex++;
        }

        int res = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; //R,L,D,U,diags..
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                int paperRolls = 0;
                for (int[] dir : dirs) {
                    int newRow = i + dir[0];
                    int newCol = j + dir[1];
                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                        if (grid[newRow][newCol] == 1) {
                            paperRolls++;
                        }
                    }
                }
                if (paperRolls < MIN_NEIGHBOURS) {
                    res++;
                }
            }
        }
        return res;
    }

    @Override
    public Integer solvePartII(List<String> input) {

        int[][] grid = null;
        int n = 0;
        int rowIndex = 0;
        for (String line : input) {
            //init
            if (grid == null) {
                n = line.length();
                grid = new int[n][n];
            }
            //build the matrix
            for (int c = 0; c < n; c++) {
                grid[rowIndex][c] = line.charAt(c) == '@' ? 1 : 0;
            }
            rowIndex++;
        }

        int res = 0;
        boolean isAnyPaperRemoved = true;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; //R,L,D,U,diags..
        while (isAnyPaperRemoved) {
            isAnyPaperRemoved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    int paperRolls = 0;
                    for (int[] dir : dirs) {
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                            if (grid[newRow][newCol] == 1) {
                                paperRolls++;
                            }
                        }
                    }
                    if (paperRolls < 4) {
                        res++;
                        grid[i][j] = 0;
                        isAnyPaperRemoved = true;
                    }
                }
            }
        }
        return res;
    }
}
