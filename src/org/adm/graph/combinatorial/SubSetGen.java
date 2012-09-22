package org.adm.graph.combinatorial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 9/20/12
 * Time: 10:07 PM
 * create all subsets based off a given array of numbers e.g array
 * [5,6,7]  will have subsets {5,6}, {5,6,7}, {5,7} and so on...
 */
public class SubSetGen {
    public static void main(String[] args) {
        SubSetGen g = new SubSetGen();
        g.backtrack(new int[]{11, 12, 13}, -1, new ArrayList<Integer>());
    }

    void backtrack(int[] numSet, int k, List<Integer> input) {
        if (isSolution(numSet, k, input)) {
            solutionFound(numSet, k, input);
        } else {
            k++;
            int[] options = new int[]{1, 0};
            for (int option : options) {
                input.add(k, option);
                backtrack(numSet, k, input);
            }

        }

    }

    private void solutionFound(int[] nums, int k, List<Integer> input) {
        StringBuilder sb = new StringBuilder(input.size());
        sb.append("{");
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (input.get(i) == 1) {
                sb.append(num);
                sb.append(",");
            }
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        System.out.println(sb);
    }

    private boolean isSolution(int[] nums, int k, List<Integer> input) {
        return k == nums.length - 1;
    }
}
