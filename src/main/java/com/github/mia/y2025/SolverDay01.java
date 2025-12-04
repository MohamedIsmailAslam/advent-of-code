package com.github.mia.y2025;

import java.util.List;

import com.github.mia.Solvable;

public class SolverDay01 implements Solvable<Integer> {

    private static final int INIT_DIAL_POS = 50;
    private static final int MAX_DIAL_POS = 100;

    @Override
    public Integer solvePartI(List<String> input) {
        int curr = INIT_DIAL_POS;
        int res = 0;

        for (String line : input) {

            int shiftBy = Integer.parseInt(line.substring(1)) % MAX_DIAL_POS;
            int shiftDir = line.startsWith("L") ? -1 : 1;

            //new pos calc
            curr += (shiftBy * shiftDir);
            curr %= MAX_DIAL_POS;

            if(curr == 0) {
                res++;
            }else if(curr < 0) {
                curr += MAX_DIAL_POS;
            }

        }
        return res;
    }

    @Override
    public Integer solvePartII(List<String> input) {
        int prev = -1;
        int curr = INIT_DIAL_POS;
        int res = 0;

        for (String line : input) {

            int shiftBy = Integer.parseInt(line.substring(1));
            int shiftDir = line.startsWith("L") ? -1 : 1;

            int rotations = shiftBy/MAX_DIAL_POS;
            res += rotations;

            //new pos calc
            prev = curr;
            shiftBy %= MAX_DIAL_POS;
            curr += (shiftBy * shiftDir);

            if(curr < 0) {
                curr += MAX_DIAL_POS;
                if(prev != 0) res++;
            } else if (curr == 0 && curr != prev) {
                res++;
            }
            else if(curr >= MAX_DIAL_POS) {
                res++;
                curr %= MAX_DIAL_POS;
            }

        }
        return res;
    }
}
