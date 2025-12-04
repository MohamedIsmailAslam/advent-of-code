package com.github.mia.y2025;

import com.github.mia.Solvable;

public class SolverFactory {

    public static Solvable getSolver(int day) {
        return switch (day) {
            case 1 -> new SolverDay01();
            case 2 -> new SolverDay02();
            case 3 -> new SolverDay03();
            case 4 -> new SolverDay04();
            case 5 -> new SolverDay05();
            case 6 -> new SolverDay06();
            case 7 -> new SolverDay07();
            case 8 -> new SolverDay08();
            case 9 -> new SolverDay09();
            case 10 -> new SolverDay10();
            case 11 -> new SolverDay11();
            case 12 -> new SolverDay12();
            default -> throw new IllegalStateException("No solution exists for day: " + day);
        };
    }
}
