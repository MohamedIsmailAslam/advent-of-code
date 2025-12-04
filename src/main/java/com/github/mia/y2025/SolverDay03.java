package com.github.mia.y2025;

import java.util.List;

import com.github.mia.Solvable;

public class SolverDay03 implements Solvable<Long> {

    @Override
    public Long solvePartI(List<String> input) {
        int firstMax = -1;
        int secondMax = -1;
        long res = 0;

        for (String line : input) {
            int len = line.length();
            for (int i = 0; i < len; i++) {
                int value = line.charAt(i) - '0';
                if (value > firstMax && i < len - 1) {
                    firstMax = value;
                    secondMax = -1; //reset
                } else if (value > secondMax) {
                    secondMax = value;
                }
            }
            int maxNum = (firstMax * 10) + secondMax;
            res += maxNum;
        }
        return res;
    }

    private static final int DIGITS = 12;

    @Override
    public Long solvePartII(List<String> input) {
        long res = 0;
        for (String line : input) {
            int[] digits = new int[12];
            int len = line.length();
            for (int i = 0; i < len; i++) {
                int value = line.charAt(i) - '0';
                int startIndex = Math.max(0, DIGITS - (len - i));
                for (int j = startIndex; j < DIGITS; j++) {
                    if (value > digits[j]) {
                        digits[j] = value;
                        if (j < DIGITS - 1) {
                            digits[j + 1] = -1; //reset
                        }
                        break;
                    }
                }
            }
            //build the number
            StringBuilder num = new StringBuilder();
            for (int n : digits) {
                num.append(n);
            }
            res += Long.parseLong(num.toString());
        }
        return res;
    }
}
