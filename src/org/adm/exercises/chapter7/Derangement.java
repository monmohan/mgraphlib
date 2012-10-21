package org.adm.exercises.chapter7;

/**
 * Created by IntelliJ IDEA.
 * User: monmohas
 * Date: 9/23/12
 * Time: 9:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Derangement {
    public static void main(String[] args) {
        Derangement pg = new Derangement();
        int[] nums = new int[]{1, 2, 3, 4};
        boolean[] options = new boolean[nums.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = true;

        }
        pg.backtrack(nums, -1, new ArrayList<Integer>(), options);
    }

    void backtrack(int[] nums, int k, ArrayList<Integer> input, boolean[] options) {
        if (isSolution(nums, k, input)) {
            solutionFound(nums, k, input);
        } else {
            k++;
            for (int i = 0, optionsLength = options.length; i < optionsLength; i++) {
                if (options[i] && !(nums[i] == nums[k])) {
                    options[i] = false;
                    input.setOrAdd(k, nums[i]);
                    backtrack(nums, k, input, options);
                    options[i] = true;
                }
            }

        }

    }

    private void solutionFound(int[] nums, int k, ArrayList<Integer> input) {
        StringBuilder sb = new StringBuilder(input.size());
        sb.append("{");
        for (Integer anInput : input) {
            sb.append(anInput);
            sb.append(",");
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        System.out.println(sb);
    }

    private boolean isSolution(int[] nums, int k, ArrayList<Integer> state) {
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
