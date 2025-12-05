package com.github.mia.y2025;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.mia.Solvable;

public class SolverDay05 implements Solvable<Long> {

    @Override
    public Long solvePartI(List<String> input) {

        List<long[]> ranges = new ArrayList<>();
        boolean rangeInput = true;
        long res = 0;
        for (String s : input) {
            if (s.isBlank()) {
                rangeInput = false;
                continue;
            }

            if (rangeInput) {
                String[] range = s.split("-");
                ranges.add(new long[]{Long.parseLong(range[0]), Long.parseLong(range[1])});
            }
            else {
                long number = Long.parseLong(s);
                for (int i = 0; i < ranges.size(); i++) {
                    if (number >= ranges.get(i)[0] && number <= ranges.get(i)[1]) {
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    @Override
    public Long solvePartII(List<String> input) {
        List<long[]> ranges = new ArrayList<>();
        long res = 0;
        for (String s : input) {
            if (s.isBlank()) {
                break;
            }
            String[] range = s.split("-");
            ranges.add(new long[]{Long.parseLong(range[0]), Long.parseLong(range[1])});
        }

        ranges.sort(Comparator.comparingLong(a -> a[0])); //sort by start

        List<long[]> mergedRanges = new ArrayList<>();
        mergedRanges.add(ranges.getFirst());

        //merging
        for (int i=1; i<ranges.size(); i++) {
            long[] range = ranges.get(i);
            long start =  range[0];
            long end = range[1];
            long[] endOfLastInterval = mergedRanges.getLast();

            if(endOfLastInterval[1] >= start) {
                endOfLastInterval[1] = Math.max(endOfLastInterval[1], end);
            }
            else {
                mergedRanges.add(range);
            }
        }
        //counting
        for (long[] range : mergedRanges) {
            long start = range[0] - 1;
            long end = range[1];
            long diff = end - start;
            res += diff;
        }

        return res;
    }
}
