package com.github.mia.y2025;

import java.util.ArrayList;
import java.util.List;

import com.github.mia.Solvable;

public class SolverDay09 implements Solvable<Long> {

    @Override
    public Long solvePartI(List<String> input) {
        List<Coordinates> coordinates = new ArrayList<>(input.size());
        for (String line : input) {
            String[] parts = line.split(",");
            coordinates.add(new Coordinates(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        long max = 0;
        for(int i = 0; i < coordinates.size(); i++) {
            Coordinates coordinatesA = coordinates.get(i);
            for(int j = i + 1; j < coordinates.size(); j++) {
                Coordinates coordinatesB = coordinates.get(j);
                int dx = Math.abs(coordinatesA.x - coordinatesB.x) + 1;
                int dy = Math.abs(coordinatesA.y - coordinatesB.y) + 1;
                long area = (long) dx * dy;
                max = Math.max(area, max);
            }
        }
        return max;
    }

    @Override
    public Long solvePartII(List<String> input) {
        throw new UnsupportedOperationException("yet to be implemented");
    }

    record Coordinates(int x, int y) {}
}
