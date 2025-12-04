package com.github.mia.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.mia.Solvable;

public class SolverDay02 implements Solvable<Long> {

    @Override
    public Long solvePartI(List<String> input) {
        long res = 0;
        for (String line : input) {
            String[] ranges = line.split(",");
            for (String range : ranges) {
                String[] rangeArr = range.split("-");
                long start = Long.parseLong(rangeArr[0]);
                long end = Long.parseLong(rangeArr[1]);
                for (long i = start; i <= end; i++) {
                    String num = String.valueOf(i);
                    int len = num.length();
                    if (len % 2 != 0) {
                        continue;
                    }
                    int splitAt = len / 2;
                    String firstPart = num.substring(0, splitAt);
                    String secondPart = num.substring(splitAt);
                    if (firstPart.equals(secondPart)) {
                        res += i;
                    }
                }
            }
        }
        return res;
    }

    private static final Map<Integer, List<Integer>> LCMs = Map.of(
            2, List.of(1),
            3, List.of(1),
            4, List.of(1, 2),
            5, List.of(1),
            6, List.of(1, 2, 3),
            7, List.of(1),
            8, List.of(1, 2, 4),
            9, List.of(1, 3),
            10, List.of(1, 2, 5));

    @Override
    public Long solvePartII(List<String> input) {
        long res = 0;
        for (String line : input) {
            String[] ranges = line.split(",");
            for (String range : ranges) {
                String[] rangeArr = range.split("-");
                long start = Long.parseLong(rangeArr[0]);
                long end = Long.parseLong(rangeArr[1]);
                for (long i = start; i <= end; i++) {
                    String num = String.valueOf(i);
                    int len = num.length();
                    if (len < 2) {
                        continue;
                    }
                    List<Integer> splitAts = LCMs.get(len);

                    for (int splitAt : splitAts) {
                        if (isDigitsRepeated(num, splitAt)) {
                            res += i;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    private static boolean isDigitsRepeated(String num, int splitAt) {
        List<String> nums = new ArrayList<>();
        for (int i = 0; i <= num.length() - splitAt; i += splitAt) {
            nums.add(num.substring(i, i + splitAt));
        }
        for (int i = 1; i < nums.size(); i++) {
            if (!nums.get(i).equals(nums.get(i - 1))) {
                return false;
            }
        }
        return true;
    }
}
