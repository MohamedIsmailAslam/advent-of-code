package com.github.mia.y2025;

import com.github.mia.Solvable;

import java.util.*;

public class SolverDay07 implements Solvable<Long> {

    @Override
    public Long solvePartI(List<String> input) {
        int rows = input.size();
        int cols = input.getFirst().length();
        char[][] grid = new char[rows][cols];
        int row = 0;
        Cell beamStartsAt = null;

        for (String line : input) {
            for (int col = 0; col < cols; col++) {
                char c = line.charAt(col);
                grid[row][col] = c;
                if (c == 'S') beamStartsAt = new Cell(row, col);
            }
            row++;
        }

        Queue<Cell> queue = new LinkedList<>();
        queue.add(beamStartsAt);
        boolean endOfGrid = false;
        long res = 0;
        Set<Cell> beams = new HashSet<>();

        //BFS
        while (!queue.isEmpty() && !endOfGrid) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Cell curBeam = queue.poll();
                int nextRow = curBeam.row + 1;
                int curCol = curBeam.col;
                if (grid[nextRow][curCol] == '^') {
                    res++;
                    if (curCol > 0 &&
                            beams.add(new Cell(nextRow, curCol - 1)))
                        queue.add(new Cell(nextRow, curCol - 1));
                    if (curCol < cols &&
                            beams.add(new Cell(nextRow, curCol + 1)))
                        queue.add(new Cell(nextRow, curCol + 1));

                } else {
                    if (beams.add(new Cell(nextRow, curCol)))
                        queue.add(new Cell(nextRow, curCol));
                }

                if (nextRow + 1 >= rows) {
                    endOfGrid = true;
                }
            }
        }

        return res;
    }

    @Override
    public Long solvePartII(List<String> input) {
        throw new UnsupportedOperationException("yet to be implemented");
    }

    record Cell(int row, int col) {
    }
}
