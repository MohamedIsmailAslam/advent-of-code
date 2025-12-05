package com.github.mia;

import static com.github.mia.util.FileReader.printBanner;
import static com.github.mia.util.FileReader.readFile;

import java.util.List;
import java.util.Scanner;

public class Runner {

    private static final String SOLVED_YEARS = "[2025]";
    private static final String SOLVED_DAYS = "[1 - 12]";

    public static void main(String[] args) {
        printBanner();
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Choose a year from " + SOLVED_YEARS);
        //int year = scanner.nextInt();
        int year = 2025;

        System.out.println("Choose a day from " + SOLVED_DAYS);
        int day = scanner.nextInt();

        System.out.println("Do you want to run with example input [y/n] ?");
        boolean testrun = "y".equalsIgnoreCase(scanner.next());

        System.out.println(String.format("Solving day %d of aoc %d", day, year));

        List<String> input = readFile(year, day, testrun);
        Solvable solver = getSolver(year, day);
        solver.execute(input);
    }

    private static Solvable getSolver(int year, int day) {
        return switch (year) {
            case 2025 -> com.github.mia.y2025.SolverFactory.getSolver(day);
            default -> throw new IllegalStateException("No solution exists for year: " + year);
        };
    }
}
