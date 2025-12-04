package com.github.mia;

import java.util.List;

public interface Solvable<T> {

    T solvePartI(List<String> input);

    T solvePartII(List<String> input);

    default void execute(List<String> input){

        System.out.println("solving part 1...");
        long start = System.currentTimeMillis();
        T answer = solvePartI(input);
        System.out.println("answer: " + answer);
        System.out.println("runtime: " + (System.currentTimeMillis() - start) + "ms");

        System.out.println("solving part 2...");
        start = System.currentTimeMillis();
        answer = solvePartII(input);
        System.out.println("answer: " + answer);
        System.out.println("runtime: " + (System.currentTimeMillis() - start) + "ms");

    }
}
