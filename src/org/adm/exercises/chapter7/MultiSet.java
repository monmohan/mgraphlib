package org.adm.exercises.chapter7;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 10/2/12
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MultiSet {
    private int[] options;
    static int total = 0;

    public static void main(String[] args) {
        MultiSet pg = new MultiSet();
        int[] nums = new int[]{1, 2, 2, 2, 4};
        Integer[] options = new Integer[nums.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = nums[i];

        }
        pg.backtrack(nums, -1, options, new Stack<Integer>());
        System.out.println("total = " + total);
    }


    void backtrack(int[] nums, int k, Integer[] options, Stack<Integer> partialSolution) {
        if (isSolution(nums, partialSolution, k)) {
            printSolution(partialSolution);
        } else {
            k++;
            Set<Integer> seen = new HashSet<Integer>();
            for (int i = 0; i < options.length; i++) {
                java.util.ArrayList<Integer> opt = new ArrayList<Integer>();
                int num = options[i];
                if (seen.add(num)) {
                    partialSolution.push(num);
                    for (int j = 0; j < options.length; j++) {
                        if (j != i) {
                            opt.add(options[j]);
                        }
                    }
                    backtrack(nums, k, opt.toArray(new Integer[opt.size()]), partialSolution);
                    partialSolution.pop();
                }

            }
        }


    }

    private void printSolution(Stack<Integer> partialSolution) {
        StringBuilder sb = new StringBuilder(partialSolution.size());
        sb.append("{");
        for (Integer anInput : partialSolution) {
            sb.append(anInput);
            sb.append(",");
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        System.out.println(sb);
        total++;

    }

    private boolean isSolution(int[] nums, Stack<Integer> partialSolution, int k) {
        return k == nums.length - 1;
    }

    //work around for Arraylist throwing exception on calling set() on a non-existent index
    static class ArrayList<E> extends java.util.ArrayList<E> {
        void setOrAdd(int index, E e) {
            if (this.size() > index && this.get(index) != null) {
                this.set(index, e);
            } else {
                this.add(e);
            }
        }
    }
}