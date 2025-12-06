package com.github.mia.y2025;

import com.github.mia.Solvable;

import java.util.List;

public class SolverDay06 implements Solvable<Long> {

    @Override
    public Long solvePartI(List<String> input) {
        String[] mathOps = input.getLast().split("\\s+");
        long[] answers = new long[mathOps.length];
        for (int i = 0; i < input.size() - 1; i++) {
            String[] numbers = input.get(i).trim().split("\\s+");
            for (int j = 0; j < numbers.length; j++) {
                long num = Long.parseLong(numbers[j]);
                if ("+".equals(mathOps[j])) {
                    answers[j] += num;
                } else {
                    if (i == 0) answers[j] = 1;
                    answers[j] *= num;
                }
            }
        }

        long res = 0;
        for (long ans : answers) {
            res += ans;
        }

        return res;
    }

    @Override
    public Long solvePartII(List<String> input) {
        String[] mathOps = input.getLast().split("\\s+");
        long[] answers = new long[mathOps.length];
        int cursor = 0;
        int opsIndex = 0;
        int maxCursor = maxLineLength(input);
        boolean isFirstNumber = true;
        while(cursor < maxCursor) {
            StringBuilder numBuilder = new StringBuilder();
            for (int i = 0; i < input.size() - 1; i++) {
                int len = input.get(i).length();
                if(cursor < len) {
                    char c = input.get(i).charAt(cursor);
                    if(c != ' ') numBuilder.append(c);
                }
            }
            String numText = numBuilder.toString();
            if (!numText.isBlank()) {
                long num = Long.parseLong(numText);
                if ("+".equals(mathOps[opsIndex])) {
                    answers[opsIndex] += num;
                } else {
                    if (isFirstNumber) {
                        answers[opsIndex] = 1;
                        isFirstNumber = false;
                    }
                    answers[opsIndex] *= num;
                }
            }
            else {
                opsIndex++;
                isFirstNumber = true;
            }
            cursor++;
        }

        long res = 0;
        for (long ans : answers) {
            res += ans;
        }

        return res;
    }

    private static int maxLineLength(List<String> lines) {
        return lines.stream().mapToInt(String::length).max().orElseThrow();
    }
}
